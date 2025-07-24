package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.request.*;
import com.github.binarywang.wxpay.bean.result.*;
import com.github.binarywang.wxpay.exception.WxPayException;

/**
 * <pre>
 *   微信签约代扣相关接口.
 *   <a href="https://pay.weixin.qq.com/wiki/doc/api/wxpay_v2/papay/chapter2_8.shtml">https://pay.weixin.qq.com/wiki/doc/api/wxpay_v2/papay/chapter2_8.shtml</a>
 *  </pre>
 *
 * @author chenliang
 * created on  2021 -08-02 4:50 下午
 */
public interface WxEntrustPapService {

  /**
   * <pre>
   *   获取公众号纯签约链接,
   *   详见：<a href="https://pay.weixin.qq.com/wiki/doc/api/wxpay_v2/papay/chapter3_1.shtml">https://pay.weixin.qq.com/wiki/doc/api/wxpay_v2/papay/chapter3_1.shtml</a>
   *   该接口返回一个签约链接，该链接只能在微信内打开
   * </pre>
   *
   * @param wxMpEntrustRequest the wx mp entrust request
   * @return string
   * @throws WxPayException the wx pay exception
   */
  String mpSign(WxMpEntrustRequest wxMpEntrustRequest) throws WxPayException;

  /**
   * <pre>
   *   获取小程序纯签约参数json
   *   详见：<a href="https://pay.weixin.qq.com/wiki/doc/api/wxpay_v2/papay/chapter3_3.shtml">https://pay.weixin.qq.com/wiki/doc/api/wxpay_v2/papay/chapter3_3.shtml</a>
   *   返回一个json 前端用来拉起一个新的签约小程序进行签约
   * </pre>
   *
   * @param wxMaEntrustRequest the wx ma entrust request
   * @return string
   * @throws WxPayException the wx pay exception
   */
  String maSign(WxMaEntrustRequest wxMaEntrustRequest) throws WxPayException;

  /**
   * <pre>
   *   获取h5纯签约支付跳转链接
   *   详见：<a href="https://pay.weixin.qq.com/wiki/doc/api/wxpay_v2/papay/chapter3_4.shtml">https://pay.weixin.qq.com/wiki/doc/api/wxpay_v2/papay/chapter3_4.shtml</a>
   *   返回一个签约链接  在浏览器请求链接拉起微信
   * </pre>
   *
   * @param wxH5EntrustRequest the wx h 5 entrust request
   * @return wx h 5 entrust result
   * @throws WxPayException the wx pay exception
   */
  WxH5EntrustResult h5Sign(WxH5EntrustRequest wxH5EntrustRequest) throws WxPayException;

  /**
   * <pre>
   *   支付中签约
   *   详见：<a href="https://pay.weixin.qq.com/wiki/doc/api/wxpay_v2/papay/chapter3_5.shtml">https://pay.weixin.qq.com/wiki/doc/api/wxpay_v2/papay/chapter3_5.shtml</a>
   *   请求微信 若微信内请求 需要构造json返回，
   *   若h5请求 直接使用mweb_url 链接即可拉起微信
   * </pre>
   *
   * @param wxPayEntrustRequest the wx pay entrust request
   * @return wx pay entrust result
   * @throws WxPayException the wx pay exception
   */
  WxPayEntrustResult paySign(WxPayEntrustRequest wxPayEntrustRequest) throws WxPayException;

  /**
   * 申请扣款
   * <pre>
   *   申请扣款
   *   详见：<a href="https://pay.weixin.qq.com/wiki/doc/api/wxpay_v2/papay/chapter3_8.shtml">https://pay.weixin.qq.com/wiki/doc/api/wxpay_v2/papay/chapter3_8.shtml</a>
   *   请求微信发起委托扣款，扣款额度和次数由使用的签约模板限制，
   *   该扣款接口是立即扣款 无延时 扣款前无消息通知。
   *
   *   • 特殊情况：周期扣费为通知后24小时扣费方式情况下，如果用户为首次签约（包含解约后重新签约），
   *   从用户签约成功时间开始算，商户在12小时内发起的扣款，会被立即执行，无延迟。商户超过12小时以后发起的扣款，都按24小时扣费规则执行
   * </pre>
   *
   * @param wxWithholdRequest the wx withhold request
   * @return wx withhold result
   * @throws WxPayException the wx pay exception
   */
  WxWithholdResult withhold(WxWithholdRequest wxWithholdRequest) throws WxPayException;

  /**
   * 服务商模式的申请扣款
   * <pre>
   *   申请扣款
   *   详见：<a href="https://pay.weixin.qq.com/wiki/doc/api/wxpay_v2/papay/chapter5_8.shtml">https://pay.weixin.qq.com/wiki/doc/api/wxpay_v2/papay/chapter5_8.shtml</a>
   *   请求微信发起委托扣款，扣款额度和次数由使用的签约模板限制，
   *   该扣款接口是立即扣款 无延时 扣款前无消息通知。
   *
   *   • 特殊情况：周期扣费为通知后24小时扣费方式情况下，如果用户为首次签约（包含解约后重新签约），
   *   从用户签约成功时间开始算，商户在12小时内发起的扣款，会被立即执行，无延迟。商户超过12小时以后发起的扣款，都按24小时扣费规则执行
   * </pre>
   *
   * @param wxWithholdRequest the wx withhold request
   * @return wx withhold result
   * @throws WxPayException the wx pay exception
   */
  WxPayCommonResult withholdPartner(WxWithholdRequest wxWithholdRequest) throws WxPayException;

