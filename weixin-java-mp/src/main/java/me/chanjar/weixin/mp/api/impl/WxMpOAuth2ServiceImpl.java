package me.chanjar.weixin.mp.api.impl;

import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.error.WxRuntimeException;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.SimpleGetRequestExecutor;
import me.chanjar.weixin.common.util.http.URIUtil;
import me.chanjar.weixin.mp.api.WxMpOAuth2Service;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

import static me.chanjar.weixin.mp.enums.WxMpApiUrl.OAuth2.*;

/**
 * oauth2 相关接口实现类.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on  2020-08-08
 */
@RequiredArgsConstructor
public class WxMpOAuth2ServiceImpl implements WxMpOAuth2Service {
  private final WxMpService wxMpService;

  @Override
  public String buildAuthorizationUrl(String redirectUri, String scope, String state) {
    return String.format(CONNECT_OAUTH2_AUTHORIZE_URL.getUrl(getMpConfigStorage()),
      getMpConfigStorage().getAppId(), URIUtil.encodeURIComponent(redirectUri), scope, StringUtils.trimToEmpty(state));
  }

  private WxOAuth2AccessToken getOAuth2AccessToken(String url) throws WxErrorException {
    try {
      RequestExecutor<String, String> executor = SimpleGetRequestExecutor.create(this.wxMpService.getRequestHttp());
      String responseText = executor.execute(url, null, WxType.MP);
      return WxOAuth2AccessToken.fromJson(responseText);
    } catch (IOException e) {
      throw new WxErrorException(WxError.builder().errorCode(99999).errorMsg(e.getMessage()).build(), e);
    }
  }

  @Override
  public WxOAuth2AccessToken getAccessToken(String code) throws WxErrorException {
    return this.getAccessToken(getMpConfigStorage().getAppId(), getMpConfigStorage().getSecret(), code);
  }

  @Override
  public WxOAuth2AccessToken getAccessToken(String appId, String appSecret, String code) throws WxErrorException {
    return this.getOAuth2AccessToken(String.format(OAUTH2_ACCESS_TOKEN_URL.getUrl(getMpConfigStorage()), appId, appSecret, code));
  }

  @Override
  public WxOAuth2AccessToken refreshAccessToken(String refreshToken) throws WxErrorException {
    String url = String.format(OAUTH2_REFRESH_TOKEN_URL.getUrl(getMpConfigStorage()), getMpConfigStorage().getAppId(), refreshToken);
    return this.getOAuth2AccessToken(url);
  }

  protected WxMpConfigStorage getMpConfigStorage() {
    return this.wxMpService.getWxMpConfigStorage();
  }

  @Override
  public WxOAuth2UserInfo getUserInfo(WxOAuth2AccessToken token, String lang) throws WxErrorException {
    if (lang == null) {
      lang = "zh_CN";
    }

    String url = String.format(OAUTH2_USERINFO_URL.getUrl(getMpConfigStorage()), token.getAccessToken(), token.getOpenId(), lang);

    try {
      RequestExecutor<String, String> executor = SimpleGetRequestExecutor.create(this.wxMpService.getRequestHttp());
      String responseText = executor.execute(url, null, WxType.MP);
      return WxOAuth2UserInfo.fromJson(responseText);
    } catch (IOException e) {
      throw new WxRuntimeException(e);
    }
  }

  @Override
  public boolean validateAccessToken(WxOAuth2AccessToken token) {
    String url = String.format(OAUTH2_VALIDATE_TOKEN_URL.getUrl(getMpConfigStorage()), token.getAccessToken(), token.getOpenId());

    try {
      SimpleGetRequestExecutor.create(this.wxMpService.getRequestHttp()).execute(url, null, WxType.MP);
    } catch (IOException e) {
      throw new WxRuntimeException(e);
    } catch (WxErrorException e) {
      return false;
    }
    return true;
  }
}
