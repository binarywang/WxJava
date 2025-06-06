package me.chanjar.weixin.common.util.http;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpResponseProxy;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class HttpResponseProxyTest {

  @Test
  public void testExtractFileNameFromContentString() throws WxErrorException {
    String content = "attachment; filename*=utf-8''%E6%B5%8B%E8%AF%95.xlsx; filename=\"æµ�è¯�.xlsx\"";
    String filename = HttpResponseProxy.extractFileNameFromContentString(content);
    assertNotNull(filename);
    assertEquals(filename, "测试.xlsx");
  }

  @Test
  public void testExtractFileNameFromContentString_another() throws WxErrorException {
    String content = "attachment; filename*=utf-8''%E8%90%A5%E4%B8%9A%E6%89%A7%E7%85%A7.jpg; filename=\"è�¥ä¸�æ�§ç�§.jpg\"";
//    String content = "attachment; filename=\"è�¥ä¸�æ�§ç�§.jpg\"";
    String filename = HttpResponseProxy.extractFileNameFromContentString(content);
    assertNotNull(filename);
    assertEquals(filename, "营业执照.jpg");
  }
}
