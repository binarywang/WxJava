package com.binarywang.spring.starter.wxjava.open.properties;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 微信开放平台单个应用配置.
 *
 * @author Binary Wang
 */
@Data
@NoArgsConstructor
public class WxOpenSingleProperties implements Serializable {
  private static final long serialVersionUID = 1980986361098922525L;

  /**
   * 设置微信开放平台的appid.
   */
  private String appId;

  /**
   * 设置微信开放平台的app secret.
   */
  private String secret;

  /**
   * 设置微信开放平台的token.
   */
  private String token;

  /**
   * 设置微信开放平台的EncodingAESKey.
   */
  private String aesKey;

  /**
   * 自定义API主机地址，用于替换默认的 https://api.weixin.qq.com
   * 例如：http://proxy.company.com:8080
   */
  private String apiHostUrl;

  /**
   * 自定义获取AccessToken地址，用于向自定义统一服务获取AccessToken
   * 例如：http://proxy.company.com:8080/oauth/token
   */
  private String accessTokenUrl;
}
