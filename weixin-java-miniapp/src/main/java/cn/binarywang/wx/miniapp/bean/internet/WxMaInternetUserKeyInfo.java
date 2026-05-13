package cn.binarywang.wx.miniapp.bean.internet;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 *
 *
 * <pre>
 * 【小程序-服务端-网络】网络相关接口.
 *  文档地址：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/internet/internet.getUserEncryptKey.html
 *  微信返回报文：
 *  {
 *  "encrypt_key":"VI6BpyrK9XH4i4AIGe86tg==",
 *  "version":10,
 *  "expire_in":3597,
 *  "iv":"6003f73ec441c386",
 *  "create_time":1616572301
 *  }
 * </pre>
 *
 * @author <a href="https://github.com/chutian0124">chutian0124</a>
 */
@Data
public class WxMaInternetUserKeyInfo implements Serializable {
  private static final long serialVersionUID = 117922490907396705L;
  /**
   * 加密key
   */
  @SerializedName("encrypt_key")
  private String encryptKey;

  /**
   * key的版本号
   */
  private Integer version;

  /**
   * 剩余有效时间
   */
  @SerializedName("expire_in")
  private Long expireIn;

  /**
   * 加密iv（Hex 编码，通常为 32 位十六进制字符，解码后为 16 字节，用于 AES-128-CBC）
   */
  private String iv;

  /**
   * 创建key的时间戳
   */
  @SerializedName("create_time")
  private Long createTime;
}
