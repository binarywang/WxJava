package com.binarywang.wxjava.store.bean.fund;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 账户余额信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class BalanceInfoResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = 4480496860612566921L;
  /** 可提现余额 */
  @JsonProperty("available_amount")
  private Integer availableAmount;

  /** 待结算余额 */
  @JsonProperty("pending_amount")
  private Integer pendingAmount;

  /** 二级商户号 */
  @JsonProperty("sub_mchid")
  private String subMchid;

}
