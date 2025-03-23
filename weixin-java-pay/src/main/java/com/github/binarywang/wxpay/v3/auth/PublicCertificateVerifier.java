package com.github.binarywang.wxpay.v3.auth;

import java.security.*;
import java.security.cert.X509Certificate;
import java.util.Base64;
import me.chanjar.weixin.common.error.WxRuntimeException;

public class PublicCertificateVerifier implements Verifier{

    private final PublicKey publicKey;

    private Verifier certificateVerifier;

    private final X509PublicCertificate publicCertificate;

    public PublicCertificateVerifier(PublicKey publicKey, String publicId) {
        this.publicKey = publicKey;
        this.publicCertificate = new X509PublicCertificate(publicKey, publicId);
    }

   public void setOtherVerifier(Verifier verifier) {
      this.certificateVerifier = verifier;
   }

    @Override
    public boolean verify(String serialNumber, byte[] message, String signature) {
        if (!serialNumber.contains("PUB_KEY_ID")) {
            return this.certificateVerifier.verify(serialNumber, message, signature);
        }
        try {
            Signature sign = Signature.getInstance("SHA256withRSA");
            sign.initVerify(publicKey);
            sign.update(message);
            return sign.verify(Base64.getDecoder().decode(signature));
        } catch (NoSuchAlgorithmException e) {
            throw new WxRuntimeException("当前Java环境不支持SHA256withRSA", e);
        } catch (SignatureException e) {
            throw new WxRuntimeException("签名验证过程发生了错误", e);
        } catch (InvalidKeyException e) {
            throw new WxRuntimeException("无效的证书", e);
        }
    }

    @Override
    public X509Certificate getValidCertificate() {
        return this.publicCertificate;
    }
}
