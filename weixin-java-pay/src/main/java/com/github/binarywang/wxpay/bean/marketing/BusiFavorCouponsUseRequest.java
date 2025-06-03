package com.github.binarywang.wxpay.bean.marketing;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 核销用户券请求对象
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_3.shtml
 * </pre>
 *
 * @author yujam
 */
@Data
@NoArgsConstructor
public class BusiFavorCouponsUseRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：券code
   * 变量名：coupon_code
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   * 券的唯一标识。
   * 示例值：sxxe34343434
   * </pre>
   */
  @SerializedName(value = "coupon_code")
  private String couponCode;

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
   * 字段名：公众账号ID
   * 变量名：appid
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   * 支持传入与当前调用接口商户号有绑定关系的appid。支持小程序appid与公众号appid。核销接口返回的openid会在该传入appid下进行计算获得。
   * 示例值：wx1234567889999
   * </pre>
   */
  @SerializedName(value = "appid")
  private String appId;

  /**
   * <pre>
   * 字段名：请求核销时间
   * 变量名：use_time
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   * 商户请求核销用户券的时间。 遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35.+08:00表示，北京时间2015年5月20日 13点29分35秒。
   * 示例值：2015-05-20T13:29:35+08:00
   * </pre>
   */
  @SerializedName(value = "use_time")
  private String useTime;

  /**
   * <pre>
   * 字段名：核销请求单据号
   * 变量名：use_request_no
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   * 每次核销请求的唯一标识，商户需保证唯一。
   * 示例值：1002600620019090123143254435
   * </pre>
   */
  @SerializedName(value = "use_request_no")
  private String useRequestNo;

  /**
   * <pre>
   * 字段名：用户标识
   * 变量名：openid
   * 是否必填：是
   * 类型：string[1,128]
   * 描述：
   * 用户的唯一标识，做安全校验使用，非必填。
   * 示例值：xsd3434454567676
   * </pre>
   */
  @SerializedName(value = "openid")
  private String openid;
}
