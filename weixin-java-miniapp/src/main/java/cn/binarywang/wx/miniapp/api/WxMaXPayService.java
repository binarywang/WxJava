package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.WxMaBaseResponse;
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
   * 道具直购。
   *
   * @param request          道具直购请求对象
   * @param sigParams        签名参数对象
   * @return                 道具直购结果
   * @throws WxErrorException 直购失败时抛出
   */
  WxMaXPayPresentGoodsResponse presentGoods(WxMaXPayPresentGoodsRequest request, WxMaXPaySigParams sigParams) throws WxErrorException;

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

  /**
   * 查询商家账户里的可提现余额。
   *
   * @param request   查询商家账户里的可提现余额请求对象
   * @param sigParams 签名参数对象
   * @return 商家账户里的可提现余额查询结果
   * @throws WxErrorException 查询失败时抛出
   */
  WxMaXPayQueryBizBalanceResponse queryBizBalance(WxMaXPayQueryBizBalanceRequest request, WxMaXPaySigParams sigParams) throws WxErrorException;

  /**
   * 查询广告金充值账户。
   *
   * @param request   查询广告金充值账户请求对象
   * @param sigParams 签名参数对象
   * @return 广告金充值账户查询结果
   * @throws WxErrorException 查询失败时抛出
   */
  WxMaXPayQueryTransferAccountResponse queryTransferAccount(WxMaXPayQueryTransferAccountRequest request, WxMaXPaySigParams sigParams) throws WxErrorException;

  /**
   * 查询广告金发放记录。
   *
   * @param request   查询广告金发放记录请求对象
   * @param sigParams 签名参数对象
   * @return 查询广告金发放记录结果
   * @throws WxErrorException 查询失败时抛出
   */
  WxMaXPayQueryAdverFundsResponse queryAdverFunds(WxMaXPayQueryAdverFundsRequest request, WxMaXPaySigParams sigParams) throws WxErrorException;

  /**
   * 充值广告金。
   *
   * @param request   充值广告金请求对象
   * @param sigParams 签名参数对象
   * @return 充值广告金结果
   * @throws WxErrorException 查询失败时抛出
   */
  WxMaXPayCreateFundsBillResponse createFundsBill(WxMaXPayCreateFundsBillRequest request, WxMaXPaySigParams sigParams) throws WxErrorException;

  /**
   * 绑定广告金充值账户。
   *
   * @param request   绑定广告金充值账户请求对象
   * @param sigParams 签名参数对象
   * @return 绑定广告金充值账户结果
   * @throws WxErrorException 查询失败时抛出
   */
  WxMaBaseResponse bindTransferAccount(WxMaXPayBindTransferAccountRequest request, WxMaXPaySigParams sigParams) throws WxErrorException;

  /**
   * 查询广告金充值记录。
   *
   * @param request   查询广告金充值记录请求对象
   * @param sigParams 签名参数对象
   * @return 查询广告金充值记录结果
   * @throws WxErrorException 查询失败时抛出
   */
  WxMaXPayQueryFundsBillResponse queryFundsBill(WxMaXPayQueryFundsBillRequest request, WxMaXPaySigParams sigParams) throws WxErrorException;

  /**
   * 查询广告金回收记录。
   *
   * @param request   查询广告金回收记录请求对象
   * @param sigParams 签名参数对象
   * @return 查询广告金回收记录结果
   * @throws WxErrorException 查询失败时抛出
   */
  WxMaXPayQueryRecoverBillResponse queryRecoverBill(WxMaXPayQueryRecoverBillRequest request, WxMaXPaySigParams sigParams) throws WxErrorException;


 /**
   * 获取投诉列表。
   *
   * @param request          获取投诉列表请求对象
   * @param sigParams        签名参数对象
   * @return                 获取投诉列表结果
   * @throws WxErrorException 查询失败时抛出
   */
  WxMaXPayGetComplaintListResponse getComplaintList(WxMaXPayGetComplaintListRequest request, WxMaXPaySigParams sigParams) throws WxErrorException;

 /**
   * 获取投诉详情。
   *
   * @param request          获取投诉详情请求对象
   * @param sigParams        签名参数对象
   * @return                 获取投诉详情结果
   * @throws WxErrorException 查询失败时抛出
   */
  WxMaXPayGetComplaintDetailResponse getComplaintDetail(WxMaXPayGetComplaintDetailRequest request, WxMaXPaySigParams sigParams) throws WxErrorException;

 /**
   * 获取协商历史。
   *
   * @param request          获取协商历史请求对象
   * @param sigParams        签名参数对象
   * @return                 获取协商历史结果
   * @throws WxErrorException 查询失败时抛出
   */
  WxMaXPayGetNegotiationHistoryResponse getNegotiationHistory(WxMaXPayGetNegotiationHistoryRequest request, WxMaXPaySigParams sigParams) throws WxErrorException;

 /**
   * 回复用户。
   *
   * @param request          回复用户请求对象
   * @param sigParams        签名参数对象
   * @return                 回复用户结果
   * @throws WxErrorException 查询失败时抛出
   */
  WxMaBaseResponse responseComplaint(WxMaXPayResponseComplaintRequest request, WxMaXPaySigParams sigParams) throws WxErrorException;

 /**
   * 完成投诉处理。
   *
   * @param request          完成投诉处理请求对象
   * @param sigParams        签名参数对象
   * @return                 完成投诉处理结果
   * @throws WxErrorException 查询失败时抛出
   */
 WxMaBaseResponse completeComplaint(WxMaXPayCompleteComplaintRequest request, WxMaXPaySigParams sigParams) throws WxErrorException;

 /**
   * 上传媒体文件（如图片，凭证等）。
   *
   * @param request          上传媒体文件（如图片，凭证等）请求对象
   * @param sigParams        签名参数对象
   * @return                 上传媒体文件（如图片，凭证等）结果
   * @throws WxErrorException 查询失败时抛出
   */
  WxMaXPayUploadVpFileResponse uploadVpFile(WxMaXPayUploadVpFileRequest request, WxMaXPaySigParams sigParams) throws WxErrorException;

 /**
   * 获取微信支付反馈投诉图片的签名头部。
   *
   * @param request          获取微信支付反馈投诉图片的签名头部请求对象
   * @param sigParams        签名参数对象
   * @return                 获取微信支付反馈投诉图片的签名头部结果
   * @throws WxErrorException 查询失败时抛出
   */
  WxMaXPayGetUploadFileSignResponse getUploadFileSign(WxMaXPayGetUploadFileSignRequest request, WxMaXPaySigParams sigParams) throws WxErrorException;

 /**
   * 下载广告金对应的商户订单信息。
   *
   * @param request          下载广告金对应的商户订单信息请求对象
   * @param sigParams        签名参数对象
   * @return                 下载广告金对应的商户订单信息结果
   * @throws WxErrorException 查询失败时抛出
   */
  WxMaXPayDownloadAdverfundsOrderResponse downloadAdverfundsOrder(WxMaXPayDownloadAdverfundsOrderRequest request, WxMaXPaySigParams sigParams) throws WxErrorException;

}
