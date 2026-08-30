package com.binarywang.wxjava.store.bean.fund;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 账户信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountInfo implements Serializable {

  private static final long serialVersionUID = -2107134853480093451L;
  /** 账户类型 {@link com.binarywang.wxjava.store.enums.AccountType} */
  @JsonProperty("bank_account_type")
  private String bankAccountType;

  /** 开户银行 */
  @JsonProperty("account_bank")
  private String accountBank;

  /** 开户银行省市编码 */
  @JsonProperty("bank_address_code")
  private String bankAddressCode;

  /** 开户银行联行号 */
  @JsonProperty("bank_branch_id")
  private String bankBranchId;

  /** 开户银行全称 */
  @JsonProperty("bank_name")
  private String bankName;

  /** 银行账号 */
  @JsonProperty("account_number")
  private String accountNumber;

  /** 开户银行名称前端展示值 */
  @JsonProperty("account_bank4show")
  private String accountBank4show;

  /** 账户名称 */
  @JsonProperty("account_name")
  private String accountName;


}
