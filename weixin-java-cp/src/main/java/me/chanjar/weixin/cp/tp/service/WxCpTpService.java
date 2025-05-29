package me.chanjar.weixin.cp.tp.service;

import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.common.util.http.MediaUploadRequestExecutor;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.cp.bean.*;
import me.chanjar.weixin.cp.config.WxCpTpConfigStorage;

import java.util.List;

/**
 * 企业微信第三方应用API的Service.
 *
 * @author zhenjun cai
 */
public interface WxCpTpService {
  /**
   * <pre>
   * 验证推送过来的消息的正确性
   * 详情请见: <a href="https://work.weixin.qq.com/api/doc#90000/90139/90968/">消息体签名校验</a>
   * </pre>
   *
   * @param msgSignature 消息签名
   * @param timestamp    时间戳
   * @param nonce        随机数
   * @param data         微信传输过来的数据，有可能是echoStr，有可能是xml消息
   * @return the boolean
   */
  boolean checkSignature(String msgSignature, String timestamp, String nonce, String data);

  /**
   * 获取suite_access_token, 不强制刷新suite_access_token
   *
   * @return the suite access token
   * @throws WxErrorException the wx error exception
   * @see #getSuiteAccessToken(boolean) #getSuiteAccessToken(boolean)#getSuiteAccessToken(boolean)
   * #getSuiteAccessToken(boolean)
   */
  String getSuiteAccessToken() throws WxErrorException;

  /**
   * <pre>
   * 获取suite_access_token，本方法线程安全
   * 且在多线程同时刷新时只刷新一次，避免超出2000次/日的调用次数上限
   * 另：本service的所有方法都会在suite_access_token过期是调用此方法
   * 程序员在非必要情况下尽量不要主动调用此方法
   * 详情请见: <a href="https://work.weixin.qq.com/api/doc#90001/90143/90600">文档</a>
   * </pre>
   *
   * @param forceRefresh 强制刷新
   * @return the suite access token
   * @throws WxErrorException the wx error exception
   */
  String getSuiteAccessToken(boolean forceRefresh) throws WxErrorException;

  /**
   * 获取suite_access_token和剩余过期时间, 不强制刷新suite_access_token
   *
   * @return suite access token and the remaining expiration time
   * @throws WxErrorException the wx error exception
   */
  WxAccessToken getSuiteAccessTokenEntity() throws WxErrorException;

  /**
   * 获取suite_access_token和剩余过期时间, 支持强制刷新suite_access_token
   *
   * @param forceRefresh 是否调用微信服务器强制刷新token
   * @return suite access token and the remaining expiration time
   * @throws WxErrorException the wx error exception
   */
  WxAccessToken getSuiteAccessTokenEntity(boolean forceRefresh) throws WxErrorException;

  /**
   * 获得suite_ticket,不强制刷新suite_ticket
   *
   * @return the suite ticket
   * @throws WxErrorException the wx error exception
   * @see #getSuiteTicket(boolean) #getSuiteTicket(boolean)#getSuiteTicket(boolean)#getSuiteTicket(boolean)
   */
  String getSuiteTicket() throws WxErrorException;

  /**
   * <pre>
   * 保存企业微信定时推送的suite_ticket,（每10分钟）
   * 详情请见：<a href="https://work.weixin.qq.com/api/doc#90001/90143/90628">文档</a>
   *
   * 注意：微信不是固定10分钟推送suite_ticket的, 且suite_ticket的有效期为30分钟
   * https://work.weixin.qq.com/api/doc/10975#%E8%8E%B7%E5%8F%96%E7%AC%AC%E4%B8%89%E6%96%B9%E5%BA%94%E7%94%A8%E5%87%AD%E8%AF%81
   * </pre>
   *
   * @param suiteTicket the suite ticket
   */
  void setSuiteTicket(String suiteTicket);

  /**
   * <pre>
   * 获得suite_ticket
   * 由于suite_ticket是微信服务器定时推送（每10分钟），不能主动获取，如果碰到过期只能抛异常
   *
   * 详情请见：<a href="https://work.weixin.qq.com/api/doc#90001/90143/90628">文档</a>
   * </pre>
   *
   * @param forceRefresh 强制刷新
   * @return the suite ticket
   * @throws WxErrorException the wx error exception
   * @see #setSuiteTicket(String) #setSuiteTicket(String)#setSuiteTicket(String)
   * @deprecated 由于无法主动刷新 ，所以这个接口实际已经没有意义，需要在接收企业微信的主动推送后，保存这个ticket
   */
  @Deprecated
  String getSuiteTicket(boolean forceRefresh) throws WxErrorException;

