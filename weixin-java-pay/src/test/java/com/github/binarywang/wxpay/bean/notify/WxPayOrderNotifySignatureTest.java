package com.github.binarywang.wxpay.bean.notify;

import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.util.SignUtils;
import com.github.binarywang.wxpay.util.XmlConfig;
import org.apache.commons.codec.digest.DigestUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * 测试签名验证逻辑
 */
public class WxPayOrderNotifySignatureTest {

  private static final String MCH_KEY = "testmchkey1234567890123456789012"; // 32位测试密钥
  private static final List<String> NO_SIGN_PARAMS = Arrays.asList("sign", "key", "xmlString", "xmlDoc", "couponList");

  @Test
  public void testSignatureVerification() throws Exception {
    // 创建一个测试用的 XML，并手动计算正确的签名
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
    
    // 计算正确的签名
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
    
    System.out.println("测试 XML:");
    System.out.println(xml);
    System.out.println("计算的签名: " + correctSign);
    
    // 测试普通模式
    System.out.println("\n=== 普通模式 ===");
    WxPayOrderNotifyResult result = WxPayOrderNotifyResult.fromXML(xml);
    Map<String, String> beanMap = result.toMap();
    
    System.out.println("toMap() 结果 (用于签名验证):");
    TreeMap<String, String> sortedMap = new TreeMap<>(beanMap);
    for (Map.Entry<String, String> entry : sortedMap.entrySet()) {
      if (!NO_SIGN_PARAMS.contains(entry.getKey())) {
        System.out.println("  " + entry.getKey() + " = " + entry.getValue());
      }
    }
    
    // 验证签名
    String verifySign = createSign(beanMap, WxPayConstants.SignType.MD5, MCH_KEY);
    System.out.println("原始签名: " + result.getSign());
    System.out.println("计算签名: " + verifySign);
    Assert.assertEquals(verifySign, result.getSign(), "签名应该匹配");
    
    // 测试 fastMode
    System.out.println("\n=== Fast Mode ===");
    XmlConfig.fastMode = true;
    try {
      result = BaseWxPayResult.fromXML(xml, WxPayOrderNotifyResult.class);
      beanMap = result.toMap();
      
      System.out.println("fastMode toMap() 结果 (用于签名验证):");
      sortedMap = new TreeMap<>(beanMap);
      for (Map.Entry<String, String> entry : sortedMap.entrySet()) {
        if (!NO_SIGN_PARAMS.contains(entry.getKey())) {
          System.out.println("  " + entry.getKey() + " = " + entry.getValue());
        }
      }
      
      verifySign = createSign(beanMap, WxPayConstants.SignType.MD5, MCH_KEY);
      System.out.println("原始签名: " + result.getSign());
      System.out.println("计算签名: " + verifySign);
      Assert.assertEquals(verifySign, result.getSign(), "fastMode 签名应该匹配");
    } finally {
      XmlConfig.fastMode = false;
    }
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
