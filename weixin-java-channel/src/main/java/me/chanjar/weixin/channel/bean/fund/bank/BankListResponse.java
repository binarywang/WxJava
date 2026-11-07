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
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.fund.bank.BankListResponse}。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class BankListResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 7912035853286944260L;
  /** 银行信息列表 */
  @JsonProperty("data")
  private List<BankInfo> data;
}
