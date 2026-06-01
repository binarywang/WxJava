package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 新增第三方货源信息请求参数.
 *
 * @author GitHub Copilot
 */
@Data
@NoArgsConstructor
public class AddProductThirdPartySourceParam implements Serializable {

  private static final long serialVersionUID = -5784320217481497742L;

  /** 场景值. */
  @JsonProperty("scene_value")
  private Integer sceneValue;

  /** 商品发布方式. */
  @JsonProperty("publish_method")
  private Integer publishMethod;

  /** 货主信息. */
  private JsonNode supplier;

  /** 货主店铺经营表现. */
  @JsonProperty("supplier_shop_performance")
  private JsonNode supplierShopPerformance;

  /** 商品在货源平台信息. */
  @JsonProperty("product_source_info")
  private JsonNode productSourceInfo;
}
