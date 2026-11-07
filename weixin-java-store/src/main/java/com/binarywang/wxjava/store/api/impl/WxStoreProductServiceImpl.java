package com.binarywang.wxjava.store.api.impl;


import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.CANCEL_AUDIT_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.DELETE_LIMIT_TASK_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.GIFT_ACTIVITY_ADD_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.GIFT_ACTIVITY_DELETE_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.GIFT_ACTIVITY_STOP_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.GIFT_PRODUCT_ADD_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.GIFT_PRODUCT_GET_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.GIFT_PRODUCT_LIST_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.GIFT_PRODUCT_ON_SALE_SET_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.GIFT_PRODUCT_STOCK_UPDATE_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.GIFT_PRODUCT_UPDATE_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.LIST_LIMIT_TASK_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.SPU_ADD_PRODUCT_THIRD_PARTY_SOURCE_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.SPU_ADD_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.SPU_AUDIT_FREE_UPDATE_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.SPU_AUDIT_STRATEGY_GET_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.SPU_AUDIT_STRATEGY_SET_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.SPU_BEGIN_TIMING_SALE_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.SPU_CANCEL_TIMING_SALE_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.SPU_CATEGORY_CLASSIFY_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.SPU_CATEGORY_PRE_CHECK_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.SPU_DELISTING_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.SPU_DEL_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.SPU_EXTERNAL_PRODUCT_MAPPING_NEW_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.SPU_EXTERNAL_PRODUCT_MAPPING_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.SPU_GET_AUDIT_QUOTA_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.SPU_GET_STOCK_BATCH_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.SPU_GET_STOCK_FLOW_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.SPU_GET_STOCK_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.SPU_GET_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.SPU_H5URL_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.SPU_LISTING_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.SPU_LIST_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.SPU_PRODUCT_BRAND_RECOMMEND_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.SPU_QRCODE_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.SPU_SCHEME_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.SPU_TAGLINK_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.SPU_UPDATE_URL;

import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import com.binarywang.wxjava.store.api.WxStoreGiftService;
import com.binarywang.wxjava.store.api.WxStoreLimitedDiscountService;
import com.binarywang.wxjava.store.api.WxStoreProductService;
import com.binarywang.wxjava.store.api.WxStoreProductStockService;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;
import com.binarywang.wxjava.store.bean.limit.LimitTaskAddResponse;
import com.binarywang.wxjava.store.bean.limit.LimitTaskListResponse;
import com.binarywang.wxjava.store.bean.limit.LimitTaskParam;
import com.binarywang.wxjava.store.bean.product.AddProductThirdPartySourceParam;
import com.binarywang.wxjava.store.bean.product.AddProductThirdPartySourceResponse;
import com.binarywang.wxjava.store.bean.product.ExternalProductMappingNewParam;
import com.binarywang.wxjava.store.bean.product.ExternalProductMappingNewResponse;
import com.binarywang.wxjava.store.bean.product.ExternalProductMappingParam;
import com.binarywang.wxjava.store.bean.product.ExternalProductMappingResponse;
import com.binarywang.wxjava.store.bean.product.GiftActivityAddParam;
import com.binarywang.wxjava.store.bean.product.GiftActivityAddResponse;
import com.binarywang.wxjava.store.bean.product.GiftActivityInfo;
import com.binarywang.wxjava.store.bean.product.GiftProductAddResponse;
import com.binarywang.wxjava.store.bean.product.GiftProductGetResponse;
import com.binarywang.wxjava.store.bean.product.GiftProductInfo;
import com.binarywang.wxjava.store.bean.product.GiftProductListParam;
import com.binarywang.wxjava.store.bean.product.GiftProductListResponse;
import com.binarywang.wxjava.store.bean.product.ProductAuditQuotaResponse;
import com.binarywang.wxjava.store.bean.product.ProductAuditStrategyResponse;
import com.binarywang.wxjava.store.bean.product.ProductAuditStrategySetParam;
import com.binarywang.wxjava.store.bean.product.ProductBrandRecommendParam;
import com.binarywang.wxjava.store.bean.product.ProductBrandRecommendResponse;
import com.binarywang.wxjava.store.bean.product.ProductCategoryClassifyParam;
import com.binarywang.wxjava.store.bean.product.ProductCategoryClassifyResponse;
import com.binarywang.wxjava.store.bean.product.ProductCategoryPreCheckParam;
import com.binarywang.wxjava.store.bean.product.ProductCategoryPreCheckResponse;
import com.binarywang.wxjava.store.bean.product.ProductSchemeParam;
import com.binarywang.wxjava.store.bean.product.ProductSchemeResponse;
import com.binarywang.wxjava.store.bean.product.ProductStockFlowParam;
import com.binarywang.wxjava.store.bean.product.ProductStockFlowResponse;
import com.binarywang.wxjava.store.bean.product.ProductTimingSaleParam;
import com.binarywang.wxjava.store.bean.product.SkuStockBatchParam;
import com.binarywang.wxjava.store.bean.product.SkuStockBatchResponse;
import com.binarywang.wxjava.store.bean.product.SkuStockResponse;
import com.binarywang.wxjava.store.bean.product.SpuFastInfo;
import com.binarywang.wxjava.store.bean.product.SpuGetResponse;
import com.binarywang.wxjava.store.bean.product.SpuInfo;
import com.binarywang.wxjava.store.bean.product.SpuListParam;
import com.binarywang.wxjava.store.bean.product.SpuListResponse;
import com.binarywang.wxjava.store.bean.product.SpuUpdateInfo;
import com.binarywang.wxjava.store.bean.product.SpuUpdateResponse;
import com.binarywang.wxjava.store.bean.product.link.ProductH5UrlResponse;
import com.binarywang.wxjava.store.bean.product.link.ProductQrCodeResponse;
import com.binarywang.wxjava.store.bean.product.link.ProductTagLinkResponse;
import com.binarywang.wxjava.store.util.JsonUtils;
import com.binarywang.wxjava.store.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 微信小店商品服务
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Slf4j
public class WxStoreProductServiceImpl implements WxStoreProductService {

