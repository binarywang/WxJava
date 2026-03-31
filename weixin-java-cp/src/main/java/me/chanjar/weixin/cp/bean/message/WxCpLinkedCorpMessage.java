package me.chanjar.weixin.cp.bean.message;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.cp.bean.article.MpnewsArticle;
import me.chanjar.weixin.cp.bean.article.NewArticle;
import org.apache.commons.lang3.ArrayUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static me.chanjar.weixin.cp.constant.WxCpConsts.LinkedCorpMsgType.*;

/**
 * 互联企业消息.
 * https://developer.work.weixin.qq.com/document/path/90250
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a> created on  2020-08-30
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpLinkedCorpMessage implements Serializable {
  private static final long serialVersionUID = 8833792280163704238L;

  /**
   * 1表示发送给应用可见范围内的所有人（包括互联企业的成员），默认为0
   */
  private Boolean isToAll;

  /**
   * 成员ID列表（消息接收者，最多支持1000个）。每个元素的格式为： corpid/userid，其中，corpid为该互联成员所属的企业，userid为该互联成员所属企业中的帐号。如果是本企业的成员，则直接传userid即可
   */
  private String[] toUsers;
  /**
   * 部门ID列表，最多支持100个。partyid在互联圈子内唯一。每个元素都是字符串类型，格式为：linked_id/party_id，其中linked_id是互联id，party_id是在互联圈子中的部门id
   * 。如果是本企业的部门，则直接传party_id即可。
   */
  private String[] toParties;
  /**
   * 本企业的标签ID列表，最多支持100个。
   */
  private String[] toTags;

  /**
   * 企业应用的id，整型。可在应用的设置页面查看
   */
  private Integer agentId;
  private String msgType;
  /**
   * 消息内容，最长不超过2048个字节
   */
  private String content;

  /**
   * 图片媒体文件id，可以调用上传临时素材接口获取
   */
  private String mediaId;
  private String thumbMediaId;
  private String title;
  private String description;
  /**
   * 表示是否是保密消息，0表示否，1表示是，默认0
   */
  private Boolean isSafe;
  private String url;
  private String btnTxt;
  private List<NewArticle> articles = new ArrayList<>();
  private List<MpnewsArticle> mpNewsArticles = new ArrayList<>();
  private String appId;
  private String page;
  private Boolean emphasisFirstItem;
  private Map<String, String> contentItems;

  /**
   * <pre>
   * 请使用.
   * {@link me.chanjar.weixin.cp.constant.WxCpConsts.LinkedCorpMsgType#TEXT}
   * {@link me.chanjar.weixin.cp.constant.WxCpConsts.LinkedCorpMsgType#IMAGE}
   * {@link me.chanjar.weixin.cp.constant.WxCpConsts.LinkedCorpMsgType#VIDEO}
   * {@link me.chanjar.weixin.cp.constant.WxCpConsts.LinkedCorpMsgType#NEWS}
   * {@link me.chanjar.weixin.cp.constant.WxCpConsts.LinkedCorpMsgType#MPNEWS}
   * {@link me.chanjar.weixin.cp.constant.WxCpConsts.LinkedCorpMsgType#MARKDOWN}
   * {@link me.chanjar.weixin.cp.constant.WxCpConsts.LinkedCorpMsgType#MINIPROGRAM_NOTICE}
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

    if (ArrayUtils.isNotEmpty(this.getToUsers())) {
      messageJson.add("touser", WxGsonBuilder.create().toJsonTree(this.getToUsers()));
    }

    if (ArrayUtils.isNotEmpty(this.getToParties())) {
      messageJson.add("toparty", WxGsonBuilder.create().toJsonTree(this.getToParties()));
    }

    if (ArrayUtils.isNotEmpty(this.getToTags())) {
      messageJson.add("totag", WxGsonBuilder.create().toJsonTree(this.getToTags()));
    }

    if (this.getIsToAll() != null) {
      messageJson.addProperty("toall", this.getIsToAll() ? 1 : 0);
    }
    messageJson.addProperty("msgtype", this.getMsgType());

    if (this.getAgentId() != null) {
      messageJson.addProperty("agentid", this.getAgentId());
    }

    this.handleMsgType(messageJson);

    if (this.getIsSafe() != null) {
      messageJson.addProperty("safe", this.getIsSafe() ? 1 : 0);
    }

    return messageJson.toString();
  }

  private void handleMsgType(JsonObject messageJson) {
    switch (this.getMsgType()) {
      case TEXT: {
        JsonObject text = new JsonObject();
        text.addProperty("content", this.getContent());
        messageJson.add("text", text);
        break;
      }
      case MARKDOWN: {
        JsonObject text = new JsonObject();
        text.addProperty("content", this.getContent());
        messageJson.add("markdown", text);
        break;
      }
      case TEXTCARD: {
        JsonObject text = new JsonObject();
        text.addProperty("title", this.getTitle());
        text.addProperty("description", this.getDescription());
        text.addProperty("url", this.getUrl());
        text.addProperty("btntxt", this.getBtnTxt());
        messageJson.add("textcard", text);
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
          articleJson.addProperty("btntxt", article.getBtnText());
          articleJsonArray.add(articleJson);
        }
        newsJsonObject.add("articles", articleJsonArray);
        messageJson.add("news", newsJsonObject);
        break;
      }
      case MPNEWS: {
        JsonObject newsJsonObject = new JsonObject();
        if (this.getMediaId() != null) {
          newsJsonObject.addProperty("media_id", this.getMediaId());
        } else {
          JsonArray articleJsonArray = new JsonArray();
          for (MpnewsArticle article : this.getMpNewsArticles()) {
            JsonObject articleJson = new JsonObject();
            articleJson.addProperty("title", article.getTitle());
            articleJson.addProperty("thumb_media_id", article.getThumbMediaId());
            articleJson.addProperty("author", article.getAuthor());
            articleJson.addProperty("content_source_url", article.getContentSourceUrl());
            articleJson.addProperty("content", article.getContent());
            articleJson.addProperty("digest", article.getDigest());
            if (article.getShowCoverPic() != null) {
              articleJson.addProperty("show_cover_pic", article.getShowCoverPic());
            }
            articleJsonArray.add(articleJson);
          }

          newsJsonObject.add("articles", articleJsonArray);
        }
        messageJson.add("mpnews", newsJsonObject);
        break;
      }
      case MINIPROGRAM_NOTICE: {
        JsonObject notice = new JsonObject();
        notice.addProperty("appid", this.getAppId());
        notice.addProperty("page", this.getPage());
        notice.addProperty("title", this.getTitle());
        notice.addProperty("description", this.getDescription());
        notice.addProperty("emphasis_first_item", this.getEmphasisFirstItem());
        JsonArray content = new JsonArray();
        for (Map.Entry<String, String> item : this.getContentItems().entrySet()) {
          JsonObject articleJson = new JsonObject();
          articleJson.addProperty("key", item.getKey());
          articleJson.addProperty("value", item.getValue());
          content.add(articleJson);
        }
        notice.add("content_item", content);

        messageJson.add("miniprogram_notice", notice);
        break;
      }
      default: {
        // do nothing
      }
    }
  }

}
