package me.chanjar.weixin.mp.api;

import com.google.gson.JsonObject;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.bean.WxNetCheckResult;
import me.chanjar.weixin.common.enums.TicketType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.service.WxImgProcService;
import me.chanjar.weixin.common.service.WxOAuth2Service;
import me.chanjar.weixin.common.service.WxOcrService;
import me.chanjar.weixin.common.service.WxService;
import me.chanjar.weixin.common.util.http.MediaUploadRequestExecutor;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.mp.bean.WxMpSemanticQuery;
import me.chanjar.weixin.mp.bean.result.WxMpCurrentAutoReplyInfo;
import me.chanjar.weixin.mp.bean.result.WxMpSemanticQueryResult;
import me.chanjar.weixin.mp.bean.result.WxMpShortKeyResult;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.enums.WxMpApiUrl;

import java.util.Map;
import java.util.function.Function;

/**
 * 微信公众号API的Service.
 *
 * @author chanjarster
 */
public interface WxMpService extends WxService {
  /**
   * <pre>
   * 短key托管 类似于短链API.
   * 详情请见: https://developers.weixin.qq.com/doc/offiaccount/Account_Management/KEY_Shortener.html
   * </pre>
   *
   * @param longData      需要转换的长信息，不超过4KB
   * @param expireSeconds 短key有效期(单位秒)，最大值为2592000（即30天），默认为2592000(30天)
   * @return shortKey 短key，15字节，base62编码(0-9/a-z/A-Z)
   * @throws WxErrorException 微信API调用异常
   */
  String genShorten(String longData, Integer expireSeconds) throws WxErrorException;

  /**
   * <pre>
   * 短key解析 将短key还原为长信息。
   * 详情请见: https://developers.weixin.qq.com/doc/offiaccount/Account_Management/KEY_Shortener.html
   * </pre>
   *
   * @param shortKey 短key，15字节，base62编码(0-9/a-z/A-Z)
   * @return WxMpShortKeyResult 解析结果，包含原始长信息
   * @throws WxErrorException 微信API调用异常
   */
  WxMpShortKeyResult fetchShorten(String shortKey) throws WxErrorException;

  /**
   * <pre>
   * 验证消息的确来自微信服务器.
   * 详情请见: http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421135319&token=&lang=zh_CN
   * </pre>
   *
   * @param timestamp 时间戳，字符串格式
   * @param nonce     随机串，字符串格式
   * @param signature 签名，字符串格式
   * @return 是否验证通过，true表示验证通过，false表示验证失败
   */
  boolean checkSignature(String timestamp, String nonce, String signature);

  /**
   * 获取access_token, 不强制刷新access_token.
   *
   * @return token access token，字符串格式
   * @throws WxErrorException 微信API调用异常
   * @see #getAccessToken(boolean) 获取access_token，可选择是否强制刷新
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
   * @param forceRefresh 是否强制刷新，true表示强制刷新，false表示使用缓存
   * @return token access token，字符串格式
   * @throws WxErrorException 微信API调用异常
   */
  String getAccessToken(boolean forceRefresh) throws WxErrorException;

  /**
   * 获得ticket,不强制刷新ticket.
   *
   * @param ticketType ticket 类型，通过TicketType枚举指定
   * @return ticket ticket，字符串格式
   * @throws WxErrorException 微信API调用异常
   * @see #getTicket(TicketType, boolean) 获得ticket，可选择是否强制刷新
   */
  String getTicket(TicketType ticketType) throws WxErrorException;

  /**
   * <pre>
   * 获得ticket.
   * 获得时会检查 Token是否过期，如果过期了，那么就刷新一下，否则就什么都不干
   * </pre>
   *
   * @param ticketType   ticket类型，通过TicketType枚举指定
   * @param forceRefresh 强制刷新，true表示强制刷新，false表示使用缓存
   * @return ticket ticket，字符串格式
   * @throws WxErrorException 微信API调用异常
   */
  String getTicket(TicketType ticketType, boolean forceRefresh) throws WxErrorException;

