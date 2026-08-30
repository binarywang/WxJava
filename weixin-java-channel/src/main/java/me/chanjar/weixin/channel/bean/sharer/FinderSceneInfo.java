package me.chanjar.weixin.channel.bean.sharer;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 视频号场景信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@Deprecated
public class FinderSceneInfo implements Serializable {

  private static final long serialVersionUID = 5298261857489231549L;
  /** 视频号唯一标识 */
  @JsonProperty("promoter_id")
  private String promoterId;

  /** 视频号昵称 */
  @JsonProperty("finder_nickname")
  private String finderNickname;

  /** 直播间唯一标识 */
  @JsonProperty("live_export_id")
  private String liveExportId;

  /** 短视频唯一标识 */
  @JsonProperty("video_export_id")
  private String videoExportId;

  /** 短视频标题 */
  @JsonProperty("video_title")
  private String videoTitle;

}
