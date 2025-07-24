package me.chanjar.weixin.mp.bean.draft;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 草稿箱能力-图片消息里的图片相关信息
 *
 * @author 阿杆
 * created on 2025/5/23
 */
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class WxMpDraftImageInfo implements Serializable {

  private static final long serialVersionUID = -1997245511033770476L;

  /**
   * 图片列表
   */
  @SerializedName("image_list")
  private List<ImageItem> imageList;

  public static WxMpDraftImageInfo fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpDraftImageInfo.class);
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ImageItem implements Serializable {
    private static final long serialVersionUID = 4180558781166966752L;
    /**
     * 图片消息里的图片素材id（必须是永久MediaID）
     */
    @SerializedName("image_media_id")
    private String imageMediaId;
  }

}
