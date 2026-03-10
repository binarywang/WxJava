package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderV3GlobalRequest;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderV3Result;
import com.github.binarywang.wxpay.bean.result.enums.GlobalTradeTypeEnum;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import me.chanjar.weixin.common.util.RandomUtils;

/**
 * 境外微信支付使用示例
 * Example usage for overseas WeChat Pay
 *
 * @author Binary Wang
 */
public class OverseasWxPayExample {

  /**
   * 境外微信支付JSAPI下单示例
   * Example for overseas WeChat Pay JSAPI order creation
   */
  public void createOverseasJsapiOrder(WxPayService payService) throws WxPayException {
    // 创建境外支付请求对象
    WxPayUnifiedOrderV3GlobalRequest request = new WxPayUnifiedOrderV3GlobalRequest();
    
    // 设置基础订单信息
    request.setOutTradeNo(RandomUtils.getRandomStr()); // 商户订单号
    request.setDescription("境外商品购买"); // 商品描述
    request.setNotifyUrl("https://your-domain.com/notify"); // 支付通知地址
    
    // 设置金额信息
    WxPayUnifiedOrderV3GlobalRequest.Amount amount = new WxPayUnifiedOrderV3GlobalRequest.Amount();
    amount.setCurrency(WxPayConstants.CurrencyType.CNY); // 币种
    amount.setTotal(100); // 金额，单位为分
    request.setAmount(amount);
    
    // 设置支付者信息
    WxPayUnifiedOrderV3GlobalRequest.Payer payer = new WxPayUnifiedOrderV3GlobalRequest.Payer();
    payer.setOpenid("用户的openid"); // 用户openid
    request.setPayer(payer);
    
    // 设置境外支付必需的参数
    request.setTradeType("JSAPI"); // 交易类型
    request.setMerchantCategoryCode("5812"); // 商户类目代码，境外商户必填
    
    // 可选：设置场景信息
    WxPayUnifiedOrderV3GlobalRequest.SceneInfo sceneInfo = new WxPayUnifiedOrderV3GlobalRequest.SceneInfo();
    sceneInfo.setPayerClientIp("用户IP地址");
    request.setSceneInfo(sceneInfo);
    
    // 调用境外支付接口
    WxPayUnifiedOrderV3Result.JsapiResult result = payService.createOrderV3Global(
        GlobalTradeTypeEnum.JSAPI, 
        request
    );
    
    // 返回的result包含前端需要的支付参数
    System.out.println("支付参数：" + result);
  }

  /**
   * 境外微信支付APP下单示例
   * Example for overseas WeChat Pay APP order creation
   */
  public void createOverseasAppOrder(WxPayService payService) throws WxPayException {
    WxPayUnifiedOrderV3GlobalRequest request = new WxPayUnifiedOrderV3GlobalRequest();
    
    // 设置基础信息
    request.setOutTradeNo(RandomUtils.getRandomStr());
    request.setDescription("境外APP商品购买");
    request.setNotifyUrl("https://your-domain.com/notify");
    
    // 设置金额
    WxPayUnifiedOrderV3GlobalRequest.Amount amount = new WxPayUnifiedOrderV3GlobalRequest.Amount();
    amount.setCurrency(WxPayConstants.CurrencyType.CNY);
    amount.setTotal(200); // 2元
    request.setAmount(amount);
    
    // APP支付不需要设置payer.openid，但需要设置空的payer对象
    request.setPayer(new WxPayUnifiedOrderV3GlobalRequest.Payer());
    
    // 境外支付必需参数
    request.setTradeType("APP");
    request.setMerchantCategoryCode("5812");
    
    // 调用境外APP支付接口
    WxPayUnifiedOrderV3Result.AppResult result = payService.createOrderV3Global(
        GlobalTradeTypeEnum.APP, 
        request
    );
    
    System.out.println("APP支付参数：" + result);
  }

  /**
   * 境外微信支付NATIVE下单示例
   * Example for overseas WeChat Pay NATIVE order creation  
   */
  public void createOverseasNativeOrder(WxPayService payService) throws WxPayException {
    WxPayUnifiedOrderV3GlobalRequest request = new WxPayUnifiedOrderV3GlobalRequest();
    
    request.setOutTradeNo(RandomUtils.getRandomStr());
    request.setDescription("境外扫码支付");
    request.setNotifyUrl("https://your-domain.com/notify");
    
    // 设置金额
    WxPayUnifiedOrderV3GlobalRequest.Amount amount = new WxPayUnifiedOrderV3GlobalRequest.Amount();
    amount.setCurrency(WxPayConstants.CurrencyType.CNY);
    amount.setTotal(300); // 3元
    request.setAmount(amount);
    
    // NATIVE支付不需要设置payer.openid
    request.setPayer(new WxPayUnifiedOrderV3GlobalRequest.Payer());
    
    // 境外支付必需参数
    request.setTradeType("NATIVE");
    request.setMerchantCategoryCode("5812");
    
    // 调用境外NATIVE支付接口
    String result = payService.createOrderV3Global(
        GlobalTradeTypeEnum.NATIVE, 
        request
    );
    
    System.out.println("NATIVE支付二维码链接：" + result);
  }

  /**
   * 配置示例
   * Configuration example
   */
  public WxPayConfig createOverseasConfig() {
    WxPayConfig config = new WxPayConfig();
    
    // 基础配置
    config.setAppId("你的AppId");
    config.setMchId("你的境外商户号");
    config.setMchKey("你的商户密钥");
    config.setNotifyUrl("https://your-domain.com/notify");
    
    // 境外支付使用的是全球API，在代码中会自动使用 https://apihk.mch.weixin.qq.com 作为基础URL
    // 无需额外设置payBaseUrl，方法内部会自动处理
    
    // V3相关配置（境外支付也使用V3接口）
    config.setPrivateKeyPath("你的私钥文件路径");
    config.setCertSerialNo("你的商户证书序列号");
    config.setApiV3Key("你的APIv3密钥");
    
    return config;
  }
}