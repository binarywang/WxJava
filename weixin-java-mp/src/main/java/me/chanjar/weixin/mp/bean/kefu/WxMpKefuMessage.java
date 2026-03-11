package me.chanjar.weixin.mp.bean.kefu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.mp.builder.kefu.*;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 客服消息.
 *
 * @author chanjarster
 */
@Data
public class WxMpKefuMessage implements Serializable {
  private static final long serialVersionUID = -9196732086954365246L;

  private String toUser;
  private String msgType;
  private String content;
  private String mediaId;
  private String thumbMediaId;
  private String title;
  private String description;
  private String musicUrl;
  private String hqMusicUrl;
  private String kfAccount;
  private String cardId;
  private String mpNewsMediaId;
  private String miniProgramAppId;
  private String miniProgramPagePath;
  private String headContent;
  private String tailContent;
  private List<WxArticle> articles = new ArrayList<>();
  private String mpNewsArticleId;

  /**
   * 菜单消息里的菜单内容.
   */
  private List<MsgMenu> msgMenus = new ArrayList<>();

  /**
   * 获得文本消息builder.
   *
   * @return 文本消息builder
   */
  public static TextBuilder TEXT() {
    return new TextBuilder();
  }

  /**
   * 获得图片消息builder.
   *
   * @return 图片消息builder
   */
  public static ImageBuilder IMAGE() {
    return new ImageBuilder();
  }

  /**
   * 获得语音消息builder.
   *
   * @return 语音消息builder
   */
  public static VoiceBuilder VOICE() {
    return new VoiceBuilder();
  }

  /**
   * 获得视频消息builder.
   *
   * @return 视频消息builder
   */
  public static VideoBuilder VIDEO() {
    return new VideoBuilder();
  }

  /**
   * 获得音乐消息builder.
   *
   * @return 音乐消息builder
   */
  public static MusicBuilder MUSIC() {
    return new MusicBuilder();
  }

  /**
   * 获得图文消息（点击跳转到外链）builder.
   *
   * @return 图文消息builder
   */
  public static NewsBuilder NEWS() {
    return new NewsBuilder();
  }

  /**
   * 获得图文消息（点击跳转到图文消息页面）builder.
   *
   * @return 图文消息builder
   */
  public static MpNewsBuilder MPNEWS() {
    return new MpNewsBuilder();
  }

  /**
   * 获得卡券消息builder.
   *
   * @return 卡券消息builder
   */
  public static WxCardBuilder WXCARD() {
    return new WxCardBuilder();
  }

  /**
   * 获得菜单消息builder.
   *
   * @return 菜单消息builder
   */
  public static WxMsgMenuBuilder MSGMENU() {
    return new WxMsgMenuBuilder();
  }

  /**
   * 小程序卡片.
   *
   * @return 小程序卡片builder
   */
  public static MiniProgramPageBuilder MINIPROGRAMPAGE() {
    return new MiniProgramPageBuilder();
  }

  /**
   * 发送图文消息（点击跳转到图文消息页面）使用通过 “发布” 系列接口得到的 article_id
   *
   * @return 图文消息builder
   */
  public static MpNewsArticleBuilder MPNEWSARTICLE() {
    return new MpNewsArticleBuilder();
  }

  /**
   * 设置消息类型
   * <p>
   * 请使用
   * {@link me.chanjar.weixin.common.api.WxConsts.KefuMsgType#TEXT}
   * {@link me.chanjar.weixin.common.api.WxConsts.KefuMsgType#IMAGE}
   * {@link me.chanjar.weixin.common.api.WxConsts.KefuMsgType#VOICE}
   * {@link me.chanjar.weixin.common.api.WxConsts.KefuMsgType#MUSIC}
   * {@link me.chanjar.weixin.common.api.WxConsts.KefuMsgType#VIDEO}
   * {@link me.chanjar.weixin.common.api.WxConsts.KefuMsgType#NEWS}
   * {@link me.chanjar.weixin.common.api.WxConsts.KefuMsgType#MPNEWS}
   * {@link me.chanjar.weixin.common.api.WxConsts.KefuMsgType#WXCARD}
   * {@link me.chanjar.weixin.common.api.WxConsts.KefuMsgType#MINIPROGRAMPAGE}
   * {@link me.chanjar.weixin.common.api.WxConsts.KefuMsgType#TASKCARD}
   * {@link me.chanjar.weixin.common.api.WxConsts.KefuMsgType#MSGMENU}
   * {@link me.chanjar.weixin.common.api.WxConsts.KefuMsgType#MP_NEWS_ARTICLE}
   * </p>
   *
   * @param msgType 消息类型
   */
  public void setMsgType(String msgType) {
    this.msgType = msgType;
  }

  public String toJson() {
    return WxMpGsonBuilder.create().toJson(this);
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class WxArticle implements Serializable {
    private static final long serialVersionUID = 5145137235440507379L;

    private String title;
    private String description;
    private String url;
    private String picUrl;
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class MsgMenu implements Serializable {
    private static final long serialVersionUID = 7020769047598378839L;

    private String id;
    private String content;
  }
}
