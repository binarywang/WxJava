package me.chanjar.weixin.open.api;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
import me.chanjar.weixin.common.bean.result.WxMinishopImageUploadResult;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.open.bean.WxOpenCreateResult;
import me.chanjar.weixin.open.bean.WxOpenGetResult;
import me.chanjar.weixin.open.bean.WxOpenMaCodeTemplate;
import me.chanjar.weixin.open.bean.ma.WxOpenMaApplyOrderPathInfo;
import me.chanjar.weixin.open.bean.message.WxOpenXmlMessage;
import me.chanjar.weixin.open.bean.minishop.*;
import me.chanjar.weixin.open.bean.minishop.coupon.WxMinishopCoupon;
import me.chanjar.weixin.open.bean.minishop.coupon.WxMinishopCouponStock;
import me.chanjar.weixin.open.bean.minishop.goods.*;
import me.chanjar.weixin.open.bean.minishop.limitdiscount.LimitDiscountGoods;
import me.chanjar.weixin.open.bean.result.*;
import me.chanjar.weixin.open.bean.tcb.ShareCloudBaseEnvRequest;
import me.chanjar.weixin.open.bean.tcb.ShareCloudBaseEnvResponse;
import me.chanjar.weixin.open.bean.tcbComponent.GetShareCloudBaseEnvResponse;
import me.chanjar.weixin.open.bean.tcbComponent.GetTcbEnvListResponse;

import java.io.File;
import java.util.List;

/**
 * .
 *
 * @author <a href="https://github.com/007gzs">007</a>
 */
public interface WxOpenComponentService {
  /**
   * The constant API_COMPONENT_TOKEN_URL.
   */
  String API_COMPONENT_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/component/api_component_token";
  /**
   * 启动ticket推送服务
   */
  String API_START_PUSH_TICKET = "https://api.weixin.qq.com/cgi-bin/component/api_start_push_ticket";
  /**
   * The constant API_CREATE_PREAUTHCODE_URL.
   */
  String API_CREATE_PREAUTHCODE_URL = "https://api.weixin.qq.com/cgi-bin/component/api_create_preauthcode";
  /**
   * The constant API_QUERY_AUTH_URL.
   */
  String API_QUERY_AUTH_URL = "https://api.weixin.qq.com/cgi-bin/component/api_query_auth";
  /**
   * The constant API_AUTHORIZER_TOKEN_URL.
   */
  String API_AUTHORIZER_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/component/api_authorizer_token";
  /**
   * The constant API_GET_AUTHORIZER_INFO_URL.
   */
  String API_GET_AUTHORIZER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/component/api_get_authorizer_info";
  /**
   * The constant GET_AUTHORIZER_OPTION_URL.
   */
  String GET_AUTHORIZER_OPTION_URL = "https://api.weixin.qq.com/cgi-bin/component/get_authorizer_option";
  /**
   * The constant SET_AUTHORIZER_OPTION_URL.
   */
  String SET_AUTHORIZER_OPTION_URL = "https://api.weixin.qq.com/cgi-bin/component/set_authorizer_option";
  /**
   * The constant API_GET_AUTHORIZER_LIST.
   */
  String API_GET_AUTHORIZER_LIST = "https://api.weixin.qq.com/cgi-bin/component/api_get_authorizer_list";

  /**
   * The constant COMPONENT_LOGIN_PAGE_URL.
   */
  String COMPONENT_LOGIN_PAGE_URL = "https://mp.weixin.qq.com/cgi-bin/componentloginpage?component_appid=%s&pre_auth_code=%s&redirect_uri=%s&auth_type=xxx&biz_appid=xxx";

  /**
   * 手机端打开授权链接.
   */
  String COMPONENT_MOBILE_LOGIN_PAGE_URL = "https://open.weixin.qq.com/wxaopen/safe/bindcomponent?action=bindcomponent&no_scan=1&component_appid=%s&pre_auth_code=%s&redirect_uri=%s&auth_type=xxx&biz_appid=xxx#wechat_redirect";
  /**
   * The constant CONNECT_OAUTH2_AUTHORIZE_URL.
   */
  String CONNECT_OAUTH2_AUTHORIZE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s&component_appid=%s#wechat_redirect";

  /**
   * 用code换取oauth2的access token.
   */
  String OAUTH2_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/component/access_token?appid=%s&code=%s&grant_type=authorization_code&component_appid=%s";
  /**
   * 刷新oauth2的access token.
   */
  String OAUTH2_REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/component/refresh_token?appid=%s&grant_type=refresh_token&refresh_token=%s&component_appid=%s";

  /**
   * The constant MINIAPP_JSCODE_2_SESSION.
   */
  String MINIAPP_JSCODE_2_SESSION = "https://api.weixin.qq.com/sns/component/jscode2session?appid=%s&js_code=%s&grant_type=authorization_code&component_appid=%s";

  /**
   * The constant CREATE_OPEN_URL.
   */
  String CREATE_OPEN_URL = "https://api.weixin.qq.com/cgi-bin/open/create";

  /**
   * The constant BIND_OPEN_URL.
   */
  String BIND_OPEN_URL = "https://api.weixin.qq.com/cgi-bin/open/bind";

  /**
   * The constant UNBIND_OPEN_URL.
   */
  String UNBIND_OPEN_URL = "https://api.weixin.qq.com/cgi-bin/open/unbind";

  /**
   * The constant GET_OPEN_URL.
   */
  String GET_OPEN_URL = "https://api.weixin.qq.com/cgi-bin/open/get";

  /**
   * 查询公众号/小程序是否绑定 open 帐号
   */
  String HAVE_OPEN_URL = "https://api.weixin.qq.com/cgi-bin/open/have";

