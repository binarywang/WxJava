package me.chanjar.weixin.channel.api.impl;

import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Spu.BEGIN_TIMING_SALE_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Spu.CANCEL_TIMING_SALE_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Spu.CATEGORY_PRE_CHECK_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Spu.EXTERNAL_PRODUCT_MAPPING_NEW_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Spu.EXTERNAL_PRODUCT_MAPPING_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Spu.PRODUCT_BRAND_RECOMMEND_URL;

import me.chanjar.weixin.channel.api.WxChannelProductAssistantService;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.product.assistant.BeginTimingSaleParam;
import me.chanjar.weixin.channel.bean.product.assistant.CancelTimingSaleParam;
import me.chanjar.weixin.channel.bean.product.assistant.CategoryPreCheckParam;
import me.chanjar.weixin.channel.bean.product.assistant.CategoryPreCheckResponse;
import me.chanjar.weixin.channel.bean.product.assistant.ExternalProductMappingNewParam;
import me.chanjar.weixin.channel.bean.product.assistant.ExternalProductMappingNewResponse;
import me.chanjar.weixin.channel.bean.product.assistant.ExternalProductMappingParam;
import me.chanjar.weixin.channel.bean.product.assistant.ExternalProductMappingResponse;
import me.chanjar.weixin.channel.bean.product.assistant.ProductBrandRecommendParam;
import me.chanjar.weixin.channel.bean.product.assistant.ProductBrandRecommendResponse;
import me.chanjar.weixin.channel.util.JsonUtils;
import me.chanjar.weixin.channel.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 微信小店商品辅助功能服务实现。
 */
public class WxChannelProductAssistantServiceImpl implements WxChannelProductAssistantService {

  /** 微信商店服务 */
  private final BaseWxChannelServiceImpl<?, ?> shopService;

  public WxChannelProductAssistantServiceImpl(BaseWxChannelServiceImpl<?, ?> shopService) {
    this.shopService = shopService;
  }

  @Override
  public CategoryPreCheckResponse categoryPreCheck(CategoryPreCheckParam param) throws WxErrorException {
    return post(CATEGORY_PRE_CHECK_URL, param, CategoryPreCheckResponse.class);
  }

  @Override
  public ProductBrandRecommendResponse getProductBrandRecommend(ProductBrandRecommendParam param)
    throws WxErrorException {
    return post(PRODUCT_BRAND_RECOMMEND_URL, param, ProductBrandRecommendResponse.class);
  }

  @Override
  public ExternalProductMappingResponse externalProductMapping(ExternalProductMappingParam param)
    throws WxErrorException {
    return post(EXTERNAL_PRODUCT_MAPPING_URL, param, ExternalProductMappingResponse.class);
  }

  @Override
  public ExternalProductMappingNewResponse externalProductMappingNew(ExternalProductMappingNewParam param)
    throws WxErrorException {
    return post(EXTERNAL_PRODUCT_MAPPING_NEW_URL, param, ExternalProductMappingNewResponse.class);
  }

  @Override
  public WxChannelBaseResponse beginTimingSale(BeginTimingSaleParam param) throws WxErrorException {
    return post(BEGIN_TIMING_SALE_URL, param, WxChannelBaseResponse.class);
  }

  @Override
  public WxChannelBaseResponse cancelTimingSale(CancelTimingSaleParam param) throws WxErrorException {
    return post(CANCEL_TIMING_SALE_URL, param, WxChannelBaseResponse.class);
  }

  private <T extends WxChannelBaseResponse> T post(String url, Object param, Class<T> responseType)
    throws WxErrorException {
    String reqJson = JsonUtils.encode(param);
    String resJson = shopService.post(url, reqJson);
    return ResponseUtils.decode(resJson, responseType);
  }
}
