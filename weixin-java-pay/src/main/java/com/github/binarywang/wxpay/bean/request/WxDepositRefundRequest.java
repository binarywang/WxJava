package com.github.binarywang.wxpay.bean.request;

import com.github.binarywang.wxpay.exception.WxPayException;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;
import me.chanjar.weixin.common.annotation.Required;

import java.util.Map;

/**
 * <pre>
 *   押金退款请求
 *   详见：<a href="https://pay.weixin.qq.com/wiki/doc/api/deposit_sl.php?chapter=27_7&index=6">https://pay.weixin.qq.com/wiki/doc/api/deposit_sl.php?chapter=27_7&index=6</a>
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
public class WxDepositRefundRequest extends BaseWxPayRequest {

  /**
   * <pre>
   * 微信押金订单号
   * transaction_id
   * 否
   * String(32)
   * 1009660380201506130728806387
   * 微信押金订单号，与out_trade_no二选一
   * </pre>
   */
  @XStreamAlias("transaction_id")
  private String transactionId;

  /**
   * <pre>
   * 商户押金订单号
   * out_trade_no
   * 否
   * String(32)
   * 20150806125346
   * 商户系统内部的押金订单号，与transaction_id二选一
   * </pre>
   */
  @XStreamAlias("out_trade_no")
  private String outTradeNo;

  /**
   * <pre>
   * 商户退款单号
   * out_refund_no
   * 是
   * String(32)
   * 1217752501201407033233368018
   * 商户系统内部的退款单号，商户系统内部唯一，同一退款单号多次请求只退一笔
   * </pre>
   */
  @Required
  @XStreamAlias("out_refund_no")
  private String outRefundNo;

  /**
   * <pre>
   * 退款金额
   * refund_fee
   * 是
   * Int
   * 100
   * 退款总金额，订单总金额，单位为分，只能为整数
   * </pre>
   */
  @Required
  @XStreamAlias("refund_fee")
  private Integer refundFee;

  /**
   * <pre>
   * 退款原因
   * refund_desc
   * 否
   * String(80)
   * 商品已售完
   * 若商户传入，会在下发给用户的退款消息中体现退款原因
   * </pre>
   */
  @XStreamAlias("refund_desc")
  private String refundDesc;

  @Override
  protected void checkConstraints() throws WxPayException {
    if (transactionId == null && outTradeNo == null) {
      throw new WxPayException("transaction_id 和 out_trade_no 不能同时为空");
    }
  }

  @Override
  protected void storeMap(Map<String, String> map) {
    if (transactionId != null) {
      map.put("transaction_id", transactionId);
    }
    if (outTradeNo != null) {
      map.put("out_trade_no", outTradeNo);
    }
    map.put("out_refund_no", outRefundNo);
    map.put("refund_fee", refundFee.toString());
    if (refundDesc != null) {
      map.put("refund_desc", refundDesc);
    }
  }
}