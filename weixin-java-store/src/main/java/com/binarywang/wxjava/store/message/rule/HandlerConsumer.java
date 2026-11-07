package com.binarywang.wxjava.store.message.rule;

import com.binarywang.wxjava.store.message.WxStoreMessage;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@FunctionalInterface
public interface HandlerConsumer<T extends WxStoreMessage, U, V, W, X> {

  void accept(T t, U u, V v, W w, X x);
}
