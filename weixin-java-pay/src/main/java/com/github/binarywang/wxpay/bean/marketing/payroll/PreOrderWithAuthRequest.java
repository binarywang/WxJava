package com.github.binarywang.wxpay.bean.marketing.payroll;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <pre>
 * 微工卡核身预下单（流程中完成授权）
 * 文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3_partner/Offline/apis/chapter4_1_1.shtml
 *
 * 适用对象：服务商
 * 请求URL：https://api.mch.weixin.qq.com/v3/payroll-card/authentications/pre-order-with-auth
 * 请求方式：POST
 * </pre>
 *
 * @author xiaoqiang
 * created on  2021/12/2
 */
@Data
@NoArgsConstructor
public class PreOrderWithAuthRequest implements Serializable {
  private static final long serialVersionUID = 1L;

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
   * 字段名：应用ID
   * 变量名：appid
   * 是否必填：二选一
   * 类型：string[1, 32]
   * 描述：
   *   是服务商在微信申请公众号/小程序或移动应用成功后分配的账号ID（与服务商主体一致），登录平台为mp.weixin.qq.com或open.weixin.qq.com。
   *  当输入应用ID时，会校验其与服务商商户号的绑定关系。服务商应用ID和与子商户应用ID至少输入一个，且必须要有拉起微工卡时使用的APPID。
   *  示例值：wxa1111111
   * </pre>
   */
  @SerializedName(value = "appid")
  private String appid;

  /**
   * <pre>
   * 字段名：子商户号
   * 变量名：sub_mchid
   * 是否必填：是
   * 类型：string[1, 32]
   * 描述：
   *  微信服务商下特约商户的商户号，由微信支付生成并下发
   * 示例值：1111111
   * </pre>
   */
  @SerializedName(value = "sub_mchid")
  private String subMchid;

  /**
   * <pre>
   * 字段名：子商户应用ID
   * 变量名：sub_appid
   * 是否必填：是
   * 类型：string[1, 32]
   * 描述：
   *  是特约商户在微信申请公众号/小程序或移动应用成功后分配的账号ID（与特约商户主体一致），登录平台为mp.weixin.qq.com或open.weixin.qq.com。当输入子商户应用ID时，会校验其与特约商户号的绑定关系。 服务商应用ID和与子商户应用ID至少输入一个，且必须要有拉起微工卡时使用的APPID。
   *  示例值：wxa1111111
   * </pre>
   */
  @SerializedName(value = "sub_appid")
  private String subAppid;

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
   * 字段名：项目名称
   * 变量名：project_name
   * 是否必填：是
   * 类型：string[1, 12]
   * 描述：
   *  该项目的名称
   *  示例值：某项目
   * </pre>
   */
  @SerializedName(value = "project_name")
  private String projectName;
  /**
   * <pre>
   * 字段名：用工单位名称
   * 变量名：employer_name
   * 是否必填：是
   * 类型：string[1, 12]
   * 描述：
   *    该用户所属的单位名称。
   *     示例值：某单位名称
   * </pre>
   */
  @SerializedName(value = "employer_name")
  private String employerName;

  /**
   * <pre>
   * 字段名：用户实名
   * 变量名：user_name
   * 是否必填：是
   * 类型：string[1, 1024]
   * 描述：
   *  用户证件号，该字段需进行加密处理，加密方法详见敏感信息加密说明。(提醒：必须在HTTP头中上送Wechatpay-Serial)
   * 示例值：7FzH5XksJG3a8HLLsaaUV6K54y1OnPMY5
   * </pre>
   */
  @SerializedName(value = "user_name")
  private String userName;

  /**
   * <pre>
   * 字段名：用户证件号
   * 变量名：id_card_number
   * 是否必填：是
   * 类型：string[1, 1024]
   * 描述：
   *  用户证件号，该字段需进行加密处理，加密方法详见敏感信息加密说明。(提醒：必须在HTTP头中上送Wechatpay-Serial)
   * 示例值：7FzH5XksJG3a8HLLsaaUV6K54y1OnPMY5
   * </pre>
   */
  @SerializedName(value = "id_card_number")
  private String idCardNumber;

  /**
   * <pre>
   * 字段名：用工类型
   * 变量名：employment_type
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   *  微工卡服务仅支持用于与商户有用工关系的用户，需明确用工类型；参考值：
   * LONG_TERM_EMPLOYMENT：长期用工，
   * SHORT_TERM_EMPLOYMENT： 短期用工，
   * COOPERATION_EMPLOYMENT：合作关系
   * 示例值：LONG_TERM_EMPLOYMENT
   * </pre>
   */
  @SerializedName(value = "employment_type")
  private String employmentType;
}
