package com.github.binarywang.wxpay.bean.request;

import com.github.binarywang.wxpay.exception.WxPayException;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;
import me.chanjar.weixin.common.annotation.Required;

import java.util.Map;

/**
 * <pre>
 *   押金撤销请求
 *   详见：<a href="https://pay.weixin.qq.com/wiki/doc/api/deposit_sl.php?chapter=27_7&index=5">https://pay.weixin.qq.com/wiki/doc/api/deposit_sl.php?chapter=27_7&index=5</a>
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
public class WxDepositUnfreezeRequest extends BaseWxPayRequest {

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
   * 商户撤销单号
   * out_trade_no
   * 是
   * String(32)
   * 20150806125346
   * 商户系统内部的撤销单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一
   * </pre>
   */
  @Required
  @XStreamAlias("out_trade_no")
  private String outTradeNo;

  /**
   * <pre>
   * 撤销金额
   * unfreeze_fee
   * 是
   * Int
   * 99
   * 撤销金额，单位为分，不能大于剩余押金金额
   * </pre>
   */
  @Required
  @XStreamAlias("unfreeze_fee")
  private Integer unfreezeFee;

  /**
   * <pre>
   * 撤销原因
   * unfreeze_desc
   * 否
   * String(128)
   * 用户主动取消
   * 对一笔撤销的具体原因描述
   * </pre>
   */
  @XStreamAlias("unfreeze_desc")
  private String unfreezeDesc;

  @Override
  protected void checkConstraints() throws WxPayException {
    // No additional constraints beyond @Required fields
  }

  @Override
  protected void storeMap(Map<String, String> map) {
    map.put("transaction_id", transactionId);
    map.put("out_trade_no", outTradeNo);
    map.put("unfreeze_fee", unfreezeFee.toString());
    if (unfreezeDesc != null) {
      map.put("unfreeze_desc", unfreezeDesc);
    }
  }
}