package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.notify.SignatureHeader;
import com.github.binarywang.wxpay.bean.profitsharing.notify.ProfitSharingNotifyV3Response;
import com.github.binarywang.wxpay.bean.profitsharing.notify.ProfitSharingNotifyV3Result;
import com.github.binarywang.wxpay.bean.profitsharing.request.*;
import com.github.binarywang.wxpay.bean.profitsharing.result.*;
import com.github.binarywang.wxpay.exception.WxPayException;

/**
 * 注意：微信最高分账比例为30%
 * 可多次分账到同一个人，但是依然不能超过30%
 *
 * @author Wang GuangXin 2019/10/22 10:05
 * @version 1.0
 */
public interface ProfitSharingService {
  /**
   * <pre>
   * 单次分账请求按照传入的分账接收方账号和资金进行分账，同时会将订单剩余的待分账金额解冻给特约商户。故操作成功后，订单不能再进行分账，也不能进行分账完结。
   * 接口频率：30QPS
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/api/allocation_sl.php?chapter=25_1&index=1
   * 接口链接：https://api.mch.weixin.qq.com/secapi/pay/profitsharing
   * </pre>
   *
   * @param request .
   * @return .
   * @throws WxPayException the wx pay exception
   */
  ProfitSharingResult profitSharing(ProfitSharingRequest request) throws WxPayException;

  /**
   * <pre>
   * 微信订单支付成功后，服务商代子商户发起分账请求，将结算后的钱分到分账接收方。多次分账请求仅会按照传入的分账接收方进行分账，不会对剩余的金额进行任何操作。故操作成功后，在待分账金额不等于零时，订单依旧能够再次进行分账。
   * 多次分账，可以将本商户作为分账接收方直接传入，实现释放资金给本商户的功能
   * 对同一笔订单最多能发起20次多次分账请求
   * 接口频率：30QPS
   * </pre>
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/api/allocation_sl.php?chapter=25_6&index=2
   * 接口链接：https://api.mch.weixin.qq.com/secapi/pay/multiprofitsharing
   *
   * @param request .
   * @return .
   * @throws WxPayException the wx pay exception
   */
  ProfitSharingResult multiProfitSharing(ProfitSharingRequest request) throws WxPayException;

  /**
   * <pre>
   * 请求分账API
   *
   * 微信订单支付成功后，商户发起分账请求，将结算后的资金分到分账接收方
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter8_1_1.shtml
   * 接口链接: https://api.mch.weixin.qq.com/v3/profitsharing/orders
   *
   * 注意：
   * 对同一笔订单最多能发起20次分账请求，每次请求最多分给50个接收方
   * 此接口采用异步处理模式，即在接收到商户请求后，优先受理请求再异步处理，最终的分账结果可以通过查询分账接口获取
   * </pre>
   *
   * @param request {@link ProfitSharingV3Request} 针对某一笔支付订单的分账方法
   * @return {@link ProfitSharingV3Result} 微信返回的分账结果
   * @throws WxPayException the wx pay exception
   * @see <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter8_1_1.shtml">微信文档</a>
   */
  ProfitSharingV3Result profitSharingV3(ProfitSharingV3Request request) throws WxPayException;

  /**
   * <pre>
   * 1、不需要进行分账的订单，可直接调用本接口将订单的金额全部解冻给特约商户
   * 2、调用多次分账接口后，需要解冻剩余资金时，调用本接口将剩余的分账金额全部解冻给特约商户
   * 3、已调用请求单次分账后，剩余待分账金额为零，不需要再调用此接口。
   * 接口频率：30QPS
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/api/allocation_sl.php?chapter=25_5&index=6
   * 接口链接：https://api.mch.weixin.qq.com/secapi/pay/profitsharingfinish
   * </pre>
   *
   * @param request .
   * @return .
   * @throws WxPayException the wx pay exception
   */
  ProfitSharingResult profitSharingFinish(ProfitSharingUnfreezeRequest request) throws WxPayException;

