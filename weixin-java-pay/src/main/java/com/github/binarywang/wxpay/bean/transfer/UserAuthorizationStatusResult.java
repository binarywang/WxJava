package com.github.binarywang.wxpay.bean.transfer;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <pre>
 * 商户查询用户授权信息接口响应结果
 * 商户通过此接口可查询用户是否对商户的商家转账场景进行了授权。
 * 文档地址：https://pay.weixin.qq.com/doc/v3/merchant/4015901167
 * </pre>
 *
 * @author wanggang
 * created on 2025/11/28
 */
@Data
@NoArgsConstructor
public class UserAuthorizationStatusResult implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 【商户AppID】 商户在微信申请公众号或移动应用成功后分配的账号ID
   */
  @SerializedName("appid")
  private String appid;

  /**
   * 【商户号】 微信支付分配的商户号
   */
  @SerializedName("mch_id")
  private String mchId;

  /**
   * 【用户标识】 用户在直连商户应用下的用户标识
   */
  @SerializedName("openid")
  private String openid;

  /**
   * 【授权状态】 用户授权状态
   * UNAUTHORIZED: 未授权
   * AUTHORIZED: 已授权
   */
  @SerializedName("authorization_state")
  private String authorizationState;

  /**
   * 【授权时间】 用户授权时间，遵循rfc3339标准格式
   * 格式为yyyy-MM-DDTHH:mm:ss+TIMEZONE
   */
  @SerializedName("authorize_time")
  private String authorizeTime;

  /**
   * 【取消授权时间】 用户取消授权时间，遵循rfc3339标准格式
   * 格式为yyyy-MM-DDTHH:mm:ss+TIMEZONE
   */
  @SerializedName("deauthorize_time")
  private String deauthorizeTime;
}
