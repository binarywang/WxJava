package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.WxPayApiData;
import com.github.binarywang.wxpay.bean.coupon.*;
import com.github.binarywang.wxpay.bean.notify.*;
import com.github.binarywang.wxpay.bean.request.*;
import com.github.binarywang.wxpay.bean.result.*;
import com.github.binarywang.wxpay.bean.result.enums.TradeTypeEnum;
import com.github.binarywang.wxpay.bean.transfer.TransferBillsNotifyResult;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;

/**
 * <pre>
 * 微信支付相关接口.
 * Created by Binary Wang on 2016/7/28.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public interface WxPayService {

  /**
   * 获取微信支付请求url前缀，沙箱环境可能不一样.
   *
   * @return the pay base url
   */
  String getPayBaseUrl();

  /**
   * Map里 加入新的 {@link WxPayConfig}，适用于动态添加新的微信商户配置.
   *
   * @param mchId       商户id
   * @param appId       微信应用id
   * @param wxPayConfig 新的微信配置
   */
  void addConfig(String mchId, String appId, WxPayConfig wxPayConfig);

  /**
   * 从 Map中 移除 {@link String mchId} 和 {@link String appId} 所对应的 {@link WxPayConfig}，适用于动态移除微信商户配置.
   *
   * @param mchId 对应商户的标识
   * @param appId 微信应用id
   */
  void removeConfig(String mchId, String appId);

  /**
   * 注入多个 {@link WxPayConfig} 的实现. 并为每个 {@link WxPayConfig} 赋予不同的 {@link String mchId} 值
   * 随机采用一个{@link String mchId}进行Http初始化操作
   *
   * @param wxPayConfigs WxPayConfig map
   */
  void setMultiConfig(Map<String, WxPayConfig> wxPayConfigs);

  /**
   * 注入多个 {@link WxPayConfig} 的实现. 并为每个 {@link WxPayConfig} 赋予不同的 {@link String label} 值
   *
   * @param wxPayConfigs WxPayConfig map
   * @param defaultMchId 设置一个{@link WxPayConfig} 所对应的{@link String mchId}进行Http初始化
   */
  void setMultiConfig(Map<String, WxPayConfig> wxPayConfigs, String defaultMchId);

  /**
   * 进行相应的商户切换.
   *
   * @param mchId 商户标识
   * @param appId 微信应用id
   * @return 切换是否成功 boolean
   */
  boolean switchover(String mchId, String appId);

  /**
   * 进行相应的商户切换.
   *
   * @param mchId 商户标识
   * @param appId 微信应用id
   * @return 切换成功 ，则返回当前对象，方便链式调用，否则抛出异常
   */
  WxPayService switchoverTo(String mchId, String appId);

  /**
   * 发送post请求，得到响应字节数组.
   *
   * @param url        请求地址
   * @param requestStr 请求信息
   * @param useKey     是否使用证书
   * @return 返回请求结果字节数组 byte [ ]
   * @throws WxPayException the wx pay exception
   */
  byte[] postForBytes(String url, String requestStr, boolean useKey) throws WxPayException;

  /**
   * 发送post请求，得到响应字符串.
   *
   * @param url        请求地址
   * @param requestStr 请求信息
   * @param useKey     是否使用证书
   * @return 返回请求结果字符串 string
   * @throws WxPayException the wx pay exception
   */
  String post(String url, String requestStr, boolean useKey) throws WxPayException;

  /**
   * 发送post请求，得到响应字符串.
   *
   * @param url        请求地址
   * @param requestStr 请求信息
   * @return 返回请求结果字符串 string
   * @throws WxPayException the wx pay exception
   */
  String postV3(String url, String requestStr) throws WxPayException;

  /**
   * 发送patch请求，得到响应字符串.
   *
   * @param url        请求地址
   * @param requestStr 请求信息
   * @return 返回请求结果字符串 string
   * @throws WxPayException the wx pay exception
   */
  String patchV3(String url, String requestStr) throws WxPayException;

  /**
   * 发送post请求，得到响应字符串.
   * <p>
   * 部分字段会包含敏感信息，所以在提交前需要在请求头中会包含"Wechatpay-Serial"信息
   *
   * @param url        请求地址
   * @param requestStr 请求信息
   * @return 返回请求结果字符串 string
   * @throws WxPayException the wx pay exception
   */
  String postV3WithWechatpaySerial(String url, String requestStr) throws WxPayException;

  /**
   * 发送post请求，得到响应字符串.
   *
   * @param url      请求地址
   * @param httpPost 请求信息
   * @return 返回请求结果字符串 string
   * @throws WxPayException the wx pay exception
   */
  String postV3(String url, HttpPost httpPost) throws WxPayException;

  /**
   * 发送http请求，得到响应字符串.
   *
   * @param url         请求地址
   * @param httpRequest 请求信息，可以是put，post，get，delete等请求
   * @return 返回请求结果字符串 string
   * @throws WxPayException the wx pay exception
   */
  String requestV3(String url, HttpRequestBase httpRequest) throws WxPayException;

  /**
   * 发送get V3请求，得到响应字符串.
   *
   * @param url 请求地址
   * @return 返回请求结果字符串 string
   * @throws WxPayException the wx pay exception
   */
  String getV3(String url) throws WxPayException;

  /**
   * 发送get请求，得到响应字符串.
   * <p>
   * 部分字段会包含敏感信息，所以在提交前需要在请求头中会包含"Wechatpay-Serial"信息
   *
   * @param url 请求地址
   * @return 返回请求结果字符串 string
   * @throws WxPayException the wx pay exception
   */
  String getV3WithWechatPaySerial(String url) throws WxPayException;

  /**
   * 发送下载 V3请求，得到响应流.
   *
   * @param url 请求地址
   * @return 返回请求响应流 input stream
   * @throws WxPayException the wx pay exception
   */
  InputStream downloadV3(String url) throws WxPayException;

  /**
   * 发送put V3请求，得到响应字符串.
   *
   * @param url        请求地址
   * @param requestStr 请求数据
   * @return 返回请求结果字符串 string
   * @throws WxPayException the wx pay exception
   */
  String putV3(String url, String requestStr) throws WxPayException;

  /**
   * 发送delete V3请求，得到响应字符串.
   *
   * @param url 请求地址
   * @return 返回请求结果字符串 string
   * @throws WxPayException the wx pay exception
   */
  String deleteV3(String url) throws WxPayException;

  /**
   * 获取微信签约代扣服务类
   *
   * @return entrust service
   */
  WxEntrustPapService getWxEntrustPapService();

  /**
   * 获取批量转账到零钱服务类.
   *
   * @return the Batch transfer to change service
   */
  PartnerTransferService getPartnerTransferService();

  /**
   * 微工卡
   *
   * @return the micro card
   */
  PayrollService getPayrollService();

  /**
   * 获取企业付款服务类.
   *
   * @return the ent pay service
   */
  EntPayService getEntPayService();

  /**
   * 获取红包接口服务类.
   *
   * @return . redpack service
   */
  RedpackService getRedpackService();

  /**
   * 获取分账服务类.
   * <p>
   * V3接口 {@link WxPayService#getProfitSharingService()}
   * </p>
   *
   * @return the ent pay service
   */
  ProfitSharingService getProfitSharingService();

  /**
   * 获取支付分服务类.
   *
   * @return the ent pay service
   */
  PayScoreService getPayScoreService();

  /**
   * 获取电商收付通服务类
   *
   * @return the ecommerce service
   */
  EcommerceService getEcommerceService();

  /**
   * 获取微信支付智慧商圈服务类
   *
   * @return the business circle service
   */
  BusinessCircleService getBusinessCircleService();

  /**
   * 获取微信支付通用媒体服务类
   *
   * @return the merchant media service
   */
  MerchantMediaService getMerchantMediaService();

  /**
   * 获取微信支付营销媒体服务类
   *
   * @return the marketing media service
   */
  MarketingMediaService getMarketingMediaService();

  /**
   * 获取微信支付营销代金券服务类
   *
   * @return the marketing favor service
   */
  MarketingFavorService getMarketingFavorService();

  /**
   * 获取微信支付营销商家券服务类
   *
   * @return the marketing favor service
   */
  MarketingBusiFavorService getMarketingBusiFavorService();

  /**
   * 获取商家转账到零钱服务类
   *
   * @return the merchant transfer service
   */
  MerchantTransferService getMerchantTransferService();

  /**
   * 获取品牌红包商家转账到零钱服务类
   *
   * @return the brand merchant transfer service
   */
  BrandMerchantTransferService getBrandMerchantTransferService();

  /**
   * 设置企业付款服务类，允许开发者自定义实现类.
   *
   * @param entPayService the ent pay service
   */
  void setEntPayService(EntPayService entPayService);

  /**
   * <pre>
   * 查询订单.
   * 详见https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_2
   * 该接口提供所有微信支付订单的查询，商户可以通过查询订单接口主动查询订单状态，完成下一步的业务逻辑。
   * 需要调用查询接口的情况：
   * ◆ 当商户后台、网络、服务器等出现异常，商户系统最终未接收到支付通知；
   * ◆ 调用支付接口后，返回系统错误或未知交易状态情况；
   * ◆ 调用被扫支付API，返回USERPAYING的状态；
   * ◆ 调用关单或撤销接口API之前，需确认支付状态；
   * 接口地址：https://api.mch.weixin.qq.com/pay/orderquery
   * </pre>
   *
   * @param transactionId 微信订单号
   * @param outTradeNo    商户系统内部的订单号，当没提供transactionId时需要传这个。
   * @return the wx pay order query result
   * @throws WxPayException the wx pay exception
   */
  WxPayOrderQueryResult queryOrder(String transactionId, String outTradeNo) throws WxPayException;

  /**
   * <pre>
   * 查询订单（适合于需要自定义子商户号和子商户appid的情形）.
   * 详见https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_2
   * 该接口提供所有微信支付订单的查询，商户可以通过查询订单接口主动查询订单状态，完成下一步的业务逻辑。
   * 需要调用查询接口的情况：
   * ◆ 当商户后台、网络、服务器等出现异常，商户系统最终未接收到支付通知；
   * ◆ 调用支付接口后，返回系统错误或未知交易状态情况；
   * ◆ 调用被扫支付API，返回USERPAYING的状态；
   * ◆ 调用关单或撤销接口API之前，需确认支付状态；
   * 接口地址：https://api.mch.weixin.qq.com/pay/orderquery
   * </pre>
   *
   * @param request 查询订单请求对象
   * @return the wx pay order query result
   * @throws WxPayException the wx pay exception
   */
  WxPayOrderQueryResult queryOrder(WxPayOrderQueryRequest request) throws WxPayException;

  /**
   * <pre>
   * 查询订单
   * 详见 <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_1_2.shtml">https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_1_2.shtml</a>
   * 商户可以通过查询订单接口主动查询订单状态，完成下一步的业务逻辑。查询订单状态可通过微信支付订单号或商户订单号两种方式查询
   * 注意：
   *  查询订单可通过微信支付订单号和商户订单号两种方式查询，两种查询方式返回结果相同
   * 需要调用查询接口的情况：
   * ◆ 当商户后台、网络、服务器等出现异常，商户系统最终未接收到支付通知。
   * ◆ 调用支付接口后，返回系统错误或未知交易状态情况。
   * ◆ 调用付款码支付API，返回USERPAYING的状态。
   * ◆ 调用关单或撤销接口API之前，需确认支付状态。
   * 接口地址：
   *  https://api.mch.weixin.qq.com/v3/pay/transactions/id/{transaction_id}
   *  https://api.mch.weixin.qq.com/v3/pay/transactions/out-trade-no/{out_trade_no}
   * </pre>
   *
   * @param transactionId 微信订单号
   * @param outTradeNo    商户系统内部的订单号，当没提供transactionId时需要传这个。
   * @return the wx pay order query result
   * @throws WxPayException the wx pay exception
   */
  WxPayOrderQueryV3Result queryOrderV3(String transactionId, String outTradeNo) throws WxPayException;

  /**
   * <pre>
   * 查询订单
   * 详见 <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_1_2.shtml">https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_1_2.shtml</a>
   * 商户可以通过查询订单接口主动查询订单状态，完成下一步的业务逻辑。查询订单状态可通过微信支付订单号或商户订单号两种方式查询
   * 注意：
   *  查询订单可通过微信支付订单号和商户订单号两种方式查询，两种查询方式返回结果相同
   * 需要调用查询接口的情况：
   * ◆ 当商户后台、网络、服务器等出现异常，商户系统最终未接收到支付通知。
   * ◆ 调用支付接口后，返回系统错误或未知交易状态情况。
   * ◆ 调用付款码支付API，返回USERPAYING的状态。
   * ◆ 调用关单或撤销接口API之前，需确认支付状态。
   * 接口地址：
   *  https://api.mch.weixin.qq.com/v3/pay/transactions/id/{transaction_id}
   *  https://api.mch.weixin.qq.com/v3/pay/transactions/out-trade-no/{out_trade_no}
   * </pre>
   *
   * @param request 查询订单请求对象
   * @return the wx pay order query result
   * @throws WxPayException the wx pay exception
   */
  WxPayOrderQueryV3Result queryOrderV3(WxPayOrderQueryV3Request request) throws WxPayException;

  /**
   * <pre>
   * 服务商模式查询订单
   * 详见 <a href="https://pay.weixin.qq.com/wiki/doc/apiv3_partner/apis/chapter4_1_2.shtml">https://pay.weixin.qq.com/wiki/doc/apiv3_partner/apis/chapter4_1_2.shtml</a>
   * 商户可以通过查询订单接口主动查询订单状态，完成下一步的业务逻辑。查询订单状态可通过微信支付订单号或商户订单号两种方式查询
   * 注意：
   *  查询订单可通过微信支付订单号和商户订单号两种方式查询，两种查询方式返回结果相同
   * 需要调用查询接口的情况：
   * ◆ 当商户后台、网络、服务器等出现异常，商户系统最终未接收到支付通知。
   * ◆ 调用支付接口后，返回系统错误或未知交易状态情况。
   * ◆ 调用付款码支付API，返回USERPAYING的状态。
   * ◆ 调用关单或撤销接口API之前，需确认支付状态。
   * 接口地址：
   *  https://api.mch.weixin.qq.com/v3/pay/partner/transactions/id/{transaction_id}
   *  https://api.mch.weixin.qq.com/v3/pay/partner/transactions/out-trade-no/{out_trade_no}
   * </pre>
   *
   * @param transactionId 微信订单号
   * @param outTradeNo    商户系统内部的订单号，当没提供transactionId时需要传这个。
   * @return the wx pay order query result
   * @throws WxPayException the wx pay exception
   */
  WxPayPartnerOrderQueryV3Result queryPartnerOrderV3(String transactionId, String outTradeNo) throws WxPayException;

  /**
   * <pre>
   * 服务商模式查询订单
   * 详见 <a href="https://pay.weixin.qq.com/wiki/doc/apiv3_partner/apis/chapter4_1_2.shtml">https://pay.weixin.qq.com/wiki/doc/apiv3_partner/apis/chapter4_1_2.shtml</a>
   * 商户可以通过查询订单接口主动查询订单状态，完成下一步的业务逻辑。查询订单状态可通过微信支付订单号或商户订单号两种方式查询
   * 注意：
   *  查询订单可通过微信支付订单号和商户订单号两种方式查询，两种查询方式返回结果相同
   * 需要调用查询接口的情况：
   * ◆ 当商户后台、网络、服务器等出现异常，商户系统最终未接收到支付通知。
   * ◆ 调用支付接口后，返回系统错误或未知交易状态情况。
   * ◆ 调用付款码支付API，返回USERPAYING的状态。
   * ◆ 调用关单或撤销接口API之前，需确认支付状态。
   * 接口地址：
   *  https://api.mch.weixin.qq.com/v3/pay/partner/transactions/id/{transaction_id}
   *  https://api.mch.weixin.qq.com/v3/pay/partner/transactions/out-trade-no/{out_trade_no}
   * </pre>
   *
   * @param request 查询订单请求对象
   * @return the wx pay order query result
   * @throws WxPayException the wx pay exception
   */
  WxPayPartnerOrderQueryV3Result queryPartnerOrderV3(WxPayPartnerOrderQueryV3Request request) throws WxPayException;

  /**
   * <pre>
   * 合单查询订单API
   * 请求URL: https://api.mch.weixin.qq.com/v3/combine-transactions/out-trade-no/{combine_out_trade_no}
   * 文档地址: <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter5_1_11.shtml">https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter5_1_11.shtml</a>
   * </pre>
   *
   * @param combineOutTradeNo 合单商户订单号
   * @return 合单支付订单信息 combine query result
   * @throws WxPayException the wx pay exception
   */
  CombineQueryResult queryCombine(String combineOutTradeNo) throws WxPayException;

  /**
   * <pre>
   * 关闭订单.
   * 应用场景
   * 以下情况需要调用关单接口：
   * 1. 商户订单支付失败需要生成新单号重新发起支付，要对原订单号调用关单，避免重复支付；
   * 2. 系统下单后，用户支付超时，系统退出不再受理，避免用户继续，请调用关单接口。
   * 注意：订单生成后不能马上调用关单接口，最短调用时间间隔为5分钟。
   * 接口地址：https://api.mch.weixin.qq.com/pay/closeorder
   * 是否需要证书：   不需要。
   * </pre>
   *
   * @param outTradeNo 商户系统内部的订单号
   * @return the wx pay order close result
   * @throws WxPayException the wx pay exception
   */
  WxPayOrderCloseResult closeOrder(String outTradeNo) throws WxPayException;

  /**
   * <pre>
   * 关闭订单（适合于需要自定义子商户号和子商户appid的情形）.
   * 应用场景
   * 以下情况需要调用关单接口：
   * 1. 商户订单支付失败需要生成新单号重新发起支付，要对原订单号调用关单，避免重复支付；
   * 2. 系统下单后，用户支付超时，系统退出不再受理，避免用户继续，请调用关单接口。
   * 注意：订单生成后不能马上调用关单接口，最短调用时间间隔为5分钟。
   * 接口地址：https://api.mch.weixin.qq.com/pay/closeorder
   * 是否需要证书：   不需要。
   * </pre>
   *
   * @param request 关闭订单请求对象
   * @return the wx pay order close result
   * @throws WxPayException the wx pay exception
   */
  WxPayOrderCloseResult closeOrder(WxPayOrderCloseRequest request) throws WxPayException;

  /**
   * <pre>
   * 关闭订单
   * 应用场景
   * 以下情况需要调用关单接口：
   * 1、商户订单支付失败需要生成新单号重新发起支付，要对原订单号调用关单，避免重复支付；
   * 2、系统下单后，用户支付超时，系统退出不再受理，避免用户继续，请调用关单接口。
   * 注意：关单没有时间限制，建议在订单生成后间隔几分钟（最短5分钟）再调用关单接口，避免出现订单状态同步不及时导致关单失败。
   * 接口地址：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_1_3.shtml
   * </pre>
   *
   * @param outTradeNo 商户系统内部的订单号
   * @return the wx pay order close result
   * @throws WxPayException the wx pay exception
   */
  void closeOrderV3(String outTradeNo) throws WxPayException;

  /**
   * <pre>
   * 服务商关闭订单
   * 应用场景
   * 以下情况需要调用关单接口：
   * 1、商户订单支付失败需要生成新单号重新发起支付，要对原订单号调用关单，避免重复支付；
   * 2、系统下单后，用户支付超时，系统退出不再受理，避免用户继续，请调用关单接口。
   * 注意：关单没有时间限制，建议在订单生成后间隔几分钟（最短5分钟）再调用关单接口，避免出现订单状态同步不及时导致关单失败。
   * 接口地址：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_1_3.shtml
   * </pre>
   *
   * @param outTradeNo 商户系统内部的订单号
   * @return the wx pay order close result
   * @throws WxPayException the wx pay exception
   */
  void closePartnerOrderV3(String outTradeNo) throws WxPayException;

  /**
   * <pre>
   * 关闭订单
   * 应用场景
   * 以下情况需要调用关单接口：
   * 1、商户订单支付失败需要生成新单号重新发起支付，要对原订单号调用关单，避免重复支付；
   * 2、系统下单后，用户支付超时，系统退出不再受理，避免用户继续，请调用关单接口。
   * 注意：关单没有时间限制，建议在订单生成后间隔几分钟（最短5分钟）再调用关单接口，避免出现订单状态同步不及时导致关单失败。
   * 接口地址：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_1_3.shtml
   * </pre>
   *
   * @param request 关闭订单请求对象
   * @return the wx pay order close result
   * @throws WxPayException the wx pay exception
   */
  void closeOrderV3(WxPayOrderCloseV3Request request) throws WxPayException;

  /**
   * <pre>
   * 服务商关闭订单
   * 应用场景
   * 以下情况需要调用关单接口：
   * 1、商户订单支付失败需要生成新单号重新发起支付，要对原订单号调用关单，避免重复支付；
   * 2、系统下单后，用户支付超时，系统退出不再受理，避免用户继续，请调用关单接口。
   * 注意：关单没有时间限制，建议在订单生成后间隔几分钟（最短5分钟）再调用关单接口，避免出现订单状态同步不及时导致关单失败。
   * 接口地址：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_1_3.shtml
   * </pre>
   *
   * @param request 关闭订单请求对象
   * @return the wx pay order close result
   * @throws WxPayException the wx pay exception
   */
  void closePartnerOrderV3(WxPayPartnerOrderCloseV3Request request) throws WxPayException;

  /**
   * <pre>
   * 合单关闭订单API
   * 请求URL: https://api.mch.weixin.qq.com/v3/combine-transactions/out-trade-no/{combine_out_trade_no}/close
   * 文档地址: <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter5_1_12.shtml">https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter5_1_12.shtml</a>
   * </pre>
   *
   * @param request 请求对象
   * @throws WxPayException the wx pay exception
   */
  void closeCombine(CombineCloseRequest request) throws WxPayException;

  /**
   * 调用统一下单接口，并组装生成支付所需参数对象.
   *
   * @param <T>     请使用{@link com.github.binarywang.wxpay.bean.order}包下的类
   * @param request 统一下单请求参数
   * @return 返回 {@link com.github.binarywang.wxpay.bean.order}包下的类对象
   * @throws WxPayException the wx pay exception
   */
  <T> T createOrder(WxPayUnifiedOrderRequest request) throws WxPayException;

  /**
   * 调用统一下单接口，并组装生成支付所需参数对象.
   *
   * @param <T>               the type parameter
   * @param specificTradeType 将使用的交易方式，不能为 null
   * @param request           统一下单请求参数，设定的 tradeType 及配置里的 tradeType 将被忽略，转而使用 specificTradeType
   * @return 返回 {@link WxPayConstants.TradeType.Specific} 指定的类
   * @throws WxPayException the wx pay exception
   * @see WxPayService#createOrder(WxPayUnifiedOrderRequest) WxPayService#createOrder(WxPayUnifiedOrderRequest)WxPayService#createOrder(WxPayUnifiedOrderRequest)
   */
  <T> T createOrder(WxPayConstants.TradeType.Specific<T> specificTradeType, WxPayUnifiedOrderRequest request) throws WxPayException;

  /**
   * 统一下单(详见https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_1)
   * 在发起微信支付前，需要调用统一下单接口，获取"预支付交易会话标识"
   * 接口地址：https://api.mch.weixin.qq.com/pay/unifiedorder
   *
   * @param request 请求对象，注意一些参数如appid、mchid等不用设置，方法内会自动从配置对象中获取到（前提是对应配置中已经设置）
   * @return the wx pay unified order result
   * @throws WxPayException the wx pay exception
   */
  WxPayUnifiedOrderResult unifiedOrder(WxPayUnifiedOrderRequest request) throws WxPayException;

  /**
   * 调用统一下单接口，并组装生成支付所需参数对象.
   *
   * @param <T>       请使用{@link WxPayUnifiedOrderV3Result}里的内部类或字段
   * @param tradeType the trade type
   * @param request   统一下单请求参数
   * @return 返回 {@link WxPayUnifiedOrderV3Result}里的内部类或字段
   * @throws WxPayException the wx pay exception
   */
  <T> T createOrderV3(TradeTypeEnum tradeType, WxPayUnifiedOrderV3Request request) throws WxPayException;

  /**
   * 服务商模式调用统一下单接口，并组装生成支付所需参数对象.
   *
   * @param <T>       请使用{@link WxPayUnifiedOrderV3Result}里的内部类或字段
   * @param tradeType the trade type
   * @param request   统一下单请求参数
   * @return 返回 {@link WxPayUnifiedOrderV3Result}里的内部类或字段
   * @throws WxPayException the wx pay exception
   */
  <T> T createPartnerOrderV3(TradeTypeEnum tradeType, WxPayPartnerUnifiedOrderV3Request request) throws WxPayException;

  /**
   * 在发起微信支付前，需要调用统一下单接口，获取"预支付交易会话标识"
   *
   * @param tradeType the trade type
   * @param request   请求对象，注意一些参数如spAppid、spMchid等不用设置，方法内会自动从配置对象中获取到（前提是对应配置中已经设置）
   * @return the wx pay unified order result
   * @throws WxPayException the wx pay exception
   */
  WxPayUnifiedOrderV3Result unifiedPartnerOrderV3(TradeTypeEnum tradeType, WxPayPartnerUnifiedOrderV3Request request) throws WxPayException;

  /**
   * 在发起微信支付前，需要调用统一下单接口，获取"预支付交易会话标识"
   *
   * @param tradeType the trade type
   * @param request   请求对象，注意一些参数如appid、mchid等不用设置，方法内会自动从配置对象中获取到（前提是对应配置中已经设置）
   * @return the wx pay unified order result
   * @throws WxPayException the wx pay exception
   */
  WxPayUnifiedOrderV3Result unifiedOrderV3(TradeTypeEnum tradeType, WxPayUnifiedOrderV3Request request) throws WxPayException;

  /**
   * <pre>
   * 合单支付API(APP支付、JSAPI支付、H5支付、NATIVE支付).
   * 请求URL:
   *  https://api.mch.weixin.qq.com/v3/combine-transactions/app
   *  https://api.mch.weixin.qq.com/v3/combine-transactions/h5
   *  https://api.mch.weixin.qq.com/v3/combine-transactions/jsapi
   *  https://api.mch.weixin.qq.com/v3/combine-transactions/native
   * 文档地址: <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/open/pay/chapter2_9_3.shtml">https://pay.weixin.qq.com/wiki/doc/apiv3/open/pay/chapter2_9_3.shtml</a>
   * </pre>
   *
   * @param tradeType 支付方式
   * @param request   请求对象
   * @return 微信合单支付返回 combine transactions result
   * @throws WxPayException the wx pay exception
   */
  CombineTransactionsResult combine(TradeTypeEnum tradeType, CombineTransactionsRequest request) throws WxPayException;

  /**
   * <pre>
   * 合单支付API(APP支付、JSAPI支付、H5支付、NATIVE支付).
   * 请求URL:
   *  https://api.mch.weixin.qq.com/v3/combine-transactions/app
   *  https://api.mch.weixin.qq.com/v3/combine-transactions/h5
   *  https://api.mch.weixin.qq.com/v3/combine-transactions/jsapi
   *  https://api.mch.weixin.qq.com/v3/combine-transactions/native
   * 文档地址: <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/open/pay/chapter2_9_3.shtml">https://pay.weixin.qq.com/wiki/doc/apiv3/open/pay/chapter2_9_3.shtml</a>
   * </pre>
   *
   * @param <T>       the type parameter
   * @param tradeType 支付方式
   * @param request   请求对象
   * @return 调起支付需要的参数 t
   * @throws WxPayException the wx pay exception
   */
  <T> T combineTransactions(TradeTypeEnum tradeType, CombineTransactionsRequest request) throws WxPayException;

  /**
   * 该接口调用“统一下单”接口，并拼装发起支付请求需要的参数.
   * 详见https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=8_5
   *
   * @param request 请求对象，注意一些参数如appid、mchid等不用设置，方法内会自动从配置对象中获取到（前提是对应配置中已经设置）
   * @return the pay info
   * @throws WxPayException the wx pay exception
   * @deprecated 建议使用 {@link WxPayService#createOrder(WxPayUnifiedOrderRequest)}
   */
  @Deprecated
  Map<String, String> getPayInfo(WxPayUnifiedOrderRequest request) throws WxPayException;

  /**
   * 获取配置.
   *
   * @return the config
   */
  WxPayConfig getConfig();

  /**
   * 设置配置对象.
   *
   * @param config the config
   */
  void setConfig(WxPayConfig config);

  /**
   * <pre>
   * 微信支付-申请退款.
   * 详见 <a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_4">https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_4</a>
   * 接口链接：https://api.mch.weixin.qq.com/secapi/pay/refund
   * </pre>
   *
   * @param request 请求对象
   * @return 退款操作结果 wx pay refund result
   * @throws WxPayException the wx pay exception
   */
  WxPayRefundResult refund(WxPayRefundRequest request) throws WxPayException;

  /**
   * <pre>
   * 申请退款API（支持单品）.
   * 详见 <a href="https://pay.weixin.qq.com/wiki/doc/api/danpin.php?chapter=9_103&index=3">https://pay.weixin.qq.com/wiki/doc/api/danpin.php?chapter=9_103&index=3</a>
   *
   * 应用场景
   * 当交易发生之后一段时间内，由于买家或者卖家的原因需要退款时，卖家可以通过退款接口将支付款退还给买家，微信支付将在收到退款请求并且验证成功之后，按照退款规则将支付款按原路退到买家帐号上。
   *
   * 注意：
   * 1、交易时间超过一年的订单无法提交退款；
   * 2、微信支付退款支持单笔交易分多次退款，多次退款需要提交原支付订单的商户订单号和设置不同的退款单号。申请退款总金额不能超过订单金额。 一笔退款失败后重新提交，请不要更换退款单号，请使用原商户退款单号。
   * 3、请求频率限制：150qps，即每秒钟正常的申请退款请求次数不超过150次
   *     错误或无效请求频率限制：6qps，即每秒钟异常或错误的退款申请请求不超过6次
   * 4、每个支付订单的部分退款次数不能超过50次
   * 5、本接口支持单品优惠订单全额退款和单品优惠订单部分退款，推荐使用本接口，如果使用不支持单品优惠部分退款的历史接口，请看https://pay.weixin.qq.com/wiki/doc/api/jsapi_sl.php?chapter=9_4
   *
   * 接口地址
   * https://api.mch.weixin.qq.com/secapi/pay/refundv2
   * https://api2.mch.weixin.qq.com/secapi/pay/refundv2(备用域名)见跨城冗灾方案
   * </pre>
   *
   * @param request 请求对象
   * @return 退款操作结果 wx pay refund result
   * @throws WxPayException the wx pay exception
   */
  WxPayRefundResult refundV2(WxPayRefundRequest request) throws WxPayException;

  /**
   * <pre>
   * 申请退款API（支持单品）.
   * 详见 <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_1_9.shtml">https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_1_9.shtml</a>
   *
   * 应用场景
   * 当交易发生之后一年内，由于买家或者卖家的原因需要退款时，卖家可以通过退款接口将支付金额退还给买家，微信支付将在收到退款请求并且验证成功之后，将支付款按原路退还至买家账号上。
   *
   * 注意：
   * 1、交易时间超过一年的订单无法提交退款
   * 2、微信支付退款支持单笔交易分多次退款（不超50次），多次退款需要提交原支付订单的商户订单号和设置不同的退款单号。申请退款总金额不能超过订单金额。 一笔退款失败后重新提交，请不要更换退款单号，请使用原商户退款单号
   * 3、错误或无效请求频率限制：6qps，即每秒钟异常或错误的退款申请请求不超过6次
   * 4、每个支付订单的部分退款次数不能超过50次
   * 5、如果同一个用户有多笔退款，建议分不同批次进行退款，避免并发退款导致退款失败
   * 6、申请退款接口的返回仅代表业务的受理情况，具体退款是否成功，需要通过退款查询接口获取结果
   * 7、一个月之前的订单申请退款频率限制为：5000/min
   *
   * 接口地址
   * https://api.mch.weixin.qq.com/v3/refund/domestic/refunds
   * </pre>
   *
   * @param request 请求对象
   * @return 退款操作结果 wx pay refund result
   * @throws WxPayException the wx pay exception
   */
  WxPayRefundV3Result refundV3(WxPayRefundV3Request request) throws WxPayException;

  /**
   * <pre>
   * 微信支付-查询退款.
   * 应用场景：
   *  提交退款申请后，通过调用该接口查询退款状态。退款有一定延时，用零钱支付的退款20分钟内到账，
   *  银行卡支付的退款3个工作日后重新查询退款状态。
   * 详见 <a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_5">https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_5</a>
   * 接口链接：https://api.mch.weixin.qq.com/pay/refundquery
   * </pre>
   * 以下四个参数四选一
   *
   * @param transactionId 微信订单号
   * @param outTradeNo    商户订单号
   * @param outRefundNo   商户退款单号
   * @param refundId      微信退款单号
   * @return 退款信息 wx pay refund query result
   * @throws WxPayException the wx pay exception
   */
  WxPayRefundQueryResult refundQuery(String transactionId, String outTradeNo, String outRefundNo, String refundId)
    throws WxPayException;

  /**
   * <pre>
   * 微信支付-查询退款（适合于需要自定义子商户号和子商户appid的情形）.
   * 应用场景：
   *  提交退款申请后，通过调用该接口查询退款状态。退款有一定延时，用零钱支付的退款20分钟内到账，
   *  银行卡支付的退款3个工作日后重新查询退款状态。
   * 详见 <a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_5">https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_5</a>
   * 接口链接：https://api.mch.weixin.qq.com/pay/refundquery
   * </pre>
   *
   * @param request 微信退款单号
   * @return 退款信息 wx pay refund query result
   * @throws WxPayException the wx pay exception
   */
  WxPayRefundQueryResult refundQuery(WxPayRefundQueryRequest request) throws WxPayException;

  /**
   * <pre>
   * 微信支付-查询退款API（支持单品）.
   * 应用场景
   *    提交退款申请后，通过调用该接口查询退款状态。退款有一定延时，用零钱支付的退款20分钟内到账，银行卡支付的退款3个工作日后重新查询退款状态。
   * 注意：
   * 1、本接口支持查询单品优惠相关退款信息，且仅支持按微信退款单号或商户退款单号查询，若继续调用老查询退款接口，
   *    请见https://pay.weixin.qq.com/wiki/doc/api/jsapi_sl.php?chapter=9_5
   * 2、请求频率限制：300qps，即每秒钟正常的退款查询请求次数不超过300次
   * 3、错误或无效请求频率限制：6qps，即每秒钟异常或错误的退款查询请求不超过6次
   *
   * 接口地址
   * https://api.mch.weixin.qq.com/pay/refundqueryv2
   * https://api2.mch.weixin.qq.com/pay/refundqueryv2(备用域名)见跨城冗灾方案
   * 详见 <a href="https://pay.weixin.qq.com/wiki/doc/api/danpin.php?chapter=9_104&index=4">https://pay.weixin.qq.com/wiki/doc/api/danpin.php?chapter=9_104&index=4</a>
   * </pre>
   *
   * @param request 微信退款单号
   * @return 退款信息 wx pay refund query result
   * @throws WxPayException the wx pay exception
   */
  WxPayRefundQueryResult refundQueryV2(WxPayRefundQueryRequest request) throws WxPayException;

  /**
   * <pre>
   * 微信支付-查询退款
   * 应用场景：
   *  提交退款申请后，通过调用该接口查询退款状态。退款有一定延时，建议在提交退款申请后1分钟发起查询退款状态，一般来说零钱支付的退款5分钟内到账，银行卡支付的退款1-3个工作日到账。
   *  详见 <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_1_10.shtml">https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_1_10.shtml</a>
   * 接口链接：https://api.mch.weixin.qq.com/v3/refund/domestic/refunds/{out_refund_no}
   * </pre>
   *
   * @param outRefundNo 商户退款单号
   * @return 退款信息 wx pay refund query result
   * @throws WxPayException the wx pay exception
   */
  WxPayRefundQueryV3Result refundQueryV3(String outRefundNo) throws WxPayException;

  /**
   * <pre>
   * 微信支付-查询退款-直连商户
   * 应用场景：
   *  提交退款申请后，通过调用该接口查询退款状态。退款有一定延时，建议在提交退款申请后1分钟发起查询退款状态，一般来说零钱支付的退款5分钟内到账，银行卡支付的退款1-3个工作日到账。
   *  详见 <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_1_10.shtml">https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_1_10.shtml</a>
   * 接口链接：https://api.mch.weixin.qq.com/v3/refund/domestic/refunds/{out_refund_no}
   * </pre>
   *
   * @param request 微信退款单号
   * @return 退款信息 wx pay refund query result
   * @throws WxPayException the wx pay exception
   */
  WxPayRefundQueryV3Result refundQueryV3(WxPayRefundQueryV3Request request) throws WxPayException;

  /**
   * <pre>
   * 微信支付-查询退款-服务商
   * 应用场景：
   *  提交退款申请后，通过调用该接口查询退款状态。退款有一定延时，建议在提交退款申请后1分钟发起查询退款状态，一般来说零钱支付的退款5分钟内到账，银行卡支付的退款1-3个工作日到账。
   *  详见 <a href="https://pay.weixin.qq.com/wiki/doc/apiv3_partner/apis/chapter4_1_10.shtml">https://pay.weixin.qq.com/wiki/doc/apiv3_partner/apis/chapter4_1_10.shtml</a>
   * 接口链接：https://api.mch.weixin.qq.com/v3/refund/domestic/refunds/{out_refund_no}?sub_mchid={sub_mchid}
   * </pre>
   *
   * @param request 微信退款单号
   * @return 退款信息 wx pay refund query result
   * @throws WxPayException the wx pay exception
   */
  WxPayRefundQueryV3Result refundPartnerQueryV3(WxPayRefundQueryV3Request request) throws WxPayException;

  /**
   * 解析支付结果通知.
   * 详见https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_7
   *
   * @param xmlData the xml data
   * @return the wx pay order notify result
   * @throws WxPayException the wx pay exception
   */
  WxPayOrderNotifyResult parseOrderNotifyResult(String xmlData) throws WxPayException;

  /**
   * 解析支付结果通知.
   * 详见https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_7
   *
   * @param xmlData  the xml data
   * @param signType 签名类型
   * @return the wx pay order notify result
   * @throws WxPayException the wx pay exception
   */
  WxPayOrderNotifyResult parseOrderNotifyResult(String xmlData, String signType) throws WxPayException;

  /**
   * 解析支付结果v3通知. 直连商户模式
   * 详见https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_1_5.shtml
   *
   * @param notifyData 通知数据
   * @param header     通知头部数据，不传则表示不校验头
   * @return the wx pay order notify result
   * @throws WxPayException the wx pay exception
   */
  WxPayNotifyV3Result parseOrderNotifyV3Result(String notifyData, SignatureHeader header) throws WxPayException;

  /**
   * 服务商模式解析支付结果v3通知.
   * 详见https://pay.weixin.qq.com/wiki/doc/apiv3_partner/apis/chapter4_1_5.shtml
   *
   * @param notifyData 通知数据
   * @param header     通知头部数据，不传则表示不校验头
   * @return the wx pay order notify result
   * @throws WxPayException the wx pay exception
   */
  WxPayPartnerNotifyV3Result parsePartnerOrderNotifyV3Result(String notifyData, SignatureHeader header) throws WxPayException;

  /**
   * 支付服务商和直连商户两种模式
   * 详见https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_1_5.shtml
   *
   * @param notifyData 通知数据
   * @param header     通知头部数据，不传则表示不校验头
   * @param resultType 结果类型
   * @param dataType   结果数据类型
   * @return the wx pay order notify result
   * @throws WxPayException the wx pay exception
   */
  <T extends WxPayBaseNotifyV3Result<E>, E> T baseParseOrderNotifyV3Result(String notifyData, SignatureHeader header, Class<T> resultType, Class<E> dataType) throws WxPayException;

  /**
   * <pre>
   * 合单支付通知回调数据处理
   * 文档地址: <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter5_1_13.shtml">https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter5_1_13.shtml</a>
   * </pre>
   *
   * @param notifyData 通知数据
   * @param header     通知头部数据，不传则表示不校验头
   * @return 解密后通知数据 combine transactions notify result
   * @throws WxPayException the wx pay exception
   */
  CombineNotifyResult parseCombineNotifyResult(String notifyData, SignatureHeader header) throws WxPayException;

  /**
   * 解析退款结果通知
   * 详见https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_16&index=9
   *
   * @param xmlData the xml data
   * @return the wx pay refund notify result
   * @throws WxPayException the wx pay exception
   */
  WxPayRefundNotifyResult parseRefundNotifyResult(String xmlData) throws WxPayException;

  /**
   * 解析直连商户退款结果通知
   * 详见https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_16&index=9
   *
   * @param notifyData 通知数据
   * @param header     通知头部数据，不传则表示不校验头
   * @return the wx pay refund notify result
   * @throws WxPayException the wx pay exception
   */
  WxPayRefundNotifyV3Result parseRefundNotifyV3Result(String notifyData, SignatureHeader header) throws WxPayException;

  /**
   * 解析商家转账批次回调通知
   * https://pay.weixin.qq.com/docs/merchant/apis/batch-transfer-to-balance/transfer-batch-callback-notice.html
   *
   * @param notifyData
   * @param header
   * @return
   * @throws WxPayException
   */
  WxPayTransferBatchesNotifyV3Result parseTransferBatchesNotifyV3Result(String notifyData, SignatureHeader header) throws WxPayException;

  /**
   * 解析商家转账批次回调通知
   * https://pay.weixin.qq.com/doc/v3/merchant/4012712115
   *
   * @param notifyData
   * @param header
   * @return
   * @throws WxPayException
   */
  TransferBillsNotifyResult parseTransferBillsNotifyV3Result(String notifyData, SignatureHeader header) throws WxPayException;

  /**
   * 解析服务商模式退款结果通知
   * 详见https://pay.weixin.qq.com/wiki/doc/apiv3_partner/apis/chapter4_1_11.shtml
   *
   * @param notifyData 通知数据
   * @param header     通知头部数据，不传则表示不校验头
   * @return the wx pay refund notify result
   * @throws WxPayException the wx pay exception
   */
  WxPayPartnerRefundNotifyV3Result parsePartnerRefundNotifyV3Result(String notifyData, SignatureHeader header) throws WxPayException;

  /**
   * 解析扫码支付回调通知
   * 详见https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=6_4
   *
   * @param xmlData  the xml data
   * @param signType 签名类型
   * @return the wx scan pay notify result
   * @throws WxPayException the wx pay exception
   */
  WxScanPayNotifyResult parseScanPayNotifyResult(String xmlData, String signType) throws WxPayException;

  /**
   * 解析扫码支付回调通知
   * 详见https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=6_4
   *
   * @param xmlData the xml data
   * @return the wx scan pay notify result
   * @throws WxPayException the wx pay exception
   */
  WxScanPayNotifyResult parseScanPayNotifyResult(String xmlData) throws WxPayException;

  /**
   * <pre>
   * 扫码支付模式一生成二维码的方法。
   * 二维码中的内容为链接，形式为：
   * weixin://wxpay/bizpayurl?sign=XXXXX&appid=XXXXX&mch_id=XXXXX&product_id=XXXXXX&time_stamp=XXXXXX&nonce_str=XXXXX
   * 其中XXXXX为商户需要填写的内容，商户将该链接生成二维码，如需要打印发布二维码，需要采用此格式。商户可调用第三方库生成二维码图片。
   * 文档详见: <a href="https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=6_4">https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=6_4</a>
   * </pre>
   *
   * @param productId  产品Id
   * @param logoFile   商户logo图片的文件对象，可以为空
   * @param sideLength 要生成的二维码的边长，如果为空，则取默认值400
   * @return 生成的二维码的字节数组 byte [ ]
   */
  byte[] createScanPayQrcodeMode1(String productId, File logoFile, Integer sideLength) throws Exception;

  /**
   * <pre>
   * 扫码支付模式一生成二维码的方法.
   * 二维码中的内容为链接，形式为：
   * weixin://wxpay/bizpayurl?sign=XXXXX&appid=XXXXX&mch_id=XXXXX&product_id=XXXXXX&time_stamp=XXXXXX&nonce_str=XXXXX
   * 其中XXXXX为商户需要填写的内容，商户将该链接生成二维码，如需要打印发布二维码，需要采用此格式。商户可调用第三方库生成二维码图片。
   * 文档详见: <a href="https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=6_4">https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=6_4</a>
   * </pre>
   *
   * @param productId 产品Id
   * @return 生成的二维码URL连接 string
   */
  String createScanPayQrcodeMode1(String productId);

  /**
   * <pre>
   * 扫码支付模式二生成二维码的方法.
   * 对应链接格式：weixin：//wxpay/bizpayurl?sr=XXXXX。请商户调用第三方库将code_url生成二维码图片。
   * 该模式链接较短，生成的二维码打印到结账小票上的识别率较高。
   * 文档详见: <a href="https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=6_5">https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=6_5</a>
   * </pre>
   *
   * @param codeUrl    微信返回的交易会话的二维码链接
   * @param logoFile   商户logo图片的文件对象，可以为空
   * @param sideLength 要生成的二维码的边长，如果为空，则取默认值400
   * @return 生成的二维码的字节数组 byte [ ]
   */
  byte[] createScanPayQrcodeMode2(String codeUrl, File logoFile, Integer sideLength) throws Exception;

  /**
   * <pre>
   * 交易保障.
   * 应用场景：
   *  商户在调用微信支付提供的相关接口时，会得到微信支付返回的相关信息以及获得整个接口的响应时间。
   *  为提高整体的服务水平，协助商户一起提高服务质量，微信支付提供了相关接口调用耗时和返回信息的主动上报接口，
   *  微信支付可以根据商户侧上报的数据进一步优化网络部署，完善服务监控，和商户更好的协作为用户提供更好的业务体验。
   * 接口地址： <a href="https://api.mch.weixin.qq.com/payitil/report">https://api.mch.weixin.qq.com/payitil/report</a>
   * 是否需要证书：不需要
   * </pre>
   *
   * @param request the request
   * @throws WxPayException the wx pay exception
   */
  void report(WxPayReportRequest request) throws WxPayException;

  /**
   * <pre>
   * 下载对账单.
   * 商户可以通过该接口下载历史交易清单。比如掉单、系统错误等导致商户侧和微信侧数据不一致，通过对账单核对后可校正支付状态。
   * 注意：
   * 1、微信侧未成功下单的交易不会出现在对账单中。支付成功后撤销的交易会出现在对账单中，跟原支付单订单号一致，bill_type为REVOKED；
   * 2、微信在次日9点启动生成前一天的对账单，建议商户10点后再获取；
   * 3、对账单中涉及金额的字段单位为“元”。
   * 4、对账单接口只能下载三个月以内的账单。
   * 接口链接：<a href="https://api.mch.weixin.qq.com/pay/downloadbill">https://api.mch.weixin.qq.com/pay/downloadbill</a>
   * 详情请见: <a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_6">下载对账单</a>
   * </pre>
   *
   * @param billDate   对账单日期 bill_date 下载对账单的日期，格式：20140603
   * @param billType   账单类型 bill_type ALL，返回当日所有订单信息，默认值，SUCCESS，返回当日成功支付的订单，REFUND，返回当日退款订单
   * @param tarType    压缩账单 tar_type 非必传参数，固定值：GZIP，返回格式为.gzip的压缩包账单。不传则默认为数据流形式。
   * @param deviceInfo 设备号 device_info 非必传参数，终端设备号
   * @return 对账内容原始字符串 string
   * @throws WxPayException the wx pay exception
   */
  String downloadRawBill(String billDate, String billType, String tarType, String deviceInfo) throws WxPayException;

  /**
   * <pre>
   * 下载对账单（适合于需要自定义子商户号和子商户appid的情形）.
   * 商户可以通过该接口下载历史交易清单。比如掉单、系统错误等导致商户侧和微信侧数据不一致，通过对账单核对后可校正支付状态。
   * 注意：
   * 1、微信侧未成功下单的交易不会出现在对账单中。支付成功后撤销的交易会出现在对账单中，跟原支付单订单号一致，bill_type为REVOKED；
   * 2、微信在次日9点启动生成前一天的对账单，建议商户10点后再获取；
   * 3、对账单中涉及金额的字段单位为“元”。
   * 4、对账单接口只能下载三个月以内的账单。
   * 接口链接：https://api.mch.weixin.qq.com/pay/downloadbill
   * 详情请见: <a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_6">下载对账单</a>
   * </pre>
   *
   * @param request 下载对账单请求
   * @return 对账内容原始字符串 string
   * @throws WxPayException the wx pay exception
   */
  String downloadRawBill(WxPayDownloadBillRequest request) throws WxPayException;

  /**
   * <pre>
   * 下载对账单.
   * 商户可以通过该接口下载历史交易清单。比如掉单、系统错误等导致商户侧和微信侧数据不一致，通过对账单核对后可校正支付状态。
   * 注意：
   * 1、微信侧未成功下单的交易不会出现在对账单中。支付成功后撤销的交易会出现在对账单中，跟原支付单订单号一致，bill_type为REVOKED；
   * 2、微信在次日9点启动生成前一天的对账单，建议商户10点后再获取；
   * 3、对账单中涉及金额的字段单位为“元”。
   * 4、对账单接口只能下载三个月以内的账单。
   * 接口链接：https://api.mch.weixin.qq.com/pay/downloadbill
   * 详情请见: <a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_6">下载对账单</a>
   * </pre>
   *
   * @param billDate   对账单日期 bill_date 下载对账单的日期，格式：20140603
   * @param billType   账单类型 bill_type ALL，返回当日所有订单信息，默认值，SUCCESS，返回当日成功支付的订单，REFUND，返回当日退款订单
   * @param tarType    压缩账单 tar_type 非必传参数，固定值：GZIP，返回格式为.gzip的压缩包账单。不传则默认为数据流形式。
   * @param deviceInfo 设备号 device_info 非必传参数，终端设备号
   * @return WxPayBillResult对象 wx pay bill result
   * @throws WxPayException the wx pay exception
   */
  WxPayBillResult downloadBill(String billDate, String billType, String tarType, String deviceInfo) throws WxPayException;

  /**
   * <pre>
   * 下载对账单（适合于需要自定义子商户号和子商户appid的情形）.
   * 商户可以通过该接口下载历史交易清单。比如掉单、系统错误等导致商户侧和微信侧数据不一致，通过对账单核对后可校正支付状态。
   * 注意：
   * 1、微信侧未成功下单的交易不会出现在对账单中。支付成功后撤销的交易会出现在对账单中，跟原支付单订单号一致，bill_type为REVOKED；
   * 2、微信在次日9点启动生成前一天的对账单，建议商户10点后再获取；
   * 3、对账单中涉及金额的字段单位为“元”。
   * 4、对账单接口只能下载三个月以内的账单。
   * 接口链接：https://api.mch.weixin.qq.com/pay/downloadbill
   * 详情请见: <a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_6">下载对账单</a>
   * </pre>
   *
   * @param request 下载对账单请求
   * @return WxPayBillResult对象 wx pay bill result
   * @throws WxPayException the wx pay exception
   */
  WxPayBillResult downloadBill(WxPayDownloadBillRequest request) throws WxPayException;

  /**
   * <pre>
   * 下载资金账单.
   * 商户可以通过该接口下载自2017年6月1日起 的历史资金流水账单。
   * 注意：
   * 1、资金账单中的数据反映的是商户微信账户资金变动情况；
   * 2、当日账单在次日上午9点开始生成，建议商户在上午10点以后获取；
   * 3、资金账单中涉及金额的字段单位为“元”。
   * 接口链接：https://api.mch.weixin.qq.com/pay/downloadfundflow
   * 详情请见: <a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_18">下载对账单</a>
   * </pre>
   *
   * @param billDate    资金账单日期 bill_date 下载对账单的日期，格式：20140603
   * @param accountType 资金账户类型 account_type Basic，基本账户，Operation，运营账户，Fees，手续费账户
   * @param tarType     压缩账单 tar_type 非必传参数，固定值：GZIP，返回格式为.gzip的压缩包账单。不传则默认为数据流形式。
   * @return WxPayFundFlowResult对象 wx pay fund flow result
   * @throws WxPayException the wx pay exception
   */
  WxPayFundFlowResult downloadFundFlow(String billDate, String accountType, String tarType) throws WxPayException;

  /**
   * <pre>
   * 下载资金账单.
   * 商户可以通过该接口下载自2017年6月1日起 的历史资金流水账单。
   * 注意：
   * 1、资金账单中的数据反映的是商户微信账户资金变动情况；
   * 2、当日账单在次日上午9点开始生成，建议商户在上午10点以后获取；
   * 3、资金账单中涉及金额的字段单位为“元”。
   * 接口链接：https://api.mch.weixin.qq.com/pay/downloadfundflow
   * 详情请见: <a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_18">下载对账单</a>
   * </pre>
   *
   * @param request 下载资金流水请求
   * @return WxPayFundFlowResult对象 wx pay fund flow result
   * @throws WxPayException the wx pay exception
   */
  WxPayFundFlowResult downloadFundFlow(WxPayDownloadFundFlowRequest request) throws WxPayException;

  /**
   * <pre>
   * 申请交易账单API
   * 微信支付按天提供交易账单文件，商户可以通过该接口获取账单文件的下载地址。文件内包含交易相关的金额、时间、营销等信息，供商户核对订单、退款、银行到账等情况。
   * 注意：
   * • 微信侧未成功下单的交易不会出现在对账单中。支付成功后撤销的交易会出现在对账单中，跟原支付单订单号一致；
   * • 对账单中涉及金额的字段单位为“元”；
   * • 对账单接口只能下载三个月以内的账单。
   * 接口链接：<a href="https://api.mch.weixin.qq.com/v3/bill/tradebill">https://api.mch.weixin.qq.com/v3/bill/tradebill</a>
   * 详情请见: <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_1_6.shtml">申请交易账单</a>
   * </pre>
   *
   * @param request 申请账单请求
   * @return Result对象 apply trade bill result
   * @throws WxPayException the wx pay exception
   */
  WxPayApplyBillV3Result applyTradeBill(WxPayApplyTradeBillV3Request request) throws WxPayException;

  /**
   * <pre>
   * 申请资金账单API
   * 微信支付按天提供微信支付账户的资金流水账单文件，商户可以通过该接口获取账单文件的下载地址。文件内包含该账户资金操作相关的业务单号、收支金额、记账时间等信息，供商户进行核对。
   * 注意：
   * • 资金账单中的数据反映的是商户微信支付账户资金变动情况；
   * • 对账单中涉及金额的字段单位为“元”。
   * 接口链接：<a href="https://api.mch.weixin.qq.com/v3/bill/fundflowbill">https://api.mch.weixin.qq.com/v3/bill/fundflowbill</a>
   * 详情请见: <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_1_7.shtml">申请资金账单</a>
   * </pre>
   *
   * @param request 申请账单请求
   * @return Result对象 apply fund flow bill result
   * @throws WxPayException the wx pay exception
   */
  WxPayApplyBillV3Result applyFundFlowBill(WxPayApplyFundFlowBillV3Request request) throws WxPayException;

  /**
   * <pre>
   * 下载账单API
   * 下载账单API为通用接口，交易/资金账单都可以通过该接口获取到对应的账单。
   * 注意：
   * • 账单文件的下载地址的有效时间为30s。
   * • 强烈建议商户将实际账单文件的哈希值和之前从接口获取到的哈希值进行比对，以确认数据的完整性。
   * • 该接口响应的信息请求头中不包含微信接口响应的签名值，因此需要跳过验签的流程
   * 接口链接：通过申请账单接口获取到“download_url”，URL有效期30s
   * 详情请见: <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_1_8.shtml">下载账单</a>
   * </pre>
   *
   * @param url 微信返回的账单地址。
   * @return 返回数据 return input stream
   * @throws WxPayException the wx pay exception
   */
  InputStream downloadBill(String url) throws WxPayException;

  /**
   * <pre>
   * 提交付款码支付.
   * 文档地址：<a href="https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_10&index=1">https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_10&index=1</a>
   * 应用场景：
   * 收银员使用扫码设备读取微信用户刷卡授权码以后，二维码或条码信息传送至商户收银台，由商户收银台或者商户后台调用该接口发起支付。
   * 提醒1：提交支付请求后微信会同步返回支付结果。当返回结果为“系统错误”时，商户系统等待5秒后调用【查询订单API】，查询支付实际交易结果；当返回结果为“USERPAYING”时，商户系统可设置间隔时间(建议10秒)重新查询支付结果，直到支付成功或超时(建议30秒)；
   * 提醒2：在调用查询接口返回后，如果交易状况不明晰，请调用【撤销订单API】，此时如果交易失败则关闭订单，该单不能再支付成功；如果交易成功，则将扣款退回到用户账户。当撤销无返回或错误时，请再次调用。注意：请勿扣款后立即调用【撤销订单API】,建议至少15秒后再调用。撤销订单API需要双向证书。
   * 接口地址：   <a href="https://api.mch.weixin.qq.com/pay/micropay">https://api.mch.weixin.qq.com/pay/micropay</a>
   * 是否需要证书：不需要。
   * </pre>
   *
   * @param request the request
   * @return the wx pay micropay result
   * @throws WxPayException the wx pay exception
   */
  WxPayMicropayResult micropay(WxPayMicropayRequest request) throws WxPayException;

  /**
   * <pre>
   * 付款码支付API.
   * 文档地址：<a href="https://pay.weixin.qq.com/docs/merchant/apis/code-payment-v3/direct/code-pay.html">https://pay.weixin.qq.com/docs/merchant/apis/code-payment-v3/direct/code-pay.html</a>
   * 应用场景：
   * 收银员使用扫码设备读取微信用户付款码以后，二维码或条码信息会传送至商户收银台，由商户收银台或者商户后台调用该接口发起支付。
   * 提醒1：提交支付请求后微信会同步返回支付结果。当返回结果为“系统错误”时，商户系统等待5秒后调用【查询订单API】，查询支付实际交易结果；当返回结果为“USERPAYING”时，商户系统可设置间隔时间(建议10秒)重新查询支付结果，直到支付成功或超时(建议30秒)；
   * 提醒2：在调用查询接口返回后，如果交易状况不明晰，请调用【撤销订单API】，此时如果交易失败则关闭订单，该单不能再支付成功；如果交易成功，则将扣款退回到用户账户。当撤销无返回或错误时，请再次调用。注意：请勿扣款后立即调用【撤销订单API】,建议至少15秒后再调用。撤销订单API需要双向证书。
   * 接口地址：   <a href="https://pay.weixin.qq.com/docs/merchant/apis/code-payment-v3/direct/reverse.html">https://pay.weixin.qq.com/docs/merchant/apis/code-payment-v3/direct/reverse.html</a>
   * 是否需要证书：不需要。
   * </pre>
   *
   * @param request the request
   * @return the wx codepay result
   * @throws WxPayException the wx pay exception
   */
  WxPayCodepayResult codepay(WxPayCodepayRequest request) throws WxPayException;


  /**
   * <pre>
   * 撤销订单API.
   * 文档地址：https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_11&index=3
   * 应用场景：
   *  支付交易返回失败或支付系统超时，调用该接口撤销交易。如果此订单用户支付失败，微信支付系统会将此订单关闭；
   *  如果用户支付成功，微信支付系统会将此订单资金退还给用户。
   *  注意：7天以内的交易单可调用撤销，其他正常支付的单如需实现相同功能请调用申请退款API。
   *  提交支付交易后调用【查询订单API】，没有明确的支付结果再调用【撤销订单API】。
   *  调用支付接口后请勿立即调用撤销订单API，建议支付后至少15s后再调用撤销订单接口。
   *  接口链接 ：https://api.mch.weixin.qq.com/secapi/pay/reverse
   *  是否需要证书：请求需要双向证书。
   * </pre>
   *
   * @param request the request
   * @return the wx pay order reverse result
   * @throws WxPayException the wx pay exception
   */
  WxPayOrderReverseResult reverseOrder(WxPayOrderReverseRequest request) throws WxPayException;

  /**
   * <pre>
   * 撤销订单API.
   * 文档地址：https://pay.weixin.qq.com/docs/merchant/apis/code-payment-v3/direct/reverse.html
   * 应用场景：
   *  支付交易返回失败或支付系统超时，调用该接口撤销交易。如果此订单用户支付失败，微信支付系统会将此订单关闭；
   *  如果用户支付成功，微信支付系统会将此订单资金退还给用户。
   *  注意：7天以内的交易单可调用撤销，其他正常支付的单如需实现相同功能请调用申请退款API。
   *  提交支付交易后调用【查询订单API】，没有明确的支付结果再调用【撤销订单API】。
   *  调用支付接口后请勿立即调用撤销订单API，建议支付后至少15s后再调用撤销订单接口。
   *  接口链接 ：https://pay.weixin.qq.com/docs/merchant/apis/code-payment-v3/direct/reverse.html
   *  是否需要证书：请求需要双向证书。
   * </pre>
   *
   * @param request the request
   * @return the wx pay order reverse result
   * @throws WxPayException the wx pay exception
   */
  WxPayOrderReverseV3Result reverseOrderV3(WxPayOrderReverseV3Request request) throws WxPayException;

  /**
   * <pre>
   * 撤销订单API.
   * 文档地址：https://pay.weixin.qq.com/docs/merchant/apis/code-payment-v3/direct/reverse.html
   * 应用场景：
   *  支付交易返回失败或支付系统超时，调用该接口撤销交易。如果此订单用户支付失败，微信支付系统会将此订单关闭；
   *  如果用户支付成功，微信支付系统会将此订单资金退还给用户。
   *  注意：7天以内的交易单可调用撤销，其他正常支付的单如需实现相同功能请调用申请退款API。
   *  提交支付交易后调用【查询订单API】，没有明确的支付结果再调用【撤销订单API】。
   *  调用支付接口后请勿立即调用撤销订单API，建议支付后至少15s后再调用撤销订单接口。
   *  接口链接 ：https://pay.weixin.qq.com/docs/merchant/apis/code-payment-v3/direct/reverse.html
   *  是否需要证书：请求需要双向证书。
   * </pre>
   *
   * @param outTradeNo 商户系统内部的订单号
   * @return the wx pay order reverse result
   * @throws WxPayException the wx pay exception
   */
  WxPayOrderReverseV3Result reverseOrderV3(String outTradeNo) throws WxPayException;


  /**
   * <pre>
   *  转换短链接.
   *  文档地址：
   *     <a href="https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_9&index=8">https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_9&index=8</a>
   *  应用场景：
   *     该接口主要用于扫码原生支付模式一中的二维码链接转成短链接(weixin://wxpay/s/XXXXXX)，减小二维码数据量，提升扫描速度和精确度。
   *  接口地址：<a href="https://api.mch.weixin.qq.com/tools/shorturl">https://api.mch.weixin.qq.com/tools/shorturl</a>
   *  是否需要证书：否
   * </pre>
   *
   * @param request 请求对象
   * @return the string
   * @throws WxPayException the wx pay exception
   */
  String shorturl(WxPayShorturlRequest request) throws WxPayException;

  /**
   * <pre>
   *  转换短链接.
   * </pre>
   *
   * @param longUrl 需要被压缩的网址
   * @return the string
   * @throws WxPayException the wx pay exception
   * @see WxPayService#shorturl(WxPayShorturlRequest) WxPayService#shorturl(WxPayShorturlRequest)WxPayService#shorturl(WxPayShorturlRequest)WxPayService#shorturl(WxPayShorturlRequest)
   */
  String shorturl(String longUrl) throws WxPayException;

  /**
   * <pre>
   * 授权码查询OPENID接口.
   *    通过授权码查询公众号Openid，调用查询后，该授权码只能由此商户号发起扣款，直至授权码更新。
   * 文档地址：
   *    <a href="https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_13&index=9">https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_13&index=9</a>
   * 接口链接:
   *    https://api.mch.weixin.qq.com/tools/authcodetoopenid
   * </pre>
   *
   * @param request 请求对象
   * @return openid string
   * @throws WxPayException the wx pay exception
   */
  String authcode2Openid(WxPayAuthcode2OpenidRequest request) throws WxPayException;

  /**
   * <pre>
   * 授权码查询OPENID接口.
   * </pre>
   *
   * @param authCode 授权码
   * @return openid string
   * @throws WxPayException the wx pay exception
   * @see WxPayService#authcode2Openid(WxPayAuthcode2OpenidRequest) WxPayService#authcode2Openid(WxPayAuthcode2OpenidRequest)WxPayService#authcode2Openid(WxPayAuthcode2OpenidRequest)WxPayService#authcode2Openid(WxPayAuthcode2OpenidRequest)
   */
  String authcode2Openid(String authCode) throws WxPayException;

  /**
   * <pre>
   * 获取仿真测试系统的验签密钥.
   * 请求Url： https://api.mch.weixin.qq.com/xdc/apiv2getsignkey/sign/getsignkey
   * 是否需要证书： 否
   * 请求方式： POST
   * 文档地址：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=23_1
   * </pre>
   *
   * @return the sandbox sign key
   * @throws WxPayException the wx pay exception
   */
  String getSandboxSignKey() throws WxPayException;

  /**
   * <pre>
   * 发放代金券
   * 接口请求链接：https://api.mch.weixin.qq.com/mmpaymkttransfers/send_coupon
   * 是否需要证书：请求需要双向证书。
   * 文档地址：https://pay.weixin.qq.com/wiki/doc/api/tools/sp_coupon.php?chapter=12_3
   * </pre>
   *
   * @param request the request
   * @return the wx pay coupon send result
   * @throws WxPayException the wx pay exception
   */
  WxPayCouponSendResult sendCoupon(WxPayCouponSendRequest request) throws WxPayException;

  /**
   * <pre>
   * 查询代金券批次.
   * 接口请求链接：https://api.mch.weixin.qq.com/mmpaymkttransfers/query_coupon_stock
   * 文档地址：https://pay.weixin.qq.com/wiki/doc/api/tools/sp_coupon.php?chapter=12_4
   * </pre>
   *
   * @param request the request
   * @return the wx pay coupon stock query result
   * @throws WxPayException the wx pay exception
   */
  WxPayCouponStockQueryResult queryCouponStock(WxPayCouponStockQueryRequest request) throws WxPayException;

  /**
   * <pre>
   * 查询代金券信息.
   * 接口请求链接：https://api.mch.weixin.qq.com/mmpaymkttransfers/querycouponsinfo
   * 文档地址：https://pay.weixin.qq.com/wiki/doc/api/tools/sp_coupon.php?chapter=12_5
   * </pre>
   *
   * @param request the request
   * @return the wx pay coupon info query result
   * @throws WxPayException the wx pay exception
   */
  WxPayCouponInfoQueryResult queryCouponInfo(WxPayCouponInfoQueryRequest request) throws WxPayException;

  /**
   * 获取微信请求数据，方便接口调用方获取处理.
   *
   * @return the wx api data
   */
  WxPayApiData getWxApiData();

  /**
   * <pre>
   * 拉取订单评价数据.
   * 商户可以通过该接口拉取用户在微信支付交易记录中针对你的支付记录进行的评价内容。商户可结合商户系统逻辑对该内容数据进行存储、分析、展示、客服回访以及其他使用。如商户业务对评价内容有依赖，可主动引导用户进入微信支付交易记录进行评价。
   * 注意：
   * 1. 该内容所有权为提供内容的微信用户，商户在使用内容的过程中应遵从用户意愿
   * 2. 一次最多拉取200条评价数据，可根据时间区间分批次拉取
   * 3. 接口只能拉取最近三个月以内的评价数据
   * 接口链接：https://api.mch.weixin.qq.com/billcommentsp/batchquerycomment
   * 是否需要证书：需要
   * 文档地址：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_17&index=10
   * </pre>
   *
   * @param beginDate 开始时间
   * @param endDate   结束时间
   * @param offset    位移
   * @param limit     条数，建议填null，否则接口会报签名错误
   * @return the string
   * @throws WxPayException the wx pay exception
   */
  String queryComment(Date beginDate, Date endDate, Integer offset, Integer limit) throws WxPayException;

  /**
   * <pre>
   * 拉取订单评价数据.
   * 商户可以通过该接口拉取用户在微信支付交易记录中针对你的支付记录进行的评价内容。商户可结合商户系统逻辑对该内容数据进行存储、分析、展示、客服回访以及其他使用。如商户业务对评价内容有依赖，可主动引导用户进入微信支付交易记录进行评价。
   * 注意：
   * 1. 该内容所有权为提供内容的微信用户，商户在使用内容的过程中应遵从用户意愿
   * 2. 一次最多拉取200条评价数据，可根据时间区间分批次拉取
   * 3. 接口只能拉取最近三个月以内的评价数据
   * 接口链接：https://api.mch.weixin.qq.com/billcommentsp/batchquerycomment
   * 是否需要证书：需要
   * 文档地址：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_17&index=10
   * </pre>
   *
   * @param request 查询请求
   * @return the string
   * @throws WxPayException the wx pay exception
   */
  String queryComment(WxPayQueryCommentRequest request) throws WxPayException;

  /**
   * <pre>
   * 获取微信刷脸支付凭证.
   * 接口请求链接：https://payapp.weixin.qq.com/face/get_wxpayface_authinfo
   * 文档地址：https://pay.weixin.qq.com/wiki/doc/api/tools/sp_coupon.php?chapter=12_5
   * </pre>
   *
   * @param request the request
   * @return the wx pay get face authinfo result
   * @throws WxPayException the wx pay exception
   */
  WxPayFaceAuthInfoResult getWxPayFaceAuthInfo(WxPayFaceAuthInfoRequest request) throws WxPayException;

  /**
   * <pre>
   * 提交刷脸支付.
   * 文档地址：https://share.weiyun.com/5dxUgCw
   * 应用场景：
   * 用户在商超，便利店，餐饮等场景，在屏幕上通过刷脸完成支付。
   * 步骤1：用户在自助收银机上点击“刷脸支付”；
   * 步骤2：发起人脸识别，摄像头自动抓取识别用户人脸，提示用户输入11位手机号码；
   * 步骤3：商户收银系统提交刷脸支付；
   * 步骤4：微信支付后台收到支付请求，验证人脸信息，返回支付结果给商户收银系统。
   * 是否需要证书：不需要。
   * </pre>
   *
   * @param request the request
   * @return the wx pay facepay result
   * @throws WxPayException the wx pay exception
   */
  WxPayFacepayResult facepay(WxPayFacepayRequest request) throws WxPayException;

  /**
   * 查询汇率
   * <pre>
   * 应用场景：商户网站的商品以外币标价时，通过该接口可以实时查询到微信使用的转换汇率。汇率更新时间为北京时间上午10:00，一天更新一次。
   * 文档地址：https://pay.weixin.qq.com/wiki/doc/api/app/app_jw.php?chapter=9_15&index=12
   * 接口链接：https://api.mch.weixin.qq.com/pay/queryexchagerate
   * </pre>
   *
   * @param feeType 外币币种
   * @param date    日期，格式为yyyyMMdd，如2009年12月25日表示为20091225。时区为GMT+8 beijing
   * @return . wx pay query exchange rate result
   * @throws WxPayException .
   */
  WxPayQueryExchangeRateResult queryExchangeRate(String feeType, String date) throws WxPayException;

  /**
   * 解析投诉通知
   * 详见https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter10_2_16.shtml
   *
   * @param notifyData 通知数据
   * @param header     通知头部数据，不传则表示不校验头
   * @return the wx pay refund notify result
   * @throws WxPayException the wx pay exception
   */
  ComplaintNotifyResult parseComplaintNotifyResult(String notifyData, SignatureHeader header) throws WxPayException;

  /**
   * 获取消费者投诉服务类.
   *
   * @return the complaints service
   */
  ComplaintService getComplaintsService();


  /**
   * 获取银行组件服务
   *
   * @return 银行组件服务
   */
  BankService getBankService();

  /**
   * 获取商家转账到零钱服务类.
   *
   * @return the transfers service
   */
  TransferService getTransferService();

  /**
   * 获取服务商支付分服务类
   *
   * @return the partner pay score service
   */
  PartnerPayScoreService getPartnerPayScoreService();

  /**
   * 获取服务商直股份签约计划服务类
   *
   * @return the partner pay score sign plan service
   */
  PartnerPayScoreSignPlanService getPartnerPayScoreSignPlanService();
}
