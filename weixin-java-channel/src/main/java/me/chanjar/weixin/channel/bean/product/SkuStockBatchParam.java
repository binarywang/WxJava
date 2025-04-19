package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkuStockBatchParam implements Serializable {

  private static final long serialVersionUID = 3706326762056220559L;

  /** 商品ID列表 注意这里是 productId ，序列化参数没有写错 */
  @JsonProperty("product_id")
  private List<String> productIds;
}
