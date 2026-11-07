package me.chanjar.weixin.channel.api.impl;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.product.assistant.BeginTimingSaleParam;
import me.chanjar.weixin.channel.bean.product.assistant.CancelTimingSaleParam;
import me.chanjar.weixin.channel.bean.product.assistant.CategoryPreCheckParam;
import me.chanjar.weixin.channel.bean.product.assistant.CategoryPreCheckResponse;
import me.chanjar.weixin.channel.bean.product.assistant.ExternalAttribute;
import me.chanjar.weixin.channel.bean.product.assistant.ExternalProductMappingNewParam;
import me.chanjar.weixin.channel.bean.product.assistant.ExternalProductMappingNewResponse;
import me.chanjar.weixin.channel.bean.product.assistant.ExternalProductMappingParam;
import me.chanjar.weixin.channel.bean.product.assistant.ExternalProductMappingResponse;
import me.chanjar.weixin.channel.bean.product.assistant.ProductBrandRecommendParam;
import me.chanjar.weixin.channel.bean.product.assistant.ProductBrandRecommendResponse;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Test;

/**
 * Tests for {@link WxChannelProductAssistantServiceImpl}.
 */
public class WxChannelProductAssistantServiceImplTest {

  @Test
  public void shouldPreCheckCategoryAndDecodeResponse() throws WxErrorException {
    CapturingChannelService channelService = new CapturingChannelService();
    channelService.response = "{\"errcode\":0,\"errmsg\":\"ok\","
      + "\"all_pass\":false,\"fail_reasons\":[\"保证金不足\"]}";
    CategoryPreCheckParam param = new CategoryPreCheckParam();
    param.setCatId(6261L);

    CategoryPreCheckResponse response = channelService.getProductAssistantService().categoryPreCheck(param);

    assertEquals(channelService.url, "https://api.weixin.qq.com/channels/ec/product/categoryprecheck");
    assertEquals(channelService.request, "{\"cat_id\":6261}");
    assertEquals(response.getErrCode(), 0);
    assertEquals(response.getErrMsg(), "ok");
    assertEquals(response.getAllPass(), Boolean.FALSE);
    assertEquals(response.getFailReasons(), Arrays.asList("保证金不足"));
  }

  @Test
  public void shouldRecommendProductBrandAndDecodeResponse() throws WxErrorException {
    CapturingChannelService channelService = new CapturingChannelService();
    channelService.response = "{\"errcode\":0,\"errmsg\":\"ok\",\"brand_id\":2100000000,"
      + "\"brand_name_chinese\":\"品牌\",\"brand_name_english\":\"brand\"}";
    ProductBrandRecommendParam param = new ProductBrandRecommendParam();
    param.setCatId(6000L);
    param.setHeadImgs(Arrays.asList("https://example.com/head.jpg"));
    param.setDetailImgs(Arrays.asList("https://example.com/detail.jpg"));
    param.setTitle("测试商品");

    ProductBrandRecommendResponse response =
      channelService.getProductAssistantService().getProductBrandRecommend(param);

    assertEquals(channelService.url, "https://api.weixin.qq.com/channels/ec/product/productbrandrecommend");
    assertEquals(channelService.request,
      "{\"cat_id\":6000,\"head_imgs\":[\"https://example.com/head.jpg\"],"
        + "\"detail_imgs\":[\"https://example.com/detail.jpg\"],\"title\":\"测试商品\"}");
    assertEquals(response.getErrCode(), 0);
    assertEquals(response.getErrMsg(), "ok");
    assertEquals(response.getBrandId(), Long.valueOf(2_100_000_000L));
    assertEquals(response.getBrandNameChinese(), "品牌");
    assertEquals(response.getBrandNameEnglish(), "brand");
  }

