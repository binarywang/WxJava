package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 站内外商品属性映射 响应
 *
 * @author <a href="https://github.com/features/copilot">GitHub Copilot</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ExternalProductMappingResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -1L;

  /** 外部商品属性key */
  @JsonProperty("external_attribute_name")
  private String externalAttributeName;

  /** 外部商品属性值 */
  @JsonProperty("external_attribute_value")
  private String externalAttributeValue;

  /** 内部商品属性key */
  @JsonProperty("internal_attribute_name")
  private String internalAttributeName;

  /** 内部商品属性值（可能多选） */
  @JsonProperty("internal_attribute_value")
  private List<String> internalAttributeValue;
}