  /**
   * <pre>
   * 保存企业微信定时推送的suite_ticket,（每10分钟）
   * 详情请见：<a href="https://work.weixin.qq.com/api/doc#90001/90143/90628">文档</a>
   *
   * 注意：微信不是固定10分钟推送suite_ticket的, 且suite_ticket的有效期为30分钟
   * https://work.weixin.qq.com/api/doc/10975#%E8%8E%B7%E5%8F%96%E7%AC%AC%E4%B8%89%E6%96%B9%E5%BA%94%E7%94%A8%E5%87%AD%E8%AF%81
   * </pre>
   *
   * @param suiteTicket      the suite ticket
   * @param expiresInSeconds the expires in seconds
   */
  void setSuiteTicket(String suiteTicket, int expiresInSeconds);

  /**
   * 获取应用的 jsapi ticket
   *
   * @param authCorpId 授权企业的cropId
   * @return jsapi ticket
   * @throws WxErrorException the wx error exception
   */
  String getSuiteJsApiTicket(String authCorpId) throws WxErrorException;

  /**
   * 获取应用的 jsapi ticket， 支持强制刷新
   *
   * @param authCorpId   the auth corp id
   * @param forceRefresh the force refresh
   * @return suite js api ticket
   * @throws WxErrorException the wx error exception
   */
  String getSuiteJsApiTicket(String authCorpId, boolean forceRefresh) throws WxErrorException;

  /**
   * 小程序登录凭证校验
   *
   * @param jsCode 登录时获取的 code
   * @return the wx cp ma js code 2 session result
   * @throws WxErrorException the wx error exception
   */
  WxCpMaJsCode2SessionResult jsCode2Session(String jsCode) throws WxErrorException;

  /**
   * 获取企业凭证
   *
   * @param authCorpId    授权方corpid
   * @param permanentCode 永久授权码，通过get_permanent_code获取
   * @return the corp token
   * @throws WxErrorException the wx error exception
   */
  WxAccessToken getCorpToken(String authCorpId, String permanentCode) throws WxErrorException;

  /**
   * 获取企业凭证, 支持强制刷新
   *
   * @param authCorpId    the auth corp id
   * @param permanentCode the permanent code
   * @param forceRefresh  the force refresh
   * @return corp token
   * @throws WxErrorException the wx error exception
   */
  WxAccessToken getCorpToken(String authCorpId, String permanentCode, boolean forceRefresh) throws WxErrorException;

  /**
   * 获取企业永久授权码 .
   *
   * @param authCode .
   * @return . permanent code
   * @throws WxErrorException the wx error exception
   */
  @Deprecated
  WxCpTpCorp getPermanentCode(String authCode) throws WxErrorException;

  /**
   * 获取企业永久授权码信息
   * <pre>
   *   原来的方法实现不全
   * </pre>
   *
   * @param authCode the auth code
   * @return permanent code info
   * @throws WxErrorException the wx error exception
   * @author yuan
   * @since 2020 -03-18
   */
  WxCpTpPermanentCodeInfo getPermanentCodeInfo(String authCode) throws WxErrorException;

  /**
   * <pre>
   *   获取预授权链接
   * </pre>
   *
   * @param redirectUri 授权完成后的回调网址
   * @param state       a-zA-Z0-9的参数值（不超过128个字节），用于第三方自行校验session，防止跨域攻击
   * @return pre auth url
   * @throws WxErrorException the wx error exception
   */
  String getPreAuthUrl(String redirectUri, String state) throws WxErrorException;

  /**
   * <pre>
   *   获取预授权链接，测试环境下使用
   * </pre>
   *
   * @param redirectUri 授权完成后的回调网址
   * @param state       a-zA-Z0-9的参数值（不超过128个字节），用于第三方自行校验session，防止跨域攻击
   * @param authType    授权类型：0 正式授权， 1 测试授权。
   * @return pre auth url
   * @throws WxErrorException the wx error exception
   * @link https ://work.weixin.qq.com/api/doc/90001/90143/90602
   */
  String getPreAuthUrl(String redirectUri, String state, int authType) throws WxErrorException;

  /**
   * 获取企业的授权信息
   *
   * @param authCorpId    授权企业的corpId
   * @param permanentCode 授权企业的永久授权码
   * @return auth info
   * @throws WxErrorException the wx error exception
   */
  WxCpTpAuthInfo getAuthInfo(String authCorpId, String permanentCode) throws WxErrorException;