  /**
   * 快速创建小程序接口.
   */
  String FAST_REGISTER_WEAPP_URL = "https://api.weixin.qq.com/cgi-bin/component/fastregisterweapp?action=create";
  /**
   * The constant FAST_REGISTER_WEAPP_SEARCH_URL.
   */
  String FAST_REGISTER_WEAPP_SEARCH_URL = "https://api.weixin.qq.com/cgi-bin/component/fastregisterweapp?action=search";

  /**
   * 快速创建个人小程序接口.
   */
  String FAST_REGISTER_PERSONAL_WEAPP_URL = "https://api.weixin.qq.com/wxa/component/fastregisterpersonalweapp?action=create";

  /**
   * 查询快速创建个人小程序任务状态接口.
   */
  String FAST_REGISTER_PERSONAL_WEAPP_SEARCH_URL = "https://api.weixin.qq.com/wxa/component/fastregisterpersonalweapp?action=query";

  /**
   * 快速创建试用小程序接口.
   */
  String FAST_REGISTER_BETA_WEAPP_URL = "https://api.weixin.qq.com/wxa/component/fastregisterbetaweapp";

  /**
   * 代小程序实现业务.
   * 小程序代码模版库管理：https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1506504150_nMMh6&token=&lang=zh_CN
   * access_token 为 component_access_token
   */
  String GET_TEMPLATE_DRAFT_LIST_URL = "https://api.weixin.qq.com/wxa/gettemplatedraftlist";
  /**
   * The constant GET_TEMPLATE_LIST_URL.
   */
  String GET_TEMPLATE_LIST_URL = "https://api.weixin.qq.com/wxa/gettemplatelist";
  /**
   * The constant ADD_TO_TEMPLATE_URL.
   */
  String ADD_TO_TEMPLATE_URL = "https://api.weixin.qq.com/wxa/addtotemplate";
  /**
   * The constant DELETE_TEMPLATE_URL.
   */
  String DELETE_TEMPLATE_URL = "https://api.weixin.qq.com/wxa/deletetemplate";

  String REGISTER_SHOP_URL = "https://api.weixin.qq.com/product/register/register_shop";

  String CHECK_SHOP_AUDITSTATUS_URL = "https://api.weixin.qq.com/product/register/check_audit_status";

  String SUBMIT_MERCHANTINFO_URL = "https://api.weixin.qq.com/product/register/submit_merchantinfo";

  String SUBMIT_BASICINFO_URL = "https://api.weixin.qq.com/product/register/submit_basicinfo";

  String UPLOAD_IMAGE_URL = "https://api.weixin.qq.com/product/img/upload";

  String MINISHOP_CATEGORY_GET_URL = "https://api.weixin.qq.com/product/category/get";

  String MINISHOP_BRAND_GET_URL = "https://api.weixin.qq.com/product/brand/get";

  String MINISHOP_DELIVERY_TEMPLATE_GET_URL = "https://api.weixin.qq.com/product/delivery/get_freight_template";

  String MINISHOP_SHOPCATEGORY_GET_URL = "https://api.weixin.qq.com/product/store/get_shopcat";

  String MINISHOP_CREATE_COUPON_URL = "https://api.weixin.qq.com/product/coupon/create";

  String MINISHOP_GET_COUPON_LIST = "https://api.weixin.qq.com/product/coupon/get_list";

  String MINISHOP_PUSH_COUPON = "https://api.weixin.qq.com/product/coupon/push";

  String MINISHOP_UPDATE_COUPON_URL = "https://api.weixin.qq.com/product/coupon/update";

  String MINISHOP_UPDATE_COUPON_STATUS_URL = "https://api.weixin.qq.com/product/coupon/update_status";

  String MINISHOP_GET_DELIVERY_COMPANY_URL = "https://api.weixin.qq.com/product/delivery/get_company_list";

  String BATCH_GET_ENVID_URL = "https://api.weixin.qq.com/componenttcb/batchgetenvid";

  String DESCRIBE_ENVS_URL = "https://api.weixin.qq.com/componenttcb/describeenvs";

  String MODIFY_ENV_URL = "https://api.weixin.qq.com/tcb/modifyenv";

  String BATCH_SHARE_ENV = "https://api.weixin.qq.com/componenttcb/batchshareenv";

  String COMPONENT_CLEAR_QUOTA_URL = "https://api.weixin.qq.com/cgi-bin/component/clear_quota/v2";

  /**
   * 设置第三方平台服务器域名
   */
  String API_MODIFY_WXA_SERVER_DOMAIN = "https://api.weixin.qq.com/cgi-bin/component/modify_wxa_server_domain";

  /**
   * 获取第三方平台业务域名校验文件
   */
  String API_GET_DOMAIN_CONFIRM_FILE = "https://api.weixin.qq.com/cgi-bin/component/get_domain_confirmfile";

  /**
   * 设置第三方平台业务域名
   */
  String API_MODIFY_WXA_JUMP_DOMAIN = "https://api.weixin.qq.com/cgi-bin/component/modify_wxa_jump_domain";

  /**
   * Gets wx mp service by appid.
   *
   * @param appid the appid
   * @return the wx mp service by appid
   */
  WxOpenMpService getWxMpServiceByAppid(String appid);

  /**
   * 获取指定appid的开放平台小程序服务（继承一般小程序服务能力）.
   *
   * @param appid .
   * @return . wx ma service by appid
   */
  WxOpenMaService getWxMaServiceByAppid(String appid);

