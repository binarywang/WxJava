package me.chanjar.weixin.cp.bean.intelligentrobot;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 智能机器人聊天请求
 *
 * @author Binary Wang
 */
@Data
public class WxCpIntelligentRobotChatRequest implements Serializable {
  private static final long serialVersionUID = -1L;

  /**
   * 机器人ID
   */
  @SerializedName("robot_id")
  private String robotId;

  /**
   * 用户ID
   */
  @SerializedName("userid")
  private String userid;

  /**
   * 消息内容
   */
  @SerializedName("message")
  private String message;

  /**
   * 会话ID，可选，用于保持会话连续性
   */
  @SerializedName("session_id")
  private String sessionId;

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

  public static WxCpIntelligentRobotChatRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpIntelligentRobotChatRequest.class);
  }
}