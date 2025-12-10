package me.chanjar.weixin.common.util.json;

import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import org.testng.annotations.Test;

import java.io.StringReader;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * GsonParser 测试类
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class GsonParserTest {

  @Test
  public void testParseString() {
    String json = "{\"code\":\"ALREADY_EXISTS\",\"message\":\"当前订单已关闭，可查询订单了解关闭原因\"}";
    JsonObject jsonObject = GsonParser.parse(json);
    assertNotNull(jsonObject);
    assertEquals(jsonObject.get("code").getAsString(), "ALREADY_EXISTS");
    assertEquals(jsonObject.get("message").getAsString(), "当前订单已关闭，可查询订单了解关闭原因");
  }

  @Test
  public void testParseReader() {
    String json = "{\"code\":\"SUCCESS\",\"message\":\"处理成功\"}";
    StringReader reader = new StringReader(json);
    JsonObject jsonObject = GsonParser.parse(reader);
    assertNotNull(jsonObject);
    assertEquals(jsonObject.get("code").getAsString(), "SUCCESS");
    assertEquals(jsonObject.get("message").getAsString(), "处理成功");
  }

  @Test
  public void testParseJsonReader() {
    String json = "{\"errcode\":0,\"errmsg\":\"ok\"}";
    JsonReader jsonReader = new JsonReader(new StringReader(json));
    JsonObject jsonObject = GsonParser.parse(jsonReader);
    assertNotNull(jsonObject);
    assertEquals(jsonObject.get("errcode").getAsInt(), 0);
    assertEquals(jsonObject.get("errmsg").getAsString(), "ok");
  }
}
