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
    ProductDistributeRequest decoded = JsonUtils.decode(json, ProductDistributeRequest.class);
    assertEquals(decoded.getSupplierId(), "1001");
    assertEquals(decoded.getProductIdList(), Arrays.asList("p1", "p2"));
  }

  @Test
  public void testEncodeDropshipAssignRequest() {
    DropshipAssignRequest request = new DropshipAssignRequest();
    request.setOrderId("o1");
    request.setSupplierId("s1");

    String json = JsonUtils.encode(request);
    assertNotNull(json);
    DropshipAssignRequest decoded = JsonUtils.decode(json, DropshipAssignRequest.class);
    assertEquals(decoded.getOrderId(), "o1");
    assertEquals(decoded.getSupplierId(), "s1");
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

  @Test
  public void testDecodeDropshipInfo() {
    DropshipInfo info = JsonUtils.decode("{\"ds_order_id\":\"d1\"}", DropshipInfo.class);

    assertNotNull(info);
    assertEquals(info.getDropshipId(), "d1");
  }
}
