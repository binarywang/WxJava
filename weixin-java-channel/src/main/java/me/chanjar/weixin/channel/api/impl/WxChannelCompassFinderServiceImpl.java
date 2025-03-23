package me.chanjar.weixin.channel.api.impl;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.channel.api.WxChannelCompassFinderService;
import me.chanjar.weixin.channel.bean.compass.CompassFinderBaseParam;
import me.chanjar.weixin.channel.bean.compass.finder.*;
import me.chanjar.weixin.channel.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;

import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.CompassFinder.*;

/**
 * 视频号助手 罗盘达人版服务实现
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Slf4j
public class WxChannelCompassFinderServiceImpl implements WxChannelCompassFinderService {

  /**
   * 微信商店服务
   */
  private final BaseWxChannelServiceImpl shopService;

  public WxChannelCompassFinderServiceImpl(BaseWxChannelServiceImpl shopService) {this.shopService = shopService;}

  @Override
  public OverallResponse getOverall(String ds) throws WxErrorException {
    CompassFinderBaseParam param = new CompassFinderBaseParam(ds);
    String resJson = shopService.post(GET_OVERALL_URL, param);
    return ResponseUtils.decode(resJson, OverallResponse.class);
  }

  @Override
  public ProductDataResponse getProductData(String ds, String productId) throws WxErrorException {
    ProductDataParam param = new ProductDataParam(ds, productId);
    String resJson = shopService.post(GET_PRODUCT_DATA_URL, param);
    return ResponseUtils.decode(resJson, ProductDataResponse.class);
  }

  @Override
  public ProductListResponse getProductList(String ds) throws WxErrorException {
    CompassFinderBaseParam param = new CompassFinderBaseParam(ds);
    String resJson = shopService.post(GET_PRODUCT_LIST_URL, param);
    return ResponseUtils.decode(resJson, ProductListResponse.class);
  }

  @Override
  public SaleProfileDataResponse getSaleProfileData(String ds, Integer type) throws WxErrorException {
    SaleProfileDataParam param = new SaleProfileDataParam(ds, type);
    String resJson = shopService.post(GET_SALE_PROFILE_DATA_URL, param);
    return ResponseUtils.decode(resJson, SaleProfileDataResponse.class);
  }

}