  /**
   * 获得jsapi_ticket,不强制刷新jsapi_ticket.
   *
   * @return jsapi ticket，字符串格式
   * @throws WxErrorException 微信API调用异常
   * @see #getJsapiTicket(boolean) 获得jsapi_ticket，可选择是否强制刷新
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
   * @param forceRefresh 强制刷新，true表示强制刷新，false表示使用缓存
   * @return jsapi ticket，字符串格式
   * @throws WxErrorException 微信API调用异常
   */
  String getJsapiTicket(boolean forceRefresh) throws WxErrorException;

  /**
   * <pre>
   * 创建调用jsapi时所需要的签名.
   *
   * 详情请见：http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141115&token=&lang=zh_CN
   * </pre>
   *
   * @param url 当前网页的URL，不包括#及其后面部分
   * @return 生成的签名对象，包含签名、时间戳、随机串等信息
   * @throws WxErrorException 微信API调用异常
   */
  WxJsapiSignature createJsapiSignature(String url) throws WxErrorException;

  /**
   * <pre>
   * 长链接转短链接接口.
   * 详情请见: http://mp.weixin.qq.com/wiki/index.php?title=长链接转短链接接口
   * </pre>
   *
   * @param longUrl 长url，需要转换的原始URL
   * @return 生成的短地址，字符串格式
   * @throws WxErrorException 微信API调用异常
   * @deprecated 请使用 {@link #genShorten(String, Integer)} 方法
   */
  @Deprecated
  String shortUrl(String longUrl) throws WxErrorException;

  /**
   * <pre>
   * 语义查询接口.
   * 详情请见：http://mp.weixin.qq.com/wiki/index.php?title=语义理解
   * </pre>
   *
   * @param semanticQuery 查询条件，包含查询内容、类型等信息
   * @return 查询结果，包含语义理解的结果和建议回复
   * @throws WxErrorException 微信API调用异常
   */
  WxMpSemanticQueryResult semanticQuery(WxMpSemanticQuery semanticQuery) throws WxErrorException;

  /**
   * <pre>
   * 构造第三方使用网站应用授权登录的url.
   * 详情请见: <a href="https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1419316505&token=&lang=zh_CN">网站应用微信登录开发指南</a>
   * URL格式为：https://open.weixin.qq.com/connect/qrconnect?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect
   * </pre>
   *
   * @param redirectUri 用户授权完成后的重定向链接，无需urlencode, 方法内会进行encode
   * @param scope       应用授权作用域，拥有多个作用域用逗号（,）分隔，网页应用目前仅填写snsapi_login即可
   * @param state       非必填，用于保持请求和回调的状态，授权请求后原样带回给第三方。该参数可用于防止csrf攻击（跨站请求伪造攻击），建议第三方带上该参数，可设置为简单的随机数加session进行校验
   * @return url 构造好的授权登录URL，字符串格式
   */
  String buildQrConnectUrl(String redirectUri, String scope, String state);

  /**
   * <pre>
   * 获取微信服务器IP地址
   * http://mp.weixin.qq.com/wiki/0/2ad4b6bfd29f30f71d39616c2a0fcedc.html
   * </pre>
   *
   * @return 微信服务器ip地址数组，包含所有微信服务器IP地址
   * @throws WxErrorException 微信API调用异常
   */
  String[] getCallbackIP() throws WxErrorException;

  /**
   * <pre>
   *  网络检测
   *  https://mp.weixin.qq.com/wiki?t=resource/res_main&id=21541575776DtsuT
   *  为了帮助开发者排查回调连接失败的问题，提供这个网络检测的API。它可以对开发者URL做域名解析，然后对所有IP进行一次ping操作，得到丢包率和耗时。
   * </pre>
   *
   * @param action   执行的检测动作，可选值：all（全部检测）、dns（仅域名解析）、ping（仅网络连通性检测）
   * @param operator 指定平台从某个运营商进行检测，可选值：CHINANET（中国电信）、UNICOM（中国联通）、CAP（中国联通）、CUCC（中国联通）
   * @return 检测结果，包含丢包率和耗时等信息
   * @throws WxErrorException 微信API调用异常
   */
  WxNetCheckResult netCheck(String action, String operator) throws WxErrorException;

