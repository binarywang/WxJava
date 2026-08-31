package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * spu库存列表
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.product.SkuStockBatchList}。
 */
@Data
@NoArgsConstructor
@Deprecated
public class SkuStockBatchList implements Serializable {
  private static final long serialVersionUID = -8082428962162052815L;

  /** 库存信息 */
  @JsonProperty("spu_stock_list")
  private List<SpuStockInfo> spuStockList;

}
