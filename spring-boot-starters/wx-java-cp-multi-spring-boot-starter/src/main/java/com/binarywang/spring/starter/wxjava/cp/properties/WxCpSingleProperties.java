package com.binarywang.spring.starter.wxjava.cp.properties;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 企业微信企业相关配置属性
 *
 * @author yl
 * created on 2023/10/16
 */
@Data
@NoArgsConstructor
public class WxCpSingleProperties implements Serializable {
  private static final long serialVersionUID = -7502823825007859418L;
  /**
   * 微信企业号 corpId
   */
  private String corpId;
  /**
   * 微信企业号 corpSecret
   */
  private String corpSecret;
  /**
   * 微信企业号应用 token
   */
  private String token;
  /**
   * 微信企业号应用 ID
   */
  private Integer agentId;
  /**
   * 微信企业号应用 EncodingAESKey
   */
  private String aesKey;
  /**
   * 微信企业号应用 会话存档私钥
   */
  private String msgAuditPriKey;
  /**
   * 微信企业号应用 会话存档类库路径
   */
  private String msgAuditLibPath;

  /**
   * 自定义企业微信服务器baseUrl，用于替换默认的 https://qyapi.weixin.qq.com
   * 例如：http://proxy.company.com:8080
   */
  private String baseApiUrl;
}
