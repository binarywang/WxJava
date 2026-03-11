package me.chanjar.weixin.common.bean.result;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Data;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;

@Data
public class WxMinishopImageUploadResult  implements Serializable {
  private static final long serialVersionUID = 330834334738622332L;

  private String errcode;
  private String errmsg;


  private WxMinishopPicFileResult picFile;


  public static WxMinishopImageUploadResult fromJson(String json) {
    JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
    WxMinishopImageUploadResult result = new WxMinishopImageUploadResult();
    result.setErrcode(jsonObject.get(WxConsts.ERR_CODE).getAsNumber().toString());
    if (result.getErrcode().equals("0")) {
      WxMinishopPicFileResult picFileResult = new WxMinishopPicFileResult();
      JsonObject picObject = jsonObject.get("pic_file").getAsJsonObject();
      JsonElement mediaId = picObject.get("media_id");
      picFileResult.setMediaId(mediaId==null ? "" : mediaId.getAsString());
      JsonElement payMediaId = picObject.get("pay_media_id");
      picFileResult.setPayMediaId(payMediaId==null ? "" : payMediaId.getAsString());
      JsonElement tempImgUrl = picObject.get("temp_img_url");
      picFileResult.setTempImgUrl(tempImgUrl==null ? "" : tempImgUrl.getAsString());
      result.setPicFile(picFileResult);

    }
    return result;
  }

  @Override
  public String toString() {
    return WxGsonBuilder.create().toJson(this);
  }
}
