package me.chanjar.weixin.channel.api.impl;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import me.chanjar.weixin.channel.api.WxChannelProductService;
import me.chanjar.weixin.channel.api.WxChannelService;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.limit.LimitTaskAddResponse;
import me.chanjar.weixin.channel.bean.limit.LimitTaskListResponse;
import me.chanjar.weixin.channel.bean.limit.LimitTaskParam;
import me.chanjar.weixin.channel.bean.limit.LimitTaskUpdateParam;
import me.chanjar.weixin.channel.bean.limit.LimitTaskUpdateResponse;
import me.chanjar.weixin.channel.bean.product.CategoryPreCheckResponse;
import me.chanjar.weixin.channel.bean.product.ExternalProductMappingNewParam;
import me.chanjar.weixin.channel.bean.product.ExternalProductMappingNewResponse;
import me.chanjar.weixin.channel.bean.product.ExternalProductMappingParam;
import me.chanjar.weixin.channel.bean.product.ExternalProductMappingResponse;
import me.chanjar.weixin.channel.bean.product.ProductBrandRecommendParam;
import me.chanjar.weixin.channel.bean.product.ProductBrandRecommendResponse;
import me.chanjar.weixin.channel.bean.product.SkuStockBatchResponse;
import me.chanjar.weixin.channel.bean.product.StockFlowParam;
import me.chanjar.weixin.channel.bean.product.StockFlowResponse;
import me.chanjar.weixin.channel.bean.product.SkuStockResponse;
import me.chanjar.weixin.channel.bean.product.SpuGetResponse;
import me.chanjar.weixin.channel.bean.product.SpuListResponse;
import me.chanjar.weixin.channel.bean.product.SpuUpdateInfo;
import me.chanjar.weixin.channel.bean.product.SpuUpdateResponse;
import me.chanjar.weixin.channel.bean.product.link.ProductH5UrlResponse;
import me.chanjar.weixin.channel.bean.product.link.ProductQrCodeResponse;
import me.chanjar.weixin.channel.bean.product.link.ProductTagLinkResponse;
import me.chanjar.weixin.channel.test.ApiTestModule;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Guice(modules = ApiTestModule.class)
public class WxChannelProductServiceImplTest {

  @Inject
  private WxChannelService channelService;

