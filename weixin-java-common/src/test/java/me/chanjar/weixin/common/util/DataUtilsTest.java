package me.chanjar.weixin.common.util;

import org.testng.annotations.*;

import static org.testng.Assert.*;

/**
 * <pre>
 *  Created by BinaryWang on 2018/5/8.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class DataUtilsTest {

  @Test
  public void testHandleDataWithSecret() {
    String data = "js_code=001tZveq0SMoiq1AEXeq0ECJeq0tZveZ&secret=5681022fa1643845392367ea88888888&grant_type=authorization_code&appid=wxe156d4848d999999";
    final String s = DataUtils.handleDataWithSecret(data);
    assertTrue(s.contains("&secret=******&"));
  }

  @Test
  public void testHandleDataWithSecretAtEnd() {
    // Secret is the last parameter in the query string, so there is no trailing &
    String data = "appid=wx123&secret=abc123";
    final String s = DataUtils.handleDataWithSecret(data);
    assertFalse(s.contains("abc123"), "Secret at the end of the string should be masked");
    assertTrue(s.contains("secret=******"), "Secret should be replaced with asterisks");
  }
}
