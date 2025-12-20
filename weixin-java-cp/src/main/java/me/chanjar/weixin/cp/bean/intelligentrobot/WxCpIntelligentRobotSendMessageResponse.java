package me.chanjar.weixin.cp.bean.intelligentrobot;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 智能机器人发送消息响应
 * 官方文档: https://developer.work.weixin.qq.com/document/path/100719
 *
 * @author Binary Wang
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxCpIntelligentRobotSendMessageResponse extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -1L;

  /**
   * 消息ID
   */
  @SerializedName("msg_id")
  private String msgId;

  /**
   * 会话ID
   */
  @SerializedName("session_id")
  private String sessionId;

  public static WxCpIntelligentRobotSendMessageResponse fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpIntelligentRobotSendMessageResponse.class);
  }

  @Override
  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }
}
