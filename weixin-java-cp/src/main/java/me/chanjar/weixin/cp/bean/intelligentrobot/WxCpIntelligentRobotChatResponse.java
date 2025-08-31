package me.chanjar.weixin.cp.bean.intelligentrobot;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 智能机器人聊天响应
 *
 * @author Binary Wang
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxCpIntelligentRobotChatResponse extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -1L;

  /**
   * 机器人回复内容
   */
  @SerializedName("reply")
  private String reply;

  /**
   * 会话ID
   */
  @SerializedName("session_id")
  private String sessionId;

  /**
   * 消息ID
   */
  @SerializedName("msg_id")
  private String msgId;

  public static WxCpIntelligentRobotChatResponse fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpIntelligentRobotChatResponse.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }
}