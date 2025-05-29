package me.chanjar.weixin.open.api;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaAuditMediaUploadResult;
import cn.binarywang.wx.miniapp.bean.WxMaUploadAuthMaterialResult;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.open.bean.ma.WxMaPrefetchDomain;
import me.chanjar.weixin.open.bean.ma.WxMaScheme;
import me.chanjar.weixin.open.bean.message.WxOpenMaSubmitAuditMessage;
import me.chanjar.weixin.open.bean.message.WxOpenMaVerifyBetaWeappMessage;
import me.chanjar.weixin.open.bean.result.*;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * <a href="https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1489144594_DhNoV&token=&lang=zh_CN">微信开放平台代小程序实现服务能力</a>
 *
 * @author yqx
 * created on  2018 /9/12
 */
public interface WxOpenMaService extends WxMaService {
  /**
   * 设置小程序服务器域名.
   *
   * <pre>
   *     授权给第三方的小程序，其服务器域名只可以为第三方的服务器，当小程序通过第三方发布代码上线后，小程序原先自己配置的服务器域名将被删除，
   *     只保留第三方平台的域名，所以第三方平台在代替小程序发布代码之前，需要调用接口为小程序添加第三方自身的域名。
   *     提示：需要先将域名登记到第三方平台的小程序服务器域名中，才可以调用接口进行配置
   * </pre>
   */
  String API_MODIFY_DOMAIN = "https://api.weixin.qq.com/wxa/modify_domain";

  /**
   * 设置小程序业务域名（仅供第三方代小程序调用）
   * <pre>
   *     授权给第三方的小程序，其业务域名只可以为第三方的服务器，当小程序通过第三方发布代码上线后，小程序原先自己配置的业务域名将被删除，
   *     只保留第三方平台的域名，所以第三方平台在代替小程序发布代码之前，需要调用接口为小程序添加业务域名。
   * 提示：
   * 1、需要先将域名登记到第三方平台的小程序业务域名中，才可以调用接口进行配置。
   * 2、为授权的小程序配置域名时支持配置子域名，例如第三方登记的业务域名如为qq.com，则可以直接将qq.com及其子域名（如xxx.qq.com）也配置到授权的小程序中。
   * </pre>
   */
  String API_SET_WEBVIEW_DOMAIN = "https://api.weixin.qq.com/wxa/setwebviewdomain";

  /**
   * 获取业务域名校验文件（仅供第三方代小程序调用）
   */
  String API_GET_WEBVIEW_DOMAIN_CONFIRM_FILE = "https://api.weixin.qq.com/wxa/get_webviewdomain_confirmfile";

  /**
   * 获取帐号基本信息
   * <pre>
   * GET请求
   * 注意：需要使用1.3环节获取到的新创建小程序appid及authorization_code换取authorizer_refresh_token进而得到authorizer_access_token。
   * </pre>
   */
  String API_GET_ACCOUNT_BASICINFO = "https://api.weixin.qq.com/cgi-bin/account/getaccountbasicinfo";

  /**
   * 绑定微信用户为小程序体验者
   */
  String API_BIND_TESTER = "https://api.weixin.qq.com/wxa/bind_tester";


  /**
   * 解除绑定微信用户为小程序体验者
   */
  String API_UNBIND_TESTER = "https://api.weixin.qq.com/wxa/unbind_tester";


  /**
   * 获取体验者列表
   */
  String API_GET_TESTERLIST = "https://api.weixin.qq.com/wxa/memberauth";

  /**
   * 以下接口基础信息设置
   * <p>
   * <a href="https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=21517799059ZSEMr&token=6f965b5daf30a98a6bbd2a386faea5c934e929bf&lang=zh_CN">...</a>
   * </p>
   * 1. 设置小程序隐私设置（是否可被搜索）
   */
  String API_CHANGE_WXA_SEARCH_STATUS = "https://api.weixin.qq.com/wxa/changewxasearchstatus";

  /**
   * 2. 查询小程序当前隐私设置（是否可被搜索）
   */
  String API_GET_WXA_SEARCH_STATUS = "https://api.weixin.qq.com/wxa/getwxasearchstatus";

