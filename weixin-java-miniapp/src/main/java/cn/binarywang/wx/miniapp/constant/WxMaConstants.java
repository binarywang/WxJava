package cn.binarywang.wx.miniapp.constant;

import lombok.experimental.UtilityClass;

/**
 * <pre>
 *  小程序常量.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@UtilityClass
public class WxMaConstants {

  /**
   * 默认的env_version值
   */
  public static final String DEFAULT_ENV_VERSION = "release";

  /**
   * 素材类型.
   */
  @UtilityClass
  public static class MediaType {
    /**
     * 图片.
     */
    public static final String IMAGE = "image";
  }

  /**
   * 消息格式.
   */
  @UtilityClass
  public static class MsgDataFormat {
    public static final String XML = "XML";
    public static final String JSON = "JSON";
  }

  /**
   * 客服消息的消息类型.
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
     * 图文链接.
     */
    public static final String LINK = "link";
    /**
     * 小程序卡片消息.
     */
    public static final String MA_PAGE = "miniprogrampage";
  }

  /**
   * 内容安全检测的媒体类型
   */
  @UtilityClass
  public static final class SecCheckMediaType {
    /**
     * 音频
     */
    public static final int VOICE = 1;

    /**
     * 图片
     */
    public static final int IMAGE = 2;
  }

  /**
   * 快递账号绑定类型
   */
  @UtilityClass
  public static final class BindAccountType {

    /**
     * 绑定
     */
    public static final String BIND = "bind";

    /**
     * 解绑
     */
    public static final String UNBIND = "unbind";
  }

  /**
   * 快递下单订单来源
   */
  @UtilityClass
  public static final class OrderAddSource {

    /**
     * 小程序
     */
    public static final int MINI_PROGRAM = 0;

    /**
     * APP或H5
     */
    public static final int APP_OR_H5 = 2;
  }

  /**
   * 快递下单保价
   */
  @UtilityClass
  public static final class OrderAddInsured {
    /**
     * 不保价
     */
    public static final int INSURED_PROGRAM = 0;

    /**
     * 保价
     */
    public static final int USE_INSURED = 1;

    /**
     * 默认保价金额
     */
    public static final int DEFAULT_INSURED_VALUE = 0;
  }

  /**
   * 小程序订阅消息跳转小程序类型
   * <p>
   * developer为开发版；trial为体验版；formal为正式版；默认为正式版
   */
  @UtilityClass
  public static final class MiniProgramState {
    /**
     * 开发版
     */
    public static final String DEVELOPER = "developer";

    /**
     * 体验版
     */
    public static final String TRIAL = "trial";

    /**
     * 正式版
     */
    public static final String FORMAL = "formal";
  }


  /**
   * 进入小程序查看的语言类型
   * 支持zh_CN(简体中文)、en_US(英文)、zh_HK(繁体中文)、zh_TW(繁体中文)，默认为zh_CN
   */
  @UtilityClass
  public static final class MiniProgramLang {
    /**
     * 简体中文
     */
    public static final String ZH_CN = "zh_CN";

    /**
     * 英文
     */
    public static final String EN_US = "en_US";

    /**
     * 繁体中文
     */
    public static final String ZH_HK = "zh_HK";

    /**
     * 繁体中文
     */
    public static final String ZH_TW = "zh_TW";
  }

  @UtilityClass
  public static final class AuditStatus {
    public static final int INVALID = 0;
    public static final int ONGOING = 1;
    public static final int REJECTED = 2;
    public static final int APPROVED = 3;
    public static final int RECOMMIT = 4;
  }


  @UtilityClass
  public static final class ExpeditedType {

    /**
     * 非加急
     */
    public static final int NORMAL = 0;

    /**
     * 加急
     */
    public static final int HIGH_PRIORITY = 1;
  }

  @UtilityClass
  public static final class UploadTaskType {
    public static final int PULL_UPLOAD = 1;
  }

  @UtilityClass
  public static final class UploadTaskStatus {
    public static final int WAITING = 1;
    public static final int WORKING = 2;
    public static final int DONE = 3;
    public static final int FAILED = 4;
  }

  @UtilityClass
  public static final class UploadResourceType {
    public static final int MEDIA = 1;
    public static final int COVER = 2;
  }
  @UtilityClass
  public static final class XPayEnv {
    public static final int PRODUCT = 0;
    public static final int SANDBOX = 1;
  }
  @UtilityClass
  public static final class XPayFirstCharge {
    public static final int NO = 0;
    public static final int YES = 1;
  }
  @UtilityClass
  public static final class XPayDeviceType {
    public static final int ANDROID = 1;
    public static final int IOS = 2;
  }
  @UtilityClass
  public static final class XPayBizType {
    public static final int SHORT_DRAMA = 1;
  }
  @UtilityClass
  public static final class XPayOrderType {
    public static final int PAY_ORDER = 0;//0-支付单
    public static final int REFUND_ORDER = 1;//1-退款单
  }
  @UtilityClass
  public static final class XPayOrderStatus {
    public static final int INIT = 0;//0-订单初始化（未创建成功，不可用于支付）
    public static final int CREATED = 1;// 1-订单创建成功
    public static final int PAID = 2;//2-订单已经支付，待发货
    public static final int PROVIDING = 3;// 3-订单发货中
    public static final int PROVIDED = 4;// 4-订单已发货
    public static final int REFUNDED = 5;// 5-订单已经退款
    public static final int CLOSED = 6;// 6-订单已经关闭（不可再使用）
    public static final int REFUND_FAILED = 7;// 7-订单退款失败
  }
  @UtilityClass
  public static final class XPayNotifyEvent {
    public static final String COIN_PAY = "xpay_coin_pay_notify";
    public static final String GOODS_DELIVER = "xpay_goods_deliver_notify";
    public static final String REFUND = "xpay_refund_notify";
    public static final String COMPLAINT = "xpay_complaint_notify";

  }
  @UtilityClass
  public static final class XPayPaymentMode {
    public static String COIN = "short_series_coin";
    public static String GOODS = "short_series_goods";

  }

  @UtilityClass
  public static final class XPayPlatform {
    public static String ANDROID = "android";
  }

  @UtilityClass
  public static final class XPayCurrencyType {
    public static String CNY = "CNY";

  }

  @UtilityClass
  public static final class XPayWxApiSigUri {
    public static String WXAPI = "requestVirtualPayment";

  }

  @UtilityClass
  public static final class XPayRefundReqFrom {
    public static final String FROM_CS = "1";//人工客服退款
    public static final String FROM_USER = "2";//用户自己发起
    public static final String FROM_MISC = "3";//1-其它
  }

  @UtilityClass
  public static final class XPayPublishStatus {
    public static final int PUBLISH_UPLOADING = 0;//0-上传中
    public static final int PUBLISH_EXISTED = 1;//1-id已经存在
    public static final int PUBLISH_SUCCESSFUL = 2;//  2-发布成功
    public static final int PUBLISH_FAILED = 3;//3-发布失败
  }
}
