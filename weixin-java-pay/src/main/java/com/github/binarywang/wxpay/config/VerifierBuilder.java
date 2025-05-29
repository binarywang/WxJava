package com.github.binarywang.wxpay.config;

import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.v3.auth.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * 验证器构建.
 *
 * @author holy
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class VerifierBuilder {
  /**
   * 构建验证器.
   * <p>
   * 场景
   * <pre>
   *   1. 老商户号，只有平台证书，未开通公钥 （已验证）
   *   2. 新商户号，被强制开通公钥，没有平台证书 （已验证）
   *   3. 老商户号，有平台证书，主动开通公钥 （未验证，具备条件的朋友，可以帮忙验证下）
   *   ...
   * </pre>
   *
   * @param certSerialNo       c
   * @param mchId              m
   * @param apiV3Key           a
   * @param merchantPrivateKey m
   * @param wxPayHttpProxy     w
   * @param certAutoUpdateTime c
   * @param payBaseUrl         p
   * @param publicKeyId        p
   * @param publicKey          p
   * @return v
   * @throws WxPayException e
   */
  @SuppressWarnings("java:S107")
  static Verifier build(
    // 平台证书 - 依赖参数
    String certSerialNo,
    String mchId,
    String apiV3Key,
    PrivateKey merchantPrivateKey,
    WxPayHttpProxy wxPayHttpProxy,
    int certAutoUpdateTime,
    String payBaseUrl,
    // 公钥 - 依赖参数
    String publicKeyId,
    PublicKey publicKey
  ) throws WxPayException {
    Verifier certificatesVerifier = null;
    Exception ex = null;

    // 构建平台证书验证器
    // （沿用旧逻辑）优先构建平台证书验证器，因为公钥验证器需要平台证书验证器 (见以下 .setOtherVerifier )
    // 新商户号默认无平台证书，已确认无法构建平台证书验证器，会抛出异常；老商户号，有平台证书主动开通公钥的情况，待具备条件的朋友验证
    // 建议公钥模式稳定后，优先构建公钥验证器，以免每次都尝试构建平台证书验证器，且失败 {@link com.github.binarywang.wxpay.v3.auth.PublicCertificateVerifier.verify}
    if (merchantPrivateKey != null && StringUtils.isNoneBlank(certSerialNo, apiV3Key)) {
      try {
        certificatesVerifier = getCertificatesVerifier(
          certSerialNo, mchId, apiV3Key, merchantPrivateKey, wxPayHttpProxy, certAutoUpdateTime, payBaseUrl
        );
      } catch (Exception e) {
        ex = e;
      }
    }

    // 构建公钥验证器
    if (publicKey != null && StringUtils.isNotBlank(publicKeyId)) {
      try {
        certificatesVerifier = getPublicCertVerifier(publicKeyId, publicKey, certificatesVerifier);
      } catch (Exception e) {
        ex = e;
      }
    }
    if (certificatesVerifier != null) {
      return certificatesVerifier;
    }

    // 有异常时抛出
    if (ex != null) {
      throw new WxPayException(ex.getMessage(), ex);
    }

    // 没有证书验证器时。不确定是否抛出异常，沿用之前逻辑，返回 null
    return null;
  }

  /**
   * 针对完全使用公钥的场景
   * @param publicKeyId 公钥id
   * @param publicKey 公钥
   * @return
   */
  static Verifier buildPublicCertVerifier(String publicKeyId, PublicKey publicKey) {
    return getPublicCertVerifier(publicKeyId, publicKey, null);
  }

  /**
   * 获取证书验证器.
   *
   * @param certSerialNo       certSerialNo
   * @param mchId              mchId
   * @param apiV3Key           apiV3Key
   * @param merchantPrivateKey merchantPrivateKey
   * @param wxPayHttpProxy     wxPayHttpProxy
   * @param certAutoUpdateTime certAutoUpdateTime
   * @param payBaseUrl         payBaseUrl
   * @return verifier
   */
  private static AutoUpdateCertificatesVerifier getCertificatesVerifier(
    String certSerialNo, String mchId, String apiV3Key, PrivateKey merchantPrivateKey,
    WxPayHttpProxy wxPayHttpProxy, int certAutoUpdateTime, String payBaseUrl
  ) {
    return new AutoUpdateCertificatesVerifier(
      new WxPayCredentials(mchId, new PrivateKeySigner(certSerialNo, merchantPrivateKey)),
      apiV3Key.getBytes(StandardCharsets.UTF_8), certAutoUpdateTime,
      payBaseUrl, wxPayHttpProxy);
  }

  /**
   * 获取公钥验证器.
   *
   * @param publicKeyId          id
   * @param publicKey            key
   * @param certificatesVerifier verifier
   * @return verifier
   */
  private static Verifier getPublicCertVerifier(String publicKeyId, PublicKey publicKey, Verifier certificatesVerifier) {
    Verifier publicCertificatesVerifier = new PublicCertificateVerifier(publicKey, publicKeyId);
    publicCertificatesVerifier.setOtherVerifier(certificatesVerifier);
    certificatesVerifier = publicCertificatesVerifier;
    return certificatesVerifier;
  }
}
