package me.chanjar.weixin.common.bean.ocr;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * OCR菜单识别结果.
 *
 * @author GitHub Copilot
 */
@Data
public class WxOcrMenuResult implements Serializable {
  private static final long serialVersionUID = -8062516251827437945L;

  @SerializedName("img_size")
  private WxOcrImgSize imgSize;
  @SerializedName("items")
  private List<Items> items;

  public static WxOcrMenuResult fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxOcrMenuResult.class);
  }

  @Override
  public String toString() {
    return WxGsonBuilder.create().toJson(this);
  }

  @Data
  public static class Items implements Serializable {
    private static final long serialVersionUID = 3066181677009102792L;

    @SerializedName("text")
    private String text;
    @SerializedName("pos")
    private WxOcrPos pos;

    @Override
    public String toString() {
      return WxGsonBuilder.create().toJson(this);
    }
  }
}
