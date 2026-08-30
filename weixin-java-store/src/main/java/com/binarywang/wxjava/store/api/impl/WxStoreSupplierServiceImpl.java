package com.binarywang.wxjava.store.api.impl;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import com.binarywang.wxjava.store.api.WxStoreSupplierService;
import com.binarywang.wxjava.store.bean.base.StreamPageParam;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;
import com.binarywang.wxjava.store.bean.supplier.DistributeTypeResponse;
import com.binarywang.wxjava.store.bean.supplier.DropshipAssignRequest;
import com.binarywang.wxjava.store.bean.supplier.DropshipDetailResponse;
import com.binarywang.wxjava.store.bean.supplier.DropshipListRequest;
import com.binarywang.wxjava.store.bean.supplier.DropshipListResponse;
import com.binarywang.wxjava.store.bean.supplier.DropshipResponse;
import com.binarywang.wxjava.store.bean.supplier.DropshipSearchRequest;
import com.binarywang.wxjava.store.bean.supplier.ProductDistributeRequest;
import com.binarywang.wxjava.store.bean.supplier.ProductListResponse;
import com.binarywang.wxjava.store.bean.supplier.SupplierInfoResponse;
import com.binarywang.wxjava.store.bean.supplier.SupplierListResponse;
import com.binarywang.wxjava.store.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonHelper;

import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Supplier.ASSIGN_DROPSHIP_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Supplier.CANCEL_DROPSHIP_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Supplier.GET_DISTRIBUTE_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Supplier.GET_DROPSHIP_LIST_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Supplier.GET_DROPSHIP_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Supplier.GET_PRODUCT_DEFAULT_DISTRIBUTE_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Supplier.GET_PRODUCT_LIST_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Supplier.GET_SUPPLIER_LIST_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Supplier.SEARCH_DROPSHIP_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Supplier.SET_ALL_DISTRIBUTION_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Supplier.SET_MANUALLY_DISTRIBUTE_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Supplier.SET_PRODUCT_DISTRIBUTE_URL;

/**
 * 微信小店代发管理服务。
 *
 * @author <a href="https://github.com/github-copilot">GitHub Copilot</a>
 */
@Slf4j
public class WxStoreSupplierServiceImpl implements WxStoreSupplierService {

  private final BaseWxStoreServiceImpl<?, ?> shopService;

  public WxStoreSupplierServiceImpl(BaseWxStoreServiceImpl<?, ?> shopService) {
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
  public WxStoreBaseResponse setManuallyDistribute() throws WxErrorException {
    String respJson = shopService.post(SET_MANUALLY_DISTRIBUTE_URL, "{}");
    return ResponseUtils.decode(respJson, WxStoreBaseResponse.class);
  }

  @Override
  public WxStoreBaseResponse setAllDistribute(String supplierId) throws WxErrorException {
    JsonObject req = GsonHelper.buildJsonObject("supplier_id", supplierId);
    String respJson = shopService.post(SET_ALL_DISTRIBUTION_URL, req);
    return ResponseUtils.decode(respJson, WxStoreBaseResponse.class);
  }

  @Override
  public WxStoreBaseResponse setProductDistribute(ProductDistributeRequest req) throws WxErrorException {
    String respJson = shopService.post(SET_PRODUCT_DISTRIBUTE_URL, req);
    return ResponseUtils.decode(respJson, WxStoreBaseResponse.class);
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
  public WxStoreBaseResponse cancelDropship(String orderId) throws WxErrorException {
    JsonObject req = GsonHelper.buildJsonObject("order_id", orderId);
    String respJson = shopService.post(CANCEL_DROPSHIP_URL, req);
    return ResponseUtils.decode(respJson, WxStoreBaseResponse.class);
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
