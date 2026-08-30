package com.binarywang.wxjava.store.bean.fund;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 账户信息响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class AccountInfoResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = -8316068503468969533L;
  /** 账户信息 */
  @JsonProperty("account_info")
  private AccountInfo accountInfo;


}
