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
 * @author Pursuer
 * @version 1.0
 * @date 2023/3/2
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class WxPayPartnerRefundV3Request extends WxPayRefundV3Request implements Serializable {
  private static final long serialVersionUID = -1L;
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
