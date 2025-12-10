package cn.binarywang.wx.miniapp.bean.kefu;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 小程序客服会话.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaKfSession implements Serializable {
  private static final long serialVersionUID = -6987567952389036965L;

  /**
   * 正在接待的客服，为空表示没有人在接待.
   */
  @SerializedName("kf_account")
  private String kfAccount;

  /**
   * 会话接入的时间.
   */
  @SerializedName("createtime")
  private Long createTime;

  public static WxMaKfSession fromJson(String json) {
    return WxMaGsonBuilder.create().fromJson(json, WxMaKfSession.class);
  }

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}