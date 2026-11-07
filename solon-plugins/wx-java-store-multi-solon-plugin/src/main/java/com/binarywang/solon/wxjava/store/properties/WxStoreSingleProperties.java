package com.binarywang.solon.wxjava.store.properties;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 微信小店相关配置属性
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 * @date 2024/9/13
 */
@Data
@NoArgsConstructor
public class WxStoreSingleProperties implements Serializable {
  private static final long serialVersionUID = 5306630351265124825L;

  /**
   * 设置微信小店的 appid.
   */
  private String appId;

  /**
   * 设置微信小店的 secret.
   */
  private String secret;

  /**
   * 设置微信小店的 token.
   */
  private String token;

  /**
   * 设置微信小店的 EncodingAESKey.
   */
  private String aesKey;

  /**
   * 是否使用稳定版 Access Token
   */
  private boolean useStableAccessToken = false;
}
