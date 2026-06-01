package me.chanjar.weixin.channel.api;

import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.gift.GiftGetResponse;
import me.chanjar.weixin.channel.bean.gift.GiftProductInfo;
import me.chanjar.weixin.channel.bean.gift.GiftProductListResponse;
import me.chanjar.weixin.channel.bean.gift.GiftProductResponse;
import me.chanjar.weixin.channel.bean.gift.GiftProductUpdateInfo;
import me.chanjar.weixin.channel.bean.gift.GiftSetParam;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 视频号小店 赠品管理服务接口
 *
 * @author <a href="https://github.com/features/copilot">GitHub Copilot</a>
 * @see <a href="https://developers.weixin.qq.com/doc/store/shop/API/channels-shop-product/gift/api_addgiftproduct.html">赠品管理接口文档</a>
 */
public interface WxChannelGiftService {

  /**
   * 添加非卖商品（赠品）
   *
   * @param info 赠品信息
   * @return GiftProductResponse
   * @throws WxErrorException 异常
   */
  GiftProductResponse addGiftProduct(GiftProductInfo info) throws WxErrorException;

  /**
   * 更新非卖商品（赠品）
   *
   * @param info 赠品更新信息
   * @return GiftProductResponse
   * @throws WxErrorException 异常
   */
  GiftProductResponse updateGiftProduct(GiftProductUpdateInfo info) throws WxErrorException;

  /**
   * 在售商品转赠品（设置在售商品为赠品）
   *
   * @param param 请求参数（product_id + skus 列表）
   * @return GiftProductListResponse
   * @throws WxErrorException 异常
   */
  GiftProductListResponse setProductAsGift(GiftSetParam param) throws WxErrorException;

  /**
   * 获取赠品详情
   *
   * @param productId 赠品商品ID
   * @param dataType  数据类型。1: 仅获取线上数据；2: 仅获取草稿数据；3: 同时获取（默认）
   * @return GiftGetResponse
   * @throws WxErrorException 异常
   */
  GiftGetResponse getGiftProduct(String productId, Integer dataType) throws WxErrorException;

  /**
   * 获取赠品列表
   *
   * @param pageSize 每页数量
   * @param nextKey  翻页上下文，不传默认获取第一页
   * @param status   赠品状态过滤
   * @return GiftProductListResponse
   * @throws WxErrorException 异常
   */
  GiftProductListResponse listGiftProduct(Integer pageSize, String nextKey, Integer status)
    throws WxErrorException;

  /**
   * 更新赠品库存
   *
   * @param productId 赠品商品ID
   * @param skuId     SKU ID
   * @param diffType  差量类型。1: 增加；2: 减少
   * @param num       变更数量
   * @return WxChannelBaseResponse
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse updateGiftStock(String productId, String skuId, Integer diffType, Integer num)
    throws WxErrorException;
}
