package me.chanjar.weixin.cp.bean.intelligentrobot;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 企业微信智能机器人回调消息.
 *
 * <p>官方文档: https://developer.work.weixin.qq.com/document/path/100719</p>
 */
@Data
public class WxCpIntelligentRobotMessage implements Serializable {
  private static final long serialVersionUID = -1L;

  /**
   * 本次回调的唯一性标志.
   */
  @SerializedName("msgid")
  private String msgId;

  /**
   * 智能机器人id.
   */
  @SerializedName("aibotid")
  private String aiBotId;

  /**
   * 会话id，仅群聊类型时返回.
   */
  @SerializedName("chatid")
  private String chatId;

  /**
   * 会话类型，single/group.
   */
  @SerializedName("chattype")
  private String chatType;

  /**
   * 消息发送者.
   */
  @SerializedName("from")
  private From from;

  /**
   * 支持主动回复消息的临时url.
   */
  @SerializedName("response_url")
  private String responseUrl;

  /**
   * 消息类型.
   */
  @SerializedName("msgtype")
  private String msgType;

  @SerializedName("text")
  private Text text;

  @SerializedName("image")
  private Image image;

  @SerializedName("mixed")
  private Mixed mixed;

  @SerializedName("voice")
  private Voice voice;

  @SerializedName("file")
  private FileInfo file;

  @SerializedName("video")
  private Video video;

  @SerializedName("quote")
  private Quote quote;

  @SerializedName("stream")
  private Stream stream;

  public static WxCpIntelligentRobotMessage fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpIntelligentRobotMessage.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

  @Data
  public static class From implements Serializable {
    private static final long serialVersionUID = -1L;

    @SerializedName("userid")
    private String userid;
  }

  @Data
  public static class Text implements Serializable {
    private static final long serialVersionUID = -1L;

    @SerializedName("content")
    private String content;
  }

  @Data
  public static class Image implements Serializable {
    private static final long serialVersionUID = -1L;

    @SerializedName("url")
    private String url;
  }

  @Data
  public static class Voice implements Serializable {
    private static final long serialVersionUID = -1L;

    @SerializedName("content")
    private String content;
  }

  @Data
  public static class FileInfo implements Serializable {
    private static final long serialVersionUID = -1L;

    @SerializedName("url")
    private String url;
  }

  @Data
  public static class Video implements Serializable {
    private static final long serialVersionUID = -1L;

    @SerializedName("url")
    private String url;
  }

  @Data
  public static class Stream implements Serializable {
    private static final long serialVersionUID = -1L;

    @SerializedName("id")
    private String id;
  }

  @Data
  public static class Mixed implements Serializable {
    private static final long serialVersionUID = -1L;

    @SerializedName("msg_item")
    private List<MixedItem> msgItem;
  }

  @Data
  public static class MixedItem implements Serializable {
    private static final long serialVersionUID = -1L;

    @SerializedName("msgtype")
    private String msgType;

    @SerializedName("text")
    private Text text;

    @SerializedName("image")
    private Image image;
  }

  @Data
  public static class Quote implements Serializable {
    private static final long serialVersionUID = -1L;

    @SerializedName("msgtype")
    private String msgType;

    @SerializedName("text")
    private Text text;

    @SerializedName("image")
    private Image image;

    @SerializedName("mixed")
    private Mixed mixed;

    @SerializedName("voice")
    private Voice voice;

    @SerializedName("file")
    private FileInfo file;

    @SerializedName("video")
    private Video video;
  }
}
