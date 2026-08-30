package me.chanjar.weixin.channel.bean.sharer;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分享员订单
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@Deprecated
public class SharerOrder implements Serializable {

  private static final long serialVersionUID = 1528673402572025670L;
  /**
   * 订单号
   */
  @JsonProperty("order_id")
  private String orderId;

  /**
   * 分享场景 {@link me.chanjar.weixin.channel.enums.ShareScene}
   */
  @JsonProperty("share_scene")
  private Integer sharerScene;

  /**
   * 分享员openid
   */
  @JsonProperty("sharer_openid")
  private String sharerOpenid;

  /**
   * 分享员类型 {@link me.chanjar.weixin.channel.enums.SharerType}
   */
  @JsonProperty("sharer_type")
  private Integer sharerType;

  /**
   * 商品sku_id
   */
  @JsonProperty("sku_id")
  private String skuId;


  /**
   * 商品唯一id
   */
  @JsonProperty("product_id")
  private String productId;


  /**
   * 是否从企微分享
   */
  @JsonProperty("from_wecom")
  private Boolean fromWxWork;


  /**
   * 视频号场景信息
   */
  @JsonProperty("finder_scene_info")
  private FinderSceneInfo sceneInfo;


}
