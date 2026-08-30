package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Sku层分享信息
 *
 * @author <a href="https://github.com/qrpcode">北鹤M</a>
 */
@Data
@NoArgsConstructor
@Deprecated
public class OrderSkuShareInfo implements Serializable {

  private static final long serialVersionUID = 705312408112124476L;

  /** 分享员openid */
  @JsonProperty("sharer_openid")
  private String sharerOpenid;

  /** 分享员unionid */
  @JsonProperty("sharer_unionid")
  private String sharerUnionid;

  /** 分享员类型，0：普通分享员，1：店铺分享员 */
  @JsonProperty("sharer_type")
  private Integer sharerType;

  /** 分享场景 {@link me.chanjar.weixin.channel.enums.ShareScene} */
  @JsonProperty("share_scene")
  private Integer shareScene;

  /** 商品skuid */
  @JsonProperty("sku_id")
  private String skuId;

  /** 是否来自企微分享 */
  @JsonProperty("from_wecom")
  private Boolean fromWecom;

}
