package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品免审更新参数
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SpuFastInfo implements Serializable {

  /** 商品ID */
  @JsonProperty("product_id")
  protected String productId;

  /** SKU列表 */
  @JsonProperty("skus")
  protected List<SkuFastInfo> skus;

}
