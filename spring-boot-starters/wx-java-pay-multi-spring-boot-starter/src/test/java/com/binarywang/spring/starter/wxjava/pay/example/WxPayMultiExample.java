package com.binarywang.spring.starter.wxjava.pay.example;

import com.binarywang.spring.starter.wxjava.pay.service.WxPayMultiServices;
import com.github.binarywang.wxpay.bean.request.WxPayRefundV3Request;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderV3Request;
import com.github.binarywang.wxpay.bean.result.WxPayOrderQueryV3Result;
import com.github.binarywang.wxpay.bean.result.WxPayRefundV3Result;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderV3Result;
import com.github.binarywang.wxpay.bean.result.enums.TradeTypeEnum;
import com.github.binarywang.wxpay.service.WxPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 微信支付多公众号关联使用示例.
 * <p>
 * 本示例展示了如何使用 wx-java-pay-multi-spring-boot-starter 来管理多个公众号的支付配置。
 * </p>
 *
 * @author Binary Wang
 */
@Slf4j
@Service
public class WxPayMultiExample {

  @Autowired
  private WxPayMultiServices wxPayMultiServices;

  /**
   * 示例1：根据appId创建支付订单.
   * <p>
   * 适用场景：系统需要支持多个公众号，根据用户所在的公众号动态选择支付配置
   * </p>
   *
   * @param appId    公众号appId
   * @param openId   用户的openId
   * @param totalFee 支付金额（分）
   * @param body     商品描述
   * @return JSAPI支付参数
   */
  public WxPayUnifiedOrderV3Result.JsapiResult createJsapiOrder(String appId, String openId,
                                                                Integer totalFee, String body) {
    try {
      // 根据appId获取对应的WxPayService
      WxPayService wxPayService = wxPayMultiServices.getWxPayService(appId);

      if (wxPayService == null) {
        log.error("未找到appId对应的微信支付配置: {}", appId);
        throw new IllegalArgumentException("未找到appId对应的微信支付配置");
      }

      // 构建支付请求
      WxPayUnifiedOrderV3Request request = new WxPayUnifiedOrderV3Request();
      request.setOutTradeNo(generateOutTradeNo());
      request.setDescription(body);
      request.setAmount(new WxPayUnifiedOrderV3Request.Amount().setTotal(totalFee));
      request.setPayer(new WxPayUnifiedOrderV3Request.Payer().setOpenid(openId));
      request.setNotifyUrl(wxPayService.getConfig().getNotifyUrl());

      // 调用微信支付API创建订单
      WxPayUnifiedOrderV3Result.JsapiResult result =
        wxPayService.createOrderV3(TradeTypeEnum.JSAPI, request);

      log.info("创建JSAPI支付订单成功，appId: {}, outTradeNo: {}", appId, request.getOutTradeNo());
      return result;

    } catch (Exception e) {
      log.error("创建JSAPI支付订单失败，appId: {}", appId, e);
      throw new RuntimeException("创建支付订单失败", e);
    }
  }

  /**
   * 示例2：服务商模式 - 为不同子商户创建订单.
   * <p>
   * 适用场景：服务商为多个子商户提供支付服务
   * </p>
   *
   * @param configKey 配置标识（在配置文件中定义）
   * @param subOpenId 子商户用户的openId
   * @param totalFee  支付金额（分）
   * @param body      商品描述
   * @return JSAPI支付参数
   */
  public WxPayUnifiedOrderV3Result.JsapiResult createPartnerOrder(String configKey, String subOpenId,
                                                                   Integer totalFee, String body) {
    try {
      // 根据配置标识获取WxPayService
      WxPayService wxPayService = wxPayMultiServices.getWxPayService(configKey);

      if (wxPayService == null) {
        log.error("未找到配置: {}", configKey);
        throw new IllegalArgumentException("未找到配置");
      }

      // 获取子商户信息
      String subAppId = wxPayService.getConfig().getSubAppId();
      String subMchId = wxPayService.getConfig().getSubMchId();
      log.info("使用服务商模式，子商户appId: {}, 子商户号: {}", subAppId, subMchId);

      // 构建支付请求
      WxPayUnifiedOrderV3Request request = new WxPayUnifiedOrderV3Request();
      request.setOutTradeNo(generateOutTradeNo());
      request.setDescription(body);
      request.setAmount(new WxPayUnifiedOrderV3Request.Amount().setTotal(totalFee));
      request.setPayer(new WxPayUnifiedOrderV3Request.Payer().setOpenid(subOpenId));
      request.setNotifyUrl(wxPayService.getConfig().getNotifyUrl());

      // 调用微信支付API创建订单
      WxPayUnifiedOrderV3Result.JsapiResult result =
        wxPayService.createOrderV3(TradeTypeEnum.JSAPI, request);

      log.info("创建服务商支付订单成功，配置: {}, outTradeNo: {}", configKey, request.getOutTradeNo());
      return result;

    } catch (Exception e) {
      log.error("创建服务商支付订单失败，配置: {}", configKey, e);
      throw new RuntimeException("创建支付订单失败", e);
    }
  }

