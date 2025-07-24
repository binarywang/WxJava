package me.chanjar.weixin.cp.bean.messagebuilder;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.cp.bean.message.WxCpMessage;
import me.chanjar.weixin.cp.bean.templatecard.*;

import java.util.List;

/**
 * <pre>
 * 模板卡片消息Builder
 * 用法: WxCustomMessage m = WxCustomMessage.TEMPLATECARD().title(...)....toUser(...).build();
 * </pre>
 *
 * @author yzts</ a>  created on  2019-05-16
 */
public class TemplateCardBuilder extends BaseBuilder<TemplateCardBuilder> {
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
   * 左图右文样式，news_notice类型的卡片，card_image 和 image_text_area 两者必填一个字段，不可都不填
   */
  private TemplateCardImageTextArea imageTextArea;

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
   * 任务id，同一个应用任务id不能重复，只能由数字、字母和“_-@”组成，最长128字节
   */
  private String taskId;

  /**
   * 按钮交互型卡片需指定。
   * button_selection
   */
  private TemplateCardButtonSelection buttonSelection;

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
   * Instantiates a new Template card builder.
   */
  public TemplateCardBuilder() {
    this.msgType = WxConsts.KefuMsgType.TEMPLATE_CARD;
  }

  /**
   * Card type template card builder.
   *
   * @param cardType the card type
   * @return the template card builder
   */
  public TemplateCardBuilder cardType(String cardType) {
    this.cardType = cardType;
    return this;
  }

  /**
   * Card image url template card builder.
   *
   * @param cardImageUrl the card image url
   * @return the template card builder
   */
  public TemplateCardBuilder cardImageUrl(String cardImageUrl) {
    this.cardImageUrl = cardImageUrl;
    return this;
  }

  /**
   * Card image aspect ratio template card builder.
   *
   * @param cardImageAspectRatio the card image aspect ratio
   * @return the template card builder
   */
  public TemplateCardBuilder cardImageAspectRatio(Float cardImageAspectRatio) {
    this.cardImageAspectRatio = cardImageAspectRatio;
    return this;
  }

  /**
   * Action menu desc template card builder.
   *
   * @param actionMenuDesc the action menu desc
   * @return the template card builder
   */
  public TemplateCardBuilder actionMenuDesc(String actionMenuDesc) {
    this.actionMenuDesc = actionMenuDesc;
    return this;
  }

  /**
   * Action menu action list template card builder.
   *
   * @param actionMenuItemList the action menu item list
   * @return the template card builder
   */
  public TemplateCardBuilder actionMenuActionList(List<ActionMenuItem> actionMenuItemList) {
    this.actionMenuActionList = actionMenuItemList;
    return this;
  }

  /**
   * Source icon url template card builder.
   *
   * @param sourceIconUrl the source icon url
   * @return the template card builder
   */
  public TemplateCardBuilder sourceIconUrl(String sourceIconUrl) {
    this.sourceIconUrl = sourceIconUrl;
    return this;
  }

  /**
   * Source desc template card builder.
   *
   * @param sourceDesc the source desc
   * @return the template card builder
   */
  public TemplateCardBuilder sourceDesc(String sourceDesc) {
    this.sourceDesc = sourceDesc;
    return this;
  }

  /**
   * Source desc color template card builder.
   *
   * @param sourceDescColor the source desc color
   * @return the template card builder
   */
  public TemplateCardBuilder sourceDescColor(Integer sourceDescColor) {
    this.sourceDescColor = sourceDescColor;
    return this;
  }

  /**
   * Main title title template card builder.
   *
   * @param mainTitleTitle the main title title
   * @return the template card builder
   */
  public TemplateCardBuilder mainTitleTitle(String mainTitleTitle) {
    this.mainTitleTitle = mainTitleTitle;
    return this;
  }

  /**
   * Main title desc template card builder.
   *
   * @param mainTitleDesc the main title desc
   * @return the template card builder
   */
  public TemplateCardBuilder mainTitleDesc(String mainTitleDesc) {
    this.mainTitleDesc = mainTitleDesc;
    return this;
  }

  /**
   * Emphasis content title template card builder.
   *
   * @param emphasisContentTitle the emphasis content title
   * @return the template card builder
   */
  public TemplateCardBuilder emphasisContentTitle(String emphasisContentTitle) {
    this.emphasisContentTitle = emphasisContentTitle;
    return this;
  }

  /**
   * Emphasis content desc template card builder.
   *
   * @param emphasisContentDesc the emphasis content desc
   * @return the template card builder
   */
  public TemplateCardBuilder emphasisContentDesc(String emphasisContentDesc) {
    this.emphasisContentDesc = emphasisContentDesc;
    return this;
  }

  /**
   * Sub title text template card builder.
   *
   * @param subTitleText the sub title text
   * @return the template card builder
   */
  public TemplateCardBuilder subTitleText(String subTitleText) {
    this.subTitleText = subTitleText;
    return this;
  }

  /**
   * Vertical contents template card builder.
   *
   * @param verticalContents the vertical contents
   * @return the template card builder
   */
  public TemplateCardBuilder verticalContents(List<VerticalContent> verticalContents) {
    this.verticalContents = verticalContents;
    return this;
  }

  /**
   * Horizontal contents template card builder.
   *
   * @param horizontalContents the horizontal contents
   * @return the template card builder
   */
  public TemplateCardBuilder horizontalContents(List<HorizontalContent> horizontalContents) {
    this.horizontalContents = horizontalContents;
    return this;
  }

  /**
   * Jumps template card builder.
   *
   * @param jumps the jumps
   * @return the template card builder
   */
  public TemplateCardBuilder jumps(List<TemplateCardJump> jumps) {
    this.jumps = jumps;
    return this;
  }