  /**
   * <pre>
   * 获取公众号的自动回复规则.
   * https://developers.weixin.qq.com/doc/offiaccount/Message_Management/Getting_Rules_for_Auto_Replies.html
   * 开发者可以通过该接口，获取公众号当前使用的自动回复规则，包括关注后自动回复、消息自动回复（60分钟内触发一次）、关键词自动回复。
   * 请注意：
   * 1、第三方平台开发者可以通过本接口，在旗下公众号将业务授权给你后，立即通过本接口检测公众号的自动回复配置，并通过接口再次给公众号设置好自动回复规则，以提升公众号运营者的业务体验。
   * 2、本接口仅能获取公众号在公众平台官网的自动回复功能中设置的自动回复规则，若公众号自行开发实现自动回复，或通过第三方平台开发者来实现，则无法获取。
   * 3、认证/未认证的服务号/订阅号，以及接口测试号，均拥有该接口权限。
   * 4、从第三方平台的公众号登录授权机制上来说，该接口从属于消息与菜单权限集。
   * 5、本接口中返回的图片/语音/视频为临时素材（临时素材每次获取都不同，3天内有效，通过素材管理-获取临时素材接口来获取这些素材），本接口返回的图文消息为永久素材素材（通过素材管理-获取永久素材接口来获取这些素材）。
   * 接口调用请求说明
   * http请求方式: GET（请使用https协议）
   * https://api.weixin.qq.com/cgi-bin/get_current_autoreply_info?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @return 公众号的自动回复规则，包含关注后自动回复、消息自动回复、关键词自动回复等信息
   * @throws WxErrorException 微信API调用异常
   */
  WxMpCurrentAutoReplyInfo getCurrentAutoReplyInfo() throws WxErrorException;

  /**
   * <pre>
   *  公众号调用或第三方平台帮公众号调用对公众号的所有api调用（包括第三方帮其调用）次数进行清零：
   *  HTTP调用：https://api.weixin.qq.com/cgi-bin/clear_quota?access_token=ACCESS_TOKEN
   *  接口文档地址：https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433744592
   *
   * </pre>
   *
   * @param appid 公众号的APPID，需要清零调用的公众号的appid
   * @throws WxErrorException 微信API调用异常
   */
  void clearQuota(String appid) throws WxErrorException;

  /**
   * <pre>
   * Service没有实现某个API的时候，可以用这个，
   * 比{@link #get}和{@link #post}方法更灵活，可以自己构造RequestExecutor用来处理不同的参数和不同的返回类型。
   * 可以参考，{@link MediaUploadRequestExecutor}的实现方法
   * </pre>
   *
   * @param <T>      返回值类型
   * @param <E>      参数类型
   * @param executor 执行器，用于处理请求和响应
   * @param url      接口地址，字符串格式
   * @param data     参数数据，根据API不同可能是不同类型
   * @return 结果，根据API不同可能是不同类型
   * @throws WxErrorException 微信API调用异常
   */
  <T, E> T execute(RequestExecutor<T, E> executor, String url, E data) throws WxErrorException;

  /**
   * 当本Service没有实现某个API的时候，可以用这个，针对所有微信API中的GET请求.
   *
   * @param url        请求接口地址，通过WxMpApiUrl枚举指定
   * @param queryParam 参数，字符串格式，通常是URL查询参数
   * @return 接口响应字符串，JSON格式
   * @throws WxErrorException 微信API调用异常
   */
  String get(WxMpApiUrl url, String queryParam) throws WxErrorException;

  /**
   * 当本Service没有实现某个API的时候，可以用这个，针对所有微信API中的POST请求.
   *
   * @param url      请求接口地址，通过WxMpApiUrl枚举指定
   * @param postData 请求参数json值，字符串格式
   * @return 接口响应字符串，JSON格式
   * @throws WxErrorException 微信API调用异常
   */
  String post(WxMpApiUrl url, String postData) throws WxErrorException;

  /**
   * 当本Service没有实现某个API的时候，可以用这个，针对所有微信API中的POST请求.
   *
   * @param url 请求接口地址，通过WxMpApiUrl枚举指定
   * @param obj 请求参数，对象格式，会被序列化为JSON
   * @return 接口响应字符串，JSON格式
   * @throws WxErrorException 微信API调用异常
   */
  String post(WxMpApiUrl url, Object obj) throws WxErrorException;

