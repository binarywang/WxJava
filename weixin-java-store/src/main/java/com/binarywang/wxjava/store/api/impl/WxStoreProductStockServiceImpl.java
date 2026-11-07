package com.binarywang.wxjava.store.api.impl;

import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.SPU_GET_STOCK_BATCH_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.SPU_GET_STOCK_FLOW_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.SPU_GET_STOCK_URL;
import static com.binarywang.wxjava.store.constant.WxStoreApiUrlConstants.Spu.SPU_UPDATE_STOCK_URL;

import java.util.List;
import com.binarywang.wxjava.store.api.WxStoreProductStockService;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;
import com.binarywang.wxjava.store.bean.product.SkuStockBatchParam;
import com.binarywang.wxjava.store.bean.product.SkuStockBatchResponse;
import com.binarywang.wxjava.store.bean.product.SkuStockParam;
import com.binarywang.wxjava.store.bean.product.SkuStockResponse;
import com.binarywang.wxjava.store.bean.product.stock.StockFlowParam;
import com.binarywang.wxjava.store.bean.product.stock.StockFlowResponse;
import com.binarywang.wxjava.store.util.JsonUtils;
import com.binarywang.wxjava.store.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 微信小店商品库存服务实现。
 */
public class WxStoreProductStockServiceImpl implements WxStoreProductStockService {

  private final BaseWxStoreServiceImpl<?, ?> shopService;

  public WxStoreProductStockServiceImpl(BaseWxStoreServiceImpl<?, ?> shopService) {
    this.shopService = shopService;
  }

  @Override
  public WxStoreBaseResponse updateStock(String productId, String skuId, Integer diffType, Integer num)
    throws WxErrorException {
    SkuStockParam param = new SkuStockParam(productId, skuId, diffType, num);
    String reqJson = JsonUtils.encode(param);
    String resJson = shopService.post(SPU_UPDATE_STOCK_URL, reqJson);
    return ResponseUtils.decode(resJson, WxStoreBaseResponse.class);
  }

  @Override
  public SkuStockResponse getSkuStock(String productId, String skuId) throws WxErrorException {
    String reqJson = "{\"product_id\":\"" + productId + "\",\"sku_id\":\"" + skuId + "\"}";
    String resJson = shopService.post(SPU_GET_STOCK_URL, reqJson);
    return ResponseUtils.decode(resJson, SkuStockResponse.class);
  }

  @Override
  public SkuStockBatchResponse getSkuStockBatch(List<String> productIds) throws WxErrorException {
    SkuStockBatchParam param = new SkuStockBatchParam(productIds);
    String reqJson = JsonUtils.encode(param);
    String resJson = shopService.post(SPU_GET_STOCK_BATCH_URL, reqJson);
    return ResponseUtils.decode(resJson, SkuStockBatchResponse.class);
  }

  @Override
  public StockFlowResponse getStockFlow(StockFlowParam param) throws WxErrorException {
    String reqJson = JsonUtils.encode(param);
    String resJson = shopService.post(SPU_GET_STOCK_FLOW_URL, reqJson);
    return ResponseUtils.decode(resJson, StockFlowResponse.class);
  }
}
