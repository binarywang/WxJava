package cn.binarywang.wx.miniapp.bean.express.request;

import cn.binarywang.wx.miniapp.constant.WxMaConstants;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class WxMaExpressOrderInsuredTest {

  @Test
  public void testDefaultValueWithNoArgsConstructor() {
    WxMaExpressOrderInsured insured = new WxMaExpressOrderInsured();

    assertEquals(insured.getUseInsured(), Integer.valueOf(WxMaConstants.OrderAddInsured.INSURED_PROGRAM));
    assertEquals(insured.getInsuredValue(), Integer.valueOf(WxMaConstants.OrderAddInsured.DEFAULT_INSURED_VALUE));
  }

  @Test
  public void testCanModifyInsuredConfigBySetter() {
    WxMaExpressOrderInsured insured = new WxMaExpressOrderInsured();
    insured.setUseInsured(WxMaConstants.OrderAddInsured.USE_INSURED);
    insured.setInsuredValue(10000);

    assertEquals(insured.getUseInsured(), Integer.valueOf(WxMaConstants.OrderAddInsured.USE_INSURED));
    assertEquals(insured.getInsuredValue(), Integer.valueOf(10000));
  }

  @Test
  public void testBuilderSupportsCustomInsuredConfig() {
    WxMaExpressOrderInsured insured = WxMaExpressOrderInsured.builder()
      .useInsured(WxMaConstants.OrderAddInsured.USE_INSURED)
      .insuredValue(5000)
      .build();

    assertEquals(insured.getUseInsured(), Integer.valueOf(WxMaConstants.OrderAddInsured.USE_INSURED));
    assertEquals(insured.getInsuredValue(), Integer.valueOf(5000));

    String json = WxMaGsonBuilder.create().toJson(insured);
    assertTrue(json.contains("\"use_insured\":1"));
    assertTrue(json.contains("\"insured_value\":5000"));
  }

  @Test
  public void testBuilderDefaultsWhenNoFieldSet() {
    WxMaExpressOrderInsured insured = WxMaExpressOrderInsured.builder().build();

    assertEquals(insured.getUseInsured(), Integer.valueOf(WxMaConstants.OrderAddInsured.INSURED_PROGRAM));
    assertEquals(insured.getInsuredValue(), Integer.valueOf(WxMaConstants.OrderAddInsured.DEFAULT_INSURED_VALUE));

    String json = WxMaGsonBuilder.create().toJson(insured);
    assertTrue(json.contains("\"use_insured\":0"));
    assertTrue(json.contains("\"insured_value\":0"));
  }
}
