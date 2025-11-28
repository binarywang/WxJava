package me.chanjar.weixin.cp.api;

import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.service.WxService;
import me.chanjar.weixin.common.session.WxSession;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.common.util.http.MediaUploadRequestExecutor;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.cp.bean.WxCpAgentJsapiSignature;
import me.chanjar.weixin.cp.bean.WxCpMaJsCode2SessionResult;
import me.chanjar.weixin.cp.bean.WxCpProviderToken;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;

/**
 * 微信API的Service.
 *
 * @author chanjaster
 */
public interface WxCpService extends WxService {
  /**
   * <pre>
   * 验证推送过来的消息的正确性
   * 详情请见: http://mp.weixin.qq.com/wiki/index.php?title=验证消息真实性
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
   * 获取access_token, 不强制刷新access_token
   *
   * @return the access token
   * @throws WxErrorException the wx error exception
   * @see #getAccessToken(boolean) #getAccessToken(boolean)#getAccessToken(boolean)#getAccessToken(boolean)
   */
  String getAccessToken() throws WxErrorException;

  /**
   * <pre>
   * 获取access_token，本方法线程安全
   * 且在多线程同时刷新时只刷新一次，避免超出2000次/日的调用次数上限
   * 另：本service的所有方法都会在access_token过期是调用此方法
   * 程序员在非必要情况下尽量不要主动调用此方法
   * 详情请见: http://mp.weixin.qq.com/wiki/index.php?title=获取access_token
   * </pre>
   *
   * @param forceRefresh 强制刷新
   * @return the access token
   * @throws WxErrorException the wx error exception
   */
  String getAccessToken(boolean forceRefresh) throws WxErrorException;

  /**
   * 获得jsapi_ticket,不强制刷新jsapi_ticket
   *
   * @return the jsapi ticket
   * @throws WxErrorException the wx error exception
   * @see #getJsapiTicket(boolean) #getJsapiTicket(boolean)#getJsapiTicket(boolean)#getJsapiTicket(boolean)
   */
  String getJsapiTicket() throws WxErrorException;

  /**
   * <pre>
   * 获得jsapi_ticket
   * 获得时会检查jsapiToken是否过期，如果过期了，那么就刷新一下，否则就什么都不干
   *
   * 详情请见：http://qydev.weixin.qq.com/wiki/index.php?title=微信JS接口#.E9.99.84.E5.BD.951-JS-SDK.E4.BD.BF.E7.94.A8.E6.9D.83.E9.99.90.E7.AD.BE.E5.90.8D.E7.AE.97.E6.B3.95
   * </pre>
   *
   * @param forceRefresh 强制刷新
   * @return the jsapi ticket
   * @throws WxErrorException the wx error exception
   */
  String getJsapiTicket(boolean forceRefresh) throws WxErrorException;

  /**
   * 获得jsapi_ticket,不强制刷新jsapi_ticket
   * 应用的jsapi_ticket用于计算agentConfig（参见“通过agentConfig注入应用的权限”）的签名，签名计算方法与上述介绍的config的签名算法完全相同，但需要注意以下区别：
   * <p>
   * 签名的jsapi_ticket必须使用以下接口获取。且必须用wx.agentConfig中的agentid对应的应用secret去获取access_token。
   * 签名用的noncestr和timestamp必须与wx.agentConfig中的nonceStr和timestamp相同。
   *
   * @return the agent jsapi ticket
   * @throws WxErrorException the wx error exception
   * @see #getJsapiTicket(boolean) #getJsapiTicket(boolean)#getJsapiTicket(boolean)#getJsapiTicket(boolean)
   */
  String getAgentJsapiTicket() throws WxErrorException;

