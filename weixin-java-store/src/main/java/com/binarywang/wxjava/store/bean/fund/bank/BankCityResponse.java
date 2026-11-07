package com.binarywang.wxjava.store.bean.fund.bank;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 银行城市信息响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BankCityResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = -6212360101083304631L;
  /** 银行城市信息列表 */
  @JsonProperty("data")
  private List<BankCityInfo> data;

  /** 总数 */
  @JsonProperty("total_count")
  private Integer totalCount;
}
