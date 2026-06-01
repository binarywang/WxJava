package me.chanjar.weixin.channel.bean.product;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.Collections;
import me.chanjar.weixin.channel.util.JsonUtils;
import org.testng.annotations.Test;

/**
 * 新增商品管理接口参数/响应模型测试.
 *
 * @author GitHub Copilot
 */
public class ProductApiBeanTest {

  @Test
  public void testEncodeProductSchemeParam() {
    ProductSchemeParam param = new ProductSchemeParam();
    param.setProductId("12345");
    param.setFromAppid("wx123");
    param.setExpire(300);
    param.setExtInfo("ext");

    String json = JsonUtils.encode(param);
    assertNotNull(json);
    assertEquals(json.contains("\"product_id\":\"12345\""), true);
    assertEquals(json.contains("\"from_appid\":\"wx123\""), true);
    assertEquals(json.contains("\"expire\":300"), true);
  }

  @Test
  public void testDecodeProductAuditQuotaResponse() {
    String json = "{\"errcode\":0,\"errmsg\":\"ok\",\"audit_quota\":{\"avail_quota\":5,\"total_quota\":10}}";
    ProductAuditQuotaResponse response = JsonUtils.decode(json, ProductAuditQuotaResponse.class);
    assertNotNull(response);
    assertNotNull(response.getAuditQuota());
    assertEquals(response.getAuditQuota().getAvailQuota().intValue(), 5);
    assertEquals(response.getAuditQuota().getTotalQuota().intValue(), 10);
  }

  @Test
  public void testEncodeExternalProductMappingNewParam() {
    ExternalProductMappingNewParam.ExternalAttribute attr = new ExternalProductMappingNewParam.ExternalAttribute();
    attr.setKey("材质");
    attr.setValue("棉");

    ExternalProductMappingNewParam param = new ExternalProductMappingNewParam();
    param.setCatId(6261L);
    param.setHeadImgs(Collections.singletonList("https://img.example.com/a.png"));
    param.setTitle("测试标题");
    param.setExternalAttributes(Collections.singletonList(attr));

    String json = JsonUtils.encode(param);
    assertNotNull(json);
    assertEquals(json.contains("\"cat_id\":6261"), true);
    assertEquals(json.contains("\"external_attributes\""), true);
  }

  @Test
  public void testDecodeProductStockFlowResponse() {
    String json = "{\"errcode\":0,\"errmsg\":\"ok\",\"data\":{\"next_key\":\"nk\",\"stock_flow_info_list\":[{\"op_time\":1}]}}";
    ProductStockFlowResponse response = JsonUtils.decode(json, ProductStockFlowResponse.class);
    assertNotNull(response);
    assertNotNull(response.getData());
    assertEquals(response.getData().getNextKey(), "nk");
    assertEquals(response.getData().getStockFlowInfoList().size(), 1);
  }
}
