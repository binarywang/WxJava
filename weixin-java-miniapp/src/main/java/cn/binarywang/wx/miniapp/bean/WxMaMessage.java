package cn.binarywang.wx.miniapp.bean;

import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.util.crypt.WxMaCryptUtils;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import cn.binarywang.wx.miniapp.util.xml.XStreamTransformer;
import com.google.gson.annotations.SerializedName;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.Data;
import me.chanjar.weixin.common.error.WxRuntimeException;
import me.chanjar.weixin.common.util.XmlUtils;
import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@XStreamAlias("xml")
@Data
public class WxMaMessage implements Serializable {
  private static final long serialVersionUID = -3586245291677274914L;

  /**
   * 使用dom4j解析的存放所有xml或json属性和值的map.
   */
  private Map<String, Object> allFieldsMap;

  @SerializedName("Encrypt")
  @XStreamAlias("Encrypt")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String encrypt;

  @SerializedName("ToUserName")
  @XStreamAlias("ToUserName")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String toUser;

  @SerializedName("FromUserName")
  @XStreamAlias("FromUserName")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String fromUser;

  @SerializedName("CreateTime")
  @XStreamAlias("CreateTime")
  private Integer createTime;

  @SerializedName("MsgType")
  @XStreamAlias("MsgType")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String msgType;

  @SerializedName("MsgDataFormat")
  @XStreamAlias("MsgDataFormat")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String msgDataFormat;

  @SerializedName("Content")
  @XStreamAlias("Content")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String content;

  @SerializedName("MsgId")
  @XStreamAlias("MsgId")
  private Long msgId;

  @SerializedName("PicUrl")
  @XStreamAlias("PicUrl")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String picUrl;

