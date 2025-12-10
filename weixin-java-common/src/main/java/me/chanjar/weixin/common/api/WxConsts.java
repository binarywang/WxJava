package me.chanjar.weixin.common.api;

import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static me.chanjar.weixin.common.error.WxMpErrorMsgEnum.*;

/**
 * 微信开发所使用到的常量类.
 *
 * @author Daniel Qian & binarywang & Wang_Wong
 */
@UtilityClass
public class WxConsts {
  /**
   * access_token 相关错误代码
   * <pre>
   * 发生以下情况时尝试刷新access_token
   * 40001 获取access_token时AppSecret错误，或者access_token无效
   * 42001 access_token超时
   * 40014 不合法的access_token，请开发者认真比对access_token的有效性（如是否过期），或查看是否正在为恰当的公众号调用接口
   * </pre>
   */
  public static final List<Integer> ACCESS_TOKEN_ERROR_CODES = Arrays.asList(CODE_40001.getCode(),
    CODE_40014.getCode(), CODE_42001.getCode());

  /**
   * 微信接口返回的参数errcode.
   */
  public static final String ERR_CODE = "errcode";

  /**
   * 微信推送过来的消息的类型，和发送给微信xml格式消息的消息类型.
   */
  @UtilityClass
  public static class XmlMsgType {
    public static final String TEXT = "text";
    public static final String IMAGE = "image";
    public static final String VOICE = "voice";
    public static final String SHORTVIDEO = "shortvideo";
    public static final String VIDEO = "video";
    public static final String NEWS = "news";
    public static final String MUSIC = "music";
    public static final String LOCATION = "location";
    public static final String LINK = "link";
    public static final String EVENT = "event";
    public static final String DEVICE_TEXT = "device_text";
    public static final String DEVICE_EVENT = "device_event";
    public static final String DEVICE_STATUS = "device_status";
    public static final String HARDWARE = "hardware";
    public static final String TRANSFER_CUSTOMER_SERVICE = "transfer_customer_service";
    public static final String UPDATE_TASKCARD = "update_taskcard";
    public static final String UPDATE_BUTTON = "update_button";
  }

  /**
   * 主动发送消息(即客服消息)的消息类型.
   */
  @UtilityClass
  public static class KefuMsgType {
    /**
     * 文本消息.
     */
    public static final String TEXT = "text";
    /**
     * 图片消息.
     */
    public static final String IMAGE = "image";
    /**
     * 语音消息.
     */
    public static final String VOICE = "voice";
    /**
     * 视频消息.
     */
    public static final String VIDEO = "video";
    /**
     * 音乐消息.
     */
    public static final String MUSIC = "music";
    /**
     * 图文消息（点击跳转到外链）.
     */
    public static final String NEWS = "news";
    /**
     * 图文消息（点击跳转到图文消息页面）.
     */
    public static final String MPNEWS = "mpnews";
    /**
     * markdown消息.
     * （目前仅支持markdown语法的子集，微工作台（原企业号）不支持展示markdown消息）
     */
    public static final String MARKDOWN = "markdown";
    /**
     * 发送文件（CP专用）.
     */
    public static final String FILE = "file";
    /**
     * 文本卡片消息（CP专用）.
     */
    public static final String TEXTCARD = "textcard";
    /**
     * 卡券消息.
     */
    public static final String WXCARD = "wxcard";
    /**
     * 转发到客服的消息.
     */
    public static final String TRANSFER_CUSTOMER_SERVICE = "transfer_customer_service";

    /**
     * 小程序卡片(要求小程序与公众号已关联).
     */
    public static final String MINIPROGRAMPAGE = "miniprogrampage";

    /**
     * 任务卡片消息.
     */
    public static final String TASKCARD = "taskcard";

    /**
     * 菜单消息.
     */
    public static final String MSGMENU = "msgmenu";

    /**
     * 小程序通知消息.
     */
    public static final String MINIPROGRAM_NOTICE = "miniprogram_notice";

