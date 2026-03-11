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
 * 草稿箱能力-图片消息里的封面裁剪信息
 *
 * @author 阿杆
 * created on 2025/5/23
 */
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class WxMpDraftCoverInfo implements Serializable {

  private static final long serialVersionUID = -1676442833397632638L;

  /**
   * 封面裁剪信息，裁剪比例ratio支持：“1_1”，“16_9”,“2.35_1”。
   * 以图片左上角（0,0），右下角（1,1）建立平面坐标系，经过裁剪后的图片，其左上角所在的坐标填入x1，y1参数，右下角所在的坐标填入x2，y2参数
   */
  @SerializedName("crop_percent_list")
  private List<CropPercent> cropPercentList;

  public static WxMpDraftCoverInfo fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpDraftCoverInfo.class);
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class CropPercent implements Serializable {
    private static final long serialVersionUID = 8495528870408737871L;
    private String ratio;
    private String x1;
    private String y1;
    private String x2;
    private String y2;
  }

}
