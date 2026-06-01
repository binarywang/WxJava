package me.chanjar.weixin.open.api.impl;

import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.service.WxOAuth2Service;
import me.chanjar.weixin.common.service.WxOAuth2ServiceDecorator;
import me.chanjar.weixin.common.util.http.URIUtil;
import me.chanjar.weixin.mp.api.WxMpOAuth2Service;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.open.api.WxOpenComponentService;
import org.apache.commons.lang3.StringUtils;

/**
 * 微信 第三方平台对于公众号 oauth2 的实现类
 *
 * @author <a href="https://www.sacoc.cn">广州跨界</a>
 */
public class WxOpenMpOAuth2ServiceImpl extends WxOAuth2ServiceDecorator implements WxMpOAuth2Service {

  private final WxOpenComponentService wxOpenComponentService;
  private final WxMpConfigStorage wxMpConfigStorage;


  public WxOpenMpOAuth2ServiceImpl(WxOpenComponentService wxOpenComponentService, WxOAuth2Service wxOAuth2Service, WxMpConfigStorage wxMpConfigStorage) {
    super(wxOAuth2Service);
    this.wxOpenComponentService = wxOpenComponentService;
    this.wxMpConfigStorage = wxMpConfigStorage;
  }

  /**
   * 第三方平台代公众号发起网页授权
   * 文档地址:<a href="https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/2.0/api/Before_Develop/Official_Accounts/official_account_website_authorization.html">第三方平台代公众号发起网页授权</a>
   *
   * @param code 微信授权code
   * @return 微信用户信息
   * @throws WxErrorException 如果微信接口调用失败将抛出此异常
   */
  @Override
  public WxOAuth2AccessToken getAccessToken(String code) throws WxErrorException {
    String url = String.format(
      WxOpenComponentService.OAUTH2_ACCESS_TOKEN_URL,
      wxMpConfigStorage.getAppId(),
      code,
      wxOpenComponentService.getWxOpenConfigStorage().getComponentAppId()
    );
    String responseContent = wxOpenComponentService.get(url);
    return WxOAuth2AccessToken.fromJson(responseContent);
  }

  @Override
  public String buildAuthorizationUrl(String redirectUri, String scope, String state) {
    return String.format(
      WxOpenComponentService.CONNECT_OAUTH2_AUTHORIZE_URL,
      wxMpConfigStorage.getAppId(),
      URIUtil.encodeURIComponent(redirectUri),
      scope,
      StringUtils.trimToEmpty(state),
      wxOpenComponentService.getWxOpenConfigStorage().getComponentAppId()
    );
  }
}

