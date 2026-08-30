package com.binarywang.solon.wxjava.store.properties;

import lombok.Data;

/**
 * redis 配置
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
public class RedisProperties {

  /**
   * 主机地址,不填则从solon容器内获取JedisPool
   */
  private String host;

  /**
   * 端口号
   */
  private int port = 6379;

  /**
   * 密码
   */
  private String password;

  /**
   * 超时
   */
  private int timeout = 2000;

  /**
   * 数据库
   */
  private int database = 0;

  private Integer maxActive;
  private Integer maxIdle;
  private Integer maxWaitMillis;
  private Integer minIdle;
}
