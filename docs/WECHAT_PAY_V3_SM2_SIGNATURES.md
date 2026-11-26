# 微信支付 APIv3：SM2 请求签名与同步响应验签

此能力只覆盖 **APIv3 HTTP 请求签名**和**同步 HTTP 响应验签**（SDK 原本验签的成功 JSON 响应），不是完整国密接入。没有响应签名类型头时仍按 RSA 验证。

## 使用预加载的国密平台证书

参照[微信支付官方 SDK 国密示例](https://github.com/wechatpay-apiv3/wechatpay-java/blob/main/shangmi/README.md#使用国密-sdk)，在可信渠道取得对应的微信支付 **SM2 平台证书**，在应用启动时预加载。商户 SM2 私钥用于签名，微信支付平台证书用于验签，两者不可混用。

下面是 WxJava 手动构造 HTTP 客户端的入口。示例只使用本地预加载证书，不触发自动下载。生产环境应在证书到期前从可信渠道更新本地证书并重建客户端；不要关闭默认的响应时间戳检查。

```java
CertificateFactory factory = CertificateFactory.getInstance("X.509", new BouncyCastleProvider());
X509Certificate platformCert;
try (InputStream in = Files.newInputStream(platformCertPath)) {
  platformCert = (X509Certificate) factory.generateCertificate(in);
  platformCert.checkValidity();
}

try (CloseableHttpClient client = WxPayV3HttpClientBuilder.create()
  .withMerchant(mchId, merchantCertSerialNo, merchantSm2PrivateKey)
  .withValidator(new WxPayValidator(new CertificatesVerifier(Collections.singletonList(platformCert))))
  .build()) {
  // 执行适用的 APIv3 同步 HTTP 请求；响应的证书序列号应与配置匹配。
}
```

此路径用本地模拟平台证书和 HTTP 应答验证，尚未与真实国密商户联调。**不要把 `WxPayConfig.fullPublicKeyModel` 当作已验证的国密商户配置**：[普通微信支付公钥模式](https://pay.wechatpay.cn/doc/v3/merchant/4013053249)记录的是 RSA 公钥和 `PUB_KEY_ID`。本项目的独立 SM2 公钥测试使用的是模拟密钥，不能证明微信支付会为国密商户提供对应的独立公钥和 ID。

## 暂不支持

- **自动下载或轮换国密平台证书**：官方接口需选择 `algorithm_type=SM2`，证书数据需要 SM4-GCM 解密。SDK 的普通证书自动更新路径未增加这两项能力。
- **国密异步回调**：普通支付和分账等回调的国密验签和 SM4-GCM 解密不在本阶段范围内。
- **国密敏感字段加解密**：属于独立能力。本阶段不要把需要该能力的业务视为已适配。

参考：[微信支付国密指引](https://pay.wechatpay.cn/doc/v3/merchant/4012076194) · [国密证书和密钥](https://pay.wechatpay.cn/doc/v3/merchant/4012076002)。
