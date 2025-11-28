package com.github.binarywang.wxpay.bean.marketing;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 修改批次预算返回对象
 * 文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_11.shtml
 *
 * @author yujam
 */
@Data
@NoArgsConstructor
public class BusiFavorStocksBudgetResult implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>* 字段名：批次当前最大发放个数
   * 变量名：max_coupons
   * 是否必填：是
   * 类型：int
   * 描述：
   * 批次最大发放个数 示例值：300
   * </pre>
   */
  @SerializedName(value = "max_coupons")
  private Integer maxCoupons;

  /**
   * <pre>* 字段名：当前单天发放上限个数
   * 变量名：max_coupons_by_day
   * 是否必填：否
   * 类型：int
   * 描述：
   * 当前单天发放上限个数 示例值：100
   * </pre>
   */
  @SerializedName(value = "max_coupons_by_day")
  private Integer maxCouponsByDay;
}
