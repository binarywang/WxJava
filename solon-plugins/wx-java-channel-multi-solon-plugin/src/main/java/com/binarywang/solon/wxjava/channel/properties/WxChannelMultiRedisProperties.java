package com.binarywang.solon.wxjava.channel.properties;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Redis配置
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 * @date 2024/9/13
 */
@Data
@NoArgsConstructor
public class WxChannelMultiRedisProperties implements Serializable {
  private static final long serialVersionUID = 9061055444734277357L;

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
   * 最大活动连接数
   */
  private Integer maxActive;

  /**
   * 最大空闲连接数
   */
  private Integer maxIdle;

  /**
   * 最小空闲连接数
   */
  private Integer minIdle;

  /**
   * 最大等待时间
   */
  private Integer maxWaitMillis;
}