  /**
   * <pre>
   * 获取应用的jsapi_ticket
   * 应用的jsapi_ticket用于计算agentConfig（参见“通过agentConfig注入应用的权限”）的签名，签名计算方法与上述介绍的config的签名算法完全相同，但需要注意以下区别：
   *
   * 签名的jsapi_ticket必须使用以下接口获取。且必须用wx.agentConfig中的agentid对应的应用secret去获取access_token。
   * 签名用的noncestr和timestamp必须与wx.agentConfig中的nonceStr和timestamp相同。
   *
   * 获得时会检查jsapiToken是否过期，如果过期了，那么就刷新一下，否则就什么都不干
   *
   * 详情请见：https://work.weixin.qq.com/api/doc#10029/%E8%8E%B7%E5%8F%96%E5%BA%94%E7%94%A8%E7%9A%84jsapi_ticket
   * </pre>
   *
   * @param forceRefresh 强制刷新
   * @return the agent jsapi ticket
   * @throws WxErrorException the wx error exception
   */
  String getAgentJsapiTicket(boolean forceRefresh) throws WxErrorException;

  /**
   * <pre>
   * 创建调用jsapi时所需要的签名
   *
   * 详情请见：http://qydev.weixin.qq.com/wiki/index.php?title=微信JS接口#.E9.99.84.E5.BD.951-JS-SDK.E4.BD.BF.E7.94.A8.E6.9D.83.E9.99.90.E7.AD.BE.E5.90.8D.E7.AE.97.E6.B3.95
   * </pre>
   *
   * @param url url
   * @return the wx jsapi signature
   * @throws WxErrorException the wx error exception
   */
  WxJsapiSignature createJsapiSignature(String url) throws WxErrorException;

  /**
   * <pre>
   *   创建调用wx.agentConfig时所需要的签名
   *
   * 详情请见：https://open.work.weixin.qq.com/api/doc/90000/90136/94313
   * </pre>
   *
   * @param url url
   * @return the agent jsapi signature
   * @throws WxErrorException the wx error exception
   */
  WxCpAgentJsapiSignature createAgentJsapiSignature(String url) throws WxErrorException;

  /**
   * 小程序登录凭证校验
   *
   * @param jsCode 登录时获取的 code
   * @return the wx cp ma js code 2 session result
   * @throws WxErrorException the wx error exception
   */
  WxCpMaJsCode2SessionResult jsCode2Session(String jsCode) throws WxErrorException;

  /**
   * <pre>
   * 获取企业微信回调IP段
   * http://qydev.weixin.qq.com/wiki/index.php?title=回调模式#.E8.8E.B7.E5.8F.96.E5.BE.AE.E4.BF.A1.E6.9C.8D.E5.8A.A1.E5.99.A8.E7.9A.84ip.E6.AE.B5
   * </pre>
   *
   * @return { "ip_list": ["101.226.103.*", "101.226.62.*"] }
   * @throws WxErrorException the wx error exception
   */
  String[] getCallbackIp() throws WxErrorException;

  /**
   * <pre>
   * 获取企业微信接口IP段
   * https://developer.work.weixin.qq.com/document/path/92520
   * </pre>
   *
   * @return 企业微信接口IP段
   * @throws WxErrorException the wx error exception
   */
  String[] getApiDomainIp() throws WxErrorException;

  /**
   * <pre>
   * 获取服务商凭证
   * 文档地址：https://work.weixin.qq.com/api/doc#90001/90143/91200
   * 请求方式：POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/service/get_provider_token
   * </pre>
   *
   * @param corpId         服务商的corpid
   * @param providerSecret 服务商的secret，在服务商管理后台可见
   * @return { "errcode":0 , "errmsg":"ok" , "provider_access_token":"enLSZ5xxxxxxJRL", "expires_in":7200 }
   * @throws WxErrorException .
   */
  WxCpProviderToken getProviderToken(String corpId, String providerSecret) throws WxErrorException;

  /**
   * 当不需要自动带accessToken的时候，可以用这个发起post请求
   *
   * @param url      接口地址
   * @param postData 请求body字符串
   * @return the string
   * @throws WxErrorException the wx error exception
   */
  String postWithoutToken(String url, String postData) throws WxErrorException;

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
   * 设置当微信系统响应系统繁忙时，要等待多少 retrySleepMillis(ms) * 2^(重试次数 - 1) 再发起重试
   * 默认：1000ms
   * </pre>
   *
   * @param retrySleepMillis 重试休息时间
   */
  void setRetrySleepMillis(int retrySleepMillis);

  /**
   * <pre>
   * 设置当微信系统响应系统繁忙时，最大重试次数
   * 默认：5次
   * </pre>
   *
   * @param maxRetryTimes 最大重试次数
   */
  void setMaxRetryTimes(int maxRetryTimes);