  /**
   * 当本Service没有实现某个API的时候，可以用这个，针对所有微信API中的POST请求.
   *
   * @param url        请求接口地址，通过WxMpApiUrl枚举指定
   * @param jsonObject 请求参数json对象，JSON格式
   * @return 接口响应字符串，JSON格式
   * @throws WxErrorException 微信API调用异常
   */
  String post(WxMpApiUrl url, JsonObject jsonObject) throws WxErrorException;

  /**
   * <pre>
   * Service没有实现某个API的时候，可以用这个，
   * 比{@link #get}和{@link #post}方法更灵活，可以自己构造RequestExecutor用来处理不同的参数和不同的返回类型。
   * 可以参考，{@link MediaUploadRequestExecutor}的实现方法
   * </pre>
   *
   * @param <T>      返回值类型
   * @param <E>      参数类型
   * @param executor 执行器，用于处理请求和响应
   * @param url      接口地址，通过WxMpApiUrl枚举指定
   * @param data     参数数据，根据API不同可能是不同类型
   * @return 结果，根据API不同可能是不同类型
   * @throws WxErrorException 微信API调用异常
   */
  <T, E> T execute(RequestExecutor<T, E> executor, WxMpApiUrl url, E data) throws WxErrorException;

  /**
   * 设置当微信系统响应系统繁忙时，要等待多少 retrySleepMillis(ms) * 2^(重试次数 - 1) 再发起重试.
   *
   * @param retrySleepMillis 重试等待时间，单位毫秒，默认1000ms
   */
  void setRetrySleepMillis(int retrySleepMillis);

  /**
   * <pre>
   * 设置当微信系统响应系统繁忙时，最大重试次数.
   * 默认：5次
   * </pre>
   *
   * @param maxRetryTimes 最大重试次数，默认5次
   */
  void setMaxRetryTimes(int maxRetryTimes);

  /**
   * 获取WxMpConfigStorage 对象.
   *
   * @return WxMpConfigStorage 微信公众号配置存储对象
   */
  WxMpConfigStorage getWxMpConfigStorage();

  /**
   * 设置 {@link WxMpConfigStorage} 的实现. 兼容老版本
   *
   * @param wxConfigProvider 微信公众号配置存储对象
   */
  void setWxMpConfigStorage(WxMpConfigStorage wxConfigProvider);

  /**
   * Map里 加入新的 {@link WxMpConfigStorage}，适用于动态添加新的微信公众号配置.
   *
   * @param mpId          公众号id，用于标识不同的公众号
   * @param configStorage 新的微信配置，微信公众号配置存储对象
   */
  void addConfigStorage(String mpId, WxMpConfigStorage configStorage);

  /**
   * 从 Map中 移除 {@link String mpId} 所对应的 {@link WxMpConfigStorage}，适用于动态移除微信公众号配置.
   *
   * @param mpId 对应公众号的标识，用于标识不同的公众号
   */
  void removeConfigStorage(String mpId);

  /**
   * 注入多个 {@link WxMpConfigStorage} 的实现. 并为每个 {@link WxMpConfigStorage} 赋予不同的 {@link String mpId} 值
   * 随机采用一个{@link String mpId}进行Http初始化操作
   *
   * @param configStorages WxMpConfigStorage map，公众号id到配置存储对象的映射
   */
  void setMultiConfigStorages(Map<String, WxMpConfigStorage> configStorages);

  /**
   * 注入多个 {@link WxMpConfigStorage} 的实现. 并为每个 {@link WxMpConfigStorage} 赋予不同的 {@link String label} 值
   *
   * @param configStorages WxMpConfigStorage map，公众号id到配置存储对象的映射
   * @param defaultMpId    设置一个{@link WxMpConfigStorage} 所对应的{@link String mpId}进行Http初始化
   */
  void setMultiConfigStorages(Map<String, WxMpConfigStorage> configStorages, String defaultMpId);

  /**
   * 进行相应的公众号切换.
   *
   * @param mpId 公众号标识，用于标识不同的公众号
   * @return 切换是否成功，true表示成功，false表示失败
   */
  boolean switchover(String mpId);

  /**
   * 进行相应的公众号切换，支持自定义配置获取函数.
   *
   * @param mpId 公众号标识，用于标识不同的公众号
   * @param func 自定义配置获取函数，当配置不存在时使用
   * @return 切换是否成功，true表示成功，false表示失败
   */
  boolean switchover(String mpId, Function<String, WxMpConfigStorage> func);

