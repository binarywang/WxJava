package com.github.binarywang.wxpay.bean.marketing.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 卡包跳转目标
 *
 * @author wangerwei
 */
@Getter
@AllArgsConstructor
public enum JumpTargetEnum {

  /**
   * PAYMENT_CODE：点击“立即使用”跳转至微信支付付款码
   */
  PAYMENT_CODE("PAYMENT_CODE"),

  /**
   * MINI_PROGRAM：点击“立即使用”跳转至配置的商家小程序（需要指定小程序appid和path）
   */
  MINI_PROGRAM("MINI_PROGRAM"),

  /**
   * DEFAULT_PAGE：点击“立即使用”跳转至默认页面
   */
  DEFAULT_PAGE("DEFAULT_PAGE");

  /**
   * 批次类型
   */
  private final String value;
}
