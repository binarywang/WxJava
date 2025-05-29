package me.chanjar.weixin.mp.api.impl;

import com.google.common.collect.Sets;
import com.google.inject.Inject;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.bean.WxNetCheckResult;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.error.WxMpErrorMsgEnum;
import me.chanjar.weixin.common.util.http.HttpClientType;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.test.ApiTestModule;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import me.chanjar.weixin.mp.util.WxMpConfigStorageHolder;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.testng.Assert.*;

/**
 * <pre>
 *  Created by BinaryWang on 2019/3/29.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Test
@Guice(modules = ApiTestModule.class)
public class BaseWxMpServiceImplTest {
  @Inject
  private WxMpService wxService;

  @Test
  public void testSwitchover() {
    assertTrue(this.wxService.switchover("another"));
    assertThat(WxMpConfigStorageHolder.get()).isEqualTo("another");
    assertFalse(this.wxService.switchover("whatever"));
    assertFalse(this.wxService.switchover("default"));
  }

  @Test
  public void testSwitchoverTo() throws WxErrorException {
    assertThat(this.wxService.switchoverTo("another").getAccessToken()).isNotEmpty();
    assertThat(WxMpConfigStorageHolder.get()).isEqualTo("another");
  }

  @Test
  public void testNetCheck() throws WxErrorException {
    WxNetCheckResult result = this.wxService.netCheck(WxConsts.NetCheckArgs.ACTIONALL, WxConsts.NetCheckArgs.OPERATORDEFAULT);
    Assert.assertNotNull(result);

  }

  @Test
  public void testGetCallbackIP() throws WxErrorException {
    String[] ipArray = this.wxService.getCallbackIP();
    System.out.println(Arrays.toString(ipArray));
    Assert.assertNotNull(ipArray);
    Assert.assertNotEquals(ipArray.length, 0);
  }

  public void testShortUrl() throws WxErrorException {
    String shortUrl = this.wxService.shortUrl("http://www.baidu.com/test?access_token=123");
    assertThat(shortUrl).isNotEmpty();
    System.out.println(shortUrl);
  }

  @Test(expectedExceptions = WxErrorException.class)
  public void testShortUrl_with_exceptional_url() throws WxErrorException {
    this.wxService.shortUrl("http://www.baidu.com/test?redirect_count=1&access_token=123");
  }

  @Test
  public void refreshAccessTokenDuplicatelyTest() throws InterruptedException {
    // 测试多线程刷新accessToken时是否重复刷新
    wxService.getWxMpConfigStorage().expireAccessToken();
    final Set<String> set = Sets.newConcurrentHashSet();
    Runnable r = () -> {
      try {
        String accessToken = wxService.getAccessToken();
        set.add(accessToken);
      } catch (WxErrorException e) {
        e.printStackTrace();
      }
    };

    final int threadNumber = 10;
    ExecutorService executorService = Executors.newFixedThreadPool(threadNumber);
    for (int i = 0; i < threadNumber; i++) {
      executorService.submit(r);
    }
    executorService.shutdown();
    boolean isTerminated = executorService.awaitTermination(15, TimeUnit.SECONDS);
    System.out.println("isTerminated: " + isTerminated);
    System.out.println("times of refreshing accessToken: " + set.size());

    assertEquals(set.size(), 1);

  }

  @Test
  public void testCheckSignature() {
  }

  @Test
  public void testGetTicket() {
  }

  @Test
  public void testTestGetTicket() {
  }

  @Test
  public void testGetJsapiTicket() {
  }

  @Test
  public void testTestGetJsapiTicket() {
  }

  @Test
  public void testCreateJsapiSignature() throws WxErrorException {
    final WxJsapiSignature jsapiSignature = this.wxService.createJsapiSignature("http://www.baidu.com");
    assertThat(jsapiSignature).isNotNull();
    assertThat(jsapiSignature.getSignature()).isNotNull();
    System.out.println(jsapiSignature);
  }

  @Test
  public void testGetAccessToken() {
  }

  @Test
  public void testSemanticQuery() {
  }

  @Test
  public void testOauth2buildAuthorizationUrl() {
  }

  @Test
  public void testBuildQrConnectUrl() {
  }

  @Test
  public void testOauth2getAccessToken() {
  }

  @Test
  public void testOauth2refreshAccessToken() {
  }

  @Test
  public void testOauth2getUserInfo() {
  }

  @Test
  public void testOauth2validateAccessToken() {
  }

  @Test
  public void testGetCurrentAutoReplyInfo() {
  }

  @Test
  public void testClearQuota() {
  }

  @Test
  public void testGet() {
  }

  @Test
  public void testTestGet() {
  }

  @Test
  public void testPost() {
  }

  @Test
  public void testTestPost() {
  }

  @Test
  public void testExecute() throws WxErrorException, IOException {

  }

  @Test
  public void testExecuteAutoRefreshToken() throws WxErrorException, IOException {
    //测试access token获取时的重试机制
    BaseWxMpServiceImpl<Object, Object> service = new BaseWxMpServiceImpl() {
      @Override
      public String getAccessToken(boolean forceRefresh) throws WxErrorException {
        return "模拟一个过期的access token:" + System.currentTimeMillis();
      }

      @Override
      protected String doGetAccessTokenRequest() throws IOException {
        return null;
      }

      @Override
      protected String doGetStableAccessTokenRequest(boolean forceRefresh) throws IOException {
        return null;
      }

      @Override
      public void initHttp() {

      }

      @Override
      public Object getRequestHttpClient() {
        return null;
      }

      @Override
      public Object getRequestHttpProxy() {
        return null;
      }

      @Override
      public HttpClientType getRequestType() {
        return null;
      }
    };
    WxMpDefaultConfigImpl config = new WxMpDefaultConfigImpl();
    config.setAppId("1");
    service.setWxMpConfigStorage(config);
    RequestExecutor<Object, Object> re = mock(RequestExecutor.class);

    AtomicInteger counter = new AtomicInteger();
    Mockito.when(re.execute(Mockito.anyString(), Mockito.any(), Mockito.any())).thenAnswer(invocation -> {
      counter.incrementAndGet();
      WxError error = WxError.builder().errorCode(WxMpErrorMsgEnum.CODE_40001.getCode()).errorMsg(WxMpErrorMsgEnum.CODE_40001.getMsg()).build();
      throw new WxErrorException(error);
    });
    try {
      Object execute = service.execute(re, "http://baidu.com", new HashMap<>());
      Assert.assertTrue(false, "代码应该不会执行到这里");
    } catch (WxErrorException e) {
      Assert.assertEquals(WxMpErrorMsgEnum.CODE_40001.getCode(), e.getError().getErrorCode());
      Assert.assertEquals(2, counter.get());
    }
  }

  @Test
  public void testTestExecute() {
  }

  @Test
  public void testExecuteInternal() {
  }

  @Test
  public void testGetWxMpConfigStorage() {
  }

  @Test
  public void testSetWxMpConfigStorage() {
  }

  @Test
  public void testSetMultiConfigStorages() {
  }

  @Test
  public void testTestSetMultiConfigStorages() {
  }

  @Test
  public void testAddConfigStorage() {
  }

  @Test
  public void testRemoveConfigStorage() {
  }

  @Test
  public void testSetRetrySleepMillis() {
  }

  @Test
  public void testSetMaxRetryTimes() {
  }

  @Test
  public void testGetKefuService() {
  }

  @Test
  public void testGetMaterialService() {
  }

  @Test
  public void testGetMenuService() {
  }

  @Test
  public void testGetUserService() {
  }

  @Test
  public void testGetUserTagService() {
  }

  @Test
  public void testGetQrcodeService() {
  }

  @Test
  public void testGetCardService() {
  }

  @Test
  public void testGetDataCubeService() {
  }

  @Test
  public void testGetBlackListService() {
  }

  @Test
  public void testGetStoreService() {
  }

  @Test
  public void testGetTemplateMsgService() {
  }

  @Test
  public void testGetSubscribeMsgService() {
  }

  @Test
  public void testGetDeviceService() {
  }

  @Test
  public void testGetShakeService() {
  }

  @Test
  public void testGetMemberCardService() {
  }

  @Test
  public void testGetRequestHttp() {
  }

  @Test
  public void testGetMassMessageService() {
  }

  @Test
  public void testSetKefuService() {
  }

  @Test
  public void testSetMaterialService() {
  }

  @Test
  public void testSetMenuService() {
  }

  @Test
  public void testSetUserService() {
  }

  @Test
  public void testSetTagService() {
  }

  @Test
  public void testSetQrCodeService() {
  }

  @Test
  public void testSetCardService() {
  }

  @Test
  public void testSetStoreService() {
  }

  @Test
  public void testSetDataCubeService() {
  }

  @Test
  public void testSetBlackListService() {
  }

  @Test
  public void testSetTemplateMsgService() {
  }

  @Test
  public void testSetDeviceService() {
  }

  @Test
  public void testSetShakeService() {
  }

  @Test
  public void testSetMemberCardService() {
  }

  @Test
  public void testSetMassMessageService() {
  }

  @Test
  public void testGetAiOpenService() {
  }

  @Test
  public void testSetAiOpenService() {
  }

  @Test
  public void testGetWifiService() {
  }

  @Test
  public void testGetOcrService() {
  }

  @Test
  public void testGetMarketingService() {
  }

  @Test
  public void testSetMarketingService() {
  }

  @Test
  public void testSetOcrService() {
  }

  @Test
  public void testGetCommentService() {
  }

  @Test
  public void testSetCommentService() {
  }

  @Test
  public void testGetImgProcService() {
  }

  @Test
  public void testSetImgProcService() {
  }
}