  /**
   * 获取指定appid的快速创建的小程序服务.
   *
   * @param appid .
   * @return . wx fast ma service by appid
   * @deprecated 2021-06-23 本接口原有方法并非仅快速创建小程序的专用接口，普通小程序授权到第三方平台皆可使用，所以请使用 {@link WxOpenMaBasicService} 类替代。获取方法: WxOpenMaService.getBasicService()
   */
  @Deprecated
  WxOpenFastMaService getWxFastMaServiceByAppid(String appid);


  /**
   * 获取指定appid的小商店服务
   *
   * @param appid
   * @return
   */
  WxOpenMinishopService getWxMinishopServiceByAppid(String appid);

  /**
   * Gets wx open config storage.
   *
   * @return the wx open config storage
   */
  WxOpenConfigStorage getWxOpenConfigStorage();

  /**
   * Check signature boolean.
   *
   * @param timestamp the timestamp
   * @param nonce     the nonce
   * @param signature the signature
   * @return the boolean
   */
  boolean checkSignature(String timestamp, String nonce, String signature);

  /**
   * 启动ticket推送服务 该 API 用于启动ticket推送服务
   *
   * @throws WxErrorException 如果调用失败返回此异常
   */
  void startPushTicket() throws WxErrorException;

  /**
   * Gets component access token.
   *
   * @param forceRefresh the force refresh
   * @return the component access token
   * @throws WxErrorException the wx error exception
   */
  String getComponentAccessToken(boolean forceRefresh) throws WxErrorException;

  /**
   * Post string.
   *
   * @param uri      the uri
   * @param postData the post data
   * @return the string
   * @throws WxErrorException the wx error exception
   */
  String post(String uri, String postData) throws WxErrorException;

  /**
   * Post string.
   *
   * @param uri            the uri
   * @param postData       the post data
   * @param accessTokenKey the access token key
   * @return the string
   * @throws WxErrorException the wx error exception
   */
  String post(String uri, String postData, String accessTokenKey) throws WxErrorException;

  String post(String uri, String postData, String accessTokenKey, String accessToken) throws WxErrorException;

  /**
   * Get string.
   *
   * @param uri the uri
   * @return the string
   * @throws WxErrorException the wx error exception
   */
  String get(String uri) throws WxErrorException;

  /**
   * Get string.
   *
   * @param uri            the uri
   * @param accessTokenKey the access token key
   * @return the string
   * @throws WxErrorException the wx error exception
   */
  String get(String uri, String accessTokenKey) throws WxErrorException;

  /**
   * 获取用户授权页URL（来路URL和成功跳转URL 的域名都需要为三方平台设置的 登录授权的发起页域名）.
   *
   * @param redirectUri the redirect uri
   * @return the pre auth url
   * @throws WxErrorException the wx error exception
   */
  String getPreAuthUrl(String redirectUri) throws WxErrorException;

  /**
   * .
   *
   * @param redirectUri the redirect uri
   * @param authType    要授权的帐号类型：1则商户点击链接后，手机端仅展示公众号、2表示仅展示小程序，3表示公众号和小程序都展示。如果为未指定，则默认小程序和公众号都展示。第三方平台开发者可以使用本字段来控制授权的帐号类型。
   * @param bizAppid    指定授权唯一的小程序或公众号                 注：authType、bizAppid 互斥。
   * @return the pre auth url
   * @throws WxErrorException the wx error exception
   */
  String getPreAuthUrl(String redirectUri, String authType, String bizAppid) throws WxErrorException;

  /**
   * 获取预授权链接（手机端预授权）.
   *
   * @param redirectUri .
   * @return . mobile pre auth url
   * @throws WxErrorException .
   */
  String getMobilePreAuthUrl(String redirectUri) throws WxErrorException;

  /**
   * 获取预授权链接（手机端预授权）.
   *
   * @param redirectUri .
   * @param authType    .
   * @param bizAppid    .
   * @return . mobile pre auth url
   * @throws WxErrorException .
   */
  String getMobilePreAuthUrl(String redirectUri, String authType, String bizAppid) throws WxErrorException;

  /**
   * Route string.
   *
   * @param wxMessage the wx message
   * @return the string
   * @throws WxErrorException the wx error exception
   */
  String route(WxOpenXmlMessage wxMessage) throws WxErrorException;

  /**
   * 使用授权码换取公众号或小程序的接口调用凭据和授权信息.
   *
   * @param authorizationCode the authorization code
   * @return the query auth
   * @throws WxErrorException the wx error exception
   */
  WxOpenQueryAuthResult getQueryAuth(String authorizationCode) throws WxErrorException;

  /**
   * 获取授权方的帐号基本信息.
   *
   * @param authorizerAppid the authorizer appid
   * @return the authorizer info
   * @throws WxErrorException the wx error exception
   */
  WxOpenAuthorizerInfoResult getAuthorizerInfo(String authorizerAppid) throws WxErrorException;

  /**
   * 获取授权方的选项设置信息.
   *
   * @param authorizerAppid the authorizer appid
   * @param optionName      the option name
   * @return the authorizer option
   * @throws WxErrorException the wx error exception
   */
  WxOpenAuthorizerOptionResult getAuthorizerOption(String authorizerAppid, String optionName) throws WxErrorException;

  /**
   * 获取所有授权方列表.
   *
   * @param begin the begin
   * @param len   the len
   * @return the authorizer list
   * @throws WxErrorException the wx error exception
   */
  WxOpenAuthorizerListResult getAuthorizerList(int begin, int len) throws WxErrorException;

  /**
   * 设置授权方的选项信息.
   *
   * @param authorizerAppid the authorizer appid
   * @param optionName      the option name
   * @param optionValue     the option value
   * @throws WxErrorException the wx error exception
   */
  void setAuthorizerOption(String authorizerAppid, String optionName, String optionValue) throws WxErrorException;

