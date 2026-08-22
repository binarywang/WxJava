package me.chanjar.weixin.channel.api.impl;

import static org.testng.Assert.assertEquals;

import java.util.Arrays;
import me.chanjar.weixin.channel.bean.product.stock.StockFlowInfo;
import me.chanjar.weixin.channel.bean.product.stock.StockFlowParam;
import me.chanjar.weixin.channel.bean.product.stock.StockFlowResponse;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Test;

/**
 * Tests for {@link WxChannelProductStockServiceImpl}.
 */
public class WxChannelProductStockServiceImplTest {

  @Test
  public void shouldGetStockFlowAndDecodeResponse() throws WxErrorException {
    CapturingChannelService channelService = new CapturingChannelService();
    channelService.response = "{\"errcode\":0,\"errmsg\":\"ok\",\"data\":{"
      + "\"stock_flow_info_list\":[{\"amount\":300,\"beginning_amount\":842,"
      + "\"ending_amount\":542,\"stock_sub_type\":1,\"op_type\":6,"
      + "\"update_time\":1689735682,\"ext_info\":{\"unmove_from_stock_sub_type\":3,"
      + "\"move_to_stock_sub_type\":4,"
      + "\"upload_source\":2,\"order_id\":\"order-id\","
      + "\"out_warehouse_id\":\"warehouse-id\","
      + "\"limited_discount_id\":\"discount-id\",\"finder_id\":\"finder-id\"}}],"
      + "\"next_key\":\"next-page\"}}";
    StockFlowParam param = new StockFlowParam();
    param.setProductId("product-id");
    param.setSkuId("sku-id");
    param.setStockType(1);
    param.setFinderId("finder-id");
    param.setBeginTime(1_689_218_360L);
    param.setEndTime(1_689_736_760L);
    param.setOpTypeList(Arrays.asList(1, 2));
    param.setPageSize(10);
    param.setNextKey("current-page");
    param.setStockTypeId("stock-type-id");

    StockFlowResponse response = channelService.getProductStockService().getStockFlow(param);

    assertEquals(channelService.url, "https://api.weixin.qq.com/channels/ec/product/stock/getflow");
    assertEquals(channelService.request,
      "{\"product_id\":\"product-id\",\"sku_id\":\"sku-id\",\"stock_type\":1,"
        + "\"finder_id\":\"finder-id\",\"begin_time\":1689218360,"
        + "\"end_time\":1689736760,\"op_type_list\":[1,2],\"page_size\":10,"
        + "\"next_key\":\"current-page\",\"stock_type_id\":\"stock-type-id\"}");
    assertEquals(response.getErrCode(), 0);
    assertEquals(response.getErrMsg(), "ok");
    assertEquals(response.getNextKey(), "next-page");
    assertEquals(response.getStockFlowInfoList().size(), 1);
    StockFlowInfo flow = response.getStockFlowInfoList().get(0);
    assertEquals(flow.getAmount(), Integer.valueOf(300));
    assertEquals(flow.getBeginningAmount(), Integer.valueOf(842));
    assertEquals(flow.getEndingAmount(), Integer.valueOf(542));
    assertEquals(flow.getStockSubType(), Integer.valueOf(1));
    assertEquals(flow.getOpType(), Integer.valueOf(6));
    assertEquals(flow.getUpdateTime(), Long.valueOf(1_689_735_682L));
    assertEquals(flow.getExtInfo().getUnmoveFromStockSubType(), Integer.valueOf(3));
    assertEquals(flow.getExtInfo().getMoveToStockSubType(), Integer.valueOf(4));
    assertEquals(flow.getExtInfo().getUploadSource(), Integer.valueOf(2));
    assertEquals(flow.getExtInfo().getOrderId(), "order-id");
    assertEquals(flow.getExtInfo().getOutWarehouseId(), "warehouse-id");
    assertEquals(flow.getExtInfo().getLimitedDiscountId(), "discount-id");
    assertEquals(flow.getExtInfo().getFinderId(), "finder-id");
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
