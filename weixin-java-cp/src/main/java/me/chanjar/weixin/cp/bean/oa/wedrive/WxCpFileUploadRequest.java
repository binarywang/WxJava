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
 * 上传文件请求.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpFileUploadRequest implements Serializable {
  private static final long serialVersionUID = -4960239393895754138L;

  @SerializedName("spaceid")
  private String spaceId;

  @SerializedName("fatherid")
  private String fatherId;

  @SerializedName("file_name")
  private String fileName;

  @SerializedName("file_base64_content")
  private String fileBase64Content;

  /**
   * From json wx cp file upload request.
   *
   * @param json the json
   * @return the wx cp file upload request
   */
  public static WxCpFileUploadRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpFileUploadRequest.class);
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
