package com.binarywang.wxjava.store.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

/**
 * Json序列化/反序列化工具类
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Slf4j
public class JsonUtils {

  private static final JsonMapper JSON_MAPPER = new JsonMapper();

  static {
    JSON_MAPPER.enable(JsonReadFeature.ALLOW_JAVA_COMMENTS.mappedFeature());
    JSON_MAPPER.enable(JsonReadFeature.ALLOW_UNQUOTED_FIELD_NAMES.mappedFeature());
    JSON_MAPPER.enable(JsonReadFeature.ALLOW_SINGLE_QUOTES.mappedFeature());
    JSON_MAPPER.enable(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature());
    JSON_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    JSON_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    JSON_MAPPER.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);
  }

  private JsonUtils() {
  }

  /**
   * 对象序列化
   *
   * @param obj 对象
   * @return json
   */
  public static String encode(Object obj) {
    try {
      return JSON_MAPPER.writeValueAsString(obj);
    } catch (IOException e) {
      log.error("encode(Object)", e);
    }
    return null;
  }

  /**
   * 对象序列化
   *
   * @param objectMapper ObjectMapper
   * @param obj          obj
   * @return json
   */
  public static String encode(ObjectMapper objectMapper, Object obj) {
    try {
      return objectMapper.writeValueAsString(obj);
    } catch (IOException e) {
      log.error("encode(Object)", e);
    }
    return null;
  }

  /**
   * 将json反序列化成对象
   *
   * @param json      json
   * @param valueType Class<T>
   * @return T
   */
  public static <T> T decode(String json, Class<T> valueType) {
    if (json == null || json.length() <= 0) {
      return null;
    }
    try {
      return JSON_MAPPER.readValue(json, valueType);
    } catch (IOException e) {
      log.info("decode(String, Class<T>)", e);
    }
    return null;
  }

  /**
   * 将json反序列化为对象
   *
   * @param json          json
   * @param typeReference TypeReference<T>
   * @return T
   */
  public static <T> T decode(String json, TypeReference<T> typeReference) {
    try {
      return (T) JSON_MAPPER.readValue(json, typeReference);
    } catch (IOException e) {
      log.info("decode(String, JsonTypeReference<T>)", e);
    }
    return null;
  }
}