  /**
   * <pre>
   * 服务商代子商户发起添加分账接收方请求，后续可通过发起分账请求将结算后的钱分到该分账接收方。
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/api/allocation_sl.php?chapter=25_3&index=4
   * 接口链接：https://api.mch.weixin.qq.com/pay/profitsharingaddreceiver
   * </pre>
   *
   * @param request .
   * @return .
   * @throws WxPayException .
   */
  ProfitSharingReceiverResult addReceiver(ProfitSharingReceiverRequest request) throws WxPayException;

  /**
   * <pre>
   * 服务商代子商户发起删除分账接收方请求，删除后不支持将结算后的钱分到该分账接收方。
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/api/allocation_sl.php?chapter=25_4&index=5
   * 接口链接：https://api.mch.weixin.qq.com/pay/profitsharingremovereceiver
   * </pre>
   *
   * @param request .
   * @return .
   * @throws WxPayException .
   */
  ProfitSharingReceiverResult removeReceiver(ProfitSharingReceiverRequest request) throws WxPayException;

  /**
   * <pre>
   * 添加分账接收方API
   *
   * 商户发起添加分账接收方请求，建立分账接收方列表。后续可通过发起分账请求，将分账方商户结算后的资金，分到该分账接收方
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter8_1_8.shtml
   * 接口链接: https://api.mch.weixin.qq.com/v3/profitsharing/receivers/add
   * </pre>
   *
   * @param request 分账接收方实体 {@link ProfitSharingReceiverV3Request}
   * @return {@link ProfitSharingReceiverV3Result} 微信返回的分账接收方结果
   * @throws WxPayException the wx pay exception
   * @see <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter8_1_8.shtml">微信文档</a>
   */
  ProfitSharingReceiverV3Result addReceiverV3(ProfitSharingReceiverV3Request request) throws WxPayException;

  /**
   * <pre>
   * 删除分账接收方API
   *
   * 商户发起删除分账接收方请求。删除后，不支持将分账方商户结算后的资金，分到该分账接收方
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter8_1_9.shtml
   * 接口链接: https://api.mch.weixin.qq.com/v3/profitsharing/receivers/delete
   * </pre>
   *
   * @param request 分账接收方实体 {@link ProfitSharingReceiverV3Request}
   * @return {@link ProfitSharingReceiverV3Result} 微信返回的删除的分账接收方结果
   * @throws WxPayException the wx pay exception
   * @see <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter8_1_9.shtml">微信文档</a>
   */
  ProfitSharingReceiverV3Result removeReceiverV3(ProfitSharingReceiverV3Request request) throws WxPayException;

  /**
   * TODO:微信返回签名失败
   * <pre>
   * 发起分账请求后，可调用此接口查询分账结果；发起分账完结请求后，可调用此接口查询分账完结的执行结果。
   * 接口频率：80QPS
   * </pre>
   *
   * @param request .
   * @return .
   * @throws WxPayException .
   */
  ProfitSharingQueryResult profitSharingQuery(ProfitSharingQueryRequest request) throws WxPayException;

  /**
   * <pre>
   * 查询分账结果API（商户平台）
   *
   * 发起分账请求后，可调用此接口查询分账结果
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter8_1_2.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/profitsharing/orders/{out_order_no}
   *
   * 注意：
   * • 发起解冻剩余资金请求后，可调用此接口查询解冻剩余资金的结果
   * </pre>
   *
   * @param outOrderNo    商户系统内部的分账单号，在商户系统内部唯一，同一分账单号多次请求等同一次。只能是数字、大小写字母_-|*@ 。
   * @param transactionId 微信支付订单号
   * @return {@link ProfitSharingV3Result} 微信返回的分账结果
   * @throws WxPayException the wx pay exception
   * @see <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter8_1_2.shtml">微信文档</a>
   */
  ProfitSharingV3Result profitSharingQueryV3(String outOrderNo, String transactionId) throws WxPayException;

