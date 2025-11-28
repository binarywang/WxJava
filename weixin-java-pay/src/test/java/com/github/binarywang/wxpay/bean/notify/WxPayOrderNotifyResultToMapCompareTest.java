package com.github.binarywang.wxpay.bean.notify;

import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.github.binarywang.wxpay.util.SignUtils;
import com.github.binarywang.wxpay.util.XmlConfig;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * 比较两种 toMap() 方法实现的差异
 */
public class WxPayOrderNotifyResultToMapCompareTest {

  @Test
  public void testCompareToMapMethods() throws Exception {
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
    
    // WxPayOrderNotifyResult 重写的 toMap()，使用 SignUtils.xmlBean2Map(this)
    Map<String, String> beanMap = result.toMap();
    
    // BaseWxPayResult.toMap() 的方式，直接从 XML 解析
    Map<String, String> xmlMap = parseXmlToMap(xml);
    
    System.out.println("=== WxPayOrderNotifyResult.toMap() (SignUtils.xmlBean2Map) ===");
    printSignString(beanMap);
    
    System.out.println("\n=== 直接从 XML 解析的 Map ===");
    printSignString(xmlMap);
    
    // 找出差异
    System.out.println("\n=== 差异 ===");
    Set<String> allKeys = new TreeSet<>();
    allKeys.addAll(beanMap.keySet());
    allKeys.addAll(xmlMap.keySet());
    
    for (String key : allKeys) {
      String beanValue = beanMap.get(key);
      String xmlValue = xmlMap.get(key);
      if (!Objects.equals(beanValue, xmlValue)) {
        System.out.println(key + ": beanMap=" + beanValue + ", xmlMap=" + xmlValue);
      }
    }
  }
  
  private static Map<String, String> parseXmlToMap(String xml) throws Exception {
    Map<String, String> result = new HashMap<>();
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setExpandEntityReferences(false);
    factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
    Document doc = factory.newDocumentBuilder().parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));

    NodeList list = (NodeList) XPathFactory.newInstance().newXPath()
      .compile("/xml/*")
      .evaluate(doc, XPathConstants.NODESET);

    int len = list.getLength();
    for (int i = 0; i < len; i++) {
      result.put(list.item(i).getNodeName(), list.item(i).getTextContent());
    }
    return result;
  }
  
  private static void printSignString(Map<String, String> params) {
    List<String> noSignParams = Arrays.asList("sign", "key", "xmlString", "xmlDoc", "couponList");
    StringBuilder sb = new StringBuilder();
    for (String key : new TreeMap<>(params).keySet()) {
      String value = params.get(key);
      if (value != null && !value.isEmpty() && !noSignParams.contains(key)) {
        sb.append(key).append("=").append(value).append("&");
      }
    }
    sb.append("key=YOUR_MCH_KEY");
    System.out.println(sb.toString());
  }
}
