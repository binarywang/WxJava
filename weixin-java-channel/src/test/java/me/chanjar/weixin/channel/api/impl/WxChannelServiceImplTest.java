package me.chanjar.weixin.channel.api.impl;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import me.chanjar.weixin.channel.api.WxChannelService;
import me.chanjar.weixin.channel.bean.limit.LimitTaskParam;
import me.chanjar.weixin.channel.bean.product.GiftProductInfo;
import org.testng.annotations.Test;

/**
 * Verifies domain service accessors and product service compatibility delegates.
 */
public class WxChannelServiceImplTest {

  @Test
  public void shouldExposeProductDomainServicesAndKeepProductServiceRoutesCompatible() throws Exception {
    CapturingChannelService channelService = new CapturingChannelService();
    assertNotNull(channelService.getGiftService());
    assertNotNull(channelService.getLimitedDiscountService());
    assertNotNull(channelService.getProductStockService());
    assertNotNull(channelService.getProductAssistantService());

    assertSameRequest(channelService, new RequestCall() {
      @Override
      public void call() throws Exception {
        channelService.getGiftService().addGiftProduct(new GiftProductInfo());
      }
    }, new RequestCall() {
      @Override
      public void call() throws Exception {
        channelService.getProductService().addGiftProduct(new GiftProductInfo());
      }
    });
    assertSameRequest(channelService, new RequestCall() {
      @Override
      public void call() throws Exception {
        channelService.getLimitedDiscountService().addLimitTask(new LimitTaskParam());
      }
    }, new RequestCall() {
      @Override
      public void call() throws Exception {
        channelService.getProductService().addLimitTask(new LimitTaskParam());
      }
    });
    assertSameRequest(channelService, new RequestCall() {
      @Override
      public void call() throws Exception {
        channelService.getProductStockService().updateStock("product-id", "sku-id", 1, 2);
      }
    }, new RequestCall() {
      @Override
      public void call() throws Exception {
        channelService.getProductService().updateStock("product-id", "sku-id", 1, 2);
      }
    });
  }

  private void assertSameRequest(CapturingChannelService channelService, RequestCall domainCall,
                                 RequestCall compatibilityCall) throws Exception {
    channelService.clearRequests();
    domainCall.call();
    List<Request> domainRequests = channelService.getRequests();
    channelService.clearRequests();
    compatibilityCall.call();
    assertEquals(channelService.getRequests(), domainRequests);
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

    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Request)) {
        return false;
      }
      Request request = (Request) other;
      return this.url.equals(request.url) && this.json.equals(request.json);
    }

    @Override
    public int hashCode() {
      return 31 * this.url.hashCode() + this.json.hashCode();
    }
  }
}
