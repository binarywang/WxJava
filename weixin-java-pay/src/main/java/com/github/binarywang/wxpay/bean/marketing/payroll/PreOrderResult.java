package com.github.binarywang.wxpay.bean.marketing.payroll;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <pre>
 * 微工卡核身预下单
 * 文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3_partner/Offline/apis/chapter4_1_3.shtml
 *
 * 适用对象：服务商
 * 请求URL：https://api.mch.weixin.qq.com/v3/payroll-card/authentications/pre-order
 * 请求方式：POST
 * </pre>
 *
 * @author xiaoqiang
 * created on  2021/12/2
 */
@Data
@NoArgsConstructor
public class PreOrderResult implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：商家核身单号
   * 变量名：authenticate_number
   * 是否必填：是
   * 类型：string[1, 64]
   * 描述：
   *  商户系统内部的商家核身单号，要求此参数只能由数字、大小写字母组成，在服务商内部唯一
   *  示例值：mcdhehfgisdhfjghed39384564i83
   * </pre>
   */
  @SerializedName(value = "authenticate_number")
  private String authenticateNumber;

  /**
   * <pre>
   * 字段名：用户标识
   * 变量名：openid
   * 是否必填：是
   * 类型：string[1, 64]
   * 描述：
   *  用户在商户对应appid下的唯一标识
   *  示例值：9x111111
   * </pre>
   */
  @SerializedName(value = "openid")
  private String openid;

  /**
   * <pre>
   * 字段名：服务商商户号
   * 变量名：mchid
   * 是否必填：是
   * 类型：string[1, 32]
   * 描述：
   *  微信服务商商户的商户号，由微信支付生成并下发。
   *  示例值：1111111
   * </pre>
   */
  @SerializedName(value = "mchid")
  private String mchid;

  /**
   * <pre>
   * 字段名：子商户号
   * 变量名：sub_mchid
   * 是否必填：是
   * 类型：string[1, 32]
   * 描述：
   *  微信服务商下特约商户的商户号，由微信支付生成并下发
   *  示例值：1111111
   * </pre>
   */
  @SerializedName(value = "sub_mchid")
  private String subMchid;

  /**
   * <pre>
   * 字段名：授权token
   * 变量名：token
   * 是否必填：是
   * 类型：string[1, 1024]
   * 描述：
   *  授权token
   *  示例值：abcdefghijklmn
   * </pre>
   */
  @SerializedName(value = "token")
  private String token;

  /**
   * <pre>
   * 字段名：token有效时间
   * 变量名：expires_in
   * 是否必填：是
   * 类型：int
   * 描述：
   * token有效时间，单位秒
   * 示例值：1800
   * </pre>
   */
  @SerializedName(value = "expires_in")
  private Integer expiresIn;

}
