package com.binarywang.spring.starter.wxjava.miniapp.properties;

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
   * 消息格式，XML或者JSON.
   */
  private String msgDataFormat;

  /**
   * 是否使用稳定版 Access Token
   */
  private boolean useStableAccessToken = false;

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
