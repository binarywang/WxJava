package com.github.binarywang.wxpay.bean.marketing;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * H5发券请求对象
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_4_1.shtml
 * </pre>
 *
 * @author yujam
 */
@Data
@NoArgsConstructor
public class BusiFavorCouponsUrlRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：批次号
   * 变量名：stock_id
   * 是否必填：否
   * 类型：string[1,20]
   * 描述：
   * 微信为每个商家券批次分配的唯一ID，批次券Code模式是MERCHANT_API或者MERCHANT_UPLOAD时，核销时必须填写批次号
   * 示例值：100088
   * </pre>
   */
  @SerializedName(value = "stock_id")
  private String stockId;

  /**
   * <pre>
   * 字段名：核销请求单据号
   * 变量名：out_request_no
   * 是否必填：是
   * 类型：string[1,128]
   * 描述：
   *  发券凭证（示例格式：商户 id+日期+流水号），可包含英文字母、数字，不允许出现其他不合法符号，商户侧需保证发放凭据号唯一性
   * </pre>
   */
  @SerializedName(value = "out_request_no")
  private String outRequestNo;

  /**
   * <pre>
   * 字段名：签名
   * 变量名：sign
   * 是否必填：是
   * 类型：string
   * 描述：
   *  签名计算值。
   *  签名方式：HMAC-SHA256。
   *  签名规则：详见《V2 签名规则》 https://pay.weixin.qq.com/wiki/doc/api/wxpay_v2/jiekouguize/chapter1_1.shtml
   *  参与签名字段说明
   *  注意：为了安全，签名必须在后台服务器计算，禁止在H5中计算，签名 key 为微信支付 apiv2 的 signkey
   *  示例值：9A0A8659F005D6984697E2CA0A9CF3B79A0A8659F005D6984697E2CA0A9CF3B7
   * </pre>
   */
  @SerializedName(value = "sign")
  private String sign;

  /**
   * <pre>
   * 字段名：发券商户号
   * 变量名：send_coupon_merchant
   * 是否必填：是
   * 类型：string[1,15]
   * 描述：
   *  调用发券接口的商户号
   * </pre>
   */
  @SerializedName(value = "send_coupon_merchant")
  private String sendCouponMerchant;

  /**
   * <pre>
   * 字段名：用户标识
   * 变量名：openid
   * 是否必填：是
   * 类型：string[1,128]
   * 描述：
   *   目标发券的用户openid
   * </pre>
   */
  @SerializedName(value = "openid")
  private String openid;
}
