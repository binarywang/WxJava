package me.chanjar.weixin.channel.bean.fund.bank;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 银行城市信息响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.fund.bank.BankCityResponse}。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class BankCityResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -6212360101083304631L;
  /** 银行城市信息列表 */
  @JsonProperty("data")
  private List<BankCityInfo> data;

  /** 总数 */
  @JsonProperty("total_count")
  private Integer totalCount;
}