  /**
   * 进行相应的公众号切换.
   *
   * @param mpId 公众号标识，用于标识不同的公众号
   * @return 切换成功 ，则返回当前对象，方便链式调用，否则抛出异常
   */
  WxMpService switchoverTo(String mpId);

  /**
   * 进行相应的公众号切换，支持自定义配置获取函数.
   *
   * @param mpId 公众号标识，用于标识不同的公众号
   * @param func 自定义配置获取函数，当配置不存在时使用
   * @return 切换成功 ，则返回当前对象，方便链式调用，否则抛出异常
   */
  WxMpService switchoverTo(String mpId, Function<String, WxMpConfigStorage> func);

  /**
   * 返回客服接口方法实现类，以方便调用其各个接口.
   *
   * @return WxMpKefuService 客服服务接口
   */
  WxMpKefuService getKefuService();

  /**
   * 返回素材相关接口方法的实现类对象，以方便调用其各个接口.
   *
   * @return WxMpMaterialService 素材服务接口
   */
  WxMpMaterialService getMaterialService();

  /**
   * 返回菜单相关接口方法的实现类对象，以方便调用其各个接口.
   *
   * @return WxMpMenuService 菜单服务接口
   */
  WxMpMenuService getMenuService();

  /**
   * 返回用户相关接口方法的实现类对象，以方便调用其各个接口.
   *
   * @return WxMpUserService 用户服务接口
   */
  WxMpUserService getUserService();

  /**
   * 返回用户标签相关接口方法的实现类对象，以方便调用其各个接口.
   *
   * @return WxMpUserTagService 用户标签服务接口
   */
  WxMpUserTagService getUserTagService();

  /**
   * 返回二维码相关接口方法的实现类对象，以方便调用其各个接口.
   *
   * @return WxMpQrcodeService 二维码服务接口
   */
  WxMpQrcodeService getQrcodeService();

  /**
   * 返回卡券相关接口方法的实现类对象，以方便调用其各个接口.
   *
   * @return WxMpCardService 卡券服务接口
   */
  WxMpCardService getCardService();

  /**
   * 返回数据分析统计相关接口方法的实现类对象，以方便调用其各个接口.
   *
   * @return WxMpDataCubeService 数据分析服务接口
   */
  WxMpDataCubeService getDataCubeService();

  /**
   * 返回用户黑名单管理相关接口方法的实现类对象，以方便调用其各个接口.
   *
   * @return WxMpUserBlacklistService 用户黑名单服务接口
   */
  WxMpUserBlacklistService getBlackListService();

  /**
   * 返回门店管理相关接口方法的实现类对象，以方便调用其各个接口.
   *
   * @return WxMpStoreService 门店服务接口
   */
  WxMpStoreService getStoreService();

  /**
   * 返回模板消息相关接口方法的实现类对象，以方便调用其各个接口.
   *
   * @return WxMpTemplateMsgService 模板消息服务接口
   */
  WxMpTemplateMsgService getTemplateMsgService();

  /**
   * 返回一次性订阅消息相关接口方法的实现类对象，以方便调用其各个接口.
   *
   * @return WxMpSubscribeMsgService 订阅消息服务接口
   */
  WxMpSubscribeMsgService getSubscribeMsgService();

  /**
   * 返回硬件平台相关接口方法的实现类对象，以方便调用其各个接口.
   *
   * @return WxMpDeviceService 硬件平台服务接口
   */
  WxMpDeviceService getDeviceService();

  /**
   * 返回摇一摇周边相关接口方法的实现类对象，以方便调用其各个接口.
   *
   * @return WxMpShakeService 摇一摇周边服务接口
   */
  WxMpShakeService getShakeService();

  /**
   * 返回会员卡相关接口方法的实现类对象，以方便调用其各个接口.
   *
   * @return WxMpMemberCardService 会员卡服务接口
   */
  WxMpMemberCardService getMemberCardService();

  /**
   * 返回营销相关接口方法的实现类对象，以方便调用其各个接口.
   *
   * @return WxMpMarketingService 营销服务接口
   */
  WxMpMarketingService getMarketingService();

  /**
   * 初始化http请求对象.
   */
  void initHttp();

