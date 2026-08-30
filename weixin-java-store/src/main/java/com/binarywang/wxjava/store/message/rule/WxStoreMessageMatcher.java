package com.binarywang.wxjava.store.message.rule;

import com.binarywang.wxjava.store.message.WxStoreMessage;

/**
 * 消息匹配器，用在消息路由的时候
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
public interface WxStoreMessageMatcher {

  /**
   * 消息是否匹配某种模式
   *
   * @param message 消息
   * @return 是否匹配
   */
  boolean match(WxStoreMessage message);

}
