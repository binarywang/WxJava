package me.chanjar.weixin.qidian.api;

import java.util.Map;

import com.google.gson.JsonObject;

import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.bean.WxNetCheckResult;
import me.chanjar.weixin.common.enums.TicketType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.service.WxService;
import me.chanjar.weixin.common.util.http.MediaUploadRequestExecutor;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.qidian.config.WxQidianConfigStorage;
import me.chanjar.weixin.qidian.enums.WxQidianApiUrl;

/**
 * 腾讯企点API的Service.
 *
 * @author alegria
 */
public interface WxQidianService extends WxService {
  /**
   * <pre>
   * 验证消息的确来自微信服务器.
   * 详情请见: http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421135319&token=&lang=zh_CN
   * </pre>
   *
   * @param timestamp 时间戳
   * @param nonce     随机串
   * @param signature 签名
   * @return 是否验证通过 boolean
   */
  boolean checkSignature(String timestamp, String nonce, String signature);

  /**
   * 获取access_token, 不强制刷新access_token.
   *
   * @return token access token
   * @throws WxErrorException .
   * @see #getAccessToken(boolean) #getAccessToken(boolean)
   */
  String getAccessToken() throws WxErrorException;

  /**
   * <pre>
   * 获取access_token，本方法线程安全.
   * 且在多线程同时刷新时只刷新一次，避免超出2000次/日的调用次数上限
   *
   * 另：本service的所有方法都会在access_token过期时调用此方法
   *
   * 程序员在非必要情况下尽量不要主动调用此方法
   *
   * 详情请见: http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140183&token=&lang=zh_CN
   * </pre>
   *
   * @param forceRefresh 是否强制刷新
   * @return token access token
   * @throws WxErrorException .
   */
  String getAccessToken(boolean forceRefresh) throws WxErrorException;

  /**
   * 获得ticket,不强制刷新ticket.
   *
   * @param type ticket 类型
   * @return ticket ticket
   * @throws WxErrorException .
   * @see #getTicket(TicketType, boolean) #getTicket(TicketType, boolean)
   */
  String getTicket(TicketType type) throws WxErrorException;

  /**
   * <pre>
   * 获得ticket.
   * 获得时会检查 Token是否过期，如果过期了，那么就刷新一下，否则就什么都不干
   * </pre>
   *
   * @param type         ticket类型
   * @param forceRefresh 强制刷新
   * @return ticket ticket
   * @throws WxErrorException .
   */
  String getTicket(TicketType type, boolean forceRefresh) throws WxErrorException;

  /**
   * 获得jsapi_ticket,不强制刷新jsapi_ticket.
   *
   * @return jsapi ticket
   * @throws WxErrorException .
   * @see #getJsapiTicket(boolean) #getJsapiTicket(boolean)
   */
  String getJsapiTicket() throws WxErrorException;

  /**
   * <pre>
   * 获得jsapi_ticket.
   * 获得时会检查jsapiToken是否过期，如果过期了，那么就刷新一下，否则就什么都不干
   *
   * 详情请见：http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141115&token=&lang=zh_CN
   * </pre>
   *
   * @param forceRefresh 强制刷新
   * @return jsapi ticket
   * @throws WxErrorException .
   */
  String getJsapiTicket(boolean forceRefresh) throws WxErrorException;

  /**
   * <pre>
   * 创建调用jsapi时所需要的签名.
   *
   * 详情请见：http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141115&token=&lang=zh_CN
   * </pre>
   *
   * @param url 地址
   * @return 生成的签名对象 wx jsapi signature
   * @throws WxErrorException .
   */
  WxJsapiSignature createJsapiSignature(String url) throws WxErrorException;

  /**
   * <pre>
   * 长链接转短链接接口.
   * 详情请见: http://mp.weixin.qq.com/wiki/index.php?title=长链接转短链接接口
   * </pre>
   *
   * @param longUrl 长url
   * @return 生成的短地址 string
   * @throws WxErrorException .
   */
  String shortUrl(String longUrl) throws WxErrorException;