    /**
     * 模板卡片消息.
     */
    public static final String TEMPLATE_CARD = "template_card";

    /**
     * 发送图文消息（点击跳转到图文消息页面）使用通过 “发布” 系列接口得到的 article_id(草稿箱功能上线后不再支持客服接口中带 media_id 的 mpnews 类型的图文消息)
     */
    public static final String MP_NEWS_ARTICLE = "mpnewsarticle";
  }

  /**
   * 发送「学校通知」类型
   * https://developer.work.weixin.qq.com/document/path/92321
   */
  @UtilityClass
  public static class SchoolContactMsgType {

    /**
     * 文本消息.
     */
    public static final String TEXT = "text";

    /**
     * 图片消息.
     */
    public static final String IMAGE = "image";

    /**
     * 语音消息.
     */
    public static final String VOICE = "voice";

    /**
     * 视频消息.
     */
    public static final String VIDEO = "video";

    /**
     * 文件消息
     */
    public static final String FILE = "file";

    /**
     * 图文消息
     */
    public static final String NEWS = "news";

    /**
     * 图文消息（mpnews）
     */
    public static final String MPNEWS = "mpnews";

    /**
     * 小程序消息
     */
    public static final String MINIPROGRAM = "miniprogram";

  }

  /**
   * 企业微信模板卡片消息的卡片类型
   */
  @UtilityClass
  public static class TemplateCardType {
    /**
     * 文本通知型卡片
     */
    public static final String TEXT_NOTICE = "text_notice";
    /**
     * 图文展示型卡片
     */
    public static final String NEWS_NOTICE = "news_notice";
    /**
     * 按钮交互型卡片
     */
    public static final String BUTTON_INTERACTION = "button_interaction";
    /**
     * 投票选择型卡片
     */
    public static final String VOTE_INTERACTION = "vote_interaction";
    /**
     * 多项选择型卡片
     */
    public static final String MULTIPLE_INTERACTION = "multiple_interaction";
  }

  /**
   * 表示是否是保密消息，0表示否，1表示是，默认0.
   */
  @UtilityClass
  public static class KefuMsgSafe {
    public static final String NO = "0";
    public static final String YES = "1";
  }

  /**
   * 群发消息的消息类型.
   */
  @UtilityClass
  public static class MassMsgType {
    public static final String MPNEWS = "mpnews";
    public static final String TEXT = "text";
    public static final String VOICE = "voice";
    public static final String IMAGE = "image";
    public static final String IMAGES = "images";
    public static final String MPVIDEO = "mpvideo";
  }

  /**
   * 群发消息后微信端推送给服务器的反馈消息.
   */
  @UtilityClass
  public static class MassMsgStatus {
    public static final String SEND_SUCCESS = "send success";
    public static final String SEND_FAIL = "send fail";
    public static final String ERR_10001 = "err(10001)";
    public static final String ERR_20001 = "err(20001)";
    public static final String ERR_20004 = "err(20004)";
    public static final String ERR_20002 = "err(20002)";
    public static final String ERR_20006 = "err(20006)";
    public static final String ERR_20008 = "err(20008)";
    public static final String ERR_20013 = "err(20013)";
    public static final String ERR_22000 = "err(22000)";
    public static final String ERR_21000 = "err(21000)";
    public static final String ERR_30001 = "err(30001)";
    public static final String ERR_30002 = "err(30002)";
    public static final String ERR_30003 = "err(30003)";
    public static final String ERR_40001 = "err(40001)";
    public static final String ERR_40002 = "err(40002)";


    /**
     * 群发反馈消息代码所对应的文字描述.
     */
    public static final Map<String, String> STATUS_DESC = new HashMap<>();

