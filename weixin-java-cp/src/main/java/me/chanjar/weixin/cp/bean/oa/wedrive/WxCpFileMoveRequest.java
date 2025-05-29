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
 * 移动文件请求.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpFileMoveRequest implements Serializable {
  private static final long serialVersionUID = -4960239393895754138L;

  /**
   * 如果移动到的目标目录与需要移动的文件重名时，是否覆盖。
   * true:重名文件覆盖
   * false:重名文件进行冲突重命名处理（移动后文件名格式如xxx(1).txt xxx(1).doc等）
   */
  @SerializedName("replace")
  private Boolean replace;

  /**
   * 当前目录的fileid,根目录时为空间spaceid
   */
  @SerializedName("fatherid")
  private String fatherId;

  /**
   * 文件fileid
   */
  @SerializedName("fileid")
  private String[] fileId;

  /**
   * From json wx cp file move request.
   *
   * @param json the json
   * @return the wx cp file move request
   */
  public static WxCpFileMoveRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpFileMoveRequest.class);
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