  /**
   * 获取RequestHttp对象.
   *
   * @return RequestHttp对象 HTTP请求处理对象
   */
  RequestHttp<?, ?> getRequestHttp();

  /**
   * 返回群发消息相关接口方法的实现类对象，以方便调用其各个接口.
   *
   * @return WxMpMassMessageService 群发消息服务接口
   */
  WxMpMassMessageService getMassMessageService();

  /**
   * 返回AI开放接口方法的实现类对象，以方便调用其各个接口.
   *
   * @return WxMpAiOpenService AI开放服务接口
   */
  WxMpAiOpenService getAiOpenService();

  /**
   * 返回WIFI接口方法的实现类对象，以方便调用其各个接口.
   *
   * @return WxMpWifiService WIFI服务接口
   */
  WxMpWifiService getWifiService();

  /**
   * 返回OCR接口方法的实现类对象，以方便调用其各个接口.
   *
   * @return WxOcrService OCR服务接口
   */
  WxOcrService getOcrService();

  /**
   * 返回图像处理接口的实现类对象，以方便调用其各个接口.
   *
   * @return WxImgProcService 图像处理服务接口
   */
  WxImgProcService getImgProcService();

  /**
   * 返回电子发票报销方相关接口
   *
   * @return WxMpReimburseInvoiceService 电子发票报销方服务接口
   */
  WxMpReimburseInvoiceService getReimburseInvoiceService();

  /**
   * 返回草稿箱相关接口
   *
   * @return WxMpDraftService 草稿箱服务接口
   */
  WxMpDraftService getDraftService();

  /**
   * 返回发布能力接口
   *
   * @return WxMpFreePublishService 发布能力服务接口
   */
  WxMpFreePublishService getFreePublishService();

  /**
   * 设置电子发票报销方服务接口
   *
   * @param reimburseInvoiceService 电子发票报销方服务接口
   */
  void setReimburseInvoiceService(WxMpReimburseInvoiceService reimburseInvoiceService);

  /**
   * 设置客服服务接口
   *
   * @param kefuService 客服服务接口
   */
  void setKefuService(WxMpKefuService kefuService);

  /**
   * 设置素材服务接口
   *
   * @param materialService 素材服务接口
   */
  void setMaterialService(WxMpMaterialService materialService);

  /**
   * 设置菜单服务接口
   *
   * @param menuService 菜单服务接口
   */
  void setMenuService(WxMpMenuService menuService);

  /**
   * 设置用户服务接口
   *
   * @param userService 用户服务接口
   */
  void setUserService(WxMpUserService userService);

  /**
   * 设置用户标签服务接口
   *
   * @param userTagService 用户标签服务接口
   */
  void setUserTagService(WxMpUserTagService userTagService);

  /**
   * 设置二维码服务接口
   *
   * @param qrcodeService 二维码服务接口
   */
  void setQrcodeService(WxMpQrcodeService qrcodeService);

  /**
   * 设置卡券服务接口
   *
   * @param cardService 卡券服务接口
   */
  void setCardService(WxMpCardService cardService);

  /**
   * 设置门店服务接口
   *
   * @param storeService 门店服务接口
   */
  void setStoreService(WxMpStoreService storeService);

  /**
   * 设置数据分析服务接口
   *
   * @param dataCubeService 数据分析服务接口
   */
  void setDataCubeService(WxMpDataCubeService dataCubeService);

  /**
   * 设置用户黑名单服务接口
   *
   * @param blackListService 用户黑名单服务接口
   */
  void setBlackListService(WxMpUserBlacklistService blackListService);

  /**
   * 设置模板消息服务接口
   *
   * @param templateMsgService 模板消息服务接口
   */
  void setTemplateMsgService(WxMpTemplateMsgService templateMsgService);

  /**
   * 设置硬件平台服务接口
   *
   * @param deviceService 硬件平台服务接口
   */
  void setDeviceService(WxMpDeviceService deviceService);

  /**
   * 设置摇一摇周边服务接口
   *
   * @param shakeService 摇一摇周边服务接口
   */
  void setShakeService(WxMpShakeService shakeService);

  /**
   * 设置会员卡服务接口
   *
   * @param memberCardService 会员卡服务接口
   */
  void setMemberCardService(WxMpMemberCardService memberCardService);

