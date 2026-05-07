package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.WxMaCode2VerifyInfoResult;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import me.chanjar.weixin.common.error.WxErrorException;

import java.util.Map;

/**
 * 用户信息相关操作接口.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public interface WxMaUserService {

  /**
   * 获取登录后的session信息.
   *
   * @param jsCode 登录时获取的 code
   * @return .
   * @throws WxErrorException .
   */
  WxMaJscode2SessionResult getSessionInfo(String jsCode) throws WxErrorException;

  /**
   * 解密用户敏感数据.
   *
   * @param sessionKey    会话密钥
   * @param encryptedData 消息密文
   * @param ivStr         加密算法的初始向量
   */
  WxMaUserInfo getUserInfo(String sessionKey, String encryptedData, String ivStr);

  /**
   * 上报用户数据后台接口.
   * <p>小游戏可以通过本接口上报key-value数据到用户的CloudStorage。</p>
   * 文档参考https://developers.weixin.qq.com/minigame/dev/document/open-api/data/setUserStorage.html
   *
   * @param kvMap      要上报的数据
   * @param sessionKey 通过wx.login 获得的登录态
   * @param openid     .
   * @throws WxErrorException .
   */
  void setUserStorage(Map<String, String> kvMap, String sessionKey, String openid) throws WxErrorException;

  /**
   * 解密用户手机号信息.
   *
   * @param sessionKey    会话密钥
   * @param encryptedData 消息密文
   * @param ivStr         加密算法的初始向量
   * @return .
   * @deprecated 当前（基础库2.21.2以下使用）旧版本，以上请使用替代方法 {@link #getPhoneNoInfo(String)}
   */
  @Deprecated
  WxMaPhoneNumberInfo getPhoneNoInfo(String sessionKey, String encryptedData, String ivStr);

  /**
   * 获取手机号信息,基础库:2.21.2及以上或2023年8月28日起
   *
   * <p>若已配置 {@code apiSignatureAesKey} 及 {@code apiSignatureRsaPrivateKey} 开启服务端 API 签名，
   * 该接口请求将自动走加密 + RSA 签名路径（见
   * <a href="https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/getting_started/api_signature.html">API签名文档</a>）。
   * 签名串格式为 {@code urlpath\nappid\ntimestamp\npostdata}（4 个字段），
   * RSA 私钥序列号通过请求头 {@code Wechatmp-Serial} 传递，不包含在签名串中。
   *
   * @param code 每个code只能使用一次，code的有效期为5min。code获取方式参考<a href="https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/getPhoneNumber.html">手机号快速验证组件</a>
   * @return 用户手机号信息
   * @throws WxErrorException .
   * @apiNote 该接口用于将code换取用户手机号。
   */
  WxMaPhoneNumberInfo getPhoneNumber(String code) throws WxErrorException;

  /**
   * 获取手机号信息,基础库:2.21.2及以上或2023年8月28日起
   *
   * @param code 每个code只能使用一次，code的有效期为5min。code获取方式参考<a href="https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/getPhoneNumber.html">手机号快速验证组件</a>
   * @return 用户手机号信息
   * @throws WxErrorException .
   * @apiNote 该接口用于将code换取用户手机号。
   * @implNote 为保持命名风格一致，此方法将更名，推荐使用{@link WxMaUserService#getPhoneNumber(String)}
   */
  @Deprecated
  WxMaPhoneNumberInfo getPhoneNoInfo(String code) throws WxErrorException;

  /**
   * 验证用户信息完整性.
   *
   * @param sessionKey 会话密钥
   * @param rawData    微信用户基本信息
   * @param signature  数据签名
   * @return .
   */
  boolean checkUserInfo(String sessionKey, String rawData, String signature);

  /**
   * 多端登录验证接口.
   * <p>
   * 通过 code 换取用户登录态信息，用于多端登录场景（如手表端）。
   * </p>
   * 文档地址：<a href="https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/miniapp/openapi/code2Verifyinfo.html">多端登录</a>
   *
   * @param code      登录时获取的 code
   * @param checkcode 手表授权页面返回的 checkcode
   * @return 登录验证结果，包含 session_key、openid、unionid 和 is_limit 字段
   * @throws WxErrorException 调用微信接口失败时抛出
   */
  WxMaCode2VerifyInfoResult getCode2VerifyInfo(String code, String checkcode) throws WxErrorException;

  /**
   * 检查登录态（checkSessionKey）.
   * <p>
   * 检验登录态是否有效，用于虚拟支付等场景构建用户签名前的登录态验证。
   * 登录态有效时返回 {@code true}；登录态已失效时，微信服务端将返回错误码（如 87009），
   * 并以 {@link me.chanjar.weixin.common.error.WxErrorException} 的形式抛出。
   * </p>
   * 文档地址：<a href="https://developers.weixin.qq.com/miniprogram/dev/server/API/user-login/api_checksessionkey.html">检查登录态</a>
   *
   * @param openid     用户唯一标识符
   * @param sessionKey 用户的 session_key，通过 {@link #getSessionInfo(String)} 获取
   * @return 登录态有效时返回 {@code true}
   * @throws WxErrorException 登录态已失效或调用微信接口失败时抛出（失效时 errcode 为 87009）
   */
  boolean checkSessionKey(String openid, String sessionKey) throws WxErrorException;
}
