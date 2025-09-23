package me.chanjar.weixin.cp.tp.service.impl;

import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.redis.RedissonWxRedisOps;
import me.chanjar.weixin.cp.bean.WxCpProviderToken;
import me.chanjar.weixin.cp.bean.WxCpTpCorpId2OpenCorpId;
import me.chanjar.weixin.cp.config.WxCpTpConfigStorage;
import me.chanjar.weixin.cp.config.impl.AbstractWxCpTpInRedisConfigImpl;
import me.chanjar.weixin.cp.config.impl.WxCpTpRedisTemplateConfigImpl;
import me.chanjar.weixin.cp.config.impl.WxCpTpRedissonConfigImpl;
import me.chanjar.weixin.cp.tp.service.WxCpTpService;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 测试用参数请在自己的企业微信第三方开发者后台查找匹配
 * 如果测试不过，请检查redis中是否存在微信定期推送的suite_ticket
 *
 * @author zhangq <zhangq002@gmail.com>
 */
public class WxCpTpServiceApacheHttpClientImplTest {

  /**
   * The constant API_URL.
   */
  public static final String API_URL = "https://qyapi.weixin.qq.com";
  /**
   * The constant SUITE_ID.
   */
  public static final String SUITE_ID = "xxxxxx";
  /**
   * The constant SUITE_SECRET.
   */
  public static final String SUITE_SECRET = "xxxxxx";
  /**
   * The constant TOKEN.
   */
  public static final String TOKEN = "xxxxxx";
  /**
   * The constant AES_KEY.
   */
  public static final String AES_KEY = "xxxxxx";
  /**
   * The constant PROVIDER_CORP_ID.
   */
  public static final String PROVIDER_CORP_ID = "xxxxxx";
  /**
   * The constant PROVIDER_SECRET.
   */
  public static final String PROVIDER_SECRET = "xxxxxx";
  /**
   * The constant REDIS_ADDR.
   */
  public static final String REDIS_ADDR = "redis://xxx.xxx.xxx.xxx:6379";
  /**
   * The constant REDIS_PASSWD.
   */
  public static final String REDIS_PASSWD = "xxxxxx";

  private static final String AUTH_CORP_ID = "xxxxxx";
  private static final String PERMANENT_CODE = "xxxxxx";

  private WxCpTpService wxCpTpService;

  /**
   * Sets up.
   */
  @BeforeMethod
  public void setUp() {
    wxCpTpService = new WxCpTpServiceApacheHttpClientImpl();
    wxCpTpService.setWxCpTpConfigStorage(wxCpTpConfigStorage());
  }

  /**
   * Wx cp tp config storage wx cp tp config storage.
   *
   * @return the wx cp tp config storage
   */
  public WxCpTpConfigStorage wxCpTpConfigStorage() {
    WxCpTpRedissonConfigImpl wxCpTpRedissonConfig=new WxCpTpRedissonConfigImpl(redissonClient(),"");
    wxCpTpRedissonConfig.setBaseApiUrl(API_URL);
    wxCpTpRedissonConfig.setSuiteId(SUITE_ID);
    wxCpTpRedissonConfig.setSuiteSecret(SUITE_SECRET);
    wxCpTpRedissonConfig.setToken(TOKEN);
    wxCpTpRedissonConfig.setEncodingAESKey(AES_KEY);
    wxCpTpRedissonConfig.setCorpId(PROVIDER_CORP_ID);
    wxCpTpRedissonConfig.setProviderSecret(PROVIDER_SECRET);
    return wxCpTpRedissonConfig;
  }

  /**
   * Redisson client redisson client.
   *
   * @return the redisson client
   */
  public RedissonClient redissonClient() {
    Config config = new Config();
    config.useSingleServer().setAddress(REDIS_ADDR).setConnectTimeout(10 * 1000).setDatabase(6)
      .setPassword(REDIS_PASSWD).setConnectionMinimumIdleSize(2).setConnectionPoolSize(2);
    return Redisson.create(config);
  }

