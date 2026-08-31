package me.chanjar.weixin.channel.bean.talent;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 带货助手-获取达人橱窗商品列表 响应
 *
 * @author <a href="https://github.com/features/copilot">GitHub Copilot</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.talent.TalentWindowProductListResponse}。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class TalentWindowProductListResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 8263047195826340712L;

  /** 橱窗商品列表 */
  @JsonProperty("products")
  private List<ProductInfo> products;

  /** 本次翻页的上下文，用于顺序翻页请求 */
  @JsonProperty("last_buffer")
  private String lastBuffer;

  /** 橱窗商品基础信息 */
  @Data
  @NoArgsConstructor
  public static class ProductInfo implements Serializable {

    private static final long serialVersionUID = 6142837490516284039L;

    /** 橱窗商品id */
    @JsonProperty("product_id")
    private String productId;

    /** 对于自营商品会返回，代表商品来源店铺的appid */
    @JsonProperty("appid")
    private String appid;

    /**
     * 商品来源：1=来源店铺的自营商品，2=来源选品中心的带货商品
     */
    @JsonProperty("product_source")
    private Integer productSource;

    /**
     * 对于带货商品会返回，代表商品在货源小店中的商品id
     */
    @JsonProperty("out_product_id")
    private String outProductId;
  }
}
