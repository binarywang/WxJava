package me.chanjar.weixin.channel.api.impl;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.channel.api.WxChannelSupplierService;
import me.chanjar.weixin.channel.bean.base.StreamPageParam;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.supplier.DistributeTypeResponse;
import me.chanjar.weixin.channel.bean.supplier.DropshipAssignRequest;
import me.chanjar.weixin.channel.bean.supplier.DropshipDetailResponse;
import me.chanjar.weixin.channel.bean.supplier.DropshipListRequest;
import me.chanjar.weixin.channel.bean.supplier.DropshipListResponse;
import me.chanjar.weixin.channel.bean.supplier.DropshipResponse;
import me.chanjar.weixin.channel.bean.supplier.DropshipSearchRequest;
import me.chanjar.weixin.channel.bean.supplier.ProductDistributeRequest;
import me.chanjar.weixin.channel.bean.supplier.ProductListResponse;
import me.chanjar.weixin.channel.bean.supplier.SupplierInfoResponse;
import me.chanjar.weixin.channel.bean.supplier.SupplierListResponse;
import me.chanjar.weixin.channel.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonHelper;

import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Supplier.ASSIGN_DROPSHIP_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Supplier.CANCEL_DROPSHIP_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Supplier.GET_DISTRIBUTE_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Supplier.GET_DROPSHIP_LIST_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Supplier.GET_DROPSHIP_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Supplier.GET_PRODUCT_DEFAULT_DISTRIBUTE_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Supplier.GET_PRODUCT_LIST_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Supplier.GET_SUPPLIER_LIST_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Supplier.SEARCH_DROPSHIP_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Supplier.SET_ALL_DISTRIBUTION_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Supplier.SET_MANUALLY_DISTRIBUTE_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Supplier.SET_PRODUCT_DISTRIBUTE_URL;

/**
 * 视频号小店代发管理服务。
 *
 * @author <a href="https://github.com/github-copilot">GitHub Copilot</a>
 */
@Slf4j
public class WxChannelSupplierServiceImpl implements WxChannelSupplierService {

  private final BaseWxChannelServiceImpl<?, ?> shopService;

  public WxChannelSupplierServiceImpl(BaseWxChannelServiceImpl<?, ?> shopService) {
    this.shopService = shopService;
  }

  @Override
  public SupplierListResponse getSupplierList() throws WxErrorException {
    return getSupplierList(null, null);
  }

  @Override
  public SupplierListResponse getSupplierList(Integer pageSize, String nextKey) throws WxErrorException {
    StreamPageParam param = new StreamPageParam(pageSize, nextKey);
    String respJson = shopService.post(GET_SUPPLIER_LIST_URL, param);
    return ResponseUtils.decode(respJson, SupplierListResponse.class);
  }

  @Override
  public DistributeTypeResponse getDistribute() throws WxErrorException {
    String respJson = shopService.post(GET_DISTRIBUTE_URL, "{}");
    return ResponseUtils.decode(respJson, DistributeTypeResponse.class);
  }

  @Override
  public WxChannelBaseResponse setManuallyDistribute() throws WxErrorException {
    String respJson = shopService.post(SET_MANUALLY_DISTRIBUTE_URL, "{}");
    return ResponseUtils.decode(respJson, WxChannelBaseResponse.class);
  }

  @Override
  public WxChannelBaseResponse setAllDistribute(String supplierId) throws WxErrorException {
    JsonObject req = GsonHelper.buildJsonObject("supplier_id", supplierId);
    String respJson = shopService.post(SET_ALL_DISTRIBUTION_URL, req);
    return ResponseUtils.decode(respJson, WxChannelBaseResponse.class);
  }

  @Override
  public WxChannelBaseResponse setProductDistribute(ProductDistributeRequest req) throws WxErrorException {
    String respJson = shopService.post(SET_PRODUCT_DISTRIBUTE_URL, req);
    return ResponseUtils.decode(respJson, WxChannelBaseResponse.class);
  }

  @Override
  public SupplierInfoResponse getProductDefaultDistribute(String productId) throws WxErrorException {
    JsonObject req = GsonHelper.buildJsonObject("product_id", productId);
    String respJson = shopService.post(GET_PRODUCT_DEFAULT_DISTRIBUTE_URL, req);
    return ResponseUtils.decode(respJson, SupplierInfoResponse.class);
  }

  @Override
  public ProductListResponse getProductList(String supplierId) throws WxErrorException {
    JsonObject req = GsonHelper.buildJsonObject("supplier_id", supplierId);
    String respJson = shopService.post(GET_PRODUCT_LIST_URL, req);
    return ResponseUtils.decode(respJson, ProductListResponse.class);
  }

  @Override
  public DropshipResponse assignOrder(DropshipAssignRequest req) throws WxErrorException {
    String respJson = shopService.post(ASSIGN_DROPSHIP_URL, req);
    return ResponseUtils.decode(respJson, DropshipResponse.class);
  }

  @Override
  public WxChannelBaseResponse cancelDropship(String orderId) throws WxErrorException {
    JsonObject req = GsonHelper.buildJsonObject("order_id", orderId);
    String respJson = shopService.post(CANCEL_DROPSHIP_URL, req);
    return ResponseUtils.decode(respJson, WxChannelBaseResponse.class);
  }

  @Override
  public DropshipDetailResponse getDropship(String orderId) throws WxErrorException {
    JsonObject req = GsonHelper.buildJsonObject("order_id", orderId);
    String respJson = shopService.post(GET_DROPSHIP_URL, req);
    return ResponseUtils.decode(respJson, DropshipDetailResponse.class);
  }

  @Override
  public DropshipListResponse listDropship(DropshipListRequest req) throws WxErrorException {
    String respJson = shopService.post(GET_DROPSHIP_LIST_URL, req);
    return ResponseUtils.decode(respJson, DropshipListResponse.class);
  }

  @Override
  public DropshipListResponse searchDropship(DropshipSearchRequest req) throws WxErrorException {
    String respJson = shopService.post(SEARCH_DROPSHIP_URL, req);
    return ResponseUtils.decode(respJson, DropshipListResponse.class);
  }
}
