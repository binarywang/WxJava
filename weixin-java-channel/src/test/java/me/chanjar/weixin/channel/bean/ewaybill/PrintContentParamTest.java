package me.chanjar.weixin.channel.bean.ewaybill;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import me.chanjar.weixin.channel.util.JsonUtils;
import org.testng.annotations.Test;

/** 电子面单打印请求 JSON 契约测试。 */
public class PrintContentParamTest {

  @Test
  public void shouldEncodeWaybillIdsAndOptionalTemplateId() {
    String json = JsonUtils.encode(new PrintContentParam(Arrays.asList("wb_1", "wb_2"), "tpl_1"));

    assertTrue(json.contains("\"waybill_ids\""));
    assertTrue(json.contains("\"template_id\":\"tpl_1\""));
    assertFalse(json.contains("\"waybill_id\""));
  }
}
