package com.github.binarywang.wxpay.bean.request;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 服务商查询订单请求对象类
 * 文档见：https://pay.weixin.qq.com/wiki/doc/apiv3_partner/apis/chapter4_1_2.shtml
 *
 * @author Pursuer
 * @version 1.0
 * @date 2023/6/25
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class WxPayPartnerOrderQueryV3Request implements Serializable {
  private static final long serialVersionUID = 1L;
  /**
   * <pre>
   * 字段名：服务商商户号
   * 变量名：sp_mchid
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   *  服务商商户号，由微信支付生成并下发。
   *  示例值：1230000109
   * </pre>
   */
  @SerializedName(value = "sp_mchid")
  private String spMchId;
  /**
   * <pre>
   * 字段名：特约商户商户号
   * 变量名：sp_mchid
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   *  特约商户商户号，由微信支付生成并下发。
   *  示例值：1230000109
   * </pre>
   */
  @SerializedName(value = "sub_mchid")
  private String subMchId;
  /**
   * <pre>
   * 字段名：微信支付订单号
   * 变量名：transaction_id
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   *  微信支付系统生成的订单号
   *  示例值：1217752501201407033233368018
   * </pre>
   */
  @SerializedName(value = "transaction_id")
  private String transactionId;
  /**
   * <pre>
   * 字段名：商户订单号
   * 变量名：out_trade_no
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   *  商户系统内部订单号，只能是数字、大小写字母_-*且在同一个商户号下唯一。
   *  特殊规则：最小字符长度为6
   *  示例值：1217752501201407033233368018
   * </pre>
   */
  @SerializedName(value = "out_trade_no")
  private String outTradeNo;
}
