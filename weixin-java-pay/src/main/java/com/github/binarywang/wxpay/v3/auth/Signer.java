package com.github.binarywang.wxpay.v3.auth;

public interface Signer {
  SignatureResult sign(byte[] message);

  /**
   * 获取用于 Authorization 请求头的认证类型。
   *
   * @return 微信支付认证类型
   */
  default String getSchema() {
    return "WECHATPAY2-SHA256-RSA2048";
  }

  class SignatureResult {
    String sign;
    String certificateSerialNumber;

    public SignatureResult(String sign, String serialNumber) {
      this.sign = sign;
      this.certificateSerialNumber = serialNumber;
    }
  }
}