  /**
   * 预扣费通知
   * <pre>
   *   预扣费接口
   *   详见：<a href="https://pay.weixin.qq.com/wiki/doc/api/wxpay_v2/papay/chapter3_10.shtml">https://pay.weixin.qq.com/wiki/doc/api/wxpay_v2/papay/chapter3_10.shtml</a>
   *   商户进行委托代扣扣费前需要在可通知时间段内调用「预扣费通知」的接口为用户发送扣费提醒，
   *   并设定扣费持续天数和预计扣费金额，经过扣费等待期后，在可扣费期内可发起扣费，扣款金额不能高于预计扣费金额，
   *   扣费失败可主动发起重试扣费（重试次数由其他规则限制），直到扣费成功，或者可扣费期结束。
   *   商户只能在北京时间每天 6:00～22:00调用「预扣费通知」
   * </pre>
   *
   * @param wxPreWithholdRequest the wx pre withhold request
   * @return string
   * @throws WxPayException the wx pay exception
   */
  String preWithhold(WxPreWithholdRequest wxPreWithholdRequest) throws WxPayException;

  /**
   * 签约状态查询
   * <pre>
   *   签约状态查询
   *   详见：<a href="https://pay.weixin.qq.com/wiki/doc/api/wxpay_v2/papay/chapter3_7.shtml">https://pay.weixin.qq.com/wiki/doc/api/wxpay_v2/papay/chapter3_7.shtml</a>
   *   查询签约关系接口提供单笔签约关系查询。
   * </pre>
   *
   * @param wxSignQueryRequest the wx sign query request
   * @return wx sign query result
   * @throws WxPayException the wx pay exception
   */
  WxSignQueryResult querySign(WxSignQueryRequest wxSignQueryRequest) throws WxPayException;


  /**
   * 申请解约
   * <pre>
   *   申请解约
   *   详见：<a href="https://pay.weixin.qq.com/wiki/doc/api/wxpay_v2/papay/chapter3_9.shtml">https://pay.weixin.qq.com/wiki/doc/api/wxpay_v2/papay/chapter3_9.shtml</a>
   *   商户与用户的签约关系有误或者商户主动要求与用户解除之前的签约协议时可调用此接口完成解约。
   *   商户可以在商户后台（pay.weixin.qq.com）设置解约回调地址，当发生解约关系的时候，微信服务器会向此地址通知解约信息，内容与签约返回一致
   * </pre>
   *
   * @param wxTerminatedContractRequest the wx terminated contract request
   * @return wx termination contract result
   * @throws WxPayException the wx pay exception
   */
  WxTerminationContractResult terminationContract(WxTerminatedContractRequest wxTerminatedContractRequest) throws WxPayException;

  /**
   * <pre>
   *   查询代扣订单
   *   详见：<a href="https://pay.weixin.qq.com/wiki/doc/api/wxpay_v2/papay/chapter4_5.shtml">https://pay.weixin.qq.com/wiki/doc/api/wxpay_v2/papay/chapter4_5.shtml</a>
   *   该接口仅提供微信扣款服务申请扣款接口创建的订单进行查询，商户可以通过该接口主动查询微信代扣订单状态，完成下一步的业务逻辑。
   *   ACCEPT等待扣款：为24小时延时扣费场景下独有的，当没有达到24小时前一直是这种状态；
   *   NOTPAY未支付：系统已经启动扣款流程，这个状态只是瞬间状态，很快会进入终态（SUCCESS、PAY_FAIL）
   *
   * </pre>
   *
   * @param wxWithholdOrderQueryRequest the wx withhold order query request
   * @return wx withhold order query result
   * @throws WxPayException the wx pay exception
   */
  WxWithholdOrderQueryResult papOrderQuery(WxWithholdOrderQueryRequest wxWithholdOrderQueryRequest) throws WxPayException;

  /**
   * <pre>
   *   签约、解约结果通知解析
   *   详见：<a href="https://pay.weixin.qq.com/doc/v2/merchant/4011987586">签约、解约结果通知</a>
   *   注意：
   *    1、同样的通知可能会多次发送给商户系统。商户系统必须能够正确处理重复的通知。 推荐的做法是：当商户系统收到通知进行处理时，先检查对应业务数据的状态，并判断该通知是否已经处理。如果未处理，则再进行处理；如果已处理，则直接返回结果成功。在对业务数据进行状态检查和处理之前，要采用数据锁进行并发控制，以避免函数重入造成的数据混乱。
   *    2、如果在所有通知频率(0/10/10/10/30/30/30/300/300/300/300/300/300/300/300/300/300/300/300/300/300/300/300/300/300/300/300/300/300/300（单位：秒）)后没有收到微信侧回调,商户应调用查询订单接口确认订单状态。
   *   特别提醒：
   *    1、商户系统对于签约、解约结果通知的内容一定要做签名验证,并校验返回的商户协议号和用户openid信息是否一致，防止数据泄露导致出现“假通知”，造成损失。
   *    2、当收到通知进行处理时，首先检查对应业务数据的状态，判断该通知是否已经处理过，如果没有处理过再进行处理，如果处理过直接返回结果成功。在对业务数据进行状态检查和处理之前，要采用数据锁进行并发控制，以避免函数重入造成的数据混乱。
   * </pre>
   *
   * @param xmlData the wx withhold order query request
   * @return wx sign result
   * @throws WxPayException the wx pay exception
   */
  WxSignQueryResult parseSignNotifyResult(String xmlData) throws WxPayException;
}