  @Test
  public void testAddProduct() throws WxErrorException {
    WxChannelProductService productService = channelService.getProductService();
    SpuUpdateInfo spuInfo = new SpuUpdateInfo();
    // ...
    SpuUpdateResponse response = productService.addProduct(spuInfo);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testUpdateProduct() throws WxErrorException {
    WxChannelProductService productService = channelService.getProductService();
    SpuUpdateInfo spuInfo = new SpuUpdateInfo();
    // ...
    SpuUpdateResponse response = productService.updateProduct(spuInfo);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testUpdateStock() throws WxErrorException {
    WxChannelProductService productService = channelService.getProductService();
    String productId = "";
    String skuId = "";
    Integer diffType = 1;
    Integer num = 10;
    WxChannelBaseResponse response = productService.updateStock(productId, skuId, diffType, num);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testDeleteProduct() throws WxErrorException {
    WxChannelProductService productService = channelService.getProductService();
    String productId = "";
    WxChannelBaseResponse response = productService.deleteProduct(productId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testCancelProductAudit() throws WxErrorException {
    WxChannelProductService productService = channelService.getProductService();
    String productId = "";
    WxChannelBaseResponse response = productService.cancelProductAudit(productId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetProduct() throws WxErrorException {
    WxChannelProductService productService = channelService.getProductService();
    String productId = "10000029995861";
    Integer dataType = 3;
    SpuGetResponse response = productService.getProduct(productId, dataType);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testListProduct() throws WxErrorException {
    WxChannelProductService productService = channelService.getProductService();
    Integer pageSize = 10;
    String nextKey = null;
    Integer status = null;
    SpuListResponse response = productService.listProduct(pageSize, nextKey, status);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testUpProduct() throws WxErrorException {
    WxChannelProductService productService = channelService.getProductService();
    String productId = "";
    WxChannelBaseResponse response = productService.upProduct(productId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testDownProduct() throws WxErrorException {
    WxChannelProductService productService = channelService.getProductService();
    String productId = "";
    WxChannelBaseResponse response = productService.downProduct(productId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetSkuStock() throws WxErrorException {
    WxChannelProductService productService = channelService.getProductService();
    String productId = "10000076089602";
    String skuId = "1918289111";
    SkuStockResponse response = productService.getSkuStock(productId, skuId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetSkuStockBatch() throws WxErrorException {
    WxChannelProductService productService = channelService.getProductService();
    List<String> productIds = new ArrayList<>();
    productIds.add("123");
    SkuStockBatchResponse response = productService.getSkuStockBatch(productIds);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetProductH5Url() throws WxErrorException {
    WxChannelProductService productService = channelService.getProductService();
    String productId = "";
    ProductH5UrlResponse response = productService.getProductH5Url(productId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
    System.out.println(response.getProductH5url());
  }

  @Test
  public void testGetProductQrCode() throws WxErrorException {
    WxChannelProductService productService = channelService.getProductService();
    String productId = "";
    ProductQrCodeResponse response = productService.getProductQrCode(productId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
    System.out.println(response.getProductQrcode());
  }

  @Test
  public void testGetProductTagLink() throws WxErrorException {
    WxChannelProductService productService = channelService.getProductService();
    String productId = "";
    ProductTagLinkResponse response = productService.getProductTagLink(productId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
    System.out.println(response.getProductTaglink());
  }

  @Test
  public void testAddLimitTask() throws WxErrorException {
    WxChannelProductService productService = channelService.getProductService();
    LimitTaskParam param = new LimitTaskParam();
    // ...
    LimitTaskAddResponse response = productService.addLimitTask(param);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testListLimitTask() throws WxErrorException {
    WxChannelProductService productService = channelService.getProductService();
    Integer pageSize = 1;
    String nextKey = "";
    Integer status = null;
    LimitTaskListResponse response = productService.listLimitTask(pageSize, nextKey, status);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testStopLimitTask() throws WxErrorException {
    WxChannelProductService productService = channelService.getProductService();
    String taskId = "";
    WxChannelBaseResponse response = productService.stopLimitTask(taskId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testDeleteLimitTask() throws WxErrorException {
    WxChannelProductService productService = channelService.getProductService();
    String taskId = "";
    WxChannelBaseResponse response = productService.deleteLimitTask(taskId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testUpdateLimitTask() throws WxErrorException {
    WxChannelProductService productService = channelService.getProductService();
    LimitTaskUpdateParam param = new LimitTaskUpdateParam();
    param.setTaskId("");
    LimitTaskUpdateResponse response = productService.updateLimitTask(param);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetStockFlow() throws WxErrorException {
    WxChannelProductService productService = channelService.getProductService();
    StockFlowParam param = new StockFlowParam();
    param.setProductId(0L);
    param.setSkuId(0L);
    param.setPageSize(10);
    StockFlowResponse response = productService.getStockFlow(param);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testCategoryPreCheck() throws WxErrorException {
    WxChannelProductService productService = channelService.getProductService();
    Long catId = 0L;
    CategoryPreCheckResponse response = productService.categoryPreCheck(catId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetProductBrandRecommend() throws WxErrorException {
    WxChannelProductService productService = channelService.getProductService();
    ProductBrandRecommendParam param = new ProductBrandRecommendParam();
    param.setCatId(0L);
    param.setTitle("test");
    ProductBrandRecommendResponse response = productService.getProductBrandRecommend(param);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testExternalProductMapping() throws WxErrorException {
    WxChannelProductService productService = channelService.getProductService();
    ExternalProductMappingParam param = new ExternalProductMappingParam();
    param.setCatId(0L);
    param.setExternalAttributeName("test");
    ExternalProductMappingResponse response = productService.externalProductMapping(param);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testExternalProductMappingNew() throws WxErrorException {
    WxChannelProductService productService = channelService.getProductService();
    ExternalProductMappingNewParam param = new ExternalProductMappingNewParam();
    param.setCatId(0L);
    ExternalProductMappingNewResponse response = productService.externalProductMappingNew(param);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testBeginTimingSale() throws WxErrorException {
    WxChannelProductService productService = channelService.getProductService();
    String productId = "";
    String taskId = "";
    WxChannelBaseResponse response = productService.beginTimingSale(productId, taskId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testCancelTimingSale() throws WxErrorException {
    WxChannelProductService productService = channelService.getProductService();
    String productId = "";
    WxChannelBaseResponse response = productService.cancelTimingSale(productId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }
}
