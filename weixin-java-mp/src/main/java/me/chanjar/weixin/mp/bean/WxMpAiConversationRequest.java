package me.chanjar.weixin.mp.bean;

import lombok.Data;
import me.chanjar.weixin.mp.enums.AiLangType;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * 微信智能对话请求对象
 * 
 * @author Binary Wang
 */
@Data
public class WxMpAiConversationRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 用户输入的对话内容
   */
  private String query;

  /**
   * 会话ID，用于保持对话上下文
   */
  private String sessionId;

  /**
   * 语言类型，默认中文
   */
  private AiLangType lang = AiLangType.zh_CN;

  public String toJson() {
    return WxMpGsonBuilder.create().toJson(this);
  }

  public static WxMpAiConversationRequest fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpAiConversationRequest.class);
  }
}