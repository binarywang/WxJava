package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.customs.*;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.CustomDeclarationService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxRuntimeException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Base64;

/**
 * <pre>
 * 支付报关 实现.
 * Created by xifengzhu on 2022/05/05.
 * </pre>
 *
 * @author <a href="https://github.com/xifengzhu">xifengzhu</a>
 */
@RequiredArgsConstructor
public class CustomDeclarationServiceImpl implements CustomDeclarationService {
  private static final Gson GSON = new GsonBuilder().create();
  private final WxPayService payService;

  @Override
  public DeclarationResult declare(DeclarationRequest request) throws WxPayException {
    String response = this.payService.postV3(DECLARATION_BASE_URL.concat("/orders"), GSON.toJson(request));
    return GSON.fromJson(response, DeclarationResult.class);
  }

  @Override
  public DeclarationQueryResult query(DeclarationQueryRequest request) throws WxPayException {
    String url = String.format("%s/orders?appid=%s&mchid=%s&order_type=%s&order_no=%s&customs=%s&offset=%s&limit=%s",
      DECLARATION_BASE_URL,
      request.getAppid(),
      request.getMchid(),
      request.getOrderType(),
      request.getOrderNo(),
      request.getCustoms(),
      request.getOffset(),
      request.getLimit()
    );
    String result = this.payService.getV3(url);
    return GSON.fromJson(result, DeclarationQueryResult.class);
  }

  @Override
  public VerifyCertificateResult verifyCertificate(VerifyCertificateRequest request) throws WxPayException {
    this.encryptFields(request);
    String response = this.payService.postV3WithWechatpaySerial(DECLARATION_BASE_URL.concat("/verify-certificate"), GSON.toJson(request));
    return GSON.fromJson(response, VerifyCertificateResult.class);
  }

  @Override
  public DeclarationResult modify(DeclarationRequest request) throws WxPayException {
    String response = this.payService.patchV3(DECLARATION_BASE_URL.concat("/orders"), GSON.toJson(request));
    return GSON.fromJson(response, DeclarationResult.class);
  }

  @Override
  public RedeclareResult redeclare(RedeclareRequest request) throws WxPayException {
    String response = this.payService.postV3(DECLARATION_BASE_URL.concat("/redeclare"), GSON.toJson(request));
    return GSON.fromJson(response, RedeclareResult.class);
  }

  private void encryptFields(VerifyCertificateRequest request) throws WxPayException {
    try {
      request.setCertificateId(encryptOAEP(request.getCertificateId()));
      request.setCertificateName(encryptOAEP(request.getCertificateName()));
    } catch (Exception e) {
      throw new WxPayException("敏感信息加密失败", e);
    }
  }

  private X509Certificate getValidCertificate() {
    return this.payService.getConfig().getVerifier().getValidCertificate();
  }

  private String encryptOAEP(String message)
    throws IllegalBlockSizeException {
    X509Certificate certificate = getValidCertificate();
    try {
      // 身份信息校验 RSA 加密，填充方案使用 `RSAES-PKCS1-v1_5`
      // https://pay.weixin.qq.com/wiki/doc/api/wxpay/ch/declarecustom_ch/chapter3_2.shtml
      Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
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
}
