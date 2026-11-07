package me.chanjar.weixin.open.util.json;

import com.google.gson.Gson;
import me.chanjar.weixin.open.bean.WxOpenAuthorizerAccessToken;
import me.chanjar.weixin.open.bean.WxOpenComponentAccessToken;
import me.chanjar.weixin.open.bean.auth.WxOpenAuthorizationInfo;
import me.chanjar.weixin.open.bean.result.WxOpenQueryAuthResult;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;

/**
 * {@link WxOpenGsonBuilder} 及其注册的反序列化适配器单元测试
 */
public class WxOpenGsonBuilderTest {

  @Test
  public void testCreateReturnsSameInstance() {
    assertSame(WxOpenGsonBuilder.create(), WxOpenGsonBuilder.create());
  }

  @Test
  public void testComponentAccessToken() {
    String json = "{\"component_access_token\":\"component_access_token_value\",\"expires_in\":7200}";
    WxOpenComponentAccessToken token = WxOpenGsonBuilder.create().fromJson(json, WxOpenComponentAccessToken.class);
    assertNotNull(token);
    assertEquals(token.getComponentAccessToken(), "component_access_token_value");
    assertEquals(token.getExpiresIn(), 7200);
  }

  @Test
  public void testAuthorizerAccessToken() {
    String json = "{\"authorizer_access_token\":\"access_token_value\","
      + "\"authorizer_refresh_token\":\"refresh_token_value\",\"expires_in\":7200}";
    WxOpenAuthorizerAccessToken token = WxOpenGsonBuilder.create().fromJson(json, WxOpenAuthorizerAccessToken.class);
    assertNotNull(token);
    assertEquals(token.getAuthorizerAccessToken(), "access_token_value");
    assertEquals(token.getAuthorizerRefreshToken(), "refresh_token_value");
    assertEquals(token.getExpiresIn(), 7200);
  }

  @Test
  public void testAuthorizationInfo() {
    String json = "{\"authorizer_appid\":\"wx0000000000000002\","
      + "\"authorizer_access_token\":\"access_token_value\","
      + "\"authorizer_refresh_token\":\"refresh_token_value\","
      + "\"expires_in\":7200,"
      + "\"func_info\":[{\"funcscope_category\":{\"id\":1}},{\"funcscope_category\":{\"id\":15}},{}]}";

    WxOpenAuthorizationInfo info = WxOpenGsonBuilder.create().fromJson(json, WxOpenAuthorizationInfo.class);
    assertNotNull(info);
    assertEquals(info.getAuthorizerAppid(), "wx0000000000000002");
    assertEquals(info.getAuthorizerAccessToken(), "access_token_value");
    assertEquals(info.getAuthorizerRefreshToken(), "refresh_token_value");
    assertEquals(info.getExpiresIn(), 7200);
    assertEquals(info.getFuncInfo(), Arrays.asList(1, 15));
  }

  @Test
  public void testAuthorizationInfoWithoutFuncInfo() {
    WxOpenAuthorizationInfo info = WxOpenGsonBuilder.create()
      .fromJson("{\"authorizer_appid\":\"wx0000000000000002\"}", WxOpenAuthorizationInfo.class);
    assertNotNull(info);
    assertNotNull(info.getFuncInfo());
    assertTrue(info.getFuncInfo().isEmpty());
  }

  @Test
  public void testQueryAuthResult() {
    String json = "{\"authorization_info\":{\"authorizer_appid\":\"wx0000000000000002\","
      + "\"authorizer_access_token\":\"access_token_value\",\"expires_in\":7200,"
      + "\"authorizer_refresh_token\":\"refresh_token_value\","
      + "\"func_info\":[{\"funcscope_category\":{\"id\":1}}]}}";

    WxOpenQueryAuthResult result = WxOpenGsonBuilder.create().fromJson(json, WxOpenQueryAuthResult.class);
    assertNotNull(result);
    assertNotNull(result.getAuthorizationInfo());
    assertEquals(result.getAuthorizationInfo().getAuthorizerAppid(), "wx0000000000000002");
    assertEquals(result.getAuthorizationInfo().getFuncInfo(), Arrays.asList(1));
  }

  @Test
  public void testHtmlEscapingDisabled() {
    Gson gson = WxOpenGsonBuilder.create();
    assertEquals(gson.toJson("a&b"), "\"a&b\"");
  }
}