  /**
   * <pre>
   * 查询分账结果API（服务商平台）
   *
   * 发起分账请求后，可调用此接口查询分账结果
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3_partner/apis/chapter8_1_2.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/profitsharing/orders/{out_order_no}
   *
   * 注意：
   * • 发起解冻剩余资金请求后，可调用此接口查询解冻剩余资金的结果
   * </pre>
   *
   * @param outOrderNo    商户系统内部的分账单号，在商户系统内部唯一，同一分账单号多次请求等同一次。只能是数字、大小写字母_-|*@ 。
   * @param transactionId 微信支付订单号
   * @param subMchId      微信支付分配的子商户号，即分账的出资商户号。
   * @return {@link ProfitSharingV3Result} 微信返回的分账结果
   * @throws WxPayException the wx pay exception
   * @see <a href="https://pay.weixin.qq.com/wiki/doc/apiv3_partner/apis/chapter8_1_2.shtml">微信文档</a>
   */
  ProfitSharingV3Result profitSharingQueryV3(String outOrderNo, String transactionId, String subMchId) throws WxPayException;

  /**
   * <pre>
   * 请求分账查询API
   *
   * 发起分账请求后，可调用此接口查询分账结果
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter8_1_2.shtml
   * 接口链接: https://api.mch.weixin.qq.com/v3/profitsharing/orders/{out_order_no}
   *
   * 注意：
   * 发起解冻剩余资金请求后，可调用此接口查询解冻剩余资金的结果
   * </pre>
   *
   * @param request {@link ProfitSharingQueryV3Request} 针对某一笔分账订单的分账方法
   * @return {@link ProfitSharingV3Result} 微信返回的分账查询结果
   * @throws WxPayException the wx pay exception
   * @see <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter8_1_2.shtml">微信文档</a>
   */
  ProfitSharingV3Result profitSharingQueryV3(ProfitSharingQueryV3Request request) throws WxPayException;

  /**
   * <pre>
   * 服务商可通过调用此接口查询订单剩余待分金额。
   * 接口频率：30QPS
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/api/allocation_sl.php?chapter=25_10&index=7
   * 接口链接：https://api.mch.weixin.qq.com/pay/profitsharingorderamountquery
   * </pre>
   *
   * @param request .
   * @return .
   * @throws WxPayException .
   */
  ProfitSharingOrderAmountQueryResult profitSharingOrderAmountQuery(ProfitSharingOrderAmountQueryRequest request) throws WxPayException;

  /**
   * <pre>
   * 查询剩余待分金额API
   *
   * 可调用此接口查询订单剩余待分金额
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter8_1_6.shtml
   * 接口链接: https://api.mch.weixin.qq.com/v3/profitsharing/transactions/{transaction_id}/amounts
   * </pre>
   *
   * @param transactionId 微信订单号，微信支付订单号
   * @return {@link ProfitSharingOrderAmountQueryV3Result} 微信返回的订单剩余待分金额结果
   * @throws WxPayException the wx pay exception
   * @see <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter8_1_6.shtml">微信文档</a>
   */
  ProfitSharingOrderAmountQueryV3Result profitSharingUnsplitAmountQueryV3(String transactionId) throws WxPayException;

  /**
   * <pre>
   * 服务商可以查询子商户设置的允许服务商分账的最大比例。
   * 接口频率：30QPS
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/api/allocation_sl.php?chapter=25_11&index=8
   * 接口链接：	https://api.mch.weixin.qq.com/pay/profitsharingmerchantratioquery
   * </pre>
   *
   * @param request .
   * @return .
   * @throws WxPayException .
   */
  ProfitSharingMerchantRatioQueryResult profitSharingMerchantRatioQuery(ProfitSharingMerchantRatioQueryRequest request) throws WxPayException;

  /**
   * <pre>
   * 查询最大分账比例
   *
   * 可调用此接口查询特约商户设置的允许服务商分账的最大比例
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3_partner/apis/chapter8_1_7.shtml
   * 接口链接: https://api.mch.weixin.qq.com/v3/profitsharing/merchant-configs/{sub_mchid}
   * </pre>
   *
   * @param subMchId 子商户号（微信支付分配的子商户号，即分账的出资商户号）
   * @return {@link ProfitSharingMerchantRatioQueryV3Result} 特约商户设置的允许服务商分账的最大比例结果
   * @throws WxPayException the wx pay exception
   * @see <a href="https://pay.weixin.qq.com/wiki/doc/apiv3_partner/apis/chapter8_1_7.shtml">服务商平台>>API字典>>资金应用>>分账>>查询最大分账比例</a>
   * @since 4.4.0
   * @date 2022-12-09
   */
  ProfitSharingMerchantRatioQueryV3Result profitSharingMerchantRatioQueryV3(String subMchId) throws WxPayException;

