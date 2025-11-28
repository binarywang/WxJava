package com.github.binarywang.wxpay.bean.notify;

import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.github.binarywang.wxpay.util.XmlConfig;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * 测试 WxPayOrderNotifyResult.toMap() 方法是否正确包含所有 XML 字段
 */
public class WxPayOrderNotifyResultToMapTest {

  @Test
  public void testToMapContainsAllFields() {
    // 从 issue 的截图中提取的 XML 数据
    String xml = "<xml>" +
      "<appid>wx58ff40508696691f</appid>" +
      "<bank_type>ICBC_DEBIT</bank_type>" +
      "<cash_fee>1</cash_fee>" +
      "<fee_type>CNY</fee_type>" +
      "<is_subscribe>N</is_subscribe>" +
      "<mch_id>1545462911</mch_id>" +
      "<nonce_str>1761723102373</nonce_str>" +
      "<openid>o1gdd16CZCi6yYvkn6j9EB_1TObM</openid>" +
      "<out_trade_no>20251029153140</out_trade_no>" +
      "<result_code>SUCCESS</result_code>" +
      "<return_code>SUCCESS</return_code>" +
      "<sign>03F5C68CA8F2E30855077FA3FC21EBEA</sign>" +
      "<time_end>20251029153852</time_end>" +
      "<total_fee>1</total_fee>" +
      "<trade_type>JSAPI</trade_type>" +
      "<transaction_id>4200002882220251029816273963B</transaction_id>" +
      "</xml>";

    WxPayOrderNotifyResult result = WxPayOrderNotifyResult.fromXML(xml);
    Map<String, String> map = result.toMap();

    System.out.println("toMap() 结果:");
    map.entrySet().stream()
      .sorted(Map.Entry.comparingByKey())
      .forEach(entry -> System.out.println("  " + entry.getKey() + " = " + entry.getValue()));

    // 验证关键字段是否存在
    Assert.assertTrue(map.containsKey("is_subscribe"), "toMap() 应该包含 is_subscribe 字段");
    Assert.assertEquals(map.get("is_subscribe"), "N", "is_subscribe 的值应该是 N");
    
    Assert.assertTrue(map.containsKey("bank_type"), "toMap() 应该包含 bank_type 字段");
    Assert.assertEquals(map.get("bank_type"), "ICBC_DEBIT", "bank_type 的值应该是 ICBC_DEBIT");
  }

  @Test
  public void testToMapWithFastMode() {
    String xml = "<xml>" +
      "<appid>wx58ff40508696691f</appid>" +
      "<bank_type>ICBC_DEBIT</bank_type>" +
      "<cash_fee>1</cash_fee>" +
      "<fee_type>CNY</fee_type>" +
      "<is_subscribe>N</is_subscribe>" +
      "<mch_id>1545462911</mch_id>" +
      "<nonce_str>1761723102373</nonce_str>" +
      "<openid>o1gdd16CZCi6yYvkn6j9EB_1TObM</openid>" +
      "<out_trade_no>20251029153140</out_trade_no>" +
      "<result_code>SUCCESS</result_code>" +
      "<return_code>SUCCESS</return_code>" +
      "<sign>03F5C68CA8F2E30855077FA3FC21EBEA</sign>" +
      "<time_end>20251029153852</time_end>" +
      "<total_fee>1</total_fee>" +
      "<trade_type>JSAPI</trade_type>" +
      "<transaction_id>4200002882220251029816273963B</transaction_id>" +
      "</xml>";

    XmlConfig.fastMode = true;
    try {
      WxPayOrderNotifyResult result = BaseWxPayResult.fromXML(xml, WxPayOrderNotifyResult.class);
      Map<String, String> map = result.toMap();

      System.out.println("fastMode toMap() 结果:");
      map.entrySet().stream()
        .sorted(Map.Entry.comparingByKey())
        .forEach(entry -> System.out.println("  " + entry.getKey() + " = " + entry.getValue()));

      // 验证关键字段是否存在
      Assert.assertTrue(map.containsKey("is_subscribe"), "fastMode toMap() 应该包含 is_subscribe 字段");
      Assert.assertEquals(map.get("is_subscribe"), "N", "is_subscribe 的值应该是 N");
    } finally {
      XmlConfig.fastMode = false;
    }
  }
}
