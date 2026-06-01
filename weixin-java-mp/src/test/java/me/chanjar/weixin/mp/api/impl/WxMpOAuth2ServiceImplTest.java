package me.chanjar.weixin.mp.api.impl;

import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpOAuth2Service;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.test.ApiTestModule;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 测试类.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on  2020-08-09
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMpOAuth2ServiceImplTest {
  @Inject
  private WxMpService mpService;

  @Test
  public void testGetOAuth2ServiceType() {
    assertThat(this.mpService.getOAuth2Service()).isInstanceOf(WxMpOAuth2Service.class);
  }

  @Test
  public void testBuildAuthorizationUrl() {
    final String url = this.mpService.getOAuth2Service().buildAuthorizationUrl("http://www.baidu.com", "test", "GOD");
    assertThat(url).isEqualTo("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" +
      this.mpService.getWxMpConfigStorage().getAppId() +
      "&redirect_uri=http%3A%2F%2Fwww.baidu.com&response_type=code&scope=test&state=GOD&connect_redirect=1#wechat_redirect");
  }

  @Test
  public void testGetAccessToken() throws WxErrorException {
    final WxOAuth2AccessToken accessToken = this.mpService.getOAuth2Service().getAccessToken("11");
    assertThat(accessToken).isNotNull();
  }

  @Test
  public void testRefreshAccessToken() throws WxErrorException {
    final WxOAuth2AccessToken accessToken = this.mpService.getOAuth2Service().refreshAccessToken("11");
    assertThat(accessToken).isNotNull();
  }

  @Test
  public void testGetUserInfo() throws WxErrorException {
    final WxOAuth2AccessToken accessToken = this.mpService.getOAuth2Service().getAccessToken("11");
    final WxOAuth2UserInfo userInfo = this.mpService.getOAuth2Service().getUserInfo(accessToken, null);
    assertThat(userInfo).isNotNull();
  }

  @Test
  public void testValidateAccessToken() {
    final boolean result = this.mpService.getOAuth2Service().validateAccessToken(new WxOAuth2AccessToken());
    assertThat(result).isTrue();
  }
}
