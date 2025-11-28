package com.github.binarywang.wxpay.bean.ecommerce;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.io.Serializable;

/**
 * 查询分账回退结果请求
 * * <pre>
 *  *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/profitsharing/chapter3_3.shtml
 *  * </pre>
 *
 * @author wangrui
 * created on  2021/02/20
 */
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReturnOrdersQueryRequest implements Serializable {
  private static final long serialVersionUID = 4250796057341297359L;

  /**
   * <pre>
   * 字段名：二级商户号
   * 变量名：sub_mchid
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  分账出资的电商平台二级商户，填写微信支付分配的商户号。
   *  示例值：1900000109
   * </pre>
   */
  @SerializedName(value = "sub_mchid")
  private String subMchid;

  /**
   * <pre>
   * 字段名：微信分账单号
   * 变量名：order_id
   * 是否必填：与out_order_no二选一
   * 类型：string（64）
   * 描述：
   *  微信分账单号，微信系统返回的唯一标识。微信分账单号和商户分账单号二选一填写。
   *  示例值：3008450740201411110007820472
   * </pre>
   */
  @SerializedName(value = "order_id")
  private String orderId;

  /**
   * <pre>
   * 字段名：商户分账单号
   * 变量名：out_order_no
   * 是否必填：与order_id二选一
   * 类型：string（64）
   * 描述：
   *   商户系统内部的分账单号，在商户系统内部唯一（单次分账、多次分账、完结分账应使用不同的商户分账单号），同一分账单号多次请求等同一次。
   *  示例值：P20150806125346
   * </pre>
   */
  @SerializedName(value = "out_order_no")
  private String outOrderNo;

  /**
   * <pre>
   * 字段名：商户回退单号
   * 变量名：out_return_no
   * 是否必填：是
   * 类型：string（64）
   * 描述：
   *   此回退单号是商户在自己后台生成的一个新的回退单号，在商户后台唯一。
   *  示例值：P20150806125346
   * </pre>
   */
  @SerializedName(value = "out_return_no")
  private String outReturnNo;
}
