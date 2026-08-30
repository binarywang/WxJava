package com.binarywang.wxjava.store.api;

import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;
import com.binarywang.wxjava.store.bean.product.GiftActivityAddResponse;
import com.binarywang.wxjava.store.bean.product.GiftActivityInfo;
import com.binarywang.wxjava.store.bean.product.GiftProductAddResponse;
import com.binarywang.wxjava.store.bean.product.GiftProductGetResponse;
import com.binarywang.wxjava.store.bean.product.GiftProductInfo;
import com.binarywang.wxjava.store.bean.product.GiftProductListParam;
import com.binarywang.wxjava.store.bean.product.GiftProductListResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 微信小店赠品与买赠活动服务。
 */
public interface WxStoreGiftService {

  /**
   * 添加非卖商品。
   *
   * @param info 赠品信息
   * @return 添加赠品响应
   * @throws WxErrorException 异常
   */
  GiftProductAddResponse addGiftProduct(GiftProductInfo info) throws WxErrorException;

  /**
   * 更新非卖商品。
   *
   * @param info 赠品信息
   * @return 操作响应
   * @throws WxErrorException 异常
   */
  WxStoreBaseResponse updateGiftProduct(GiftProductInfo info) throws WxErrorException;

  /**
   * 在售商品转赠品。
   *
   * @param productId 商品ID
   * @return 操作响应
   * @throws WxErrorException 异常
   */
  WxStoreBaseResponse setProductAsGift(String productId) throws WxErrorException;

  /**
   * 获取赠品。
   *
   * @param productId 赠品商品ID
   * @return 赠品详情响应
   * @throws WxErrorException 异常
   */
  GiftProductGetResponse getGiftProduct(String productId) throws WxErrorException;

  /**
   * 获取赠品列表。
   *
   * @param param 查询参数
   * @return 赠品列表
   * @throws WxErrorException 异常
   */
  GiftProductListResponse listGiftProduct(GiftProductListParam param) throws WxErrorException;

  /**
   * 更新赠品库存。
   *
   * @param productId 赠品商品ID
   * @param skuId 赠品sku_id
   * @param diffType 修改类型 1增加 2减少 3设置
   * @param num 增加、减少或者设置的库存值
   * @return 操作响应
   * @throws WxErrorException 异常
   */
  WxStoreBaseResponse updateGiftStock(String productId, String skuId, Integer diffType, Integer num)
    throws WxErrorException;

  /**
   * 创建赠品活动。
   *
   * @param info 活动信息
   * @return 创建赠品活动响应
   * @throws WxErrorException 异常
   */
  GiftActivityAddResponse addGiftActivity(GiftActivityInfo info) throws WxErrorException;

  /**
   * 删除赠品活动。
   *
   * @param activityId 活动ID
   * @return 操作响应
   * @throws WxErrorException 异常
   */
  WxStoreBaseResponse deleteGiftActivity(String activityId) throws WxErrorException;

  /**
   * 停止赠品活动。
   *
   * @param activityId 活动ID
   * @return 操作响应
   * @throws WxErrorException 异常
   */
  WxStoreBaseResponse stopGiftActivity(String activityId) throws WxErrorException;
}
