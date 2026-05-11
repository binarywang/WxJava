package com.github.binarywang.wxpay.bean.merchanttransfer;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * The type Electronic bill apply request.
 *
 * @author glz
 * created on  2022-6-11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ElectronicBillApplyRequest implements Serializable {
  private static final long serialVersionUID = -2121536206019844928L;
  /**
   * <pre>
   * 字段名：商户转账单号
   * 变量名：out_bill_no
   * 是否必填：是
   * 类型：string[5,32]
   * 描述：
   *  body商户系统内部的商户转账单号，在商户系统内部唯一。兼容旧字段out_batch_no
   * 示例值：plfk2020042013
   * </pre>
   */
  @SerializedName(value = "out_bill_no", alternate = {"out_batch_no"})
  private String outBatchNo;
}
