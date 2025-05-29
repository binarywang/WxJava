package me.chanjar.weixin.channel.api.impl;

import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.League.GET_SUPPLIER_BALANCE_FLOW_DETAIL_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.League.GET_SUPPLIER_BALANCE_FLOW_LIST_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.League.GET_SUPPLIER_BALANCE_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.League.GET_SUPPLIER_ITEM_LIST_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.League.GET_SUPPLIER_ITEM_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.League.GET_SUPPLIER_ORDER_LIST_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.League.GET_SUPPLIER_ORDER_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.League.GET_SUPPLIER_SHOP_LIST_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.League.GET_SUPPLIER_SHOP_URL;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.channel.api.WxLeagueSupplierService;
import me.chanjar.weixin.channel.bean.base.StreamPageParam;
import me.chanjar.weixin.channel.bean.league.supplier.CommissionOrderListParam;
import me.chanjar.weixin.channel.bean.league.supplier.CommissionOrderListResponse;
import me.chanjar.weixin.channel.bean.league.supplier.CommissionOrderResponse;
import me.chanjar.weixin.channel.bean.league.supplier.CoopProductDetailParam;
import me.chanjar.weixin.channel.bean.league.supplier.CoopProductListParam;
import me.chanjar.weixin.channel.bean.league.supplier.CoopProductListResponse;
import me.chanjar.weixin.channel.bean.league.supplier.CoopProductResponse;
import me.chanjar.weixin.channel.bean.league.supplier.FlowListParam;
import me.chanjar.weixin.channel.bean.league.supplier.ShopDetailResponse;
import me.chanjar.weixin.channel.bean.league.supplier.ShopListResponse;
import me.chanjar.weixin.channel.bean.league.supplier.SupplierBalanceResponse;
import me.chanjar.weixin.channel.bean.league.supplier.SupplierFlowDetailResponse;
import me.chanjar.weixin.channel.bean.league.supplier.SupplierFlowListResponse;
import me.chanjar.weixin.channel.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 视频号小店 优选联盟 团长数据服务
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Slf4j
public class WxLeagueSupplierServiceImpl implements WxLeagueSupplierService {

  /** 微信商店服务 */
  private final BaseWxChannelServiceImpl<?, ?> shopService;

  public WxLeagueSupplierServiceImpl(BaseWxChannelServiceImpl<?, ?> shopService) {
    this.shopService = shopService;
  }

  @Override
  public SupplierBalanceResponse getBalanceInfo() throws WxErrorException {
    String resJson = shopService.post(GET_SUPPLIER_BALANCE_URL, "{}");
    return ResponseUtils.decode(resJson, SupplierBalanceResponse.class);
  }

  @Override
  public SupplierFlowDetailResponse getFlowDetail(String flowId) throws WxErrorException {
    String reqJson = "{\"flow_id\":\"" + flowId + "\"}";
    String resJson = shopService.post(GET_SUPPLIER_BALANCE_FLOW_DETAIL_URL, reqJson);
    return ResponseUtils.decode(resJson, SupplierFlowDetailResponse.class);
  }

  @Override
  public SupplierFlowListResponse getFlowList(FlowListParam param) throws WxErrorException {
    String resJson = shopService.post(GET_SUPPLIER_BALANCE_FLOW_LIST_URL, param);
    return ResponseUtils.decode(resJson, SupplierFlowListResponse.class);
  }

  @Override
  public CoopProductResponse getProductDetail(String productId, String appId) throws WxErrorException {
    CoopProductDetailParam param = new CoopProductDetailParam(productId, appId);
    String resJson = shopService.post(GET_SUPPLIER_ITEM_URL, param);
    return ResponseUtils.decode(resJson, CoopProductResponse.class);
  }

  @Override
  public CoopProductListResponse getProductList(String appid, Integer pageSize, String nextKey)
    throws WxErrorException {
    CoopProductListParam param = new CoopProductListParam(appid, pageSize, nextKey);
    String resJson = shopService.post(GET_SUPPLIER_ITEM_LIST_URL, param);
    return ResponseUtils.decode(resJson, CoopProductListResponse.class);
  }

  @Override
  public CommissionOrderResponse getCommissionOrder(String orderId, String skuId) throws WxErrorException {
    String reqJson = "{\"order_id\":\"" + orderId + "\",\"sku_id\":\"" + skuId + "\"}";
    String resJson = shopService.post(GET_SUPPLIER_ORDER_URL, reqJson);
    return ResponseUtils.decode(resJson, CommissionOrderResponse.class);
  }

  @Override
  public CommissionOrderListResponse getCommissionOrderList(CommissionOrderListParam param) throws WxErrorException {
    String resJson = shopService.post(GET_SUPPLIER_ORDER_LIST_URL, param);
    return ResponseUtils.decode(resJson, CommissionOrderListResponse.class);
  }

  @Override
  public ShopDetailResponse getShopDetail(String appid) throws WxErrorException {
    String reqJson = "{\"appid\":\"" + appid + "\"}";
    String resJson = shopService.post(GET_SUPPLIER_SHOP_URL, reqJson);
    return ResponseUtils.decode(resJson, ShopDetailResponse.class);
  }

  @Override
  public ShopListResponse getShopList(Integer pageSize, String nextKey) throws WxErrorException {
    StreamPageParam param = new StreamPageParam(pageSize, nextKey);
    String resJson = shopService.post(GET_SUPPLIER_SHOP_LIST_URL, param);
    return ResponseUtils.decode(resJson, ShopListResponse.class);
  }
}
