package me.chanjar.weixin.channel.api;

import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.product.GiftActivityAddResponse;
import me.chanjar.weixin.channel.bean.product.GiftActivityInfo;
import me.chanjar.weixin.channel.bean.product.GiftProductAddResponse;
import me.chanjar.weixin.channel.bean.product.GiftProductGetResponse;
import me.chanjar.weixin.channel.bean.product.GiftProductInfo;
import me.chanjar.weixin.channel.bean.product.GiftProductListParam;
import me.chanjar.weixin.channel.bean.product.GiftProductListResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 微信小店赠品与买赠活动服务。
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.api.WxStoreGiftService}。
 */
@Deprecated
public interface WxChannelGiftService {

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
  WxChannelBaseResponse updateGiftProduct(GiftProductInfo info) throws WxErrorException;

  /**
   * 在售商品转赠品。
   *
   * @param productId 商品ID
   * @return 操作响应
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse setProductAsGift(String productId) throws WxErrorException;

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
  WxChannelBaseResponse updateGiftStock(String productId, String skuId, Integer diffType, Integer num)
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
  WxChannelBaseResponse deleteGiftActivity(String activityId) throws WxErrorException;

  /**
   * 停止赠品活动。
   *
   * @param activityId 活动ID
   * @return 操作响应
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse stopGiftActivity(String activityId) throws WxErrorException;
}