  /**
   * 3.1. 获取展示的公众号信息
   */
  String API_GET_SHOW_WXA_ITEM = "https://api.weixin.qq.com/wxa/getshowwxaitem";

  /**
   * 3.2 设置展示的公众号
   */
  String API_UPDATE_SHOW_WXA_ITEM = "https://api.weixin.qq.com/wxa/updateshowwxaitem";


  /**
   * 以下接口为三方平台代小程序实现的代码管理功能
   * <p>
   * <a href="https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1489140610_Uavc4&token=fe774228c66725425675810097f9e48d0737a4bf&lang=zh_CN">...</a>
   * </p>
   * 1. 为授权的小程序帐号上传小程序代码
   */
  String API_CODE_COMMIT = "https://api.weixin.qq.com/wxa/commit";

  /**
   * 2. 获取体验小程序的体验二维码
   */
  String API_TEST_QRCODE = "https://api.weixin.qq.com/wxa/get_qrcode";

  /**
   * 3. 试用小程序快速认证
   */
  String API_VERIFY_BETA_WEAPP = "https://api.weixin.qq.com/wxa/verifybetaweapp";

  /**
   * 4. 获取授权小程序帐号的可选类目
   */
  String API_GET_CATEGORY = "https://api.weixin.qq.com/wxa/get_category";

  /**
   * 5. 获取小程序的第三方提交代码的页面配置（仅供第三方开发者代小程序调用）
   */
  String API_GET_PAGE = "https://api.weixin.qq.com/wxa/get_page";

  /**
   * 6. 将第三方提交的代码包提交审核（仅供第三方开发者代小程序调用）
   */
  String API_SUBMIT_AUDIT = "https://api.weixin.qq.com/wxa/submit_audit";

  /**
   * 7. 查询某个指定版本的审核状态（仅供第三方代小程序调用）
   */
  String API_GET_AUDIT_STATUS = "https://api.weixin.qq.com/wxa/get_auditstatus";

  /**
   * 8. 查询最新一次提交的审核状态（仅供第三方代小程序调用）
   */
  String API_GET_LATEST_AUDIT_STATUS = "https://api.weixin.qq.com/wxa/get_latest_auditstatus";

  /**
   * 9. 发布已通过审核的小程序（仅供第三方代小程序调用）
   */
  String API_RELEASE = "https://api.weixin.qq.com/wxa/release";

  /**
   * 10.1 修改小程序线上代码的可见状态（仅供第三方代小程序调用)
   */
  String API_CHANGE_VISITSTATUS = "https://api.weixin.qq.com/wxa/change_visitstatus";

  /**
   * 10.2 查询小程序线上代码的可见状态（仅供第三方代小程序调用)
   */
  String API_GET_VISITSTATUS = "https://api.weixin.qq.com/wxa/getvisitstatus";

  /**
   * 11.小程序版本回退（仅供第三方代小程序调用）
   */
  String API_REVERT_CODE_RELEASE = "https://api.weixin.qq.com/wxa/revertcoderelease";

  /**
   * 12.查询当前设置的最低基础库版本及各版本用户占比 （仅供第三方代小程序调用）
   */
  String API_GET_WEAPP_SUPPORT_VERSION = "https://api.weixin.qq.com/cgi-bin/wxopen/getweappsupportversion";

  /**
   * 13.设置最低基础库版本（仅供第三方代小程序调用）
   */
  String API_SET_WEAPP_SUPPORT_VERSION = "https://api.weixin.qq.com/cgi-bin/wxopen/setweappsupportversion";

  /**
   * 14.设置小程序“扫普通链接二维码打开小程序”能力
   * <p>
   * <a href="https://mp.weixin.qq.com/debug/wxadoc/introduction/qrcode.html">...</a>
   * 14.1 增加或修改二维码规则
   */
  String API_QRCODE_JUMP_ADD = "https://api.weixin.qq.com/cgi-bin/wxopen/qrcodejumpadd";

  /**
   * 14.2 获取已设置的二维码规则
   */
  String API_QRCODE_JUMP_GET = "https://api.weixin.qq.com/cgi-bin/wxopen/qrcodejumpget";

  /**
   * 14.3 获取校验文件名称及内容
   */
  String API_QRCODE_JUMP_DOWNLOAD = "https://api.weixin.qq.com/cgi-bin/wxopen/qrcodejumpdownload";

