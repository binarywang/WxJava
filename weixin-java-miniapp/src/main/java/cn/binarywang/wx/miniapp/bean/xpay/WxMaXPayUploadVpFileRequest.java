package cn.binarywang.wx.miniapp.bean.xpay;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @date 2025-07-23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaXPayUploadVpFileRequest implements Serializable {
  private static final long serialVersionUID = 7495157056049312108L;
  @SerializedName("env")
  private Integer env;
  @SerializedName("base64_img")
  private String base64Img;
  @SerializedName("img_url")
  private String imgUrl;
  @SerializedName("file_name")
  private String fileName;


  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}
