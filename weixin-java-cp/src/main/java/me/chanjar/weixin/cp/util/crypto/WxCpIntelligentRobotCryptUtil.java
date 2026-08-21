package me.chanjar.weixin.cp.util.crypto;

import com.google.gson.JsonObject;
import me.chanjar.weixin.common.util.crypto.SHA1;
import me.chanjar.weixin.common.util.crypto.WxCryptUtil;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

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
    return decryptContent(msgSignature, timestamp, nonce, encryptedContent);
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
