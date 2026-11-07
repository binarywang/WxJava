package me.chanjar.weixin.channel.api;

import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.product.assistant.BeginTimingSaleParam;
import me.chanjar.weixin.channel.bean.product.assistant.CancelTimingSaleParam;
import me.chanjar.weixin.channel.bean.product.assistant.CategoryPreCheckParam;
import me.chanjar.weixin.channel.bean.product.assistant.CategoryPreCheckResponse;
import me.chanjar.weixin.channel.bean.product.assistant.ExternalProductMappingNewParam;
import me.chanjar.weixin.channel.bean.product.assistant.ExternalProductMappingNewResponse;
import me.chanjar.weixin.channel.bean.product.assistant.ExternalProductMappingParam;
import me.chanjar.weixin.channel.bean.product.assistant.ExternalProductMappingResponse;
import me.chanjar.weixin.channel.bean.product.assistant.ProductBrandRecommendParam;
import me.chanjar.weixin.channel.bean.product.assistant.ProductBrandRecommendResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 微信小店商品辅助功能服务。
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.api.WxStoreProductAssistantService}。
 */
@Deprecated
public interface WxChannelProductAssistantService {

  /**
   * 发品前校验。
   *
   * @param param 校验参数
   * @return 校验结果
   * @throws WxErrorException 异常
   */
  CategoryPreCheckResponse categoryPreCheck(CategoryPreCheckParam param) throws WxErrorException;

  /**
   * 获取商品品牌推荐。
   *
   * @param param 推荐参数
   * @return 推荐结果
   * @throws WxErrorException 异常
   */
  ProductBrandRecommendResponse getProductBrandRecommend(ProductBrandRecommendParam param)
    throws WxErrorException;

  /**
   * 获取站内外商品属性映射。
   *
   * @param param 映射参数
   * @return 映射结果
   * @throws WxErrorException 异常
   */
  ExternalProductMappingResponse externalProductMapping(ExternalProductMappingParam param)
    throws WxErrorException;

  /**
   * 获取商品属性映射及推荐。
   *
   * @param param 映射参数
   * @return 映射结果
   * @throws WxErrorException 异常
   */
  ExternalProductMappingNewResponse externalProductMappingNew(ExternalProductMappingNewParam param)
    throws WxErrorException;

  /**
   * 将定时开售商品改为立即开售。
   *
   * @param param 开售参数
   * @return 操作结果
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse beginTimingSale(BeginTimingSaleParam param) throws WxErrorException;

  /**
   * 取消商品定时开售。
   *
   * @param param 取消参数
   * @return 操作结果
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse cancelTimingSale(CancelTimingSaleParam param) throws WxErrorException;
}
