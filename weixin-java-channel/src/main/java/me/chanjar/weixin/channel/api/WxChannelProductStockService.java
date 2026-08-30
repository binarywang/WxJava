package me.chanjar.weixin.channel.api;

import java.util.List;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.product.SkuStockBatchResponse;
import me.chanjar.weixin.channel.bean.product.SkuStockResponse;
import me.chanjar.weixin.channel.bean.product.stock.StockFlowParam;
import me.chanjar.weixin.channel.bean.product.stock.StockFlowResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 微信小店商品库存服务。
 */
@Deprecated
public interface WxChannelProductStockService {

  /**
   * 更新商品库存。
   *
   * @param productId 商品ID
   * @param skuId 商品sku_id
   * @param diffType 修改类型 1增加 2减少 3设置
   * @param num 增加、减少或者设置的库存值
   * @return 操作响应
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse updateStock(String productId, String skuId, Integer diffType, Integer num)
    throws WxErrorException;

  /**
   * 获取商品实时库存。
   *
   * @param productId 商品ID
   * @param skuId 商品sku_id
   * @return 库存响应
   * @throws WxErrorException 异常
   */
  SkuStockResponse getSkuStock(String productId, String skuId) throws WxErrorException;

  /**
   * 批量获取库存信息。
   *
   * @param productIds 商品ID列表，单次请求不超过50个
   * @return 库存信息
   * @throws WxErrorException 异常
   */
  SkuStockBatchResponse getSkuStockBatch(List<String> productIds) throws WxErrorException;

  /**
   * 获取商品库存流水。
   *
   * @param param 库存流水查询参数
   * @return 库存流水响应
   * @throws WxErrorException 异常
   */
  StockFlowResponse getStockFlow(StockFlowParam param) throws WxErrorException;
}
