package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 商品属性映射及推荐响应.
 *
 * @author GitHub Copilot
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ExternalProductMappingNewResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 4536547956225312823L;

  /** 映射属性结果. */
  @JsonProperty("attributes")
  private List<JsonNode> attributes;
}
