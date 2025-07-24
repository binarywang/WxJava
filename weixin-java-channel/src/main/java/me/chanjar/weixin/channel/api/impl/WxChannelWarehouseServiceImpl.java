package me.chanjar.weixin.channel.api.impl;


import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Warehouse.ADD_COVER_AREA_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Warehouse.ADD_WAREHOUSE_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Warehouse.DELETE_COVER_AREA_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Warehouse.GET_WAREHOUSE_PRIORITY_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Warehouse.GET_WAREHOUSE_STOCK_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Warehouse.GET_WAREHOUSE_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Warehouse.LIST_WAREHOUSE_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Warehouse.SET_WAREHOUSE_PRIORITY_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Warehouse.UPDATE_WAREHOUSE_STOCK_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Warehouse.UPDATE_WAREHOUSE_URL;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.channel.api.WxChannelWarehouseService;
import me.chanjar.weixin.channel.bean.base.StreamPageParam;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.warehouse.LocationPriorityResponse;
import me.chanjar.weixin.channel.bean.warehouse.PriorityLocationParam;
import me.chanjar.weixin.channel.bean.warehouse.StockGetParam;
import me.chanjar.weixin.channel.bean.warehouse.UpdateLocationParam;
import me.chanjar.weixin.channel.bean.warehouse.WarehouseIdsResponse;
import me.chanjar.weixin.channel.bean.warehouse.WarehouseLocation;
import me.chanjar.weixin.channel.bean.warehouse.WarehouseLocationParam;
import me.chanjar.weixin.channel.bean.warehouse.WarehouseParam;
import me.chanjar.weixin.channel.bean.warehouse.WarehouseResponse;
import me.chanjar.weixin.channel.bean.warehouse.WarehouseStockParam;
import me.chanjar.weixin.channel.bean.warehouse.WarehouseStockResponse;
import me.chanjar.weixin.channel.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 视频号小店 区域仓库服务实现
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Slf4j
public class WxChannelWarehouseServiceImpl implements WxChannelWarehouseService {

  /** 微信商店服务 */
  private final BaseWxChannelServiceImpl<?, ?> shopService;

  public WxChannelWarehouseServiceImpl(BaseWxChannelServiceImpl<?, ?> shopService) {
    this.shopService = shopService;
  }

  @Override
  public WxChannelBaseResponse createWarehouse(WarehouseParam param) throws WxErrorException {
    String resJson = shopService.post(ADD_WAREHOUSE_URL, param);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
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
  public WxChannelBaseResponse updateWarehouse(String outWarehouseId, String name, String intro)
    throws WxErrorException {
    String reqJson = "{\"out_warehouse_id\":\"" + outWarehouseId +
      "\",\"name\":\"" + name + "\",\"intro\":\"" + intro + "\"}";
    String resJson = shopService.post(UPDATE_WAREHOUSE_URL, reqJson);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public WxChannelBaseResponse addWarehouseArea(String outWarehouseId, List<WarehouseLocation> coverLocations)
    throws WxErrorException {
    UpdateLocationParam param = new UpdateLocationParam(outWarehouseId, coverLocations);
    String resJson = shopService.post(ADD_COVER_AREA_URL, param);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public WxChannelBaseResponse deleteWarehouseArea(String outWarehouseId, List<WarehouseLocation> coverLocations)
    throws WxErrorException {
    UpdateLocationParam param = new UpdateLocationParam(outWarehouseId, coverLocations);
    String resJson = shopService.post(DELETE_COVER_AREA_URL, param);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);

  }

  @Override
  public WxChannelBaseResponse setWarehousePriority(PriorityLocationParam param) throws WxErrorException {
    String resJson = shopService.post(SET_WAREHOUSE_PRIORITY_URL, param);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);

  }

  @Override
  public LocationPriorityResponse getWarehousePriority(Integer addressId1, Integer addressId2, Integer addressId3,
    Integer addressId4) throws WxErrorException {
    WarehouseLocationParam param = new WarehouseLocationParam(addressId1, addressId2, addressId3, addressId4);
    String resJson = shopService.post(GET_WAREHOUSE_PRIORITY_URL, param);
    return ResponseUtils.decode(resJson, LocationPriorityResponse.class);
  }

  @Override
  public WxChannelBaseResponse updateWarehouseStock(WarehouseStockParam param) throws WxErrorException {
    String resJson = shopService.post(UPDATE_WAREHOUSE_STOCK_URL, param);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
  }

  @Override
  public WarehouseStockResponse getWarehouseStock(String productId, String skuId, String outWarehouseId)
    throws WxErrorException {
    StockGetParam param = new StockGetParam(productId, skuId, outWarehouseId);
    String resJson = shopService.post(GET_WAREHOUSE_STOCK_URL, param);
    return ResponseUtils.decode(resJson, WarehouseStockResponse.class);
  }
}