  @SerializedName("MediaId")
  @XStreamAlias("MediaId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String mediaId;

  @SerializedName("Event")
  @XStreamAlias("Event")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String event;

  @SerializedName("Title")
  @XStreamAlias("Title")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String title;

  @SerializedName("AppId")
  @XStreamAlias("AppId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String appId;

  @SerializedName("PagePath")
  @XStreamAlias("PagePath")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String pagePath;

  @SerializedName("ThumbUrl")
  @XStreamAlias("ThumbUrl")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String thumbUrl;

  @SerializedName("ThumbMediaId")
  @XStreamAlias("ThumbMediaId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String thumbMediaId;

  @SerializedName("SessionFrom")
  @XStreamAlias("SessionFrom")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String sessionFrom;

  /**
   * 以下是异步校验图片/音频是否含有违法违规内容的异步检测结果推送报文中的参数
   */
  @SerializedName("isrisky")
  @XStreamAlias("isrisky")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String isRisky;

  @SerializedName("extra_info_json")
  @XStreamAlias("extra_info_json")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String extraInfoJson;

  @SerializedName("appid")
  @XStreamAlias("appid")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String appid;

  @SerializedName("trace_id")
  @XStreamAlias("trace_id")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String traceId;

  @SerializedName("status_code")
  @XStreamAlias("status_code")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String statusCode;

  /**
   * 异步校验图片/音频内容安全 接口版本
   * @since 2.0
   */
  @SerializedName("version")
  @XStreamAlias("version")
  private Integer version;
  /**
   * 异步校验图片/音频内容安全 综合结果
   * @since 2.0
   */
  @SerializedName("result")
  @XStreamAlias("result")
  private WxMaMediaAsyncCheckResult.ResultBean result;
  /**
   * 异步校验图片/音频内容安全 详细检测结果
   * @since 2.0
   */
  @SerializedName("detail")
  @XStreamAlias("detail")
  @XStreamImplicit
  private List<WxMaMediaAsyncCheckResult.DetailBean> detail;

  @SerializedName("Scene")
  @XStreamAlias("Scene")
  private Integer scene;

  @SerializedName("Query")
  @XStreamAlias("Query")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String query;

  @SerializedName("AppID")
  @XStreamAlias("AppID")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String appID;

  @SerializedName("RevokeInfo")
  @XStreamAlias("RevokeInfo")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String revokeInfo;

  @SerializedName("OpenID")
  @XStreamAlias("OpenID")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String openId;

  @SerializedName("PluginID")
  @XStreamAlias("PluginID")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String pluginId;

  @SerializedName("OpenPID")
  @XStreamAlias("OpenPID")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String openPid;

  @XStreamAlias("SubscribeMsgPopupEvent")
  private WxMaSubscribeMsgEvent.SubscribeMsgPopupEvent subscribeMsgPopupEvent;

  @XStreamAlias("SubscribeMsgChangeEvent")
  private WxMaSubscribeMsgEvent.SubscribeMsgChangeEvent subscribeMsgChangeEvent;

  @XStreamAlias("SubscribeMsgSentEvent")
  private WxMaSubscribeMsgEvent.SubscribeMsgSentEvent subscribeMsgSentEvent;

  // 小程序基本信息

  //region 小程序基本信息 infoType=notify_3rd_wxa_auth_and_icp

  /**
   * 返回值
   */
  @XStreamAlias("ret")
  private String ret;

  /**
   * 一级类目id
   */
  @XStreamAlias("first")
  private String first;

  /**
   * 二级类目id
   */
  @XStreamAlias("second")
  private String second;

  /**
   * 驳回原因
   */
  @XStreamAlias("reason")
  private String reason;

  /**
   * 小程序代码审核驳回原因
   */
  @XStreamAlias("Reason")
  private String weAppReason;

  /**
   * 昵称
   */
  @XStreamAlias("nickname")
  private String nickname;

  /**
   * 原始通知内容
   */
  private String context;

  /**
   * 微信支付订单号
   */
  @XStreamAlias("transaction_id")
  private String transactionId;
  /**
   * 商户号
   */
  @XStreamAlias("merchant_id")
  private String merchantId;
  /**
   * 子商户号
   */
  @XStreamAlias("sub_merchant_id")
  private String subMerchantId;
  /**
   * 商户订单号
   */
  @XStreamAlias("merchant_trade_no")
  private String merchantTradeNo;
  /**
   * 支付成功时间，秒级时间戳
   */
  @XStreamAlias("pay_time")
  private Long payTime;
  /**
   * 消息文本内容
   */
  @XStreamAlias("msg")
  private String msg;
  /**
   * 发货时间，秒级时间戳
   */
  @XStreamAlias("shipped_time")
  private Long shippedTime;
  /**
   * 预计结算时间，秒级时间戳。发货时推送才有该字段
   */
  @XStreamAlias("estimated_settlement_time")
  private Long estimatedSettlementTime;
  /**
   * 确认收货方式：1. 手动确认收货；2. 自动确认收货。结算时推送才有该字段
   */
  @XStreamAlias("confirm_receive_method")
  private Integer confirmReceiveMethod;
  /**
   * 确认收货时间，秒级时间戳。结算时推送才有该字段
   */
  @XStreamAlias("confirm_receive_time")
  private Long confirmReceiveTime;
  /**
   * 订单结算时间，秒级时间戳。结算时推送才有该字段
   */
  @XStreamAlias("settlement_time")
  private Long settlementTime;

  /**
   * 不要直接使用这个字段，
   * 这个字段只是为了适配 SubscribeMsgPopupEvent SubscribeMsgChangeEvent SubscribeMsgSentEvent
   * 在json里面名称都是List并且有时候是对象有时候是数组的问题
   * 当List只有一个对象的时候，微信服务器推送过来的的List是对象而非数组，当有多个对象的时候推送过来的才是数组
   * 当只有一个对象的时候
   * "List": {
   *         "TemplateId": "hD-ixGOhYmUfjOnI8MCzQMPshzGVeux_2vzyvQu7O68",
   *         "SubscribeStatusString": "accept",
   *         "PopupScene": "0"
   *     }
   * 当有多条数据的时候
   * "List": [   {
   *         "TemplateId": "hD-ixGOhYmUfjOnI8MCzQMPshzGVeux_2vzyvQu7O68",
   *         "SubscribeStatusString": "accept",
   *         "PopupScene": "0"
   *     }, {
   *         "TemplateId": "hD-ixGOhYmUfjOnI8MCzQMPshzGVeux_2vzyvQu7O68",
   *         "SubscribeStatusString": "accept",
   *         "PopupScene": "0"
   *     }]
   */
  @SerializedName("List")
  private WxMaSubscribeMsgEvent.WxMaSubscribeMsgEventJson uselessMsg;

  public static WxMaMessage fromXml(String xml) {
    WxMaMessage message = XStreamTransformer.fromXml(WxMaMessage.class, xml);
    message.setAllFieldsMap(XmlUtils.xml2Map(xml));
    return message;
  }

  @Deprecated
  public static WxMaMessage fromXml(InputStream is) {
    return XStreamTransformer.fromXml(WxMaMessage.class, is);
  }

  /**
   * 从加密字符串转换.
   *
   * @param encryptedXml 密文
   * @param wxMaConfig   配置存储器对象
   * @param timestamp    时间戳
   * @param nonce        随机串
   * @param msgSignature 签名串
   */
  public static WxMaMessage fromEncryptedXml(String encryptedXml,
                                             WxMaConfig wxMaConfig, String timestamp, String nonce,
                                             String msgSignature) {
    String plainText = new WxMaCryptUtils(wxMaConfig).decryptXml(msgSignature, timestamp, nonce, encryptedXml);
    WxMaMessage wxMaMessage = fromXml(plainText);
    wxMaMessage.setContext(plainText);
    return wxMaMessage;
  }

  public static WxMaMessage fromEncryptedXml(InputStream is, WxMaConfig wxMaConfig, String timestamp,
                                             String nonce, String msgSignature) {
    try {
      return fromEncryptedXml(IOUtils.toString(is, StandardCharsets.UTF_8), wxMaConfig,
        timestamp, nonce, msgSignature);
    } catch (IOException e) {
      throw new WxRuntimeException(e);
    }
  }

  public static WxMaMessage fromJson(String json) {
    WxMaMessage message =  WxMaGsonBuilder.create().fromJson(json, WxMaMessage.class);
    // 在这里处理 event的json格式时候的 list 问题，让json和xml的程序接口可以保持一致， 详见 uselessMsg 字段的注释
    if (message.getUselessMsg() != null) {
      if (StringUtils.equals(message.getEvent(), "subscribe_msg_popup_event")) {
        message.setSubscribeMsgPopupEvent(message.getUselessMsg().getPopupEvents());
      } else if (StringUtils.equals(message.getEvent(), "subscribe_msg_change_event")) {
        message.setSubscribeMsgChangeEvent(message.getUselessMsg().getChangeEvents());
      } else if (StringUtils.equals(message.getEvent(), "subscribe_msg_sent_event")) {
        message.setSubscribeMsgSentEvent(message.getUselessMsg().getSentEvent());
      }
      message.setUselessMsg(null);
    }
    message.setAllFieldsMap(WxMaGsonBuilder.create().fromJson(json, Map.class));
    return message;
  }

  public static WxMaMessage fromEncryptedJson(String encryptedJson, WxMaConfig config) {
    try {
      WxMaMessage encryptedMessage = fromJson(encryptedJson);
      String plainText = new WxMaCryptUtils(config).decrypt(encryptedMessage.getEncrypt());
      return fromJson(plainText);
    } catch (Exception e) {
      throw new WxRuntimeException(e);
    }
  }

  public static WxMaMessage fromEncryptedJson(InputStream inputStream, WxMaConfig config) {
    try {
      return fromEncryptedJson(IOUtils.toString(inputStream, StandardCharsets.UTF_8), config);
    } catch (IOException e) {
      throw new WxRuntimeException(e);
    }
  }

  @Override
  public String toString() {
    return this.toJson();
  }

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }

  public String getAppid() {
    return appid;
  }

  public void setAppid(String appid) {
    this.appid = appid;
  }

  public String getAppID() {
    return appID;
  }

  public void setAppID(String appID) {
    this.appID = appID;
  }

  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

}
