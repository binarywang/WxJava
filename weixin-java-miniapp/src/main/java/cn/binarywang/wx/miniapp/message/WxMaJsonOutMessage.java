package cn.binarywang.wx.miniapp.message;

import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import cn.binarywang.wx.miniapp.util.crypt.WxMaCryptUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 微信小程序输出给微信服务器的JSON格式消息.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WxMaJsonOutMessage implements WxMaOutMessage {
  private static final long serialVersionUID = 4241135225946919154L;

  protected String toUserName;
  protected String fromUserName;
  protected Long createTime;
  protected String msgType;

  /**
   * 转换成JSON格式.
   */
  @Override
  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }

  /**
   * 转换成XML格式（对于JSON消息类型，返回JSON格式）.
   */
  @Override
  public String toXml() {
    // JSON消息类型默认返回JSON格式
    return toJson();
  }

  /**
   * 转换成加密的JSON格式.
   */
  @Override
  public String toEncryptedJson(WxMaConfig config) {
    String plainJson = toJson();
    WxMaCryptUtils pc = new WxMaCryptUtils(config);
    return pc.encrypt(plainJson);
  }

  /**
   * 转换成加密的XML格式（对于JSON消息类型，返回加密的JSON格式）.
   */
  @Override
  public String toEncryptedXml(WxMaConfig config) {
    // JSON消息类型默认返回加密的JSON格式
    return toEncryptedJson(config);
  }
}