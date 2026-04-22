package cn.binarywang.wx.miniapp.util.crypt;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.Security;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import me.chanjar.weixin.common.error.WxRuntimeException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import cn.binarywang.wx.miniapp.config.WxMaConfig;
import me.chanjar.weixin.common.util.crypto.PKCS7Encoder;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxMaCryptUtils extends me.chanjar.weixin.common.util.crypto.WxCryptUtil {
  private static final Charset UTF_8 = StandardCharsets.UTF_8;

  public WxMaCryptUtils(WxMaConfig config) {
    this.appidOrCorpid = config.getAppid();
    this.token = config.getToken();
    this.aesKey = java.util.Base64.getDecoder().decode(StringUtils.remove(config.getAesKey(), " "));
  }

  /**
   * AES解密.
   *
   * @param sessionKey    session_key
   * @param encryptedData 消息密文
   * @param ivStr         iv字符串
   * @return 解密后的字符串
   */
  public static String decrypt(String sessionKey, String encryptedData, String ivStr) {
    try {
      AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
      params.init(new IvParameterSpec(Base64.decodeBase64(ivStr)));

      Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
      cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(Base64.decodeBase64(sessionKey), "AES"), params);

      return new String(PKCS7Encoder.decode(cipher.doFinal(Base64.decodeBase64(encryptedData))), UTF_8);
    } catch (Exception e) {
      throw new WxRuntimeException("AES解密失败！", e);
    }
  }


  /**
   * AES解密.
   *
   * @param sessionKey    session_key
   * @param encryptedData 消息密文
   * @param ivStr         iv字符串
   * @return 解密后的字符串
   */
  public static String decryptAnotherWay(String sessionKey, String encryptedData, String ivStr) {
    byte[] keyBytes = Base64.decodeBase64(sessionKey.getBytes(UTF_8));

    int base = 16;
    if (keyBytes.length % base != 0) {
      int groups = keyBytes.length / base + (keyBytes.length % base != 0 ? 1 : 0);
      byte[] temp = new byte[groups * base];
      Arrays.fill(temp, (byte) 0);
      System.arraycopy(keyBytes, 0, temp, 0, keyBytes.length);
      keyBytes = temp;
    }

    Security.addProvider(new BouncyCastleProvider());
    Key key = new SecretKeySpec(keyBytes, "AES");
    try {
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
      cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(Base64.decodeBase64(ivStr.getBytes(UTF_8))));
      return new String(cipher.doFinal(Base64.decodeBase64(encryptedData.getBytes(UTF_8))), UTF_8);
    } catch (Exception e) {
      throw new WxRuntimeException("AES解密失败！", e);
    }
  }

  /**
   * 使用用户加密 key 对数据进行 AES-128-CBC 解密（用于小程序加密网络通道）.
   *
   * <pre>
   * 参考文档：https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/user-encryptkey.html
   * encryptKey 来自 getUserEncryptKey 接口返回的 encrypt_key 字段（Base64 编码）
   * iv 来自 getUserEncryptKey 接口返回的 iv 字段（Hex 编码）
   * </pre>
   *
   * @param encryptKey    用户加密 key（Base64 编码）
   * @param hexIv         加密 iv（Hex 编码）
   * @param encryptedData 加密数据（Base64 编码）
   * @return 解密后的字符串
   */
  public static String decryptWithEncryptKey(String encryptKey, String hexIv, String encryptedData) {
    try {
      byte[] keyBytes = Base64.decodeBase64(encryptKey);
      byte[] ivBytes = hexToBytes(hexIv);
      byte[] dataBytes = Base64.decodeBase64(encryptedData);

      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      cipher.init(Cipher.DECRYPT_MODE,
        new SecretKeySpec(keyBytes, "AES"),
        new IvParameterSpec(ivBytes));
      return new String(cipher.doFinal(dataBytes), UTF_8);
    } catch (Exception e) {
      throw new WxRuntimeException("AES解密失败！", e);
    }
  }

  /**
   * 使用用户加密 key 对数据进行 AES-128-CBC 加密（用于小程序加密网络通道）.
   *
   * <pre>
   * 参考文档：https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/user-encryptkey.html
   * encryptKey 来自 getUserEncryptKey 接口返回的 encrypt_key 字段（Base64 编码）
   * iv 来自 getUserEncryptKey 接口返回的 iv 字段（Hex 编码）
   * </pre>
   *
   * @param encryptKey 用户加密 key（Base64 编码）
   * @param hexIv      加密 iv（Hex 编码）
   * @param data       待加密的明文字符串
   * @return 加密后的数据（Base64 编码）
   */
  public static String encryptWithEncryptKey(String encryptKey, String hexIv, String data) {
    try {
      byte[] keyBytes = Base64.decodeBase64(encryptKey);
      byte[] ivBytes = hexToBytes(hexIv);

      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      cipher.init(Cipher.ENCRYPT_MODE,
        new SecretKeySpec(keyBytes, "AES"),
        new IvParameterSpec(ivBytes));
      return Base64.encodeBase64String(cipher.doFinal(data.getBytes(UTF_8)));
    } catch (Exception e) {
      throw new WxRuntimeException("AES加密失败！", e);
    }
  }

  /**
   * 将 Hex 字符串转换为字节数组.
   */
  private static byte[] hexToBytes(String hex) {
    int len = hex.length();
    byte[] data = new byte[len / 2];
    for (int i = 0; i < len; i += 2) {
      data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
        + Character.digit(hex.charAt(i + 1), 16));
    }
    return data;
  }

}
