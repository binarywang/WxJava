package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.ecommerce.SignatureHeader;
import com.github.binarywang.wxpay.bean.marketing.*;
import com.github.binarywang.wxpay.exception.WxPayException;

/**
 * <pre>
 * 微信支付营销代金券接口
 * </pre>
 *
 * @author thinsstar
 */
public interface MarketingFavorService {
  /**
   * <pre>
   * 代金券接口-创建代金券批次API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/marketing/convention/chapter3_1.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/marketing/favor/coupon-stocks
   * </pre>
   *
   * @param request 请求对象
   * @return FavorStocksResult 微信返回的批次号信息。
   * @throws WxPayException the wx pay exception
   */
  FavorStocksCreateResult createFavorStocksV3(FavorStocksCreateRequest request) throws WxPayException;

  /**
   * <pre>
   * 代金券接口-发放代金券API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/marketing/convention/chapter3_2.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/marketing/favor/users/{openid}/coupons
   * </pre>
   *
   * @param openid  用户openid
   * @param request 请求对象
   * @return FavorStocksResult 微信返回的发放结果信息。
   * @throws WxPayException the wx pay exception
   */
  FavorCouponsCreateResult createFavorCouponsV3(String openid, FavorCouponsCreateRequest request) throws WxPayException;

  /**
   * <pre>
   * 代金券接口-激活代金券批次API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/marketing/convention/chapter3_3.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/marketing/favor/stocks/{stock_id}/start
   * </pre>
   *
   * @param stockId 批次号
   * @param request 请求对象
   * @return FavorStocksStartResult 微信返回的激活信息。
   * @throws WxPayException the wx pay exception
   */
  FavorStocksStartResult startFavorStocksV3(String stockId, FavorStocksSetRequest request) throws WxPayException;

  /**
   * <pre>
   * 代金券接口-条件查询代金券批次列表API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/marketing/convention/chapter3_4.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/marketing/favor/stocks
   * </pre>
   *
   * @param request 请求对象
   * @return FavorStocksQueryResult 微信返回的批次列表信息。
   * @throws WxPayException the wx pay exception
   */
  FavorStocksQueryResult queryFavorStocksV3(FavorStocksQueryRequest request) throws WxPayException;

  /**
   * <pre>
   * 代金券接口-查询批次详情API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/marketing/convention/chapter3_5.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/marketing/favor/stocks/{stock_id}
   * </pre>
   *
   * @param stockId           批次号
   * @param stockCreatorMchid 创建批次的商户号
   * @return FavorStocksQueryResult 微信返回的批次详情信息。
   * @throws WxPayException the wx pay exception
   */
  FavorStocksGetResult getFavorStocksV3(String stockId, String stockCreatorMchid) throws WxPayException;

  /**
   * <pre>
   * 代金券接口-查询代金券详情API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/marketing/convention/chapter3_6.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/marketing/favor/users/{openid}/coupons/{coupon_id}
   * </pre>
   *
   * @param couponId 代金券id
   * @param appid    公众账号ID
   * @param openid   用户openid
   * @return FavorCouponsGetResult 微信返回的代金券详情信息。
   * @throws WxPayException the wx pay exception
   */
  FavorCouponsGetResult getFavorCouponsV3(String couponId, String appid, String openid) throws WxPayException;

  /**
   * <pre>
   * 代金券接口-查询代金券可用商户API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/marketing/convention/chapter3_7.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/marketing/favor/stocks/{stock_id}/merchants
   * </pre>
   *
   * @param stockId           批次号
   * @param stockCreatorMchid 创建批次的商户号
   * @param offset            分页大小
   * @param limit             创建批次的商户号
   * @return FavorStocksMerchantsGetResult 微信返回的代金券可用商户信息。
   * @throws WxPayException the wx pay exception
   */
  FavorStocksMerchantsGetResult getFavorStocksMerchantsV3(String stockId, String stockCreatorMchid, Integer offset, Integer limit) throws WxPayException;

  /**
   * <pre>
   * 代金券接口-查询代金券可用单品API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/marketing/convention/chapter3_8.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/marketing/favor/stocks/{stock_id}/items
   * </pre>
   *
   * @param stockId           批次号
   * @param stockCreatorMchid 创建批次的商户号
   * @param offset            分页大小
   * @param limit             创建批次的商户号
   * @return FavorStocksItemsGetResult 微信返回的代金券可用单品信息。
   * @throws WxPayException the wx pay exception
   */
  FavorStocksItemsGetResult getFavorStocksItemsV3(String stockId, String stockCreatorMchid, Integer offset, Integer limit) throws WxPayException;

  /**
   * <pre>
   * 代金券接口-根据商户号查用户的券API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/marketing/convention/chapter3_9.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/marketing/favor/users/{openid}/coupons
   * </pre>
   *
   * @param request 请求对象
   * @return FavorCouponsQueryResult 微信返回的用户的券信息。
   * @throws WxPayException the wx pay exception
   */
  FavorCouponsQueryResult queryFavorCouponsV3(FavorCouponsQueryRequest request) throws WxPayException;

  /**
   * <pre>
   * 代金券接口-下载批次核销明细API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/marketing/convention/chapter3_10.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/marketing/favor/stocks/{stock_id}/use-flow
   * </pre>
   *
   * @param stockId 批次号
   * @return FavorStocksFlowGetResult 微信返回的下载信息。
   * @throws WxPayException the wx pay exception
   */
  FavorStocksFlowGetResult getFavorStocksUseFlowV3(String stockId) throws WxPayException;

  /**
   * <pre>
   * 代金券接口-下载批次退款明细API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/marketing/convention/chapter3_11.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/marketing/favor/stocks/{stock_id}/refund-flow
   * </pre>
   *
   * @param stockId 批次号
   * @return FavorStocksFlowGetResult 微信返回的下载信息。
   * @throws WxPayException the wx pay exception
   */
  FavorStocksFlowGetResult getFavorStocksRefundFlowV3(String stockId) throws WxPayException;

  /**
   * <pre>
   * 代金券接口-设置消息通知地址API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/marketing/convention/chapter3_12.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/marketing/favor/callbacks
   * </pre>
   *
   * @param request 请求对象
   * @return FavorCallbacksSaveResult 微信返回的结果信息。
   * @throws WxPayException the wx pay exception
   */
  FavorCallbacksSaveResult saveFavorCallbacksV3(FavorCallbacksSaveRequest request) throws WxPayException;

  /**
   * <pre>
   * 代金券接口-暂停代金券批次API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/marketing/convention/chapter3_13.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/marketing/favor/stocks/{stock_id}/pause
   * </pre>
   *
   * @param request 请求对象
   * @return FavorStocksPauseResult 微信返回的结果信息。
   * @throws WxPayException the wx pay exception
   */
  FavorStocksPauseResult pauseFavorStocksV3(String stockId, FavorStocksSetRequest request) throws WxPayException;

  /**
   * <pre>
   * 代金券接口-重启代金券批次API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/marketing/convention/chapter3_14.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/marketing/favor/stocks/{stock_id}/restart
   * </pre>
   *
   * @param request 请求对象
   * @return FavorStocksRestartResult 微信返回的结果信息。
   * @throws WxPayException the wx pay exception
   */
  FavorStocksRestartResult restartFavorStocksV3(String stockId, FavorStocksSetRequest request) throws WxPayException;

  UseNotifyData parseNotifyData(String data, SignatureHeader header) throws WxPayException;

  FavorCouponsUseResult decryptNotifyDataResource(UseNotifyData data) throws WxPayException;
}
