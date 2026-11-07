package me.chanjar.weixin.common.util.crypto;

import java.io.IOException;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.testng.annotations.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import static org.testng.Assert.*;

@Test
public class WxCryptUtilTest {
  String encodingAesKey = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFE";
  String token = "pamtest";
  String timestamp = "1409304348";
  String nonce = "xxxxxx";
  String appId = "wxb11529c136998cb6";
  String randomStr = "aaaabbbbccccdddd";

  String xmlFormat = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><Encrypt><![CDATA[%1$s]]></Encrypt></xml>";
  String replyMsg = "我是中文abcd123";

  String afterAesEncrypt = "jn1L23DB+6ELqJ+6bruv21Y6MD7KeIfP82D6gU39rmkgczbWwt5+3bnyg5K55bgVtVzd832WzZGMhkP72vVOfg==";

  String replyMsg2 = "<xml><ToUserName><![CDATA[oia2Tj我是中文jewbmiOUlr6X-1crbLOvLw]]></ToUserName><FromUserName><![CDATA[gh_7f083739789a]]></FromUserName><CreateTime>1407743423</CreateTime><MsgType><![CDATA[video]]></MsgType><Video><MediaId><![CDATA[eYJ1MbwPRJtOvIEabaxHs7TX2D-HV71s79GUxqdUkjm6Gs2Ed1KF3ulAOA9H1xG0]]></MediaId><Title><![CDATA[testCallBackReplyVideo]]></Title><Description><![CDATA[testCallBackReplyVideo]]></Description></Video></xml>";
  String afterAesEncrypt2 = "jn1L23DB+6ELqJ+6bruv23M2GmYfkv0xBh2h+XTBOKVKcgDFHle6gqcZ1cZrk3e1qjPQ1F4RsLWzQRG9udbKWesxlkupqcEcW7ZQweImX9+wLMa0GaUzpkycA8+IamDBxn5loLgZpnS7fVAbExOkK5DYHBmv5tptA9tklE/fTIILHR8HLXa5nQvFb3tYPKAlHF3rtTeayNf0QuM+UW/wM9enGIDIJHF7CLHiDNAYxr+r+OrJCmPQyTy8cVWlu9iSvOHPT/77bZqJucQHQ04sq7KZI27OcqpQNSto2OdHCoTccjggX5Z9Mma0nMJBU+jLKJ38YB1fBIz+vBzsYjrTmFQ44YfeEuZ+xRTQwr92vhA9OxchWVINGC50qE/6lmkwWTwGX9wtQpsJKhP+oS7rvTY8+VdzETdfakjkwQ5/Xka042OlUb1/slTwo4RscuQ+RdxSGvDahxAJ6+EAjLt9d8igHngxIbf6YyqqROxuxqIeIch3CssH/LqRs+iAcILvApYZckqmA7FNERspKA5f8GoJ9sv8xmGvZ9Yrf57cExWtnX8aCMMaBropU/1k+hKP5LVdzbWCG0hGwx/dQudYR/eXp3P0XxjlFiy+9DMlaFExWUZQDajPkdPrEeOwofJb";

  public void testNormal() throws ParserConfigurationException, SAXException, IOException {
    WxCryptUtil pc = new WxCryptUtil(this.token, this.encodingAesKey, this.appId);
    String encryptedXml = pc.encrypt(this.replyMsg);

    System.out.println(encryptedXml);

    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    documentBuilderFactory.setExpandEntityReferences(false);
    documentBuilderFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
    DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
    Document document = documentBuilder.parse(new InputSource(new StringReader(encryptedXml)));

    Element root = document.getDocumentElement();
    String cipherText = root.getElementsByTagName("Encrypt").item(0).getTextContent();
    System.out.println(cipherText);

    String msgSignature = root.getElementsByTagName("MsgSignature").item(0).getTextContent();
    System.out.println(msgSignature);

    String timestamp = root.getElementsByTagName("TimeStamp").item(0).getTextContent();
    System.out.println(timestamp);

    String nonce = root.getElementsByTagName("Nonce").item(0).getTextContent();
    System.out.println(nonce);

    String messageText = String.format(this.xmlFormat, cipherText);
    System.out.println(messageText);

    // 第三方收到企业号平台发送的消息
    String plainMessage = pc.decrypt(cipherText);
    System.out.println(plainMessage);

    assertEquals(plainMessage, this.replyMsg);
  }

  public void testAesEncrypt() {
    WxCryptUtil pc = new WxCryptUtil(this.token, this.encodingAesKey, this.appId);
    assertEquals(pc.encrypt(this.randomStr, this.replyMsg), this.afterAesEncrypt);
  }

  public void testAesEncrypt2() {
    WxCryptUtil pc = new WxCryptUtil(this.token, this.encodingAesKey, this.appId);
    assertEquals(pc.encrypt(this.randomStr, this.replyMsg2), this.afterAesEncrypt2);
  }

  public void testValidateSignatureError() throws ParserConfigurationException, SAXException,
    IOException {
    try {
      WxCryptUtil pc = new WxCryptUtil(this.token, this.encodingAesKey, this.appId);
      String afterEncrpt = pc.encrypt(this.replyMsg);
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      dbf.setExpandEntityReferences(false);
      dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);

      DocumentBuilder db = dbf.newDocumentBuilder();
      StringReader sr = new StringReader(afterEncrpt);
      InputSource is = new InputSource(sr);
      Document document = db.parse(is);

      Element root = document.getDocumentElement();
      NodeList nodelist1 = root.getElementsByTagName("Encrypt");

      String encrypt = nodelist1.item(0).getTextContent();
      String fromXML = String.format(this.xmlFormat, encrypt);
      pc.decryptXml("12345", this.timestamp, this.nonce, fromXML); // 这里签名错误
    } catch (RuntimeException e) {
      assertEquals(e.getMessage(), "加密消息签名校验失败");
      return;
    }
    fail("错误流程不抛出异常？？？");
  }

}