    static {
      STATUS_DESC.put(SEND_SUCCESS, "发送成功");
      STATUS_DESC.put(SEND_FAIL, "发送失败");
      STATUS_DESC.put(ERR_10001, "涉嫌广告");
      STATUS_DESC.put(ERR_20001, "涉嫌政治");
      STATUS_DESC.put(ERR_20004, "涉嫌社会");
      STATUS_DESC.put(ERR_20002, "涉嫌色情");
      STATUS_DESC.put(ERR_20006, "涉嫌违法犯罪");
      STATUS_DESC.put(ERR_20008, "涉嫌欺诈");
      STATUS_DESC.put(ERR_20013, "涉嫌版权");
      STATUS_DESC.put(ERR_22000, "涉嫌互推_互相宣传");
      STATUS_DESC.put(ERR_21000, "涉嫌其他");
      STATUS_DESC.put(ERR_30001, "原创校验出现系统错误且用户选择了被判为转载就不群发");
      STATUS_DESC.put(ERR_30002, "原创校验被判定为不能群发");
      STATUS_DESC.put(ERR_30003, "原创校验被判定为转载文且用户选择了被判为转载就不群发");
      STATUS_DESC.put(ERR_40001, "管理员拒绝");
      STATUS_DESC.put(ERR_40002, "管理员30分钟内无响应，超时");
    }
  }

  /**
   * 微信端推送过来的事件类型.
   */
  @UtilityClass
  public static class EventType {
    public static final String SUBSCRIBE = "subscribe";
    public static final String UNSUBSCRIBE = "unsubscribe";
    public static final String SCAN = "SCAN";
    public static final String LOCATION = "LOCATION";
    public static final String CLICK = "CLICK";
    public static final String VIEW = "VIEW";
    public static final String MASS_SEND_JOB_FINISH = "MASSSENDJOBFINISH";

    public static final String SYS_APPROVAL_CHANGE = "sys_approval_change";
    /**
     * 扫码推事件的事件推送
     */
    public static final String SCANCODE_PUSH = "scancode_push";
    /**
     * 扫码推事件且弹出“消息接收中”提示框的事件推送.
     */
    public static final String SCANCODE_WAITMSG = "scancode_waitmsg";
    /**
     * 弹出系统拍照发图的事件推送.
     */
    public static final String PIC_SYSPHOTO = "pic_sysphoto";
    /**
     * 弹出拍照或者相册发图的事件推送.
     */
    public static final String PIC_PHOTO_OR_ALBUM = "pic_photo_or_album";
    /**
     * 弹出微信相册发图器的事件推送.
     */
    public static final String PIC_WEIXIN = "pic_weixin";
    /**
     * 弹出地理位置选择器的事件推送.
     */
    public static final String LOCATION_SELECT = "location_select";

    /**
     * 授权用户资料变更事件
     * 1、 当部分用户的资料存在风险时，平台会对用户资料进行清理，并通过消息推送服务器通知最近30天授权过的公众号开发者，我们建议开发者留意响应该事件，及时主动更新或清理用户的头像及昵称，降低风险。
     * 2、 当用户撤回授权信息时，平台会通过消息推送服务器通知给公众号开发者，请开发者注意及时删除用户信息。
     */
    public static final String USER_INFO_MODIFIED = "user_info_modified";

    /**
     * 用户撤回授权事件
     */
    public static final String USER_AUTHORIZATION_REVOKE = "user_authorization_revoke";

    /**
     * 群发模板回调事件
     */
    public static final String TEMPLATE_SEND_JOB_FINISH = "TEMPLATESENDJOBFINISH";

    /**
     * 微信小店 订单付款通知.
     */
    public static final String MERCHANT_ORDER = "merchant_order";

    /**
     * 卡券事件：卡券通过审核
     */
    public static final String CARD_PASS_CHECK = "card_pass_check";

    /**
     * 卡券事件：卡券未通过审核
     */
    public static final String CARD_NOT_PASS_CHECK = "card_not_pass_check";

    /**
     * 卡券事件：用户领取卡券
     */
    public static final String CARD_USER_GET_CARD = "user_get_card";

    /**
     * 卡券事件：用户转赠卡券
     */
    public static final String CARD_USER_GIFTING_CARD = "user_gifting_card";

    /**
     * 异步安全校验事件
     */
    public static final String WXA_MEDIA_CHECK = "wxa_media_check";

