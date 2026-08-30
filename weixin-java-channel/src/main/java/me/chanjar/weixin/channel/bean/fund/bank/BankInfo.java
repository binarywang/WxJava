package me.chanjar.weixin.channel.bean.fund.bank;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 银行信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@Deprecated
public class BankInfo implements Serializable {

  private static final long serialVersionUID = -4837989875996346711L;
  /** 开户银行 */
  @JsonProperty("account_bank")
  private String accountBank;

  /** 银行编码 */
  @JsonProperty("bank_code")
  private String bankCode;

  /** 银行联号 */
  @JsonProperty("bank_id")
  private String bankId;

  /** 银行名称（不包括支行） */
  @JsonProperty("bank_name")
  private String bankName;

  /** 银行类型(1.对公，2.对私) */
  @JsonProperty("bank_type")
  private Integer bankType;

  /** 是否需要填写支行信息 */
  @JsonProperty("need_branch")
  private Boolean needBranch;

  /** 支行联号 */
  @JsonProperty("branch_id")
  private String branchId;

}
