package me.chanjar.weixin.channel.api;

import me.chanjar.weixin.channel.bean.compass.finder.OverallResponse;
import me.chanjar.weixin.channel.bean.compass.finder.ProductDataResponse;
import me.chanjar.weixin.channel.bean.compass.finder.ProductListResponse;
import me.chanjar.weixin.channel.bean.compass.finder.SaleProfileDataResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 视频号助手 罗盘达人版服务
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
public interface WxChannelCompassFinderService {

  /**
   * 获取电商概览数据
   *
   * @param ds 日期，格式 yyyyMMdd
   * @return 电商概览数据
   *
   * @throws WxErrorException 异常
   */
  OverallResponse getOverall(String ds) throws WxErrorException;

  /**
   * 获取带货商品数据
   *
   * @param ds        日期，格式 yyyyMMdd
   * @param productId 商品id
   * @return 带货商品数据
   *
   * @throws WxErrorException 异常
   */
  ProductDataResponse getProductData(String ds, String productId) throws WxErrorException;

  /**
   * 获取带货商品列表
   *
   * @param ds 日期，格式 yyyyMMdd
   * @return 带货商品列表
   *
   * @throws WxErrorException 异常
   */
  ProductListResponse getProductList(String ds) throws WxErrorException;

  /**
   * 获取带货人群数据
   *
   * @param ds   日期，格式 yyyyMMdd
   * @param type 用户类型，1=商品曝光用户, 2=商品点击用户, 3=购买用户, 4=首购用户, 5=复购用户, 6=直播观看用户
   * @return 带货人群数据
   *
   * @throws WxErrorException 异常
   */
  SaleProfileDataResponse getSaleProfileData(String ds, Integer type) throws WxErrorException;

}
