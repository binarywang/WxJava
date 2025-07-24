package me.chanjar.weixin.common.util.xml;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.thoughtworks.xstream.converters.basic.StringConverter;

/**
 * Integer型数组转换器.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on  2019-08-22
 */
public class IntegerArrayConverter extends StringConverter {
  @Override
  public boolean canConvert(Class type) {
    return type == Integer[].class;
  }

  @Override
  public String toString(Object obj) {
    return "<![CDATA[" + Joiner.on(",").join((Integer[]) obj) + "]]>";
  }

  @Override
  public Object fromString(String str) {

    if (str == null || str.isEmpty()) {
      return null;
    }

    final Iterable<String> iterable = Splitter.on(",").split(str);
    final String[] strings = Iterables.toArray(iterable, String.class);
    Integer[] result = new Integer[strings.length];
    int index = 0;
    for (String string : strings) {
      result[index++] = Integer.parseInt(string);
    }

    return result;
  }
}
