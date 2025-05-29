package com.binarywang.spring.starter.wxjava.channel.enums;

/**
 * httpclient类型
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 * @date 2024/9/13
 */
public enum HttpClientType {
  /**
   * HttpClient
   */
  HTTP_CLIENT,
  // WxChannelServiceOkHttpImpl 实现经测试无法正常完成业务固暂不支持OK_HTTP方式
//  /**
//   * OkHttp.
//   */
//  OK_HTTP,
}
