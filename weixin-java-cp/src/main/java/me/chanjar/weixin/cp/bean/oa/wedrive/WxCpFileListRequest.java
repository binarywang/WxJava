package me.chanjar.weixin.cp.bean.oa.wedrive;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 获取文件列表请求.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpFileListRequest implements Serializable {
  private static final long serialVersionUID = -4960239393895754138L;

  @SerializedName("spaceid")
  private String spaceId;

  @SerializedName("fatherid")
  private String fatherId;

  @SerializedName("sort_type")
  private Integer sortType;

  @SerializedName("start")
  private Integer start;

  @SerializedName("limit")
  private Integer limit;

  /**
   * From json wx cp file list request.
   *
   * @param json the json
   * @return the wx cp file list request
   */
  public static WxCpFileListRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpFileListRequest.class);
  }

  /**
   * To json string.
   *
   * @return the string
   */
  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
