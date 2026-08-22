package com.github.binarywang.wxpay.bean.invoice;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.io.Serializable;
/** 用户抬头填写小程序跳转信息。 */
@Data public class TitleUrlResult implements Serializable {
  private static final long serialVersionUID = 1L;
  @SerializedName("title_url") private String titleUrl;
  @SerializedName("miniprogram_appid") private String miniprogramAppid;
  @SerializedName("miniprogram_path") private String miniprogramPath;
  @SerializedName("miniprogram_user_name") private String miniprogramUserName;
}
