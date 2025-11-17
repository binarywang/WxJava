package cn.binarywang.wx.miniapp.message;

import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.util.crypt.WxMaCryptUtils;
import cn.binarywang.wx.miniapp.util.xml.XStreamTransformer;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;

import java.io.Serializable;

/**
 * 微信小程序输出给微信服务器的消息.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on  2019-06-22
 */
@Data
@XStreamAlias("xml")
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WxMaXmlOutMessage implements WxMaOutMessage {
  private static final long serialVersionUID = 4241135225946919153L;

  @XStreamAlias("ToUserName")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String toUserName;

  @XStreamAlias("FromUserName")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String fromUserName;

  @XStreamAlias("CreateTime")
  protected Long createTime;

  @XStreamAlias("MsgType")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String msgType;

  @SuppressWarnings("unchecked")
  @Override
  public String toXml() {
    return XStreamTransformer.toXml((Class<WxMaXmlOutMessage>) this.getClass(), this);
  }

  /**
   * 转换成JSON格式（对于XML消息类型，返回XML格式）.
   */
  @Override
  public String toJson() {
    // XML消息类型默认返回XML格式
    return toXml();
  }

  /**
   * 转换成加密的xml格式.
   */
  @Override
  public String toEncryptedXml(WxMaConfig config) {
    String plainXml = toXml();
    WxMaCryptUtils pc = new WxMaCryptUtils(config);
    return pc.encrypt(plainXml);
  }

  /**
   * 转换成加密的JSON格式（对于XML消息类型，返回加密的XML格式）.
   */
  @Override
  public String toEncryptedJson(WxMaConfig config) {
    // XML消息类型默认返回加密的XML格式
    return toEncryptedXml(config);
  }
}
