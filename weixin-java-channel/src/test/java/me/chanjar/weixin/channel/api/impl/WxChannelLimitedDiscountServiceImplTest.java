package me.chanjar.weixin.channel.api.impl;

import static org.testng.Assert.assertEquals;

import java.util.Arrays;
import me.chanjar.weixin.channel.bean.limit.LimitSkuUpdate;
import me.chanjar.weixin.channel.bean.limit.LimitTaskUpdateParam;
import me.chanjar.weixin.channel.bean.limit.LimitTaskUpdateResponse;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Test;

/**
 * Tests for {@link WxChannelLimitedDiscountServiceImpl}.
 */
public class WxChannelLimitedDiscountServiceImplTest {

  @Test
  public void shouldUpdateLimitedDiscountTaskAndDecodeResponse() throws WxErrorException {
    CapturingChannelService channelService = new CapturingChannelService();
    channelService.response = "{\"errcode\":0,\"errmsg\":\"ok\","
      + "\"task_id\":\"task-id\",\"title\":\"updated title\"}";
    LimitSkuUpdate sku = new LimitSkuUpdate();
    sku.setProductId("product-id");
    sku.setSkuId("sku-id");
    sku.setSalePrice(2888);
    sku.setSaleStock(5);
    LimitTaskUpdateParam param = new LimitTaskUpdateParam();
    param.setTaskId("task-id");
    param.setStatus(0);
    param.setStartTime(1_700_000_000L);
    param.setEndTime(1_700_003_600L);
    param.setTitle("updated title");
    param.setSkus(Arrays.asList(sku));

    LimitTaskUpdateResponse response = channelService.getLimitedDiscountService().updateLimitTask(param);

    assertEquals(channelService.url,
      "https://api.weixin.qq.com/channels/ec/product/limiteddiscounttask/update");
    assertEquals(channelService.request,
      "{\"task_id\":\"task-id\",\"status\":0,\"start_time\":1700000000,"
        + "\"end_time\":1700003600,\"title\":\"updated title\","
        + "\"limited_discount_skus\":[{\"product_id\":\"product-id\","
        + "\"sku_id\":\"sku-id\",\"sale_price\":2888,\"sale_stock\":5}]}");
    assertEquals(response.getErrCode(), 0);
    assertEquals(response.getErrMsg(), "ok");
    assertEquals(response.getTaskId(), "task-id");
    assertEquals(response.getTitle(), "updated title");
  }

  private static class CapturingChannelService extends WxChannelServiceImpl {
    private String url;
    private String request;
    private String response;

    @Override
    public String post(String url, String postData) {
      this.url = url;
      this.request = postData;
      return this.response;
    }
  }
}
