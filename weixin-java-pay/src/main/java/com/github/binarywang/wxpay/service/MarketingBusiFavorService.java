package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.marketing.*;
import com.github.binarywang.wxpay.exception.WxPayException;

/**
 * <pre>
 * 微信支付营销商家券接口
 * </pre>
 *
 * @author yujam
 */
public interface MarketingBusiFavorService {
  /**
   * <pre>
   * 商家券接口-创建商家券API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_1.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/marketing/busifavor/stocks
   * </pre>
   *
   * @param request 请求对象 {@link BusiFavorStocksCreateRequest}
   * @return FavorStocksResult 微信返回的批次号信息。
   * @throws WxPayException the wx pay exception
   */
  BusiFavorStocksCreateResult createBusiFavorStocksV3(BusiFavorStocksCreateRequest request) throws WxPayException;

  /**
   * <pre>
   * 商家券接口-查询商家券详情API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_2.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/marketing/busifavor/stocks/{stock_id}
   * </pre>
   *
   * @param stockId 微信为每个商家券批次分配的唯一ID
   * @return BusiFavorStocksGetResult 微信返回的批次号信息。 {@link BusiFavorStocksGetResult}
   * @throws WxPayException the wx pay exception
   */
  BusiFavorStocksGetResult getBusiFavorStocksV3(String stockId) throws WxPayException;

  /**
   * <pre>
   * 商家券接口-核销用户券API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_3.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/marketing/busifavor/coupons/use
   * </pre>
   *
   * @param request 请求对象 {@link BusiFavorCouponsUseRequest}
   * @return BusiFavorCouponsUseResult 微信返回的信息。
   * @throws WxPayException the wx pay exception
   */
  BusiFavorCouponsUseResult verifyBusiFavorCouponsUseV3(BusiFavorCouponsUseRequest request) throws WxPayException;

  /**
   * <pre>
   * 商家券接口-H5发券API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_4_1.shtml
   * 接口链接：https://action.weixin.qq.com/busifavor/getcouponinfo
   * </pre>
   *
   * @param request 请求对象 {@link BusiFavorCouponsUrlRequest}
   * @return String H5领券地址
   * @throws WxPayException the wx pay exception
   */
  String buildBusiFavorCouponinfoUrl(BusiFavorCouponsUrlRequest request) throws WxPayException;

  /**
   * <pre>
   * 商家券接口-根据过滤条件查询用户券API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_4.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/marketing/busifavor/users/{openid}/coupons
   * </pre>
   *
   * @param request 请求对象 {@link BusiFavorQueryUserCouponsRequest}
   * @return BusiFavorQueryUserCouponsResult
   * @throws WxPayException the wx pay exception
   */
  BusiFavorQueryUserCouponsResult queryBusiFavorUsersCoupons(BusiFavorQueryUserCouponsRequest request) throws WxPayException;

  /**
   * <pre>
   * 商家券接口-查询用户单张券详情API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_5.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/marketing/busifavor/users/{openid}/coupons/{coupon_code}/appids/{appid}
   * </pre>
   *
   * @param request 请求对象 {@link BusiFavorQueryOneUserCouponsResult}
   * @return BusiFavorQueryOneUserCouponsRequest
   * @throws WxPayException the wx pay exception
   */
  BusiFavorQueryOneUserCouponsResult queryOneBusiFavorUsersCoupons(BusiFavorQueryOneUserCouponsRequest request) throws WxPayException;

  /**
   * <pre>
   * 商家券接口-上传预存code API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_6.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/marketing/busifavor/stocks/{stock_id}/couponcodes
   * </pre>
   *
   * @param stockId 批次号
   * @param request 请求对象 {@link BusiFavorCouponCodeRequest}
   * @return BusiFavorCouponCodeResult
   * @throws WxPayException the wx pay exception
   */
  BusiFavorCouponCodeResult uploadBusiFavorCouponCodes(String stockId, BusiFavorCouponCodeRequest request) throws WxPayException;

  /**
   * <pre>
   * 商家券接口-设置商家券事件通知地址 API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_7.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/marketing/busifavor/callbacks
   * </pre>
   *
   * @param request 请求对象 {@link BusiFavorCallbacksRequest}
   * @return BusiFavorCallbacksResult
   * @throws WxPayException the wx pay exception
   */
  BusiFavorCallbacksResult createBusiFavorCallbacks(BusiFavorCallbacksRequest request) throws WxPayException;