    /**
     * 卡券事件：用户核销卡券
     */
    public static final String CARD_USER_CONSUME_CARD = "user_consume_card";


    /**
     * 卡券事件：用户通过卡券的微信买单完成时推送
     */
    public static final String CARD_USER_PAY_FROM_PAY_CELL = "user_pay_from_pay_cell";


    /**
     * 卡券事件：用户提交会员卡开卡信息
     */
    public static final String CARD_SUBMIT_MEMBERCARD_USER_INFO = "submit_membercard_user_info";

    /**
     * 卡券事件：用户打开查看卡券
     */
    public static final String CARD_USER_VIEW_CARD = "user_view_card";

    /**
     * 卡券事件：用户删除卡券
     */
    public static final String CARD_USER_DEL_CARD = "user_del_card";

    /**
     * 卡券事件：用户在卡券里点击查看公众号进入会话时（需要用户已经关注公众号）
     */
    public static final String CARD_USER_ENTER_SESSION_FROM_CARD = "user_enter_session_from_card";

    /**
     * 卡券事件：当用户的会员卡积分余额发生变动时
     */
    public static final String CARD_UPDATE_MEMBER_CARD = "update_member_card";

    /**
     * 卡券事件：当某个card_id的初始库存数大于200且当前库存小于等于100时，用户尝试领券会触发发送事件给商户，事件每隔12h发送一次
     */
    public static final String CARD_SKU_REMIND = "card_sku_remind";

    /**
     * 卡券事件：当商户朋友的券券点发生变动时
     */
    public static final String CARD_PAY_ORDER = "card_pay_order";

    /**
     * 小程序审核事件：审核通过
     */
    public static final String WEAPP_AUDIT_SUCCESS = "weapp_audit_success";

    /**
     * 小程序审核事件：审核不通过
     */
    public static final String WEAPP_AUDIT_FAIL = "weapp_audit_fail";


    /**
     * 小程序审核事件：审核延后
     */
    public static final String WEAPP_AUDIT_DELAY = "weapp_audit_delay";

    /**
     * 小程序自定义交易组件支付通知
     */
    public static final String OPEN_PRODUCT_ORDER_PAY = "open_product_order_pay";
    /**
     * 点击菜单跳转小程序的事件推送
     */
    public static final String VIEW_MINIPROGRAM = "view_miniprogram";

    /**
     * 订阅通知事件：用户操作订阅通知弹窗
     */
    public static final String SUBSCRIBE_MSG_POPUP_EVENT = "subscribe_msg_popup_event";

    /**
     * 订阅通知事件：用户管理订阅通知
     */
    public static final String SUBSCRIBE_MSG_CHANGE_EVENT = "subscribe_msg_change_event";

    /**
     * 订阅通知事件：发送订阅通知回调
     */
    public static final String SUBSCRIBE_MSG_SENT_EVENT = "subscribe_msg_sent_event";

    /**
     * 名称审核事件
     */
    public static final String WXA_NICKNAME_AUDIT = "wxa_nickname_audit";
    /**
     * 小程序违规记录事件
     */
    public static final String WXA_ILLEGAL_RECORD = "wxa_illegal_record";
    /**
     * 小程序申诉记录推送
     */
    public static final String WXA_APPEAL_RECORD = "wxa_appeal_record";
    /**
     * 隐私权限审核结果推送
     */
    public static final String WXA_PRIVACY_APPLY = "wxa_privacy_apply";
    /**
     * 类目审核结果事件推送
     */
    public static final String WXA_CATEGORY_AUDIT = "wxa_category_audit";
    /**
     * 小程序微信认证支付成功事件
     */
    public static final String WX_VERIFY_PAY_SUCC = "wx_verify_pay_succ";
    /**
     * 小程序微信认证派单事件
     */
    public static final String WX_VERIFY_DISPATCH = "wx_verify_dispatch";
    /**
     * 提醒需要上传发货信息事件：曾经发过货的小程序，订单超过48小时未发货时
     */
    public static final String TRADE_MANAGE_REMIND_SHIPPING = "trade_manage_remind_shipping";
    /**
     * 订单完成发货时、订单结算时
     */
    public static final String TRADE_MANAGE_ORDER_SETTLEMENT = "trade_manage_order_settlement";
  }

