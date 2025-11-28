package com.github.binarywang.wxpay.bean.marketing;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 根据过滤条件查询用户券返回对象
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_4.shtml
 * </pre>
 *
 * @author yujam
 */
@Data
@NoArgsConstructor
public class BusiFavorQueryUserCouponsResult implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>* 字段名：+结果集
   * 变量名：data
   * 是否必填：是
   * 类型：array
   * 描述：
   * 结果集
   * </pre>
   */
  @SerializedName(value = "data")
  private List<BusiFavorQueryOneUserCouponsResult> data;

  /**
   * <pre>* 字段名：总数量
   * 变量名：total_count
   * 是否必填：是
   * 类型：int
   * 描述：
   * 总数量 示例值： 100
   * </pre>
   */
  @SerializedName(value = "total_count")
  private Integer totalCount;

  /**
   * <pre>* 字段名：分页页码
   * 变量名：offset
   * 是否必填：是
   * 类型：int
   * 描述：
   * 分页页码 示例值：1
   * </pre>
   */
  @SerializedName(value = "offset")
  private Integer offset;

  /**
   * <pre>* 字段名：分页大小
   * 变量名：limit
   * 是否必填：是
   * 类型：int
   * 描述：
   * 分页大小 示例值：10
   * </pre>
   */
  @SerializedName(value = "limit")
  private Integer limit;

}
