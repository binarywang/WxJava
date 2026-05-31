package me.chanjar.weixin.channel.bean.supplier;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

import java.util.Arrays;
import me.chanjar.weixin.channel.util.JsonUtils;
import org.testng.annotations.Test;

/**
 * @author <a href="https://github.com/github-copilot">GitHub Copilot</a>
 */
public class WxChannelSupplierBeanTest {

  @Test
  public void testEncodeProductDistributeRequest() {
    ProductDistributeRequest request = new ProductDistributeRequest();
    request.setSupplierId("1001");
    request.setProductIdList(Arrays.asList("p1", "p2"));

    String json = JsonUtils.encode(request);
    assertNotNull(json);
    assertFalse(json.contains("supplierId"));
    assertFalse(json.contains("productIdList"));
    assertEquals(json, "{\"supplier_id\":\"1001\",\"product_id_list\":[\"p1\",\"p2\"]}");
  }

  @Test
  public void testEncodeDropshipAssignRequest() {
    DropshipAssignRequest request = new DropshipAssignRequest();
    request.setOrderId("o1");
    request.setSupplierId("s1");

    String json = JsonUtils.encode(request);
    assertNotNull(json);
    assertEquals(json, "{\"order_id\":\"o1\",\"supplier_id\":\"s1\"}");
  }

  @Test
  public void testDecodeSupplierListResponse() {
    String json = "{\"errcode\":0,\"errmsg\":\"ok\",\"supplier_list\":[{\"supplier_id\":\"s1\",\"supplier_name\":\"供货商1\",\"status\":1}],\"has_more\":false}";
    SupplierListResponse response = JsonUtils.decode(json, SupplierListResponse.class);

    assertNotNull(response);
    assertEquals(response.getErrCode(), 0);
    assertEquals(response.getSupplierList().size(), 1);
    assertEquals(response.getSupplierList().get(0).getSupplierId(), "s1");
  }
}
