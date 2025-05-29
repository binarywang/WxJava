package me.chanjar.weixin.channel.bean.live.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 商品基础数据
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@NoArgsConstructor
public class SpuBaseData implements Serializable {

  private static final long serialVersionUID = 3170611962212344198L;

  /**
   * 店铺商品id
   */
  @JsonProperty("src_spu_id")
  private String srcSpuId;

  /**
   * 店铺id
   */
  @JsonProperty("src")
  private Long src;

  /**
   * 商品名称
   */
  @JsonProperty("spu_name")
  private String spuName;

  /**
   * 商品id
   */
  @JsonProperty("spu_id")
  private Long spuId;

  /**
   * 商品小图
   */
  @JsonProperty("thumb_url")
  private String thumbUrl;

  /**
   * 商品价格
   */
  @JsonProperty("price")
  private Long price;

  /**
   * 店铺名称
   */
  @JsonProperty("src_name")
  private String srcName;

  /**
   * 库存
   */
  @JsonProperty("stock")
  private Long stock;

}
