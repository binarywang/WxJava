package me.chanjar.weixin.channel.api.impl;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.product.AddProductThirdPartySourceParam;
import me.chanjar.weixin.channel.bean.product.AddProductThirdPartySourceResponse;
import me.chanjar.weixin.channel.bean.product.ExternalProductMappingNewParam;
import me.chanjar.weixin.channel.bean.product.ExternalProductMappingNewResponse;
import me.chanjar.weixin.channel.bean.product.ExternalProductMappingParam;
import me.chanjar.weixin.channel.bean.product.ExternalProductMappingResponse;
import me.chanjar.weixin.channel.bean.product.ProductAuditQuotaResponse;
import me.chanjar.weixin.channel.bean.product.ProductAuditStrategyInfo;
import me.chanjar.weixin.channel.bean.product.ProductAuditStrategyResponse;
import me.chanjar.weixin.channel.bean.product.ProductAuditStrategySetParam;
import me.chanjar.weixin.channel.bean.product.ProductBrandRecommendParam;
import me.chanjar.weixin.channel.bean.product.ProductBrandRecommendResponse;
import me.chanjar.weixin.channel.bean.product.ProductCategoryClassifyParam;
import me.chanjar.weixin.channel.bean.product.ProductCategoryClassifyResponse;
import me.chanjar.weixin.channel.bean.product.ProductCategoryPreCheckParam;
import me.chanjar.weixin.channel.bean.product.ProductCategoryPreCheckResponse;
import me.chanjar.weixin.channel.bean.product.ProductSchemeParam;
import me.chanjar.weixin.channel.bean.product.ProductSchemeResponse;
import me.chanjar.weixin.channel.bean.product.ProductStockFlowParam;
import me.chanjar.weixin.channel.bean.product.ProductStockFlowResponse;
import me.chanjar.weixin.channel.bean.product.ProductTimingSaleParam;
import me.chanjar.weixin.channel.util.JsonUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Test;

/** Tests the product-management endpoint contracts without making network calls. */
public class WxChannelProductManagementServiceImplTest {

  @Test
  public void shouldGetProductScheme() throws WxErrorException {
    RecordingChannelService channelService = response("{\"errcode\":0,\"errmsg\":\"ok\",\"openlink\":\"weixin://dl/business/?t=abc\"}");
    ProductSchemeParam param = new ProductSchemeParam();
    param.setProductId("10001");
    param.setFromAppid("wx-app");
    param.setExpire(3600);
    param.setExtInfo("source");

    ProductSchemeResponse result = productService(channelService).getProductScheme(param);

    assertRequest(channelService, "/channels/ec/product/scheme/get", "{\"product_id\":\"10001\",\"from_appid\":\"wx-app\",\"expire\":3600,\"ext_info\":\"source\"}");
    assertEquals(result.getOpenlink(), "weixin://dl/business/?t=abc");
  }

  @Test
  public void shouldClassifyProductCategoryAndDecodePermission() throws WxErrorException {
    RecordingChannelService channelService = response("{\"errcode\":0,\"errmsg\":\"ok\",\"categories\":[{\"cats\":[{\"cat_info\":{\"cat_id\":\"6000\",\"cat_name\":\"童鞋\"},\"has_permission\":true}]}],\"wrong_cat\":false}");
    ProductCategoryClassifyParam param = new ProductCategoryClassifyParam();
    param.setReqType(2);
    param.setTitle("儿童雨鞋");
    param.setHeadImgs(Arrays.asList("https://example.com/image"));
    param.setCatId("6000");

    ProductCategoryClassifyResponse result = productService(channelService).classifyProductCategory(param);

    assertRequest(channelService, "/channels/ec/product/category/classify", "{\"req_type\":2,\"title\":\"儿童雨鞋\",\"head_imgs\":[\"https://example.com/image\"],\"cat_id\":\"6000\"}");
    assertEquals(result.getCategories().get(0).getCats().get(0).getCatInfo().getCatId(), "6000");
    assertTrue(result.getCategories().get(0).getCats().get(0).getHasPermission());
  }

  @Test
  public void shouldBeginTimingSale() throws WxErrorException {
    RecordingChannelService channelService = response(okResponse());
    ProductTimingSaleParam param = new ProductTimingSaleParam();
    param.setProductId("10001");
    param.setTaskId(123L);

    WxChannelBaseResponse result = productService(channelService).beginTimingSale(param);

    assertRequest(channelService, "/channels/ec/product/begintimingsale", "{\"product_id\":\"10001\",\"task_id\":123}");
    assertTrue(result.isSuccess());
  }