  /**
   * TODO:这个接口用真实的数据返回【参数不正确】，我对比官方文档除了缺少sub_mch_id，和sub_appid之外其他相同，当我随便填了一个商户id的时候，提示【回退方没有开通分账回退功能】
   * <pre>
   * 仅对订单进行退款时，如果订单已经分账，可以先调用此接口将指定的金额从分账接收方（仅限商户类型的分账接收方）回退给特约商户，然后再退款。
   * 回退以原分账请求为依据，可以对分给分账接收方的金额进行多次回退，只要满足累计回退不超过该请求中分给接收方的金额。
   * 此接口采用同步处理模式，即在接收到商户请求后，会实时返回处理结果。
   * 此功能需要接收方在商户平台-交易中心-分账-分账接收设置下，开启同意分账回退后，才能使用。
   * 接口频率：30QPS
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/api/allocation_sl.php?chapter=25_7&index=7
   * 接口链接：https://api.mch.weixin.qq.com/secapi/pay/profitsharingreturn
   * </pre>
   *
   * @param returnRequest .
   * @return .
   * @throws WxPayException .
   */
  ProfitSharingReturnResult profitSharingReturn(ProfitSharingReturnRequest returnRequest) throws WxPayException;

  /**
   * <pre>
   * 请求分账回退API
   *
   * 如果订单已经分账，在退款时，可以先调此接口，将已分账的资金从分账接收方的账户回退给分账方，再发起退款
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter8_1_3.shtml
   * 接口链接: https://api.mch.weixin.qq.com/v3/profitsharing/return-orders
   *
   * 注意：
   * • 分账回退以原分账单为依据，支持多次回退，申请回退总金额不能超过原分账单分给该接收方的金额
   * • 此接口采用同步处理模式，即在接收到商户请求后，会实时返回处理结果
   * • 对同一笔分账单最多能发起20次分账回退请求
   * • 退款和分账回退没有耦合，分账回退可以先于退款请求，也可以后于退款请求
   * • 此功能需要接收方在商户平台-交易中心-分账-分账接收设置下，开启同意分账回退后，才能使用
   * </pre>
   *
   * @param request {@link ProfitSharingReturnV3Request} 针对某一笔支付订单的分账方法
   * @return {@link ProfitSharingReturnV3Result} 微信返回的分账回退结果
   * @throws WxPayException the wx pay exception
   * @see <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter8_1_3.shtml">微信文档</a>
   */
  ProfitSharingReturnV3Result profitSharingReturnV3(ProfitSharingReturnV3Request request) throws WxPayException;

  /**
   * TODO:因profitsharingReturn接口无法使用，没有办法对这里进行真实的测试，模拟数据这里返回【记录不存在】
   * <pre>
   * 商户需要核实回退结果，可调用此接口查询回退结果。
   * 如果分账回退接口返回状态为处理中，可调用此接口查询回退结果。
   * 接口频率：30QPS
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/api/allocation_sl.php?chapter=25_8&index=8
   * 接口链接：https://api.mch.weixin.qq.com/pay/profitsharingreturnquery
   * </pre>
   *
   * @param queryRequest .
   * @return .
   * @throws WxPayException .
   */
  ProfitSharingReturnResult profitSharingReturnQuery(ProfitSharingReturnQueryRequest queryRequest) throws WxPayException;

  /**
   * <pre>
   * 查询分账回退结果API（商户平台）
   *
   * 商户需要核实回退结果，可调用此接口查询回退结果
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter8_1_4.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/profitsharing/return-orders/{out_return_no}
   *
   * 注意：
   * • 如果分账回退接口返回状态为处理中，可调用此接口查询回退结果
   * </pre>
   *
   * @param outOrderNo  原发起分账请求时使用的商户系统内部的分账单号
   * @param outReturnNo 调用回退接口提供的商户系统内部的回退单号
   * @return {@link ProfitSharingReturnV3Result} 微信返回的分账回退结果
   * @throws WxPayException the wx pay exception
   * @see <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter8_1_4.shtml">微信文档</a>
   */
  ProfitSharingReturnV3Result profitSharingReturnQueryV3(String outOrderNo, String outReturnNo) throws WxPayException;

