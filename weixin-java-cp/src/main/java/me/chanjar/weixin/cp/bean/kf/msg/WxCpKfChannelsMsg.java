package me.chanjar.weixin.cp.bean.kf.msg;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 视频号消息
 *
 * @author liuzhao created on 2024/2/25
 */
@NoArgsConstructor
@Data
public class WxCpKfChannelsMsg {

  /**
   * 视频号名称
   */
  @SerializedName("nickname")
  private String nickname;

  /**
   * 视频/直播标题
   */
  @SerializedName("title")
  private String title;

  /**
   * 视频/直播描述
   */
  @SerializedName("desc")
  private String desc;

  /**
   * 封面图片url
   */
  @SerializedName("cover_url")
  private String coverUrl;

  /**
   * 视频/直播链接
   */
  @SerializedName("url")
  private String url;

  /**
   * 视频号账号名称
   */
  @SerializedName("find_username")
  private String findUsername;

}
