package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/** 商品属性映射及推荐响应. */
@Data
@EqualsAndHashCode(callSuper = true)
public class ExternalProductMappingNewResponse extends WxChannelBaseResponse {
  private static final long serialVersionUID = 4536547956225312823L;

  @JsonProperty("attributes")
  private List<ExternalProductMappingNewParam.ExternalAttribute> attributes;
}
