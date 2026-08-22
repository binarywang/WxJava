package me.chanjar.weixin.channel.api.impl;

import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Spu.SPU_GET_STOCK_BATCH_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Spu.SPU_GET_STOCK_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.Spu.SPU_UPDATE_STOCK_URL;

import java.util.List;
import me.chanjar.weixin.channel.api.WxChannelProductStockService;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.product.SkuStockBatchParam;
import me.chanjar.weixin.channel.bean.product.SkuStockBatchResponse;
import me.chanjar.weixin.channel.bean.product.SkuStockParam;
import me.chanjar.weixin.channel.bean.product.SkuStockResponse;
import me.chanjar.weixin.channel.util.JsonUtils;
import me.chanjar.weixin.channel.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 微信小店商品库存服务实现。
 */
public class WxChannelProductStockServiceImpl implements WxChannelProductStockService {

  private final BaseWxChannelServiceImpl<?, ?> shopService;

  public WxChannelProductStockServiceImpl(BaseWxChannelServiceImpl<?, ?> shopService) {
    this.shopService = shopService;
  }

  @Override
  public WxChannelBaseResponse updateStock(String productId, String skuId, Integer diffType, Integer num)
    throws WxErrorException {
    SkuStockParam param = new SkuStockParam(productId, skuId, diffType, num);
    String reqJson = JsonUtils.encode(param);
    String resJson = shopService.post(SPU_UPDATE_STOCK_URL, reqJson);
    return ResponseUtils.decode(resJson, WxChannelBaseResponse.class);
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
}
