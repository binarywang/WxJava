package me.chanjar.weixin.common.util;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 *  签名工具类
 *  Created by BinaryWang on 2018/7/11.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Slf4j
public class SignUtils {
  /**
   * HmacSHA256 签名算法
   *
   * @param message 签名数据
   * @param key     签名密钥
   * @return 签名结果
   */
  public static String createHmacSha256Sign(String message, String key) {
    try {
      Mac sha256 = Mac.getInstance("HmacSHA256");
      SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
      sha256.init(secretKeySpec);
      byte[] bytes = sha256.doFinal(message.getBytes(StandardCharsets.UTF_8));
      return Hex.encodeHexString(bytes).toUpperCase();
    } catch (NoSuchAlgorithmException | InvalidKeyException e) {
      SignUtils.log.error(e.getMessage(), e);
    }

    return null;
  }
}
