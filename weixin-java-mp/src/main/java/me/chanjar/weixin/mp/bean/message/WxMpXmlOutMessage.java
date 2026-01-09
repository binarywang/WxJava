package me.chanjar.weixin.mp.bean.message;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Data;
import me.chanjar.weixin.common.util.crypto.WxCryptUtil;
import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.builder.outxml.*;
import me.chanjar.weixin.mp.util.crypto.WxMpCryptUtil;
import me.chanjar.weixin.mp.util.xml.XStreamTransformer;

import java.io.Serializable;

@Data
@XStreamAlias("xml")
@JacksonXmlRootElement(localName = "xml")
public abstract class WxMpXmlOutMessage implements Serializable {
  private static final long serialVersionUID = -381382011286216263L;

  @XStreamAlias("ToUserName")
  @XStreamConverter(value = XStreamCDataConverter.class)
  @JacksonXmlProperty(localName = "ToUserName")
  @JacksonXmlCData
  protected String toUserName;

  @XStreamAlias("FromUserName")
  @XStreamConverter(value = XStreamCDataConverter.class)
  @JacksonXmlProperty(localName = "FromUserName")
  @JacksonXmlCData
  protected String fromUserName;

  @XStreamAlias("CreateTime")
  @JacksonXmlProperty(localName = "CreateTime")
  protected Long createTime;

  @XStreamAlias("MsgType")
  @XStreamConverter(value = XStreamCDataConverter.class)
  @JacksonXmlProperty(localName = "MsgType")
  @JacksonXmlCData
  protected String msgType;


  @XStreamAlias("Encrypt")
  @XStreamConverter(value = XStreamCDataConverter.class)
  @JacksonXmlProperty(localName = "Encrypt")
  @JacksonXmlCData
  private String encrypt;

  @XStreamAlias("MsgSignature")
  @XStreamConverter(value = XStreamCDataConverter.class)
  @JacksonXmlProperty(localName = "MsgSignature")
  @JacksonXmlCData
  private String msgSignature;

  @XStreamAlias("TimeStamp")
  @XStreamConverter(value = XStreamCDataConverter.class)
  @JacksonXmlProperty(localName = "TimeStamp")
  @JacksonXmlCData
  private String timeStamp;

  @XStreamAlias("Nonce")
  @XStreamConverter(value = XStreamCDataConverter.class)
  @JacksonXmlProperty(localName = "Nonce")
  @JacksonXmlCData
  private String nonce;

  /**
   * 获得文本消息builder
   *
   * @return 文本消息构建器
   */
  public static TextBuilder TEXT() {
    return new TextBuilder();
  }

  /**
   * 获得图片消息builder
   *
   * @return 图片消息构建器
   */
  public static ImageBuilder IMAGE() {
    return new ImageBuilder();
  }

  /**
   * 获得语音消息builder
   *
   * @return 语音消息构建器
   */
  public static VoiceBuilder VOICE() {
    return new VoiceBuilder();
  }

  /**
   * 获得视频消息builder
   *
   * @return 视频消息构建器
   */
  public static VideoBuilder VIDEO() {
    return new VideoBuilder();
  }

  /**
   * 获得音乐消息builder
   *
   * @return 音乐消息构建器
   */
  public static MusicBuilder MUSIC() {
    return new MusicBuilder();
  }

  /**
   * 获得图文消息builder
   *
   * @return 图文消息构建器
   */
  public static NewsBuilder NEWS() {
    return new NewsBuilder();
  }

  /**
   * 获得客服消息builder
   *
   * @return 客服消息构建器
   */
  public static TransferCustomerServiceBuilder TRANSFER_CUSTOMER_SERVICE() {
    return new TransferCustomerServiceBuilder();
  }

  /**
   * 获得设备消息builder
   *
   * @return 设备消息builder
   */
  public static DeviceBuilder DEVICE() {
      return new DeviceBuilder();
  }

  /**
   * 转换成xml格式
   *
   * @return xml格式字符串
   */
  @SuppressWarnings("unchecked")
  public String toXml() {
    return XStreamTransformer.toXml((Class<WxMpXmlOutMessage>) this.getClass(), this);
  }

  /**
   * 转换成加密的结果
   *
   * @param wxMpConfigStorage 公众号配置
   * @return 加密后的消息对象
   */
  public WxMpXmlOutMessage toEncrypted(WxMpConfigStorage wxMpConfigStorage) {
    String plainXml = toXml();
    WxMpCryptUtil pc = new WxMpCryptUtil(wxMpConfigStorage);
    WxCryptUtil.EncryptContext context = pc.encryptContext(plainXml);
    WxMpXmlOutMessage res = new WxMpXmlOutMessage() {};
    res.setNonce(context.getNonce());
    res.setEncrypt(context.getEncrypt());
    res.setTimeStamp(context.getTimeStamp());
    res.setMsgSignature(context.getSignature());
    return res;
  }

  /**
   * 转换成加密的xml格式
   *
   * @param wxMpConfigStorage 公众号配置
   * @return 加密后的xml格式字符串
   */
  public String toEncryptedXml(WxMpConfigStorage wxMpConfigStorage) {
    String plainXml = toXml();
    WxMpCryptUtil pc = new WxMpCryptUtil(wxMpConfigStorage);
    return pc.encrypt(plainXml);
  }
}
