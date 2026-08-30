package com.binarywang.wxjava.store.bean.supplier;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 按商品自动分配商品列表响应。
 *
 * @author <a href="https://github.com/github-copilot">GitHub Copilot</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductListResponse extends WxStoreBaseResponse {
  private static final long serialVersionUID = -7096250227033388295L;

  @JsonProperty("product_list")
  private List<ProductInfo> productList;

  @JsonProperty("next_key")
  private String nextKey;

  @JsonProperty("has_more")
  private Boolean hasMore;

  @Data
  @NoArgsConstructor
  public static class ProductInfo implements Serializable {
    private static final long serialVersionUID = -4482299212575966325L;

    @JsonProperty("product_id")
    private String productId;

    @JsonProperty("supplier_id")
    private String supplierId;
  }
}