  /**
   * 获取某个sessionId对应的session,如果sessionId没有对应的session，则新建一个并返回。
   *
   * @param id id可以为任意字符串，建议使用FromUserName作为id
   * @return the session
   */
  WxSession getSession(String id);

  /**
   * 获取某个sessionId对应的session,如果sessionId没有对应的session，若create为true则新建一个，否则返回null。
   *
   * @param id     id可以为任意字符串，建议使用FromUserName作为id
   * @param create 是否新建
   * @return the session
   */
  WxSession getSession(String id, boolean create);

  /**
   * 获取WxSessionManager 对象
   *
   * @return WxSessionManager session manager
   */
  WxSessionManager getSessionManager();

  /**
   * <pre>
   * 设置WxSessionManager，只有当需要使用个性化的WxSessionManager的时候才需要调用此方法，
   * WxCpService默认使用的是{@link me.chanjar.weixin.common.session.StandardSessionManager}
   * </pre>
   *
   * @param sessionManager 会话管理器
   */
  void setSessionManager(WxSessionManager sessionManager);

  /**
   * 上传部门列表覆盖企业号上的部门信息
   *
   * @param mediaId 媒体id
   * @return the string
   * @throws WxErrorException the wx error exception
   */
  String replaceParty(String mediaId) throws WxErrorException;

  /**
   * 上传用户列表，增量更新成员
   *
   * @param mediaId 媒体id
   * @return jobId 异步任务id
   * @throws WxErrorException the wx error exception
   */
  String syncUser(String mediaId) throws WxErrorException;

  /**
   * 上传用户列表覆盖企业号上的用户信息
   *
   * @param mediaId 媒体id
   * @return the string
   * @throws WxErrorException the wx error exception
   */
  String replaceUser(String mediaId) throws WxErrorException;

  /**
   * 获取异步任务结果
   *
   * @param jobId 异步任务id
   * @return the task result
   * @throws WxErrorException the wx error exception
   */
  String getTaskResult(String jobId) throws WxErrorException;

  /**
   * 初始化http请求对象
   */
  void initHttp();

  /**
   * 获取WxCpConfigStorage 对象
   *
   * @return WxCpConfigStorage wx cp config storage
   */
  WxCpConfigStorage getWxCpConfigStorage();

  /**
   * 注入 {@link WxCpConfigStorage} 的实现
   *
   * @param wxConfigProvider 配置对象
   */
  void setWxCpConfigStorage(WxCpConfigStorage wxConfigProvider);

  /**
   * 构造扫码登录链接 - 构造独立窗口登录二维码
   *
   * @param redirectUri 重定向地址，需要进行UrlEncode
   * @param state       用于保持请求和回调的状态，授权请求后原样带回给企业。该参数可用于防止csrf攻击（跨站请求伪造攻击），建议企业带上该参数，可设置为简单的随机数加session进行校验
   * @return . string
   */
  String buildQrConnectUrl(String redirectUri, String state);

  /**
   * 获取部门相关接口的服务类对象
   *
   * @return the department service
   */
  WxCpDepartmentService getDepartmentService();

  /**
   * 获取媒体相关接口的服务类对象
   *
   * @return the media service
   */
  WxCpMediaService getMediaService();

  /**
   * 获取菜单相关接口的服务类对象
   *
   * @return the menu service
   */
  WxCpMenuService getMenuService();

  /**
   * 获取Oauth2相关接口的服务类对象
   *
   * @return the oauth 2 service
   */
  WxCpOAuth2Service getOauth2Service();

  /**
   * 获取标签相关接口的服务类对象
   *
   * @return the tag service
   */
  WxCpTagService getTagService();

  /**
   * 获取用户相关接口的服务类对象
   *
   * @return the user service
   */
  WxCpUserService getUserService();

  /**
   * Gets external contact service.
   *
   * @return the external contact service
   */
  WxCpExternalContactService getExternalContactService();

  /**
   * 获取群聊服务
   *
   * @return 群聊服务 chat service
   */
  WxCpChatService getChatService();

  /**
   * 获取任务卡片服务
   *
   * @return 任务卡片服务 task card service
   */
  WxCpTaskCardService getTaskCardService();