  /**
   * 14.4 删除已设置的二维码规则
   */
  String API_QRCODE_JUMP_DELETE = "https://api.weixin.qq.com/cgi-bin/wxopen/qrcodejumpdelete";

  /**
   * 14.5 发布已设置的二维码规则
   */
  String API_QRCODE_JUMP_PUBLISH = "https://api.weixin.qq.com/cgi-bin/wxopen/qrcodejumppublish";

  /**
   * 15.小程序审核撤回
   * <p>
   * 单个帐号每天审核撤回次数最多不超过1次，一个月不超过10次。
   * </p>
   */
  String API_UNDO_CODE_AUDIT = "https://api.weixin.qq.com/wxa/undocodeaudit";

  /**
   * 16.1 小程序分阶段发布-分阶段发布接口
   */
  String API_GRAY_RELEASE = "https://api.weixin.qq.com/wxa/grayrelease";

  /**
   * 16.2 小程序分阶段发布-取消分阶段发布
   */
  String API_REVERT_GRAY_RELEASE = "https://api.weixin.qq.com/wxa/revertgrayrelease";

  /**
   * 16.3 小程序分阶段发布-查询当前分阶段发布详情
   */
  String API_GET_GRAY_RELEASE_PLAN = "https://api.weixin.qq.com/wxa/getgrayreleaseplan";

  /**
   * 17 获取隐私接口检测结果
   */
  String API_GET_CODE_PRIVACY_INFO = "https://api.weixin.qq.com/wxa/security/get_code_privacy_info";


  /**
   * 查询服务商的当月提审限额和加急次数（Quota）
   */
  String API_QUERY_QUOTA = "https://api.weixin.qq.com/wxa/queryquota";

  /**
   * 加急审核申请
   */
  String API_SPEED_AUDIT = "https://api.weixin.qq.com/wxa/speedupaudit";


  /**
   * 获取小程序scheme码
   */
  String API_GENERATE_SCHEME = "https://api.weixin.qq.com/wxa/generatescheme";


  /**
   * 通过此接口开通自定义版交易组件，将同步返回接入结果，不再有异步事件回调。
   */
  String API_REGISTER_SHOP_COMPONENT = "https://api.weixin.qq.com/shop/register/apply";

  /**
   * 小程序审核 提审素材上传接口
   */
  String API_AUDIT_UPLOAD_MEDIA = "https://api.weixin.qq.com/wxa/uploadmedia";


  /**
   * 小程序管理-查询小程序版本信息
   */
  String API_GET_VERSION_INFO = "https://api.weixin.qq.com/wxa/getversioninfo";

  /**
   * 设置DNS预解析域名
   */
  String API_WX_SET_PREFETCH_DOMAIN = "https://api.weixin.qq.com/wxa/set_prefetchdnsdomain";

  /**
   * 获取DNS预解析域名
   */
  String API_GET_PREFETCH_DOMAIN = "https://api.weixin.qq.com/wxa/get_prefetchdnsdomain";

  /**
   * 申请开通直播
   */
  String API_WX_APPLY_LIVE_INFO = "https://api.weixin.qq.com/wxa/business/applyliveinfo";

  /**
   * 小程序认证上传补充材料
   */
  String API_UPLOAD_AUTH_MATERIAL = "https://api.weixin.qq.com/wxa/sec/uploadauthmaterial";

  /**
   * 获得小程序的域名配置信息
   *
   * @return the domain
   * @throws WxErrorException the wx error exception
   */
  WxOpenMaDomainResult getDomain() throws WxErrorException;

