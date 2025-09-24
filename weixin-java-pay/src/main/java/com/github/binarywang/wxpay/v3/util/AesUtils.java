package com.github.binarywang.wxpay.v3.util;

import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class AesUtils {

  static final int KEY_LENGTH_BYTE = 32;
  static final int TAG_LENGTH_BIT = 128;
  private final byte[] aesKey;

  public AesUtils(byte[] key) {
    if (key.length != KEY_LENGTH_BYTE) {
      throw new IllegalArgumentException("无效的ApiV3Key，长度必须为32个字节");
    }
    this.aesKey = key;
  }

  public static byte[] decryptToByte(byte[] nonce, byte[] cipherData, byte[] key)
    throws GeneralSecurityException {
    return decryptToByte(null, nonce, cipherData, key);
  }

  public static byte[] decryptToByte(byte[] associatedData, byte[] nonce, byte[] cipherData, byte[] key)
    throws GeneralSecurityException {
    try {
      Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

      SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
      GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH_BIT, nonce);

      cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, spec);
      if (associatedData != null) {
        cipher.updateAAD(associatedData);
      }
      return cipher.doFinal(cipherData);
    } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
      throw new IllegalStateException(e);
    } catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
      throw new IllegalArgumentException(e);
    }
  }

  public String decryptToString(byte[] associatedData, byte[] nonce, String ciphertext)
    throws GeneralSecurityException, IOException {
    try {
      Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

      SecretKeySpec key = new SecretKeySpec(aesKey, "AES");
      GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH_BIT, nonce);

      cipher.init(Cipher.DECRYPT_MODE, key, spec);
      cipher.updateAAD(associatedData);

      return new String(cipher.doFinal(Base64.getDecoder().decode(StringUtils.remove(ciphertext, " "))), StandardCharsets.UTF_8);
    } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
      throw new IllegalStateException(e);
    } catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
      throw new IllegalArgumentException(e);
    }
  }

  public static String decryptToString(String associatedData, String nonce, String ciphertext, String apiV3Key)
    throws GeneralSecurityException, IOException {
    try {
      Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

      SecretKeySpec key = new SecretKeySpec(apiV3Key.getBytes(StandardCharsets.UTF_8), "AES");
      GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH_BIT, nonce.getBytes(StandardCharsets.UTF_8));

      cipher.init(Cipher.DECRYPT_MODE, key, spec);
      cipher.updateAAD(associatedData.getBytes(StandardCharsets.UTF_8));

      return new String(cipher.doFinal(Base64.getDecoder().decode(ciphertext)), StandardCharsets.UTF_8);
    } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
      throw new IllegalStateException(e);
    } catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
      throw new IllegalArgumentException(e);
    }
  }


  public static String createSign(Map<String, String> map, String mchKey) {
    Map<String, String> params = map;
    SortedMap<String, String> sortedMap = new TreeMap<>(params);

    StringBuilder toSign = new StringBuilder();
    for (String key : sortedMap.keySet()) {
      String value = params.get(key);
      if ("sign".equals(key) || StringUtils.isEmpty(value)) {
        continue;
      }
      toSign.append(key).append("=").append(value).append("&");
    }
    toSign.append("key=" + mchKey);
    return HMACSHA256(toSign.toString(), mchKey);

  }

  public static String HMACSHA256(String data, String key) {
    try {
      Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
      SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
      sha256_HMAC.init(secret_key);
      byte[] array = sha256_HMAC.doFinal(data.getBytes(StandardCharsets.UTF_8));
      StringBuilder sb = new StringBuilder();
      for (byte item : array) {
        sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
      }
      return sb.toString().toUpperCase();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }


}