  /**
   * <pre>
   * 查询分账回退结果API（服务商平台）
   *
   * 商户需要核实回退结果，可调用此接口查询回退结果
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3_partner/apis/chapter8_1_3.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/profitsharing/return-orders/{out_return_no}
   *
   * 注意：
   * • 如果分账回退接口返回状态为处理中，可调用此接口查询回退结果
   * </pre>
   *
   * @param outOrderNo  原发起分账请求时使用的商户系统内部的分账单号
   * @param outReturnNo 调用回退接口提供的商户系统内部的回退单号
   * @param subMchId      微信支付分配的子商户号，即分账的回退方商户号。
   * @return {@link ProfitSharingReturnV3Result} 微信返回的分账回退结果
   * @throws WxPayException the wx pay exception
   * @see <a href="https://pay.weixin.qq.com/wiki/doc/apiv3_partner/apis/chapter8_1_3.shtml">微信文档</a>
   */
  ProfitSharingReturnV3Result profitSharingReturnQueryV3(String outOrderNo, String outReturnNo, String subMchId) throws WxPayException;

  /**
   * <pre>
   * 解冻剩余资金API
   *
   * 不需要进行分账的订单，可直接调用本接口将订单的金额全部解冻给特约商户
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter8_1_5.shtml
   * 接口链接: https://api.mch.weixin.qq.com/v3/profitsharing/orders/unfreeze
   *
   * 注意：
   * • 调用分账接口后，需要解冻剩余资金时，调用本接口将剩余的分账金额全部解冻给特约商户
   * • 此接口采用异步处理模式，即在接收到商户请求后，优先受理请求再异步处理，最终的分账结果可以通过查询分账接口获取
   * </pre>
   *
   * @param request 解冻剩余资金请求实体 {@link ProfitSharingUnfreezeV3Request}
   * @return {@link ProfitSharingReturnV3Result} 微信返回的解冻剩余资金结果
   * @throws WxPayException the wx pay exception
   * @see <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter8_1_5.shtml">微信文档</a>
   */
  ProfitSharingUnfreezeV3Result profitSharingUnfreeze(ProfitSharingUnfreezeV3Request request) throws WxPayException;

  /**
   * <pre>
   * 分账动账通知
   *
   * 分账或分账回退成功后，微信会把相关变动结果发送给分账接收方（只支持商户）。
   * 对后台通知交互时，如果微信收到应答不是成功或超时，微信认为通知失败，微信会通过一定的策略定期重新发起通知，尽可能提高通知的成功率，但微信不保证通知最终能成功。
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter8_1_10.shtml
   * </pre>
   *
   * @param notifyData 分账通知实体
   * @param header     分账通知头 {@link SignatureHeader}
   * @return {@link ProfitSharingNotifyV3Response} 资源对象
   * @throws WxPayException the wx pay exception
   * @see <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter8_1_10.shtml">微信文档</a>
   */
  ProfitSharingNotifyV3Result parseProfitSharingNotifyResult(String notifyData, SignatureHeader header) throws WxPayException;

  /**
   * <pre>
   * 申请分账账单
   *
   * 微信支付按天提供分账账单文件，商户可以通过该接口获取账单文件的下载地址
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3_partner/apis/chapter8_1_11.shtml
   * 接口链接: https://api.mch.weixin.qq.com/v3/profitsharing/bills
   * </pre>
   *
   * @param request 申请分账账单请求实体（{@link ProfitSharingBillV3Request}）
   * @return {@link ProfitSharingBillV3Result} 申请分账账单结果类
   * @throws WxPayException the wx pay exception
   * @see <a href="https://pay.weixin.qq.com/wiki/doc/apiv3_partner/apis/chapter8_1_11.shtml">服务商平台>>API字典>>资金应用>>分账>>申请分账账单API</a>
   * @since 4.4.0
   * @date 2022-12-09
   */
  ProfitSharingBillV3Result profitSharingBill(ProfitSharingBillV3Request request) throws WxPayException;
}