  /**
   * 修改域名
   * <a href="https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/2.0/api/Mini_Program_Basic_Info/Server_Address_Configuration.html">文档地址</a>
   *
   * @param action           delete删除, set覆盖, get获取
   * @param requestDomains   request 合法域名；当 action 是 get 时不需要此字段
   * @param wsRequestDomains socket 合法域名；当 action 是 get 时不需要此字段
   * @param uploadDomains    uploadFile 合法域名；当 action 是 get 时不需要此字段
   * @param downloadDomains  downloadFile 合法域名；当 action 是 get 时不需要此字段
   * @param tcpDomains       tcp 合法域名；当 action 是 get 时不需要此字段
   * @param udpDomains       udp 合法域名；当 action 是 get 时不需要此字段
   * @return the wx open ma domain result
   * @throws WxErrorException the wx error exception
   */
  WxOpenMaDomainResult modifyDomain(String action, List<String> requestDomains, List<String> wsRequestDomains,
                                    List<String> uploadDomains, List<String> downloadDomains,
                                    List<String> udpDomains, List<String> tcpDomains) throws WxErrorException;

  /**
   * 获取小程序的业务域名
   *
   * @return 直接返回字符串 web view domain
   * @throws WxErrorException the wx error exception
   */
  String getWebViewDomain() throws WxErrorException;

  /**
   * 获取小程序的业务域名
   *
   * @return web view domain info
   * @throws WxErrorException the wx error exception
   */
  WxOpenMaWebDomainResult getWebViewDomainInfo() throws WxErrorException;

  /**
   * 设置小程序的业务域名
   *
   * @param action     add添加, delete删除, set覆盖
   * @param domainList the domain list
   * @return 直接返回字符串 web view domain
   * @throws WxErrorException the wx error exception
   */
  String setWebViewDomain(String action, List<String> domainList) throws WxErrorException;

  /**
   * 设置小程序的业务域名
   *
   * @param action     add添加, delete删除, set覆盖
   * @param domainList the domain list
   * @return web view domain info
   * @throws WxErrorException the wx error exception
   */
  WxOpenMaWebDomainResult setWebViewDomainInfo(String action, List<String> domainList) throws WxErrorException;

  /**
   * 获取业务域名校验文件
   *
   * @return 业务域名校验文件信息
   * @throws WxErrorException 操作失败时抛出，具体错误码请看文档
   */
  WxOpenMaDomainConfirmFileResult getWebviewDomainConfirmFile() throws WxErrorException;

  /**
   * 获取小程序的信息
   *
   * @return the account basic info
   * @throws WxErrorException the wx error exception
   */
  String getAccountBasicInfo() throws WxErrorException;

  /**
   * 绑定小程序体验者
   *
   * @param wechatId 体验者微信号（不是openid）
   * @return wx open ma bind tester result
   * @throws WxErrorException the wx error exception
   */
  WxOpenMaBindTesterResult bindTester(String wechatId) throws WxErrorException;

  /**
   * 解除绑定小程序体验者
   *
   * @param wechatId 体验者微信号（不是openid）
   * @return the wx open result
   * @throws WxErrorException the wx error exception
   */
  WxOpenResult unbindTester(String wechatId) throws WxErrorException;

  /**
   * 解除绑定小程序体验者，其他平台绑定的体验者无法获取到wechatid，可用此方法解绑，详见文档
   * https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/Mini_Programs/unbind_tester.html
   *
   * @param userStr 人员对应的唯一字符串， 可通过获取已绑定的体验者列表获取人员对应的字符串
   * @return the wx open result
   * @throws WxErrorException the wx error exception
   */
  WxOpenResult unbindTesterByUserStr(String userStr) throws WxErrorException;

  /**
   * 获得体验者列表
   *
   * @return the tester list
   * @throws WxErrorException the wx error exception
   */
  WxOpenMaTesterListResult getTesterList() throws WxErrorException;

  /**
   * 设置小程序隐私设置（是否可被搜索）
   *
   * @param status 1表示不可搜索，0表示可搜索
   * @return the wx open result
   * @throws WxErrorException the wx error exception
   */
  WxOpenResult changeWxaSearchStatus(Integer status) throws WxErrorException;

  /**
   * 2. 查询小程序当前隐私设置（是否可被搜索）
   *
   * @return the wxa search status
   * @throws WxErrorException the wx error exception
   */
  WxOpenMaSearchStatusResult getWxaSearchStatus() throws WxErrorException;

  /**
   * 3.1 获取展示的公众号信息
   *
   * @return the show wxa item
   * @throws WxErrorException the wx error exception
   */
  WxOpenMaShowItemResult getShowWxaItem() throws WxErrorException;

