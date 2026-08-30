package com.binarywang.wxjava.store.api.impl;

import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.CompassShop.FINDER_AUTH_LIST_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.CompassShop.FINDER_LIST_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.CompassShop.GET_FINDER_OVERALL_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.CompassShop.GET_FINDER_PRODUCT_LIST_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.CompassShop.GET_FINDER_PRODUCT_OVERALL_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.CompassShop.GET_LIVE_LIST_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.CompassShop.GET_SHOP_OVERALL_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.CompassShop.GET_SHOP_PRODUCT_DATA_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.CompassShop.GET_SHOP_PRODUCT_LIST_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.CompassShop.GET_SHOP_SALE_PROFILE_DATA_URL;

import lombok.extern.slf4j.Slf4j;
import com.binarywang.wxjava.store.api.WxStoreCompassShopService;
import com.binarywang.wxjava.store.bean.compass.CompassFinderBaseParam;
import com.binarywang.wxjava.store.bean.compass.shop.CompassFinderIdParam;
import com.binarywang.wxjava.store.bean.compass.shop.FinderAuthListResponse;
import com.binarywang.wxjava.store.bean.compass.shop.FinderListResponse;
import com.binarywang.wxjava.store.bean.compass.shop.FinderOverallResponse;
import com.binarywang.wxjava.store.bean.compass.shop.FinderProductListResponse;
import com.binarywang.wxjava.store.bean.compass.shop.FinderProductOverallResponse;
import com.binarywang.wxjava.store.bean.compass.shop.ShopLiveListResponse;
import com.binarywang.wxjava.store.bean.compass.shop.ShopOverallResponse;
import com.binarywang.wxjava.store.bean.compass.shop.ShopProductDataParam;
import com.binarywang.wxjava.store.bean.compass.shop.ShopProductDataResponse;
import com.binarywang.wxjava.store.bean.compass.shop.ShopProductListResponse;
import com.binarywang.wxjava.store.bean.compass.shop.ShopSaleProfileDataParam;
import com.binarywang.wxjava.store.bean.compass.shop.ShopSaleProfileDataResponse;
import com.binarywang.wxjava.store.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 微信小店 罗盘商家版 服务实现
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Slf4j
public class WxStoreCompassShopServiceImpl implements WxStoreCompassShopService {

  /**
   * 微信商店服务
   */
  private final BaseWxStoreServiceImpl<?, ?> shopService;

  public WxStoreCompassShopServiceImpl(BaseWxStoreServiceImpl<?, ?> shopService) {this.shopService = shopService;}

  @Override
  public ShopOverallResponse getShopOverall(String ds) throws WxErrorException {
    CompassFinderBaseParam param = new CompassFinderBaseParam(ds);
    String resJson = shopService.post(GET_SHOP_OVERALL_URL, param);
    return ResponseUtils.decode(resJson, ShopOverallResponse.class);
  }

  @Override
  public FinderAuthListResponse getFinderAuthorizationList() throws WxErrorException {
    String resJson = shopService.post(FINDER_AUTH_LIST_URL, "{}");
    return ResponseUtils.decode(resJson, FinderAuthListResponse.class);
  }

  @Override
  public FinderListResponse getFinderList(String ds) throws WxErrorException {
    CompassFinderBaseParam param = new CompassFinderBaseParam(ds);
    String resJson = shopService.post(FINDER_LIST_URL, param);
    return ResponseUtils.decode(resJson, FinderListResponse.class);
  }

  @Override
  public FinderOverallResponse getFinderOverall(String ds) throws WxErrorException {
    CompassFinderBaseParam param = new CompassFinderBaseParam(ds);
    String resJson = shopService.post(GET_FINDER_OVERALL_URL, param);
    return ResponseUtils.decode(resJson, FinderOverallResponse.class);
  }

  @Override
  public FinderProductListResponse getFinderProductList(String ds, String finderId) throws WxErrorException {
    CompassFinderIdParam param = new CompassFinderIdParam(ds, finderId);
    String resJson = shopService.post(GET_FINDER_PRODUCT_LIST_URL, param);
    return ResponseUtils.decode(resJson, FinderProductListResponse.class);
  }

  @Override
  public FinderProductOverallResponse getFinderProductOverall(String ds, String finderId) throws WxErrorException {
    CompassFinderIdParam param = new CompassFinderIdParam(ds, finderId);
    String resJson = shopService.post(GET_FINDER_PRODUCT_OVERALL_URL, param);
    return ResponseUtils.decode(resJson, FinderProductOverallResponse.class);
  }

  @Override
  public ShopLiveListResponse getShopLiveList(String ds, String finderId) throws WxErrorException {
    CompassFinderIdParam param = new CompassFinderIdParam(ds, finderId);
    String resJson = shopService.post(GET_LIVE_LIST_URL, param);
    return ResponseUtils.decode(resJson, ShopLiveListResponse.class);
  }

  @Override
  public ShopProductDataResponse getShopProductData(String ds, String productId) throws WxErrorException {
    ShopProductDataParam param = new ShopProductDataParam(ds, productId);
    String resJson = shopService.post(GET_SHOP_PRODUCT_DATA_URL, param);
    return ResponseUtils.decode(resJson, ShopProductDataResponse.class);
  }

  @Override
  public ShopProductListResponse getShopProductList(String ds) throws WxErrorException {
    CompassFinderBaseParam param = new CompassFinderBaseParam(ds);
    String resJson = shopService.post(GET_SHOP_PRODUCT_LIST_URL, param);
    return ResponseUtils.decode(resJson, ShopProductListResponse.class);
  }

  @Override
  public ShopSaleProfileDataResponse getShopSaleProfileData(String ds, Integer type) throws WxErrorException {
    ShopSaleProfileDataParam param = new ShopSaleProfileDataParam(ds, type);
    String resJson = shopService.post(GET_SHOP_SALE_PROFILE_DATA_URL, param);
    return ResponseUtils.decode(resJson, ShopSaleProfileDataResponse.class);
  }
}