  @Test
  public void shouldCancelTimingSale() throws WxErrorException {
    RecordingChannelService channelService = response(okResponse());

    WxChannelBaseResponse result = productService(channelService).cancelTimingSale("10001");

    assertRequest(channelService, "/channels/ec/product/canceltimingsale", "{\"product_id\":\"10001\"}");
    assertTrue(result.isSuccess());
  }

  @Test
  public void shouldMapExternalProductAttribute() throws WxErrorException {
    RecordingChannelService channelService = response("{\"errcode\":0,\"errmsg\":\"ok\",\"external_attribute_name\":\"材质\",\"external_attribute_value\":\"塑胶\",\"internal_attribute_name\":\"鞋面材质\",\"internal_attribute_value\":[\"塑胶\"]}");
    ExternalProductMappingParam param = new ExternalProductMappingParam();
    param.setCatId(6000L);
    param.setExternalAttributeName("材质");
    param.setExternalAttributeValue("塑胶");
    param.setExternalCategoryName("母婴:童鞋");

    ExternalProductMappingResponse result = productService(channelService).externalProductMapping(param);

    assertRequest(channelService, "/channels/ec/product/externalproductmapping", "{\"cat_id\":6000,\"external_attribute_name\":\"材质\",\"external_attribute_value\":\"塑胶\",\"external_category_name\":\"母婴:童鞋\"}");
    assertEquals(result.getInternalAttributeValue(), Arrays.asList("塑胶"));
  }

  @Test
  public void shouldPreCheckCategory() throws WxErrorException {
    RecordingChannelService channelService = response("{\"errcode\":0,\"errmsg\":\"ok\",\"all_pass\":false,\"fail_reasons\":[\"缺少资质\"]}");
    ProductCategoryPreCheckParam param = new ProductCategoryPreCheckParam();
    param.setCatId(6000L);

    ProductCategoryPreCheckResponse result = productService(channelService).categoryPreCheck(param);

    assertRequest(channelService, "/channels/ec/product/categoryprecheck", "{\"cat_id\":6000}");
    assertEquals(result.getFailReasons(), Arrays.asList("缺少资质"));
  }

  @Test
  public void shouldGetProductAuditStrategy() throws WxErrorException {
    RecordingChannelService channelService = response("{\"errcode\":0,\"errmsg\":\"ok\",\"audit_strategy\":{\"hide_err_field_flag\":1,\"hit_duplicated_flag\":0,\"hit_low_risk_rule_flag\":1}}");

    ProductAuditStrategyResponse result = productService(channelService).getProductAuditStrategy();

    assertRequest(channelService, "/channels/ec/product/auditstrategy/get", "{}");
    assertEquals(result.getAuditStrategy().getHideErrFieldFlag(), Integer.valueOf(1));
  }

  @Test
  public void shouldSetProductAuditStrategy() throws WxErrorException {
    RecordingChannelService channelService = response(okResponse());
    ProductAuditStrategyInfo strategy = new ProductAuditStrategyInfo();
    strategy.setHideErrFieldFlag(1);
    ProductAuditStrategySetParam param = new ProductAuditStrategySetParam();
    param.setAuditStrategy(strategy);

    WxChannelBaseResponse result = productService(channelService).setProductAuditStrategy(param);

    assertRequest(channelService, "/channels/ec/product/auditstrategy/set", "{\"audit_strategy\":{\"hide_err_field_flag\":1}}");
    assertTrue(result.isSuccess());
  }

  @Test
  public void shouldGetProductAuditQuota() throws WxErrorException {
    RecordingChannelService channelService = response("{\"errcode\":0,\"errmsg\":\"ok\",\"audit_quota\":{\"block_status\":0,\"avail_quota\":20,\"total_quota\":100,\"unlimited_type\":0,\"audit_total_quota\":100,\"audit_total_remaining\":80,\"new_product_total_quota\":50,\"new_product_remaining\":40}}");

    ProductAuditQuotaResponse result = productService(channelService).getProductAuditQuota();

    assertRequest(channelService, "/channels/ec/product/getauditquota", "{}");
    assertEquals(result.getAuditQuota().getNewProductRemaining(), Integer.valueOf(40));
  }