  /**
   * 3.2 设置展示的公众号
   *
   * @param flag    0 关闭，1 开启
   * @param mpAppId 如果开启，需要传新的公众号appid
   * @return the wx open result
   * @throws WxErrorException the wx error exception
   */
  WxOpenResult updateShowWxaItem(Integer flag, String mpAppId) throws WxErrorException;

  /**
   * 1、为授权的小程序帐号上传小程序代码
   *
   * @param templateId    代码模板ID
   * @param userVersion   用户定义版本
   * @param userDesc      用户定义版本描述
   * @param extJsonObject 为了方便第三方平台的开发者引入 extAppid 的开发调试工作，引入ext.json配置文件概念，该参数则是用于控制ext.json配置文件的内容。
   *                      如果是普通模板可以使用 WxMaOpenCommitExtInfo 类构造参数，
   *                      如果是标准模板可支持的参数为：{"extAppid":'', "ext": {}, "window": {}} 所以可以使用 WxMaOpenCommitStandardExt 构造参数
   * @return the wx open result
   * @throws WxErrorException the wx error exception
   * @see me.chanjar.weixin.open.bean.ma.WxMaOpenCommitStandardExt
   * @see me.chanjar.weixin.open.bean.ma.WxMaOpenCommitExtInfo
   */
  WxOpenResult codeCommit(Long templateId, String userVersion, String userDesc, Object extJsonObject) throws WxErrorException;

  /**
   * 获取体验小程序的体验二维码
   *
   * @param pagePath the page path
   * @param params   the params
   * @return the test qrcode
   * @throws WxErrorException the wx error exception
   */
  File getTestQrcode(String pagePath, Map<String, String> params) throws WxErrorException;

  /**
   * 试用小程序快速认证
   *
   * @param verifyBetaWeappMessage the verify mini program message
   * @return the wx open result
   * @throws WxErrorException the wx error exception
   */
  WxOpenResult verifyBetaWeapp(WxOpenMaVerifyBetaWeappMessage verifyBetaWeappMessage) throws WxErrorException;

  /**
   * 获取授权小程序帐号的可选类目
   * <p>
   * 注意：该接口可获取已设置的二级类目及用于代码审核的可选三级类目。
   * </p>
   *
   * @return the category list
   * @throws WxErrorException the wx error exception
   */
  WxOpenMaCategoryListResult getCategoryList() throws WxErrorException;

  /**
   * 获取小程序的第三方提交代码的页面配置（仅供第三方开发者代小程序调用）
   *
   * @return page list
   * @throws WxErrorException the wx error exception
   */
  WxOpenMaPageListResult getPageList() throws WxErrorException;

  /**
   * 将第三方提交的代码包提交审核（仅供第三方开发者代小程序调用）
   *
   * @param submitAuditMessage the submit audit message
   * @return the wx open ma submit audit result
   * @throws WxErrorException the wx error exception
   */
  WxOpenMaSubmitAuditResult submitAudit(WxOpenMaSubmitAuditMessage submitAuditMessage) throws WxErrorException;

  /**
   * 查询某个指定版本的审核状态（仅供第三方代小程序调用）
   *
   * @param auditId the auditid
   * @return the audit status
   * @throws WxErrorException the wx error exception
   */
  WxOpenMaQueryAuditResult getAuditStatus(Long auditId) throws WxErrorException;

  /**
   * 8. 查询最新一次提交的审核状态（仅供第三方代小程序调用）
   *
   * @return 。
   * @throws WxErrorException 。
   */
  WxOpenMaQueryAuditResult getLatestAuditStatus() throws WxErrorException;

  /**
   * 9. 发布已通过审核的小程序（仅供第三方代小程序调用）
   * <p>
   * 请填写空的数据包，POST的json数据包为空即可。
   * </p>
   *
   * @return 。
   * @throws WxErrorException 。
   */
  WxOpenResult releaseAudited() throws WxErrorException;

  /**
   * 10.1 修改小程序线上代码的可见状态（仅供第三方代小程序调用）
   *
   * @param action the action
   * @return the wx open result
   * @throws WxErrorException the wx error exception
   */
  WxOpenResult changeVisitStatus(String action) throws WxErrorException;

