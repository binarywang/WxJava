package me.chanjar.weixin.cp.bean.oa.wedrive;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 下载文件返回信息.
 *
 * @author Wang_Wong
 */
@Data
public class WxCpFileDownload extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -5028321625142879581L;

  @SerializedName("download_url")
  private String downloadUrl;

  @SerializedName("cookie_name")
  private String cookieName;

  @SerializedName("cookie_value")
  private String cookieValue;

  /**
   * From json wx cp file download.
   *
   * @param json the json
   * @return the wx cp file download
   */
  public static WxCpFileDownload fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpFileDownload.class);
  }

}
