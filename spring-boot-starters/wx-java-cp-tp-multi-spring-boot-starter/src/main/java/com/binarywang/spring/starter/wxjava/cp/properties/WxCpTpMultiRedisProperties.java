package com.binarywang.spring.starter.wxjava.cp.properties;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Redis配置.
 *
 * @author yl
 * created on 2023/10/16
 */
@Data
@NoArgsConstructor
public class WxCpTpMultiRedisProperties implements Serializable {
  private static final long serialVersionUID = -5924815351660074401L;

  /**
   * 主机地址.
   */
  private String host;

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

  private Integer maxActive;
  private Integer maxIdle;
  private Integer maxWaitMillis;
  private Integer minIdle;
}
