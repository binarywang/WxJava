package me.chanjar.weixin.cp.bean.message;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import me.chanjar.weixin.cp.bean.article.NewArticle;
import me.chanjar.weixin.cp.bean.templatecard.*;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;

import static me.chanjar.weixin.cp.constant.WxCpConsts.GroupRobotMsgType.*;

/**
 * 微信群机器人消息
 *
 * @author yr  created on  2020-08-20
 */
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Data
public class WxCpGroupRobotMessage implements Serializable {
  private static final long serialVersionUID = -4301684507150486556L;

  /**
   * 消息类型
   */
  private String msgType;

  /**
   * 文本内容，最长不超过2048个字节，markdown内容，最长不超过4096个字节，必须是utf8编码
   * 必填
   */
  private String content;
  /**
   * userid的列表，提醒群中的指定成员(@某个成员)，@all表示提醒所有人，如果开发者获取不到userid，可以使用mentioned_mobile_list
   */
  private List<String> mentionedList;
  /**
   * 手机号列表，提醒手机号对应的群成员(@某个成员)，@all表示提醒所有人
   */
  private List<String> mentionedMobileList;
  /**
   * 图片内容的base64编码
   */
  private String base64;
  /**
   * 图片内容（base64编码前）的md5值
   */
  private String md5;
  /**
   * 图文消息，一个图文消息支持1到8条图文
   */
  private List<NewArticle> articles;

  /**
   * 文件id
   */
  private String mediaId;

  private Integer agentId;

  // 模板型卡片特有属性
  /**
   * 模板卡片类型，文本通知型卡片填写 “text_notice”,
   * 图文展示型卡片此处填写 “news_notice”,
   * 按钮交互型卡片填写”button_interaction”,
   * 投票选择型卡片填写”vote_interaction”,
   * 多项选择型卡片填写 “multiple_interaction”
   */
  private String cardType;

  /**
   * 卡片来源样式信息，不需要来源样式可不填写
   * 来源图片的url
   */
  private String sourceIconUrl;
  /**
   * 卡片来源样式信息，不需要来源样式可不填写
   * 来源图片的描述，建议不超过20个字
   */
  private String sourceDesc;

  /**
   * 来源文字的颜色，目前支持：0(默认) 灰色，1 黑色，2 红色，3 绿色
   */
  private Integer sourceDescColor;

  /**
   * 更多操作界面的描述
   */
  private String actionMenuDesc;

  /**
   * 操作列表，列表长度取值范围为 [1, 3]
   */
  private List<ActionMenuItem> actionMenuActionList;

  /**
   * 一级标题，建议不超过36个字
   */
  private String mainTitleTitle;
  /**
   * 标题辅助信息，建议不超过44个字
   */
  private String mainTitleDesc;

  /**
   * 图文展示型的卡片必须有图片字段。
   * 图片的url.
   */
  private String cardImageUrl;

  /**
   * 图片的宽高比，宽高比要小于2.25，大于1.3，不填该参数默认1.3
   */
  private Float cardImageAspectRatio;
  /**
   * 关键数据样式
   * 关键数据样式的数据内容，建议不超过14个字
   */
  private String emphasisContentTitle;
  /**
   * 关键数据样式的数据描述内容，建议不超过22个字
   */
  private String emphasisContentDesc;

  /**
   * 二级普通文本，建议不超过160个字
   */
  private String subTitleText;

  /**
   * 卡片二级垂直内容，该字段可为空数组，但有数据的话需确认对应字段是否必填，列表长度不超过4
   */
  private List<VerticalContent> verticalContents;

  /**
   * 二级标题+文本列表，该字段可为空数组，但有数据的话需确认对应字段是否必填，列表长度不超过6
   */
  private List<HorizontalContent> horizontalContents;

  /**
   * 跳转指引样式的列表，该字段可为空数组，但有数据的话需确认对应字段是否必填，列表长度不超过3
   */
  private List<TemplateCardJump> jumps;

  /**
   * 整体卡片的点击跳转事件，text_notice必填本字段
   * 跳转事件类型，1 代表跳转url，2 代表打开小程序。text_notice卡片模版中该字段取值范围为[1,2]
   */
  private Integer cardActionType;
  /**
   * 跳转事件的url，card_action.type是1时必填
   */
  private String cardActionUrl;

  /**
   * 跳转事件的小程序的appid，必须是与当前应用关联的小程序，card_action.type是2时必填
   */
  private String cardActionAppid;

  /**
   * 跳转事件的小程序的pagepath，card_action.type是2时选填
   */
  private String cardActionPagepath;

  /**
   * 按钮交互型卡片需指定。
   * 按钮列表，该字段可为空数组，但有数据的话需确认对应字段是否必填，列表长度不超过6
   */
  private List<TemplateCardButton> buttons;

  /**
   * 投票选择型卡片需要指定
   * 选择题key值，用户提交选项后，会产生回调事件，回调事件会带上该key值表示该题，最长支持1024字节
   */
  private String checkboxQuestionKey;

