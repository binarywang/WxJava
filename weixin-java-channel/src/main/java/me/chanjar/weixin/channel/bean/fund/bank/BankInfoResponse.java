package me.chanjar.weixin.channel.bean.fund.bank;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 银行信息响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.fund.bank.BankInfoResponse}。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class BankInfoResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 8583893898929290526L;
  /** 银行信息列表 */
  @JsonProperty("data")
  private List<BankInfo> data;

  /** 总数 */
  @JsonProperty("total_count")
  private Integer totalCount;
}
