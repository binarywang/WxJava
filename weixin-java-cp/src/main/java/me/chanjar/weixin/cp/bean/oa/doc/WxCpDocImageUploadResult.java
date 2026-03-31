package me.chanjar.weixin.cp.bean.oa.doc;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 上传文档图片结果.
 */
@Data
public class WxCpDocImageUploadResult extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -324580838619106032L;

  @SerializedName("url")
  private String url;

  @SerializedName("image_url")
  private String imageUrl;

  @SerializedName("fileid")
  private String fileId;

  @SerializedName("imageid")
  private String imageId;

  @SerializedName("media_id")
  private String mediaId;

  @SerializedName("md5")
  private String md5;

  public String getEffectiveUrl() {
    return this.imageUrl != null ? this.imageUrl : this.url;
  }

  public static WxCpDocImageUploadResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpDocImageUploadResult.class);
  }

  @Override
  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }
}
