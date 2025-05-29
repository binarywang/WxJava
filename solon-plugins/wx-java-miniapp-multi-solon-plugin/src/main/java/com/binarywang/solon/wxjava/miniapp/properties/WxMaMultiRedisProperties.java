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
public class WxMaMultiRedisProperties implements Serializable {
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
