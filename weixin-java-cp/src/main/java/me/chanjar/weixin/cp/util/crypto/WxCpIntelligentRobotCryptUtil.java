package me.chanjar.weixin.cp.util.crypto;

import com.google.gson.JsonObject;
import me.chanjar.weixin.common.util.crypto.SHA1;
import me.chanjar.weixin.common.util.crypto.WxCryptUtil;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;
import me.chanjar.weixin.common.error.WxRuntimeException;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.UUID;

/**
 * 企业微信智能机器人 API 模式消息加解密工具.
 *
 * <p>机器人 API 模式使用机器人后台配置的 Token、EncodingAESKey 和机器人 ID，
 * 与企业应用 access_token 无关。</p>
 */
public class WxCpIntelligentRobotCryptUtil extends WxCryptUtil {

  public WxCpIntelligentRobotCryptUtil(String token, String encodingAesKey, String aiBotId) {
    super(token, encodingAesKey, aiBotId);
  }

  /**
   * 解密机器人 API 模式的 JSON 回调消息.
   */
  public String decrypt(String msgSignature, String timestamp, String nonce, String encryptedContent) {
    String signature = SHA1.gen(this.token, timestamp, nonce, encryptedContent);
    if (!signature.equals(msgSignature)) {
      throw new WxRuntimeException("加密消息签名校验失败");
    }

    try {
      Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
      cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(this.aesKey, "AES"),
        new IvParameterSpec(Arrays.copyOfRange(this.aesKey, 0, 16)));
      byte[] bytes = me.chanjar.weixin.common.util.crypto.PKCS7Encoder.decode(
        cipher.doFinal(Base64.decodeBase64(encryptedContent)));
      if (bytes.length < 20) {
        throw new WxRuntimeException("解密后数据长度异常，可能为错误的密文或EncodingAESKey");
      }

      int plainTextLength = 0;
      for (int index = 16; index < 20; index++) {
        plainTextLength = (plainTextLength << 8) | (bytes[index] & 0xff);
      }
      int plainTextEnd = 20 + plainTextLength;
      if (plainTextLength < 0 || plainTextEnd > bytes.length) {
        throw new WxRuntimeException("解密后数据格式非法：消息长度不正确，可能为错误的密文或EncodingAESKey");
      }

      String receiverId = new String(Arrays.copyOfRange(bytes, plainTextEnd, bytes.length), StandardCharsets.UTF_8);
      if (!this.appidOrCorpid.equals(receiverId)) {
        throw new WxRuntimeException("智能机器人ID不正确，请核实！");
      }
      return new String(Arrays.copyOfRange(bytes, 20, plainTextEnd), StandardCharsets.UTF_8);
    } catch (WxRuntimeException e) {
      throw e;
    } catch (Exception e) {
      throw new WxRuntimeException(e);
    }
  }

  /**
   * 加密机器人 API 模式的 JSON 回复消息.
   */
  public String encrypt(String plainJson, String timestamp, String nonce) {
    String encryptedContent = encrypt(UUID.randomUUID().toString().replace("-", "").substring(0, 16), plainJson);
    JsonObject result = new JsonObject();
    result.addProperty("encrypt", encryptedContent);
    result.addProperty("msg_signature", SHA1.gen(this.token, timestamp, nonce, encryptedContent));
    result.addProperty("timestamp", timestamp);
    result.addProperty("nonce", nonce);
    return WxCpGsonBuilder.create().toJson(result);
  }

  /**
   * 解密 URL 校验请求中的 echostr.
   */
  public String verifyUrl(String msgSignature, String timestamp, String nonce, String echoStr) {
    return decrypt(msgSignature, timestamp, nonce, echoStr);
  }
}
