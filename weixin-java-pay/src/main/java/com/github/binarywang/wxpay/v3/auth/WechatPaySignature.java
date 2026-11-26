package com.github.binarywang.wxpay.v3.auth;

import org.bouncycastle.jcajce.spec.SM2ParameterSpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Signature;

final class WechatPaySignature {
  static final String RSA_TYPE = "WECHATPAY2-SHA256-RSA2048";
  static final String SM2_TYPE = "WECHATPAY2-SM2-WITH-SM3";

  private static final byte[] SM2_USER_ID =
    "1234567812345678".getBytes(StandardCharsets.US_ASCII);
  private static final Provider BOUNCY_CASTLE_PROVIDER = new BouncyCastleProvider();

  private WechatPaySignature() {
  }

  static Signature create(String signatureType)
    throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
    if (signatureType == null || RSA_TYPE.equals(signatureType)) {
      return Signature.getInstance("SHA256withRSA");
    }
    if (SM2_TYPE.equals(signatureType)) {
      Signature signature = Signature.getInstance("SM3withSM2", BOUNCY_CASTLE_PROVIDER);
      signature.setParameter(new SM2ParameterSpec(SM2_USER_ID));
      return signature;
    }
    throw new IllegalArgumentException("不支持的微信支付签名类型: " + signatureType);
  }

  static boolean isSupported(String signatureType) {
    return signatureType == null || RSA_TYPE.equals(signatureType) || SM2_TYPE.equals(signatureType);
  }
}
