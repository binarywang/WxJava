package me.chanjar.weixin.channel.bean.compass.shop;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 店铺开播数据
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class ShopLiveData implements Serializable {

  /** 直播id */
  @JsonProperty("live_id")
  private String liveId;

  /** 直播标题 */
  @JsonProperty("live_title")
  private String liveTitle;

  /** 开播时间，unix时间戳 */
  @JsonProperty("live_time")
  private String liveTime;

  /** 直播时长，单位秒 */
  @JsonProperty("live_duration")
  private String liveDuration;

  /** 直播封面 */
  @JsonProperty("live_cover_img_url")
  private String liveCoverImgUrl;
}
