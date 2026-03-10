package com.github.binarywang.wxpay.bean.subscriptionbilling;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 取消预约扣费请求参数
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/doc/v3/merchant/4012161105
 * </pre>
 *
 * @author Binary Wang
 */
@Data
@NoArgsConstructor
public class SubscriptionCancelRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：预约扣费ID
   * 变量名：subscription_id
   * 是否必填：是
   * 类型：string(64)
   * 描述：
   *  微信支付预约扣费ID
   *  示例值：1217752501201407033233368018
   * </pre>
   */
  @SerializedName("subscription_id")
  private String subscriptionId;

  /**
   * <pre>
   * 字段名：取消原因
   * 变量名：cancel_reason
   * 是否必填：否
   * 类型：string(256)
   * 描述：
   *  取消原因描述
   *  示例值：用户主动取消
   * </pre>
   */
  @SerializedName("cancel_reason")
  private String cancelReason;
}