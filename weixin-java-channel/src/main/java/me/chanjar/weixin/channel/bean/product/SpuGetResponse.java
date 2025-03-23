package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 商品信息 响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SpuGetResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -8955745006296226140L;

  /** 商品线上数据，入参data_type==2时不返回该字段；入参data_type==3且商品未处于上架状态，不返回该字段 */
  @JsonProperty("product")
  private SpuInfo product;

  /** 商品草稿数据，入参data_type==1时不返回该字段 */
  @JsonProperty("edit_product")
  private SpuInfo editProduct;

  /** 当日售卖上限提醒，当店铺受到售卖管控时返回，没有返回本字段表示没有无额外限制 */
  @JsonProperty("sale_limit_info")
  private ProductSaleLimitInfo saleLimitInfo;
}
