package me.chanjar.weixin.cp.bean.msgaudit;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

/**
 * 聊天记录数据内容.
 *
 * @author Wang_Wong
 */
@Data
public class WxCpChatModel implements Serializable {
  private static final long serialVersionUID = -5028321625140879571L;

  @SerializedName("msgid")
  private String msgId;

  @SerializedName("action")
  private String action;

  @SerializedName("from")
  private String from;

  @SerializedName("tolist")
  private String[] tolist;

  @SerializedName("roomid")
  private String roomId;

  @SerializedName("msgtime")
  private Long msgTime;

  @SerializedName("msgtype")
  private String msgType;

  /**
   * 文本
   */
  @SerializedName("text")
  private Text text;

  /**
   * 图片
   */
  @SerializedName("image")
  private Image image;

  /**
   * 撤回消息
   */
  @SerializedName("revoke")
  private Revoke revoke;

  /**
   * 同意会话聊天内容
   */
  @SerializedName(value = "agree")
  private Agree agree;

  @SerializedName(value = "disagree")
  private Agree disagree;

  /**
   * 语音
   */
  @SerializedName(value = "voice")
  private Voice voice;

  /**
   * 视频
   */
  @SerializedName(value = "video")
  private Video video;

  /**
   * 名片
   */
  @SerializedName(value = "card")
  private Card card;

  /**
   * 位置
   */
  @SerializedName(value = "location")
  private Location location;

  /**
   * 表情
   */
  @SerializedName(value = "emotion")
  private Emotion emotion;

  /**
   * 文件
   */
  @SerializedName(value = "file")
  private File file;

  /**
   * 链接
   */
  @SerializedName(value = "link")
  private Link link;

  /**
   * 小程序消息
   */
  @SerializedName(value = "weapp")
  private Weapp weapp;

  /**
   * 会话记录消息
   */
  @SerializedName(value = "chatrecord")
  private ChatRecord chatRecord;

  /**
   * 待办消息 官网暂无
   */

  /**
   * 投票消息 官网暂无
   */

  /**
   * 填表消息
   */
  @SerializedName(value = "collect")
  private Collect collect;

  /**
   * 红包消息
   * 互通红包消息
   */
  @SerializedName("redpacket")
  private Redpacket redPacket;

  /**
   * 会议邀请消息
   */
  @SerializedName("meeting")
  private Meeting meeting;

  /**
   * 切换企业日志
   */
  @SerializedName("time")
  private Long time;

  @SerializedName("user")
  private String user;

  /**
   * 在线文档消息
   */
  @SerializedName("doc")
  private Doc doc;

  @SerializedName("info")
  private Info info;

  /**
   * 日程消息
   */
  @SerializedName("calendar")
  private Calendar calendar;

  /**
   * 混合消息
   */
  @SerializedName("mixed")
  private Mixed mixed;

  /**
   * 音频存档消息
   */
  @SerializedName("voiceid")
  private String voiceId;

  @SerializedName("meeting_voice_call")
  private MeetingVoiceCall meetingVoiceCall;

  /**
   * 音频共享文档消息
   */
  @SerializedName("voipid")
  private String voipId;

  @SerializedName("voip_doc_share")
  private WxCpFileItem voipDocShare;

  /**
   * 视频号消息
   */
  @SerializedName("sphfeed")
  private SphFeed sphFeed;

