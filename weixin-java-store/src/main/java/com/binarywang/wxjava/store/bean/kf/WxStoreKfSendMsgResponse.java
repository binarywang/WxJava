package com.binarywang.wxjava.store.bean.kf;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/** 发送客服消息响应。 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WxStoreKfSendMsgResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = 1L;

  /** 消息 id。 */
  @JsonProperty("msg_id")
  private String msgId;
}
