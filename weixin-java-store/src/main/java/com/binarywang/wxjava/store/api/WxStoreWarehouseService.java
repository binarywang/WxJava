package com.binarywang.wxjava.store.api;


import java.util.List;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;
import com.binarywang.wxjava.store.bean.warehouse.LocationPriorityResponse;
import com.binarywang.wxjava.store.bean.warehouse.PriorityLocationParam;
import com.binarywang.wxjava.store.bean.warehouse.WarehouseIdsResponse;
import com.binarywang.wxjava.store.bean.warehouse.WarehouseLocation;
import com.binarywang.wxjava.store.bean.warehouse.WarehouseParam;
import com.binarywang.wxjava.store.bean.warehouse.WarehouseResponse;
import com.binarywang.wxjava.store.bean.warehouse.WarehouseStockParam;
import com.binarywang.wxjava.store.bean.warehouse.WarehouseStockResponse;
import me.chanjar.weixin.common.error.WxErrorException;


/**
 * 微信小店 区域仓库服务
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
public interface WxStoreWarehouseService {

  /**
   * 创建仓库
   *
   * @param param 仓库信息
   * @return 响应
   *
   * @throws WxErrorException 异常
   */
  WxStoreBaseResponse createWarehouse(WarehouseParam param) throws WxErrorException;

  /**
   * 查询仓库列表
   *
   * @param pageSize 每页数量（最大不超过10）
   * @param nextKey  由上次请求返回，记录翻页的上下文。传入时会从上次返回的结果往后翻一页，不传默认拉取第一页数据
   * @return 响应
   *
   * @throws WxErrorException 异常
   */
  WarehouseIdsResponse listWarehouse(Integer pageSize, String nextKey) throws WxErrorException;

  /**
   * 获取仓库详情
   *
   * @param outWarehouseId 外部仓库ID
   * @return 响应
   *
   * @throws WxErrorException 异常
   */
  WarehouseResponse getWarehouse(String outWarehouseId) throws WxErrorException;

  /**
   * 修改仓库详情
   *
   * @param outWarehouseId 外部仓库ID
   * @param name           仓库名称
   * @param intro          仓库介绍
   * @return 响应
   *
   * @throws WxErrorException 异常
   */
  WxStoreBaseResponse updateWarehouse(String outWarehouseId, String name, String intro) throws WxErrorException;

  /**
   * 批量增加覆盖区域
   *
   * @param outWarehouseId 外部仓库ID
   * @param coverLocations 覆盖区域
   * @return 响应
   *
   * @throws WxErrorException 异常
   */
  WxStoreBaseResponse addWarehouseArea(String outWarehouseId, List<WarehouseLocation> coverLocations)
    throws WxErrorException;

  /**
   * 批量删除覆盖区域
   *
   * @param outWarehouseId 外部仓库ID
   * @param coverLocations 覆盖区域
   * @return 响应
   *
   * @throws WxErrorException 异常
   */
  WxStoreBaseResponse deleteWarehouseArea(String outWarehouseId, List<WarehouseLocation> coverLocations)
    throws WxErrorException;

  /**
   * 设置指定地址下的仓的优先级
   *
   * @param param 参数
   * @return 响应
   *
   * @throws WxErrorException 异常
   */
  WxStoreBaseResponse setWarehousePriority(PriorityLocationParam param) throws WxErrorException;

  /**
   * 获取指定地址下的仓的优先级
   *
   * @param addressId1 省份地址编码
   * @param addressId2 市地址编码
   * @param addressId3 区地址编码
   * @param addressId4 街道地址编码
   * @return 仓的优先级
   *
   * @throws WxErrorException 异常
   */
  LocationPriorityResponse getWarehousePriority(Integer addressId1, Integer addressId2, Integer addressId3,
    Integer addressId4) throws WxErrorException;

  /**
   * 更新区域仓库存数量
   *
   * @param param 参数
   * @return 响应
   *
   * @throws WxErrorException 异常
   */
  WxStoreBaseResponse updateWarehouseStock(WarehouseStockParam param) throws WxErrorException;

  /**
   * 获取区域仓库存数量
   *
   * @param productId      商品ID
   * @param outWarehouseId 外部仓库ID
   * @param skuId          商品skuId
   * @return 响应
   *
   * @throws WxErrorException 异常
   */
  WarehouseStockResponse getWarehouseStock(String productId, String skuId, String outWarehouseId)
    throws WxErrorException;
}
