package me.chanjar.weixin.cp.bean.oa.wedrive;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 删除文件请求.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpFileDeleteRequest implements Serializable {
  private static final long serialVersionUID = -4960239393895754138L;

  @SerializedName("fileid")
  private List<String> fileId;

  /**
   * From json wx cp file delete request.
   *
   * @param json the json
   * @return the wx cp file delete request
   */
  public static WxCpFileDeleteRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpFileDeleteRequest.class);
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
