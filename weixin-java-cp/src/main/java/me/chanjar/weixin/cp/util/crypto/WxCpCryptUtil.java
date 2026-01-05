package me.chanjar.weixin.cp.util.crypto;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.crypto.WxCryptUtil;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import org.apache.commons.lang3.Strings;
import org.bouncycastle.asn1.pkcs.RSAPrivateKey;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.util.Base64;
import java.util.Objects;

/**
 * The type Wx cp crypt util.
 *
 * @author qian
 */
public class WxCpCryptUtil extends WxCryptUtil {
  /**
   * Instantiates a new Wx cp crypt util.
   *
   * @param wxCpConfigStorage the wx cp config storage
   */
  public WxCpCryptUtil(WxCpConfigStorage wxCpConfigStorage) {
    /*
     * @param token          公众平台上，开发者设置的token
     * @param encodingAesKey 公众平台上，开发者设置的EncodingAESKey
     * @param appidOrCorpid          公众平台appid
     */
    String encodingAesKey = wxCpConfigStorage.getAesKey();
    String token = wxCpConfigStorage.getToken();
    String corpId = wxCpConfigStorage.getCorpId();

    this.token = token;
    this.appidOrCorpid = corpId;
    this.aesKey = Base64.getDecoder().decode(Strings.CS.remove(encodingAesKey, " "));
  }

  /**
   * 判断使用PKCS8或者PKCS1进行解密
   *
   * @param encryptRandomKey 使用PUBLICKEY_VER指定版本的公钥进行非对称加密后base64加密的内容
   * @param msgAuditPriKey   会话存档私钥
   * @param pkcs1            使用什么方式进行解密，1代表使用PKCS1进行解密，2代表PKCS8进行解密 ...
   * @return string
   * @throws Exception the exception
   */
  public static String decryptPriKey(String encryptRandomKey, String msgAuditPriKey, Integer pkcs1) throws Exception {
    if (Objects.isNull(pkcs1)) {
      throw new WxErrorException("请配置会话存档解密方式");
    }

    if (Objects.equals(pkcs1, 1)) {
      return decryptPriKeyByPKCS1(encryptRandomKey, msgAuditPriKey);
    }

    return decryptPriKeyByPKCS8(encryptRandomKey, msgAuditPriKey);
  }

  /**
   * PKCS8 解密私钥
   *
   * @param encryptRandomKey the encrypt random key
   * @param msgAuditPriKey   the msg audit pri key
   * @return string
   * @throws Exception the exception
   */
  public static String decryptPriKeyByPKCS8(String encryptRandomKey, String msgAuditPriKey) throws Exception {
    String privateKey = msgAuditPriKey.replaceAll("(\r\n|\r|\n|\n\r)", "")
      .replace("-----BEGIN PRIVATE KEY-----", "")
      .replace("-----END PRIVATE KEY-----", "")
      .replace(" ", "");

    byte[] keyBytes = Base64.getDecoder().decode(privateKey);
    PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);

    Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
    cipher.init(Cipher.DECRYPT_MODE, priKey);
    byte[] utf8 = cipher.doFinal(Base64.getDecoder().decode(encryptRandomKey));
    return new String(utf8, StandardCharsets.UTF_8);
  }

  /**
   * 会话存档，PKCS1 解密私钥
   * 企业获取的会话内容将用公钥加密，企业用自行保存的私钥解开会话内容数据
   *
   * @param encryptRandomKey 使用PUBLICKEY_VER指定版本的公钥进行非对称加密后base64加密的内容，需要业务方先base64
   *                         decode处理后，再使用指定版本的私钥进行解密，得出内容。String类型
   * @param msgAuditPriKey   会话存档私钥
   * @return string
   * @throws Exception the exception
   */
  public static String decryptPriKeyByPKCS1(String encryptRandomKey, String msgAuditPriKey) throws Exception {
    String privateKey = msgAuditPriKey.replaceAll("(\r\n|\r|\n|\n\r)", "")
      .replace("-----BEGIN RSA PRIVATE KEY-----", "")
      .replace("-----END RSA PRIVATE KEY-----", "")
      .replace(" ", "");

    byte[] keyBytes = Base64.getDecoder().decode(privateKey);
    // Java 8 以后 sun.security.util.DerInputStream 和 sun.security.util.DerValue 无法使用
    // 因此改为通过 org.bouncycastle:bcprov-jdk18on 来完成 ASN1 编码数据解析
    final RSAPrivateKey key = RSAPrivateKey.getInstance(keyBytes);
    final RSAPrivateCrtKeySpec keySpec = new RSAPrivateCrtKeySpec(
      key.getModulus(),
      key.getPublicExponent(),
      key.getPrivateExponent(),
      key.getPrime1(),
      key.getPrime2(),
      key.getExponent1(),
      key.getExponent2(),
      key.getCoefficient());

    PrivateKey priKey = KeyFactory.getInstance("RSA").generatePrivate(keySpec);
    Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
    cipher.init(Cipher.DECRYPT_MODE, priKey);
    byte[] utf8 = cipher.doFinal(Base64.getDecoder().decode(encryptRandomKey));
    return new String(utf8, StandardCharsets.UTF_8);
  }

}
