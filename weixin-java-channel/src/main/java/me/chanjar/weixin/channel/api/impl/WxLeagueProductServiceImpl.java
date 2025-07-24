package me.chanjar.weixin.channel.api.impl;


import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.League.BATCH_ADD_LEAGUE_ITEM_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.League.DELETE_LEAGUE_ITEM_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.League.GET_LEAGUE_ITEM_LIST_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.League.GET_LEAGUE_ITEM_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.League.UPDATE_LEAGUE_ITEM_URL;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.channel.api.WxLeagueProductService;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.league.product.BatchAddParam;
import me.chanjar.weixin.channel.bean.league.product.BatchAddResponse;
import me.chanjar.weixin.channel.bean.league.product.ProductDeleteParam;
import me.chanjar.weixin.channel.bean.league.product.ProductDetailParam;
import me.chanjar.weixin.channel.bean.league.product.ProductDetailResponse;
import me.chanjar.weixin.channel.bean.league.product.ProductListParam;
import me.chanjar.weixin.channel.bean.league.product.ProductListResponse;
import me.chanjar.weixin.channel.bean.league.product.ProductUpdateParam;
import me.chanjar.weixin.channel.bean.league.product.ProductUpdateResponse;
import me.chanjar.weixin.channel.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;


/**
 * 视频号小店 商品服务
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Slf4j
public class WxLeagueProductServiceImpl implements WxLeagueProductService {

  /** 微信商店服务 */
  private final BaseWxChannelServiceImpl<?, ?> shopService;

  public WxLeagueProductServiceImpl(BaseWxChannelServiceImpl<?, ?>shopService) {
    this.shopService = shopService;
  }

  @Override
  public BatchAddResponse batchAddProduct(BatchAddParam param) throws WxErrorException {
    String resJson = shopService.post(BATCH_ADD_LEAGUE_ITEM_URL, param);
    return ResponseUtils.decode(resJson, BatchAddResponse.class);
  }

  @Override
  public ProductUpdateResponse updateProduct(ProductUpdateParam param) throws WxErrorException {
    String resJson = shopService.post(UPDATE_LEAGUE_ITEM_URL, param);
    return ResponseUtils.decode(resJson, ProductUpdateResponse.class);
  }

  @Override
  public WxChannelBaseResponse deleteProduct(Integer type, String productId, String infoId) throws WxErrorException {
    ProductDeleteParam param = new ProductDeleteParam(type, productId, infoId);
    String resJson = shopService.post(DELETE_LEAGUE_ITEM_URL, param);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public ProductDetailResponse getProductDetail(ProductDetailParam param) throws WxErrorException {
    String resJson = shopService.post(GET_LEAGUE_ITEM_URL, param);
    return ResponseUtils.decode(resJson, ProductDetailResponse.class);
  }

  @Override
  public ProductListResponse listProduct(ProductListParam param) throws WxErrorException {
    String resJson = shopService.post(GET_LEAGUE_ITEM_LIST_URL, param);
    return ResponseUtils.decode(resJson, ProductListResponse.class);
  }
}
