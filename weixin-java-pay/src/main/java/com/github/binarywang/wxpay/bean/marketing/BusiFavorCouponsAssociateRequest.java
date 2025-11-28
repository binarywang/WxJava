package com.github.binarywang.wxpay.bean.marketing;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 关联订单信息请求对象
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_9.shtml
 * </pre>
 *
 * @author yujam
 */
@Data
@NoArgsConstructor
public class BusiFavorCouponsAssociateRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>* 字段名：批次号
   * 变量名：stock_id
   * 是否必填：是
   * 类型：string[1,20]
   * 描述：
   * body 微信为每个商家券批次分配的唯一ID，对于商户自定义code的批次，关联请求必须填写批次号 示例值：100088
   * </pre>
   */
  @SerializedName(value = "stock_id")
  private String stockId;

  /**
   * <pre>* 字段名：券code
   * 变量名：coupon_code
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   * body 券的唯一标识 示例值：sxxe34343434
   * </pre>
   */
  @SerializedName(value = "coupon_code")
  private String couponCode;

  /**
   * <pre>* 字段名：关联的商户订单号
   * 变量名：out_trade_no
   * 是否必填：是
   * 类型：string[1,128]
   * 描述：
   * body 微信支付下单时的商户订单号，欲与该商家券关联的微信支付 示例值：MCH_102233445
   * </pre>
   */
  @SerializedName(value = "out_trade_no")
  private String outTradeNo;

  /**
   * <pre>* 字段名：商户请求单号
   * 变量名：out_request_no
   * 是否必填：是
   * 类型：string[1,128]
   * 描述：
   * body 商户创建批次凭据号（格式：商户id+日期+流水号），商户侧需保持唯一性，可包含英文字母，数字，｜，_，*，-等内容，不允许出现其他不合法符号。 示例值：1002600620019090123143254435
   * </pre>
   */
  @SerializedName(value = "out_request_no")
  private String outRequestNo;
}
