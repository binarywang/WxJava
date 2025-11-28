package com.github.binarywang.wxpay.bean.marketing.busifavor;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 券发放相关规则
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_1.shtml
 * </pre>
 *
 * @author yujam
 */
@Data
@NoArgsConstructor
public class StockSendRule implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：批次最大发放个数
   * 变量名：max_coupons
   * 是否必填：是
   * 类型：int
   * 描述：
   *  批次最大可发放个数限制
   *  特殊规则：取值范围 1 ≤ value ≤ 1000000000
   *  示例值：100
   * </pre>
   */
  @SerializedName(value = "max_coupons")
  private Integer maxCoupons;

  /**
   * <pre>
   * 字段名：用户最大可领个数
   * 变量名：max_coupons_per_user
   * 是否必填：是
   * 类型：int
   * 描述：
   *  用户可领个数，每个用户最多100张券 。
   *  示例值：5
   * </pre>
   */
  @SerializedName(value = "max_coupons_per_user")
  private Integer maxCouponsPerUser;

  /**
   * <pre>
   * 字段名：单天发放上限个数
   * 变量名：max_coupons_by_day
   * 是否必填：否
   * 类型：bool
   * 描述：
   *  单天发放上限个数（stock_type为DISCOUNT或EXCHANGE时可传入此字段控制单天发放上限）。
   *  特殊规则：取值范围 1 ≤ value ≤ 1000000000
   *  示例值：100
   * </pre>
   */
  @SerializedName(value = "max_coupons_by_day")
  private Integer maxCouponsByDay;

  /**
   * <pre>
   * 字段名：是否开启自然人限制
   * 变量名：natural_person_limit
   * 是否必填：否
   * 类型：bool
   * 描述：
   *  不填默认否，枚举值：
   *  true：是
   *  false：否
   *  示例值：false
   * </pre>
   */
  @SerializedName(value = "natural_person_limit")
  private Boolean naturalPersonLimit;

  /**
   * <pre>
   * 字段名：可疑账号拦截
   * 变量名：prevent_api_abuse
   * 是否必填：否
   * 类型：bool
   * 描述：
   *  不填默认否，枚举值：
   *  true：是
   *  false：否
   *  示例值：false
   * </pre>
   */
  @SerializedName(value = "prevent_api_abuse")
  private Boolean preventApiAbuse;

  /**
   * <pre>
   * 字段名：是否允许转赠
   * 变量名：transferable
   * 是否必填：否
   * 类型：bool
   * 描述：
   *  不填默认否，枚举值：
   *  true：是
   *  false：否
   *  该字段暂未开放
   *  示例值：false
   * </pre>
   */
  @SerializedName(value = "transferable")
  private Boolean transferable;

  /**
   * <pre>
   * 字段名：是否允许分享链接
   * 变量名：shareable
   * 是否必填：否
   * 类型：bool
   * 描述：
   *  不填默认否，枚举值：
   *  true：是
   *  false：否
   *  该字段暂未开放
   *  示例值：false
   * </pre>
   */
  @SerializedName(value = "shareable")
  private Boolean shareable;
}
