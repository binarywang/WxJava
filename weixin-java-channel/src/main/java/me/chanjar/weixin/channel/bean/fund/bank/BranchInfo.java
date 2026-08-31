package me.chanjar.weixin.channel.bean.fund.bank;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分店信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.fund.bank.BranchInfo}。
 */
@Data
@NoArgsConstructor
@Deprecated
public class BranchInfo implements Serializable {

  private static final long serialVersionUID = -2744729367131146892L;
  /** 支行联号 */
  @JsonProperty("branch_id")
  private Integer branchId;

  /** 银行全称（含支行） */
  @JsonProperty("branch_name")
  private String branchName;
}
