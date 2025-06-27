package me.chanjar.weixin.common.util.http;

/**
 * Created by ecoolper on 2017/4/28.
 */
public enum HttpClientType {
  /**
   * jodd-http.
   */
  JODD_HTTP,
  /**
   * apache httpclient 4.x.
   */
  APACHE_HTTP,
  /**
   * okhttp.
   */
  OK_HTTP,
  /**
   * apache httpclient 5.x.
   */
  HTTP_COMPONENTS
}
