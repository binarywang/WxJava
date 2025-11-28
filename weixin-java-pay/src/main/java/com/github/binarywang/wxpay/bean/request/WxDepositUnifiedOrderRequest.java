package com.github.binarywang.wxpay.bean.request;

import com.github.binarywang.wxpay.exception.WxPayException;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;
import me.chanjar.weixin.common.annotation.Required;

import java.util.Map;

/**
 * <pre>
 *   押金下单请求
 *   详见：<a href="https://pay.weixin.qq.com/wiki/doc/api/deposit_sl.php?chapter=27_7&index=2">https://pay.weixin.qq.com/wiki/doc/api/deposit_sl.php?chapter=27_7&index=2</a>
 * </pre>
 *
 * @author Binary Wang
 * created on  2024-09-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("xml")
public class WxDepositUnifiedOrderRequest extends BaseWxPayRequest {

  /**
   * <pre>
   * 押金商品描述
   * body
   * 是
   * String(128)
   * 共享单车押金
   * 押金商品描述
   * </pre>
   */
  @Required
  @XStreamAlias("body")
  private String body;

  /**
   * <pre>
   * 商户订单号
   * out_trade_no
   * 是
   * String(32)
   * 20150806125346
   * 商户系统内部的订单号,32个字符内、可包含字母, 其他说明见商户订单号
   * </pre>
   */
  @Required
  @XStreamAlias("out_trade_no")
  private String outTradeNo;

  /**
   * <pre>
   * 押金金额
   * total_fee
   * 是
   * Int
   * 99
   * 押金金额，单位为分，只能为整数，详见支付金额
   * </pre>
   */
  @Required
  @XStreamAlias("total_fee")
  private Integer totalFee;

  /**
   * <pre>
   * 终端IP
   * spbill_create_ip
   * 是
   * String(16)
   * 123.12.12.123
   * 用户端实际ip
   * </pre>
   */
  @Required
  @XStreamAlias("spbill_create_ip")
  private String spbillCreateIp;

  /**
   * <pre>
   * 通知地址
   * notify_url
   * 是
   * String(256)
   * http://www.weixin.qq.com/wxpay/pay.php
   * 接收微信支付异步通知回调地址
   * </pre>
   */
  @Required
  @XStreamAlias("notify_url")
  private String notifyUrl;

  /**
   * <pre>
   * 交易类型
   * trade_type
   * 是
   * String(16)
   * JSAPI
   * 交易类型，取值如下：JSAPI，NATIVE，APP，WAP
   * </pre>
   */
  @Required
  @XStreamAlias("trade_type")
  private String tradeType;

  /**
   * <pre>
   * 用户标识
   * openid
   * 否
   * String(128)
   * oUpF8uMuAJO_M2pxb1Q9zNjWeS6o
   * trade_type=JSAPI时，此参数必传，用户在商户appid下的唯一标识。
   * </pre>
   */
  @XStreamAlias("openid")
  private String openid;

  /**
   * <pre>
   * 商品详情
   * detail
   * 否
   * String(8192)
   * 详情
   * 商品名称明细列表
   * </pre>
   */
  @XStreamAlias("detail")
  private String detail;

  /**
   * <pre>
   * 附加数据
   * attach
   * 否
   * String(127)
   * 深圳分店
   * 附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
   * </pre>
   */
  @XStreamAlias("attach")
  private String attach;

  /**
   * <pre>
   * 货币类型
   * fee_type
   * 否
   * String(16)
   * CNY
   * 符合ISO 4217标准的三位字母代码，默认人民币：CNY
   * </pre>
   */
  @XStreamAlias("fee_type")
  private String feeType;

  /**
   * <pre>
   * 交易起始时间
   * time_start
   * 否
   * String(14)
   * 20091225091010
   * 订单生成时间，格式为yyyyMMddHHmmss
   * </pre>
   */
  @XStreamAlias("time_start")
  private String timeStart;

  /**
   * <pre>
   * 交易结束时间
   * time_expire
   * 否
   * String(14)
   * 20091227091010
   * 订单失效时间，格式为yyyyMMddHHmmss
   * </pre>
   */
  @XStreamAlias("time_expire")
  private String timeExpire;

  @Override
  protected void checkConstraints() throws WxPayException {
    if ("JSAPI".equals(this.tradeType) && this.openid == null) {
      throw new WxPayException("当trade_type为JSAPI时，openid为必填参数");
    }
  }

  @Override
  protected void storeMap(Map<String, String> map) {
    map.put("body", body);
    map.put("out_trade_no", outTradeNo);
    map.put("total_fee", totalFee.toString());
    map.put("spbill_create_ip", spbillCreateIp);
    map.put("notify_url", notifyUrl);
    map.put("trade_type", tradeType);
    if (openid != null) {
      map.put("openid", openid);
    }
    if (detail != null) {
      map.put("detail", detail);
    }
    if (attach != null) {
      map.put("attach", attach);
    }
    if (feeType != null) {
      map.put("fee_type", feeType);
    }
    if (timeStart != null) {
      map.put("time_start", timeStart);
    }
    if (timeExpire != null) {
      map.put("time_expire", timeExpire);
    }
  }
}