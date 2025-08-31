package cn.binarywang.wx.miniapp.bean.kefu.request;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 小程序客服账号操作请求.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaKfAccountRequest implements Serializable {
  private static final long serialVersionUID = -4953504451749066635L;

  /**
   * 客服账号.
   */
  @SerializedName("kf_account")
  private String kfAccount;

  /**
   * 客服昵称.
   */
  @SerializedName("kf_nick")
  private String kfNick;

  /**
   * 客服密码.
   */
  @SerializedName("kf_pwd")
  private String kfPwd;

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }

  public static WxMaKfAccountRequest fromJson(String json) {
    return WxMaGsonBuilder.create().fromJson(json, WxMaKfAccountRequest.class);
  }
}