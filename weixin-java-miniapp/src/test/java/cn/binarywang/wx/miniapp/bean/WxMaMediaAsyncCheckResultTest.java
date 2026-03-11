package cn.binarywang.wx.miniapp.bean;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * 测试多媒体内容安全异步检测结果解析
 *
 * @author copilot
 */
@Test
public class WxMaMediaAsyncCheckResultTest {

  public void testFromJsonWithResultAndDetail() {
    String json = "{\n"
      + "  \"trace_id\": \"test_trace_id_001\",\n"
      + "  \"result\": {\n"
      + "    \"suggest\": \"risky\",\n"
      + "    \"label\": 20001\n"
      + "  },\n"
      + "  \"detail\": [\n"
      + "    {\n"
      + "      \"strategy\": \"content_model\",\n"
      + "      \"errcode\": 0,\n"
      + "      \"suggest\": \"risky\",\n"
      + "      \"label\": 20006,\n"
      + "      \"prob\": 90\n"
      + "    }\n"
      + "  ]\n"
      + "}";

    WxMaMediaAsyncCheckResult result = WxMaMediaAsyncCheckResult.fromJson(json);

    assertNotNull(result);
    assertEquals(result.getTraceId(), "test_trace_id_001");

    assertNotNull(result.getResult());
    assertEquals(result.getResult().getSuggest(), "risky");
    assertEquals(result.getResult().getLabel(), "20001");

    assertNotNull(result.getDetail());
    assertEquals(result.getDetail().size(), 1);
    WxMaMediaAsyncCheckResult.DetailBean detail = result.getDetail().get(0);
    assertEquals(detail.getStrategy(), "content_model");
    assertEquals(detail.getErrcode(), Integer.valueOf(0));
    assertEquals(detail.getSuggest(), "risky");
    assertEquals(detail.getLabel(), "20006");
    assertEquals(detail.getProb(), Integer.valueOf(90));
  }
}
