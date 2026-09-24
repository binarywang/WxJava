package me.chanjar.weixin.common.util.http;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URLDecoder;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.error.WxRuntimeException;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SensitiveRequestUtilsTest {
  @Test
  public void encodesRawValuesExactlyOnce() throws Exception {
    for (String value : new String[]{"NORMAL_CODE", "", "a b\n", "+&=#?%2F", "中文\uD83D\uDE00"}) {
      String encoded = SensitiveRequestUtils.encodeQueryValue(value);
      assertEquals(URLDecoder.decode(encoded, "UTF-8"), value);
      assertFalse(encoded.contains("&"));
      assertFalse(encoded.contains("#"));
    }
    assertEquals(SensitiveRequestUtils.encodeQueryValue("%2F"), "%252F");
  }

  @Test(expectedExceptions = NullPointerException.class)
  public void doesNotConvertNullToAValue() {
    SensitiveRequestUtils.encodeQueryValue(null);
  }

  @Test
  public void removesAllOriginalErrorRepresentations() {
    WxError error = WxError.builder().errorCode(40029).errorMsg("FAKE_SECRET")
      .errorMsgEn("FAKE_SECRET").json("FAKE_SECRET").build();
    WxErrorException original = new WxErrorException(error, new IllegalArgumentException("FAKE_SECRET"));
    original.addSuppressed(new IllegalStateException("FAKE_SECRET"));
    WxErrorException safe = SensitiveRequestUtils.sanitize(original);
    assertEquals(safe.getError().getErrorCode(), 40029);
    assertNull(safe.getError().getJson());
    assertNull(safe.getError().getErrorMsgEn());
    assertSafe(safe, original);
    assertEquals(original.getError().getJson(), "FAKE_SECRET");
  }

  @Test
  public void retainsCommonRuntimeCategoriesWithoutCauses() {
    for (RuntimeException original : new RuntimeException[]{new IllegalArgumentException("FAKE_SECRET"),
      new IllegalStateException("FAKE_SECRET"), new NullPointerException("FAKE_SECRET"),
      new WxRuntimeException("FAKE_SECRET")}) {
      original.initCause(new RuntimeException("FAKE_SECRET"));
      original.addSuppressed(new RuntimeException("FAKE_SECRET"));
      RuntimeException safe = SensitiveRequestUtils.sanitize(original);
      assertEquals(safe.getClass(), original.getClass());
      assertSafe(safe, original);
    }
    assertTrue(SensitiveRequestUtils.sanitize(new UnsupportedOperationException("FAKE_SECRET"))
      instanceof WxRuntimeException);
  }

  private void assertSafe(Throwable safe, Throwable original) {
    StringWriter trace = new StringWriter();
    safe.printStackTrace(new PrintWriter(trace));
    assertFalse(trace.toString().contains("FAKE_SECRET"));
    assertNull(safe.getCause());
    assertEquals(safe.getSuppressed().length, 0);
    assertEquals(safe.getStackTrace(), original.getStackTrace());
  }
}
