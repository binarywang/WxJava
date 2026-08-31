package me.chanjar.weixin.channel.bean.product.assistant;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 取消商品开售参数。
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.product.assistant.CancelTimingSaleParam}。
 */
@Data
@NoArgsConstructor
@Deprecated
public class CancelTimingSaleParam implements Serializable {

  private static final long serialVersionUID = -3750831026611057323L;

  /** 商品 ID。 */
  @JsonProperty("product_id")
  private String productId;
}
