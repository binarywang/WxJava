package com.github.binarywang.wxpay.bean.notify;

/**
 * 微信支付公用回调
 *
 * @author Pursuer
 * @version 1.0
 * @since 2023/6/15
 * @param <T> 解密后的数据类型
 */
public interface WxPayBaseNotifyV3Result<T> {
  /**
   * 设置原始数据
   *
   * @param rawData 原始数据
   * @author Pursuer
   * @since 2023/6/15
   */
  void setRawData(OriginNotifyResponse rawData);

  /**
   * 解密后的数据
   *
   * @param data 解密后的数据
   * @author Pursuer
   * @since 2023/6/15
   */
  void setResult(T data);
}
