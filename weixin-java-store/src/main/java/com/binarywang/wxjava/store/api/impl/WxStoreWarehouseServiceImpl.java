package com.binarywang.wxjava.store.api.impl;


import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Warehouse.ADD_COVER_AREA_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Warehouse.ADD_WAREHOUSE_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Warehouse.DELETE_COVER_AREA_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Warehouse.GET_WAREHOUSE_PRIORITY_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Warehouse.GET_WAREHOUSE_STOCK_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Warehouse.GET_WAREHOUSE_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Warehouse.LIST_WAREHOUSE_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Warehouse.SET_WAREHOUSE_PRIORITY_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Warehouse.UPDATE_WAREHOUSE_STOCK_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Warehouse.UPDATE_WAREHOUSE_URL;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import com.binarywang.wxjava.store.api.WxStoreWarehouseService;
import com.binarywang.wxjava.store.bean.base.StreamPageParam;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;
import com.binarywang.wxjava.store.bean.warehouse.LocationPriorityResponse;
import com.binarywang.wxjava.store.bean.warehouse.PriorityLocationParam;
import com.binarywang.wxjava.store.bean.warehouse.StockGetParam;
import com.binarywang.wxjava.store.bean.warehouse.UpdateLocationParam;
import com.binarywang.wxjava.store.bean.warehouse.WarehouseIdsResponse;
import com.binarywang.wxjava.store.bean.warehouse.WarehouseLocation;
import com.binarywang.wxjava.store.bean.warehouse.WarehouseLocationParam;
import com.binarywang.wxjava.store.bean.warehouse.WarehouseParam;
import com.binarywang.wxjava.store.bean.warehouse.WarehouseResponse;
import com.binarywang.wxjava.store.bean.warehouse.WarehouseStockParam;
import com.binarywang.wxjava.store.bean.warehouse.WarehouseStockResponse;
import com.binarywang.wxjava.store.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 微信小店 区域仓库服务实现
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Slf4j
public class WxStoreWarehouseServiceImpl implements WxStoreWarehouseService {

  /** 微信商店服务 */
  private final BaseWxStoreServiceImpl<?, ?> shopService;

  public WxStoreWarehouseServiceImpl(BaseWxStoreServiceImpl<?, ?> shopService) {
    this.shopService = shopService;
  }

  @Override
  public WxStoreBaseResponse createWarehouse(WarehouseParam param) throws WxErrorException {
    String resJson = shopService.post(ADD_WAREHOUSE_URL, param);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  @Override
  public WarehouseIdsResponse listWarehouse(Integer pageSize, String nextKey) throws WxErrorException {
    StreamPageParam param = new StreamPageParam(pageSize, nextKey);
    String resJson = shopService.post(LIST_WAREHOUSE_URL, param);
    return ResponseUtils.decode(resJson, WarehouseIdsResponse.class);
  }

  @Override
  public WarehouseResponse getWarehouse(String outWarehouseId) throws WxErrorException {
    String reqJson = "{\"out_warehouse_id\":\"" + outWarehouseId + "\"}";
    String resJson = shopService.post(GET_WAREHOUSE_URL, reqJson);
    return ResponseUtils.decode(resJson, WarehouseResponse.class);
  }

  @Override
  public WxStoreBaseResponse updateWarehouse(String outWarehouseId, String name, String intro)
    throws WxErrorException {
    String reqJson = "{\"out_warehouse_id\":\"" + outWarehouseId +
      "\",\"name\":\"" + name + "\",\"intro\":\"" + intro + "\"}";
    String resJson = shopService.post(UPDATE_WAREHOUSE_URL, reqJson);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  @Override
  public WxStoreBaseResponse addWarehouseArea(String outWarehouseId, List<WarehouseLocation> coverLocations)
    throws WxErrorException {
    UpdateLocationParam param = new UpdateLocationParam(outWarehouseId, coverLocations);
    String resJson = shopService.post(ADD_COVER_AREA_URL, param);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  @Override
  public WxStoreBaseResponse deleteWarehouseArea(String outWarehouseId, List<WarehouseLocation> coverLocations)
    throws WxErrorException {
    UpdateLocationParam param = new UpdateLocationParam(outWarehouseId, coverLocations);
    String resJson = shopService.post(DELETE_COVER_AREA_URL, param);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);

  }

  @Override
  public WxStoreBaseResponse setWarehousePriority(PriorityLocationParam param) throws WxErrorException {
    String resJson = shopService.post(SET_WAREHOUSE_PRIORITY_URL, param);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);

  }

  @Override
  public LocationPriorityResponse getWarehousePriority(Integer addressId1, Integer addressId2, Integer addressId3,
    Integer addressId4) throws WxErrorException {
    WarehouseLocationParam param = new WarehouseLocationParam(addressId1, addressId2, addressId3, addressId4);
    String resJson = shopService.post(GET_WAREHOUSE_PRIORITY_URL, param);
    return ResponseUtils.decode(resJson, LocationPriorityResponse.class);
  }

  @Override
  public WxStoreBaseResponse updateWarehouseStock(WarehouseStockParam param) throws WxErrorException {
    String resJson = shopService.post(UPDATE_WAREHOUSE_STOCK_URL, param);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  @Override
  public WarehouseStockResponse getWarehouseStock(String productId, String skuId, String outWarehouseId)
    throws WxErrorException {
    StockGetParam param = new StockGetParam(productId, skuId, outWarehouseId);
    String resJson = shopService.post(GET_WAREHOUSE_STOCK_URL, param);
    return ResponseUtils.decode(resJson, WarehouseStockResponse.class);
  }
}
