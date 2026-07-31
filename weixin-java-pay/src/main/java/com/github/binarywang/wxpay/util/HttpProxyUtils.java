package com.github.binarywang.wxpay.util;

import com.github.binarywang.wxpay.config.WxPayHttpProxy;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * 微信支付 HTTP Proxy 工具类
 *
 * @author Long Yu
 * created on  2021-12-28 15:58:03
 */
public class HttpProxyUtils {

  /**
   * 配置 http 正向代理 可以实现内网服务通过代理调用接口
   * 参考代码: AbstractWxPayApacheHttpServiceImpl 中的方法 createHttpClientBuilder
   *
   * @param wxPayHttpProxy 代理配置
   * @param httpClientBuilder http构造参数
   */
  public static void initHttpProxy(HttpClientBuilder httpClientBuilder, WxPayHttpProxy wxPayHttpProxy) {
    if(wxPayHttpProxy == null){
      return;
    }
    if (StringUtils.isNotBlank(wxPayHttpProxy.getHttpProxyHost()) && wxPayHttpProxy.getHttpProxyPort() > 0) {
      if (StringUtils.isEmpty(wxPayHttpProxy.getHttpProxyUsername())) {
        wxPayHttpProxy.setHttpProxyUsername("whatever");
      }

      // 使用代理服务器 需要用户认证的代理服务器
      CredentialsProvider provider = new BasicCredentialsProvider();
      provider.setCredentials(new AuthScope(wxPayHttpProxy.getHttpProxyHost(), wxPayHttpProxy.getHttpProxyPort()),
        new UsernamePasswordCredentials(wxPayHttpProxy.getHttpProxyUsername(), wxPayHttpProxy.getHttpProxyPassword()));
      httpClientBuilder.setDefaultCredentialsProvider(provider);
      httpClientBuilder.setProxy(new HttpHost(wxPayHttpProxy.getHttpProxyHost(), wxPayHttpProxy.getHttpProxyPort()));
    }
  }





}
