package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品销售库存限制
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSaleLimitInfo implements Serializable {

  /** 是否受到管控，商品存在售卖限制时，固定返回1 */
  @JsonProperty("is_limited")
  private Integer limited;

  /** 售卖限制标题 */
  @JsonProperty("title")
  private String title;

  /** 售卖限制描述 */
  @JsonProperty("sub_title")
  private String subTitle;
}