  /**
   * 10.2 查询小程序服务状态（仅供第三方代小程序调用）
   *
   * @return 小程序服务状态
   * @throws WxErrorException 查询失败时返回，具体错误码请看此接口的注释文档
   */
  WxOpenMaVisitStatusResult getVisitStatus() throws WxErrorException;

  /**
   * 11. 小程序版本回退（仅供第三方代小程序调用）
   *
   * @return 。
   * @throws WxErrorException 。
   */
  WxOpenResult revertCodeRelease() throws WxErrorException;

  /**
   * 获取可回退的小程序版本
   * 调用本接口可以获取可回退的小程序版本（最多保存最近发布或回退的5个版本
   * 文档地址: https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/2.0/api/code/get_history_version.html
   *
   * @return 历史版本信息
   * @throws WxErrorException 如果调用微信接口失败抛出此异常
   */
  WxOpenMaHistoryVersionResult getHistoryVersion() throws WxErrorException;

  /**
   * 15. 小程序审核撤回
   * <p>
   * 单个帐号每天审核撤回次数最多不超过1次，一个月不超过10次。
   * </p>
   *
   * @return 。
   * @throws WxErrorException 。
   */
  WxOpenResult undoCodeAudit() throws WxErrorException;

  /**
   * 12. 查询当前设置的最低基础库版本及各版本用户占比 （仅供第三方代小程序调用）
   *
   * @return 。
   * @throws WxErrorException 。
   */
  String getSupportVersion() throws WxErrorException;

  /**
   * 12. 查询当前设置的最低基础库版本及各版本用户占比 （仅供第三方代小程序调用）
   *
   * @return . support version info
   * @throws WxErrorException .
   */
  WxOpenMaWeappSupportVersionResult getSupportVersionInfo() throws WxErrorException;

  /**
   * 设置最低基础库版本（仅供第三方代小程序调用）
   *
   * @param version the version
   * @return the support version
   * @throws WxErrorException the wx error exception
   */
  String setSupportVersion(String version) throws WxErrorException;

  /**
   * 13. 设置最低基础库版本（仅供第三方代小程序调用）
   *
   * @param version the version
   * @return support version info
   * @throws WxErrorException the wx error exception
   */
  WxOpenResult setSupportVersionInfo(String version) throws WxErrorException;

  /**
   * 16. 小程序分阶段发布 - 1)分阶段发布接口
   *
   * @param grayPercentage 灰度的百分比，1到100的整数
   * @return . wx open result
   * @throws WxErrorException .
   */
  WxOpenResult grayRelease(Integer grayPercentage) throws WxErrorException;

  /**
   * 16. 小程序分阶段发布 - 2)取消分阶段发布
   *
   * @return . wx open result
   * @throws WxErrorException .
   */
  WxOpenResult revertGrayRelease() throws WxErrorException;

  /**
   * 16. 小程序分阶段发布 - 3)查询当前分阶段发布详情
   *
   * @return . gray release plan
   * @throws WxErrorException .
   */
  WxOpenMaGrayReleasePlanResult getGrayReleasePlan() throws WxErrorException;

  /**
   * 17. 获取隐私接口检测结果
   * https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/code-management/getCodePrivacyInfo.html
   *
   * @return {@link WxOpenMaGetCodePrivacyInfoResult }
   * @throws WxErrorException wx错误异常
   * @author Yuan
   */
  WxOpenMaGetCodePrivacyInfoResult getCodePrivacyInfo() throws WxErrorException;

  /**
   * 查询服务商的当月提审限额和加急次数（Quota）
   * https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/Mini_Programs/code/query_quota.html
   *
   * @return the wx open ma query quota result
   * @throws WxErrorException the wx error exception
   */
  WxOpenMaQueryQuotaResult queryQuota() throws WxErrorException;

  /**
   * 加急审核申请
   * 有加急次数的第三方可以通过该接口，对已经提审的小程序进行加急操作，加急后的小程序预计2-12小时内审完。
   *
   * @param auditId the auditid
   * @return the boolean
   * @throws WxErrorException the wx error exception
   */
  Boolean speedAudit(Long auditId) throws WxErrorException;

