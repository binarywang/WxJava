package com.github.binarywang.wxpay.bean.request;

import com.github.binarywang.wxpay.exception.WxPayException;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;
import me.chanjar.weixin.common.annotation.Required;

import java.util.Map;

/**
 * <pre>
 *   查询押金订单请求
 *   详见：<a href="https://pay.weixin.qq.com/wiki/doc/api/deposit_sl.php?chapter=27_7&index=3">https://pay.weixin.qq.com/wiki/doc/api/deposit_sl.php?chapter=27_7&index=3</a>
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
public class WxDepositOrderQueryRequest extends BaseWxPayRequest {

  /**
   * <pre>
   * 微信订单号
   * transaction_id
   * 否
   * String(32)
   * 1009660380201506130728806387
   * 微信的订单号，优先使用
   * </pre>
   */
  @XStreamAlias("transaction_id")
  private String transactionId;

  /**
   * <pre>
   * 商户订单号
   * out_trade_no
   * 否
   * String(32)
   * 20150806125346
   * 商户系统内部的订单号，当没提供transaction_id时需要传这个
   * </pre>
   */
  @XStreamAlias("out_trade_no")
  private String outTradeNo;

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
  }
}