package cn.binarywang.wx.miniapp.bean.kefu;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 小程序客服列表.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaKfList implements Serializable {
  private static final long serialVersionUID = 6416633293297389972L;

  @SerializedName("kf_list")
  private List<WxMaKfInfo> kfList;

  public static WxMaKfList fromJson(String json) {
    return WxMaGsonBuilder.create().fromJson(json, WxMaKfList.class);
  }

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}