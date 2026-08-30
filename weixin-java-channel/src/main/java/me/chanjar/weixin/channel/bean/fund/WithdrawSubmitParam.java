package me.chanjar.weixin.channel.bean.fund;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 提现提交参数
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Deprecated
public class WithdrawSubmitParam implements Serializable {

  private static final long serialVersionUID = 5801338663530567830L;
  /** 提现金额（单位：分） */
  @JsonProperty("amount")
  private Integer amount;

  /** 提现备注 */
  @JsonProperty("remark")
  private String remark;

  /** 银行附言 */
  @JsonProperty("bank_memo")
  private String bankMemo;

}
