package me.chanjar.weixin.channel.api;

import me.chanjar.weixin.channel.bean.compass.shop.FinderAuthListResponse;
import me.chanjar.weixin.channel.bean.compass.shop.FinderListResponse;
import me.chanjar.weixin.channel.bean.compass.shop.FinderOverallResponse;
import me.chanjar.weixin.channel.bean.compass.shop.FinderProductListResponse;
import me.chanjar.weixin.channel.bean.compass.shop.FinderProductOverallResponse;
import me.chanjar.weixin.channel.bean.compass.shop.ShopLiveListResponse;
import me.chanjar.weixin.channel.bean.compass.shop.ShopOverallResponse;
import me.chanjar.weixin.channel.bean.compass.shop.ShopProductDataResponse;
import me.chanjar.weixin.channel.bean.compass.shop.ShopProductListResponse;
import me.chanjar.weixin.channel.bean.compass.shop.ShopSaleProfileDataResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 视频号/微信小店 罗盘商家版服务
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
public interface WxChannelCompassShopService {

  /**
   * 获取电商概览数据
   *
   * @param ds 日期，格式 yyyyMMdd
   * @return 电商概览数据
   *
   * @throws WxErrorException 异常
   */
  ShopOverallResponse getShopOverall(String ds) throws WxErrorException;

  /**
   * 获取授权视频号列表
   *
   * @return 获取授权视频号列表
   *
   * @throws WxErrorException 异常
   */
  FinderAuthListResponse getFinderAuthorizationList() throws WxErrorException;

  /**
   * 获取带货达人列表
   *
   * @param ds 日期，格式 yyyyMMdd
   * @return 带货达人列表
   *
   * @throws WxErrorException 异常
   */
  FinderListResponse getFinderList(String ds) throws WxErrorException;

  /**
   * 获取带货数据概览
   *
   * @param ds 日期，格式 yyyyMMdd
   * @return 带货数据概览
   *
   * @throws WxErrorException 异常
   */
  FinderOverallResponse getFinderOverall(String ds) throws WxErrorException;

  /**
   * 获取带货达人商品列表
   *
   * @param ds       日期，格式YYYYMMDD
   * @param finderId 视频号ID
   * @return 带货达人商品列表
   *
   * @throws WxErrorException 异常
   */
  FinderProductListResponse getFinderProductList(String ds, String finderId) throws WxErrorException;

  /**
   * 获取带货达人详情
   *
   * @param ds       日期，格式YYYYMMDD
   * @param finderId 视频号ID
   * @return 带货达人详情
   *
   * @throws WxErrorException 异常
   */
  FinderProductOverallResponse getFinderProductOverall(String ds, String finderId) throws WxErrorException;

  /**
   * 获取店铺开播列表
   *
   * @param ds       日期，格式YYYYMMDD
   * @param finderId 视频号ID
   * @return 店铺开播列表
   *
   * @throws WxErrorException 异常
   */
  ShopLiveListResponse getShopLiveList(String ds, String finderId) throws WxErrorException;

  /**
   * 获取商品详细信息
   *
   * @param ds        日期，格式YYYYMMDD
   * @param productId 商品id
   * @return 商品详细信息
   *
   * @throws WxErrorException 异常
   */
  ShopProductDataResponse getShopProductData(String ds, String productId) throws WxErrorException;

  /**
   * 获取商品列表
   *
   * @param ds 日期，格式YYYYMMDD
   * @return 商品列表
   *
   * @throws WxErrorException 异常
   */
  ShopProductListResponse getShopProductList(String ds) throws WxErrorException;

  /**
   * 获取店铺人群数据
   *
   * @param ds   日期，格式 yyyyMMdd
   * @param type 用户类型，1商品曝光用户 2商品点击用户 3购买用户 4首购用户 5复购用户
   * @return 店铺人群数据
   *
   * @throws WxErrorException 异常
   */
  ShopSaleProfileDataResponse getShopSaleProfileData(String ds, Integer type) throws WxErrorException;

}