  /**
   * <pre>
   * 构造第三方使用网站应用授权登录的url.
   * 详情请见: <a href=
  "https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1419316505&token=&lang=zh_CN">网站应用微信登录开发指南</a>
   * URL格式为：https://open.weixin.qq.com/connect/qrconnect?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect
   * </pre>
   *
   * @param redirectUri 用户授权完成后的重定向链接，无需urlencode, 方法内会进行encode
   * @param scope       应用授权作用域，拥有多个作用域用逗号（,）分隔，网页应用目前仅填写snsapi_login即可
   * @param state       非必填，用于保持请求和回调的状态，授权请求后原样带回给第三方。该参数可用于防止csrf攻击（跨站请求伪造攻击），建议第三方带上该参数，可设置为简单的随机数加session进行校验
   * @return url string
   */
  String buildQrConnectUrl(String redirectUri, String scope, String state);

  /**
   * <pre>
   * 获取微信服务器IP地址
   * http://mp.weixin.qq.com/wiki/0/2ad4b6bfd29f30f71d39616c2a0fcedc.html
   * </pre>
   *
   * @return 微信服务器ip地址数组 string [ ]
   * @throws WxErrorException .
   */
  String[] getCallbackIP() throws WxErrorException;

  /**
   * <pre>
   *  网络检测
   *  https://mp.weixin.qq.com/wiki?t=resource/res_main&id=21541575776DtsuT
   *  为了帮助开发者排查回调连接失败的问题，提供这个网络检测的API。它可以对开发者URL做域名解析，然后对所有IP进行一次ping操作，得到丢包率和耗时。
   * </pre>
   *
   * @param action   执行的检测动作
   * @param operator 指定平台从某个运营商进行检测
   * @return 检测结果 wx net check result
   * @throws WxErrorException .
   */
  WxNetCheckResult netCheck(String action, String operator) throws WxErrorException;

  /**
   * <pre>
   *  公众号调用或第三方平台帮公众号调用对公众号的所有api调用（包括第三方帮其调用）次数进行清零：
   *  HTTP调用：https://api.weixin.qq.com/cgi-bin/clear_quota?access_token=ACCESS_TOKEN
   *  接口文档地址：https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433744592
   *
   * </pre>
   *
   * @param appid 公众号的APPID
   * @throws WxErrorException the wx error exception
   */
  void clearQuota(String appid) throws WxErrorException;

  /**
   * <pre>
   * Service没有实现某个API的时候，可以用这个，
   * 比{@link #get}和{@link #post}方法更灵活，可以自己构造RequestExecutor用来处理不同的参数和不同的返回类型。
   * 可以参考，{@link MediaUploadRequestExecutor}的实现方法
   * </pre>
   *
   * @param <T>      the type parameter
   * @param <E>      the type parameter
   * @param executor 执行器
   * @param url      接口地址
   * @param data     参数数据
   * @return 结果 t
   * @throws WxErrorException 异常
   */
  <T, E> T execute(RequestExecutor<T, E> executor, String url, E data) throws WxErrorException;

  /**
   * 当本Service没有实现某个API的时候，可以用这个，针对所有微信API中的GET请求.
   *
   * @param url        请求接口地址
   * @param queryParam 参数
   * @return 接口响应字符串 string
   * @throws WxErrorException 异常
   */
  String get(WxQidianApiUrl url, String queryParam) throws WxErrorException;

  /**
   * 当本Service没有实现某个API的时候，可以用这个，针对所有微信API中的POST请求.
   *
   * @param url      请求接口地址
   * @param postData 请求参数json值
   * @return 接口响应字符串 string
   * @throws WxErrorException 异常
   */
  String post(WxQidianApiUrl url, String postData) throws WxErrorException;

