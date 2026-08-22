package me.chanjar.weixin.channel.api.impl;


import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Spu.CANCEL_AUDIT_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Spu.SPU_ADD_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Spu.SPU_AUDIT_FREE_UPDATE_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Spu.SPU_DELISTING_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Spu.SPU_DEL_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Spu.SPU_GET_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Spu.SPU_H5URL_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Spu.SPU_LISTING_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Spu.SPU_LIST_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Spu.SPU_QRCODE_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Spu.SPU_TAGLINK_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Spu.SPU_UPDATE_URL;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.channel.api.WxChannelGiftService;
import me.chanjar.weixin.channel.api.WxChannelLimitedDiscountService;
import me.chanjar.weixin.channel.api.WxChannelProductService;
import me.chanjar.weixin.channel.api.WxChannelProductStockService;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.limit.LimitTaskAddResponse;
import me.chanjar.weixin.channel.bean.limit.LimitTaskListResponse;
import me.chanjar.weixin.channel.bean.limit.LimitTaskParam;
import me.chanjar.weixin.channel.bean.product.GiftActivityAddResponse;
import me.chanjar.weixin.channel.bean.product.GiftActivityInfo;
import me.chanjar.weixin.channel.bean.product.GiftProductAddResponse;
import me.chanjar.weixin.channel.bean.product.GiftProductGetResponse;
import me.chanjar.weixin.channel.bean.product.GiftProductInfo;
import me.chanjar.weixin.channel.bean.product.GiftProductListParam;
import me.chanjar.weixin.channel.bean.product.GiftProductListResponse;
import me.chanjar.weixin.channel.bean.product.SkuStockBatchResponse;
import me.chanjar.weixin.channel.bean.product.SkuStockResponse;
import me.chanjar.weixin.channel.bean.product.SpuFastInfo;
import me.chanjar.weixin.channel.bean.product.SpuGetResponse;
import me.chanjar.weixin.channel.bean.product.SpuInfo;
import me.chanjar.weixin.channel.bean.product.SpuListParam;
import me.chanjar.weixin.channel.bean.product.SpuListResponse;
import me.chanjar.weixin.channel.bean.product.SpuUpdateInfo;
import me.chanjar.weixin.channel.bean.product.SpuUpdateResponse;
import me.chanjar.weixin.channel.bean.product.link.ProductH5UrlResponse;
import me.chanjar.weixin.channel.bean.product.link.ProductQrCodeResponse;
import me.chanjar.weixin.channel.bean.product.link.ProductTagLinkResponse;
import me.chanjar.weixin.channel.util.JsonUtils;
import me.chanjar.weixin.channel.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 视频号小店商品服务
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Slf4j
public class WxChannelProductServiceImpl implements WxChannelProductService {

  /** 微信商店服务 */
  private final BaseWxChannelServiceImpl<?, ?> shopService;
  private final WxChannelGiftService giftService;
  private final WxChannelLimitedDiscountService limitedDiscountService;
  private final WxChannelProductStockService productStockService;

  public WxChannelProductServiceImpl(BaseWxChannelServiceImpl<?, ?> shopService) {
    this(shopService, new WxChannelGiftServiceImpl(shopService),
      new WxChannelLimitedDiscountServiceImpl(shopService), new WxChannelProductStockServiceImpl(shopService));
  }

  WxChannelProductServiceImpl(BaseWxChannelServiceImpl<?, ?> shopService, WxChannelGiftService giftService,
                              WxChannelLimitedDiscountService limitedDiscountService,
                              WxChannelProductStockService productStockService) {
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
  public WxChannelBaseResponse updateProductAuditFree(SpuFastInfo info) throws WxErrorException {
    String reqJson = JsonUtils.encode(info);
    String resJson = shopService.post(SPU_AUDIT_FREE_UPDATE_URL, reqJson);
    return ResponseUtils.decode(resJson, SpuUpdateResponse.class);
  }

  @Override
  public WxChannelBaseResponse updateStock(String productId, String skuId, Integer diffType, Integer num)
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
  protected WxChannelBaseResponse simpleProductRequest(String url, String productId) throws WxErrorException {
    String reqJson = this.generateProductIdJson(productId, null);
    String resJson = shopService.post(url, reqJson);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public WxChannelBaseResponse deleteProduct(String productId) throws WxErrorException {
    return simpleProductRequest(SPU_DEL_URL, productId);
  }

  @Override
  public WxChannelBaseResponse cancelProductAudit(String productId) throws WxErrorException {
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
  public WxChannelBaseResponse upProduct(String productId) throws WxErrorException {
    return simpleProductRequest(SPU_LISTING_URL, productId);
  }

  @Override
  public WxChannelBaseResponse downProduct(String productId) throws WxErrorException {
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
  public GiftProductAddResponse addGiftProduct(GiftProductInfo info) throws WxErrorException {
    return giftService.addGiftProduct(info);
  }

  @Override
  public WxChannelBaseResponse updateGiftProduct(GiftProductInfo info) throws WxErrorException {
    return giftService.updateGiftProduct(info);
  }

  @Override
  public WxChannelBaseResponse setProductAsGift(String productId) throws WxErrorException {
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
  public WxChannelBaseResponse updateGiftStock(String productId, String skuId, Integer diffType, Integer num)
    throws WxErrorException {
    return giftService.updateGiftStock(productId, skuId, diffType, num);
  }

  @Override
  public GiftActivityAddResponse addGiftActivity(GiftActivityInfo info) throws WxErrorException {
    return giftService.addGiftActivity(info);
  }

  @Override
  public WxChannelBaseResponse deleteGiftActivity(String activityId) throws WxErrorException {
    return giftService.deleteGiftActivity(activityId);
  }

  @Override
  public WxChannelBaseResponse stopGiftActivity(String activityId) throws WxErrorException {
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
  public WxChannelBaseResponse stopLimitTask(String taskId) throws WxErrorException {
    return limitedDiscountService.stopLimitTask(taskId);
  }

  @Override
  public WxChannelBaseResponse deleteLimitTask(String taskId) throws WxErrorException {
    return limitedDiscountService.deleteLimitTask(taskId);
  }
}