  @Test
  public void shouldMapExternalProductAttributeAndDecodeResponse() throws WxErrorException {
    CapturingChannelService channelService = new CapturingChannelService();
    channelService.response = "{\"errcode\":0,\"errmsg\":\"ok\","
      + "\"external_attribute_name\":\"帮面材质\",\"external_attribute_value\":\"塑胶\","
      + "\"internal_attribute_name\":\"鞋面材质\",\"internal_attribute_value\":[\"塑胶\"]}";
    ExternalProductMappingParam param = new ExternalProductMappingParam();
    param.setCatId(6261L);
    param.setExternalAttributeName("帮面材质");
    param.setExternalAttributeValue("塑胶");
    param.setExternalCategoryName("母婴:童鞋:雨鞋");

    ExternalProductMappingResponse response =
      channelService.getProductAssistantService().externalProductMapping(param);

    assertEquals(channelService.url, "https://api.weixin.qq.com/channels/ec/product/externalproductmapping");
    assertEquals(channelService.request,
      "{\"cat_id\":6261,\"external_attribute_name\":\"帮面材质\","
        + "\"external_attribute_value\":\"塑胶\",\"external_category_name\":\"母婴:童鞋:雨鞋\"}");
    assertEquals(response.getErrCode(), 0);
    assertEquals(response.getExternalAttributeName(), "帮面材质");
    assertEquals(response.getExternalAttributeValue(), "塑胶");
    assertEquals(response.getInternalAttributeName(), "鞋面材质");
    assertEquals(response.getInternalAttributeValue(), Arrays.asList("塑胶"));
  }

  @Test
  public void shouldMapMultipleExternalProductAttributesAndDecodeResponse() throws WxErrorException {
    CapturingChannelService channelService = new CapturingChannelService();
    channelService.response = "{\"errcode\":0,\"errmsg\":\"ok\","
      + "\"attributes\":[{\"key\":\"鞋面材质\",\"value\":\"塑胶\"}]}";
    ExternalAttribute externalAttribute = new ExternalAttribute();
    externalAttribute.setKey("帮面材质");
    externalAttribute.setValue("塑胶");
    ExternalProductMappingNewParam param = new ExternalProductMappingNewParam();
    param.setCatId(6000L);
    param.setExternalCategoryName("母婴:童鞋:雨鞋");
    param.setHeadImgs(Arrays.asList("https://example.com/head.jpg"));
    param.setDetailImgs(Arrays.asList("https://example.com/detail.jpg"));
    param.setTitle("测试商品");
    param.setExternalAttributes(Arrays.asList(externalAttribute));

    ExternalProductMappingNewResponse response =
      channelService.getProductAssistantService().externalProductMappingNew(param);

    assertEquals(channelService.url, "https://api.weixin.qq.com/channels/ec/product/externalproductmappingnew");
    assertEquals(channelService.request,
      "{\"cat_id\":6000,\"external_category_name\":\"母婴:童鞋:雨鞋\","
        + "\"head_imgs\":[\"https://example.com/head.jpg\"],"
        + "\"detail_imgs\":[\"https://example.com/detail.jpg\"],\"title\":\"测试商品\","
        + "\"external_attributes\":[{\"key\":\"帮面材质\",\"value\":\"塑胶\"}]}");
    assertEquals(response.getErrCode(), 0);
    assertEquals(response.getAttributes().size(), 1);
    assertEquals(response.getAttributes().get(0).getKey(), "鞋面材质");
    assertEquals(response.getAttributes().get(0).getValue(), "塑胶");
  }

  @Test
  public void shouldBeginTimingSaleWithStringIdentifiers() throws WxErrorException {
    CapturingChannelService channelService = new CapturingChannelService();
    channelService.response = "{\"errcode\":0,\"errmsg\":\"ok\"}";
    BeginTimingSaleParam param = new BeginTimingSaleParam();
    param.setProductId("9007199254740993");
    param.setTaskId("000123456789");

    WxChannelBaseResponse response = channelService.getProductAssistantService().beginTimingSale(param);

    assertEquals(channelService.url, "https://api.weixin.qq.com/channels/ec/product/begintimingsale");
    assertEquals(channelService.request,
      "{\"product_id\":\"9007199254740993\",\"task_id\":\"000123456789\"}");
    assertTrue(response.isSuccess());
  }

  @Test
  public void shouldCancelTimingSaleWithStringProductIdentifier() throws WxErrorException {
    CapturingChannelService channelService = new CapturingChannelService();
    channelService.response = "{\"errcode\":0,\"errmsg\":\"ok\"}";
    CancelTimingSaleParam param = new CancelTimingSaleParam();
    param.setProductId("9007199254740993");

    WxChannelBaseResponse response = channelService.getProductAssistantService().cancelTimingSale(param);

    assertEquals(channelService.url, "https://api.weixin.qq.com/channels/ec/product/canceltimingsale");
    assertEquals(channelService.request, "{\"product_id\":\"9007199254740993\"}");
    assertTrue(response.isSuccess());
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
