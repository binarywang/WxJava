package com.github.binarywang.wxpay.util;

import me.chanjar.weixin.common.error.WxRuntimeException;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Proxy;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.testng.Assert.assertTrue;

public class RequestUtilsTest {

  @Test
  public void testGetReaderFailure() {
    IOException failure = new IOException("getReader failed");
    HttpServletRequest request = (HttpServletRequest) Proxy.newProxyInstance(
      HttpServletRequest.class.getClassLoader(), new Class<?>[]{HttpServletRequest.class},
      (proxy, method, args) -> {
        if ("getReader".equals(method.getName())) {
          throw failure;
        }
        throw new UnsupportedOperationException(method.getName());
      });

    assertThatThrownBy(() -> RequestUtils.readData(request))
      .isInstanceOf(WxRuntimeException.class).hasCause(failure);
  }

  @Test
  public void testPartialReadFailureClosesReader() {
    IOException failure = new IOException("read failed");
    boolean[] closed = {false};
    BufferedReader reader = new BufferedReader(new StringReader("")) {
      private int reads;

      @Override
      public String readLine() throws IOException {
        if (reads++ == 0) {
          return "partial body";
        }
        throw failure;
      }

      @Override
      public void close() throws IOException {
        closed[0] = true;
        super.close();
      }
    };

    assertThatThrownBy(() -> RequestUtils.readData(requestWithReader(reader)))
      .isInstanceOf(WxRuntimeException.class).hasCause(failure);
    assertTrue(closed[0], "Reader must be closed after a read failure");
  }

  @Test
  public void testCloseFailure() {
    IOException failure = new IOException("close failed");
    BufferedReader reader = new BufferedReader(new StringReader("complete body")) {
      @Override
      public void close() throws IOException {
        super.close();
        throw failure;
      }
    };

    assertThatThrownBy(() -> RequestUtils.readData(requestWithReader(reader)))
      .isInstanceOf(WxRuntimeException.class).hasCause(failure);
  }

  private HttpServletRequest requestWithReader(BufferedReader reader) {
    return (HttpServletRequest) Proxy.newProxyInstance(
      HttpServletRequest.class.getClassLoader(), new Class<?>[]{HttpServletRequest.class},
      (proxy, method, args) -> {
        if ("getReader".equals(method.getName())) {
          return reader;
        }
        throw new UnsupportedOperationException(method.getName());
      });
  }
}
