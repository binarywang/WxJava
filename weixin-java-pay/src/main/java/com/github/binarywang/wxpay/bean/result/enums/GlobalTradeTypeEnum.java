package com.github.binarywang.wxpay.bean.result.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 境外微信支付方式
 * Overseas WeChat Pay trade types with global endpoints
 *
 * @author Binary Wang
 */
@Getter
@AllArgsConstructor
public enum GlobalTradeTypeEnum {
  /**
   * APP
   */
  APP("/global/v3/transactions/app"),
  /**
   * JSAPI 或 小程序
   */
  JSAPI("/global/v3/transactions/jsapi"),
  /**
   * NATIVE
   */
  NATIVE("/global/v3/transactions/native"),
  /**
   * H5
   */
  H5("/global/v3/transactions/h5");

  /**
   * 境外下单url
   */
  private final String url;
}