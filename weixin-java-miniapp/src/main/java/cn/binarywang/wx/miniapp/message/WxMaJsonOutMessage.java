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
public class WxMaJsonOutMessage implements Serializable {
  private static final long serialVersionUID = 4241135225946919154L;

  protected String toUserName;
  protected String fromUserName;
  protected Long createTime;
  protected String msgType;

  /**
   * 转换成JSON格式.
   */
  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }

  /**
   * 转换成加密的JSON格式.
   */
  public String toEncryptedJson(WxMaConfig config) {
    String plainJson = toJson();
    WxMaCryptUtils pc = new WxMaCryptUtils(config);
    return pc.encrypt(plainJson);
  }
}