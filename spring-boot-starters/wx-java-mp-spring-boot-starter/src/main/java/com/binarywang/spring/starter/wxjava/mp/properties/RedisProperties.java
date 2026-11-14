package com.binarywang.spring.starter.wxjava.mp.properties;

import lombok.Data;

import java.io.Serializable;

/**
 * redis 配置属性.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on  2020-08-30
 */
@Data
public class RedisProperties implements Serializable {
  private static final long serialVersionUID = -5924815351660074401L;

  /**
   * 主机地址.不填则从spring容器内获取JedisPool或RedissonClient
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
