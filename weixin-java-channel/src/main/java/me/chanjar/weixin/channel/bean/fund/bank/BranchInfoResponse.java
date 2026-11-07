package me.chanjar.weixin.channel.bean.fund.bank;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 支行信息响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.fund.bank.BranchInfoResponse}。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class BranchInfoResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -1419832502854175767L;
  /** 总数 */
  @JsonProperty("total_count")
  private Integer totalCount;

  /** 当前分页数量 */
  @JsonProperty("count")
  private Integer count;

  /** 银行名称 */
  @JsonProperty("account_bank")
  private String accountBank;

  /** 银行编码 */
  @JsonProperty("account_bank_code")
  private String accountBankCode;

  /** 银行别名 */
  @JsonProperty("bank_alias")
  private String bankAlias;

  /** 银行别名编码 */
  @JsonProperty("bank_alias_code")
  private String bankAliasCode;

  /** 支行信息列表 */
  @JsonProperty("data")
  private List<BranchInfo> data;

}
