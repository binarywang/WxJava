package cn.binarywang.wx.miniapp.bean.complaint;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 小程序投诉通知回调URL请求实体
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on 2025-01-01
 */
@Data
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class WxMaComplaintNotifyUrlRequest implements Serializable {
  private static final long serialVersionUID = 3244929701614280806L;

  /**
   * <pre>
   * 字段名：通知地址
   * 是否必填：是
   * 描述：通知地址，仅支持https
   * </pre>
   */
  @SerializedName("url")
  private String url;

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}