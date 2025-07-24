package com.github.binarywang.wxpay.v3.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import me.chanjar.weixin.common.error.WxRuntimeException;

public class PemUtils {

  public static PrivateKey loadPrivateKey(InputStream inputStream) {
    try {
      ByteArrayOutputStream array = new ByteArrayOutputStream();
      byte[] buffer = new byte[1024];
      int length;
      while ((length = inputStream.read(buffer)) != -1) {
        array.write(buffer, 0, length);
      }

      String privateKey = array.toString("utf-8")
          .replace("-----BEGIN PRIVATE KEY-----", "")
          .replace("-----END PRIVATE KEY-----", "")
          .replaceAll("\\s+", "");

      KeyFactory kf = KeyFactory.getInstance("RSA");
      return kf.generatePrivate(
          new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey)));
    } catch (NoSuchAlgorithmException e) {
      throw new WxRuntimeException("当前Java环境不支持RSA", e);
    } catch (InvalidKeySpecException e) {
      throw new WxRuntimeException("无效的密钥格式");
    } catch (IOException e) {
      throw new WxRuntimeException("无效的密钥");
    }
  }

  public static X509Certificate loadCertificate(InputStream inputStream) {
    try {
      CertificateFactory cf = CertificateFactory.getInstance("X.509");
      X509Certificate cert = (X509Certificate) cf.generateCertificate(inputStream);
      cert.checkValidity();
      return cert;
    } catch (CertificateExpiredException e) {
      throw new WxRuntimeException("证书已过期", e);
    } catch (CertificateNotYetValidException e) {
      throw new WxRuntimeException("证书尚未生效", e);
    } catch (CertificateException e) {
      throw new WxRuntimeException("无效的证书", e);
    }
  }

  public static PublicKey loadPublicKey(InputStream inputStream){
    try {
      ByteArrayOutputStream array = new ByteArrayOutputStream();
      byte[] buffer = new byte[1024];
      int length;
      while ((length = inputStream.read(buffer)) != -1) {
        array.write(buffer, 0, length);
      }

      String publicKey = array.toString("utf-8")
        .replace("-----BEGIN PUBLIC KEY-----", "")
        .replace("-----END PUBLIC KEY-----", "")
        .replaceAll("\\s+", "");
      return KeyFactory.getInstance("RSA")
        .generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey)));
    } catch (NoSuchAlgorithmException e) {
      throw new WxRuntimeException("当前Java环境不支持RSA", e);
    } catch (InvalidKeySpecException e) {
      throw new WxRuntimeException("无效的密钥格式");
    } catch (IOException e) {
      throw new WxRuntimeException("无效的密钥");
    }
  }
}