  /**
   * 获取授权企业的 jsapi ticket
   *
   * @param authCorpId 授权企业的cropId
   * @return jsapi ticket
   * @throws WxErrorException the wx error exception
   */
  String getAuthCorpJsApiTicket(String authCorpId) throws WxErrorException;

  /**
   * 获取授权企业的 jsapi ticket, 支持强制刷新
   *
   * @param authCorpId   the auth corp id
   * @param forceRefresh the force refresh
   * @return auth corp js api ticket
   * @throws WxErrorException the wx error exception
   */
  String getAuthCorpJsApiTicket(String authCorpId, boolean forceRefresh) throws WxErrorException;

  /**
   * 当本Service没有实现某个API的时候，可以用这个，针对所有微信API中的GET请求.
   *
   * @param url        接口地址
   * @param queryParam 请求参数
   * @return the string
   * @throws WxErrorException the wx error exception
   */
  String get(String url, String queryParam) throws WxErrorException;

  /**
   * 当本Service没有实现某个API的时候，可以用这个，针对所有微信API中的GET请求.
   *
   * @param url                     接口地址
   * @param queryParam              请求参数
   * @param withoutSuiteAccessToken 请求是否忽略SuiteAccessToken 默认不忽略-false
   * @return the string
   * @throws WxErrorException the wx error exception
   */
  String get(String url, String queryParam, boolean withoutSuiteAccessToken) throws WxErrorException;

  /**
   * 当本Service没有实现某个API的时候，可以用这个，针对所有微信API中的POST请求.
   *
   * @param url      接口地址
   * @param postData 请求body字符串
   * @return the string
   * @throws WxErrorException the wx error exception
   */
  String post(String url, String postData) throws WxErrorException;

  /**
   * 当本Service没有实现某个API的时候，可以用这个，针对所有微信API中的POST请求.
   *
   * @param url      接口地址
   * @param postData 请求body字符串
   * @param withoutSuiteAccessToken 请求是否忽略SuiteAccessToken 默认不忽略-false
   * @return the string
   * @throws WxErrorException the wx error exception
   */
  String post(String url, String postData, boolean withoutSuiteAccessToken) throws WxErrorException;

  /**
   * <pre>
   * Service没有实现某个API的时候，可以用这个，
   * 比{@link #get}和{@link #post}方法更灵活，可以自己构造RequestExecutor用来处理不同的参数和不同的返回类型。
   * 可以参考，{@link MediaUploadRequestExecutor}的实现方法
   * </pre>
   *
   * @param <T>      请求值类型
   * @param <E>      返回值类型
   * @param executor 执行器
   * @param uri      请求地址
   * @param data     参数
   * @return the t
   * @throws WxErrorException the wx error exception
   */
  <T, E> T execute(RequestExecutor<T, E> executor, String uri, E data) throws WxErrorException;

  /**
   * <pre>
   * 设置当微信系统响应系统繁忙时，要等待多少 retrySleepMillis(ms) * 2^(重试次数 - 1) 再发起重试.
   * 默认：1000ms
   * </pre>
   *
   * @param retrySleepMillis 重试休息时间
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
   * 初始化http请求对象
   */
  void initHttp();

  /**
   * 获取WxCpTpConfigStorage 对象.
   *
   * @return WxCpTpConfigStorage wx cp tp config storage
   * @deprecated storage应该在service内部使用 ，提供这个接口，容易破坏这个封装
   */
  @Deprecated
  WxCpTpConfigStorage getWxCpTpConfigStorage();

  /**
   * 注入 {@link WxCpTpConfigStorage} 的实现.
   *
   * @param wxConfigProvider 配置对象
   */
  void setWxCpTpConfigStorage(WxCpTpConfigStorage wxConfigProvider);

  /**
   * http请求对象.
   *
   * @return the request http
   */
  RequestHttp<?, ?> getRequestHttp();

  /**
   * 获取WxSessionManager 对象
   *
   * @return WxSessionManager session manager
   */
  WxSessionManager getSessionManager();

  /**
   * <pre>
   * 获取登录/访问用户身份
   * 1、<a href="https://developer.work.weixin.qq.com/document/path/91121">网页授权登录对应的文档</a>
   * 2、<a href="https://developer.work.weixin.qq.com/document/path/98179">企业微信web登录对应的文档</a>
   * </pre>
   *
   * @param code the code
   * @return user info 3 rd
   * @throws WxErrorException the wx error exception
   */
  WxCpTpUserInfo getUserInfo3rd(String code) throws WxErrorException;

