package com.binarywang.wxjava.store.api.impl;

import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.BEGIN_TIMING_SALE_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.CANCEL_TIMING_SALE_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.CATEGORY_PRE_CHECK_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.EXTERNAL_PRODUCT_MAPPING_NEW_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.EXTERNAL_PRODUCT_MAPPING_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.PRODUCT_BRAND_RECOMMEND_URL;

import com.binarywang.wxjava.store.api.WxStoreProductAssistantService;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;
import com.binarywang.wxjava.store.bean.product.assistant.BeginTimingSaleParam;
import com.binarywang.wxjava.store.bean.product.assistant.CancelTimingSaleParam;
import com.binarywang.wxjava.store.bean.product.assistant.CategoryPreCheckParam;
import com.binarywang.wxjava.store.bean.product.assistant.CategoryPreCheckResponse;
import com.binarywang.wxjava.store.bean.product.assistant.ExternalProductMappingNewParam;
import com.binarywang.wxjava.store.bean.product.assistant.ExternalProductMappingNewResponse;
import com.binarywang.wxjava.store.bean.product.assistant.ExternalProductMappingParam;
import com.binarywang.wxjava.store.bean.product.assistant.ExternalProductMappingResponse;
import com.binarywang.wxjava.store.bean.product.assistant.ProductBrandRecommendParam;
import com.binarywang.wxjava.store.bean.product.assistant.ProductBrandRecommendResponse;
import com.binarywang.wxjava.store.util.JsonUtils;
import com.binarywang.wxjava.store.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 微信小店商品辅助功能服务实现。
 */
public class WxStoreProductAssistantServiceImpl implements WxStoreProductAssistantService {

  /** 微信商店服务 */
  private final BaseWxStoreServiceImpl<?, ?> shopService;

  public WxStoreProductAssistantServiceImpl(BaseWxStoreServiceImpl<?, ?> shopService) {
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
  public WxStoreBaseResponse beginTimingSale(BeginTimingSaleParam param) throws WxErrorException {
    return post(BEGIN_TIMING_SALE_URL, param, WxStoreBaseResponse.class);
  }

  @Override
  public WxStoreBaseResponse cancelTimingSale(CancelTimingSaleParam param) throws WxErrorException {
    return post(CANCEL_TIMING_SALE_URL, param, WxStoreBaseResponse.class);
  }

  private <T extends WxStoreBaseResponse> T post(String url, Object param, Class<T> responseType)
    throws WxErrorException {
    String reqJson = JsonUtils.encode(param);
    String resJson = shopService.post(url, reqJson);
    return ResponseUtils.decode(resJson, responseType);
  }
}
