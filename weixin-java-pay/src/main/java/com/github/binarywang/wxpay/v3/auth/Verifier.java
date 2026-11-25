package com.github.binarywang.wxpay.v3.auth;

import java.security.cert.X509Certificate;

public interface Verifier {
  boolean verify(String serialNumber, byte[] message, String signature);

  /**
   * 按响应头指定的签名类型验签。
   *
   * @param serialNumber 证书或公钥序列号
   * @param message 待验签消息
   * @param signature Base64 编码的签名
   * @param signatureType 微信支付签名类型；为空时按 RSA 处理
   * @return 验签成功时返回 true
   */
  default boolean verify(String serialNumber, byte[] message, String signature, String signatureType) {
    return verify(serialNumber, message, signature);
  }

  X509Certificate getValidCertificate();

  default void setOtherVerifier(Verifier verifier) {};
}
