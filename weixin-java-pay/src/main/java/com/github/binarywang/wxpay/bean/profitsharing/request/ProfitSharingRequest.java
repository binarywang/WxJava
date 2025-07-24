package com.github.binarywang.wxpay.bean.profitsharing.request;

import com.github.binarywang.wxpay.bean.request.BaseWxPayRequest;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;
import me.chanjar.weixin.common.annotation.Required;

import java.util.Map;

/**
 * @author Wang GuangXin 2019/10/21 17:57
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("xml")
public class ProfitSharingRequest extends BaseWxPayRequest {
  private static final long serialVersionUID = 212049937430575842L;
  /**
   * <pre>
   * 字段名：微信订单号.
   * 变量名：transaction_id
   * 是否必填：是
   * String(32)
   * 示例值：4208450740201411110007820472
   * 描述：微信支付订单号
   * </pre>
   */
  @XStreamAlias("transaction_id")
  @Required
  private String transactionId;
  /**
   * <pre>
   * 字段名：商户分账单号.
   * 变量名：out_order_no
   * 是否必填：是
   * String(64)
   * 示例值：P20150806125346
   * 描述：商户系统内部的分账单号，在商户系统内部唯一（单次分账、多次分账、完结分账应使用不同的商户分账单号），同一分账单号多次请求等同一次。只能是数字、大小写字母_-|*@
   * </pre>
   */
  @XStreamAlias("out_order_no")
  @Required
  private String outOrderNo;
  /**
   * <pre>
   * 字段名：分账接收方列表.
   * 变量名：receivers
   * 是否必填：是
   * String(10240)
   * 示例值：[
   *     {
   *          "type": "MERCHANT_ID",
   *          "account":"190001001",
   *          "amount":100,
   *          "description": "分到商户"
   * },
   *     {
   *          "type": "PERSONAL_WECHATID",
   *          "account":"86693952",
   *          "amount":888,
   *          "description": "分到个人"
   * }
   * ]
   * 描述：分账接收方列表，不超过50个json对象，不能设置分账方作为分账接受方，使用Json格式
   * </pre>
   */
  @XStreamAlias("receivers")
  @Required
  private String receivers;

  @Override
  protected void checkConstraints() throws WxPayException {
    // 目前仅支持HMAC-SHA256.
    this.setSignType(WxPayConstants.SignType.HMAC_SHA256);
  }

  @Override
  protected void storeMap(Map<String, String> map) {
    map.put("transaction_id", transactionId);
    map.put("out_order_no", outOrderNo);
    map.put("receivers", receivers);
  }
}
