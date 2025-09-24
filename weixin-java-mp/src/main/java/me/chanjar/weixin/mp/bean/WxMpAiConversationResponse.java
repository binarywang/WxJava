package me.chanjar.weixin.mp.bean;

import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * 微信智能对话响应对象
 * 
 * @author Binary Wang
 */
@Data
public class WxMpAiConversationResponse implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 智能对话回复内容
   */
  private String reply;

  /**
   * 会话ID
   */
  private String sessionId;

  /**
   * 错误码
   */
  private Integer errcode;

  /**
   * 错误消息
   */
  private String errmsg;

  public String toJson() {
    return WxMpGsonBuilder.create().toJson(this);
  }

  public static WxMpAiConversationResponse fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpAiConversationResponse.class);
  }
}