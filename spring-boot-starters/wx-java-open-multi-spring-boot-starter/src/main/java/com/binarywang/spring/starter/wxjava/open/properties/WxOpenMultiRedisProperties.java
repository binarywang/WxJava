package com.binarywang.spring.starter.wxjava.open.properties;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 微信开放平台多账号Redis配置.
 *
 * @author Binary Wang
 */
@Data
@NoArgsConstructor
public class WxOpenMultiRedisProperties implements Serializable {
  private static final long serialVersionUID = -5924815351660074401L;

  /**
   * 主机地址.
   */
  private String host = "127.0.0.1";

  /**
   * 端口号.
   */
  private int port = 6379;

  /**
   * 密码.
   */
  private String password;

  /**
   * 超时.
   */
  private int timeout = 2000;

  /**
   * 数据库.
   */
  private int database = 0;

  /**
   * sentinel ips
   */
  private String sentinelIps;

  /**
   * sentinel name
   */
  private String sentinelName;

  private Integer maxActive;
  private Integer maxIdle;
  private Integer maxWaitMillis;
  private Integer minIdle;
}
