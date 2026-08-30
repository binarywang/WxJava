package com.binarywang.wxjava.store.bean.fund.bank;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 银行信息响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BankListResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = 7912035853286944260L;
  /** 银行信息列表 */
  @JsonProperty("data")
  private List<BankInfo> data;
}
