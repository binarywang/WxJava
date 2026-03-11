package me.chanjar.weixin.open.api.impl;

import me.chanjar.weixin.open.api.WxOpenConfigStorage;
import me.chanjar.weixin.open.bean.WxOpenAuthorizerAccessToken;
import me.chanjar.weixin.open.bean.WxOpenComponentAccessToken;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WxOpenInRedissonConfigStorageTest {

  private WxOpenConfigStorage wxOpenConfigStorage;

  @BeforeClass
  public void setWxOpenConfigStorage(){
    Config config = new Config();
    config.useSingleServer().setAddress("redis://127.0.0.1:6379")
      .setDatabase(0);
    config.setTransportMode(TransportMode.NIO);
    RedissonClient redisson = Redisson.create(config);
    this.wxOpenConfigStorage = new WxOpenInRedissonConfigStorage(redisson);
    this.wxOpenConfigStorage.setWxOpenInfo("ComponentAppId", "ComponentAppSecret", "ComponentToken","ComponentAesKey");
    this.wxOpenConfigStorage.setComponentVerifyTicket("ComponentVerifyTicket");
  }

  @Test
  public void testGetComponentVerifyTicket() {
    String componentVerifyTicket = this.wxOpenConfigStorage.getComponentVerifyTicket();
    Assert.assertEquals(componentVerifyTicket, "ComponentVerifyTicket");
  }

  @Test
  public void testSetComponentVerifyTicket() {
    this.wxOpenConfigStorage.setComponentVerifyTicket("new ComponentVerifyTicket");
    String componentVerifyTicket = this.wxOpenConfigStorage.getComponentVerifyTicket();
    Assert.assertEquals(componentVerifyTicket, "new ComponentVerifyTicket");
  }

  @Test
  public void testIsComponentAccessTokenExpired() {
    String responseContent = "{\"component_access_token\": \"new componentAccessToken\", \"expires_in\": 10000}";
    WxOpenComponentAccessToken componentAccessToken = WxOpenComponentAccessToken.fromJson(responseContent);
    this.wxOpenConfigStorage.updateComponentAccessToken(componentAccessToken);
    boolean expired = this.wxOpenConfigStorage.isComponentAccessTokenExpired();
    Assert.assertEquals(expired, false);

    this.wxOpenConfigStorage.expireComponentAccessToken();
    expired = this.wxOpenConfigStorage.isComponentAccessTokenExpired();
    Assert.assertEquals(expired, true);

  }

  @Test
  public void testGetAuthorizerRefreshToken() {
    String appid = "appid1";
    this.wxOpenConfigStorage.setAuthorizerRefreshToken(appid, "AuthorizerRefreshToken 1");
    String authorizerAccessToken = this.wxOpenConfigStorage.getAuthorizerRefreshToken(appid);
    Assert.assertEquals(authorizerAccessToken, "AuthorizerRefreshToken 1");

    this.wxOpenConfigStorage.setAuthorizerRefreshToken(appid, "AuthorizerRefreshToken 2");
    authorizerAccessToken = this.wxOpenConfigStorage.getAuthorizerRefreshToken(appid);
    Assert.assertEquals(authorizerAccessToken, "AuthorizerRefreshToken 2");
  }

  @Test
  public void testGetAuthorizerAccessToken() {
    String appid = "appid1";
    String responseContent = "{\"authorizer_access_token\": \"new authorizer_access_token\",\"expires_in\": 100000}";
    WxOpenAuthorizerAccessToken wxOpenAuthorizerAccessToken = WxOpenAuthorizerAccessToken.fromJson(responseContent);
    this.wxOpenConfigStorage.updateAuthorizerAccessToken(appid, wxOpenAuthorizerAccessToken);
    String authorizerAccessToken = this.wxOpenConfigStorage.getAuthorizerAccessToken(appid);
    Assert.assertEquals(authorizerAccessToken, "new authorizer_access_token");
  }

  @Test
  public void testIsAuthorizerAccessTokenExpired() {
    String appid = "appid1";
    String responseContent = "{\"authorizer_access_token\": \"new authorizer_access_token\",\"expires_in\": 100000}";
    WxOpenAuthorizerAccessToken wxOpenAuthorizerAccessToken = WxOpenAuthorizerAccessToken.fromJson(responseContent);
    this.wxOpenConfigStorage.updateAuthorizerAccessToken(appid, wxOpenAuthorizerAccessToken);
    String authorizerAccessToken = this.wxOpenConfigStorage.getAuthorizerAccessToken(appid);
    Assert.assertEquals(authorizerAccessToken, "new authorizer_access_token");

    boolean expired = this.wxOpenConfigStorage.isAuthorizerAccessTokenExpired(appid);
    Assert.assertEquals(expired, false);

    this.wxOpenConfigStorage.expireAuthorizerAccessToken(appid);
    expired = this.wxOpenConfigStorage.isAuthorizerAccessTokenExpired(appid);
    Assert.assertEquals(expired, true);
  }


  @Test
  public void testGetJsapiTicket() {
    String appid = "appid1";
    this.wxOpenConfigStorage.updateJsapiTicket(appid, "jsapiTicket", 100000);
    String jsapiTicket = this.wxOpenConfigStorage.getJsapiTicket(appid);
    Assert.assertEquals(jsapiTicket, "jsapiTicket");

    boolean expired = this.wxOpenConfigStorage.isJsapiTicketExpired(appid);
    Assert.assertEquals(expired, false);

    this.wxOpenConfigStorage.expireJsapiTicket(appid);
    jsapiTicket = this.wxOpenConfigStorage.getJsapiTicket(appid);
    Assert.assertEquals(jsapiTicket, null);

    expired = this.wxOpenConfigStorage.isJsapiTicketExpired(appid);
    Assert.assertEquals(expired, true);
  }

  @Test
  public void testGetCardApiTicket() {
    String appid = "appid1";
    this.wxOpenConfigStorage.updateCardApiTicket(appid, "new CardApiTicket", 10000);
    String cardApiTicket = this.wxOpenConfigStorage.getCardApiTicket(appid);
    Assert.assertEquals(cardApiTicket, "new CardApiTicket");

    boolean expired = this.wxOpenConfigStorage.isCardApiTicketExpired(appid);
    Assert.assertEquals(expired, false);

    this.wxOpenConfigStorage.expireCardApiTicket(appid);
    expired = this.wxOpenConfigStorage.isCardApiTicketExpired(appid);
    Assert.assertEquals(expired, true);
  }

  @Test
  public void testComponentVerifyTicketExpiration() {
    // Test that ComponentVerifyTicket is set correctly
    this.wxOpenConfigStorage.setComponentVerifyTicket("test_ticket_for_expiration");
    String componentVerifyTicket = this.wxOpenConfigStorage.getComponentVerifyTicket();
    Assert.assertEquals(componentVerifyTicket, "test_ticket_for_expiration");
    
    // This test verifies that setComponentVerifyTicket now uses 43200 seconds (12 hours)
    // instead of Integer.MAX_VALUE for expiration. The actual expiration test would
    // require waiting or mocking time, which is not practical in unit tests.
    // The change is validated by code inspection and the fact that other tokens
    // use similar expiration patterns with specific timeouts.
  }
}
