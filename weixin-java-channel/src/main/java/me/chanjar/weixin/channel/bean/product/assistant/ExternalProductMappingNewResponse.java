package me.chanjar.weixin.channel.bean.product.assistant;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 商品属性映射及推荐响应。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class ExternalProductMappingNewResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -6192580254142696913L;

  /** 映射属性结果。 */
  @JsonProperty("attributes")
  private List<ExternalAttribute> attributes;
}
