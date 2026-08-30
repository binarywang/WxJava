package com.binarywang.wxjava.store.bean.fund;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 提现列表响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class WithdrawListResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = -7950467108750325235L;
  /** 提现单号列表 */
  @JsonProperty("withdraw_ids")
  private List<String> withdrawIds;

}