  /**
   * 上传多媒体（临时素材）文件的类型.
   */
  public static class MediaFileType {
    public static final String IMAGE = "image";
    public static final String VOICE = "voice";
    public static final String VIDEO = "video";
    public static final String THUMB = "thumb";
    public static final String FILE = "file";
  }

  /**
   * 自定义菜单的按钮类型.
   */
  @UtilityClass
  public static class MenuButtonType {
    /**
     * 点击推事件.
     */
    public static final String CLICK = "click";
    /**
     * 跳转URL.
     */
    public static final String VIEW = "view";
    /**
     * 跳转到小程序.
     */
    public static final String MINIPROGRAM = "miniprogram";
    /**
     * 扫码推事件.
     */
    public static final String SCANCODE_PUSH = "scancode_push";
    /**
     * 扫码推事件且弹出“消息接收中”提示框.
     */
    public static final String SCANCODE_WAITMSG = "scancode_waitmsg";
    /**
     * 弹出系统拍照发图.
     */
    public static final String PIC_SYSPHOTO = "pic_sysphoto";
    /**
     * 弹出拍照或者相册发图.
     */
    public static final String PIC_PHOTO_OR_ALBUM = "pic_photo_or_album";
    /**
     * 弹出微信相册发图器.
     */
    public static final String PIC_WEIXIN = "pic_weixin";
    /**
     * 弹出地理位置选择器.
     */
    public static final String LOCATION_SELECT = "location_select";
    /**
     * 下发消息（除文本消息）.
     */
    public static final String MEDIA_ID = "media_id";
    /**
     * 跳转图文消息URL.
     */
    public static final String VIEW_LIMITED = "view_limited";
  }

  /**
   * oauth2网页授权的scope.
   */
  @UtilityClass
  public static class OAuth2Scope {
    /**
     * 不弹出授权页面，直接跳转，只能获取用户openid.
     */
    public static final String SNSAPI_BASE = "snsapi_base";

    /**
     * 弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息.
     */
    public static final String SNSAPI_USERINFO = "snsapi_userinfo";

    /**
     * 手动授权,可获取成员的详细信息,包含手机、邮箱。只适用于企业微信或企业号.
     */
    public static final String SNSAPI_PRIVATEINFO = "snsapi_privateinfo";
  }

  /**
   * 网页应用登录授权作用域.
   */
  @UtilityClass
  public static class QrConnectScope {
    public static final String SNSAPI_LOGIN = "snsapi_login";
  }

  /**
   * 永久素材类型.
   */
  @UtilityClass
  public static class MaterialType {
    public static final String NEWS = "news";
    public static final String VOICE = "voice";
    public static final String IMAGE = "image";
    public static final String VIDEO = "video";
  }


  /**
   * 网络检测入参.
   */
  @UtilityClass
  public static class NetCheckArgs {
    public static final String ACTIONDNS = "dns";
    public static final String ACTIONPING = "ping";
    public static final String ACTIONALL = "all";
    public static final String OPERATORUNICOM = "UNICOM";
    public static final String OPERATORCHINANET = "CHINANET";
    public static final String OPERATORCAP = "CAP";
    public static final String OPERATORDEFAULT = "DEFAULT";
  }

  /**
   * appId 类型
   */
  @UtilityClass
  public static class AppIdType {
    /**
     * 公众号appId类型
     */
    public static final String MP_TYPE = "mp";
    /**
     * 小程序appId类型
     */
    public static final String MINI_TYPE = "mini";
  }

  /**
   * 新建文章类型
   */
  @UtilityClass
  public static class ArticleType {
    /**
     * 图文消息
     */
    public static final String NEWS = "news";
    /**
     * 图片消息
     */
    public static final String NEWS_PIC = "newspic";
  }
}