  /**
   * Gets agent service.
   *
   * @return the agent service
   */
  WxCpAgentService getAgentService();

  /**
   * Gets message service.
   *
   * @return the message service
   */
  WxCpMessageService getMessageService();

  /**
   * 获取OA相关接口的服务类对象.
   *
   * @return the oa service
   */
  WxCpOaService getOaService();

  /**
   * 获取家校应用复学码相关接口的服务类对象
   *
   * @return school service
   */
  WxCpSchoolService getSchoolService();

  /**
   * 获取家校沟通相关接口的服务类对象
   *
   * @return school user service
   */
  WxCpSchoolUserService getSchoolUserService();

  /**
   * 获取家校应用健康上报的服务类对象
   *
   * @return school health service
   */
  WxCpSchoolHealthService getSchoolHealthService();

  /**
   * 获取直播相关接口的服务类对象
   *
   * @return the Living service
   */
  WxCpLivingService getLivingService();

  /**
   * 获取OA 自建应用相关接口的服务类对象
   *
   * @return oa agent service
   */
  WxCpOaAgentService getOaAgentService();

  /**
   * 获取OA效率工具 微盘的服务类对象
   *
   * @return oa we drive service
   */
  WxCpOaWeDriveService getOaWeDriveService();

  /**
   * 获取会话存档相关接口的服务类对象
   *
   * @return msg audit service
   */
  WxCpMsgAuditService getMsgAuditService();

  /**
   * 获取日历相关接口的服务类对象
   *
   * @return the oa calendar service
   */
  WxCpOaCalendarService getOaCalendarService();

  /**
   * 获取会议室相关接口的服务类对象
   *
   * @return the oa meetingroom service
   */
  WxCpOaMeetingRoomService getOaMeetingRoomService();

  /**
   * 获取日程相关接口的服务类对象
   *
   * @return the oa schedule service
   */
  WxCpOaScheduleService getOaScheduleService();

  /**
   * 获取群机器人消息推送服务
   *
   * @return 群机器人消息推送服务 group robot service
   */
  WxCpGroupRobotService getGroupRobotService();

  /**
   * 获取工作台服务
   *
   * @return the workbench service
   */
  WxCpAgentWorkBenchService getWorkBenchService();

  /**
   * 获取微信客服服务
   *
   * @return 微信客服服务 kf service
   */
  WxCpKfService getKfService();

  /**
   * http请求对象
   *
   * @return the request http
   */
  RequestHttp<?, ?> getRequestHttp();

  /**
   * Sets user service.
   *
   * @param userService the user service
   */
  void setUserService(WxCpUserService userService);

  /**
   * Sets department service.
   *
   * @param departmentService the department service
   */
  void setDepartmentService(WxCpDepartmentService departmentService);

  /**
   * Sets media service.
   *
   * @param mediaService the media service
   */
  void setMediaService(WxCpMediaService mediaService);

  /**
   * Sets menu service.
   *
   * @param menuService the menu service
   */
  void setMenuService(WxCpMenuService menuService);

  /**
   * Sets oauth 2 service.
   *
   * @param oauth2Service the oauth 2 service
   */
  void setOauth2Service(WxCpOAuth2Service oauth2Service);

  /**
   * Sets tag service.
   *
   * @param tagService the tag service
   */
  void setTagService(WxCpTagService tagService);

  /**
   * Sets kf service.
   *
   * @param kfService the kf service
   */
  void setKfService(WxCpKfService kfService);

  /**
   * 获取异步导出服务
   *
   * @return 异步导出服务 export service
   */
  WxCpExportService getExportService();

  /**
   * 设置异步导出服务
   *
   * @param exportService 异步导出服务
   */
  void setExportService(WxCpExportService exportService);

  /**
   * 相关接口的服务类对象
   *
   * @return the meeting service
   */
  WxCpMeetingService getMeetingService();

  /**
   * 企业互联的服务类对象
   *
   * @return
   */
  WxCpCorpGroupService getCorpGroupService();

  /**
   * 获取智能机器人服务
   *
   * @return 智能机器人服务 intelligent robot service
   */
  WxCpIntelligentRobotService getIntelligentRobotService();
}
