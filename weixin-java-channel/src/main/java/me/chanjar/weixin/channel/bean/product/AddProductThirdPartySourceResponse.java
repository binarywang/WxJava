package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/** 新增第三方货源信息响应. */
@Data
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class AddProductThirdPartySourceResponse extends WxChannelBaseResponse {
  private static final long serialVersionUID = -7528226120383065861L;

  @JsonProperty("third_party_source_id")
  private Long thirdPartySourceId;
}
