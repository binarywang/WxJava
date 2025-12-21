package com.github.binarywang.wxpay.bean.notify;

import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.github.binarywang.wxpay.util.XmlConfig;
import org.testng.*;
import org.testng.annotations.*;

/**
 * <pre>
 * Created by Binary Wang on 2017-6-15.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxPayOrderNotifyResultTest {
  /**
   * Test from xml.
   */
  @Test
  public void testFromXML() {
    String xmlString = "<xml>\n" +
      "  <appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
      "  <attach><![CDATA[支付测试]]></attach>\n" +
      "  <bank_type><![CDATA[CFT]]></bank_type>\n" +
      "  <fee_type><![CDATA[CNY]]></fee_type>\n" +
      "  <is_subscribe><![CDATA[Y]]></is_subscribe>\n" +
      "  <mch_id><![CDATA[10000100]]></mch_id>\n" +
      "  <nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str>\n" +
      "  <openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid>\n" +
      "  <out_trade_no><![CDATA[1409811653]]></out_trade_no>\n" +
      "  <result_code><![CDATA[SUCCESS]]></result_code>\n" +
      "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
      "  <sign><![CDATA[B552ED6B279343CB493C5DD0D78AB241]]></sign>\n" +
      "  <sub_mch_id><![CDATA[10000100]]></sub_mch_id>\n" +
      "  <time_end><![CDATA[20140903131540]]></time_end>\n" +
      "  <total_fee>1</total_fee>\n" +
      "  <trade_type><![CDATA[JSAPI]]></trade_type>\n" +
      "  <transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id>\n" +
      "   <coupon_count>2</coupon_count>\n" +
      "   <coupon_type_1><![CDATA[NO_CASH]]></coupon_type_1>\n" +
      "   <coupon_id_1>10001</coupon_id_1>\n" +
      "   <coupon_fee_1>200</coupon_fee_1>\n" +
      "   <coupon_type_0><![CDATA[CASH]]></coupon_type_0>\n" +
      "   <coupon_id_0>10000</coupon_id_0>\n" +
      "   <coupon_fee_0>100</coupon_fee_0>\n" +
      "</xml>";

    WxPayOrderNotifyResult result = WxPayOrderNotifyResult.fromXML(xmlString);

    Assert.assertEquals(result.getCouponCount().intValue(), 2);
    Assert.assertNotNull(result.getCouponList());
    Assert.assertEquals(result.getCouponList().size(), 2);

    Assert.assertEquals(result.getCouponList().get(0).getCouponFee().intValue(), 100);
    Assert.assertEquals(result.getCouponList().get(1).getCouponFee().intValue(), 200);

    Assert.assertEquals(result.getCouponList().get(0).getCouponType(), "CASH");
    Assert.assertEquals(result.getCouponList().get(1).getCouponType(), "NO_CASH");

    Assert.assertEquals(result.getCouponList().get(0).getCouponId(), "10000");
    Assert.assertEquals(result.getCouponList().get(1).getCouponId(), "10001");

    //fast mode test
    XmlConfig.fastMode = true;
    try {
      result = BaseWxPayResult.fromXML(xmlString, WxPayOrderNotifyResult.class);

      Assert.assertEquals(result.getCouponCount().intValue(), 2);
      Assert.assertNotNull(result.getCouponList());
      Assert.assertEquals(result.getCouponList().size(), 2);

      Assert.assertEquals(result.getCouponList().get(0).getCouponFee().intValue(), 100);
      Assert.assertEquals(result.getCouponList().get(1).getCouponFee().intValue(), 200);

      Assert.assertEquals(result.getCouponList().get(0).getCouponType(), "CASH");
      Assert.assertEquals(result.getCouponList().get(1).getCouponType(), "NO_CASH");

      Assert.assertEquals(result.getCouponList().get(0).getCouponId(), "10000");
      Assert.assertEquals(result.getCouponList().get(1).getCouponId(), "10001");
    } finally {
      XmlConfig.fastMode = false;
    }
  }

}
