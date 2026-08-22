package cn.binarywang.wx.miniapp.bean.scheme;

import me.chanjar.weixin.common.util.json.GsonParser;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WxMaGenerateNfcSchemeRequestTest {
  @Test
  public void testToJson() {
    WxMaGenerateNfcSchemeRequest request = WxMaGenerateNfcSchemeRequest.newBuilder()
      .jumpWxa(WxMaGenerateNfcSchemeRequest.JumpWxa.newBuilder()
        .path("pages/index/index")
        .query("device=demo")
        .envVersion("trial")
        .build())
      .modelId("model-demo")
      .sn("sn-demo")
      .build();

    String expectedJson = "{\n"
      + "  \"jump_wxa\": {\n"
      + "    \"path\": \"pages/index/index\",\n"
      + "    \"query\": \"device=demo\",\n"
      + "    \"env_version\": \"trial\"\n"
      + "  },\n"
      + "  \"model_id\": \"model-demo\",\n"
      + "  \"sn\": \"sn-demo\"\n"
      + "}";

    assertThat(request.toJson()).isEqualTo(GsonParser.parse(expectedJson).toString());
  }
}
