package com.github.binarywang.wxpay.bean.marketing.payroll;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <pre>
 * 查询微工卡授权关系
 * 文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3_partner/Offline/apis/chapter4_1_2.shtml
 *
 * 适用对象：服务商
 * 请求URL：https://api.mch.weixin.qq.com/v3/payroll-card/relations/{openid}
 * 请求方式：GET
 * </pre>
 *
 * @author xiaoqiang
 * created on  2021/12/2
 */
@Data
@NoArgsConstructor
public class RelationsResult implements Serializable {
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
   * 字段名：授权状态
   * 变量名：authorize_state
   * 是否必填：是
   * 类型：string[1, 32]
   * 描述：
   *  授权状态：
   *      UNAUTHORIZED：未授权
   *      AUTHORIZED：已授权
   *      DEAUTHORIZED：已取消授权
   * 示例值：UNAUTHORIZED
   * </pre>
   */
  @SerializedName(value = "authorize_state")
  private String authorizeState;

  /**
   * <pre>
   * 字段名：授权时间
   * 变量名：authorize_time
   * 是否必填：否
   * 类型：string[1, 32]
   * 描述：
   *  授权时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss.sss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss.sss表示时分秒毫秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35.120+08:00表示北京时间2015年05月20日13点29分35秒。
   *  示例值：2015-05-20T13:29:35.120+08:00
   * </pre>
   */
  @SerializedName(value = "authorize_time")
  private String authorizeTime;

  /**
   * <pre>
   * 字段名：取消授权时间
   * 变量名：deauthorize_time
   * 是否必填：否
   * 类型：string[1, 32]
   * 描述：
   *  取消授权时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss.sss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss.sss表示时分秒毫秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35.120+08:00表示北京时间2015年05月20日13点29分35秒。
   *  示例值：2015-05-20T13:29:35.120+08:00
   * </pre>
   */
  @SerializedName(value = "deauthorize_time")
  private String deauthorizeTime;

}
