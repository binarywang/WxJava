package com.github.binarywang.wxpay.bean.ecommerce;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 关闭普通订单请求
 *
 * @author f00lish
 * created on  2020/12/09
 */
@Data
@NoArgsConstructor
@Deprecated
public class PartnerTransactionsCloseRequest implements Serializable {

  private static final long serialVersionUID = -7602636370950088329L;

  /**
   * <pre>
   * 字段名：服务商户号
   * 变量名：sp_mchid
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  服务商户号，由微信支付生成并下发
   * 示例值：1230000109
   * </pre>
   */
  @SerializedName(value = "sp_mchid")
  private String spMchid;

  /**
   * <pre>
   * 字段名：二级商户号
   * 变量名：sub_mchid
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  二级商户的商户号，有微信支付生成并下发。
   * 示例值：1900000109
   * </pre>
   */
  @SerializedName(value = "sub_mchid")
  private String subMchid;

  /**
   * <pre>
   * 字段名：商户订单号
   * 变量名：out_trade_no
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  商户系统内部订单号，只能是数字、大小写字母_-*且在同一个商户号下唯一，详见【商户订单号】。
   * 特殊规则：最小字符长度为6
   * 示例值：1217752501201407033233368018
   * </pre>
   */
  private transient String outTradeNo;
}