  /**
   * 选择题模式，单选：0，多选：1，不填默认0
   */
  private Integer checkboxMode;

  /**
   * 选项list，选项个数不超过 20 个，最少1个
   */
  private List<CheckboxOption> options;

  /**
   * 提交按钮样式
   * 按钮文案，建议不超过10个字，不填默认为提交
   */
  private String submitButtonText;
  /**
   * 提交按钮的key，会产生回调事件将本参数作为EventKey返回，最长支持1024字节
   */
  private String submitButtonKey;
  /**
   * 下拉式的选择器列表，multiple_interaction类型的卡片该字段不可为空，一个消息最多支持 3 个选择器
   */
  private List<MultipleSelect> selects;

  /**
   * 引用文献样式
   */
  private QuoteArea quoteArea;

  /**
   * To json string.
   *
   * @return the string
   */
  public String toJson() {
    JsonObject messageJson = new JsonObject();
    messageJson.addProperty("msgtype", this.getMsgType());
    if (this.getAgentId() != null) {
      messageJson.addProperty("agentid", this.getAgentId());
    }

    switch (this.getMsgType()) {
      case TEXT: {
        JsonObject text = new JsonObject();
        JsonArray uidJsonArray = new JsonArray();
        JsonArray mobileJsonArray = new JsonArray();

        text.addProperty("content", this.getContent());

        if (this.getMentionedList() != null) {
          for (String item : this.getMentionedList()) {
            uidJsonArray.add(item);
          }
        }
        if (this.getMentionedMobileList() != null) {
          for (String item : this.getMentionedMobileList()) {
            mobileJsonArray.add(item);
          }
        }
        text.add("mentioned_list", uidJsonArray);
        text.add("mentioned_mobile_list", mobileJsonArray);
        messageJson.add("text", text);
        break;
      }
      case MARKDOWN: {
        JsonObject text = new JsonObject();
        text.addProperty("content", this.getContent());
        messageJson.add("markdown", text);
        break;
      }
      case IMAGE: {
        JsonObject text = new JsonObject();
        text.addProperty("base64", this.getBase64());
        text.addProperty("md5", this.getMd5());
        messageJson.add("image", text);
        break;
      }
      case NEWS: {
        JsonObject text = new JsonObject();
        JsonArray array = new JsonArray();
        for (NewArticle article : this.getArticles()) {
          JsonObject articleJson = new JsonObject();
          articleJson.addProperty("title", article.getTitle());
          articleJson.addProperty("description", article.getDescription());
          articleJson.addProperty("url", article.getUrl());
          articleJson.addProperty("picurl", article.getPicUrl());
          array.add(articleJson);
        }
        text.add("articles", array);
        messageJson.add("news", text);
        break;
      }
      case FILE: {
        JsonObject file = new JsonObject();
        file.addProperty("media_id", this.getMediaId());
        messageJson.add("file", file);
        break;
      }
      case TEMPLATE_CARD: {
        JsonObject template = new JsonObject();
        template.addProperty("card_type", this.getCardType());

        if (StringUtils.isNotBlank(this.getSourceIconUrl()) || StringUtils.isNotBlank(this.getSourceDesc())) {
          JsonObject source = new JsonObject();
          if (StringUtils.isNotBlank(this.getSourceIconUrl())) {
            source.addProperty("icon_url", this.getSourceIconUrl());
          }
          if (StringUtils.isNotBlank(this.getSourceDesc())) {
            source.addProperty("desc", this.getSourceDesc());
          }
          source.addProperty("desc_color", this.getSourceDescColor());
          template.add("source", source);
        }

        if (StringUtils.isNotBlank(this.getActionMenuDesc())) {
          JsonObject action_menu = new JsonObject();
          action_menu.addProperty("desc", this.getActionMenuDesc());
          JsonArray actionList = new JsonArray();
          List<ActionMenuItem> actionMenuItemList = this.getActionMenuActionList();
          for (ActionMenuItem actionItemI : actionMenuItemList) {
            actionList.add(actionItemI.toJson());
          }
          action_menu.add("action_list", actionList);
          template.add("action_menu", action_menu);
        }

        if (StringUtils.isNotBlank(this.getMainTitleTitle()) || StringUtils.isNotBlank(this.getMainTitleDesc())) {
          JsonObject mainTitle = new JsonObject();
          if (StringUtils.isNotBlank(this.getMainTitleTitle())) {
            mainTitle.addProperty("title", this.getMainTitleTitle());
          }
          if (StringUtils.isNotBlank(this.getMainTitleDesc())) {
            mainTitle.addProperty("desc", this.getMainTitleDesc());
          }
          template.add("main_title", mainTitle);
        }

        if (StringUtils.isNotBlank(this.getCardImageUrl()) || this.getCardImageAspectRatio() != null) {
          JsonObject cardImage = new JsonObject();
          if (StringUtils.isNotBlank(this.getCardImageUrl())) {
            cardImage.addProperty("url", this.getCardImageUrl());
          }
          if (null != this.getCardImageAspectRatio()) {
            cardImage.addProperty("aspect_ratio", this.getCardImageAspectRatio());
          }
          template.add("card_image", cardImage);
        }

        if (StringUtils.isNotBlank(this.getEmphasisContentTitle()) || StringUtils.isNotBlank(this.getEmphasisContentDesc())) {
          JsonObject emphasisContent = new JsonObject();
          if (StringUtils.isNotBlank(this.getEmphasisContentTitle())) {
            emphasisContent.addProperty("title", this.getEmphasisContentTitle());
          }
          if (StringUtils.isNotBlank(this.getEmphasisContentDesc())) {
            emphasisContent.addProperty("desc", this.getEmphasisContentDesc());
          }
          template.add("emphasis_content", emphasisContent);
        }


        if (StringUtils.isNotBlank(this.getSubTitleText())) {
          template.addProperty("sub_title_text", this.getSubTitleText());
        }

        List<VerticalContent> verticalContents = this.getVerticalContents();
        if (null != verticalContents && !verticalContents.isEmpty()) {
          JsonArray vContentJsonArray = new JsonArray();
          for (VerticalContent vContent : this.getVerticalContents()) {
            JsonObject tempObject = vContent.toJson();
            vContentJsonArray.add(tempObject);
          }
          template.add("vertical_content_list", vContentJsonArray);
        }

        List<HorizontalContent> horizontalContents = this.getHorizontalContents();
        if (null != horizontalContents && !horizontalContents.isEmpty()) {
          JsonArray hContentJsonArray = new JsonArray();
          for (HorizontalContent hContent : this.getHorizontalContents()) {
            JsonObject tempObject = hContent.toJson();
            hContentJsonArray.add(tempObject);
          }
          template.add("horizontal_content_list", hContentJsonArray);
        }

        List<TemplateCardJump> jumps = this.getJumps();
        if (null != jumps && !jumps.isEmpty()) {
          JsonArray jumpJsonArray = new JsonArray();
          for (TemplateCardJump jump : this.getJumps()) {
            JsonObject tempObject = jump.toJson();
            jumpJsonArray.add(tempObject);
          }
          template.add("jump_list", jumpJsonArray);
        }

        if (null != this.getCardActionType()) {
          JsonObject cardAction = new JsonObject();
          cardAction.addProperty("type", this.getCardActionType());
          if (StringUtils.isNotBlank(this.getCardActionUrl())) {
            cardAction.addProperty("url", this.getCardActionUrl());
          }
          if (StringUtils.isNotBlank(this.getCardActionAppid())) {
            cardAction.addProperty("appid", this.getCardActionAppid());
          }
          if (StringUtils.isNotBlank(this.getCardActionPagepath())) {
            cardAction.addProperty("pagepath", this.getCardActionPagepath());
          }
          template.add("card_action", cardAction);
        }

        List<TemplateCardButton> buttons = this.getButtons();
        if (null != buttons && !buttons.isEmpty()) {
          JsonArray btnJsonArray = new JsonArray();
          for (TemplateCardButton btn : this.getButtons()) {
            JsonObject tempObject = btn.toJson();
            btnJsonArray.add(tempObject);
          }
          template.add("button_list", btnJsonArray);
        }

        // checkbox
        if (StringUtils.isNotBlank(this.getCheckboxQuestionKey())) {
          JsonObject checkBox = new JsonObject();
          checkBox.addProperty("question_key", this.getCheckboxQuestionKey());
          if (null != this.getCheckboxMode()) {
            checkBox.addProperty("mode", this.getCheckboxMode());
          }
          JsonArray optionArray = new JsonArray();
          for (CheckboxOption option : this.getOptions()) {
            JsonObject tempObject = option.toJson();
            optionArray.add(tempObject);
          }
          checkBox.add("option_list", optionArray);

          template.add("checkbox", checkBox);
        }

        // submit_button
        if (StringUtils.isNotBlank(this.getSubmitButtonText()) || StringUtils.isNotBlank(this.getSubmitButtonKey())) {
          JsonObject submit_button = new JsonObject();
          if (StringUtils.isNotBlank(this.getSubmitButtonText())) {
            submit_button.addProperty("text", this.getSubmitButtonText());
          }
          if (StringUtils.isNotBlank(this.getSubmitButtonKey())) {
            submit_button.addProperty("key", this.getSubmitButtonKey());
          }
          template.add("submit_button", submit_button);
        }

        // select_list
        List<MultipleSelect> selects = this.getSelects();
        if (null != selects && !selects.isEmpty()) {
          JsonArray selectJsonArray = new JsonArray();
          for (MultipleSelect select : this.getSelects()) {
            JsonObject tempObject = select.toJson();
            selectJsonArray.add(tempObject);
          }
          template.add("select_list", selectJsonArray);
        }

        QuoteArea quoteArea = this.getQuoteArea();
        if (null != quoteArea) {
          JsonObject quoteAreaJson = quoteArea.toJson();
          template.add("quote_area", quoteAreaJson);
        }

        messageJson.add("template_card", template);
        break;
      }
      default:

    }

    return messageJson.toString();
  }
}
