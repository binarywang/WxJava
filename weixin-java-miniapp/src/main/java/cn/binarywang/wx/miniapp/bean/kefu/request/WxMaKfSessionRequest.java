package cn.binarywang.wx.miniapp.bean.kefu.request;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 小程序客服会话操作请求.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaKfSessionRequest implements Serializable {
  private static final long serialVersionUID = -3278295399203344398L;

  /**
   * 客服账号.
   */
  @SerializedName("kf_account")
  private String kfAccount;

  /**
   * 用户openid.
   */
  @SerializedName("openid")
  private String openid;

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }

  public static WxMaKfSessionRequest fromJson(String json) {
    return WxMaGsonBuilder.create().fromJson(json, WxMaKfSessionRequest.class);
  }
}