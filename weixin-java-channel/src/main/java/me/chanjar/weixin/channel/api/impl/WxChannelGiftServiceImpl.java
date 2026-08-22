package me.chanjar.weixin.channel.api.impl;

import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Spu.GIFT_ACTIVITY_ADD_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Spu.GIFT_ACTIVITY_DELETE_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Spu.GIFT_ACTIVITY_STOP_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Spu.GIFT_PRODUCT_ADD_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Spu.GIFT_PRODUCT_GET_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Spu.GIFT_PRODUCT_LIST_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Spu.GIFT_PRODUCT_ON_SALE_SET_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Spu.GIFT_PRODUCT_STOCK_UPDATE_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Spu.GIFT_PRODUCT_UPDATE_URL;

import me.chanjar.weixin.channel.api.WxChannelGiftService;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.product.GiftActivityAddParam;
import me.chanjar.weixin.channel.bean.product.GiftActivityAddResponse;
import me.chanjar.weixin.channel.bean.product.GiftActivityInfo;
import me.chanjar.weixin.channel.bean.product.GiftProductAddResponse;
import me.chanjar.weixin.channel.bean.product.GiftProductGetResponse;
import me.chanjar.weixin.channel.bean.product.GiftProductInfo;
import me.chanjar.weixin.channel.bean.product.GiftProductListParam;
import me.chanjar.weixin.channel.bean.product.GiftProductListResponse;
import me.chanjar.weixin.channel.bean.product.SkuStockParam;
import me.chanjar.weixin.channel.util.JsonUtils;
import me.chanjar.weixin.channel.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 微信小店赠品与买赠活动服务实现。
 */
public class WxChannelGiftServiceImpl implements WxChannelGiftService {

  private final BaseWxChannelServiceImpl<?, ?> shopService;

  public WxChannelGiftServiceImpl(BaseWxChannelServiceImpl<?, ?> shopService) {
    this.shopService = shopService;
  }

  @Override
  public GiftProductAddResponse addGiftProduct(GiftProductInfo info) throws WxErrorException {
    String reqJson = JsonUtils.encode(info);
    String resJson = shopService.post(GIFT_PRODUCT_ADD_URL, reqJson);
    return ResponseUtils.decode(resJson, GiftProductAddResponse.class);
  }

  @Override
  public WxChannelBaseResponse updateGiftProduct(GiftProductInfo info) throws WxErrorException {
    String reqJson = JsonUtils.encode(info);
    String resJson = shopService.post(GIFT_PRODUCT_UPDATE_URL, reqJson);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public WxChannelBaseResponse setProductAsGift(String productId) throws WxErrorException {
    String reqJson = "{\"product_id\":\"" + productId + "\"}";
    String resJson = shopService.post(GIFT_PRODUCT_ON_SALE_SET_URL, reqJson);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
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
  public WxChannelBaseResponse updateGiftStock(String productId, String skuId, Integer diffType, Integer num)
    throws WxErrorException {
    SkuStockParam param = new SkuStockParam(productId, skuId, diffType, num);
    String reqJson = JsonUtils.encode(param);
    String resJson = shopService.post(GIFT_PRODUCT_STOCK_UPDATE_URL, reqJson);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public GiftActivityAddResponse addGiftActivity(GiftActivityInfo info) throws WxErrorException {
    GiftActivityAddParam param = new GiftActivityAddParam(info);
    String reqJson = JsonUtils.encode(param);
    String resJson = shopService.post(GIFT_ACTIVITY_ADD_URL, reqJson);
    return ResponseUtils.decode(resJson, GiftActivityAddResponse.class);
  }

  @Override
  public WxChannelBaseResponse deleteGiftActivity(String activityId) throws WxErrorException {
    String reqJson = "{\"activity_id\":\"" + activityId + "\"}";
    String resJson = shopService.post(GIFT_ACTIVITY_DELETE_URL, reqJson);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public WxChannelBaseResponse stopGiftActivity(String activityId) throws WxErrorException {
    String reqJson = "{\"activity_id\":\"" + activityId + "\"}";
    String resJson = shopService.post(GIFT_ACTIVITY_STOP_URL, reqJson);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }
}
