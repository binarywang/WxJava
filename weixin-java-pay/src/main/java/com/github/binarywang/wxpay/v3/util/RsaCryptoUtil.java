package com.github.binarywang.wxpay.v3.util;

import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.v3.SpecEncrypt;
import me.chanjar.weixin.common.error.WxRuntimeException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.Collection;

/**
 * 微信支付敏感信息加密
 * 文档见： https://wechatpay-api.gitbook.io/wechatpay-api-v3/qian-ming-zhi-nan-1/min-gan-xin-xi-jia-mi
 *
 * @author zhouyongshen
 **/
public class RsaCryptoUtil {


  static String JAVA_LANG_STRING = "java.lang.String";

  public static void encryptFields(Object encryptObject, X509Certificate certificate) throws WxPayException {
    try {
      encryptField(encryptObject, certificate);
    } catch (Exception e) {
      throw new WxPayException("敏感信息加密失败", e);
    }
  }

  private static void encryptField(Object encryptObject, X509Certificate certificate) throws IllegalAccessException, IllegalBlockSizeException {
    Class<?> infoClass = encryptObject.getClass();
    Field[] infoFieldArray = infoClass.getDeclaredFields();
    for (Field field : infoFieldArray) {
      if (field.isAnnotationPresent(SpecEncrypt.class)) {
        //字段使用了@SpecEncrypt进行标识
        if (field.getType().getTypeName().equals(JAVA_LANG_STRING)) {
          field.setAccessible(true);
          Object oldValue = field.get(encryptObject);
          if (oldValue != null) {
            String oldStr = (String) oldValue;
            if (!oldStr.trim().isEmpty()) {
              field.set(encryptObject, encryptOAEP(oldStr, certificate));
            }
          }
        } else {
          field.setAccessible(true);
          Object obj = field.get(encryptObject);
          if (obj == null) {
            continue;
          }
          if (obj instanceof Collection<?>) {
            Collection<?> collection = (Collection<?>) obj;
            for (Object o : collection) {
              if (o != null) {
                encryptField(o, certificate);
              }
            }
          } else {
            encryptField(obj, certificate);
          }
        }
      }
    }
  }

  public static String encryptOAEP(String message, X509Certificate certificate)
    throws IllegalBlockSizeException {
    try {
      Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-1AndMGF1Padding");
      cipher.init(Cipher.ENCRYPT_MODE, certificate.getPublicKey());

      byte[] data = message.getBytes(StandardCharsets.UTF_8);
      byte[] ciphertext = cipher.doFinal(data);
      return Base64.getEncoder().encodeToString(ciphertext);
    } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
      throw new WxRuntimeException("当前Java环境不支持RSA v1.5/OAEP", e);
    } catch (InvalidKeyException e) {
      throw new IllegalArgumentException("无效的证书", e);
    } catch (IllegalBlockSizeException | BadPaddingException e) {
      throw new IllegalBlockSizeException("加密原串的长度不能超过214字节");
    }
  }

  public static String decryptOAEP(String ciphertext, PrivateKey privateKey)
    throws BadPaddingException {
    try {
      Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-1AndMGF1Padding");
      cipher.init(Cipher.DECRYPT_MODE, privateKey);

      byte[] data = Base64.getDecoder().decode(ciphertext);
      return new String(cipher.doFinal(data), StandardCharsets.UTF_8);
    } catch (NoSuchPaddingException | NoSuchAlgorithmException e) {
      throw new WxRuntimeException("当前Java环境不支持RSA v1.5/OAEP", e);
    } catch (InvalidKeyException e) {
      throw new IllegalArgumentException("无效的私钥", e);
    } catch (BadPaddingException | IllegalBlockSizeException e) {
      throw new BadPaddingException("解密失败");
    }
  }
}
