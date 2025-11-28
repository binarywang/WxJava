package com.github.binarywang.wxpay.bean.marketing;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 申请退券请求对象
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_13.shtml
 * </pre>
 *
 * @author yujam
 */
@Data
@NoArgsConstructor
public class BusiFavorCouponsReturnRequest implements Serializable {

  /**
   * <pre>* 字段名：券code
   * 变量名：coupon_code
   * 是否必填：是
   * 类型：string[1,20]
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
   * 类型：string[1,32]
   * 描述：
   * body券的所属批次号 示例值：1234567891
   * </pre>
   */
  @SerializedName(value = "stock_id")
  private String stockId;

  /**
   * <pre>* 字段名：退券请求单据号
   * 变量名：return_request_no
   * 是否必填：是
   * 类型：string[1, 128]
   * 描述：
   * body每次退券请求的唯一标识，商户需保证唯一 示例值：1002600620019090123143254436
   * </pre>
   */
  @SerializedName(value = "return_request_no")
  private String returnRequestNo;
}
