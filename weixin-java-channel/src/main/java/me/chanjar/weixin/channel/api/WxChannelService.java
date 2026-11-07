package me.chanjar.weixin.channel.api;

/**
 * The interface Wx Channel service
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
public interface WxChannelService extends BaseWxChannelService {

  /**
   * 商家客服服务。
   *
   * @return 商家客服服务

   * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.api.WxStoreService#getKfService()}。
   */
  @Deprecated
  default WxChannelKfService getKfService() {
    throw new UnsupportedOperationException("WxChannelService implementation does not support getKfService()");
  }

  /**
   * 基础接口服务
   *
   * @return 基础接口服务

   * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.api.WxStoreService#getBasicService()}。
   */
  @Deprecated
  WxChannelBasicService getBasicService();

  /**
   * 商品类目服务
   *
   * @return 商品类目服务

   * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.api.WxStoreService#getCategoryService()}。
   */
  @Deprecated
  WxChannelCategoryService getCategoryService();

  /**
   * 品牌服务
   *
   * @return 品牌服务

   * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.api.WxStoreService#getBrandService()}。
   */
  @Deprecated
  WxChannelBrandService getBrandService();

  /**
   * 商品服务
   *
   * @return 商品服务

   * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.api.WxStoreService#getProductService()}。
   */
  @Deprecated
  WxChannelProductService getProductService();

  /**
   * 赠品与买赠活动服务
   *
   * @return 赠品与买赠活动服务

   * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.api.WxStoreService#getGiftService()}。
   */
  @Deprecated
  default WxChannelGiftService getGiftService() {
    throw new UnsupportedOperationException("Gift service is not supported by this implementation");
  }

  /**
   * 限时抢购服务
   *
   * @return 限时抢购服务

   * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.api.WxStoreService#getLimitedDiscountService()}。
   */
  @Deprecated
  default WxChannelLimitedDiscountService getLimitedDiscountService() {
    throw new UnsupportedOperationException("Limited discount service is not supported by this implementation");
  }

  /**
   * 商品库存服务
   *
   * @return 商品库存服务

   * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.api.WxStoreService#getProductStockService()}。
   */
  @Deprecated
  default WxChannelProductStockService getProductStockService() {
    throw new UnsupportedOperationException("Product stock service is not supported by this implementation");
  }

  /**
   * 商品辅助功能服务
   *
   * @return 商品辅助功能服务

   * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.api.WxStoreService#getProductAssistantService()}。
   */
  @Deprecated
  default WxChannelProductAssistantService getProductAssistantService() {
    throw new UnsupportedOperationException("Product assistant service is not supported by this implementation");
  }

  /**
   * 仓库服务
   *
   * @return 仓库服务

   * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.api.WxStoreService#getWarehouseService()}。
   */
  @Deprecated
  WxChannelWarehouseService getWarehouseService();

  /**
   * 订单服务
   *
   * @return 订单服务

   * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.api.WxStoreService#getOrderService()}。
   */
  @Deprecated
  WxChannelOrderService getOrderService();

  /**
   * 售后服务
   *
   * @return 售后服务

   * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.api.WxStoreService#getAfterSaleService()}。
   */
  @Deprecated
  WxChannelAfterSaleService getAfterSaleService();

  /**
   * 运费模板服务
   *
   * @return 运费模板服务

   * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.api.WxStoreService#getFreightTemplateService()}。
   */
  @Deprecated
  WxChannelFreightTemplateService getFreightTemplateService();

  /**
   * 地址服务
   *
   * @return 地址服务

   * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.api.WxStoreService#getAddressService()}。
   */
  @Deprecated
  WxChannelAddressService getAddressService();

  /**
   * 优惠券服务
   *
   * @return 优惠券服务

   * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.api.WxStoreService#getCouponService()}。
   */
  @Deprecated
  WxChannelCouponService getCouponService();

  /**
   * 分享员服务
   *
   * @return 分享员服务

   * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.api.WxStoreService#getSharerService()}。
   */
  @Deprecated
  WxChannelSharerService getSharerService();

  /**
   * 资金服务
   *
   * @return 资金服务

   * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.api.WxStoreService#getFundService()}。
   */
  @Deprecated
  WxChannelFundService getFundService();

  /**
   * 主页管理服务
   *
   * @return 主页管理服务

   * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.api.WxStoreService#getHomePageService()}。
   */
  @Deprecated
  WxStoreHomePageService getHomePageService();

  /**
   * 合作账号服务
   *
   * @return 团长合作服务

   * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.api.WxStoreService#getCooperationService()}。
   */
  @Deprecated
  WxStoreCooperationService getCooperationService();

  /**
   * 视频号/微信小店 罗盘商家版服务
   *
   * @return 罗盘商家版服务

   * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.api.WxStoreService#getCompassShopService()}。
   */
  @Deprecated
  WxChannelCompassShopService getCompassShopService();

  /**
   * 优选联盟-团长合作达人管理服务
   *
   * @return 团长合作达人管理服务
   */
  WxLeagueWindowService getLeagueWindowService();

  /**
   * 代发管理服务
   *
   * @return 代发管理服务

   * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.api.WxStoreService#getSupplierService()}。
   */
  @Deprecated
  WxChannelSupplierService getSupplierService();

  /**
   * 优选联盟-团长服务
   *
   * @return 团长服务
   */
  WxLeagueSupplierService getLeagueSupplierService();

  /**
   * 优选联盟-达人服务
   *
   * @return 达人服务
   */
  WxLeaguePromoterService getLeaguePromoterService();

  /**
   * 优选联盟-商品服务
   *
   * @return 商品服务
   */
  WxLeagueProductService getLeagueProductService();

  /**
   * 视频号助手 留资组件管理服务
   *
   * @return 留资组件管理服务
   */
  WxLeadComponentService getLeadComponentService();

  /**
   * 视频号助手 留资服务的直播数据服务
   *
   * @return 留资服务的直播数据服务
   */
  WxFinderLiveService getFinderLiveService();

  /**
   * 视频号助手 橱窗管理服务
   *
   * @return 橱窗管理服务
   */
  WxAssistantService getAssistantService();

  /**
   * 会员功能
   *
   * @return 会员服务

   * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.api.WxStoreService#getVipService()}。
   */
  @Deprecated
  WxChannelVipService getVipService();

  /**
   * 视频号助手-罗盘达人版服务
   *
   * @return 罗盘达人版服务
   */
  WxChannelCompassFinderService getCompassFinderService();

  /**
   * 视频号助手-直播大屏数据服务
   *
   * @return 直播大屏数据服务
   */
  WxChannelLiveDashboardService getLiveDashboardService();

  /**
   * 质检管理服务.
   *
   * @return 质检管理服务

   * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.api.WxStoreService#getQicService()}。
   */
  @Deprecated
  WxChannelQicService getQicService();

  /**
   * 微信小店-带货助手服务
   *
   * @return 带货助手服务
   */
  WxTalentService getTalentService();

  /**
   * 收藏管理服务
   *
   * @return 收藏管理服务

   * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.api.WxStoreService#getFavoriteService()}。
   */
  @Deprecated
  WxChannelFavoriteService getFavoriteService();

  /**
   * 电子面单服务
   *
   * @return 电子面单服务

   * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.api.WxStoreService#getEwaybillService()}。
   */
  @Deprecated
  default WxChannelEwaybillService getEwaybillService() {
    throw new UnsupportedOperationException("当前 WxChannelService 实现不支持电子面单服务");
  }

}