  /**
   * image_text_area template card builder.
   *
   * @param imageTextArea the card image_text_area
   * @return the template card builder
   */
  public TemplateCardBuilder imageTextArea(TemplateCardImageTextArea imageTextArea) {
    this.imageTextArea = imageTextArea;
    return this;
  }

  /**
   * Card action type template card builder.
   *
   * @param cardActionType the card action type
   * @return the template card builder
   */
  public TemplateCardBuilder cardActionType(Integer cardActionType) {
    this.cardActionType = cardActionType;
    return this;
  }

  /**
   * Card action url template card builder.
   *
   * @param cardActionUrl the card action url
   * @return the template card builder
   */
  public TemplateCardBuilder cardActionUrl(String cardActionUrl) {
    this.cardActionUrl = cardActionUrl;
    return this;
  }

  /**
   * Card action appid template card builder.
   *
   * @param cardActionAppid the card action appid
   * @return the template card builder
   */
  public TemplateCardBuilder cardActionAppid(String cardActionAppid) {
    this.cardActionAppid = cardActionAppid;
    return this;
  }

  /**
   * Card action pagepath template card builder.
   *
   * @param cardActionPagepath the card action pagepath
   * @return the template card builder
   */
  public TemplateCardBuilder cardActionPagepath(String cardActionPagepath) {
    this.cardActionPagepath = cardActionPagepath;
    return this;
  }

  /**
   * Task id template card builder.
   *
   * @param taskId the task id
   * @return the template card builder
   */
  public TemplateCardBuilder taskId(String taskId) {
    this.taskId = taskId;
    return this;
  }

  /**
   * Button selection template card builder.
   *
   * @param buttonSelection the button selection
   * @return the template card builder
   */
  public TemplateCardBuilder buttonSelection(TemplateCardButtonSelection buttonSelection) {
    this.buttonSelection = buttonSelection;
    return this;
  }

  /**
   * Buttons template card builder.
   *
   * @param buttons the buttons
   * @return the template card builder
   */
  public TemplateCardBuilder buttons(List<TemplateCardButton> buttons) {
    this.buttons = buttons;
    return this;
  }

  /**
   * Checkbox question key template card builder.
   *
   * @param checkboxQuestionKey the checkbox question key
   * @return the template card builder
   */
  public TemplateCardBuilder checkboxQuestionKey(String checkboxQuestionKey) {
    this.checkboxQuestionKey = checkboxQuestionKey;
    return this;
  }

  /**
   * Checkbox mode template card builder.
   *
   * @param checkboxMode the checkbox mode
   * @return the template card builder
   */
  public TemplateCardBuilder checkboxMode(Integer checkboxMode) {
    this.checkboxMode = checkboxMode;
    return this;
  }

  /**
   * Options template card builder.
   *
   * @param options the options
   * @return the template card builder
   */
  public TemplateCardBuilder options(List<CheckboxOption> options) {
    this.options = options;
    return this;
  }

  /**
   * Submit button text template card builder.
   *
   * @param submitButtonText the submit button text
   * @return the template card builder
   */
  public TemplateCardBuilder submitButtonText(String submitButtonText) {
    this.submitButtonText = submitButtonText;
    return this;
  }

  /**
   * Submit button key template card builder.
   *
   * @param submitButtonKey the submit button key
   * @return the template card builder
   */
  public TemplateCardBuilder submitButtonKey(String submitButtonKey) {
    this.submitButtonKey = submitButtonKey;
    return this;
  }

  /**
   * Selects template card builder.
   *
   * @param selects the selects
   * @return the template card builder
   */
  public TemplateCardBuilder selects(List<MultipleSelect> selects) {
    this.selects = selects;
    return this;
  }

  /**
   * Quote area template card builder.
   *
   * @param quoteArea the quote area
   * @return the template card builder
   */
  public TemplateCardBuilder quoteArea(QuoteArea quoteArea) {
    this.quoteArea = quoteArea;
    return this;
  }

  @Override
  public WxCpMessage build() {
    WxCpMessage m = super.build();
    m.setSafe(null);
    m.setCardType(this.cardType);
    m.setSourceIconUrl(this.sourceIconUrl);
    m.setSourceDesc(this.sourceDesc);
    m.setSourceDescColor(this.sourceDescColor);
    m.setActionMenuDesc(this.actionMenuDesc);
    m.setActionMenuActionList(this.actionMenuActionList);
    m.setMainTitleTitle(this.mainTitleTitle);
    m.setMainTitleDesc(this.mainTitleDesc);
    m.setImageTextArea(this.imageTextArea);
    m.setCardImageUrl(this.cardImageUrl);
    m.setCardImageAspectRatio(this.cardImageAspectRatio);
    m.setEmphasisContentTitle(this.emphasisContentTitle);
    m.setEmphasisContentDesc(this.emphasisContentDesc);
    m.setSubTitleText(this.subTitleText);
    m.setVerticalContents(this.verticalContents);
    m.setHorizontalContents(this.horizontalContents);
    m.setJumps(this.jumps);
    m.setCardActionType(this.cardActionType);
    m.setCardActionAppid(this.cardActionAppid);
    m.setCardActionPagepath(this.cardActionPagepath);
    m.setCardActionUrl(this.cardActionUrl);
    m.setTaskId(this.taskId);
    m.setButtonSelection(this.buttonSelection);
    m.setButtons(this.buttons);
    m.setCheckboxMode(this.checkboxMode);
    m.setCheckboxQuestionKey(this.checkboxQuestionKey);
    m.setOptions(this.options);
    m.setSubmitButtonText(this.submitButtonText);
    m.setSubmitButtonKey(this.submitButtonKey);
    m.setSelects(this.selects);
    m.setQuoteArea(this.quoteArea);
    return m;
  }
}
