package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分享信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@Deprecated
public class OrderSharerInfo implements Serializable {

  private static final long serialVersionUID = 7183259072254660971L;
  /**
   * 分享员openid
   */
  @JsonProperty("sharer_openid")
  private String sharerOpenid;

  /**
   * 分享员unionid
   */
  @JsonProperty("sharer_unionid")
  private String sharerUnionid;

  /**
   * 分享员类型，0：普通分享员，1：店铺分享员
   */
  @JsonProperty("sharer_type")
  private Integer sharerType;

  /**
   * 分享场景 {@link me.chanjar.weixin.channel.enums.ShareScene}
   */
  @JsonProperty("share_scene")
  private Integer shareScene;

  /**
   * 分享员数据是否已经解析完成【1:解析完成 0:解析中】
   */
  @JsonProperty("handling_progress")
  private Integer handlingProgress;
}
