package com.github.binarywang.wxpay.bean.businesscircle;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 商圈退款成功通知内容
 * <pre>
 *  文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/businesscircle/chapter3_3.shtml
 * </pre>
 *
 * @author thinsstar
 */
@NoArgsConstructor
@Data
public class RefundResult implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 商户号
   * <p>
   * 微信支付分配的商户号
   * 示例值：1230000109
   */
  @SerializedName("mchid")
  private String mchid;

  /**
   * 商圈商户名称
   * <p>
   * 商圈商户名称
   * 示例值：微信支付
   */
  @SerializedName("merchant_name")
  private String merchantName;

  /**
   * 门店名称
   * <p>
   * 门店名称，商圈在商圈小程序上圈店时填写的门店名称
   * 示例值：微信支付
   */
  @SerializedName("shop_name")
  private String shopName;

  /**
   * 门店编号
   * <p>
   * 门店编号，商圈在商圈小程序上圈店时填写的门店编号，用于跟商圈自身已有的商户识别码对齐
   * 示例值：123456
   */
  @SerializedName("shop_number")
  private String shop_number;

  /**
   * 小程序APPID
   * <p>
   * 顾客授权积分时使用的小程序的appid
   * 示例值：wxd678efh567hg6787
   */
  @SerializedName("appid")
  private String appid;

  /**
   * 用户标识
   * <p>
   * 顾客授权时使用的小程序上的openid
   * 示例值：oUpF8uMuAJ2pxb1Q9zNjWeS6o
   */
  @SerializedName("openid")
  private String openid;

  /**
   * 退款完成时间
   * <p>
   * 退款完成时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒毫秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示北京时间2015年05月20日13点29分35秒（需要增加所有跟时间有关的参数的描述）
   * 示例值：2015-05-20T13:29:35+08:00
   */
  @SerializedName("refund_time")
  private String refundTime;

  /**
   * 消费金额
   * <p>
   * 用户实际消费金额，单位（分）
   * 示例值：100
   */
  @SerializedName("pay_amount")
  private Integer payAmount;

  /**
   * 退款金额
   * <p>
   * 用户退款金额，单位（分）
   * 示例值：100
   */
  @SerializedName("refund_amount")
  private Integer refundAmount;

  /**
   * 微信支付订单号
   * <p>
   * 微信支付订单号
   * 示例值：1234567890
   */
  @SerializedName("transaction_id")
  private String transactionId;

  /**
   * 微信支付退款单号
   * <p>
   * 微信支付退款单号
   * 示例值：1217752501201407033233368999
   */
  @SerializedName("refund_id")
  private String refundId;
}