  /**
   * 示例3：查询订单状态.
   * <p>
   * 适用场景：查询不同公众号的订单支付状态
   * </p>
   *
   * @param appId      公众号appId
   * @param outTradeNo 商户订单号
   * @return 订单状态
   */
  public String queryOrderStatus(String appId, String outTradeNo) {
    try {
      WxPayService wxPayService = wxPayMultiServices.getWxPayService(appId);

      if (wxPayService == null) {
        log.error("未找到appId对应的微信支付配置: {}", appId);
        throw new IllegalArgumentException("未找到appId对应的微信支付配置");
      }

      // 查询订单
      WxPayOrderQueryV3Result result = wxPayService.queryOrderV3(null, outTradeNo);
      String tradeState = result.getTradeState();

      log.info("查询订单状态成功，appId: {}, outTradeNo: {}, 状态: {}", appId, outTradeNo, tradeState);
      return tradeState;

    } catch (Exception e) {
      log.error("查询订单状态失败，appId: {}, outTradeNo: {}", appId, outTradeNo, e);
      throw new RuntimeException("查询订单失败", e);
    }
  }

  /**
   * 示例4：申请退款.
   * <p>
   * 适用场景：为不同公众号的订单申请退款
   * </p>
   *
   * @param appId      公众号appId
   * @param outTradeNo 商户订单号
   * @param refundFee  退款金额（分）
   * @param totalFee   订单总金额（分）
   * @param reason     退款原因
   * @return 退款单号
   */
  public String refund(String appId, String outTradeNo, Integer refundFee,
                       Integer totalFee, String reason) {
    try {
      WxPayService wxPayService = wxPayMultiServices.getWxPayService(appId);

      if (wxPayService == null) {
        log.error("未找到appId对应的微信支付配置: {}", appId);
        throw new IllegalArgumentException("未找到appId对应的微信支付配置");
      }

      // 构建退款请求
      com.github.binarywang.wxpay.bean.request.WxPayRefundV3Request request =
        new com.github.binarywang.wxpay.bean.request.WxPayRefundV3Request();
      request.setOutTradeNo(outTradeNo);
      request.setOutRefundNo(generateRefundNo());
      request.setReason(reason);
      request.setNotifyUrl(wxPayService.getConfig().getRefundNotifyUrl());

      com.github.binarywang.wxpay.bean.request.WxPayRefundV3Request.Amount amount =
        new com.github.binarywang.wxpay.bean.request.WxPayRefundV3Request.Amount();
      amount.setRefund(refundFee);
      amount.setTotal(totalFee);
      amount.setCurrency("CNY");
      request.setAmount(amount);

      // 调用微信支付API申请退款
      WxPayRefundV3Result result = wxPayService.refundV3(request);

      log.info("申请退款成功，appId: {}, outTradeNo: {}, outRefundNo: {}",
        appId, outTradeNo, request.getOutRefundNo());
      return request.getOutRefundNo();

    } catch (Exception e) {
      log.error("申请退款失败，appId: {}, outTradeNo: {}", appId, outTradeNo, e);
      throw new RuntimeException("申请退款失败", e);
    }
  }

  /**
   * 示例5：动态管理配置.
   * <p>
   * 适用场景：需要在运行时更新配置（如证书更新后需要重新加载）
   * </p>
   *
   * @param configKey 配置标识
   */
  public void reloadConfig(String configKey) {
    try {
      // 移除缓存的WxPayService实例
      wxPayMultiServices.removeWxPayService(configKey);
      log.info("移除配置成功，下次获取时将重新创建: {}", configKey);

      // 下次调用 getWxPayService 时会重新创建实例
      WxPayService wxPayService = wxPayMultiServices.getWxPayService(configKey);
      if (wxPayService != null) {
        log.info("重新加载配置成功: {}", configKey);
      }

    } catch (Exception e) {
      log.error("重新加载配置失败: {}", configKey, e);
      throw new RuntimeException("重新加载配置失败", e);
    }
  }

  /**
   * 生成商户订单号.
   *
   * @return 商户订单号
   */
  private String generateOutTradeNo() {
    return "ORDER_" + System.currentTimeMillis();
  }

  /**
   * 生成商户退款单号.
   *
   * @return 商户退款单号
   */
  private String generateRefundNo() {
    return "REFUND_" + System.currentTimeMillis();
  }
}
