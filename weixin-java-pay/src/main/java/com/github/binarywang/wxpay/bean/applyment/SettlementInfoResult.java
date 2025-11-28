package com.github.binarywang.wxpay.bean.applyment;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 查询结算账户返回对象信息
 *
 * @see <a href="https://pay.weixin.qq.com/wiki/doc/apiv3_partner/apis/chapter11_1_4.shtml">查询结算账户</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class SettlementInfoResult implements Serializable {
  private static final long serialVersionUID = 4568552340365230872L;
  /**
   * 账户类型
   */
  @SerializedName("account_type")
  private String accountType;
  /**
   * 开户银行
   */
  @SerializedName("account_bank")
  private String accountBank;
  /**
   * 开户银行全称（含支行]
   */
  @SerializedName("bank_name")
  private String bankName;
  /**
   * 开户银行联行号
   */
  @SerializedName("bank_branch_id")
  private String bankBranchId;
  /**
   * 银行账号
   */
  @SerializedName("account_number")
  private String accountNumber;
  /**
   * 汇款验证结果
   *
   * @see com.github.binarywang.wxpay.bean.applyment.enums.SettlementVerifyResultEnum
   */
  @SerializedName("verify_result")
  private String verifyResult;
  /**
   * 汇款验证失败原因
   *
   * @since 4.4.0
   * @date 2022.12.09
   */
  @SerializedName("verify_fail_reason")
  private String verifyFailReason;
}
