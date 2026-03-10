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
 *   押金退款结果
 *   详见：<a href="https://pay.weixin.qq.com/wiki/doc/api/deposit_sl.php?chapter=27_7&index=6">https://pay.weixin.qq.com/wiki/doc/api/deposit_sl.php?chapter=27_7&index=6</a>
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
public class WxDepositRefundResult extends BaseWxPayResult implements Serializable {

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
   * 商户退款单号
   * out_refund_no
   * 是
   * String(32)
   * 1217752501201407033233368018
   * 商户系统内部的退款单号
   * </pre>
   */
  @XStreamAlias("out_refund_no")
  private String outRefundNo;

  /**
   * <pre>
   * 微信退款单号
   * refund_id
   * 是
   * String(32)
   * 1217752501201407033233368018
   * 微信退款单号
   * </pre>
   */
  @XStreamAlias("refund_id")
  private String refundId;

  /**
   * <pre>
   * 退款金额
   * refund_fee
   * 是
   * Int
   * 100
   * 退款总金额,单位为分,可以做部分退款
   * </pre>
   */
  @XStreamAlias("refund_fee")
  private Integer refundFee;

  /**
   * <pre>
   * 现金退款金额
   * cash_refund_fee
   * 否
   * Int
   * 100
   * 现金退款金额，单位为分，只能为整数
   * </pre>
   */
  @XStreamAlias("cash_refund_fee")
  private Integer cashRefundFee;

  @Override
  protected void loadXml(Document d) {
    transactionId = readXmlString(d, "transaction_id");
    outRefundNo = readXmlString(d, "out_refund_no");
    refundId = readXmlString(d, "refund_id");
    refundFee = readXmlInteger(d, "refund_fee");
    cashRefundFee = readXmlInteger(d, "cash_refund_fee");
  }
}