  /**
   * 当本Service没有实现某个API的时候，可以用这个，针对所有微信API中的POST请求.
   *
   * @param url        请求接口地址
   * @param jsonObject 请求参数json对象
   * @return 接口响应字符串 string
   * @throws WxErrorException 异常
   */
  String post(WxQidianApiUrl url, JsonObject jsonObject) throws WxErrorException;

  /**
   * <pre>
   * Service没有实现某个API的时候，可以用这个，
   * 比{@link #get}和{@link #post}方法更灵活，可以自己构造RequestExecutor用来处理不同的参数和不同的返回类型。
   * 可以参考，{@link MediaUploadRequestExecutor}的实现方法
   * </pre>
   *
   * @param <T>      the type parameter
   * @param <E>      the type parameter
   * @param executor 执行器
   * @param url      接口地址
   * @param data     参数数据
   * @return 结果 t
   * @throws WxErrorException 异常
   */
  <T, E> T execute(RequestExecutor<T, E> executor, WxQidianApiUrl url, E data) throws WxErrorException;

  /**
   * 设置当微信系统响应系统繁忙时，要等待多少 retrySleepMillis(ms) * 2^(重试次数 - 1) 再发起重试.
   *
   * @param retrySleepMillis 默认：1000ms
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
   * 获取WxMpConfigStorage 对象.
   *
   * @return WxMpConfigStorage wx mp config storage
   */
  WxQidianConfigStorage getWxMpConfigStorage();

  /**
   * 设置 {@link WxQidianConfigStorage} 的实现. 兼容老版本
   *
   * @param wxConfigProvider .
   */
  void setWxMpConfigStorage(WxQidianConfigStorage wxConfigProvider);

  /**
   * Map里 加入新的 {@link WxQidianConfigStorage}，适用于动态添加新的微信公众号配置.
   *
   * @param mpId          公众号id
   * @param configStorage 新的微信配置
   */
  void addConfigStorage(String mpId, WxQidianConfigStorage configStorage);

  /**
   * 从 Map中 移除 {@link String mpId} 所对应的
   * {@link WxQidianConfigStorage}，适用于动态移除微信公众号配置.
   *
   * @param mpId 对应公众号的标识
   */
  void removeConfigStorage(String mpId);

  /**
   * 注入多个 {@link WxQidianConfigStorage} 的实现. 并为每个 {@link WxQidianConfigStorage}
   * 赋予不同的 {@link String mpId} 值 随机采用一个{@link String mpId}进行Http初始化操作
   *
   * @param configStorages WxMpConfigStorage map
   */
  void setMultiConfigStorages(Map<String, WxQidianConfigStorage> configStorages);

  /**
   * 注入多个 {@link WxQidianConfigStorage} 的实现. 并为每个 {@link WxQidianConfigStorage}
   * 赋予不同的 {@link String label} 值
   *
   * @param configStorages WxMpConfigStorage map
   * @param defaultMpId    设置一个{@link WxQidianConfigStorage} 所对应的{@link String
   *                       mpId}进行Http初始化
   */
  void setMultiConfigStorages(Map<String, WxQidianConfigStorage> configStorages, String defaultMpId);

  /**
   * 进行相应的公众号切换.
   *
   * @param mpId 公众号标识
   * @return 切换是否成功 boolean
   */
  boolean switchover(String mpId);

  /**
   * 进行相应的公众号切换.
   *
   * @param mpId 公众号标识
   * @return 切换成功 ，则返回当前对象，方便链式调用，否则抛出异常
   */
  WxQidianService switchoverTo(String mpId);

  /**
   * 初始化http请求对象.
   */
  void initHttp();

  /**
   * 获取RequestHttp对象.
   *
   * @return RequestHttp对象 request http
   */
  RequestHttp<?, ?> getRequestHttp();

  WxQidianDialService getDialService();

  WxQidianCallDataService getCallDataService();
}