  /**
   * Gets authorizer access token.
   *
   * @param appid        the appid
   * @param forceRefresh the force refresh
   * @return the authorizer access token
   * @throws WxErrorException the wx error exception
   */
  String getAuthorizerAccessToken(String appid, boolean forceRefresh) throws WxErrorException;

  /**
   * Oauth 2 get access token wx mp o auth 2 access token.
   *
   * @param appid the appid
   * @param code  the code
   * @return the wx mp o auth 2 access token
   * @throws WxErrorException the wx error exception
   * @see WxMpService#getOAuth2Service()
   * @deprecated 2021-05-21: 已修正公众号相关接口,请使用:WxOpenComponentService.getWxMpServiceByAppid(mpAppId).getOAuth2Service().getAccessToken(code)
   */
  @Deprecated
  WxOAuth2AccessToken oauth2getAccessToken(String appid, String code) throws WxErrorException;

  /**
   * Check signature boolean.
   *
   * @param appId     the app id
   * @param timestamp the timestamp
   * @param nonce     the nonce
   * @param signature the signature
   * @return the boolean
   */
  boolean checkSignature(String appId, String timestamp, String nonce, String signature);

  /**
   * Oauth 2 refresh access token wx mp o auth 2 access token.
   *
   * @param appid        the appid
   * @param refreshToken the refresh token
   * @return the wx mp o auth 2 access token
   * @throws WxErrorException the wx error exception
   */
  WxOAuth2AccessToken oauth2refreshAccessToken(String appid, String refreshToken) throws WxErrorException;

  /**
   * Oauth 2 build authorization url string.
   *
   * @param appid       the appid
   * @param redirectUri the redirect uri
   * @param scope       the scope
   * @param state       the state
   * @return the string
   * @see WxMpService#getOAuth2Service()
   * @deprecated 2021-05-21: 已修正公众号相关接口,请使用:WxOpenCommpentService.getWxMpServiceByAppid(mpAppId).getOAuth2Service().buildAuthorizationUrl(redirectUri, scope, state)
   */
  @Deprecated
  String oauth2buildAuthorizationUrl(String appid, String redirectUri, String scope, String state);

  /**
   * Miniapp jscode 2 session wx ma jscode 2 session result.
   *
   * @param appId  the app id
   * @param jsCode the js code
   * @return the wx ma jscode 2 session result
   * @throws WxErrorException the wx error exception
   */
  WxMaJscode2SessionResult miniappJscode2Session(String appId, String jsCode) throws WxErrorException;

  /**
   * 获取草稿箱内的所有临时代码草稿.
   *
   * @return 草稿箱代码模板列表 （draftId）
   * @throws WxErrorException 获取失败时返回，具体错误码请看此接口的注释文档
   */
  List<WxOpenMaCodeTemplate> getTemplateDraftList() throws WxErrorException;

  /**
   * 获取代码模版库中的所有小程序代码模版.
   *
   * @return 小程序代码模版列表 （templateId）
   * @throws WxErrorException 获取失败时返回，具体错误码请看此接口的注释文档
   * @see #getTemplateList(Integer)
   */
  @Deprecated
  List<WxOpenMaCodeTemplate> getTemplateList() throws WxErrorException;

  /**
   * 获取代码模版库中的所有小程序代码模版.
   * 文档:https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/2.0/api/ThirdParty/code_template/gettemplatelist.html
   *
   * @param templateType 模板类型,可空,默认全部,填0普通模,1标准模板
   * @return 小程序代码模版列表 （templateId）
   * @throws WxErrorException 获取失败时返回，具体错误码请看此接口的注释文档
   */
  List<WxOpenMaCodeTemplate> getTemplateList(Integer templateType) throws WxErrorException;

  /**
   * 请参考并使用 {@link #addToTemplate(long, int)}.
   * 将草稿箱的草稿选为小程序代码模版.
   *
   * @param draftId 草稿ID，本字段可通过“获取草稿箱内的所有临时代码草稿”接口获得
   * @throws WxErrorException 操作失败时抛出，具体错误码请看此接口的注释文档
   * @see #getTemplateDraftList #getTemplateDraftList
   */
  @Deprecated
  void addToTemplate(long draftId) throws WxErrorException;

  /**
   * https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/2.0/api/ThirdParty/code_template/addtotemplate.html
   * 将草稿添加到代码模板库.
   *
   * @param draftId      草稿ID，本字段可通过“获取草稿箱内的所有临时代码草稿”接口获得
   * @param templateType 代码模版类型，【普通模板:0, 标准模板：1】
   * @throws WxErrorException 操作失败时抛出，具体错误码请看此接口的注释文档
   * @see #getTemplateDraftList #getTemplateDraftList
   */
  void addToTemplate(long draftId, int templateType) throws WxErrorException;

  /**
   * 删除指定小程序代码模版.
   *
   * @param templateId 要删除的模版ID
   * @throws WxErrorException 操作失败时抛出，具体错误码请看此接口的注释文档
   * @see #getTemplateList #getTemplateList
   */
  void deleteTemplate(long templateId) throws WxErrorException;

  /**
   * https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1498704199_1bcax&token=6df5e3650041eff2cd3ec3662425ad8d7beec8d9&lang=zh_CN
   * 创建 开放平台帐号并绑定公众号/小程序.
   * https://api.weixin.qq.com/cgi-bin/open/create
   *
   * @param appId     公众号/小程序的appId
   * @param appIdType appId类型   me.chanjar.weixin.common.api.WxConsts.AppIdType   mp-公众号  mini-小程序
   * @return . wx open create result
   * @throws WxErrorException .
   */
  WxOpenCreateResult createOpenAccount(String appId, String appIdType) throws WxErrorException;

