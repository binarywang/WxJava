package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/** 商品属性映射及推荐响应. */
@Data
@EqualsAndHashCode(callSuper = true)
public class ExternalProductMappingNewResponse extends WxChannelBaseResponse {
  private static final long serialVersionUID = 4536547956225312823L;

  @JsonProperty("attributes")
  private List<Attribute> attributes;

  /** 推荐属性. */
  @Data
  @NoArgsConstructor
  public static class Attribute implements Serializable {
    private static final long serialVersionUID = -4072024462101489333L;

    private String key;
    private String value;
  }
}
