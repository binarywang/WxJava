package me.chanjar.weixin.common.util.http;

/**
 * Created by ecoolper on 2017/4/22.
 *
 * @author ecoolper
 */
public interface RequestHttp<H, P> {

  /**
   * 返回httpClient.
   *
   * @return 返回httpClient
   */
  H getRequestHttpClient();

  /**
   * 返回httpProxy.
   *
   * @return 返回httpProxy
   */
  P getRequestHttpProxy();

  /**
   * 返回HttpType.
   *
   * @return HttpType
   */
  HttpClientType getRequestType();

}