  @Test
  public void shouldMapAndRecommendExternalProductAttributes() throws WxErrorException {
    RecordingChannelService channelService = response("{\"errcode\":0,\"errmsg\":\"ok\",\"attributes\":[{\"key\":\"鞋面材质\",\"value\":\"塑胶\"}]}");
    ExternalProductMappingNewParam.ExternalAttribute attribute = new ExternalProductMappingNewParam.ExternalAttribute();
    attribute.setKey("材质");
    attribute.setValue("塑胶");
    ExternalProductMappingNewParam param = new ExternalProductMappingNewParam();
    param.setCatId(6000L);
    param.setExternalCategoryName("母婴:童鞋");
    param.setHeadImgs(Arrays.asList("https://example.com/head"));
    param.setTitle("儿童雨鞋");
    param.setExternalAttributes(Arrays.asList(attribute));

    ExternalProductMappingNewResponse result = productService(channelService).externalProductMappingNew(param);

    assertRequest(channelService, "/channels/ec/product/externalproductmappingnew", "{\"cat_id\":6000,\"external_category_name\":\"母婴:童鞋\",\"head_imgs\":[\"https://example.com/head\"],\"title\":\"儿童雨鞋\",\"external_attributes\":[{\"key\":\"材质\",\"value\":\"塑胶\"}]}");
    assertEquals(result.getAttributes().get(0).getKey(), "鞋面材质");
  }

  @Test
  public void shouldRecommendProductBrand() throws WxErrorException {
    RecordingChannelService channelService = response("{\"errcode\":0,\"errmsg\":\"ok\",\"brand_id\":123,\"brand_name_chinese\":\"品牌\",\"brand_name_english\":\"Brand\"}");
    ProductBrandRecommendParam param = new ProductBrandRecommendParam();
    param.setCatId(6000L);
    param.setHeadImgs(Arrays.asList("https://example.com/head"));
    param.setTitle("商品");

    ProductBrandRecommendResponse result = productService(channelService).productBrandRecommend(param);

    assertRequest(channelService, "/channels/ec/product/productbrandrecommend", "{\"cat_id\":6000,\"head_imgs\":[\"https://example.com/head\"],\"title\":\"商品\"}");
    assertEquals(result.getBrandId(), Long.valueOf(123));
  }

  @Test
  public void shouldAddThirdPartyProductSource() throws WxErrorException {
    RecordingChannelService channelService = response("{\"errcode\":0,\"errmsg\":\"ok\",\"third_party_source_id\":12345}");
    AddProductThirdPartySourceParam param = new AddProductThirdPartySourceParam();
    param.setSceneValue(1);
    param.setPublishMethod(2);

    AddProductThirdPartySourceResponse result = productService(channelService).addProductThirdPartySource(param);

    assertRequest(channelService, "/channels/ec/product/addproductthirdpartysource", "{\"scene_value\":1,\"publish_method\":2}");
    assertEquals(result.getThirdPartySourceId(), Long.valueOf(12345));
  }

  @Test
  public void shouldGetStockFlowWithRequiredPaginationAndStockTypeId() throws WxErrorException {
    RecordingChannelService channelService = response("{\"errcode\":0,\"errmsg\":\"ok\",\"data\":{\"stock_flow_info_list\":[{\"amount\":2}],\"next_key\":\"next\"}}");
    ProductStockFlowParam param = new ProductStockFlowParam();
    param.setProductId("10001");
    param.setSkuId("10002");
    param.setStockType(2);
    param.setStockTypeId("activity-1");
    param.setBeginTime(100L);
    param.setEndTime(200L);
    param.setPageSize(10);

    ProductStockFlowResponse result = productService(channelService).getStockFlow(param);

    assertRequest(channelService, "/channels/ec/product/stock/getflow", "{\"product_id\":\"10001\",\"sku_id\":\"10002\",\"stock_type\":2,\"begin_time\":100,\"end_time\":200,\"page_size\":10,\"stock_type_id\":\"activity-1\"}");
    assertEquals(result.getData().getStockFlowInfoList().size(), 1);
    assertEquals(result.getData().getNextKey(), "next");
  }

  private static WxChannelProductServiceImpl productService(RecordingChannelService channelService) {
    return new WxChannelProductServiceImpl(channelService);
  }

  private static RecordingChannelService response(String response) {
    return new RecordingChannelService(response);
  }

  private static String okResponse() {
    return "{\"errcode\":0,\"errmsg\":\"ok\"}";
  }

  private static void assertRequest(RecordingChannelService channelService, String path, String expectedJson) {
    assertTrue(channelService.getUrl().endsWith(path));
    assertEquals(JsonUtils.decode(channelService.getRequestJson(), Object.class), JsonUtils.decode(expectedJson, Object.class));
  }

  private static class RecordingChannelService extends WxChannelServiceHttpClientImpl {
    private final String response;
    private String url;
    private String requestJson;

    RecordingChannelService(String response) {
      this.response = response;
    }

    @Override
    public String post(String url, String postData) {
      this.url = url;
      this.requestJson = postData;
      return response;
    }

    String getUrl() {
      return url;
    }

    String getRequestJson() {
      return requestJson;
    }
  }
}
