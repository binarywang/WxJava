package me.chanjar.weixin.cp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpOauth2UserInfo;
import me.chanjar.weixin.cp.bean.WxCpUserDetail;
import me.chanjar.weixin.cp.bean.workbench.WxCpSecondVerificationInfo;

/**
 * <pre>
 * OAuth2相关管理接口.
 *  Created by BinaryWang on 2017/6/24.
 * </pre>
 * <p>
 * 文档1：https://developer.work.weixin.qq.com/document/path/91856
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public interface WxCpOAuth2Service {

  /**
   * <pre>
   * 构造oauth2授权的url连接.
   * </pre>
   *
   * @param state 状态码
   * @return url string
   */
  String buildAuthorizationUrl(String state);

  /**
   * <pre>
   * 构造oauth2授权的url连接.
   * 详情请见: http://qydev.weixin.qq.com/wiki/index.php?title=企业获取code
   * </pre>
   *
   * @param redirectUri 跳转链接地址
   * @param state       状态码
   * @return url string
   */
  String buildAuthorizationUrl(String redirectUri, String state);

  /**
   * <pre>
   * 构造oauth2授权的url连接
   * 详情请见: http://qydev.weixin.qq.com/wiki/index.php?title=企业获取code
   * </pre>
   *
   * @param redirectUri 跳转链接地址
   * @param state       状态码
   * @param scope       取值参考me.chanjar.weixin.common.api.WxConsts.OAuth2Scope类
   * @return url string
   */
  String buildAuthorizationUrl(String redirectUri, String state, String scope);

  /**
   * <pre>
   * 用oauth2获取用户信息
   * http://qydev.weixin.qq.com/wiki/index.php?title=根据code获取成员信息
   * 因为企业号oauth2.0必须在应用设置里设置通过ICP备案的可信域名，所以无法测试，因此这个方法很可能是坏的。
   *
   * 注意: 这个方法使用WxCpConfigStorage里的agentId
   * </pre>
   *
   * @param code 微信oauth授权返回的代码
   * @return WxCpOauth2UserInfo user info
   * @throws WxErrorException 异常
   * @see #getUserInfo(Integer, String) #getUserInfo(Integer, String)
   */
  WxCpOauth2UserInfo getUserInfo(String code) throws WxErrorException;

  /**
   * <pre>
   * 根据code获取成员信息
   * http://qydev.weixin.qq.com/wiki/index.php?title=根据code获取成员信息
   * https://work.weixin.qq.com/api/doc#10028/根据code获取成员信息
   * https://work.weixin.qq.com/api/doc#90000/90135/91023  获取访问用户身份
   * 因为企业号oauth2.0必须在应用设置里设置通过ICP备案的可信域名，所以无法测试，因此这个方法很可能是坏的。
   *
   * 注意: 这个方法不使用WxCpConfigStorage里的agentId，需要开发人员自己给出
   * </pre>
   *
   * @param agentId 企业号应用的id
   * @param code    通过成员授权获取到的code，最大为512字节。每次成员授权带上的code将不一样，code只能使用一次，5分钟未被使用自动过期。
   * @return WxCpOauth2UserInfo user info
   * @throws WxErrorException 异常
   * @see #getUserInfo(String) #getUserInfo(String)
   */
  WxCpOauth2UserInfo getUserInfo(Integer agentId, String code) throws WxErrorException;

  /**
   * 获取家校访问用户身份
   * 该接口用于根据code获取家长或者学生信息
   * <pre>
   * 请求方式：GET（HTTPS）
   * {@code 请求地址：https://qyapi.weixin.qq.com/cgi-bin/school/getuserinfo?access_token=ACCESS_TOKEN&code=CODE}
   * </pre>
   *
   * @param code the code
   * @return school user info
   * @throws WxErrorException the wx error exception
   */
  WxCpOauth2UserInfo getSchoolUserInfo(String code) throws WxErrorException;

  /**
   * <pre>
   * 使用user_ticket获取成员详情
   *
   * 文档地址：https://developer.work.weixin.qq.com/document/path/95833
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/auth/getuserdetail?access_token=ACCESS_TOKEN
   *
   * 注意: 原/cgi-bin/user/getuserdetail接口的url已变更为/cgi-bin/auth/getuserdetail，旧接口暂时还可以使用，但建议使用新接口
   *
   * 权限说明：需要有对应应用的使用权限，且成员必须在授权应用的可见范围内。
   * 适用范围：企业内部开发、服务商代开发
   * </pre>
   *
   * @param userTicket 成员票据
   * @return WxCpUserDetail user detail
   * @throws WxErrorException 异常
   */
  WxCpUserDetail getUserDetail(String userTicket) throws WxErrorException;

  /**
   * <pre>
   * 获取用户登录身份
   * {@code https://qyapi.weixin.qq.com/cgi-bin/auth/getuserinfo?access_token=ACCESS_TOKEN&code=CODE}
   * 该接口可使用用户登录成功颁发的code来获取成员信息，适用于自建应用与代开发应用
   *
   * 注意: 旧的/user/getuserinfo 接口的url已变更为auth/getuserinfo，不过旧接口依旧可以使用，建议是关注新接口即可
   *
   * 适用范围：身份验证中网页授权开发和企业微信Web登录的获取用户登录身份
   * </pre>
   *
   * @param code 通过成员授权获取到的code，最大为512字节。每次成员授权带上的code将不一样，code只能使用一次，5分钟未被使用自动过期。
   * @return WxCpOauth2UserInfo user info
   * @throws WxErrorException 异常
   * @see #getUserInfo(Integer, String) #getUserInfo(Integer, String)
   */
  WxCpOauth2UserInfo getAuthUserInfo(String code) throws WxErrorException;

  /**
   * 获取用户二次验证信息
   * <pre>
   * api: https://qyapi.weixin.qq.com/cgi-bin/auth/get_tfa_info?access_token=ACCESS_TOKEN
   * 权限说明：仅『通讯录同步』或者自建应用可调用，如用自建应用调用，用户需要在二次验证范围和应用可见范围内。
   * 并发限制：20
   * </pre>
   *
   * @param code 用户进入二次验证页面时，企业微信颁发的code,每次成员授权带上的code将不一样，code只能使用一次，5分钟未被使用自动过期
   * @return 二次验证授权码，开发者可以调用通过二次验证接口，解锁企业微信终端.tfa_code有效期五分钟，且只能使用一次。
   * @throws WxErrorException 微信错误异常
   */
  WxCpSecondVerificationInfo getTfaInfo(String code) throws WxErrorException;
}
