package com.github.binarywang.wxpay.bean.result;

import com.github.binarywang.wxpay.util.XmlConfig;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <pre>
 *  Created by BinaryWang on 2018/4/22.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxPayRefundResultTest {
  @Test
  public void testFromXML() {
    /*
      该xml字符串来自于官方文档示例，稍加改造，加上代金卷
      refund_channel 是个什么鬼，官方文档只字不提
     */
    String xmlString = "<xml>\n" +
      "   <return_code><![CDATA[SUCCESS]]></return_code>\n" +
      "   <return_msg><![CDATA[OK]]></return_msg>\n" +
      "   <appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
      "   <mch_id><![CDATA[10000100]]></mch_id>\n" +
      "   <nonce_str><![CDATA[NfsMFbUFpdbEhPXP]]></nonce_str>\n" +
      "   <sign><![CDATA[B7274EB9F8925EB93100DD2085FA56C0]]></sign>\n" +
      "   <result_code><![CDATA[SUCCESS]]></result_code>\n" +
      "   <transaction_id><![CDATA[1008450740201411110005820873]]></transaction_id>\n" +
      "   <out_trade_no><![CDATA[1415757673]]></out_trade_no>\n" +
      "   <out_refund_no><![CDATA[1415701182]]></out_refund_no>\n" +
      "   <refund_id><![CDATA[2008450740201411110000174436]]></refund_id>\n" +
      "   <refund_channel><![CDATA[]]></refund_channel>\n" +
      "   <coupon_refund_fee>1</coupon_refund_fee>\n" +
      "   <coupon_refund_count>1</coupon_refund_count>\n" +
      "   <coupon_refund_id_0>123</coupon_refund_id_0>\n" +
      "   <coupon_refund_fee_0>1</coupon_refund_fee_0>\n" +
      "   <coupon_type_0><![CDATA[CASH]]></coupon_type_0>\n" +
      "   <refund_fee>2</refund_fee> \n" +
      "</xml>";

    WxPayRefundResult result = BaseWxPayResult.fromXML(xmlString, WxPayRefundResult.class);
    result.composeRefundCoupons();
    assertThat(result.getRefundCoupons()).isNotEmpty();
    assertThat(result.getRefundCoupons().get(0).getCouponRefundId()).isEqualTo("123");
    assertThat(result.getRefundCoupons().get(0).getCouponType()).isEqualTo("CASH");
    assertThat(result.getRefundCoupons().get(0).getCouponRefundFee()).isEqualTo(1);
  }

  @Test
  public void testFromXML_danpin() {
    //样例来自：https://pay.weixin.qq.com/wiki/doc/api/danpin.php?chapter=9_103&index=3
    String xmlString = "<xml>\n" +
      "<return_code><![CDATA[SUCCESS]]></return_code>\n" +
      "<return_msg><![CDATA[OK]]></return_msg>\n" +
      "<appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
      "<mch_id><![CDATA[10000100]]></mch_id>\n" +
      "<nonce_str><![CDATA[NfsMFbUFpdbEhPXP]]></nonce_str>\n" +
      "<sign><![CDATA[B7274EB9F8925EB93100DD2085FA56C0]]></sign>\n" +
      "<result_code><![CDATA[SUCCESS]]></result_code>\n" +
      "<transaction_id><![CDATA[1008450740201411110005820873]]></transaction_id>\n" +
      "<out_trade_no><![CDATA[1415757673]]></out_trade_no>\n" +
      "<out_refund_no><![CDATA[1415701182]]></out_refund_no>\n" +
      "<refund_id><![CDATA[2008450740201411110000174436]]></refund_id>\n" +
      "<refund_channel><![CDATA[]]></refund_channel>\n" +
      "<total_fee>1</total_fee >\n" +
      "<refund_fee>1</refund_fee>\n" +
      "<cash_fee>1</cash_fee >\n" +
      "<cash_refund_fee>1</cash_refund_fee>\n" +
      "<promotion_detail>{\"promotion_detail\":[{\"promotion_id\":\"109519\",\"scope\":\"SINGLE\",\"type\":\"DISCOUNT\",\"refund_amount\":5,\"goods_detail\":[{\"goods_id\":\"a_goods1\",\"refund_quantity\":7,\"price\":1,\"refund_amount\":4},{\"goods_id\":\"a_goods2\",\"refund_quantity\":1,\"price\":2,\"refund_amount\":1}]}]}</promotion_detail>\n" +
      "</xml>";

    WxPayRefundResult result = BaseWxPayResult.fromXML(xmlString, WxPayRefundResult.class);
    result.composePromotionDetails();
    assertThat(result.getPromotionDetails()).isNotEmpty();
    assertThat(result.getPromotionDetails().get(0).getPromotionId()).isEqualTo("109519");
    assertThat(result.getPromotionDetails().get(0).getRefundAmount()).isEqualTo(5);
    assertThat(result.getPromotionDetails().get(0).getScope()).isEqualTo("SINGLE");
    assertThat(result.getPromotionDetails().get(0).getType()).isEqualTo("DISCOUNT");

    assertThat(result.getPromotionDetails().get(0).getGoodsDetails()).isNotEmpty();
    assertThat(result.getPromotionDetails().get(0).getGoodsDetails().get(0).getGoodsId()).isEqualTo("a_goods1");
    assertThat(result.getPromotionDetails().get(0).getGoodsDetails().get(0).getRefundQuantity()).isEqualTo(7);
    assertThat(result.getPromotionDetails().get(0).getGoodsDetails().get(0).getRefundAmount()).isEqualTo(4);
    assertThat(result.getPromotionDetails().get(0).getGoodsDetails().get(0).getPrice()).isEqualTo(1);

    assertThat(result.getPromotionDetails().get(0).getGoodsDetails().get(1).getGoodsId()).isEqualTo("a_goods2");
    assertThat(result.getPromotionDetails().get(0).getGoodsDetails().get(1).getRefundQuantity()).isEqualTo(1);
    assertThat(result.getPromotionDetails().get(0).getGoodsDetails().get(1).getRefundAmount()).isEqualTo(1);
    assertThat(result.getPromotionDetails().get(0).getGoodsDetails().get(1).getPrice()).isEqualTo(2);
  }

  @Test
  public void testFromXMLFastMode() {
    /*
      该xml字符串来自于官方文档示例，稍加改造，加上代金卷
      refund_channel 是个什么鬼，官方文档只字不提
     */
    String xmlString = "<xml>\n" +
      "   <return_code><![CDATA[SUCCESS]]></return_code>\n" +
      "   <return_msg><![CDATA[OK]]></return_msg>\n" +
      "   <appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
      "   <mch_id><![CDATA[10000100]]></mch_id>\n" +
      "   <nonce_str><![CDATA[NfsMFbUFpdbEhPXP]]></nonce_str>\n" +
      "   <sign><![CDATA[B7274EB9F8925EB93100DD2085FA56C0]]></sign>\n" +
      "   <result_code><![CDATA[SUCCESS]]></result_code>\n" +
      "   <transaction_id><![CDATA[1008450740201411110005820873]]></transaction_id>\n" +
      "   <out_trade_no><![CDATA[1415757673]]></out_trade_no>\n" +
      "   <out_refund_no><![CDATA[1415701182]]></out_refund_no>\n" +
      "   <refund_id><![CDATA[2008450740201411110000174436]]></refund_id>\n" +
      "   <refund_channel><![CDATA[]]></refund_channel>\n" +
      "   <coupon_refund_fee>1</coupon_refund_fee>\n" +
      "   <coupon_refund_count>1</coupon_refund_count>\n" +
      "   <coupon_refund_id_0>123</coupon_refund_id_0>\n" +
      "   <coupon_refund_fee_0>1</coupon_refund_fee_0>\n" +
      "   <coupon_type_0><![CDATA[CASH]]></coupon_type_0>\n" +
      "   <refund_fee>2</refund_fee> \n" +
      "</xml>";
    XmlConfig.fastMode = true;
    try {
      WxPayRefundResult result = BaseWxPayResult.fromXML(xmlString, WxPayRefundResult.class);
      result.composeRefundCoupons();
      assertThat(result.getRefundCoupons()).isNotEmpty();
      assertThat(result.getRefundCoupons().get(0).getCouponRefundId()).isEqualTo("123");
      assertThat(result.getRefundCoupons().get(0).getCouponType()).isEqualTo("CASH");
      assertThat(result.getRefundCoupons().get(0).getCouponRefundFee()).isEqualTo(1);
    } finally {
      XmlConfig.fastMode = false;
    }
  }

  @Test
  void benchmark() {
    long now = System.currentTimeMillis();
    int loops = 10000;
    for (int i = 0; i < loops; i++) {
      testFromXML();
    }
    System.out.println(" reflect mode:\t" + (System.currentTimeMillis() - now) + " (ms) ");

    now = System.currentTimeMillis();
    for (int i = 0; i < loops; i++) {
      testFromXMLFastMode();
    }
    System.out.println(" fast    mode:\t" + (System.currentTimeMillis() - now) + " (ms) ");
  }

}