  /**
   * (1)增加或修改二维码规则
   *
   * @param wxQrcodeJumpRule the wx qrcode jump rule
   * @return the wx open result
   * @throws WxErrorException the wx error exception
   */
  WxOpenResult addQrcodeJump(WxQrcodeJumpRule wxQrcodeJumpRule) throws WxErrorException;

  /**
   * (2)获取已设置的二维码规则
   *
   * @return the qrcode jump
   * @throws WxErrorException the wx error exception
   */
  WxGetQrcodeJumpResult getQrcodeJump() throws WxErrorException;

  /**
   * (3)获取校验文件名称及内容
   *
   * @return the wx downlooad qrcode jump result
   * @throws WxErrorException the wx error exception
   */
  WxDownlooadQrcodeJumpResult downloadQrcodeJump() throws WxErrorException;

  /**
   * (4)删除已设置的二维码规则
   *
   * @param prefix the prefix
   * @return the wx open result
   * @throws WxErrorException the wx error exception
   */
  WxOpenResult deleteQrcodeJump(String prefix) throws WxErrorException;

  /**
   * (5)发布已设置的二维码规则
   *
   * @param prefix the prefix
   * @return the wx open result
   * @throws WxErrorException the wx error exception
   */
  WxOpenResult publishQrcodeJump(String prefix) throws WxErrorException;

  WxMaScheme generateMaScheme(String jumpWxaPath, String jumpWxaQuery, Boolean isExpire, Long expireTime) throws WxErrorException;

  /**
   * 为小程序开通小商店组件
   *
   * @return
   */
  WxOpenResult registerShopComponent() throws WxErrorException;

  /**
   * 小程序基础信息服务  (小程序名称、头像、描述、类目等信息设置)
   *
   * @return 小程序基础信息服务
   */
  WxOpenMaBasicService getBasicService();

  /**
   * 小程序认证（年审）服务
   *
   * @return 小程序认证（年审）服务
   */
  WxOpenMaAuthService getAuthService();

  /**
   * 小程序备案服务
   *
   * @return 小程序备案服务
   */
  WxOpenMaIcpService getIcpService();

  /**
   * 小程序用户隐私保护指引服务
   *
   * @return 小程序用户隐私保护指引服务
   */
  WxOpenMaPrivacyService getPrivacyService();

  /**
   * 半屏小程序服务
   *
   * @return {@link WxOpenMaEmbeddedService }
   * @author Yuan
   * @date 2024-12-04 18:42:21
   */
  WxOpenMaEmbeddedService getEmbeddedService();

  /**
   * 购物订单
   *
   * @return 购物订单服务
   */
  WxOpenMaShoppingOrdersService getShoppingOrdersService();

  /**
   * 小程序审核 提审素材上传接口
   *
   * @return 结果
   */
  WxMaAuditMediaUploadResult uploadMedia(File file) throws WxErrorException;

  /**
   * 查询小程序版本信息
   *
   * @return the wx open result
   * @throws WxErrorException the wx error exception
   */
  WxOpenVersioninfoResult getVersionInfo() throws WxErrorException;

  /**
   * 设置DNS预解析域名
   *
   * @param domain 预解析域名列表
   * @return {@link WxOpenResult}
   * @throws WxErrorException the wx error exception
   */
  WxOpenResult setPrefetchDomain(WxMaPrefetchDomain domain) throws WxErrorException;

  /**
   * 获取DNS预解析域名
   *
   * @return {@link WxOpenMaPrefetchDomainResult}
   * @throws WxErrorException he wx error exception
   */
  WxOpenMaPrefetchDomainResult getPrefetchDomain() throws WxErrorException;

  /**
   * 申请开通直播
   * 文档地址：
   * <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/live-player/applyLivelnfo.html">https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/live-player/applyLivelnfo.html</a>
   *
   * @return {@link WxOpenMaApplyLiveInfoResult}
   * @throws WxErrorException the wx error exception
   */
  WxOpenMaApplyLiveInfoResult applyLiveInfo() throws WxErrorException;

  /**
   * 小程序认证上传补充材料
   *
   * @return 结果
   * @see #getAuthService() 应使用此处方法处理小程序认证相关业务
   */
  @Deprecated
  WxMaUploadAuthMaterialResult uploadAuthMaterial(File file) throws WxErrorException;

}
