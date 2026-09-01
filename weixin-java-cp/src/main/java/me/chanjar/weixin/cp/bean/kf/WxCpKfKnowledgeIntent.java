package me.chanjar.weixin.cp.bean.kf;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 微信客服知识库问答。
 */
@Data
public class WxCpKfKnowledgeIntent implements Serializable {
  private static final long serialVersionUID = -3777712166335763935L;

  @SerializedName("group_id")
  private String groupId;

  @SerializedName("intent_id")
  private String intentId;

  private Question question;

  @SerializedName("similar_questions")
  private SimilarQuestions similarQuestions;

  private List<Answer> answers;

  public static WxCpKfKnowledgeIntent fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpKfKnowledgeIntent.class);
  }

  @Data
  public static class Question implements Serializable {
    private static final long serialVersionUID = -1833564733770700525L;
    private Text text;
  }

  @Data
  public static class Text implements Serializable {
    private static final long serialVersionUID = -4775152873471313775L;
    private String content;
  }

  @Data
  public static class SimilarQuestions implements Serializable {
    private static final long serialVersionUID = -3338135520171174537L;
    private List<Question> items;
  }

  @Data
  public static class Answer implements Serializable {
    private static final long serialVersionUID = 482114683168970317L;
    private Text text;
    private List<Attachment> attachments;
  }

  @Data
  public static class Attachment implements Serializable {
    private static final long serialVersionUID = 547601649734690079L;
    @SerializedName("msgtype")
    private String msgType;
    private Image image;
    private Video video;
    private Link link;
    @SerializedName("miniprogram")
    private MiniProgram miniProgram;
  }

  @Data
  public static class Image implements Serializable {
    private static final long serialVersionUID = -6305485241850490695L;
    @SerializedName("media_id")
    private String mediaId;
    private String name;
  }

  @Data
  public static class Video implements Serializable {
    private static final long serialVersionUID = 4002145709503795505L;
    @SerializedName("media_id")
    private String mediaId;
    private String name;
  }

  @Data
  public static class Link implements Serializable {
    private static final long serialVersionUID = -1844812819777892606L;
    private String title;
    @SerializedName("pic_url")
    private String picUrl;
    private String desc;
    private String url;
  }

  @Data
  public static class MiniProgram implements Serializable {
    private static final long serialVersionUID = 6893025025270416975L;
    private String title;
    @SerializedName("thumb_media_id")
    private String thumbMediaId;
    private String appid;
    private String pagepath;
  }
}
