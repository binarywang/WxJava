package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 站内外商品属性映射（新版） 响应
 *
 * @author <a href="https://github.com/features/copilot">GitHub Copilot</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ExternalProductMappingNewResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -1L;

  /** 内部商品属性列表 */
  @JsonProperty("attributes")
  private List<ExternalAttribute> attributes;
}
