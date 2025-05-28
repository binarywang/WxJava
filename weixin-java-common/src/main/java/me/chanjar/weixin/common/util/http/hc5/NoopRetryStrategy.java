package me.chanjar.weixin.common.util.http.hc5;

import org.apache.hc.client5.http.HttpRequestRetryStrategy;
import org.apache.hc.core5.http.HttpRequest;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.protocol.HttpContext;
import org.apache.hc.core5.util.TimeValue;

import java.io.IOException;

/**
 * NoopRetryStrategy
 *
 * @author altusea
 */
public class NoopRetryStrategy implements HttpRequestRetryStrategy {

  public static final HttpRequestRetryStrategy INSTANCE = new NoopRetryStrategy();

  @Override
  public boolean retryRequest(HttpRequest request, IOException exception, int execCount, HttpContext context) {
    return false;
  }

  @Override
  public boolean retryRequest(HttpResponse response, int execCount, HttpContext context) {
    return false;
  }

  @Override
  public TimeValue getRetryInterval(HttpResponse response, int execCount, HttpContext context) {
    return TimeValue.ZERO_MILLISECONDS;
  }
}
