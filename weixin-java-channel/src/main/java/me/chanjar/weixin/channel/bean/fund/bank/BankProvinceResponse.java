package me.chanjar.weixin.channel.bean.fund.bank;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 银行省份信息响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@Deprecated
public class BankProvinceResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -6187805847136359892L;
  /** 银行省份信息列表 */
  @JsonProperty("data")
  private List<BankProvinceInfo> data;

  /** 总数 */
  @JsonProperty("total_count")
  private Integer totalCount;
}
