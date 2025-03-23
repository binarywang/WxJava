package com.github.binarywang.wxpay.bean.bank;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 银行信息
 *
 * @author zhongjun
 * created on  2022/5/12
 **/
@Data
public class BankInfo implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 银行别名
   */
  @SerializedName("bank_alias")
  private String bankAlias;
  /**
   * 银行别名编码
   */
  @SerializedName("bank_alias_code")
  private String bankAliasCode;
  /**
   * 开户银行
   */
  @SerializedName("account_bank")
  private String accountBank;
  /**
   * 开户银行编码
   */
  @SerializedName("account_bank_code")
  private Integer accountBankCode;
  /**
   * 是否需要填写支行
   */
  @SerializedName("need_bank_branch")
  private Boolean needBankBranch;
}
