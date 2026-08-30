package com.binarywang.wxjava.store.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import java.io.IOException;
import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;

/**
 * Xml序列化/反序列化工具类
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Slf4j
public class XmlUtils {

  private static final XmlMapper XML_MAPPER = new XmlMapper();

  static {
    XML_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    XML_MAPPER.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);
    // 带有xml文件头，<?xml version="1.0" encoding="utf-8" ?>
    XML_MAPPER.disable(ToXmlGenerator.Feature.WRITE_XML_DECLARATION);
  }

  private XmlUtils() {
  }

  /**
   * 对象序列化
   *
   * @param obj 对象
   * @return json
   */
  public static String encode(Object obj) {
    try {
      return XML_MAPPER.writeValueAsString(obj);
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
   * 将xml反序列化成对象
   *
   * @param xml       xml
   * @param valueType Class<T>
   * @return T
   */
  public static <T> T decode(String xml, Class<T> valueType) {
    if (xml == null || xml.length() <= 0) {
      return null;
    }
    try {
      return XML_MAPPER.readValue(xml, valueType);
    } catch (IOException e) {
      log.info("decode(String, Class<T>)", e);
    }
    return null;
  }

  /**
   * 将xml反序列化为对象
   *
   * @param xml           xml
   * @param typeReference TypeReference<T>
   * @return T
   */
  public static <T> T decode(String xml, TypeReference<T> typeReference) {
    try {
      return (T) XML_MAPPER.readValue(xml, typeReference);
    } catch (IOException e) {
      log.info("decode(String, TypeReference<T>)", e);
    }
    return null;
  }

  /**
   * 将xml反序列化为对象
   *
   * @param is        InputStream
   * @param valueType Class<T>
   * @return T
   */
  public static <T> T decode(InputStream is, Class<T> valueType) {
    try {
      return (T) XML_MAPPER.readValue(is, valueType);
    } catch (IOException e) {
      log.info("decode(InputStream, Class<T>)", e);
    }
    return null;
  }
}
