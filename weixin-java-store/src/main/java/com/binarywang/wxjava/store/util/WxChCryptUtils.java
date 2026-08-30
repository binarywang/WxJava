package com.binarywang.wxjava.store.util;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import lombok.extern.slf4j.Slf4j;
import com.binarywang.wxjava.store.config.WxStoreConfig;
import me.chanjar.weixin.common.util.crypto.PKCS7Encoder;
import me.chanjar.weixin.common.util.crypto.WxCryptUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Slf4j
public class WxChCryptUtils extends WxCryptUtil {

  protected static final Charset UTF_8 = StandardCharsets.UTF_8;

  public WxChCryptUtils(WxStoreConfig config) {
    this.appidOrCorpid = config.getAppid();
    this.token = config.getToken();
    this.aesKey = Base64.decodeBase64(StringUtils.trim(config.getAesKey()) + "=");
  }

  /**
   * AES解密
   *
   * @param sessionKey    session_key
   * @param encryptedData 消息密文
   * @param ivStr         iv字符串
   */
  public static String decrypt(String sessionKey, String encryptedData, String ivStr) {
    try {
      AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
      params.init(new IvParameterSpec(Base64.decodeBase64(ivStr)));

      Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
      cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(Base64.decodeBase64(sessionKey), "AES"), params);

      return new String(PKCS7Encoder.decode(cipher.doFinal(Base64.decodeBase64(encryptedData))), UTF_8);
    } catch (Exception e) {
      throw new RuntimeException("AES解密失败！", e);
    }
  }

}
