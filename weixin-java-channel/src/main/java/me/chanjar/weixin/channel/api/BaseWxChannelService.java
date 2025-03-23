package me.chanjar.weixin.channel.api;

import me.chanjar.weixin.channel.config.WxChannelConfig;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.service.WxService;
import me.chanjar.weixin.common.util.http.MediaUploadRequestExecutor;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;

/**
 * The interface Wx Channel service
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
public interface BaseWxChannelService extends WxService {

  /**
   * <pre>
   * 验证消息的确来自微信服务器.
   * 详情请见: http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421135319&token=&lang=zh_CN
   * </pre>
   *
   * @param timestamp the timestamp
   * @param nonce     the nonce
   * @param signature the signature
   * @return the boolean
   */
  boolean checkSignature(String timestamp, String nonce, String signature);

  /**
   * 获取access_token, 不强制刷新access_token.
   *
   * @return the access token
   *
   * @throws WxErrorException the wx error exception
   * @see #getAccessToken(boolean) #getAccessToken(boolean)
   */
  String getAccessToken() throws WxErrorException;

  /**
   * <pre>
   * 获取access_token，本方法线程安全.
   * 且在多线程同时刷新时只刷新一次，避免超出2000次/日的调用次数上限
   * 使用【稳定版接口】获取access_token时，限制【20次/日】，连续使用该模式时，请保证调用时间隔至少为30s，否则不会刷新
   *
   * 程序员在非必要情况下尽量不要主动调用此方法
   *
   * 详情请见: http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140183&token=&lang=zh_CN
   * </pre>
   *
   * @param forceRefresh 强制刷新
   * @return the access token
   *
   * @throws WxErrorException the wx error exception
   */
  String getAccessToken(boolean forceRefresh) throws WxErrorException;

  /**
   * <pre>
   * Service没有实现某个API的时候，可以用这个，
   * 比{@link #get}和{@link #post}方法更灵活，可以自己构造RequestExecutor用来处理不同的参数和不同的返回类型。
   * 可以参考，{@link MediaUploadRequestExecutor}的实现方法
   * </pre>
   *
   * @param <T>      .
   * @param <E>      .
   * @param executor 执行器
   * @param uri      接口请求地址
   * @param data     参数或请求数据
   * @return . t
   *
   * @throws WxErrorException the wx error exception
   */
  <T, E> T execute(RequestExecutor<T, E> executor, String uri, E data) throws WxErrorException;

  /**
   * 执行器
   *
   * @param <T>      .
   * @param <E>      .
   * @param executor 执行器
   * @param uri      接口请求地址
   * @param data     参数或请求数据
   * @return T
   *
   * @throws WxErrorException the wx error exception
   */
  <T, E> T executeWithoutLog(RequestExecutor<T, E> executor, String uri, E data) throws WxErrorException;

  /**
   * <pre>
   * 设置当微信系统响应系统繁忙时，要等待多少 retrySleepMillis(ms) * 2^(重试次数 - 1) 再发起重试.
   * 默认：1000ms
   * </pre>
   *
   * @param retrySleepMillis 重试等待毫秒数
   */
  void setRetrySleepMillis(int retrySleepMillis);

  /**
   * <pre>
   * 设置当微信系统响应系统繁忙时，最大重试次数.
   * 默认：5次
   * </pre>
   *
   * @param maxRetryTimes 最大重试次数
   */
  void setMaxRetryTimes(int maxRetryTimes);

  /**
   * WxChannelConfig对象
   *
   * @return WxMaConfig wx channel config
   */
  WxChannelConfig getConfig();

  /**
   * 注入 {@link WxChannelConfig} 的实现.
   *
   * @param config config
   */
  void setConfig(WxChannelConfig config);

  /**
   * 初始化http请求对象.
   */
  void initHttp();

  /**
   * 请求http请求相关信息.
   *
   * @return . request http
   */
  RequestHttp getRequestHttp();
}
