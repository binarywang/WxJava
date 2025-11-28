package me.chanjar.weixin.qidian.util.json;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;

import java.io.File;
import java.util.Objects;

/**
 * @author someone
 */
public class WxQidianGsonBuilder {

  private static final GsonBuilder INSTANCE = new GsonBuilder();
  private static volatile Gson GSON_INSTANCE;

  static {
    INSTANCE.disableHtmlEscaping();

    INSTANCE.setExclusionStrategies(new ExclusionStrategy() {
      @Override
      public boolean shouldSkipField(FieldAttributes fieldAttributes) {
        return false;
      }

      @Override
      public boolean shouldSkipClass(Class<?> aClass) {
        return aClass == File.class || aClass == ApacheHttpClientBuilder.class;
      }
    });
  }

  public static Gson create() {
    if (Objects.isNull(GSON_INSTANCE)) {
      synchronized (INSTANCE) {
        if (Objects.isNull(GSON_INSTANCE)) {
          GSON_INSTANCE = INSTANCE.create();
        }
      }
    }
    return GSON_INSTANCE;
  }

}
