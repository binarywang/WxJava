package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;

/** 获取商品移动应用跳转 scheme 码请求参数.
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.product.ProductSchemeParam}。
*/
@Data
@Deprecated
public class ProductSchemeParam implements Serializable {
  private static final long serialVersionUID = 613832623081127830L;

  @JsonProperty("product_id")
  private String productId;
  @JsonProperty("from_appid")
  private String fromAppid;
  private Integer expire;
  @JsonProperty("ext_info")
  private String extInfo;
}
