package me.chanjar.weixin.cp.util.crypto;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.Strings;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * The type Wx cp crypt util test.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a> created on  2020-06-11
 */
public class WxCpCryptUtilTest {
  /**
   * Test.
   */
  @Test
  public void test() {
    String encodingAesKey = "jWmYm7qr5nMoAUwZRjGtBxmz3KA1tkAj3ykkR6q2B2C";
    final byte[] commonsCodec = Base64.decodeBase64(encodingAesKey + "=");
    final byte[] guava = java.util.Base64.getDecoder().decode(Strings.CS.remove(encodingAesKey, " "));
    final byte[] guava1 = java.util.Base64.getDecoder().decode(Strings.CS.remove(encodingAesKey + "=", " "));
    assertEquals(commonsCodec, guava);
    assertEquals(guava1, guava);
  }
}