  /** 微信商店服务 */
  private final BaseWxStoreServiceImpl<?, ?> shopService;
  private final WxStoreGiftService giftService;
  private final WxStoreLimitedDiscountService limitedDiscountService;
  private final WxStoreProductStockService productStockService;

  public WxStoreProductServiceImpl(BaseWxStoreServiceImpl<?, ?> shopService) {
    this(shopService, new WxStoreGiftServiceImpl(shopService),
      new WxStoreLimitedDiscountServiceImpl(shopService), new WxStoreProductStockServiceImpl(shopService));
  }

  WxStoreProductServiceImpl(BaseWxStoreServiceImpl<?, ?> shopService, WxStoreGiftService giftService,
                              WxStoreLimitedDiscountService limitedDiscountService,
                              WxStoreProductStockService productStockService) {
    this.shopService = shopService;
    this.giftService = giftService;
    this.limitedDiscountService = limitedDiscountService;
    this.productStockService = productStockService;
  }

  @Override
  public SpuUpdateResponse addProduct(SpuUpdateInfo info) throws WxErrorException {
    String reqJson = JsonUtils.encode(info);
    String resJson = shopService.post(SPU_ADD_URL, reqJson);
    return ResponseUtils.decode(resJson, SpuUpdateResponse.class);
  }

  @Override
  public SpuUpdateResponse updateProduct(SpuUpdateInfo info) throws WxErrorException {
    String reqJson = JsonUtils.encode(info);
    String resJson = shopService.post(SPU_UPDATE_URL, reqJson);
    return ResponseUtils.decode(resJson, SpuUpdateResponse.class);
  }

  @Override
  public SpuUpdateResponse addProduct(SpuInfo info) throws WxErrorException {
    String reqJson = JsonUtils.encode(info);
    String resJson = shopService.post(SPU_ADD_URL, reqJson);
    return ResponseUtils.decode(resJson, SpuUpdateResponse.class);
  }

  @Override
  public SpuUpdateResponse updateProduct(SpuInfo info) throws WxErrorException {
    String reqJson = JsonUtils.encode(info);
    String resJson = shopService.post(SPU_UPDATE_URL, reqJson);
    return ResponseUtils.decode(resJson, SpuUpdateResponse.class);
  }

  @Override
  public WxStoreBaseResponse updateProductAuditFree(SpuFastInfo info) throws WxErrorException {
    String reqJson = JsonUtils.encode(info);
    String resJson = shopService.post(SPU_AUDIT_FREE_UPDATE_URL, reqJson);
    return ResponseUtils.decode(resJson, SpuUpdateResponse.class);
  }

  @Override
  public WxStoreBaseResponse updateStock(String productId, String skuId, Integer diffType, Integer num)
    throws WxErrorException {
    return productStockService.updateStock(productId, skuId, diffType, num);
  }

  /**
   * 生成商品id Json
   *
   * @param productId 商品ID
   * @param dataType  默认取1。1:获取线上数据, 2:获取草稿数据, 3:同时获取线上和草稿数据（注意：需成功上架后才有线上数据）
   * @return json
   */
  protected String generateProductIdJson(String productId, Integer dataType) {
    StringBuilder sb = new StringBuilder();
    sb.append('{');
    if (productId != null) {
      sb.append("\"product_id\":").append(productId);
    }

    if (dataType != null) {
      sb.append(",").append("\"data_type\":").append(dataType);
    }
    sb.append('}');
    return sb.toString();
  }

