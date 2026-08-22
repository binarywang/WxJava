package me.chanjar.weixin.channel.bean.ewaybill;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import me.chanjar.weixin.channel.util.JsonUtils;
import org.testng.annotations.Test;

/** 电子面单打印请求 JSON 契约测试。 */
public class PrintContentParamTest {

  @Test
  public void shouldHaveNoArgsConstructor() {
    PrintContentParam param = new PrintContentParam();
    param.setEwaybillOrderId("order_1");

    assertTrue(JsonUtils.encode(param).contains("\"ewaybill_order_id\""));
  }

  @Test
  public void shouldEncodeWaybillIdsAndOptionalTemplateId() {
    String json = JsonUtils.encode(new PrintContentParam("order_1", "tpl_1"));

    assertTrue(json.contains("\"ewaybill_order_id\""));
    assertTrue(json.contains("\"template_id\":\"tpl_1\""));
    assertFalse(json.contains("\"waybill_id\""));
  }
}
