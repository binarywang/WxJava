package com.github.binarywang.wxpay.v3.auth;


import com.github.binarywang.wxpay.v3.Validator;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import java.util.function.LongSupplier;

/**
 * @author spvycf & F00lish
 */
@Slf4j
public class WxPayValidator implements Validator {
  /**
   * 默认允许的应答时间戳偏差（秒），与微信支付官方 SDK 保持一致：5 分钟。
   */
  public static final long DEFAULT_TIMESTAMP_TOLERANCE_SECONDS = TimeUnit.MINUTES.toSeconds(5);

  private final Verifier verifier;
  private final long timestampToleranceSeconds;
  private final LongSupplier currentTimeSeconds;

  /**
   * 使用默认的时间戳容差（{@link #DEFAULT_TIMESTAMP_TOLERANCE_SECONDS}）构造校验器。
   *
   * @param verifier 平台证书验签器
   */
  public WxPayValidator(Verifier verifier) {
    this(verifier, DEFAULT_TIMESTAMP_TOLERANCE_SECONDS);
  }

  /**
   * 构造校验器并指定允许的应答时间戳偏差。
   * <p>
   * 校验签名之前会先比较应答头 Wechatpay-Timestamp 与本地时间，
   * 二者之差（绝对值）超过容差的应答视为验签失败，以防止重放攻击。
   * </p>
   *
   * @param verifier                  平台证书验签器
   * @param timestampToleranceSeconds 允许的时间戳偏差（秒）；传 0 或负数表示关闭时间戳校验，
   *                                  仅建议在本地时钟确实无法与微信服务器保持同步的环境中使用
   */
  public WxPayValidator(Verifier verifier, long timestampToleranceSeconds) {
    this(verifier, timestampToleranceSeconds, () -> TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
  }

  /**
   * 供单元测试注入固定时钟使用。
   *
   * @param verifier                  平台证书验签器
   * @param timestampToleranceSeconds 允许的时间戳偏差（秒），0 或负数表示关闭校验
   * @param currentTimeSeconds        当前时间（秒）的来源
   */
  WxPayValidator(Verifier verifier, long timestampToleranceSeconds, LongSupplier currentTimeSeconds) {
    this.verifier = verifier;
    this.timestampToleranceSeconds = timestampToleranceSeconds;
    this.currentTimeSeconds = currentTimeSeconds;
  }

  @Override
  public final boolean validate(CloseableHttpResponse response) throws IOException {
    if (!ContentType.APPLICATION_JSON.getMimeType().equals(ContentType.parse(String.valueOf(response.getFirstHeader(
      "Content-Type").getValue())).getMimeType())) {
      return true;
    }
    Header serialNo = response.getFirstHeader("Wechatpay-Serial");
    Header sign = response.getFirstHeader("Wechatpay-Signature");
    Header timestamp = response.getFirstHeader("Wechatpay-TimeStamp");
    Header nonce = response.getFirstHeader("Wechatpay-Nonce");

    if (timestamp == null || nonce == null || serialNo == null || sign == null) {
      return false;
    }

    if (!isTimestampFresh(timestamp.getValue())) {
      return false;
    }

    String message = buildMessage(response);
    return verifier.verify(serialNo.getValue(), message.getBytes(StandardCharsets.UTF_8), sign.getValue());
  }

  /**
   * 校验应答时间戳是否在允许的偏差范围内。
   *
   * @param timestamp 应答头 Wechatpay-Timestamp 的值（秒级时间戳）
   * @return 时间戳合法且与本地时间之差不超过容差时返回 true；容差为 0 或负数时恒为 true
   */
  private boolean isTimestampFresh(String timestamp) {
    if (this.timestampToleranceSeconds <= 0) {
      return true;
    }

    final long responseTime;
    try {
      responseTime = Long.parseLong(timestamp.trim());
    } catch (NumberFormatException e) {
      log.warn("微信支付应答时间戳格式非法，拒绝该应答: Wechatpay-Timestamp={}", timestamp);
      return false;
    }

    // 秒级 Unix 时间戳不可能为负；先拒绝负值，后面的减法和 Math.abs 才不会溢出
    if (responseTime < 0) {
      log.warn("微信支付应答时间戳为负数，拒绝该应答: Wechatpay-Timestamp={}", timestamp);
      return false;
    }

    long offset = Math.abs(this.currentTimeSeconds.getAsLong() - responseTime);
    if (offset > this.timestampToleranceSeconds) {
      log.warn("微信支付应答时间戳超出允许范围，拒绝该应答以防止重放: Wechatpay-Timestamp={}, 与本地时间相差 {} 秒, 允许偏差 {} 秒",
        timestamp, offset, this.timestampToleranceSeconds);
      return false;
    }
    return true;
  }

  protected final String buildMessage(CloseableHttpResponse response) throws IOException {
    String timestamp = response.getFirstHeader("Wechatpay-TimeStamp").getValue();
    String nonce = response.getFirstHeader("Wechatpay-Nonce").getValue();

    String body = getResponseBody(response);
    return timestamp + "\n"
      + nonce + "\n"
      + body + "\n";
  }

  protected final String getResponseBody(CloseableHttpResponse response) throws IOException {
    HttpEntity entity = response.getEntity();

    return (entity != null && entity.isRepeatable()) ? EntityUtils.toString(entity) : "";
  }
}
