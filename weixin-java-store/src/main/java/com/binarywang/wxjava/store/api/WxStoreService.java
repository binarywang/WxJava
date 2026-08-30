package com.binarywang.wxjava.store.api;

/**
 * The interface Wx Store service
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
public interface WxStoreService extends BaseWxStoreService {

  /**
   * 商家客服服务。
   *
   * @return 商家客服服务
   */
  default WxStoreKfService getKfService() {
    throw new UnsupportedOperationException("WxStoreService implementation does not support getKfService()");
  }

  /**
   * 基础接口服务
   *
   * @return 基础接口服务
   */
  WxStoreBasicService getBasicService();

  /**
   * 商品类目服务
   *
   * @return 商品类目服务
   */
  WxStoreCategoryService getCategoryService();

  /**
   * 品牌服务
   *
   * @return 品牌服务
   */
  WxStoreBrandService getBrandService();

  /**
   * 商品服务
   *
   * @return 商品服务
   */
  WxStoreProductService getProductService();

  /**
   * 赠品与买赠活动服务
   *
   * @return 赠品与买赠活动服务
   */
  default WxStoreGiftService getGiftService() {
    throw new UnsupportedOperationException("Gift service is not supported by this implementation");
  }

  /**
   * 限时抢购服务
   *
   * @return 限时抢购服务
   */
  default WxStoreLimitedDiscountService getLimitedDiscountService() {
    throw new UnsupportedOperationException("Limited discount service is not supported by this implementation");
  }

  /**
   * 商品库存服务
   *
   * @return 商品库存服务
   */
  default WxStoreProductStockService getProductStockService() {
    throw new UnsupportedOperationException("Product stock service is not supported by this implementation");
  }

  /**
   * 商品辅助功能服务
   *
   * @return 商品辅助功能服务
   */
  default WxStoreProductAssistantService getProductAssistantService() {
    throw new UnsupportedOperationException("Product assistant service is not supported by this implementation");
  }

  /**
   * 仓库服务
   *
   * @return 仓库服务
   */
  WxStoreWarehouseService getWarehouseService();

  /**
   * 订单服务
   *
   * @return 订单服务
   */
  WxStoreOrderService getOrderService();

  /**
   * 售后服务
   *
   * @return 售后服务
   */
  WxStoreAfterSaleService getAfterSaleService();

  /**
   * 运费模板服务
   *
   * @return 运费模板服务
   */
  WxStoreFreightTemplateService getFreightTemplateService();

  /**
   * 地址服务
   *
   * @return 地址服务
   */
  WxStoreAddressService getAddressService();

  /**
   * 优惠券服务
   *
   * @return 优惠券服务
   */
  WxStoreCouponService getCouponService();

  /**
   * 分享员服务
   *
   * @return 分享员服务
   */
  WxStoreSharerService getSharerService();

  /**
   * 资金服务
   *
   * @return 资金服务
   */
  WxStoreFundService getFundService();

  /**
   * 主页管理服务
   *
   * @return 主页管理服务
   */
  WxStoreHomePageService getHomePageService();

  /**
   * 合作账号服务
   *
   * @return 团长合作服务
   */
  WxStoreCooperationService getCooperationService();

  /**
   * 微信小店 罗盘商家版服务
   *
   * @return 罗盘商家版服务
   */
  WxStoreCompassShopService getCompassShopService();

  /**
   * 代发管理服务
   *
   * @return 代发管理服务
   */
  WxStoreSupplierService getSupplierService();

  /**
   * 会员功能
   *
   * @return 会员服务
   */
  WxStoreVipService getVipService();

  /**
   * 质检管理服务.
   *
   * @return 质检管理服务
   */
  WxStoreQicService getQicService();

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
   */
  WxStoreFavoriteService getFavoriteService();

  /**
   * 电子面单服务
   *
   * @return 电子面单服务
   */
  default WxStoreEwaybillService getEwaybillService() {
    throw new UnsupportedOperationException("当前 WxStoreService 实现不支持电子面单服务");
  }

}
