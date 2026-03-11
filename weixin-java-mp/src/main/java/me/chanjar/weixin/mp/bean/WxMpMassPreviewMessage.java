package me.chanjar.weixin.mp.bean;

import lombok.Data;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * @author miller
 */
@Data
public class WxMpMassPreviewMessage implements Serializable {
  private static final long serialVersionUID = 9095211638358424020L;

  private String toWxUserName;
  private String toWxUserOpenid;
  /**
   * <pre>
   * 消息类型
   * 请使用
   * {@link me.chanjar.weixin.common.api.WxConsts.MassMsgType#IMAGE}
   * {@link me.chanjar.weixin.common.api.WxConsts.MassMsgType#MPNEWS}
   * {@link me.chanjar.weixin.common.api.WxConsts.MassMsgType#TEXT}
   * {@link me.chanjar.weixin.common.api.WxConsts.MassMsgType#MPVIDEO}
   * {@link me.chanjar.weixin.common.api.WxConsts.MassMsgType#VOICE}
   * 如果msgtype和media_id不匹配的话，会返回系统繁忙的错误
   * </pre>
   */
  private String msgType;
  private String content;
  private String mediaId;

  public WxMpMassPreviewMessage() {
    super();
  }

  public String toJson() {
    return WxMpGsonBuilder.create().toJson(this);
  }
}
