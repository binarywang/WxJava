package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import com.google.inject.Inject;
import lombok.SneakyThrows;
import me.chanjar.weixin.common.bean.CommonUploadParam;
import me.chanjar.weixin.common.bean.WxAccessTokenEntity;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.error.WxMpErrorMsgEnum;
import me.chanjar.weixin.common.util.http.HttpClientType;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import org.apache.commons.lang3.StringUtils;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMaServiceImplTest {

  @Inject
  private WxMaService wxService;
  @Inject
  private WxMaServiceOkHttpImpl wxMaServiceOkHttp;

  public void testRefreshAccessToken() throws WxErrorException {
    WxMaConfig configStorage = this.wxService.getWxMaConfig();
    String before = configStorage.getAccessToken();
    this.wxService.getAccessToken(false);

    String after = configStorage.getAccessToken();
    assertNotEquals(before, after);
    assertTrue(StringUtils.isNotBlank(after));
  }


  private void updateAccessTokenBefore(WxAccessTokenEntity wxAccessTokenEntity) {
    System.out.println("token:" + wxAccessTokenEntity.toString());
  }

  public void testTokenCallBack() throws WxErrorException {
    WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
    WxMaConfig configStorage = this.wxService.getWxMaConfig();
    config.setAppid(configStorage.getAppid());
    config.setSecret(configStorage.getSecret());
//    //第一种方式
//    config.setUpdateAccessTokenBefore(e -> {
//      System.out.println("token:" + e.toString());
//    });
    //第二种方式
    config.setUpdateAccessTokenBefore(this::updateAccessTokenBefore);
    this.wxService.setWxMaConfig(config);

    String before = config.getAccessToken();
    this.wxService.getAccessToken(true);
    String after = config.getAccessToken();
    assertNotEquals(before, after);
    assertTrue(StringUtils.isNotBlank(after));
    config.enableUpdateAccessTokenBefore(false);
    this.wxService.getAccessToken(true);
    after = config.getAccessToken();
    System.out.println(after);
  }

  public void testStableRefreshAccessToken() throws WxErrorException {
    WxMaConfig configStorage = this.wxMaServiceOkHttp.getWxMaConfig();
    configStorage.useStableAccessToken(true);
    String before = configStorage.getAccessToken();
    this.wxMaServiceOkHttp.getAccessToken(false);
    String after = configStorage.getAccessToken();
    assertNotEquals(before, after);
    assertTrue(StringUtils.isNotBlank(after));
  }


  @Test(expectedExceptions = {WxErrorException.class})
  public void testGetPaidUnionId() throws WxErrorException {
    final String unionId = this.wxService.getPaidUnionId("1", null, "3", "4");
    assertThat(unionId).isNotEmpty();
  }

  @Test
  public void testPost() throws WxErrorException {
    final String postResult = this.wxService.post("https://api.weixin.qq.com/wxa/setdynamicdata", "{\n" +
      "    \"data\": \"{\\\"items\\\": [{\\\"from\\\":{\\\"city_name_cn\\\":\\\"广州市\\\"},\\\"to\\\":{\\\"city_name_cn\\\":\\\"北京市\\\"}}], \\\"attribute\\\": {\\\"count\\\": 1, \\\"totalcount\\\": 100, \\\"id\\\": \\\"1\\\", \\\"seq\\\": 0}}\",\n" +
      "    \"lifespan\": 86400,\n" +
      "    \"query\": \"{\\\"type\\\":100005}\",\n" +
      "    \"scene\": 1\n" +
      "}");

    System.out.println(postResult);
  }

  @Test
  public void testGetRequestHttpClient() {
  }

  @Test
  public void testGetRequestHttpProxy() {
  }

  @Test
  public void testGetRequestType() {
    assertThat(new WxMaServiceImpl().getRequestType()).isEqualTo(HttpClientType.APACHE_HTTP);
    assertThat(new WxMaServiceHttpComponentsImpl().getRequestType()).isEqualTo(HttpClientType.HTTP_COMPONENTS);
  }

  @Test
  public void testInitHttp() {
  }

  @Test
  public void testGetRequestHttp() {
  }

  @Test
  public void testGetAccessToken() {
  }

  @Test
  public void testJsCode2SessionInfo() {
  }

  @Test
  public void testSetDynamicData() throws WxErrorException {
    this.wxService.setDynamicData(86400, "1000001", 1,
      "{\"items\":[{\"stock_name\":\"腾讯控股\", \"stock_code\":\"00700\", \"stock_market\":\"hk\"}], \"attribute\":{\"count\":2, \"totalcount\": 100, \"id\": \"XXX\", \"seq\": 0}}");
  }

  @Test
  public void testCheckSignature() {
  }

  @Test
  public void testTestGetAccessToken() {
  }

  @Test
  public void testGet() {
  }

  @Test
  public void testExecute() {
  }

  @Test
  public void testExecuteAutoRefreshToken() throws WxErrorException, IOException {
    //测试access token获取时的重试机制
    WxMaServiceImpl service = new WxMaServiceImpl() {
      @Override
      public String getAccessToken(boolean forceRefresh) throws WxErrorException {
        return "模拟一个过期的access token:" + System.currentTimeMillis();
      }
    };
    WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
    config.setAppid("1");
    service.setWxMaConfig(config);
    RequestExecutor<Object, Object> re = mock(RequestExecutor.class);

    AtomicInteger counter = new AtomicInteger();
    Mockito.when(re.execute(Mockito.anyString(), Mockito.any(), Mockito.any())).thenAnswer((invocation) -> {
      counter.incrementAndGet();
      WxError error = WxError.builder().errorCode(WxMpErrorMsgEnum.CODE_40001.getCode()).errorMsg(WxMpErrorMsgEnum.CODE_40001.getMsg()).build();
      throw new WxErrorException(error);
    });
    try {
      Object execute = service.execute(re, "http://baidu.com", new HashMap<>());
      Assert.fail("代码应该不会执行到这里");
    } catch (WxErrorException e) {
      Assert.assertEquals(WxMpErrorMsgEnum.CODE_40001.getCode(), e.getError().getErrorCode());
      Assert.assertEquals(2, counter.get());
    }
  }

  @SneakyThrows
  @Test
  public void upload() {
    CommonUploadParam param = CommonUploadParam.fromFile("media", new File("./static/1.jpg"));
    String result = wxService.upload("https://api.weixin.qq.com/wxa/sec/uploadauthmaterial", param);
    System.out.println(result);
  }

  @Test
  public void testGetWxMaConfig() {
  }

  @Test
  public void testSetWxMaConfig() {
  }

  @Test
  public void testSetRetrySleepMillis() {
  }

  @Test
  public void testSetMaxRetryTimes() {
  }

  @Test
  public void testGetMsgService() {
  }

  @Test
  public void testGetMediaService() {
  }

  @Test
  public void testGetUserService() {
  }

  @Test
  public void testGetQrcodeService() {
  }

  @Test
  public void testGetTemplateService() {
  }

  @Test
  public void testGetSubscribeService() {
  }

  @Test
  public void testGetAnalysisService() {
  }

  @Test
  public void testGetCodeService() {
  }

  @Test
  public void testGetJsapiService() {
  }

  @Test
  public void testGetSettingService() {
  }

  @Test
  public void testGetShareService() {
  }

  @Test
  public void testGetRunService() {
  }

  @Test
  public void testGetSecCheckService() {
  }

  @Test
  public void testGetPluginService() {
  }

  @Test
  public void testGetExpressService() {
  }

  @Test
  public void testGetCloudService() {
  }
}
