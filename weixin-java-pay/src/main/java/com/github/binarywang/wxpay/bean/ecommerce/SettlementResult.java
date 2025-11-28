package com.github.binarywang.wxpay.bean.ecommerce;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 查询结算账户结果
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/applyments/chapter3_5.shtml
 * </pre>
 */
@Data
@NoArgsConstructor
public class SettlementResult implements Serializable {
  /**
   * <pre>
   * 字段名：账户类型
   * 变量名：account_type
   * 是否必填：是
   * 类型：string(32)
   * 描述：
   * 枚举值：
   *    ACCOUNT_TYPE_BUSINESS：对公银行账户
   *    ACCOUNT_TYPE_PRIVATE：经营者个人银行卡
   * 示例值：ACCOUNT_TYPE_BUSINESS
   * </pre>
   */
  @SerializedName(value = "account_type")
  private String accountType;

  /**
   * <pre>
   * 字段名：开户银行
   * 变量名：account_bank
   * 是否必填：是
   * 类型：string(128)
   * 描述：
   *  返回特约商户的结算账户-开户银行全称。
   * 示例值：工商银行
   * </pre>
   */
  @SerializedName(value = "account_bank")
  private String accountBank;

  /**
   * <pre>
   * 字段名：开户银行全称（含支行）
   * 变量名：bank_name
   * 是否必填：是
   * 类型：string(128)
   * 描述：
   *  返回特约商户的结算账户-开户银行全称（含支行）。
   * 示例值：施秉县农村信用合作联社城关信用社
   * </pre>
   */
  @SerializedName(value = "bank_name")
  private String bankName;

  /**
   * <pre>
   * 字段名：开户银行联行号
   * 变量名：bank_branch_id
   * 是否必填：是
   * 类型：string(128)
   * 描述：
   *  返回特约商户的结算账户-联行号。
   * 示例值：402713354941
   * </pre>
   */
  @SerializedName(value = "bank_branch_id")
  private String bankBranchId;

  /**
   * <pre>
   * 字段名：银行账号
   * 变量名：account_number
   * 是否必填：是
   * 类型：string(128)
   * 描述：
   *  返回特约商户的结算账户-银行账号，掩码显示。
   * 示例值：62*************78
   * </pre>
   */
  @SerializedName(value = "account_number")
  private String accountNumber;

  /**
   * <pre>
   * 字段名：汇款验证结果
   * 变量名：verify_result
   * 是否必填：是
   * 类型：string(32)
   * 描述：
   *  返回特约商户的结算账户-汇款验证结果。
   *    VERIFYING：系统汇款验证中，商户可发起提现尝试。
   *    VERIFY_SUCCESS：系统成功汇款，该账户可正常发起提现。
   *    VERIFY_FAIL：系统汇款失败，该账户无法发起提现，请检查修改。
   * 示例值：VERIFY_SUCCESS
   * </pre>
   */
  @SerializedName(value = "verify_result")
  private String verifyResult;
}