  /**
   * https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/api/account/bind.html
   * 将公众号/小程序绑定到开放平台帐号下
   *
   * @param appId     公众号/小程序的appId
   * @param appIdType appId类型   me.chanjar.weixin.common.api.WxConsts.AppIdType   mp-公众号  mini-小程序
   * @param openAppid 开放平台帐号 appid，由创建开发平台帐号接口返回
   * @return the boolean
   * @throws WxErrorException the wx error exception
   */
  Boolean bindOpenAccount(String appId, String appIdType, String openAppid) throws WxErrorException;

  /**
   * https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/api/account/unbind.html
   * 将公众号/小程序从开放平台帐号下解绑
   *
   * @param appId     公众号/小程序的appId
   * @param appIdType appId类型   me.chanjar.weixin.common.api.WxConsts.AppIdType   mp-公众号  mini-小程序
   * @param openAppid 开放平台帐号 appid，由创建开发平台帐号接口返回
   * @return the boolean
   * @throws WxErrorException the wx error exception
   */
  Boolean unbindOpenAccount(String appId, String appIdType, String openAppid) throws WxErrorException;

  /**
   * https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/api/account/get.html
   * 获取公众号/小程序所绑定的开放平台帐号
   *
   * @param appId     公众号/小程序的appId
   * @param appIdType appId类型   me.chanjar.weixin.common.api.WxConsts.AppIdType   mp-公众号  mini-小程序
   * @return 开放平台帐号 appid，由创建开发平台帐号接口返回
   * @throws WxErrorException the wx error exception
   */
  WxOpenGetResult getOpenAccount(String appId, String appIdType) throws WxErrorException;

  /**
   * https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/2.0/api/Mini_Program_Basic_Info/getbindopeninfo.html
   * 查询公众号/小程序是否绑定 open 帐号
   *
   * @return 是否绑定 open 帐号，true表示绑定；false表示未绑定任何 open 帐号
   * @throws WxErrorException the wx error exception
   */
  WxOpenHaveResult haveOpen() throws WxErrorException;


  /**
   * https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=21538208049W8uwq&token=&lang=zh_CN
   * 第三方平台快速创建小程序.
   * 注意：创建任务逻辑串行，单次任务结束后才可以使用相同信息下发第二次任务，请注意规避任务阻塞
   *
   * @param name               企业名（需与工商部门登记信息一致）
   * @param code               企业代码
   * @param codeType           企业代码类型 1：统一社会信用代码（18位） 2：组织机构代码（9位xxxxxxxx-x） 3：营业执照注册号(15位)
   * @param legalPersonaWechat 法人微信号
   * @param legalPersonaName   法人姓名（绑定银行卡）
   * @param componentPhone     第三方联系电话（方便法人与第三方联系）
   * @return . wx open result
   * @throws WxErrorException .
   */
  WxOpenResult fastRegisterWeapp(String name, String code, String codeType, String legalPersonaWechat, String legalPersonaName, String componentPhone) throws WxErrorException;

  /**
   * https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=21538208049W8uwq&token=&lang=zh_CN
   * 查询第三方平台快速创建小程序的任务状态
   * 注意：该接口只提供当下任务结果查询，不建议过分依赖该接口查询所创建小程序。
   * 小程序的成功状态可在第三方服务器中自行对账、查询。
   * 不要频繁调用search接口，消息接收需通过服务器查看。调用search接口会消耗接口整体调用quato
   *
   * @param name               企业名（需与工商部门登记信息一致）
   * @param legalPersonaWechat 法人微信号
   * @param legalPersonaName   法人姓名（绑定银行卡）
   * @return the wx open result
   * @throws WxErrorException .
   */
  WxOpenResult fastRegisterWeappSearch(String name, String legalPersonaWechat, String legalPersonaName) throws WxErrorException;


  /**
   * https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/2.0/api/Register_Mini_Programs/fastregisterpersonalweapp.html
   * 快速创建个人小程序
   *
   * @param idname         个人用户名字
   * @param wxuser         个人用户微信号
   * @param componentPhone 第三方联系电话
   * @return the wx open result
   * @throws WxErrorException
   */
  WxOpenRegisterPersonalWeappResult fastRegisterPersonalWeapp(String idname, String wxuser, String componentPhone) throws WxErrorException;

  /**
   * https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/2.0/api/Register_Mini_Programs/fastregisterpersonalweapp.html
   * 查询个人小程序注册任务状态
   *
   * @param taskid 任务ID
   * @return the wx open result
   * @throws WxErrorException
   */
  WxOpenRegisterPersonalWeappResult fastRegisterPersonalWeappSearch(String taskid) throws WxErrorException;

  /**
   * https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/2.0/api/beta_Mini_Programs/fastregister.html
   * 注册试用小程序
   *
   * @param name   小程序名称
   * @param openid 微信用户的openid（不是微信号）
   * @return the wx open result
   * @throws WxErrorException
   */
  WxOpenRegisterBetaWeappResult fastRegisterBetaWeapp(String name, String openid) throws WxErrorException;

  /**
   * https://api.weixin.qq.com/product/register/register_shop?component_access_token=xxxxxxxxx
   * 注册小商店账号
   *
   * @param wxName           微信号（必填）
   * @param idCardName       身份证姓名（必填）
   * @param idCardNumber     身份证号（必填）
   * @param channelId        渠道号，服务商后台生成渠道信息。（选填）
   * @param apiOpenstoreType 1-整店打包（开通小商店），2-组件开放（开通小程序，并且已经完整的嵌入电商功能）（必填）
   * @param authPageUrl      授权url（选填）
   * @return the wx open result
   * @throws WxErrorException
   */
  WxOpenResult registerShop(String wxName, String idCardName, String idCardNumber, String channelId, Integer apiOpenstoreType, String authPageUrl) throws WxErrorException;


