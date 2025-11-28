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
 *   押金消费结果
 *   详见：<a href="https://pay.weixin.qq.com/wiki/doc/api/deposit_sl.php?chapter=27_7&index=4">https://pay.weixin.qq.com/wiki/doc/api/deposit_sl.php?chapter=27_7&index=4</a>
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
public class WxDepositConsumeResult extends BaseWxPayResult implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 微信订单号
   * transaction_id
   * 是
   * String(32)
   * 1217752501201407033233368018
   * 微信支付押金订单号
   * </pre>
   */
  @XStreamAlias("transaction_id")
  private String transactionId;

  /**
   * <pre>
   * 商户消费单号
   * out_trade_no
   * 是
   * String(32)
   * 20150806125346
   * 商户系统内部的消费单号
   * </pre>
   */
  @XStreamAlias("out_trade_no")
  private String outTradeNo;

  /**
   * <pre>
   * 消费金额
   * consume_fee
   * 是
   * Int
   * 88
   * 本次消费的金额，单位为分
   * </pre>
   */
  @XStreamAlias("consume_fee")
  private Integer consumeFee;

  /**
   * <pre>
   * 剩余押金
   * remain_fee
   * 是
   * Int
   * 11
   * 剩余押金金额，单位为分
   * </pre>
   */
  @XStreamAlias("remain_fee")
  private Integer remainFee;

  /**
   * <pre>
   * 消费时间
   * time_end
   * 是
   * String(14)
   * 20141030133525
   * 消费完成时间，格式为yyyyMMddHHmmss
   * </pre>
   */
  @XStreamAlias("time_end")
  private String timeEnd;

  @Override
  protected void loadXml(Document d) {
    transactionId = readXmlString(d, "transaction_id");
    outTradeNo = readXmlString(d, "out_trade_no");
    consumeFee = readXmlInteger(d, "consume_fee");
    remainFee = readXmlInteger(d, "remain_fee");
    timeEnd = readXmlString(d, "time_end");
  }
}