package me.chanjar.weixin.channel.api.impl;

import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Gift.ADD_GIFT_PRODUCT_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Gift.GET_GIFT_PRODUCT_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Gift.LIST_GIFT_PRODUCT_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Gift.SET_PRODUCT_AS_GIFT_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Gift.UPDATE_GIFT_PRODUCT_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Gift.UPDATE_GIFT_STOCK_URL;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.channel.api.WxChannelGiftService;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.gift.GiftGetResponse;
import me.chanjar.weixin.channel.bean.gift.GiftListParam;
import me.chanjar.weixin.channel.bean.gift.GiftProductInfo;
import me.chanjar.weixin.channel.bean.gift.GiftProductListResponse;
import me.chanjar.weixin.channel.bean.gift.GiftProductResponse;
import me.chanjar.weixin.channel.bean.gift.GiftProductUpdateInfo;
import me.chanjar.weixin.channel.bean.gift.GiftSetParam;
import me.chanjar.weixin.channel.util.JsonUtils;
import me.chanjar.weixin.channel.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 视频号小店 赠品管理服务实现
 *
 * @author <a href="https://github.com/features/copilot">GitHub Copilot</a>
 */
@Slf4j
public class WxChannelGiftServiceImpl implements WxChannelGiftService {

  /** 微信商店服务 */
  private final BaseWxChannelServiceImpl<?, ?> shopService;

  public WxChannelGiftServiceImpl(BaseWxChannelServiceImpl<?, ?> shopService) {
    this.shopService = shopService;
  }

  @Override
  public GiftProductResponse addGiftProduct(GiftProductInfo info) throws WxErrorException {
    String reqJson = JsonUtils.encode(info);
    String resJson = shopService.post(ADD_GIFT_PRODUCT_URL, reqJson);
    return ResponseUtils.decode(resJson, GiftProductResponse.class);
  }

  @Override
  public GiftProductResponse updateGiftProduct(GiftProductUpdateInfo info) throws WxErrorException {
    String reqJson = JsonUtils.encode(info);
    String resJson = shopService.post(UPDATE_GIFT_PRODUCT_URL, reqJson);
    return ResponseUtils.decode(resJson, GiftProductResponse.class);
  }

  @Override
  public GiftProductListResponse setProductAsGift(GiftSetParam param) throws WxErrorException {
    String reqJson = JsonUtils.encode(param);
    String resJson = shopService.post(SET_PRODUCT_AS_GIFT_URL, reqJson);
    return ResponseUtils.decode(resJson, GiftProductListResponse.class);
  }

  @Override
  public GiftGetResponse getGiftProduct(String productId, Integer dataType) throws WxErrorException {
    String reqJson = "{\"product_id\":\"" + productId + "\",\"data_type\":" + dataType + "}";
    String resJson = shopService.post(GET_GIFT_PRODUCT_URL, reqJson);
    return ResponseUtils.decode(resJson, GiftGetResponse.class);
  }

  @Override
  public GiftProductListResponse listGiftProduct(Integer pageSize, String nextKey, Integer status)
    throws WxErrorException {
    GiftListParam param = new GiftListParam(pageSize, nextKey, status);
    String reqJson = JsonUtils.encode(param);
    String resJson = shopService.post(LIST_GIFT_PRODUCT_URL, reqJson);
    return ResponseUtils.decode(resJson, GiftProductListResponse.class);
  }

  @Override
  public WxChannelBaseResponse updateGiftStock(String productId, String skuId, Integer diffType, Integer num)
    throws WxErrorException {
    String reqJson = "{\"product_id\":\"" + productId + "\",\"sku_id\":\"" + skuId
      + "\",\"diff_type\":" + diffType + ",\"num\":" + num + "}";
    String resJson = shopService.post(UPDATE_GIFT_STOCK_URL, reqJson);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }
}
