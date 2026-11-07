package com.binarywang.wxjava.store.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/** 商品属性映射及推荐响应. */
@Data
@EqualsAndHashCode(callSuper = true)
public class ExternalProductMappingNewResponse extends WxStoreBaseResponse {
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
