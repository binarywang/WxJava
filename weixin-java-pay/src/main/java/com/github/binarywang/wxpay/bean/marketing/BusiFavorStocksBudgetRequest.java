package com.github.binarywang.wxpay.bean.marketing;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 修改批次预算请求对象
 * 文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_11.shtml
 *
 * @author yujam
 */
@Data
@NoArgsConstructor
public class BusiFavorStocksBudgetRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>* 字段名：批次号
   * 变量名：stock_id
   * 是否必填：是
   * 类型：string[1,20]
   * 描述：
   * path批次号 示例值：98065001
   * </pre>
   */
  @SerializedName(value = "stock_id")
  private String stockId;

  /**
   * <pre>* 字段名：目标批次最大发放个数
   * 变量名：target_max_coupons
   * 是否必填：二选一
   * 类型：int
   * 描述：
   * body批次最大发放个数 示例值：3000
   * </pre>
   */
  @SerializedName(value = "target_max_coupons")
  private Integer targetMaxCoupons;

  /**
   * <pre>* 字段名：目标单天发放上限个数
   * 变量名：target_max_coupons
   * 是否必填：二选一
   * 类型：int
   * 描述：
   * body 目标单天发放上限个数 示例值：3000
   * </pre>
   */
  @SerializedName(value = "target_max_coupons_by_day")
  private Integer targetMaxCouponsByDay;

  /**
   * <pre>* 字段名：当前批次最大发放个数
   * 变量名：current_max_coupons
   * 是否必填：否
   * 类型：int
   * 描述：
   * body当前批次最大发放个数，当传入target_max_coupons大于0时，current_max_coupons必传 示例值：500
   * </pre>
   */
  @SerializedName(value = "current_max_coupons")
  private Integer currentMaxCoupons;

  /**
   * <pre>* 字段名：当前单天发放上限个数
   * 变量名：current_max_coupons_by_day
   * 是否必填：否
   * 类型：int
   * 描述：
   * body当前单天发放上限个数 ，当传入target_max_coupons_by_day大于0时，current_max_coupons_by_day必填 示例值：300
   * </pre>
   */
  @SerializedName(value = "current_max_coupons_by_day")
  private Integer currentMaxCouponsByDay;

  /**
   * <pre>* 字段名：修改预算请求单据号
   * 变量名：modify_budget_request_no
   * 是否必填：是
   * 类型：string[1,128]
   * 描述：
   * body修改预算请求单据号 示例值：1002600620019090123143254436
   * </pre>
   */
  @SerializedName(value = "modify_budget_request_no")
  private String modifyBudgetRequestNo;

}