  /**
   * From json wx cp chat model.
   *
   * @param json the json
   * @return the wx cp chat model
   */
  public static WxCpChatModel fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpChatModel.class);
  }

  /**
   * To json string.
   *
   * @return the string
   */
  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }


  /**
   * The type Text.
   */
  @Getter
  @Setter
  public static class Text implements Serializable {
    private static final long serialVersionUID = -5028321625140879571L;

    @SerializedName("content")
    private String content;

    /**
     * From json text.
     *
     * @param json the json
     * @return the text
     */
    public static Text fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, Text.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }


  /**
   * The type Image.
   */
  @Getter
  @Setter
  public static class Image implements Serializable {
    private static final long serialVersionUID = -5028321625140879571L;

    @SerializedName("md5sum")
    private String md5Sum;

    @SerializedName("sdkfileid")
    private String sdkFileId;

    @SerializedName("filesize")
    private Long fileSize;

    /**
     * From json image.
     *
     * @param json the json
     * @return the image
     */
    public static Image fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, Image.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }


  /**
   * The type Revoke.
   */
  @Getter
  @Setter
  public static class Revoke implements Serializable {
    private static final long serialVersionUID = -5028321625140879571L;

    @SerializedName("pre_msgid")
    private String preMsgId;

    /**
     * From json revoke.
     *
     * @param json the json
     * @return the revoke
     */
    public static Revoke fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, Revoke.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }


  /**
   * The type Agree.
   */
  @Getter
  @Setter
  public static class Agree implements Serializable {
    private static final long serialVersionUID = -5028321625140879571L;

    @SerializedName("userid")
    private String userId;

    @SerializedName(value = "agree_time")
    private Long agreeTime;

    @SerializedName(value = "disagree_time")
    private Long disagreeTime;

    /**
     * From json agree.
     *
     * @param json the json
     * @return the agree
     */
    public static Agree fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, Agree.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }


  /**
   * The type Voice.
   */
  @Getter
  @Setter
  public static class Voice implements Serializable {
    private static final long serialVersionUID = -5028321625140879571L;

    @SerializedName("md5sum")
    private String md5Sum;

    @SerializedName("sdkfileid")
    private String sdkFileId;

    @SerializedName("voice_size")
    private Long voiceSize;

    @SerializedName("play_length")
    private Long playLength;

    /**
     * From json voice.
     *
     * @param json the json
     * @return the voice
     */
    public static Voice fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, Voice.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }


  /**
   * The type Video.
   */
  @Getter
  @Setter
  public static class Video implements Serializable {
    private static final long serialVersionUID = -5028321625140879571L;

    @SerializedName("md5sum")
    private String md5Sum;

    @SerializedName("sdkfileid")
    private String sdkFileId;

    @SerializedName("filesize")
    private Long fileSize;

    @SerializedName("play_length")
    private Long playLength;

    /**
     * From json video.
     *
     * @param json the json
     * @return the video
     */
    public static Video fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, Video.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }


  /**
   * The type Card.
   */
  @Getter
  @Setter
  public static class Card implements Serializable {
    private static final long serialVersionUID = -5028321625140879571L;

    @SerializedName("corpname")
    private String corpName;

    @SerializedName("userid")
    private String userId;

    /**
     * From json card.
     *
     * @param json the json
     * @return the card
     */
    public static Card fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, Card.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }


  /**
   * The type Location.
   */
  @Getter
  @Setter
  public static class Location implements Serializable {
    private static final long serialVersionUID = -5028321625140879571L;

    @SerializedName("longitude")
    private Double longitude;

    @SerializedName("latitude")
    private Double latitude;

    @SerializedName("address")
    private String address;

    @SerializedName("title")
    private String title;

    @SerializedName("zoom")
    private Integer zoom;

    /**
     * From json location.
     *
     * @param json the json
     * @return the location
     */
    public static Location fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, Location.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }


  /**
   * The type Emotion.
   */
  @Getter
  @Setter
  public static class Emotion implements Serializable {
    private static final long serialVersionUID = -5028321625140879571L;

    @SerializedName("type")
    private Integer type;

    @SerializedName("width")
    private Integer width;

    @SerializedName("height")
    private Integer height;

    @SerializedName("title")
    private String title;

    @SerializedName("imagesize")
    private Integer imageSize;

    @SerializedName("md5sum")
    private String md5Sum;

    @SerializedName("sdkfileid")
    private String sdkFileId;

    /**
     * From json emotion.
     *
     * @param json the json
     * @return the emotion
     */
    public static Emotion fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, Emotion.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }


  /**
   * The type File.
   */
  @Getter
  @Setter
  public static class File implements Serializable {
    private static final long serialVersionUID = -5028321625140879571L;

    @SerializedName("md5sum")
    private String md5Sum;

    @SerializedName("filename")
    private String fileName;

    @SerializedName("fileext")
    private String fileExt;

    @SerializedName("sdkfileid")
    private String sdkFileId;

    @SerializedName("filesize")
    private Integer fileSize;

    /**
     * From json file.
     *
     * @param json the json
     * @return the file
     */
    public static File fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, File.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }


  /**
   * The type Link.
   */
  @Getter
  @Setter
  public static class Link implements Serializable {
    private static final long serialVersionUID = -5028321625140879571L;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("link_url")
    private String linkUrl;

    @SerializedName("image_url")
    private String imageUrl;

    /**
     * From json link.
     *
     * @param json the json
     * @return the link
     */
    public static Link fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, Link.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }


  /**
   * 小程序消息
   */
  @Getter
  @Setter
  public static class Weapp implements Serializable {
    private static final long serialVersionUID = -5028321625140879571L;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("username")
    private String userName;

    @SerializedName("displayname")
    private String displayName;

    /**
     * From json weapp.
     *
     * @param json the json
     * @return the weapp
     */
    public static Weapp fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, Weapp.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }


  /**
   * 会话记录消息
   */
  @Getter
  @Setter
  public static class ChatRecord implements Serializable {
    private static final long serialVersionUID = -5028321625140879571L;

    @SerializedName(value = "item")
    private List<ChatRecordItem> item;

    @SerializedName("title")
    private String title;

    /**
     * From json chat record.
     *
     * @param json the json
     * @return the chat record
     */
    public static ChatRecord fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, ChatRecord.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }


  /**
   * The type Chat record item.
   */
  @Getter
  @Setter
  public static class ChatRecordItem implements Serializable {
    private static final long serialVersionUID = -5028321625140879571L;

    @SerializedName("type")
    private String type;

    @SerializedName("msgtime")
    private Long msgTime;

    @SerializedName("content")
    private String content;

    @SerializedName("from_chatroom")
    private Boolean fromChatRoom;

    /**
     * From json chat record item.
     *
     * @param json the json
     * @return the chat record item
     */
    public static ChatRecordItem fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, ChatRecordItem.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }


  /**
   * 填表消息
   */
  @Getter
  @Setter
  public static class Collect implements Serializable {
    private static final long serialVersionUID = -5028321625140879571L;

    @SerializedName("room_name")
    private String roomName;

    @SerializedName("creator")
    private String creator;

    @SerializedName("create_time")
    private String createTime;

    @SerializedName("title")
    private String title;

    @SerializedName("details")
    private List<Details> details;

    /**
     * From json collect.
     *
     * @param json the json
     * @return the collect
     */
    public static Collect fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, Collect.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }


  /**
   * The type Details.
   */
  @Getter
  @Setter
  public static class Details implements Serializable {
    private static final long serialVersionUID = -5028321625140879571L;

    /**
     * 表项id Uint64类型
     */
    @SerializedName("id")
    private BigInteger id;

    @SerializedName("ques")
    private String ques;

    @SerializedName("type")
    private String type;

    /**
     * From json details.
     *
     * @param json the json
     * @return the details
     */
    public static Details fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, Details.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }


  /**
   * 红包消息
   */
  @Getter
  @Setter
  public static class Redpacket implements Serializable {
    private static final long serialVersionUID = -5028321625140879571L;

    @SerializedName("type")
    private Integer type;

    @SerializedName("totalcnt")
    private Integer totalCnt;

    @SerializedName("totalamount")
    private Integer totalAmount;

    @SerializedName("wish")
    private String wish;

    /**
     * From json redpacket.
     *
     * @param json the json
     * @return the redpacket
     */
    public static Redpacket fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, Redpacket.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }


  /**
   * 会议邀请消息
   */
  @Getter
  @Setter
  public static class Meeting implements Serializable {
    private static final long serialVersionUID = -5028321625140879571L;

    @SerializedName("topic")
    private String topic;

    @SerializedName("starttime")
    private Long startTime;

    @SerializedName("endtime")
    private Long endTime;

    @SerializedName("address")
    private String address;

    @SerializedName("remarks")
    private String remarks;

    @SerializedName("meetingtype")
    private Integer meetingType;

    /**
     * 会议id Uint64类型
     */
    @SerializedName("meetingid")
    private BigInteger meetingId;

    @SerializedName("status")
    private Integer status;

    /**
     * From json meeting.
     *
     * @param json the json
     * @return the meeting
     */
    public static Meeting fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, Meeting.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }


  /**
   * 在线文档消息
   */
  @Getter
  @Setter
  public static class Doc implements Serializable {
    private static final long serialVersionUID = -5028321625140879571L;

    @SerializedName("title")
    private String title;

    @SerializedName("doc_creator")
    private String docCreator;

    @SerializedName("link_url")
    private String linkUrl;

    /**
     * From json doc.
     *
     * @param json the json
     * @return the doc
     */
    public static Doc fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, Doc.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }


  /**
   * MarkDown格式消息
   */
  @Getter
  @Setter
  public static class Info implements Serializable {
    private static final long serialVersionUID = -5028321625140879571L;

    @SerializedName("content")
    private String content;

    @SerializedName("item")
    private List<NewsItem> newsItem;

    /**
     * From json info.
     *
     * @param json the json
     * @return the info
     */
    public static Info fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, Info.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }


  /**
   * 图文消息
   */
  @Getter
  @Setter
  public static class NewsItem implements Serializable {
    private static final long serialVersionUID = -5028321625140879571L;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("url")
    private String url;

    @SerializedName("picurl")
    private String picUrl;

    /**
     * From json news item.
     *
     * @param json the json
     * @return the news item
     */
    public static NewsItem fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, NewsItem.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }


  /**
   * 日程消息
   */
  @Getter
  @Setter
  public static class Calendar implements Serializable {
    private static final long serialVersionUID = -5028321625140879571L;

    @SerializedName("title")
    private String title;

    @SerializedName("creatorname")
    private String creatorName;

    @SerializedName("attendeename")
    private String[] attendeeName;

    @SerializedName("starttime")
    private Long startTime;

    @SerializedName("endtime")
    private Long endTime;

    @SerializedName("place")
    private String place;

    @SerializedName("remarks")
    private String remarks;

    /**
     * From json calendar.
     *
     * @param json the json
     * @return the calendar
     */
    public static Calendar fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, Calendar.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }


  /**
   * 混合消息
   */
  @Getter
  @Setter
  public static class Mixed implements Serializable {
    private static final long serialVersionUID = -5028321625140879571L;

    @SerializedName("item")
    private List<Item> item;

    /**
     * The type Item.
     */
    @Getter
    @Setter
    public static class Item implements Serializable {
      private static final long serialVersionUID = -5028321625140879571L;

      @SerializedName("type")
      private String type;

      @SerializedName("content")
      private String content;

    }

  }


  /**
   * 音频存档消息
   */
  @Getter
  @Setter
  public static class MeetingVoiceCall implements Serializable {
    private static final long serialVersionUID = -5028321625140879571L;

    @SerializedName("endtime")
    private Long endTime;

    @SerializedName("sdkfileid")
    private String sdkFileId;

    @SerializedName("demofiledata")
    private List<DemoFileData> demoFileData;

    @SerializedName("sharescreendata")
    private List<ShareScreenData> shareScreenData;

    /**
     * From json meeting voice call.
     *
     * @param json the json
     * @return the meeting voice call
     */
    public static MeetingVoiceCall fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, MeetingVoiceCall.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

    /**
     * The type Demo file data.
     */
    @Getter
    @Setter
    public static class DemoFileData implements Serializable {
      private static final long serialVersionUID = -5028321625140879571L;

      @SerializedName("filename")
      private String fileName;

      @SerializedName("demooperator")
      private String demoOperator;

      @SerializedName("starttime")
      private Long startTime;

      @SerializedName("endtime")
      private Long endTime;

      /**
       * From json demo file data.
       *
       * @param json the json
       * @return the demo file data
       */
      public static DemoFileData fromJson(String json) {
        return WxCpGsonBuilder.create().fromJson(json, DemoFileData.class);
      }

      /**
       * To json string.
       *
       * @return the string
       */
      public String toJson() {
        return WxCpGsonBuilder.create().toJson(this);
      }

    }

    /**
     * The type Share screen data.
     */
    @Getter
    @Setter
    public static class ShareScreenData implements Serializable {
      private static final long serialVersionUID = -5028321625140879571L;

      @SerializedName("share")
      private String share;

      @SerializedName("starttime")
      private Long startTime;

      @SerializedName("endtime")
      private Long endTime;

      /**
       * From json share screen data.
       *
       * @param json the json
       * @return the share screen data
       */
      public static ShareScreenData fromJson(String json) {
        return WxCpGsonBuilder.create().fromJson(json, ShareScreenData.class);
      }

      /**
       * To json string.
       *
       * @return the string
       */
      public String toJson() {
        return WxCpGsonBuilder.create().toJson(this);
      }

    }

  }


  /**
   * 视频号消息
   */
  @Getter
  @Setter
  public static class SphFeed implements Serializable {
    private static final long serialVersionUID = -5028321625140879571L;

    @SerializedName("feed_type")
    private Integer feedType;

    @SerializedName("sph_name")
    private String sphName;

    @SerializedName("feed_desc")
    private String feedDesc;

    /**
     * From json sph feed.
     *
     * @param json the json
     * @return the sph feed
     */
    public static SphFeed fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, SphFeed.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }


}
