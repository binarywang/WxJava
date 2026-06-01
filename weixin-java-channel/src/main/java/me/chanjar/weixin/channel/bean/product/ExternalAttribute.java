package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 外部属性键值对
 *
 * @author <a href="https://github.com/features/copilot">GitHub Copilot</a>
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExternalAttribute implements Serializable {

  private static final long serialVersionUID = -1L;

  /** 属性名称（key） */
  @JsonProperty("key")
  private String key;

  /** 属性值（value） */
  @JsonProperty("value")
  private String value;
}
