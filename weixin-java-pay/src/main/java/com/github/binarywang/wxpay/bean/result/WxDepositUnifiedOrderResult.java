package com.github.binarywang.wxpay.bean.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.w3c.dom.Document;

import java.io.Serializable;

/**
 * <pre>
 *   押金下单结果
 *   详见：<a href="https://pay.weixin.qq.com/wiki/doc/api/deposit_sl.php?chapter=27_7&index=2">https://pay.weixin.qq.com/wiki/doc/api/deposit_sl.php?chapter=27_7&index=2</a>
 * </pre>
 *
 * @author Binary Wang
 * created on  2024-09-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("xml")
public class WxDepositUnifiedOrderResult extends BaseWxPayResult implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 交易类型
   * trade_type
   * 是
   * String(16)
   * JSAPI
   * 交易类型，取值为：JSAPI，NATIVE，APP等
   * </pre>
   */
  @XStreamAlias("trade_type")
  private String tradeType;

  /**
   * <pre>
   * 预支付交易会话标识
   * prepay_id
   * 是
   * String(64)
   * wx201410272009395522657a690389285100
   * 微信生成的预支付会话标识，用于后续接口调用中使用，该值有效期为2小时
   * </pre>
   */
  @XStreamAlias("prepay_id")
  private String prepayId;

  /**
   * <pre>
   * 二维码链接
   * code_url
   * 否
   * String(64)
   * URl：weixin：//wxpay/s/An4baqw
   * trade_type 为 NATIVE 时有返回，可将该参数值生成二维码展示出来进行扫码支付
   * </pre>
   */
  @XStreamAlias("code_url")
  private String codeUrl;

  /**
   * <pre>
   * 微信订单号
   * transaction_id
   * 是
   * String(32)
   * 1217752501201407033233368018
   * 微信支付分配的交易会话标识
   * </pre>
   */
  @XStreamAlias("transaction_id")
  private String transactionId;

  @Override
  protected void loadXml(Document d) {
    tradeType = readXmlString(d, "trade_type");
    prepayId = readXmlString(d, "prepay_id");
    codeUrl = readXmlString(d, "code_url");
    transactionId = readXmlString(d, "transaction_id");
  }
}