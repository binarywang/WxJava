package com.github.binarywang.wxpay.bean.customs;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author xifenzhu
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class DeclarationQueryRequest implements Serializable {

  private static final long serialVersionUID = -251403491989628142L;
  /**
   * <pre>
   * 字段名：机构APPID
   * 变量名：appid
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  微信分配的公众账号ID
   *  示例值：wxd678efh567hg6787
   * </pre>
   */
  @SerializedName(value = "appid")
  private String appid;

  /**
   * <pre>
   * 字段名：商户号
   * 变量名：mchid
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  微信支付分配的商户号
   *  示例值：1230000109
   * </pre>
   */
  @SerializedName(value = "mchid")
  private String mchid;

  /**
   * <pre>
   * 字段名：订单类型
   * 变量名：order_type
   * 是否必填：是
   * 类型：string（16）
   * 描述：
   *  4种订单号类型，选择一种
   *  out_trade_no   商户订单号
   *  transaction_id  微信支付订单号
   *  sub_order_no  商户子订单号
   *  sub_order_id  微信子订单号
   *  示例值：out_trade_no
   * </pre>
   */
  @SerializedName(value = "order_type")
  private String orderType;

  /**
   * <pre>
   * 字段名：订单号
   * 变量名：order_no
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  根据订单号类型，传入不同的订单号码
   *  示例值：20150806125346
   * </pre>
   */
  @SerializedName(value = "order_no")
  private String orderNo;

  /**
   * <pre>
   * 字段名：海关
   * 变量名：customs
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  海关代码, 枚举值参见参数规定-海关列表（https://pay.weixin.qq.com/wiki/doc/api/wxpay/ch/declarecustom_ch/chapter2_3.shtml#menu11）
   *  示例值：SHANGHAI_ZS
   * </pre>
   */
  @SerializedName(value = "customs")
  private String customs;

  /**
   * <pre>
   * 字段名：偏移量
   * 变量名：offset
   * 是否必填：是
   * 类型：int
   * 描述：
   *  非0整数，该次请求资源的起始位置，从0开始计数。调用方选填，默认为0
   *  示例值：0
   * </pre>
   */
  @SerializedName(value = "offset")
  private String offset;

  /**
   * <pre>
   * 字段名：请求最大记录条数
   * 变量名：limit
   * 是否必填：是
   * 类型：int
   * 描述：
   *  非0非负的整数，该次请求可返回的最大资源条数。调用方选填，默认值建议为20
   *  示例值：20
   * </pre>
   */
  @SerializedName(value = "limit")
  private String limit;

}
