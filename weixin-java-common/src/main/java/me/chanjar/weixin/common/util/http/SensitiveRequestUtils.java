package me.chanjar.weixin.common.util.http;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.error.WxRuntimeException;

/**
 * Safe parameter and exception handling for requests containing credentials.
 */
public final class SensitiveRequestUtils {
  private SensitiveRequestUtils() {
  }

  /**
   * Encodes one raw query parameter value, without interpreting existing percent escapes.
   *
   * @param value raw, non-null parameter value
   * @return UTF-8 form-encoded value
   * @throws NullPointerException if the value is null
   */
  public static String encodeQueryValue(String value) {
    try {
      return URLEncoder.encode(value, StandardCharsets.UTF_8.name());
    } catch (UnsupportedEncodingException e) {
      throw new IllegalStateException("UTF-8 is not available");
    }
  }

  /**
   * Retains the WeChat error code and stack frames without exposing response data or causes.
   * This is intended for credential-bearing entry points, not general exception conversion.
   *
   * @param failure original failure
   * @return safe exception without the original message, JSON, cause or suppressed exceptions
   */
  public static WxErrorException sanitize(WxErrorException failure) {
    WxErrorException safe = new WxErrorException(new WxError(failure.getError().getErrorCode(),
      "Sensitive request failed"));
    safe.setStackTrace(failure.getStackTrace());
    return safe;
  }

  /**
   * Removes request data from a runtime failure. Common argument, state and null failures
   * keep their categories; other runtime failures become {@link WxRuntimeException}.
   * Original exception class names and stack frames remain available for diagnosis.
   *
   * @param failure original failure
   * @return safe exception without the original message, cause or suppressed exceptions
   */
  public static RuntimeException sanitize(RuntimeException failure) {
    String message = "Sensitive request failed (" + failure.getClass().getName() + ")";
    RuntimeException safe;
    if (failure instanceof IllegalArgumentException) {
      safe = new IllegalArgumentException(message);
    } else if (failure instanceof IllegalStateException) {
      safe = new IllegalStateException(message);
    } else if (failure instanceof NullPointerException) {
      safe = new NullPointerException(message);
    } else {
      safe = new WxRuntimeException(message);
    }
    safe.setStackTrace(failure.getStackTrace());
    return safe;
  }
}