  /**
   * <pre>
   * 商家券接口-查询商家券事件通知地址 API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_8.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/marketing/busifavor/callbacks
   * </pre>
   *
   * @param request 请求对象 {@link BusiFavorCallbacksRequest}
   * @return BusiFavorCallbacksResult
   * @throws WxPayException the wx pay exception
   */
  BusiFavorCallbacksResult queryBusiFavorCallbacks(BusiFavorCallbacksRequest request) throws WxPayException;

  /**
   * <pre>
   * 商家券接口-关联订单信息 API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_9.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/marketing/busifavor/coupons/associate
   * </pre>
   *
   * @param request 请求对象 {@link BusiFavorCouponsAssociateRequest}
   * @return BusiFavorCouponsAssociateResult
   * @throws WxPayException the wx pay exception
   */
  BusiFavorCouponsAssociateResult queryBusiFavorCouponsAssociate(BusiFavorCouponsAssociateRequest request) throws WxPayException;

  /**
   * <pre>
   * 商家券接口-取消关联订单信息 API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_10.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/marketing/busifavor/coupons/disassociate
   * </pre>
   *
   * @param request 请求对象 {@link BusiFavorCouponsAssociateRequest}
   * @return BusiFavorCouponsAssociateResult
   * @throws WxPayException the wx pay exception
   */
  BusiFavorCouponsAssociateResult queryBusiFavorCouponsDisAssociate(BusiFavorCouponsAssociateRequest request) throws WxPayException;

  /**
   * <pre>
   * 商家券接口-修改批次预算 API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_11.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/marketing/busifavor/stocks/{stock_id}/budget
   * </pre>
   *
   * @param stockId 批次号
   * @param request 请求对象 {@link BusiFavorStocksBudgetRequest}
   * @return BusiFavorStocksBudgetResult
   * @throws WxPayException the wx pay exception
   */
  BusiFavorStocksBudgetResult updateBusiFavorStocksBudget(String stockId, BusiFavorStocksBudgetRequest request) throws WxPayException;

  /**
   * <pre>
   * 商家券接口-创建商家券API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_12.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/marketing/busifavor/stocks/{stock_id}
   * </pre>
   *
   * @param stockId 批次号
   * @param request 请求对象 {@link BusiFavorStocksCreateRequest}
   * @return String 处理成功 应答无内容。
   * @throws WxPayException the wx pay exception
   */
  String updateBusiFavorStocksV3(String stockId, BusiFavorStocksCreateRequest request) throws WxPayException;

  /**
   * <pre>
   * 商家券接口-申请退款API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_13.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/marketing/busifavor/coupons/return
   * </pre>
   *
   * @param request 请求对象 {@link BusiFavorCouponsReturnRequest}
   * @return BusiFavorCouponsReturnResult
   * @throws WxPayException the wx pay exception
   */
  BusiFavorCouponsReturnResult returnBusiFavorCoupons(BusiFavorCouponsReturnRequest request) throws WxPayException;

  /**
   * <pre>
   * 商家券接口-使券失效API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_15.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/marketing/busifavor/coupons/deactivate
   * </pre>
   *
   * @param request 请求对象 {@link BusiFavorCouponsDeactivateRequest}
   * @return BusiFavorCouponsDeactivateResult
   * @throws WxPayException the wx pay exception
   */
  BusiFavorCouponsDeactivateResult deactiveBusiFavorCoupons(BusiFavorCouponsDeactivateRequest request) throws WxPayException;

  /**
   * <pre>
   * 商家券接口-营销补差付款API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_16.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/marketing/busifavor/subsidy/pay-receipts
   * </pre>
   *
   * @param request 请求对象 {@link BusiFavorSubsidyResult}
   * @return BusiFavorSubsidyRequest
   * @throws WxPayException the wx pay exception
   */
  BusiFavorSubsidyResult subsidyBusiFavorPayReceipts(BusiFavorSubsidyRequest request) throws WxPayException;

  /**
   * <pre>
   * 商家券接口-查询营销补差付款单详情API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_17.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/marketing/busifavor/subsidy/pay-receipts/{subsidy_receipt_id}
   * </pre>
   *
   * @param subsidyReceiptId 补差付款唯一单号
   * @return BusiFavorSubsidyRequest
   * @throws WxPayException the wx pay exception
   */
  BusiFavorSubsidyResult queryBusiFavorSubsidyPayReceipts(String subsidyReceiptId) throws WxPayException;

  /**
   * <pre>
   * 商家券接口-领券事件回调通知API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_15.shtml
   * </pre>
   *
   * @param url     回调地址
   * @param request 领券事件回调通知请求对象
   * @return BusiFavorNotifyResult
   * @throws WxPayException the wx pay exception
   */
  BusiFavorNotifyResult notifyBusiFavor(String url, BusiFavorNotifyRequest request) throws WxPayException;
}
