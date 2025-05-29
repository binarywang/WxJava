package com.binarywang.solon.wxjava.miniapp.properties;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author monch
 * created on 2024/9/6
 */
@Data
@NoArgsConstructor
public class WxMaSingleProperties implements Serializable {
  private static final long serialVersionUID = 1980986361098922525L;
  /**
   * 设置微信公众号的 appid.
   */
  private String appId;

  /**
   * 设置微信公众号的 app secret.
   */
  private String appSecret;

  /**
   * 设置微信公众号的 token.
   */
  private String token;

  /**
   * 设置微信公众号的 EncodingAESKey.
   */
  private String aesKey;

  /**
   * 是否使用稳定版 Access Token
   */
  private boolean useStableAccessToken = false;
}
