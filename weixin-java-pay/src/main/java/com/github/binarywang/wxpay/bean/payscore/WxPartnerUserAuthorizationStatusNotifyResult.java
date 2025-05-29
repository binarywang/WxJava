package com.github.binarywang.wxpay.bean.payscore;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 授权/解除授权服务回调通知结果
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3_partner/Offline/apis/chapter6_2_23.shtml
 * </pre>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WxPartnerUserAuthorizationStatusNotifyResult extends UserAuthorizationStatusNotifyResult implements Serializable {

  private static final long serialVersionUID = 8809250065540275783L;

  /**
   * <pre>
   * 字段名：子商户应用ID
   * 变量名：sub_appid
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   *  子商户申请的公众号或移动应用APPID。
   * 示例值：wxd678efh567hg6787
   * </pre>
   */
  @SerializedName(value = "sub_appid")
  private String subAppId;

  /**
   * <pre>
   * 字段名：子商户的商户号
   * 变量名：sub_mchid
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   *  子商户商户号，由微信支付生成并下发。
   * 示例值：1230000109
   * </pre>
   */
  @SerializedName(value = "sub_mchid")
  private String subMchId;

  /**
   * <pre>
   * 字段名：子商户公众号下openid
   * 变量名：sub_mchid
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   *  微信用户在商户对应sub_appid下的唯一标识。（传了sub_appid的情况下则只返回sub_openid）。
   * 示例值：oUpF8uMuAJO_M2pxb1Q9zNjWeS6o
   * </pre>
   */
  @SerializedName(value = "sub_openid")
  private String subOpenid;


}
