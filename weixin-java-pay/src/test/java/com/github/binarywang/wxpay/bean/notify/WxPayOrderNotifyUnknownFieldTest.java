package com.github.binarywang.wxpay.bean.notify;

import com.github.binarywang.wxpay.constant.WxPayConstants;
import org.apache.commons.codec.digest.DigestUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

/**
 * 测试当微信支付回调 XML 包含未在 Java Bean 中定义的字段时，签名验证是否正常。
 * <p>
 * 问题背景：当微信返回的 XML 包含某些未在 WxPayOrderNotifyResult 中定义的字段时，
 * 这些字段会被微信服务器用于签名计算。如果 toMap() 方法丢失了这些字段，
 * 则签名验证会失败，抛出 "参数格式校验错误！" 异常。
 * </p>
 * <p>
 * 解决方案：修改 WxPayOrderNotifyResult.toMap() 方法，使用父类的 toMap() 方法
 * 直接从原始 XML 解析所有字段，而不是使用 SignUtils.xmlBean2Map(this)。
 * </p>
 *
 * @see <a href="https://github.com/binarywang/WxJava/issues/3750">Issue #3750</a>
 */
public class WxPayOrderNotifyUnknownFieldTest {

  private static final String MCH_KEY = "testmchkey1234567890123456789012";
  private static final List<String> NO_SIGN_PARAMS = Arrays.asList("sign", "key", "xmlString", "xmlDoc", "couponList");

  @Test
  public void testSignatureWithUnknownField() throws Exception {
    // 创建一个测试用的 XML，包含一个未知字段 (未在 WxPayOrderNotifyResult 中定义)
    Map<String, String> params = new LinkedHashMap<>();
    params.put("appid", "wx58ff40508696691f");
    params.put("bank_type", "ICBC_DEBIT");
    params.put("cash_fee", "1");
    params.put("fee_type", "CNY");
    params.put("is_subscribe", "N");
    params.put("mch_id", "1545462911");
    params.put("nonce_str", "1761723102373");
    params.put("openid", "o1gdd16CZCi6yYvkn6j9EB_1TObM");
    params.put("out_trade_no", "20251029153140");
    params.put("result_code", "SUCCESS");
    params.put("return_code", "SUCCESS");
    params.put("time_end", "20251029153852");
    params.put("total_fee", "1");
    params.put("trade_type", "JSAPI");
    params.put("transaction_id", "4200002882220251029816273963B");
    // 添加一个未知字段
    params.put("unknown_field", "unknown_value");
    
    // 计算正确的签名 (包含未知字段)
    String correctSign = createSign(params, WxPayConstants.SignType.MD5, MCH_KEY);
    params.put("sign", correctSign);
    
    // 创建 XML
    StringBuilder xmlBuilder = new StringBuilder("<xml>");
    for (Map.Entry<String, String> entry : params.entrySet()) {
      xmlBuilder.append("<").append(entry.getKey()).append(">")
        .append(entry.getValue())
        .append("</").append(entry.getKey()).append(">");
    }
    xmlBuilder.append("</xml>");
    String xml = xmlBuilder.toString();
    
    System.out.println("测试 XML (包含未知字段 unknown_field):");
    System.out.println(xml);
    System.out.println("正确的签名 (包含未知字段计算): " + correctSign);
    
    // 解析 XML
    WxPayOrderNotifyResult result = WxPayOrderNotifyResult.fromXML(xml);
    Map<String, String> beanMap = result.toMap();
    
    System.out.println("\ntoMap() 结果:");
    TreeMap<String, String> sortedMap = new TreeMap<>(beanMap);
    for (Map.Entry<String, String> entry : sortedMap.entrySet()) {
      System.out.println("  " + entry.getKey() + " = " + entry.getValue());
    }
    
    // 检查 unknown_field 是否存在
    boolean hasUnknownField = beanMap.containsKey("unknown_field");
    System.out.println("\ntoMap() 是否包含 unknown_field: " + hasUnknownField);
    
    // 验证签名
    String verifySign = createSign(beanMap, WxPayConstants.SignType.MD5, MCH_KEY);
    System.out.println("原始签名: " + result.getSign());
    System.out.println("计算签名: " + verifySign);
    
    // 这个测试验证修复后 toMap() 能正确包含所有字段
    Assert.assertTrue(hasUnknownField, "toMap() 应该包含 unknown_field");
    Assert.assertEquals(verifySign, result.getSign(), "签名应该匹配");
  }
  
  private static String createSign(Map<String, String> params, String signType, String signKey) {
    StringBuilder toSign = new StringBuilder();
    for (String key : new TreeMap<>(params).keySet()) {
      String value = params.get(key);
      if (value != null && !value.isEmpty() && !NO_SIGN_PARAMS.contains(key)) {
        toSign.append(key).append("=").append(value).append("&");
      }
    }
    toSign.append("key=").append(signKey);
    return DigestUtils.md5Hex(toSign.toString()).toUpperCase();
  }
}
