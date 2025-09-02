package com.github.binarywang.wxpay.bean.result;

import lombok.Data;

import java.io.Serializable;

/**
 * <pre>
 * 微信支付v3结果共用属性类.
 * 用于保存原始JSON响应内容，便于访问API返回的新增字段.
 * </pre>
 *
 * @author Binary Wang
 */
@Data
public abstract class BaseWxPayV3Result implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 原始JSON字符串.
   * 保存微信支付v3 API返回的原始JSON内容，便于访问未在Result类中定义的字段.
   */
  private String rawJsonString;

  /**
   * 获取原始JSON响应内容.
   *
   * @return 原始JSON字符串
   */
  public String getRawJsonString() {
    return rawJsonString;
  }

  /**
   * 设置原始JSON响应内容.
   *
   * @param rawJsonString 原始JSON字符串
   */
  public void setRawJsonString(String rawJsonString) {
    this.rawJsonString = rawJsonString;
  }
}