  /**
   * https://api.weixin.qq.com/product/register/check_audit_status
   * 异步状态查询
   *
   * @param wxName 微信号
   * @return
   */
  String checkAuditStatus(String wxName) throws WxErrorException;


  /**
   * 已经获取到小商店的appId，那么需要通过accesstoken来获取该小商店的状态
   *
   * @param appId
   * @param wxName
   * @return
   * @throws WxErrorException
   */
  String checkAuditStatus(String appId, String wxName) throws WxErrorException;

  /**
   * @param appId
   * @param subjectType
   * @param busiLicense
   * @param organizationCodeInfo
   * @param idcardInfo
   * @param superAdministratorInfo
   * @param merchantShoprtName
   * @return
   */
  WxOpenResult submitMerchantInfo(String appId, String subjectType, MinishopBusiLicense busiLicense, MinishopOrganizationCodeInfo organizationCodeInfo, MinishopIdcardInfo idcardInfo, MinishopSuperAdministratorInfo superAdministratorInfo, String merchantShoprtName) throws WxErrorException;

  /**
   * @param appId
   * @param nameInfo
   * @param returnInfo
   * @return
   * @throws WxErrorException
   */
  WxOpenResult submitBasicInfo(String appId, MinishopNameInfo nameInfo, MinishopReturnInfo returnInfo) throws WxErrorException;


  /**
   * @param height
   * @param width
   * @param file
   * @return
   * @throws WxErrorException
   */
  WxMinishopImageUploadResult uploadMinishopImagePicFile(String appId, Integer height, Integer width, File file) throws WxErrorException;


  /**
   * 获取小商店的类目详情
   *
   * @param appId：小商店APPID
   * @param fCatId：父类目ID，可先填0获取根部类目
   * @return 小商店类目信息列表
   */
  MinishopCategories getMinishopCategories(String appId, Integer fCatId) throws WxErrorException;


  /**
   * 获取小商店品牌信息
   *
   * @param appId：小商店appID
   * @return
   */
  MinishopBrandList getMinishopBrands(String appId) throws WxErrorException;


  /**
   * 获取小商店运费模版信息
   *
   * @param appId：小商店appID
   * @return
   */
  MinishopDeliveryTemplateResult getMinishopDeliveryTemplate(String appId) throws WxErrorException;


  /**
   * 获取小商店商品分类信息
   *
   * @param appId
   * @return
   */
  MinishopShopCatList getMinishopCatList(String appId) throws WxErrorException;


  /**
   * 获取小商店的快递公司列表
   *
   * @param appId
   * @return
   * @throws WxErrorException
   */
  WxMinishopAddGoodsSpuResult<List<WxMinishopDeliveryCompany>> getMinishopDeliveryCompany(String appId) throws WxErrorException;

  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
  //小商店优惠券接口

  /**
   * 创建小商店优惠券
   *
   * @param appId：小商店的appId
   * @param couponInfo：     优惠券信息
   * @return couponId: 优惠券ID
   * @throws WxErrorException
   */
  Integer minishopCreateCoupon(String appId, WxMinishopCoupon couponInfo) throws WxErrorException;


  /**
   * 与小商店对接，获取小商店的优惠券信息
   *
   * @param appId：小商店的appId
   * @param startCreateTime：优惠券创建时间的搜索开始时间
   * @param endCreateTime：优惠券创建时间的搜索结束时间
   * @param status：优惠券状态
   * @param page：第几页（最小填1）
   * @param pageSize：每页数量(不超过10,000)
   * @return
   * @throws WxErrorException
   */
  WxMinishopCouponStock minishopGetCouponList(String appId, String startCreateTime, String endCreateTime, Integer status, Integer page, Integer pageSize) throws WxErrorException;


  /**
   * 与小商店对接，将优惠券发送给某人
   *
   * @param appid：小商店appId
   * @param openId：优惠券接收人的openId
   * @param couponId:            优惠券ID
   * @return
   */
  WxOpenResult minishopPushCouponToUser(String appid, String openId, Integer couponId) throws WxErrorException;


  /**
   * 与小商店对接，更新商城优惠券
   *
   * @param appId
   * @param couponInfo
   * @return
   * @throws WxErrorException
   */
  Integer minishopUpdateCoupon(String appId, WxMinishopCoupon couponInfo) throws WxErrorException;


  /**
   * 从优惠券创建后status=1，可流转到2,4,5, COUPON_STATUS_VALID = 2 ;//生效 COUPON_STATUS_INVALID = 4 ;//已作废 COUPON_STATUS_DEL = 5;//删除
   *
   * @param appId
   * @param couponId
   * @param status
   * @return
   * @throws WxErrorException
   */
  WxOpenResult minishopUpdateCouponStatus(String appId, Integer couponId, Integer status) throws WxErrorException;


  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
  //小商店spu接口
  String MINISHOP_ADD_SPU_URL = "https://api.weixin.qq.com/product/spu/add";

  String MINISHOP_DEL_SPU_URL = "https://api.weixin.qq.com/product/spu/del";

  String MINISHOP_UPDATE_SPU_URL = "https://api.weixin.qq.com/product/spu/update";

  String MINISHOP_LISTING_SPU_URL = "https://api.weixin.qq.com/product/spu/listing";

