/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package me.chanjar.weixin.common.util.res;

import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * An internationalization / localization helper class which reduces
 * the bother of handling ResourceBundles and takes care of the
 * common cases of message formating which otherwise require the
 * creation of Object arrays and such.
 * <p>
 * <p>The StringManager operates on a package basis. One StringManager
 * per package can be created and accessed via the getManager method
 * call.
 * <p>
 * <p>The StringManager will look for a ResourceBundle named by
 * the package name given plus the suffix of "LocalStrings". In
 * practice, this means that the localized information will be contained
 * in a LocalStrings.properties file located in the package
 * directory of the classpath.
 * <p>
 * <p>Please see the documentation for java.util.ResourceBundle for
 * more information.
 *
 * @author James Duncan Davidson [duncan@eng.sun.com]
 * @author James Todd [gonzo@eng.sun.com]
 * @author Mel Martinez [mmartinez@g1440.com]
 * @see java.util.ResourceBundle
 */
public class StringManager {

  private static final Map<String, Map<Locale, StringManager>> MANAGERS = new ConcurrentHashMap<>();
  private static int LOCALE_CACHE_SIZE = 10;
  /**
   * The ResourceBundle for this StringManager.
   */
  private final ResourceBundle bundle;
  private final Locale locale;

  /**
   * Creates a new StringManager for a given package. This is a
   * private method and all access to it is arbitrated by the
   * static getManager method call so that only one StringManager
   * per package will be created.
   *
   * @param packageName Name of package to create StringManager for.
   */
  private StringManager(String packageName, Locale locale) {
    String bundleName = packageName + ".LocalStrings";
    ResourceBundle bnd = null;
    try {
      bnd = ResourceBundle.getBundle(bundleName, locale);
    } catch (MissingResourceException ex) {
      // Try from the current loader (that's the case for trusted apps)
      // Should only be required if using a TC5 style classloader structure
      // where common != shared != server
      ClassLoader cl = Thread.currentThread().getContextClassLoader();
      if (cl != null) {
        try {
          bnd = ResourceBundle.getBundle(bundleName, locale, cl);
        } catch (MissingResourceException ex2) {
          // Ignore
        }
      }
    }
    this.bundle = bnd;
    // Get the actual locale, which may be different from the requested one
    if (this.bundle != null) {
      Locale bundleLocale = this.bundle.getLocale();
      if (bundleLocale.equals(Locale.ROOT)) {
        this.locale = Locale.ENGLISH;
      } else {
        this.locale = bundleLocale;
      }
    } else {
      this.locale = null;
    }
  }

  /**
   * Get the StringManager for a particular package. If a manager for
   * a package already exists, it will be reused, else a new
   * StringManager will be created and returned.
   *
   * @param packageName The package name
   */
  public static synchronized StringManager getManager(
    String packageName) {
    return getManager(packageName, Locale.getDefault());
  }

  /**
   * Get the StringManager for a particular package and Locale. If a manager
   * for a package/Locale combination already exists, it will be reused, else
   * a new StringManager will be created and returned.
   *
   * @param packageName The package name
   * @param locale      The Locale
   */
  public static synchronized StringManager getManager(
    String packageName, Locale locale) {

    Map<Locale, StringManager> map = MANAGERS.get(packageName);
    if (map == null) {
      /*
       * Don't want the HashMap to be expanded beyond LOCALE_CACHE_SIZE.
       * Expansion occurs when size() exceeds capacity. Therefore keep
       * size at or below capacity.
       * removeEldestEntry() executes after insertion therefore the test
       * for removal needs to use one less than the maximum desired size
       *
       */
      map = new LinkedHashMap<Locale, StringManager>(LOCALE_CACHE_SIZE, 1, true) {
        private static final long serialVersionUID = 1L;

        @Override
        protected boolean removeEldestEntry(
          Map.Entry<Locale, StringManager> eldest) {
          return size() > (LOCALE_CACHE_SIZE - 1);
        }
      };
      MANAGERS.put(packageName, map);
    }

    StringManager mgr = map.get(locale);
    if (mgr == null) {
      mgr = new StringManager(packageName, locale);
      map.put(locale, mgr);
    }
    return mgr;
  }

  // --------------------------------------------------------------
  // STATIC SUPPORT METHODS
  // --------------------------------------------------------------

  /**
   * Retrieve the StringManager for a list of Locales. The first StringManager
   * found will be returned.
   *
   * @param requestedLocales the list of Locales
   * @return the found StringManager or the default StringManager
   */
  public static StringManager getManager(String packageName,
                                         Enumeration<Locale> requestedLocales) {
    while (requestedLocales.hasMoreElements()) {
      Locale locale = requestedLocales.nextElement();
      StringManager result = getManager(packageName, locale);
      if (result.getLocale().equals(locale)) {
        return result;
      }
    }
    // Return the default
    return getManager(packageName);
  }

  /**
   * Get a string from the underlying resource bundle or return
   * null if the String is not found.
   *
   * @param key to desired resource String
   * @return resource String matching <i>key</i> from underlying
   * bundle or null if not found.
   * @throws IllegalArgumentException if <i>key</i> is null.
   */
  public String getString(String key) {
    if (key == null) {
      String msg = "key may not have a null value";

      throw new IllegalArgumentException(msg);
    }

    String str = null;

    try {
      // Avoid NPE if bundle is null and treat it like an MRE
      if (this.bundle != null) {
        str = this.bundle.getString(key);
      }
    } catch (MissingResourceException mre) {
      //bad: shouldn't mask an exception the following way:
      //   str = "[cannot find message associated with key '" + key +
      //         "' due to " + mre + "]";
      //     because it hides the fact that the String was missing
      //     from the calling code.
      //good: could just throw the exception (or wrap it in another)
      //      but that would probably cause much havoc on existing
      //      code.
      //better: consistent with container pattern to
      //      simply return null.  Calling code can then do
      //      a null check.
      str = null;
    }

    return str;
  }

  /**
   * Get a string from the underlying resource bundle and format
   * it with the given set of arguments.
   *
   * @param key
   * @param args
   */
  public String getString(final String key, final Object... args) {
    String value = getString(key);
    if (value == null) {
      value = key;
    }

    MessageFormat mf = new MessageFormat(value);
    mf.setLocale(this.locale);
    return mf.format(args, new StringBuffer(), null).toString();
  }

  /**
   * Identify the Locale this StringManager is associated with
   */
  public Locale getLocale() {
    return this.locale;
  }
}