  /**
   * <pre>
   * 获取访问用户敏感信息
   * <a href="https://developer.work.weixin.qq.com/document/path/95833">文档地址</a>
   * </pre>
   *
   * @param userTicket the user ticket
   * @return user detail 3 rd
   * @throws WxErrorException the wx error exception
   */
  WxCpTpUserDetail getUserDetail3rd(String userTicket) throws WxErrorException;

  /**
   * 获取登录用户信息
   * <p>
   * 文档地址：https://work.weixin.qq.com/api/doc/90001/90143/91125
   *
   * @param authCode the auth code
   * @return login info
   * @throws WxErrorException the wx error exception
   */
  WxTpLoginInfo getLoginInfo(String authCode) throws WxErrorException;

  /**
   * 获取带参授权链接
   * <p>
   * <a href="https://developer.work.weixin.qq.com/document/path/95436">查看文档</a>
   *
   * @param state state
   * @param templateIdList 代开发自建应用模版ID列表，数量不能超过9个
   * @return customized auth url
   * @throws WxErrorException the wx error exception
   */
  WxTpCustomizedAuthUrl getCustomizedAuthUrl(String state, List<String> templateIdList) throws WxErrorException;

  /**
   * 获取服务商providerToken
   *
   * @return the wx cp provider token
   * @throws WxErrorException the wx error exception
   */
  String getWxCpProviderToken() throws WxErrorException;

  /**
   * 获取服务商providerToken和剩余过期时间
   *
   * @return wx cp provider token entity
   * @throws WxErrorException the wx error exception
   */
  WxCpProviderToken getWxCpProviderTokenEntity() throws WxErrorException;

  /**
   * 获取服务商providerToken和剩余过期时间，支持强制刷新
   *
   * @param forceRefresh the force refresh
   * @return wx cp provider token entity
   * @throws WxErrorException the wx error exception
   */
  WxCpProviderToken getWxCpProviderTokenEntity(boolean forceRefresh) throws WxErrorException;

  /**
   * get contact service
   *
   * @return WxCpTpContactService wx cp tp contact service
   */
  WxCpTpContactService getWxCpTpContactService();

  /**
   * set contact service
   *
   * @param wxCpTpContactService the contact service
   */
  void setWxCpTpContactService(WxCpTpContactService wxCpTpContactService);

  /**
   * get department service
   *
   * @return WxCpTpDepartmentService wx cp tp department service
   */
  WxCpTpDepartmentService getWxCpTpDepartmentService();

  /**
   * set department service
   *
   * @param wxCpTpDepartmentService the department service
   */
  void setWxCpTpDepartmentService(WxCpTpDepartmentService wxCpTpDepartmentService);

  /**
   * get media service
   *
   * @return WxCpTpMediaService wx cp tp media service
   */
  WxCpTpMediaService getWxCpTpMediaService();

  /**
   * set media service
   *
   * @param wxCpTpMediaService the media service
   */
  void setWxCpTpMediaService(WxCpTpMediaService wxCpTpMediaService);

  /**
   * get oa service
   *
   * @return WxCpTpOAService wx cp tp oa service
   */
  WxCpTpOAService getWxCpTpOAService();

  /**
   * set oa service
   *
   * @param wxCpTpOAService the oa service
   */
  void setWxCpTpOAService(WxCpTpOAService wxCpTpOAService);

  /**
   * get user service
   *
   * @return WxCpTpUserService wx cp tp user service
   */
  WxCpTpUserService getWxCpTpUserService();

  /**
   * set user service
   *
   * @param wxCpTpUserService the set user service
   */
  void setWxCpTpUserService(WxCpTpUserService wxCpTpUserService);

  /**
   * set license service
   *
   * @param wxCpTpLicenseService the oa service
   */
  void setWxCpTpLicenseService(WxCpTpLicenseService wxCpTpLicenseService);


  /**
   * get license service
   *
   * @return getCpTPLicenseService wx cp tp license service
   */
  WxCpTpLicenseService getWxCpTpLicenseService();

  /**
   * 获取应用的管理员列表
   *
   * @param authCorpId the auth corp id
   * @param agentId    the agent id
   * @return admin list
   * @throws WxErrorException the wx error exception
   */
  WxCpTpAdmin getAdminList(String authCorpId, Integer agentId) throws WxErrorException;