  String MINISHOP_DELISTING_SPU_URL = "https://api.weixin.qq.com/product/spu/delisting";

  /**
   * 小商店添加商品接口，添加商品后只是添加到草稿箱，需要通过调用上架商品，并通过审核才能在商城中显示。
   *
   * @param appId
   * @param spu
   * @return
   * @throws WxErrorException
   */
  WxMinishopAddGoodsSpuResult<WxMinishopAddGoodsSpuData> minishopGoodsAddSpu(String appId, WxMinishopSpu spu) throws WxErrorException;


  /**
   * 小商店删除商品接口，直接删除，不会存在小商店回收站里面。
   *
   * @param appId
   * @param productId
   * @param outProductId
   * @return
   * @throws WxErrorException
   */
  WxOpenResult minishopGoodsDelSpu(String appId, Long productId, Long outProductId) throws WxErrorException;


  /**
   * 小商店更新商品接口，不会直接影响上架商品的信息，而是存在草稿箱，需要调用上架商品接口，并通过审核才能在商城中显示。
   *
   * @param appId
   * @param spu
   * @return
   * @throws WxErrorException
   */
  WxMinishopAddGoodsSpuResult<WxMinishopAddGoodsSpuData> minishopGoodsUpdateSpu(String appId, WxMinishopSpu spu) throws WxErrorException;


  /**
   * 上架商品。
   *
   * @param appId
   * @param productId
   * @param outProductId
   * @return
   * @throws WxErrorException
   */
  WxOpenResult minishopGoodsListingSpu(String appId, Long productId, Long outProductId) throws WxErrorException;


  /**
   * 下架商品
   *
   * @param appId
   * @param productId
   * @param outProductId
   * @return
   * @throws WxErrorException
   */
  WxOpenResult minishopGoodsDelistingSpu(String appId, Long productId, Long outProductId) throws WxErrorException;


  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  //小商店sku接口
  String MINISHOP_ADD_SKU_URL = "https://api.weixin.qq.com/product/sku/add";

  String MINISHOP_BATCH_ADD_SKU_URL = "https://api.weixin.qq.com/product/sku/batch_add";

  String MINISHOP_DEL_SKU_URL = "https://api.weixin.qq.com/product/sku/del";

  String MINISHOP_UPDATE_SKU_URL = "https://api.weixin.qq.com/product/sku/update";

  String MINISHOP_UPDATE_SKU_PRICE_URL = "https://api.weixin.qq.com/product/sku/update_price";

  String MINISHOP_UPDATE_SKU_STOCK_URL = "https://api.weixin.qq.com/product/stock/update";

  /**
   * 小商店新增sku信息
   *
   * @param appId
   * @param sku
   * @return
   * @throws WxErrorException
   */
  WxMinishopAddGoodsSpuResult<WxMinishopAddGoodsSkuData> minishiopGoodsAddSku(String appId, WxMinishopSku sku) throws WxErrorException;


  /**
   * 小商店批量新增sku信息
   *
   * @param appId
   * @param skuList
   * @return
   * @throws WxErrorException
   */
  WxOpenResult minishopGoodsBatchAddSku(String appId, List<WxMinishopSku> skuList) throws WxErrorException;


  /**
   * 小商店删除sku消息
   *
   * @param appId
   * @param productId
   * @param outProductId
   * @param outSkuId
   * @param skuId
   * @return
   * @throws WxErrorException
   */
  WxOpenResult minishopGoodsDelSku(String appId, Long productId, Long outProductId, String outSkuId, Long skuId) throws WxErrorException;


  /**
   * 小商店更新sku
   *
   * @param appId
   * @param sku
   * @return
   * @throws WxErrorException
   */
  WxOpenResult minishopGoodsUpdateSku(String appId, WxMinishopSku sku) throws WxErrorException;


  /**
   * 小商店更新sku价格
   *
   * @param appId
   * @param productId
   * @param outProductId
   * @param outSkuId
   * @param skuId
   * @param salePrice
   * @param marketPrice
   * @return
   * @throws WxErrorException
   */
  WxOpenResult minishopGoodsUpdateSkuPrice(String appId, Long productId, Long outProductId, String outSkuId, Long skuId, Long salePrice, Long marketPrice) throws WxErrorException;


  /**
   * 小商店更新sku库存
   *
   * @param appId
   * @param productId
   * @param outProductId
   * @param outSkuId
   * @param skuId
   * @param type
   * @param stockNum
   * @return
   * @throws WxErrorException
   */
  WxOpenResult minishopGoodsUpdateSkuStock(String appId, Long productId, Long outProductId, String outSkuId, Long skuId, Integer type, Integer stockNum) throws WxErrorException;


  /**
   * 小商店通用Post接口
   *
   * @param appId
   * @param url
   * @param requestParam
   * @return
   * @throws WxErrorException
   */
  String minishopCommonPost(String appId, String url, String requestParam) throws WxErrorException;


  //////////////////////////////////////////////////////////////
  //商品抢购任务-秒杀活动
  String API_MINISHOP_ADD_LIMIT_DISCOUNT_URL = "https://api.weixin.qq.com/product/limiteddiscount/add/";

  String API_MINISHOP_GET_LIMIT_DISCOUNT_URL = "https://api.weixin.qq.com/product/limiteddiscount/get_list/";

  String API_MINISHOP_UPDATE_LIMIT_DICOUNT_STATUS_URL = "https://api.weixin.qq.com/product/limiteddiscount/update_status/";

