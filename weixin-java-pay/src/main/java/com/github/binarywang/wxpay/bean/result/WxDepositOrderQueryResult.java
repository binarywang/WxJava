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
 *   查询押金订单结果
 *   详见：<a href="https://pay.weixin.qq.com/wiki/doc/api/deposit_sl.php?chapter=27_7&index=3">https://pay.weixin.qq.com/wiki/doc/api/deposit_sl.php?chapter=27_7&index=3</a>
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
public class WxDepositOrderQueryResult extends BaseWxPayResult implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 微信订单号
   * transaction_id
   * 是
   * String(32)
   * 1217752501201407033233368018
   * 微信支付订单号
   * </pre>
   */
  @XStreamAlias("transaction_id")
  private String transactionId;

  /**
   * <pre>
   * 商户订单号
   * out_trade_no
   * 是
   * String(32)
   * 20150806125346
   * 商户系统内部订单号
   * </pre>
   */
  @XStreamAlias("out_trade_no")
  private String outTradeNo;

  /**
   * <pre>
   * 交易状态
   * trade_state
   * 是
   * String(32)
   * SUCCESS
   * 交易状态：
   * SUCCESS—支付成功
   * REFUND—转入退款
   * NOTPAY—未支付
   * CLOSED—已关闭
   * REVOKED—已撤销（付款码支付）
   * USERPAYING—用户支付中（付款码支付）
   * PAYERROR—支付失败(其他原因，如银行返回失败)
   * </pre>
   */
  @XStreamAlias("trade_state")
  private String tradeState;

  /**
   * <pre>
   * 交易状态描述
   * trade_state_desc
   * 是
   * String(256)
   * 支付成功
   * 对当前查询订单状态的描述和下一步操作的指引
   * </pre>
   */
  @XStreamAlias("trade_state_desc")
  private String tradeStateDesc;

  /**
   * <pre>
   * 押金金额
   * total_fee
   * 否
   * Int
   * 99
   * 订单总金额，单位为分
   * </pre>
   */
  @XStreamAlias("total_fee")
  private Integer totalFee;

  /**
   * <pre>
   * 现金支付金额
   * cash_fee
   * 否
   * Int
   * 99
   * 现金支付金额订单现金支付金额
   * </pre>
   */
  @XStreamAlias("cash_fee")
  private Integer cashFee;

  /**
   * <pre>
   * 支付完成时间
   * time_end
   * 否
   * String(14)
   * 20141030133525
   * 订单支付时间，格式为yyyyMMddHHmmss
   * </pre>
   */
  @XStreamAlias("time_end")
  private String timeEnd;

  /**
   * <pre>
   * 剩余押金
   * remain_fee
   * 否
   * Int
   * 88
   * 剩余押金金额，单位为分
   * </pre>
   */
  @XStreamAlias("remain_fee")
  private Integer remainFee;

  @Override
  protected void loadXml(Document d) {
    transactionId = readXmlString(d, "transaction_id");
    outTradeNo = readXmlString(d, "out_trade_no");
    tradeState = readXmlString(d, "trade_state");
    tradeStateDesc = readXmlString(d, "trade_state_desc");
    totalFee = readXmlInteger(d, "total_fee");
    cashFee = readXmlInteger(d, "cash_fee");
    timeEnd = readXmlString(d, "time_end");
    remainFee = readXmlInteger(d, "remain_fee");
  }
}