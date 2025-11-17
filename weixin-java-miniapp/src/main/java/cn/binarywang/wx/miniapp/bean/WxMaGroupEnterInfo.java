package cn.binarywang.wx.miniapp.bean;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * 微信小程序群入口信息.
 * 对应 wx.getGroupEnterInfo 接口返回的解密数据
 *
 * @see <a href="https://developers.weixin.qq.com/miniprogram/dev/api/open-api/group/wx.getGroupEnterInfo.html">wx.getGroupEnterInfo 官方文档</a>
 */
@Data
public class WxMaGroupEnterInfo implements Serializable {
  private static final long serialVersionUID = -8053613683499632227L;

  /**
   * 多聊群下返回的群唯一标识.
   */
  @SerializedName("opengid")
  private String openGId;

  /**
   * 单聊群下返回的群唯一标识.
   */
  @SerializedName("open_single_roomid")
  private String openSingleRoomid;

  /**
   * 用户在当前群的唯一标识.
   */
  @SerializedName("group_openid")
  private String groupOpenid;

  /**
   * 聊天室类型.
   */
  @SerializedName("chat_type")
  private Integer chatType;

  public static WxMaGroupEnterInfo fromJson(String json) {
    return WxMaGsonBuilder.create().fromJson(json, WxMaGroupEnterInfo.class);
  }
}
