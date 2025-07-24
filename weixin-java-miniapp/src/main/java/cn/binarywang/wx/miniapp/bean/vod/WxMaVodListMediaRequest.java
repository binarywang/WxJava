package cn.binarywang.wx.miniapp.bean.vod;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaVodListMediaRequest implements Serializable {
  private static final long serialVersionUID = 7495157056049312108L;

  /**
   * <pre>
   *   必填：否
   *   说明：根据剧目id获取剧集信息
   * </pre>
   */
  @SerializedName("drama_id")
  private Integer dramaId;

  /**
   * <pre>
   *   必填：否
   *   说明：媒资文件名，支持精确匹配、模糊匹配。文件太多时使用该参数进行模糊匹配可能无法得到结果，推荐使用 media_name_fuzzy 参数。
   * </pre>
   */
  @SerializedName("media_name")
  private String mediaName;

  /**
   * <pre>
   *   必填：否
   *   说明：媒资文件名，模糊匹配。
   * </pre>
   */
  @SerializedName("media_name_fuzzy")
  private String mediaNameFuzzy;

  /**
   * <pre>
   *   必填：否
   *   说明：媒资上传时间 >= start_time。
   * </pre>
   */
  @SerializedName("start_time")
  private Long startTime;

  /**
   * <pre>
   *   必填：否
   *   说明：媒资上传时间 < end_time。
   * </pre>
   */
  @SerializedName("end_time")
  private Long endTime;

  /**
   * <pre>
   * 必填：否
   * 说明：分页拉取的起始偏移量。默认值：0。
   * </pre>
   */
  @SerializedName("offset")
  private Integer offset;

  /**
   * <pre>
   *   必填：否
   *   说明：分页拉取的最大返回结果数。默认值：100；最大值：100。
   * </pre>
   */
  @SerializedName("limit")
  private Integer limit;

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}
