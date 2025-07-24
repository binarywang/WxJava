package me.chanjar.weixin.open.bean.icp;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.chanjar.weixin.open.bean.result.WxOpenResult;

/**
 * @author xzh
 * @Description 上传小程序备案媒体材料结果
 * @createTime 2024/08/14 10:52
 */
@Getter
@Setter
@NoArgsConstructor
public class WxOpenUploadIcpMediaResult extends WxOpenResult {

  private static final long serialVersionUID = 6929154695595201734L;

  /**
   * 媒体材料类型。目前支持两种：图片("image")和视频("video")，示例值："image"
   */
  @SerializedName("type")
  private String type;

  /**
   * 媒体id，示例值："4ahCGpd3CYkE6RpkNkUR5czt3LvG8xDnDdKAz6bBKttSfM8p4k5Rj6823HXugPwQBurgMezyib7"
   */
  @SerializedName("media_id")
  private String mediaId;

  /**
   * 创建时间，UNIX时间戳，示例值：1692883651
   */
  @SerializedName("created_at")
  private String createdAt;
}