  /**
   * Test get suite access token entity.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetSuiteAccessTokenEntity() throws WxErrorException {
    wxCpTpService.getWxCpTpConfigStorage().expireSuiteAccessToken();
    WxAccessToken suiteAccessTokenEntity = wxCpTpService.getSuiteAccessTokenEntity(true);
    System.out.println("suiteAccessTokenEntity:" + suiteAccessTokenEntity);
    Assert.assertTrue(
      StringUtils.isNotBlank(suiteAccessTokenEntity.getAccessToken()) && suiteAccessTokenEntity.getExpiresIn() > 0);
    suiteAccessTokenEntity = wxCpTpService.getSuiteAccessTokenEntity();
    System.out.println("suiteAccessTokenEntity:" + suiteAccessTokenEntity);
    Assert.assertTrue(
      StringUtils.isNotBlank(suiteAccessTokenEntity.getAccessToken()) && suiteAccessTokenEntity.getExpiresIn() > 0);
  }

  /**
   * Test get wx cp provider token entity.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetWxCpProviderTokenEntity() throws WxErrorException {
    wxCpTpService.getWxCpTpConfigStorage().expireProviderToken();
    WxCpProviderToken providerToken = wxCpTpService.getWxCpProviderTokenEntity(true);
    System.out.println("providerToken:" + providerToken);
    Assert
      .assertTrue(StringUtils.isNotBlank(providerToken.getProviderAccessToken()) && providerToken.getExpiresIn() > 0);
    providerToken = wxCpTpService.getWxCpProviderTokenEntity();
    System.out.println("providerToken:" + providerToken);
    Assert
      .assertTrue(StringUtils.isNotBlank(providerToken.getProviderAccessToken()) && providerToken.getExpiresIn() > 0);
  }

  /**
   * Test get corp token.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetCorpToken() throws WxErrorException {
    wxCpTpService.getWxCpTpConfigStorage().expireAccessToken(AUTH_CORP_ID);
    WxAccessToken accessToken = wxCpTpService.getCorpToken(AUTH_CORP_ID, PERMANENT_CODE, true);
    System.out.println("accessToken:" + accessToken);
    accessToken = wxCpTpService.getCorpToken(AUTH_CORP_ID, PERMANENT_CODE);
    System.out.println("accessToken:" + accessToken);
  }

  /**
   * Test get auth corp js api ticket.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetAuthCorpJsApiTicket() throws WxErrorException {
    wxCpTpService.getWxCpTpConfigStorage().expireAuthCorpJsApiTicket(AUTH_CORP_ID);
    String authCorpJsApiTicket = wxCpTpService.getAuthCorpJsApiTicket(AUTH_CORP_ID, true);
    System.out.println("authCorpJsApiTicket:" + authCorpJsApiTicket);
    authCorpJsApiTicket = wxCpTpService.getAuthCorpJsApiTicket(AUTH_CORP_ID);
    System.out.println("authCorpJsApiTicket:" + authCorpJsApiTicket);
  }

  /**
   * Test get suite js api ticket.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetSuiteJsApiTicket() throws WxErrorException {
    wxCpTpService.getWxCpTpConfigStorage().expireAuthSuiteJsApiTicket(AUTH_CORP_ID);
    String suiteJsApiTicket = wxCpTpService.getSuiteJsApiTicket(AUTH_CORP_ID, true);
    System.out.println("suiteJsApiTicket:" + suiteJsApiTicket);
    suiteJsApiTicket = wxCpTpService.getSuiteJsApiTicket(AUTH_CORP_ID);
    System.out.println("suiteJsApiTicket:" + suiteJsApiTicket);
  }

  @Test
  public void testCorpId2OpenCorpId() throws WxErrorException {
    WxCpTpCorpId2OpenCorpId openCorpId = wxCpTpService.corpId2OpenCorpId("wpVIkfEAAAu2wGiOEeNMQ69afwLM6BbA");
    System.out.println("openCorpId:" + openCorpId);
  }
}
