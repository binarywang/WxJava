package me.chanjar.weixin.cp.bean.message;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import me.chanjar.weixin.common.api.WxConsts.SchoolContactMsgType;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.cp.bean.article.MpnewsArticle;
import me.chanjar.weixin.cp.bean.article.NewArticle;
import org.apache.commons.lang3.ArrayUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static me.chanjar.weixin.common.api.WxConsts.SchoolContactMsgType.*;

/**
 * 发送「学校通知」
 * https://developer.work.weixin.qq.com/document/path/92321
 *
 * @author <a href="https://github.com/0katekate0">Wang_Wong</a> created on  2022-06-29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpSchoolContactMessage implements Serializable {
  private static final long serialVersionUID = 8833792280163704238L;

  /**
   * 指定发送对象，0表示发送给家长，1表示发送给学生，2表示发送给家长和学生，默认为0。
   */
  @SerializedName("recv_scope")
  private Integer recvScope = 0;

  /**
   * 家校通讯录家长列表，recv_scope为0或2表示发送给对应的家长，recv_scope为1忽略，（最多支持1000个）
   */
  @SerializedName("to_parent_userid")
  private String[] toParentUserId;

  /**
   * 家校通讯录学生列表，recv_scope为0表示发送给学生的所有家长，recv_scope为1表示发送给学生，recv_scope为2表示发送给学生和学生的所有家长（最多支持1000个）
   */
  @SerializedName("to_student_userid")
  private String[] toStudentUserId;

  /**
   * 家校通讯录部门列表，recv_scope为0表示发送给班级的所有家长，recv_scope为1表示发送给班级的所有学生，recv_scope为2表示发送给班级的所有学生和家长（最多支持100个）
   */
  @SerializedName("to_party")
  private String[] toParty;

  /**
   * 1表示字段生效，0表示字段无效。recv_scope为0表示发送给学校的所有家长，recv_scope为1表示发送给学校的所有学生，recv_scope为2表示发送给学校的所有学生和家长，默认为0
   */
  @SerializedName("toall")
  private Boolean toAll = false;

  /**
   * 消息类型
   */
  @SerializedName("msgtype")
  private String msgType;

  /**
   * 企业应用的id，整型。可在应用的设置页面查看
   */
  @SerializedName("agentid")
  private Long agentId;

  public void setAgentId(long agentId) {
    this.agentId = Long.valueOf(agentId);
  }

  /**
   * 消息内容，最长不超过2048个字节（支持id转译）
   */
  @SerializedName("content")
  private String content;

  /**
   * enable_id_trans
   * 表示是否开启id转译，0表示否，1表示是，默认0
   */
  @SerializedName("enable_id_trans")
  private Boolean enableIdTrans = false;

  /**
   * enable_duplicate_check
   * 表示是否开启重复消息检查，0表示否，1表示是，默认0
   */
  @SerializedName("enable_duplicate_check")
  private Boolean enableDuplicateCheck = false;

  /**
   * duplicate_check_interval
   * 表示是否重复消息检查的时间间隔，默认1800s，最大不超过4小时
   */
  @SerializedName("duplicate_check_interval")
  private Integer duplicateCheckInterval;

  /**
   * 图片媒体文件id，可以调用上传临时素材接口获取
   */
  @SerializedName("media_id")
  private String mediaId;

  /**
   * 视频消息的标题，不超过128个字节，超过会自动截断
   */
  @SerializedName("title")
  private String title;

  /**
   * 视频消息的描述，不超过512个字节，超过会自动截断
   */
  @SerializedName("description")
  private String description;

  /**
   * 小程序消息封面的mediaid，封面图建议尺寸为520*416
   */
  @SerializedName("thumb_media_id")
  private String thumbMediaId;

  /**
   * 小程序appid，必须是关联到企业的小程序应用
   */
  @SerializedName("appid")
  private String appId;

  /**
   * 点击消息卡片后进入的小程序页面路径
   */
  @SerializedName("pagepath")
  private String pagePath;

  /**
   * 图文消息
   * https://developer.work.weixin.qq.com/document/path/92321#%E5%9B%BE%E6%96%87%E6%B6%88%E6%81%AF
   */
  private List<NewArticle> articles = new ArrayList<>();

  /**
   * 图文消息（mpnews）
   * https://developer.work.weixin.qq.com/document/path/92321#%E5%9B%BE%E6%96%87%E6%B6%88%E6%81%AF%EF%BC%88mpnews%EF
   * %BC%89
   * <p>
   * mpnews类型的图文消息，跟普通的图文消息一致，唯一的差异是图文内容存储在企业微信。
   * 多次发送mpnews，会被认为是不同的图文，阅读、点赞的统计会被分开计算。
   */
  private List<MpnewsArticle> mpNewsArticles = new ArrayList<>();

  /**
   * <pre>
   * 请使用.
   * {@link SchoolContactMsgType#TEXT}
   * {@link SchoolContactMsgType#IMAGE}
   * {@link SchoolContactMsgType#VOICE}
   * {@link SchoolContactMsgType#VIDEO}
   * {@link SchoolContactMsgType#NEWS}
   * {@link SchoolContactMsgType#MPNEWS}
   * {@link SchoolContactMsgType#MINIPROGRAM}
   * </pre>
   *
   * @param msgType 消息类型
   */
  public void setMsgType(String msgType) {
    this.msgType = msgType;
  }

  /**
   * To json string.
   *
   * @return the string
   */
  public String toJson() {
    JsonObject messageJson = new JsonObject();

    if (this.getRecvScope() != null) {
      messageJson.addProperty("recv_scope", this.getRecvScope());
    }

    if (ArrayUtils.isNotEmpty(this.getToParentUserId())) {
      messageJson.add("to_parent_userid", WxGsonBuilder.create().toJsonTree(this.getToParentUserId()));
    }

    if (ArrayUtils.isNotEmpty(this.getToStudentUserId())) {
      messageJson.add("to_student_userid", WxGsonBuilder.create().toJsonTree(this.getToStudentUserId()));
    }

    if (ArrayUtils.isNotEmpty(this.getToParty())) {
      messageJson.add("to_party", WxGsonBuilder.create().toJsonTree(this.getToParty()));
    }

    if (this.getToAll() != null) {
      messageJson.addProperty("toall", this.getToAll() ? 1 : 0);
    }

    messageJson.addProperty("msgtype", this.getMsgType());

    if (this.getAgentId() != null) {
      messageJson.addProperty("agentid", this.getAgentId());
    }

    if (this.getEnableIdTrans() != null && this.getEnableIdTrans()) {
      messageJson.addProperty("enable_id_trans", 1);
    }

    if (this.getEnableDuplicateCheck() != null && this.getEnableDuplicateCheck()) {
      messageJson.addProperty("enable_duplicate_check", 1);
    }

    if (this.getDuplicateCheckInterval() != null) {
      messageJson.addProperty("duplicate_check_interval", this.getDuplicateCheckInterval());
    }

    this.handleMsgType(messageJson);

    return messageJson.toString();
  }

  /**
   * 封装消息类型
   *
   * @param messageJson
   */
  private void handleMsgType(JsonObject messageJson) {
    switch (this.getMsgType()) {
      case TEXT: {
        JsonObject text = new JsonObject();
        text.addProperty("content", this.getContent());
        messageJson.add("text", text);
        break;
      }
      case IMAGE: {
        JsonObject image = new JsonObject();
        image.addProperty("media_id", this.getMediaId());
        messageJson.add("image", image);
        break;
      }
      case FILE: {
        JsonObject image = new JsonObject();
        image.addProperty("media_id", this.getMediaId());
        messageJson.add("file", image);
        break;
      }
      case VOICE: {
        JsonObject voice = new JsonObject();
        voice.addProperty("media_id", this.getMediaId());
        messageJson.add("voice", voice);
        break;
      }
      case VIDEO: {
        JsonObject video = new JsonObject();
        video.addProperty("media_id", this.getMediaId());
        video.addProperty("title", this.getTitle());
        video.addProperty("description", this.getDescription());
        messageJson.add("video", video);
        break;
      }
      case NEWS: {
        JsonObject newsJsonObject = new JsonObject();
        JsonArray articleJsonArray = new JsonArray();
        for (NewArticle article : this.getArticles()) {
          JsonObject articleJson = new JsonObject();
          articleJson.addProperty("title", article.getTitle());
          articleJson.addProperty("description", article.getDescription());
          articleJson.addProperty("url", article.getUrl());
          articleJson.addProperty("picurl", article.getPicUrl());
          articleJsonArray.add(articleJson);
        }
        newsJsonObject.add("articles", articleJsonArray);
        messageJson.add("news", newsJsonObject);
        break;
      }
      case MPNEWS: {
        JsonObject newsJsonObject = new JsonObject();
        JsonArray articleJsonArray = new JsonArray();
        for (MpnewsArticle article : this.getMpNewsArticles()) {
          JsonObject articleJson = new JsonObject();
          articleJson.addProperty("title", article.getTitle());
          articleJson.addProperty("thumb_media_id", article.getThumbMediaId());
          articleJson.addProperty("author", article.getAuthor());
          articleJson.addProperty("content_source_url", article.getContentSourceUrl());
          articleJson.addProperty("content", article.getContent());
          articleJson.addProperty("digest", article.getDigest());
          articleJsonArray.add(articleJson);
        }
        newsJsonObject.add("articles", articleJsonArray);
        messageJson.add("mpnews", newsJsonObject);
        break;
      }
      case MINIPROGRAM: {
        JsonObject miniprogram = new JsonObject();
        miniprogram.addProperty("appid", this.getAppId());
        miniprogram.addProperty("pagepath", this.getPagePath());
        miniprogram.addProperty("title", this.getTitle());
        miniprogram.addProperty("thumb_media_id", this.getThumbMediaId());

        messageJson.add("miniprogram", miniprogram);
        break;
      }
      default: {
        // do nothing
      }

    }

  }

}
