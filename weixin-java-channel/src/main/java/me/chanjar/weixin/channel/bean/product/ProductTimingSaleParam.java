package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;

/** 商品立即开售请求参数.
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.product.ProductTimingSaleParam}。
*/
@Data
@Deprecated
public class ProductTimingSaleParam implements Serializable {
  private static final long serialVersionUID = -7185451543781817487L;

  @JsonProperty("product_id")
  private String productId;
  @JsonProperty("task_id")
  private Long taskId;
}