  /**
   * 简单的商品请求 参数是商品id 只返回基本结果
   *
   * @param url       资源路径
   * @param productId 商品ID
   * @return 是否成功
   */
  protected WxStoreBaseResponse simpleProductRequest(String url, String productId) throws WxErrorException {
    String reqJson = this.generateProductIdJson(productId, null);
    String resJson = shopService.post(url, reqJson);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  @Override
  public WxStoreBaseResponse deleteProduct(String productId) throws WxErrorException {
    return simpleProductRequest(SPU_DEL_URL, productId);
  }

  @Override
  public WxStoreBaseResponse cancelProductAudit(String productId) throws WxErrorException {
    return simpleProductRequest(CANCEL_AUDIT_URL, productId);
  }

  @Override
  public SpuGetResponse getProduct(String productId, Integer dataType) throws WxErrorException {
    String reqJson = this.generateProductIdJson(productId, dataType);
    String resJson = shopService.post(SPU_GET_URL, reqJson);
    return ResponseUtils.decode(resJson, SpuGetResponse.class);
  }

  @Override
  public SpuListResponse listProduct(Integer pageSize, String nextKey, Integer status) throws WxErrorException {
    SpuListParam param = new SpuListParam(pageSize, nextKey, status);
    String reqJson = JsonUtils.encode(param);
    String resJson = shopService.post(SPU_LIST_URL, reqJson);
    return ResponseUtils.decode(resJson, SpuListResponse.class);
  }

  @Override
  public WxStoreBaseResponse upProduct(String productId) throws WxErrorException {
    return simpleProductRequest(SPU_LISTING_URL, productId);
  }

  @Override
  public WxStoreBaseResponse downProduct(String productId) throws WxErrorException {
    return simpleProductRequest(SPU_DELISTING_URL, productId);
  }

  @Override
  public SkuStockResponse getSkuStock(String productId, String skuId) throws WxErrorException {
    return productStockService.getSkuStock(productId, skuId);
  }

  @Override
  public SkuStockBatchResponse getSkuStockBatch(List<String> productIds) throws WxErrorException {
    return productStockService.getSkuStockBatch(productIds);
  }

  @Override
  public ProductH5UrlResponse getProductH5Url(String productId) throws WxErrorException {
    String reqJson = "{\"product_id\":\"" + productId + "\"}";
    String resJson = shopService.post(SPU_H5URL_URL, reqJson);
    return ResponseUtils.decode(resJson, ProductH5UrlResponse.class);
  }

  @Override
  public ProductQrCodeResponse getProductQrCode(String productId) throws WxErrorException {
    String reqJson = "{\"product_id\":\"" + productId + "\"}";
    String resJson = shopService.post(SPU_QRCODE_URL, reqJson);
    return ResponseUtils.decode(resJson, ProductQrCodeResponse.class);
  }

  @Override
  public ProductTagLinkResponse getProductTagLink(String productId) throws WxErrorException {
    String reqJson = "{\"product_id\":\"" + productId + "\"}";
    String resJson = shopService.post(SPU_TAGLINK_URL, reqJson);
    return ResponseUtils.decode(resJson, ProductTagLinkResponse.class);
  }

  @Override
  public ProductSchemeResponse getProductScheme(ProductSchemeParam param) throws WxErrorException {
    return postAndDecode(SPU_SCHEME_URL, param, ProductSchemeResponse.class);
  }

  @Override
  public ProductCategoryClassifyResponse classifyProductCategory(ProductCategoryClassifyParam param)
    throws WxErrorException {
    return postAndDecode(SPU_CATEGORY_CLASSIFY_URL, param, ProductCategoryClassifyResponse.class);
  }

  @Override
  public WxStoreBaseResponse beginTimingSale(ProductTimingSaleParam param) throws WxErrorException {
    return postAndDecode(SPU_BEGIN_TIMING_SALE_URL, param, WxStoreBaseResponse.class);
  }

  @Override
  public WxStoreBaseResponse cancelTimingSale(String productId) throws WxErrorException {
    return postAndDecode(SPU_CANCEL_TIMING_SALE_URL, Collections.singletonMap("product_id", productId),
      WxStoreBaseResponse.class);
  }

  @Override
  public ExternalProductMappingResponse externalProductMapping(ExternalProductMappingParam param)
    throws WxErrorException {
    return postAndDecode(SPU_EXTERNAL_PRODUCT_MAPPING_URL, param, ExternalProductMappingResponse.class);
  }

  @Override
  public ProductCategoryPreCheckResponse categoryPreCheck(ProductCategoryPreCheckParam param)
    throws WxErrorException {
    return postAndDecode(SPU_CATEGORY_PRE_CHECK_URL, param, ProductCategoryPreCheckResponse.class);
  }

  @Override
  public ProductAuditStrategyResponse getProductAuditStrategy() throws WxErrorException {
    return postAndDecode(SPU_AUDIT_STRATEGY_GET_URL, "{}", ProductAuditStrategyResponse.class);
  }

  @Override
  public WxStoreBaseResponse setProductAuditStrategy(ProductAuditStrategySetParam param) throws WxErrorException {
    return postAndDecode(SPU_AUDIT_STRATEGY_SET_URL, param, WxStoreBaseResponse.class);
  }

  @Override
  public ProductAuditQuotaResponse getProductAuditQuota() throws WxErrorException {
    return postAndDecode(SPU_GET_AUDIT_QUOTA_URL, "{}", ProductAuditQuotaResponse.class);
  }

  @Override
  public ExternalProductMappingNewResponse externalProductMappingNew(ExternalProductMappingNewParam param)
    throws WxErrorException {
    return postAndDecode(SPU_EXTERNAL_PRODUCT_MAPPING_NEW_URL, param, ExternalProductMappingNewResponse.class);
  }

  @Override
  public ProductBrandRecommendResponse productBrandRecommend(ProductBrandRecommendParam param)
    throws WxErrorException {
    return postAndDecode(SPU_PRODUCT_BRAND_RECOMMEND_URL, param, ProductBrandRecommendResponse.class);
  }

  @Override
  public AddProductThirdPartySourceResponse addProductThirdPartySource(AddProductThirdPartySourceParam param)
    throws WxErrorException {
    return postAndDecode(SPU_ADD_PRODUCT_THIRD_PARTY_SOURCE_URL, param, AddProductThirdPartySourceResponse.class);
  }

  @Override
  public ProductStockFlowResponse getStockFlow(ProductStockFlowParam param) throws WxErrorException {
    return postAndDecode(SPU_GET_STOCK_FLOW_URL, param, ProductStockFlowResponse.class);
  }

  private <T extends WxStoreBaseResponse> T postAndDecode(String url, Object param, Class<T> responseType)
    throws WxErrorException {
    String reqJson = param instanceof String ? (String) param : JsonUtils.encode(param);
    String resJson = shopService.post(url, reqJson);
    return ResponseUtils.decode(resJson, responseType);
  }

  @Override
  public GiftProductAddResponse addGiftProduct(GiftProductInfo info) throws WxErrorException {
    return giftService.addGiftProduct(info);
  }

  @Override
  public WxStoreBaseResponse updateGiftProduct(GiftProductInfo info) throws WxErrorException {
    return giftService.updateGiftProduct(info);
  }

  @Override
  public WxStoreBaseResponse setProductAsGift(String productId) throws WxErrorException {
    return giftService.setProductAsGift(productId);
  }

  @Override
  public GiftProductGetResponse getGiftProduct(String productId) throws WxErrorException {
    return giftService.getGiftProduct(productId);
  }

  @Override
  public GiftProductListResponse listGiftProduct(GiftProductListParam param) throws WxErrorException {
    return giftService.listGiftProduct(param);
  }

  @Override
  public WxStoreBaseResponse updateGiftStock(String productId, String skuId, Integer diffType, Integer num)
    throws WxErrorException {
    return giftService.updateGiftStock(productId, skuId, diffType, num);
  }

  @Override
  public GiftActivityAddResponse addGiftActivity(GiftActivityInfo info) throws WxErrorException {
    return giftService.addGiftActivity(info);
  }

  @Override
  public WxStoreBaseResponse deleteGiftActivity(String activityId) throws WxErrorException {
    return giftService.deleteGiftActivity(activityId);
  }

  @Override
  public WxStoreBaseResponse stopGiftActivity(String activityId) throws WxErrorException {
    return giftService.stopGiftActivity(activityId);
  }

  @Override
  public LimitTaskAddResponse addLimitTask(LimitTaskParam param) throws WxErrorException {
    return limitedDiscountService.addLimitTask(param);
  }

  @Override
  public LimitTaskListResponse listLimitTask(Integer pageSize, String nextKey, Integer status)
    throws WxErrorException {
    return limitedDiscountService.listLimitTask(pageSize, nextKey, status);
  }

  @Override
  public WxStoreBaseResponse stopLimitTask(String taskId) throws WxErrorException {
    return limitedDiscountService.stopLimitTask(taskId);
  }

  @Override
  public WxStoreBaseResponse deleteLimitTask(String taskId) throws WxErrorException {
    return limitedDiscountService.deleteLimitTask(taskId);
  }
}
