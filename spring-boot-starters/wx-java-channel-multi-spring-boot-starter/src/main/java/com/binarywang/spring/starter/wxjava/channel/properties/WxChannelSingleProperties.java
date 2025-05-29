package com.binarywang.spring.starter.wxjava.channel.properties;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 微信视频号相关配置属性
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 * @date 2024/9/13
 */
@Data
@NoArgsConstructor
public class WxChannelSingleProperties implements Serializable {
  private static final long serialVersionUID = 5306630351265124825L;

  /**
   * 设置微信视频号的 appid.
   */
  private String appId;

  /**
   * 设置微信视频号的 secret.
   */
  private String secret;

  /**
   * 设置微信视频号的 token.
   */
  private String token;

  /**
   * 设置微信视频号的 EncodingAESKey.
   */
  private String aesKey;

  /**
   * 是否使用稳定版 Access Token
   */
  private boolean useStableAccessToken = false;
}
