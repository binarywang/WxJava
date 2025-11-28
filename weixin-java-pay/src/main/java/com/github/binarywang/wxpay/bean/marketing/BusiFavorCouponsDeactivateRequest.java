package com.github.binarywang.wxpay.bean.marketing;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 使券失效请求对象
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_14.shtml
 * </pre>
 *
 * @author yujam
 */
@Data
@NoArgsConstructor
public class BusiFavorCouponsDeactivateRequest implements Serializable {

  /**
   * <pre>* 字段名：券code
   * 变量名：coupon_code
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   * body券的唯一标识 示例值：sxxe34343434
   * </pre>
   */
  @SerializedName(value = "coupon_code")
  private String couponCode;

  /**
   * <pre>* 字段名：批次号
   * 变量名：stock_id
   * 是否必填：是
   * 类型：string[1,20]
   * 描述：
   * body券的所属批次号 示例值：1234567891
   * </pre>
   */
  @SerializedName(value = "stock_id")
  private String stockId;

  /**
   * <pre>* 字段名：失效请求单据号
   * 变量名：deactivate_request_no
   * 是否必填：是
   * 类型：string[1, 128]
   * 描述：
   * body每次失效请求的唯一标识，商户需保证唯一 示例值：1002600620019090123143254436
   * </pre>
   */
  @SerializedName(value = "deactivate_request_no")
  private String deactivateRequestNo;

  /**
   * <pre>* 字段名：失效原因
   * 变量名：deactivate_reason
   * 是否必填：否
   * 类型：string[1, 64]
   * 描述：
   * body商户失效券的原因 示例值：此券使用时间设置错误
   * </pre>
   */
  @SerializedName(value = "deactivate_reason")
  private String deactivateReason;
}
