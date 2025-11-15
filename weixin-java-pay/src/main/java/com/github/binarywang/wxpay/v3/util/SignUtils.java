package com.github.binarywang.wxpay.v3.util;

import me.chanjar.weixin.common.error.WxRuntimeException;

import java.security.*;
import java.util.Base64;
import java.util.Random;

/**
* @author cloudx
 */
public class SignUtils {
  public static String sign(String string, PrivateKey privateKey) {
    try {
      Signature sign = Signature.getInstance("SHA256withRSA");
      sign.initSign(privateKey);
      sign.update(string.getBytes());

      return Base64.getEncoder().encodeToString(sign.sign());
    } catch (NoSuchAlgorithmException e) {
      throw new WxRuntimeException("当前Java环境不支持SHA256withRSA", e);
    } catch (SignatureException e) {
      throw new WxRuntimeException("签名计算失败", e);
    } catch (InvalidKeyException e) {
      throw new WxRuntimeException("无效的私钥", e);
    }
  }

  /**
   * 随机生成32位字符串.
   */
  public static String genRandomStr() {
    return genRandomStr(32);
  }

  private static volatile Random random;

  private static Random getRandom() {
    if (random == null) {
      synchronized (SignUtils.class) {
        if (random == null) {
          random = new Random();
        }
      }
    }
    return random;
  }

  /**
   * 生成随机字符串
   *
   * @param length 字符串长度
   * @return 随机字符串
   */
  public static String genRandomStr(int length) {
    String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    Random r = getRandom();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < length; i++) {
      int number = r.nextInt(base.length());
      sb.append(base.charAt(number));
    }
    return sb.toString();
  }
}
