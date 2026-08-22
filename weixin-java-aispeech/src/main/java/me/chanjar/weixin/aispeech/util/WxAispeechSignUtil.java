package me.chanjar.weixin.aispeech.util;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

public final class WxAispeechSignUtil {
  private WxAispeechSignUtil() {
  }

  public static String calcDialogSign(String token, long timestamp, String nonce, String body) {
    String bodyMd5 = DigestUtils.md5Hex(defaultString(body));
    return DigestUtils.md5Hex(defaultString(token) + timestamp + defaultString(nonce) + bodyMd5);
  }

  public static String calcKnowledgeSignature(String secretKey, long timestamp, String nonce, String requestId,
                                              String requestBody) {
    String payload = timestamp + "\n" + defaultString(nonce) + "\n" + defaultString(requestId) + "\n"
      + defaultString(requestBody);
    try {
      Mac mac = Mac.getInstance("HmacSHA256");
      mac.init(new SecretKeySpec(defaultString(secretKey).getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
      return bytesToHex(mac.doFinal(payload.getBytes(StandardCharsets.UTF_8)));
    } catch (GeneralSecurityException e) {
      throw new IllegalStateException("HmacSHA256 signature failed", e);
    }
  }

  public static String encryptAesCbcToBase64(String plainText, String aesKey) {
    try {
      byte[] keyBytes = decodeAesKey(aesKey);
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyBytes, "AES"), new IvParameterSpec(Arrays.copyOf(keyBytes, 16)));
      byte[] encrypted = cipher.doFinal(defaultString(plainText).getBytes(StandardCharsets.UTF_8));
      return Base64.encodeBase64String(encrypted);
    } catch (GeneralSecurityException e) {
      throw new IllegalStateException("AES CBC encrypt failed", e);
    }
  }

  public static String decryptAesCbcFromBase64(String cipherTextBase64, String aesKey) {
    try {
      byte[] keyBytes = decodeAesKey(aesKey);
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyBytes, "AES"), new IvParameterSpec(Arrays.copyOf(keyBytes, 16)));
      byte[] encrypted = Base64.decodeBase64(defaultString(cipherTextBase64));
      return new String(cipher.doFinal(encrypted), StandardCharsets.UTF_8);
    } catch (GeneralSecurityException e) {
      throw new IllegalStateException("AES CBC decrypt failed", e);
    }
  }

  private static byte[] decodeAesKey(String aesKey) {
    return Base64.decodeBase64(defaultString(aesKey) + "=");
  }

  private static String defaultString(String value) {
    return value == null ? "" : value;
  }

  private static String bytesToHex(byte[] bytes) {
    StringBuilder builder = new StringBuilder(bytes.length * 2);
    for (byte b : bytes) {
      builder.append(String.format("%02x", b));
    }
    return builder.toString();
  }
}