  /**
   * 添加抢购任务
   * 每个商品(SPU)同一时间只能有一个抢购任务。 如果当前有抢购任务没有结束，无论是否开始，都不允许创建第二个抢购任务 可以提前修改抢购任务状态为结束后，再创建新的任务。 每次创建抢购任务时，必须填充该SPU下 所有SKU的抢购信息
   *
   * @param appId
   * @param limitDiscountGoods
   * @return
   * @throws WxErrorException
   */
  Integer addLimitDiscountGoods(String appId, LimitDiscountGoods limitDiscountGoods) throws WxErrorException;

  /**
   * status为0代表 还未结束的抢购任务，无论是否开始 status为1代表已经结束的 抢购任务 如果不填status，则两种都拉取
   *
   * @param appId
   * @param status
   * @return
   */
  List<LimitDiscountGoods> getLimitDiscountList(String appId, Integer status) throws WxErrorException;


  /**
   * 修改抢购任务状态
   * 用于提前结束抢购任务，无论抢购任务是否在执行中，都可以关闭。 也可以直接删除抢购任务 注意：结束后不允许再开启，状态不可逆
   *
   * @param appId
   * @param taskId
   * @param status
   * @return
   */
  WxOpenResult updateLimitDiscountStatus(String appId, Long taskId, Integer status) throws WxErrorException;

  /**
   * 查询环境共享信息
   * https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/cloudbase-batch/env-mgnt/getShareCloudbaseEnv.html
   *
   * @param appids 要查询的appid
   * @return
   */
  GetShareCloudBaseEnvResponse getShareCloudBaseEnv(List<String> appids) throws WxErrorException;


  /**
   * 获取环境信息
   * https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/cloudbase-batch/env-mgnt/getTcbEnvList.html
   *
   * @return
   * @throws WxErrorException
   */
  GetTcbEnvListResponse getTcbEnvList() throws WxErrorException;

  /**
   * 转换云环境
   * https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/cloudbase-batch/env-mgnt/changeTcbEnv.html
   *
   * @param env 环境id
   * @return
   * @throws WxErrorException
   */
  WxOpenResult changeTcbEnv(String env) throws WxErrorException;


  /**
   * 环境共享
   * https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/cloudbase-batch/env-mgnt/shareCloudbaseEnv.html
   *
   * @param request
   * @return
   * @throws WxErrorException
   */
  ShareCloudBaseEnvResponse shareCloudBaseEnv(ShareCloudBaseEnvRequest request) throws WxErrorException;

  /**
   * 使用 AppSecret 重置第三方平台 API 调用次数
   * https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/openapi/clearComponentQuotaByAppSecret.html
   *
   * @param appid 授权用户appid
   * @return
   * @throws WxErrorException
   */
  WxOpenResult clearQuotaV2(String appid) throws WxErrorException;

  //////////////////////////////////////////////////////////////
  /**
   * 申请设置订单页path信息
   */
  String OPEN_APPLY_SET_ORDER_PATH_INFO = "https://api.weixin.qq.com/wxa/security/applysetorderpathinfo";

  /**
   * 申请设置订单页path信息
   * 注意：一次提交不超过100个appid
   *
   * @param info 订单页path信息
   * @return .
   * @throws WxErrorException .
   */
  WxOpenResult applySetOrderPathInfo(WxOpenMaApplyOrderPathInfo info) throws WxErrorException;

  /**
   * 设置第三方平台服务器域名
   * <a href="https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/2.0/api/ThirdParty/domain/modify_server_domain.html">文档地址</a>
   *
   * @param action           add添加, delete删除, set覆盖, get获取
   * @param requestDomains   request 合法域名；当 action 是 get 时不需要此字段
   * @param wsRequestDomains socket 合法域名；当 action 是 get 时不需要此字段
   * @param uploadDomains    uploadFile 合法域名；当 action 是 get 时不需要此字段
   * @param downloadDomains  downloadFile 合法域名；当 action 是 get 时不需要此字段
   * @param tcpDomains       tcp 合法域名；当 action 是 get 时不需要此字段
   * @param udpDomains       udp 合法域名；当 action 是 get 时不需要此字段
   * @return the wx open ma domain result
   * @throws WxErrorException the wx error exception
   */
  WxOpenMaDomainResult modifyWxaServerDomain(String action, List<String> requestDomains, List<String> wsRequestDomains,
                                             List<String> uploadDomains, List<String> downloadDomains,
                                             List<String> udpDomains, List<String> tcpDomains) throws WxErrorException;

  /**
   * 获取第三方平台业务域名校验文件
   * <a href="https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/2.0/api/ThirdParty/domain/get_domain_confirmfile.html">文档地址</a>
   *
   * @return 业务域名校验文件信息
   * @throws WxErrorException 操作失败时抛出，具体错误码请看文档
   */
  WxOpenMaDomainConfirmFileResult getDomainConfirmFile() throws WxErrorException;

  /**
   * 设置第三方平台业务域名
   * <a href="https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/2.0/api/ThirdParty/domain/modify_jump_domain.html">文档地址</a>
   *
   * @param action     add添加, delete删除, set覆盖, get获取
   * @param domainList the domain list
   * @return 直接返回字符串
   * @throws WxErrorException the wx error exception
   */
  String modifyWxaJumpDomain(String action, List<String> domainList) throws WxErrorException;

  /**
   * 设置第三方平台业务域名
   * <a href="https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/2.0/api/ThirdParty/domain/modify_jump_domain.html">文档地址</a>
   *
   * @param action     add添加, delete删除, set覆盖, get获取
   * @param domainList the domain list
   * @return web view domain info
   * @throws WxErrorException the wx error exception
   */
  WxOpenMaWebDomainResult modifyWxaJumpDomainInfo(String action, List<String> domainList) throws WxErrorException;

}
