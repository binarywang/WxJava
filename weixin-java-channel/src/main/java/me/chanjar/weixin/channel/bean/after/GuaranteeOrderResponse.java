package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

@Data
@EqualsAndHashCode(callSuper = true)
public class GuaranteeOrderResponse extends WxChannelBaseResponse {
  private static final long serialVersionUID = 3977781489692530604L;

  @JsonProperty("guarantee_info")
  private GuaranteeInfo guaranteeInfo;
}