  /**
   * 设置群发消息服务接口
   *
   * @param massMessageService 群发消息服务接口
   */
  void setMassMessageService(WxMpMassMessageService massMessageService);

  /**
   * 设置AI开放服务接口
   *
   * @param aiOpenService AI开放服务接口
   */
  void setAiOpenService(WxMpAiOpenService aiOpenService);

  /**
   * 设置营销服务接口
   *
   * @param marketingService 营销服务接口
   */
  void setMarketingService(WxMpMarketingService marketingService);

  /**
   * 设置OCR服务接口
   *
   * @param ocrService OCR服务接口
   */
  void setOcrService(WxOcrService ocrService);

  /**
   * 设置图像处理服务接口
   *
   * @param imgProcService 图像处理服务接口
   */
  void setImgProcService(WxImgProcService imgProcService);

  /**
   * 返回评论数据管理接口方法的实现类对象，以方便调用其各个接口.
   *
   * @return WxMpCommentService 评论数据管理服务接口
   */
  WxMpCommentService getCommentService();

  /**
   * 设置评论数据管理服务接口
   *
   * @param commentService 评论数据管理服务接口
   */
  void setCommentService(WxMpCommentService commentService);

  /**
   * 获取OAuth2服务接口
   *
   * @return WxOAuth2Service OAuth2服务接口
   */
  WxOAuth2Service getOAuth2Service();

  /**
   * 设置OAuth2服务接口
   *
   * @param oAuth2Service OAuth2服务接口
   */
  void setOAuth2Service(WxOAuth2Service oAuth2Service);

  /**
   * 获取微信导购服务接口
   *
   * @return WxMpGuideService 微信导购服务接口
   */
  WxMpGuideService getGuideService();

  /**
   * 设置微信导购服务接口
   *
   * @param guideService 微信导购服务接口
   */
  void setGuideService(WxMpGuideService guideService);

  /**
   * 获取微信导购买家服务接口
   *
   * @return WxMpGuideBuyerService 微信导购买家服务接口
   */
  WxMpGuideBuyerService getGuideBuyerService();

  /**
   * 设置微信导购买家服务接口
   *
   * @param guideBuyerService 微信导购买家服务接口
   */
  void setGuideBuyerService(WxMpGuideBuyerService guideBuyerService);

  /**
   * 获取微信导购标签服务接口
   *
   * @return WxMpGuideTagService 微信导购标签服务接口
   */
  WxMpGuideTagService getGuideTagService();

  /**
   * 设置微信导购标签服务接口
   *
   * @param guideTagService 微信导购标签服务接口
   */
  void setGuideTagService(WxMpGuideTagService guideTagService);

  /**
   * 获取微信导购素材服务接口
   *
   * @return WxMpGuideMaterialService 微信导购素材服务接口
   */
  WxMpGuideMaterialService getGuideMaterialService();

  /**
   * 设置微信导购素材服务接口
   *
   * @param guideMaterialService 微信导购素材服务接口
   */
  void setGuideMaterialService(WxMpGuideMaterialService guideMaterialService);

  /**
   * 获取微信导购批量任务服务接口
   *
   * @return WxMpGuideMassedJobService 微信导购批量任务服务接口
   */
  WxMpGuideMassedJobService getGuideMassedJobService();

  /**
   * 设置微信导购批量任务服务接口
   *
   * @param guideMassedJobService 微信导购批量任务服务接口
   */
  void setGuideMassedJobService(WxMpGuideMassedJobService guideMassedJobService);

  /**
   * 获取微信商户发票服务接口
   *
   * @return WxMpMerchantInvoiceService 微信商户发票服务接口
   */
  WxMpMerchantInvoiceService getMerchantInvoiceService();

  /**
   * 设置微信商户发票服务接口
   *
   * @param merchantInvoiceService 微信商户发票服务接口
   */
  void setMerchantInvoiceService(WxMpMerchantInvoiceService merchantInvoiceService);

  /**
   * 设置草稿箱服务接口
   *
   * @param draftService 草稿箱服务接口
   */
  void setDraftService(WxMpDraftService draftService);

  /**
   * 设置发布能力服务接口
   *
   * @param freePublishService 发布能力服务接口
   */
  void setFreePublishService(WxMpFreePublishService freePublishService);
}
