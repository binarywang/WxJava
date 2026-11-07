package me.chanjar.weixin.channel.api.impl;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import me.chanjar.weixin.channel.api.WxChannelService;
import me.chanjar.weixin.channel.bean.limit.LimitSku;
import me.chanjar.weixin.channel.bean.limit.LimitTaskParam;
import me.chanjar.weixin.channel.bean.product.GiftProductInfo;
import org.testng.annotations.Test;

/**
 * Verifies domain service accessors and product service compatibility delegates.
 */
public class WxChannelServiceImplTest {

  @Test
  public void shouldKeepNewDomainServiceAccessorsCompatibleWithExistingImplementations() throws Exception {
    assertDefaultMethod("getGiftService");
    assertDefaultMethod("getLimitedDiscountService");
    assertDefaultMethod("getProductStockService");
    assertDefaultMethod("getProductAssistantService");
  }

  @Test
  public void shouldExposeProductDomainServices() {
    CapturingChannelService channelService = new CapturingChannelService();
    assertNotNull(channelService.getGiftService());
    assertNotNull(channelService.getLimitedDiscountService());
    assertNotNull(channelService.getProductStockService());
    assertNotNull(channelService.getProductAssistantService());
  }

  @Test
  public void shouldRouteGiftProductCallsExactlyOnce() throws Exception {
    CapturingChannelService channelService = new CapturingChannelService();
    GiftProductInfo info = new GiftProductInfo();
    info.setListing(1);

    assertRequest(channelService, new RequestCall() {
      @Override
      public void call() throws Exception {
        channelService.getGiftService().addGiftProduct(info);
      }
    }, "https://api.weixin.qq.com/channels/ec/product/gift/add", "{\"listing\":1}");
    assertRequest(channelService, new RequestCall() {
      @Override
      public void call() throws Exception {
        channelService.getProductService().addGiftProduct(info);
      }
    }, "https://api.weixin.qq.com/channels/ec/product/gift/add", "{\"listing\":1}");
  }

  @Test
  public void shouldRouteLimitedDiscountCallsExactlyOnce() throws Exception {
    CapturingChannelService channelService = new CapturingChannelService();
    LimitTaskParam param = new LimitTaskParam();
    param.setProductId("product-id");
    param.setStartTime(new Date(1_000));
    param.setEndTime(new Date(2_000));
    param.setSkus(Arrays.asList(new LimitSku("sku-id", 100, 2)));

    assertRequest(channelService, new RequestCall() {
      @Override
      public void call() throws Exception {
        channelService.getLimitedDiscountService().addLimitTask(param);
      }
    }, "https://api.weixin.qq.com/channels/ec/product/limiteddiscounttask/add",
      "{\"product_id\":\"product-id\",\"start_time\":1000,\"end_time\":2000," +
        "\"limited_discount_skus\":[{\"sku_id\":\"sku-id\",\"sale_price\":100,\"sale_stock\":2}]}");
    assertRequest(channelService, new RequestCall() {
      @Override
      public void call() throws Exception {
        channelService.getProductService().addLimitTask(param);
      }
    }, "https://api.weixin.qq.com/channels/ec/product/limiteddiscounttask/add",
      "{\"product_id\":\"product-id\",\"start_time\":1000,\"end_time\":2000," +
        "\"limited_discount_skus\":[{\"sku_id\":\"sku-id\",\"sale_price\":100,\"sale_stock\":2}]}");
  }

  @Test
  public void shouldRouteStockCallsExactlyOnce() throws Exception {
    CapturingChannelService channelService = new CapturingChannelService();

    assertRequest(channelService, new RequestCall() {
      @Override
      public void call() throws Exception {
        channelService.getProductStockService().updateStock("product-id", "sku-id", 1, 2);
      }
    }, "https://api.weixin.qq.com/channels/ec/product/stock/update",
      "{\"product_id\":\"product-id\",\"sku_id\":\"sku-id\",\"diff_type\":1,\"num\":2}");
    assertRequest(channelService, new RequestCall() {
      @Override
      public void call() throws Exception {
        channelService.getProductService().updateStock("product-id", "sku-id", 1, 2);
      }
    }, "https://api.weixin.qq.com/channels/ec/product/stock/update",
      "{\"product_id\":\"product-id\",\"sku_id\":\"sku-id\",\"diff_type\":1,\"num\":2}");
  }

  private void assertDefaultMethod(String methodName) throws Exception {
    Method method = WxChannelService.class.getMethod(methodName);
    assertTrue(method.isDefault(), methodName + " must remain compatible with existing implementations");
  }

  private void assertRequest(CapturingChannelService channelService, RequestCall call,
                             String expectedUrl, String expectedJson) throws Exception {
    channelService.clearRequests();
    call.call();
    List<Request> requests = channelService.getRequests();
    assertEquals(requests.size(), 1);
    assertEquals(requests.get(0).url, expectedUrl);
    assertEquals(requests.get(0).json, expectedJson);
  }

  private interface RequestCall {
    void call() throws Exception;
  }

  private static class CapturingChannelService extends WxChannelServiceImpl {
    private final List<Request> requests = new ArrayList<>();

    @Override
    public String post(String url, String postData) {
      this.requests.add(new Request(url, postData));
      return "{\"errcode\":0}";
    }

    private List<Request> getRequests() {
      return new ArrayList<>(this.requests);
    }

    private void clearRequests() {
      this.requests.clear();
    }
  }

  private static class Request {
    private final String url;
    private final String json;

    private Request(String url, String json) {
      this.url = url;
      this.json = json;
    }

  }
}