  /**
   * 获取应用二维码
   * @param suiteId 第三方应用id（即ww或wx开头的suiteid）
   * @param appId 第三方应用id，单应用不需要该参数，多应用旧套件才需要传该参数。若不传默认为1
   * @param state state值，用于区分不同的安装渠道
   * @param style 二维码样式选项，默认为不带说明外框小尺寸。0：带说明外框的二维码，适合于实体物料，1：带说明外框的二维码，适合于屏幕类，2：不带说明外框（小尺寸），3：不带说明外框（中尺寸），4：不带说明外框（大尺寸）。具体样式与服务商管理端获取到的应用二维码样式一一对应，参见下文二维码样式说明
   * @param resultType 结果返回方式，默认为返回二维码图片buffer。1：二维码图片buffer，2：二维码图片url
   * @return 二维码
   * @throws WxErrorException the wx error exception
   */
  WxCpTpAppQrcode getAppQrcode(String suiteId, String appId, String state, Integer style, Integer resultType) throws WxErrorException ;

  /**
   *
   * 明文corpid转换为加密corpid 为更好地保护企业与用户的数据，第三方应用获取的corpid不再是明文的corpid，将升级为第三方服务商级别的加密corpid。<a href="https://developer.work.weixin.qq.com/document/path/95327">文档说明</a>
   * 第三方可以将已有的明文corpid转换为第三方的加密corpid。
   * @param corpId
   * @return
   * @throws WxErrorException
   */
  WxCpTpCorpId2OpenCorpId corpId2OpenCorpId(String corpId) throws WxErrorException;

  /**
   * 创建机构级jsApiTicket签名
   * 详情参见<a href="https://work.weixin.qq.com/api/doc/90001/90144/90539">企业微信第三方应用开发文档</a>
   *
   * @param url        调用JS接口页面的完整URL
   * @param authCorpId the auth corp id
   * @return wx jsapi signature
   * @throws WxErrorException the wx error exception
   */
  WxJsapiSignature createAuthCorpJsApiTicketSignature(String url, String authCorpId) throws WxErrorException;

  /**
   * 创建应用级jsapiTicket签名
   * 详情参见：<a href="https://work.weixin.qq.com/api/doc/90001/90144/90539">企业微信第三方应用开发文档</a>
   *
   * @param url        调用JS接口页面的完整URL
   * @param authCorpId the auth corp id
   * @return wx jsapi signature
   * @throws WxErrorException the wx error exception
   */
  WxJsapiSignature createSuiteJsApiTicketSignature(String url, String authCorpId) throws WxErrorException;

  /**
   * 使套件accessToken缓存失效
   */
  void expireSuiteAccessToken();

  /**
   * 使机构accessToken缓存失效
   *
   * @param authCorpId 机构id
   */
  void expireAccessToken(String authCorpId);

  /**
   * 使机构jsapiticket缓存失效
   *
   * @param authCorpId 机构id
   */
  void expireAuthCorpJsApiTicket(String authCorpId);

  /**
   * 使应用jsapiticket失效
   *
   * @param authCorpId 机构id
   */
  void expireAuthSuiteJsApiTicket(String authCorpId);

  /**
   * 使供应商accessToken失效
   */
  void expireProviderToken();

  /**
   * 获取应用版本付费订单相关接口服务
   *
   * @return the wx cp tp order service
   */
  WxCpTpOrderService getWxCpTpOrderService();

  /**
   * 设置应用版本付费订单相关接口服务
   *
   * @param wxCpTpOrderService the wx cp tp order service
   */
  void setWxCpTpOrderService(WxCpTpOrderService wxCpTpOrderService);

  /**
   * 获取应用版本付费版本相关接口服务
   *
   * @return the wx cp tp edition service
   */
  WxCpTpEditionService getWxCpTpEditionService();

  /**
   * 设置应用版本付费版本相关接口服务
   *
   * @param wxCpTpEditionService the wx cp tp edition service
   */
  void setWxCpTpOrderService(WxCpTpEditionService wxCpTpEditionService);


  WxCpTpIdConvertService getWxCpTpIdConverService();

  void setWxCpTpIdConverService(WxCpTpIdConvertService wxCpTpIdConvertService);

  /**
   * 构造第三方应用oauth2链接
   */
  WxCpTpOAuth2Service getWxCpTpOAuth2Service();

  void setWxCpTpOAuth2Service(WxCpTpOAuth2Service wxCpTpOAuth2Service);

}
