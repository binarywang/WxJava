package com.github.binarywang.wxpay.bean.request;

import com.github.binarywang.wxpay.exception.WxPayException;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;
import me.chanjar.weixin.common.annotation.Required;

import java.util.Map;

/**
 * <pre>
 *   押金消费请求
 *   详见：<a href="https://pay.weixin.qq.com/wiki/doc/api/deposit_sl.php?chapter=27_7&index=4">https://pay.weixin.qq.com/wiki/doc/api/deposit_sl.php?chapter=27_7&index=4</a>
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
public class WxDepositConsumeRequest extends BaseWxPayRequest {

  /**
   * <pre>
   * 微信押金订单号
   * transaction_id
   * 是
   * String(32)
   * 1009660380201506130728806387
   * 微信押金订单号
   * </pre>
   */
  @Required
  @XStreamAlias("transaction_id")
  private String transactionId;

  /**
   * <pre>
   * 商户消费单号
   * out_trade_no
   * 是
   * String(32)
   * 20150806125346
   * 商户系统内部的消费单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一
   * </pre>
   */
  @Required
  @XStreamAlias("out_trade_no")
  private String outTradeNo;

  /**
   * <pre>
   * 消费金额
   * consume_fee
   * 是
   * Int
   * 88
   * 消费金额，单位为分，不能大于押金金额
   * </pre>
   */
  @Required
  @XStreamAlias("consume_fee")
  private Integer consumeFee;

  /**
   * <pre>
   * 消费描述
   * consume_desc
   * 否
   * String(128)
   * 单车使用费
   * 对一笔消费的具体描述信息
   * </pre>
   */
  @XStreamAlias("consume_desc")
  private String consumeDesc;

  @Override
  protected void checkConstraints() throws WxPayException {
    // No additional constraints beyond @Required fields
  }

  @Override
  protected void storeMap(Map<String, String> map) {
    map.put("transaction_id", transactionId);
    map.put("out_trade_no", outTradeNo);
    map.put("consume_fee", consumeFee.toString());
    if (consumeDesc != null) {
      map.put("consume_desc", consumeDesc);
    }
  }
}