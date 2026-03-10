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
 * 小程序客服会话列表.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaKfSessionList implements Serializable {
  private static final long serialVersionUID = -1538600729426188776L;

  @SerializedName("sessionlist")
  private List<SessionInfo> sessionList;

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class SessionInfo implements Serializable {
    private static final long serialVersionUID = -2077261313274513580L;

    /**
     * 粉丝的openid.
     */
    @SerializedName("openid")
    private String openid;

    /**
     * 会话创建时间，UNIX时间戳.
     */
    @SerializedName("createtime")
    private Long createTime;

    /**
     * 粉丝的最后一条消息的时间，UNIX时间戳.
     */
    @SerializedName("latest_time")
    private Long latestTime;
  }

  public static WxMaKfSessionList fromJson(String json) {
    return WxMaGsonBuilder.create().fromJson(json, WxMaKfSessionList.class);
  }

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}