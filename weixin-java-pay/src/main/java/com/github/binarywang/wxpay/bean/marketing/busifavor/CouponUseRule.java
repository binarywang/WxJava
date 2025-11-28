package com.github.binarywang.wxpay.bean.marketing.busifavor;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 核销规则
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_1.shtml
 * </pre>
 *
 * @author yujam
 */
@Data
@NoArgsConstructor
public class CouponUseRule implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：券生效时间
   * 变量名：coupon_available_time
   * 是否必填：是
   * 类型：object
   * 描述：
   *  允许指定券的特殊生效时间规则。
   *  该字段暂未开放
   * </pre>
   */
  @SerializedName(value = "coupon_available_time")
  private CouponAvailableTime couponAvailableTime;

  /**
   * <pre>
   * 字段名：固定面额满减券使用规则
   * 变量名：fixed_normal_coupon
   * 是否必填：否
   * 类型：object
   * 描述：
   *  stock_type为NORMAL时必填。
   * </pre>
   */
  @SerializedName(value = "fixed_normal_coupon")
  private FixedNormalCoupon fixedNormalCoupon;
  /**
   * <pre>
   * 字段名：折扣券使用规则
   * 变量名：discount_coupon
   * 是否必填：否
   * 类型：object
   * 描述：
   *  stock_type为DISCOUNT时必填。
   * </pre>
   */
  @SerializedName(value = "discount_coupon")
  private DiscountCoupon discountCoupon;

  /**
   * <pre>
   * 字段名：换购券使用规则
   * 变量名：exchange_coupon
   * 是否必填：否
   * 类型：object
   * 描述：
   *  stock_type为EXCHANG时必填。
   * </pre>
   */
  @SerializedName(value = "exchange_coupon")
  private ExchangeCoupon exchangeCoupon;

  /**
   * <pre>
   * 字段名：核销方式
   * 变量名：use_method
   * 是否必填：是
   * 类型：string[1,128]
   * 描述：
   * 枚举值：
   *  OFF_LINE：线下滴码核销，点击券“立即使用”跳转展示券二维码详情。
   *  MINI_PROGRAMS：线上小程序核销，点击券“立即使用”跳转至配置的商家小程序（需要添加小程序appid和path）。
   *  PAYMENT_CODE：微信支付付款码核销，点击券“立即使用”跳转至微信支付钱包付款码。
   *  SELF_CONSUME：用户自助核销，点击券“立即使用”跳转至用户自助操作核销界面（当前暂不支持用户自助核销）。
   *  示例值：OFF_LINE
   * </pre>
   */
  @SerializedName(value = "use_method")
  private String useMethod;

  /**
   * <pre>
   * 字段名：小程序appid
   * 变量名：mini_programs_appid
   * 是否必填：否
   * 类型：string[1,32]
   * 描述：
   *  核销方式为线上小程序核销才有效。
   *  示例值：wx23232232323
   * </pre>
   */
  @SerializedName(value = "mini_programs_appid")
  private String miniProgramsAppid;

  /**
   * <pre>
   * 字段名：小程序path
   * 变量名：mini_programs_path
   * 是否必填：否
   * 类型：string[1,128]
   * 描述：
   *  核销方式为线上小程序核销才有效。
   *  示例值：/path/index/index
   * </pre>
   */
  @SerializedName(value = "mini_programs_path")
  private String miniProgramsPath;

}
