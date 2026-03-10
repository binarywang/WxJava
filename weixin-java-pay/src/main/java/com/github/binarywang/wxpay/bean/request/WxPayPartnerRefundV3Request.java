package com.github.binarywang.wxpay.bean.request;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 微信支付服务商退款请求
 * 文档见：https://pay.weixin.qq.com/wiki/doc/apiv3_partner/apis/chapter4_1_9.shtml
 *
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class WxPayPartnerRefundV3Request extends WxPayRefundV3Request implements Serializable {
  private static final long serialVersionUID = -1L;
  /**
   * <pre>
   * 字段名：服务商应用ID
   * 变量名：sp_appid
   * 是否必填：是
   * 类型：string[1, 32]
   * 描述：
   *  服务商申请的公众号或移动应用appid。
   *  示例值：wx8888888888888888
   * </pre>
   */
  @SerializedName(value = "sp_appid")
  private String spAppid;
  /**
   * <pre>
   * 字段名：子商户应用ID
   * 变量名：sub_appid
   * 是否必填：否
   * 类型：string[1, 32]
   * 描述：
   *  子商户申请的公众号或移动应用appid。如果传了sub_appid，那sub_appid对应的订单必须存在。
   *  示例值：wx8888888888888888
   * </pre>
   */
  @SerializedName(value = "sub_appid")
  private String subAppid;
  /**
   * <pre>
   * 字段名：退款资金来源
   * 变量名：funds_account
   * 是否必填：否
   * 类型：string[1, 32]
   * 描述：
   *  若传递此参数则使用对应的资金账户退款，否则默认使用未结算资金退款（仅对老资金流商户适用）
   *  示例值：
   *    UNSETTLED : 未结算资金
   *    AVAILABLE : 可用余额
   *    UNAVAILABLE : 不可用余额
   *    OPERATION : 运营户
   *    BASIC : 基本账户（含可用余额和不可用余额）
   * </pre>
   */
  @SerializedName(value = "funds_account")
  private String fundsAccount;
}
