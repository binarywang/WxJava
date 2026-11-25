package com.github.binarywang.wxpay.v3.auth;

import me.chanjar.weixin.common.error.WxRuntimeException;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Base64;

public class PrivateKeySigner implements Signer {
  private String certificateSerialNumber;

  private PrivateKey privateKey;

  public PrivateKeySigner(String serialNumber, PrivateKey privateKey) {
    this.certificateSerialNumber = serialNumber;
    this.privateKey = privateKey;
  }

  @Override
  public SignatureResult sign(byte[] message) {
    try {
      String schema = getSchema();
      Signature sign = WechatPaySignature.create(schema);
      sign.initSign(privateKey);
      sign.update(message);

      return new SignatureResult(
          Base64.getEncoder().encodeToString(sign.sign()), certificateSerialNumber);
    } catch (InvalidAlgorithmParameterException e) {
      throw new WxRuntimeException("签名参数无效", e);
    } catch (NoSuchAlgorithmException e) {
      throw new WxRuntimeException("当前Java环境不支持" + getSignatureAlgorithm(), e);
    } catch (SignatureException e) {
      throw new WxRuntimeException("签名计算失败", e);
    } catch (InvalidKeyException e) {
      throw new WxRuntimeException("无效的私钥", e);
    }
  }

  @Override
  public String getSchema() {
    return isSm2Key() ? WechatPaySignature.SM2_TYPE : WechatPaySignature.RSA_TYPE;
  }

  private boolean isSm2Key() {
    String algorithm = privateKey.getAlgorithm();
    return "EC".equalsIgnoreCase(algorithm) || "ECDSA".equalsIgnoreCase(algorithm)
      || "SM2".equalsIgnoreCase(algorithm);
  }

  private String getSignatureAlgorithm() {
    return isSm2Key() ? "SM3withSM2" : "SHA256withRSA";
  }
}
