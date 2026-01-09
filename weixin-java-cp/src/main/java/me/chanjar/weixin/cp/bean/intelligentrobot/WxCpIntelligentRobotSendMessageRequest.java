package me.chanjar.weixin.cp.bean.intelligentrobot;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 智能机器人发送消息请求
 * 官方文档: https://developer.work.weixin.qq.com/document/path/100719
 *
 * @author Binary Wang
 */
@Data
public class WxCpIntelligentRobotSendMessageRequest implements Serializable {
  private static final long serialVersionUID = -1L;

  /**
   * 机器人ID，必填
   */
  @SerializedName("robot_id")
  private String robotId;

  /**
   * 接收消息的用户ID，必填
   */
  @SerializedName("userid")
  private String userid;

  /**
   * 消息内容，必填
   */
  @SerializedName("message")
  private String message;

  /**
   * 会话ID，可选，用于保持会话连续性
   */
  @SerializedName("session_id")
  private String sessionId;

  /**
   * 消息ID，可选
   */
  @SerializedName("msg_id")
  private String msgId;

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

  public static WxCpIntelligentRobotSendMessageRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpIntelligentRobotSendMessageRequest.class);
  }
}
