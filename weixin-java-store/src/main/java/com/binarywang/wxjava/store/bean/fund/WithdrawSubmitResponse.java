package com.binarywang.wxjava.store.bean.fund;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 提现提交响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WithdrawSubmitResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = -8269579250564427758L;
  /** 二维码ticket,可用于获取二维码和查询二维码状态 */
  @JsonProperty("qrcode_ticket")
  private String qrcodeTicket;
}
