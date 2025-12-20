package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.mipay.MedInsOrdersRequest;
import com.github.binarywang.wxpay.bean.mipay.MedInsOrdersResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.MiPayService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.testbase.ApiTestModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * 医保接口测试
 * @author xgl
 * @date 2025/12/20 10:04
 */
@Slf4j
@Test
@Guice(modules = ApiTestModule.class)
public class MiPayServiceImplTest {

  @Inject
  private WxPayService wxPayService;

  private static final Gson GSON = new GsonBuilder().create();


  /**
   * 医保自费混合收款下单测试
   * @throws WxPayException
   */
  @Test
  public void medInsOrders() throws WxPayException {
    String requestParamStr = "{\"mix_pay_type\":\"CASH_AND_INSURANCE\",\"order_type\":\"REG_PAY\",\"appid\":\"wxdace645e0bc2cXXX\",\"sub_appid\":\"wxdace645e0bc2cXXX\",\"sub_mchid\":\"1900008XXX\",\"openid\":\"o4GgauInH_RCEdvrrNGrntXDuXXX\",\"sub_openid\":\"o4GgauInH_RCEdvrrNGrntXDuXXX\",\"payer\":{\"name\":\"张三\",\"id_digest\":\"09eb26e839ff3a2e3980352ae45ef09e\",\"card_type\":\"ID_CARD\"},\"pay_for_relatives\":false,\"relative\":{\"name\":\"张三\",\"id_digest\":\"09eb26e839ff3a2e3980352ae45ef09e\",\"card_type\":\"ID_CARD\"},\"out_trade_no\":\"202204022005169952975171534816\",\"serial_no\":\"1217752501201\",\"pay_order_id\":\"ORD530100202204022006350000021\",\"pay_auth_no\":\"AUTH530100202204022006310000034\",\"geo_location\":\"102.682296,25.054260\",\"city_id\":\"530100\",\"med_inst_name\":\"北大医院\",\"med_inst_no\":\"1217752501201407033233368318\",\"med_ins_order_create_time\":\"2015-05-20T13:29:35+08:00\",\"total_fee\":202000,\"med_ins_gov_fee\":100000,\"med_ins_self_fee\":45000,\"med_ins_other_fee\":5000,\"med_ins_cash_fee\":50000,\"wechat_pay_cash_fee\":42000,\"cash_add_detail\":[{\"cash_add_fee\":2000,\"cash_add_type\":\"FREIGHT\"}],\"cash_reduce_detail\":[{\"cash_reduce_fee\":10000,\"cash_reduce_type\":\"DEFAULT_REDUCE_TYPE\"}],\"callback_url\":\"https://www.weixin.qq.com/wxpay/pay.php\",\"prepay_id\":\"wx201410272009395522657a690389285100\",\"passthrough_request_content\":\"{\\\"payAuthNo\\\":\\\"AUTH****\\\",\\\"payOrdId\\\":\\\"ORD****\\\",\\\"setlLatlnt\\\":\\\"118.096435,24.485407\\\"}\",\"extends\":\"{}\",\"attach\":\"{}\",\"channel_no\":\"AAGN9uhZc5EGyRdairKW7Qnu\",\"med_ins_test_env\":false}";

    MedInsOrdersRequest request = GSON.fromJson(requestParamStr, MedInsOrdersRequest.class);

    MiPayService miPayService = wxPayService.getMiPayService();

    MedInsOrdersResult result = miPayService.medInsOrders(request);

    log.info(result.toString());
  }

  /**
   * 使用医保自费混合订单号查看下单结果测试
   * @throws WxPayException
   */
  @Test
  public void getMedInsOrderByMixTradeNo() throws WxPayException {
    // 测试用的医保自费混合订单号和医疗机构商户号
    String mixTradeNo = "202204022005169952975171534816";
    String subMchid = "1900000109";

    MiPayService miPayService = wxPayService.getMiPayService();

    MedInsOrdersResult result = miPayService.getMedInsOrderByMixTradeNo(mixTradeNo, subMchid);

    log.info(result.toString());
  }


}
