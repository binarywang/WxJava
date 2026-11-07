package com.binarywang.wxjava.store.api;

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
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 微信小店代发管理服务。
 *
 * @author <a href="https://github.com/github-copilot">GitHub Copilot</a>
 * @see <a href="https://developers.weixin.qq.com/doc/channels/API/supplier/">代发管理接口文档</a>
 */
public interface WxStoreSupplierService {

  /** 获取供货商列表。 */
  SupplierListResponse getSupplierList() throws WxErrorException;

  /**
   * 获取供货商列表。
   *
   * @param pageSize 每页数量
   * @param nextKey  由上次请求返回，记录翻页的上下文。传入时会从上次返回的结果往后翻一页
   * @return 供货商列表响应
   * @throws WxErrorException 异常
   */
  SupplierListResponse getSupplierList(Integer pageSize, String nextKey) throws WxErrorException;

  /** 获取分配方式。 */
  DistributeTypeResponse getDistribute() throws WxErrorException;

  /** 设置全店订单手动分配。 */
  WxStoreBaseResponse setManuallyDistribute() throws WxErrorException;

  /** 设置全店订单自动分配。 */
  WxStoreBaseResponse setAllDistribute(String supplierId) throws WxErrorException;

  /** 设置按商品自动分配。 */
  WxStoreBaseResponse setProductDistribute(ProductDistributeRequest req) throws WxErrorException;

  /** 获取商品对应的自动分配供货商。 */
  SupplierInfoResponse getProductDefaultDistribute(String productId) throws WxErrorException;

  /** 获取按商品自动分配的商品列表。 */
  ProductListResponse getProductList(String supplierId) throws WxErrorException;

  /** 分配订单代发。 */
  DropshipResponse assignOrder(DropshipAssignRequest req) throws WxErrorException;

  /** 取消分配代发单。 */
  WxStoreBaseResponse cancelDropship(String orderId) throws WxErrorException;

  /** 查询代发单详情。 */
  DropshipDetailResponse getDropship(String orderId) throws WxErrorException;

  /** 拉取代发单列表。 */
  DropshipListResponse listDropship(DropshipListRequest req) throws WxErrorException;

  /** 搜索代发单。 */
  DropshipListResponse searchDropship(DropshipSearchRequest req) throws WxErrorException;
}
