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
public class WxCpTpSingleProperties implements Serializable {
  private static final long serialVersionUID = -7502823825007859418L;
  /**
   * 微信企业号 corpId
   */
  private String corpId;
  /**
   * 微信企业号 服务商 providerSecret
   */
  private String providerSecret;
  /**
   * 微信企业号应用 token
   */
  private String token;

  private String encodingAESKey;

  /**
   * 微信企业号 第三方 应用 ID
   */
  private String suiteId;
  /**
   * 微信企业号应用
   */
  private String suiteSecret;


}
