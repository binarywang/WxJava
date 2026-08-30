package com.binarywang.wxjava.store.api.impl;

import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.GIFT_ACTIVITY_ADD_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.GIFT_ACTIVITY_DELETE_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.GIFT_ACTIVITY_STOP_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.GIFT_PRODUCT_ADD_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.GIFT_PRODUCT_GET_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.GIFT_PRODUCT_LIST_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.GIFT_PRODUCT_ON_SALE_SET_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.GIFT_PRODUCT_STOCK_UPDATE_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.GIFT_PRODUCT_UPDATE_URL;

import com.binarywang.wxjava.store.api.WxStoreGiftService;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;
import com.binarywang.wxjava.store.bean.product.GiftActivityAddParam;
import com.binarywang.wxjava.store.bean.product.GiftActivityAddResponse;
import com.binarywang.wxjava.store.bean.product.GiftActivityInfo;
import com.binarywang.wxjava.store.bean.product.GiftProductAddResponse;
import com.binarywang.wxjava.store.bean.product.GiftProductGetResponse;
import com.binarywang.wxjava.store.bean.product.GiftProductInfo;
import com.binarywang.wxjava.store.bean.product.GiftProductListParam;
import com.binarywang.wxjava.store.bean.product.GiftProductListResponse;
import com.binarywang.wxjava.store.bean.product.SkuStockParam;
import com.binarywang.wxjava.store.util.JsonUtils;
import com.binarywang.wxjava.store.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 微信小店赠品与买赠活动服务实现。
 */
public class WxStoreGiftServiceImpl implements WxStoreGiftService {

  private final BaseWxStoreServiceImpl<?, ?> shopService;

  public WxStoreGiftServiceImpl(BaseWxStoreServiceImpl<?, ?> shopService) {
    this.shopService = shopService;
  }

  @Override
  public GiftProductAddResponse addGiftProduct(GiftProductInfo info) throws WxErrorException {
    String reqJson = JsonUtils.encode(info);
    String resJson = shopService.post(GIFT_PRODUCT_ADD_URL, reqJson);
    return ResponseUtils.decode(resJson, GiftProductAddResponse.class);
  }

  @Override
  public WxStoreBaseResponse updateGiftProduct(GiftProductInfo info) throws WxErrorException {
    String reqJson = JsonUtils.encode(info);
    String resJson = shopService.post(GIFT_PRODUCT_UPDATE_URL, reqJson);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  @Override
  public WxStoreBaseResponse setProductAsGift(String productId) throws WxErrorException {
    String reqJson = "{\"product_id\":\"" + productId + "\"}";
    String resJson = shopService.post(GIFT_PRODUCT_ON_SALE_SET_URL, reqJson);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  @Override
  public GiftProductGetResponse getGiftProduct(String productId) throws WxErrorException {
    String reqJson = "{\"product_id\":\"" + productId + "\"}";
    String resJson = shopService.post(GIFT_PRODUCT_GET_URL, reqJson);
    return ResponseUtils.decode(resJson, GiftProductGetResponse.class);
  }

  @Override
  public GiftProductListResponse listGiftProduct(GiftProductListParam param) throws WxErrorException {
    String reqJson = JsonUtils.encode(param);
    String resJson = shopService.post(GIFT_PRODUCT_LIST_URL, reqJson);
    return ResponseUtils.decode(resJson, GiftProductListResponse.class);
  }

  @Override
  public WxStoreBaseResponse updateGiftStock(String productId, String skuId, Integer diffType, Integer num)
    throws WxErrorException {
    SkuStockParam param = new SkuStockParam(productId, skuId, diffType, num);
    String reqJson = JsonUtils.encode(param);
    String resJson = shopService.post(GIFT_PRODUCT_STOCK_UPDATE_URL, reqJson);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  @Override
  public GiftActivityAddResponse addGiftActivity(GiftActivityInfo info) throws WxErrorException {
    GiftActivityAddParam param = new GiftActivityAddParam(info);
    String reqJson = JsonUtils.encode(param);
    String resJson = shopService.post(GIFT_ACTIVITY_ADD_URL, reqJson);
    return ResponseUtils.decode(resJson, GiftActivityAddResponse.class);
  }

  @Override
  public WxStoreBaseResponse deleteGiftActivity(String activityId) throws WxErrorException {
    String reqJson = "{\"activity_id\":\"" + activityId + "\"}";
    String resJson = shopService.post(GIFT_ACTIVITY_DELETE_URL, reqJson);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  @Override
  public WxStoreBaseResponse stopGiftActivity(String activityId) throws WxErrorException {
    String reqJson = "{\"activity_id\":\"" + activityId + "\"}";
    String resJson = shopService.post(GIFT_ACTIVITY_STOP_URL, reqJson);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }
}
