package com.binarywang.wxjava.store.enums;

/**
 * 消息类型
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
public enum MessageType {
  EVENT("event"),
  ;

  private final String key;

  MessageType(String key) {
    this.key = key;
  }

  public String getKey() {
    return key;
  }
}
