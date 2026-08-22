package me.chanjar.weixin.channel.bean.ewaybill;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import me.chanjar.weixin.channel.util.JsonUtils;
import me.chanjar.weixin.channel.util.ResponseUtils;
import org.testng.annotations.Test;

/**
 * @author GitHub Copilot
 */
public class WxChannelEwaybillBeanTest {

  @Test
  public void testTemplateIdParamEncode() {
    TemplateIdParam param = new TemplateIdParam("tpl_1");
    String json = JsonUtils.encode(param);
    assertNotNull(json);
    assertTrue(json.contains("\"template_id\":\"tpl_1\""));
  }

  @Test
  public void testWaybillIdsParamEncode() {
    WaybillIdsParam param = new WaybillIdsParam(Arrays.asList("wb_1", "wb_2"));
    String json = JsonUtils.encode(param);
    assertNotNull(json);
    assertTrue(json.contains("\"waybill_ids\""));
    assertTrue(json.contains("wb_1"));
    assertTrue(json.contains("wb_2"));
  }

  @Test
  public void testDynamicRequestEncode() {
    PreCreateRequest request = new PreCreateRequest();
    request.addParam("order_id", "o_1");
    request.addParam("package_quantity", 2);

    String json = JsonUtils.encode(request);
    assertNotNull(json);
    assertTrue(json.contains("\"order_id\":\"o_1\""));
    assertTrue(json.contains("\"package_quantity\":2"));
    assertFalse(json.contains("\"params\""));
  }

  @Test
  public void testDynamicResponseDecode() {
    String json = "{\"errcode\":0,\"errmsg\":\"ok\",\"waybill_id\":\"wb_1\",\"print_info\":\"abc\"}";
    PrintContentResponse response = ResponseUtils.decode(json, PrintContentResponse.class);

    assertNotNull(response);
    assertTrue(response.isSuccess());
    assertEquals(response.getExtra().get("waybill_id"), "wb_1");
    assertEquals(response.getExtra().get("print_info"), "abc");
  }
}
