package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.xpay.*;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 小程序虚拟支付相关接口。
 * 文档：https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/industry/virtual-payment.html
 *
 */
public interface WxMaXPayService {

  /**
   * 查询用户虚拟币余额。
   *
   * @param request          查询用户余额请求对象
   * @param sigParams        签名参数对象
   * @return                 用户余额查询结果
   * @throws WxErrorException 查询失败时抛出
   */
  WxMaXPayQueryUserBalanceResponse queryUserBalance(WxMaXPayQueryUserBalanceRequest request, WxMaXPaySigParams sigParams) throws WxErrorException;

  /**
   * 虚拟币充值下单。
   *
   * @param request          虚拟币充值请求对象
   * @param sigParams        签名参数对象
   * @return                 虚拟币充值结果
   * @throws WxErrorException 充值失败时抛出
   */
  WxMaXPayCurrencyPayResponse currencyPay(WxMaXPayCurrencyPayRequest request, WxMaXPaySigParams sigParams) throws WxErrorException;

  /**
   * 查询订单信息。
   *
   * @param request          查询订单请求对象
   * @param sigParams        签名参数对象
   * @return                 订单查询结果
   * @throws WxErrorException 查询失败时抛出
   */
  WxMaXPayQueryOrderResponse queryOrder(WxMaXPayQueryOrderRequest request, WxMaXPaySigParams sigParams) throws WxErrorException;

  /**
   * 取消虚拟币充值订单。
   *
   * @param request          取消充值订单请求对象
   * @param sigParams        签名参数对象
   * @return                 取消充值订单结果
   * @throws WxErrorException 取消失败时抛出
   */
  WxMaXPayCancelCurrencyPayResponse cancelCurrencyPay(WxMaXPayCancelCurrencyPayRequest request, WxMaXPaySigParams sigParams) throws WxErrorException;

  /**
   * 通知发货。
   *
   * @param request          通知发货请求对象
   * @param sigParams        签名参数对象
   * @return                 通知发货是否成功
   * @throws WxErrorException 通知失败时抛出
   */
  boolean notifyProvideGoods(WxMaXPayNotifyProvideGoodsRequest request, WxMaXPaySigParams sigParams) throws WxErrorException;

  /**
   * 赠送虚拟币。
   *
   * @param request          赠送虚拟币请求对象
   * @param sigParams        签名参数对象
   * @return                 赠送虚拟币结果
   * @throws WxErrorException 赠送失败时抛出
   */
  WxMaXPayPresentCurrencyResponse presentCurrency(WxMaXPayPresentCurrencyRequest request, WxMaXPaySigParams sigParams) throws WxErrorException;

  /**
   * 下载对账单。
   *
   * @param request          下载对账单请求对象
   * @param sigParams        签名参数对象
   * @return                 对账单下载结果
   * @throws WxErrorException 下载失败时抛出
   */
  WxMaXPayDownloadBillResponse downloadBill(WxMaXPayDownloadBillRequest request, WxMaXPaySigParams sigParams) throws WxErrorException;

  /**
   * 退款申请。
   *
   * @param request          退款申请请求对象
   * @param sigParams        签名参数对象
   * @return                 退款申请结果
   * @throws WxErrorException 退款失败时抛出
   */
  WxMaXPayRefundOrderResponse refundOrder(WxMaXPayRefundOrderRequest request, WxMaXPaySigParams sigParams) throws WxErrorException;

  /**
   * 创建提现订单。
   *
   * @param request          创建提现订单请求对象
   * @param sigParams        签名参数对象
   * @return                 创建提现订单结果
   * @throws WxErrorException 创建失败时抛出
   */
  WxMaXPayCreateWithdrawOrderResponse createWithdrawOrder(WxMaXPayCreateWithdrawOrderRequest request, WxMaXPaySigParams sigParams) throws WxErrorException;

  /**
   * 查询提现订单。
   *
   * @param request          查询提现订单请求对象
   * @param sigParams        签名参数对象
   * @return                 提现订单查询结果
   * @throws WxErrorException 查询失败时抛出
   */
  WxMaXPayQueryWithdrawOrderResponse queryWithdrawOrder(WxMaXPayQueryWithdrawOrderRequest request, WxMaXPaySigParams sigParams) throws WxErrorException;

  /**
   * 启动道具上传。
   *
   * @param request          启动道具上传请求对象
   * @param sigParams        签名参数对象
   * @return                 启动道具上传是否成功
   * @throws WxErrorException 启动失败时抛出
   */
  boolean startUploadGoods(WxMaXPayStartUploadGoodsRequest request, WxMaXPaySigParams sigParams) throws WxErrorException;

  /**
   * 查询道具上传状态。
   *
   * @param request          查询道具上传状态请求对象
   * @param sigParams        签名参数对象
   * @return                 道具上传状态查询结果
   * @throws WxErrorException 查询失败时抛出
   */
  WxMaXPayQueryUploadGoodsResponse queryUploadGoods(WxMaXPayQueryUploadGoodsRequest request, WxMaXPaySigParams sigParams) throws WxErrorException;

  /**
   * 启动道具发布。
   *
   * @param request          启动道具发布请求对象
   * @param sigParams        签名参数对象
   * @return                 启动道具发布是否成功
   * @throws WxErrorException 启动失败时抛出
   */
  boolean startPublishGoods(WxMaXPayStartPublishGoodsRequest request, WxMaXPaySigParams sigParams) throws WxErrorException;

  /**
   * 查询道具发布状态。
   *
   * @param request          查询道具发布状态请求对象
   * @param sigParams        签名参数对象
   * @return                 道具发布状态查询结果
   * @throws WxErrorException 查询失败时抛出
   */
  WxMaXPayQueryPublishGoodsResponse queryPublishGoods(WxMaXPayQueryPublishGoodsRequest request, WxMaXPaySigParams sigParams) throws WxErrorException;
}
