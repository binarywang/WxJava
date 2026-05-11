package cn.binarywang.wx.miniapp.util.crypt;


import org.testng.annotations.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * <pre>
 *
 * Created by Binary Wang on 2018/12/25.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxMaCryptUtilsTest {
  // 模拟来自 getUserEncryptKey 接口返回的 encrypt_key（Base64，解码后 16 字节）
  // 和 iv（Hex，32 位十六进制字符，解码后 16 字节，AES-128-CBC 要求）
  private static final String ENCRYPT_KEY = "VI6BpyrK9XH4i4AIGe86tg==";
  private static final String HEX_IV = "6003f73ec441c3866003f73ec441c386";

  @Test
  public void testDecrypt() {
    String sessionKey = "7MG7jbTToVVRWRXVA885rg==";
    String encryptedData = "BY6VOgcWbwGcyrunK0ECWI8rnDsT69DucZ+M78tc1aL9aM/3bEAHFYd4fu7kRjWhD4YfjObw44T9vUqKyHIjbKs6hvtEasZZEIW35x4a91xVgN48ZqZ7MTQqUlP13kDUlkuwYh+/8g8yceu4kNbjowYrhihx+SV7CfjKCveJ7TSepr5Z7aLv1o+rfeelfOwn++WN/YoQsuZ6S3L4fWlWe5DAAUnFUI6cJvxxCohVzbrVXhyH2AqQdSjH2WnMYFeaGFIbcoxMznlk7oEwFn+hBj63dyT/swdYQfEdzuyCBmKXy8d6l1RKVX6Y65coTD8kIlbr+FKsqYrXVUIUBSwehqYuOdhYWZ9Bntl5DWU1oqzAPCnMn2cAIoQpQPKP7IGSxMOvCNAMhVXbE7BvnWuVuGF+AM5tXAa9IVUhcMImGwLQqm4iV5uBd+5OcFObh3A4VJk9iBCBWSkBHa/rV9CVoY0bFv2F9/2Hv82++Ybl274=";
    String ivStr = "TarMFjnzHVxy8pdS93wQbw==";
    System.out.println(WxMaCryptUtils.decrypt(sessionKey, encryptedData, ivStr));
//    System.out.println(WxMaCryptUtils.decryptAnotherWay(sessionKey, encryptedData, ivStr));
  }

  @Test
  public void testDecryptAnotherWay() {
    String encryptedData = "CiyLU1Aw2KjvrjMdj8YKliAjtP4gsMZMQmRzooG2xrDcvSnxIMXFufNstNGTyaGS9uT5geRa0W4oTOb1WT7fJlAC+oNPdbB+3hVbJSRgv+4lGOETKUQz6OYStslQ142dNCuabNPGBzlooOmB231qMM85d2/fV6ChevvXvQP8Hkue1poOFtnEtpyxVLW1zAo6/1Xx1COxFvrc2d7UL/lmHInNlxuacJXwu0fjpXfz/YqYzBIBzD6WUfTIF9GRHpOn/Hz7saL8xz+W//FRAUid1OksQaQx4CMs8LOddcQhULW4ucetDf96JcR3g0gfRK4PC7E/r7Z6xNrXd2UIeorGj5Ef7b1pJAYB6Y5anaHqZ9J6nKEBvB4DnNLIVWSgARns/8wR2SiRS7MNACwTyrGvt9ts8p12PKFdlqYTopNHR1Vf7XjfhQlVsAJdNiKdYmYVoKlaRv85IfVunYzO0IKXsyl7JCUjCpoG20f0a04COwfneQAGGwd5oa+T8yO5hzuyDb/XcxxmK01EpqOyuxINew==";
    String ivStr = "r7BXXKkLb8qrSNn05n0qiA==";
    String sessionKey = "tiihtNczf5v6AKRyjwEUhQ==";

    assertThat(WxMaCryptUtils.decrypt(sessionKey, encryptedData, ivStr))
      .isEqualTo(WxMaCryptUtils.decryptAnotherWay(sessionKey, encryptedData, ivStr));
  }

  /**
   * 测试使用用户加密 key（来自小程序加密网络通道）进行加密和解密的对称性.
   * encrypt_key 为 Base64 编码的 16 字节 AES-128 密钥，iv 为 Hex 编码的 16 字节初始向量。
   */
  @Test
  public void testEncryptAndDecryptWithEncryptKey() {
    String plainText = "{\"userId\":\"12345\",\"amount\":100}";

    String encrypted = WxMaCryptUtils.encryptWithEncryptKey(ENCRYPT_KEY, HEX_IV, plainText);
    assertThat(encrypted).isNotNull().isNotEmpty();

    String decrypted = WxMaCryptUtils.decryptWithEncryptKey(ENCRYPT_KEY, HEX_IV, encrypted);
    assertThat(decrypted).isEqualTo(plainText);
  }

  /**
   * 测试加密网络通道的加解密对称性（不同明文）.
   */
  @Test
  public void testEncryptDecryptSymmetryWithEncryptKey() {
    String plainText = "hello miniprogram";

    String encrypted = WxMaCryptUtils.encryptWithEncryptKey(ENCRYPT_KEY, HEX_IV, plainText);
    String decrypted = WxMaCryptUtils.decryptWithEncryptKey(ENCRYPT_KEY, HEX_IV, encrypted);
    assertThat(decrypted).isEqualTo(plainText);
  }

  /**
   * 测试 hexIv 为奇数长度时，应抛出 IllegalArgumentException.
   */
  @Test
  public void testEncryptWithEncryptKeyInvalidHexIvOddLength() {
    assertThatThrownBy(() -> WxMaCryptUtils.encryptWithEncryptKey(ENCRYPT_KEY, "abc", "data"))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("长度必须为偶数");
  }

  /**
   * 测试 hexIv 包含非十六进制字符时，应抛出 IllegalArgumentException.
   */
  @Test
  public void testEncryptWithEncryptKeyInvalidHexIvNonHexChar() {
    // 32 位但含非法字符 'z'
    assertThatThrownBy(() -> WxMaCryptUtils.encryptWithEncryptKey(
      ENCRYPT_KEY, "6003f73ec441c3866003f73ec441z386", "data"))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("非法字符");
  }

  /**
   * 测试 hexIv 解码后不足 16 字节（如仅 16 位 hex = 8 字节）时，应抛出 IllegalArgumentException.
   */
  @Test
  public void testEncryptWithEncryptKeyShortHexIv() {
    // 16 位 hex = 8 字节，不满足 AES-CBC 要求的 16 字节
    assertThatThrownBy(() -> WxMaCryptUtils.encryptWithEncryptKey(
      ENCRYPT_KEY, "6003f73ec441c386", "data"))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("hexIv 解码后必须为 16 字节");
  }

  /**
   * 测试 encryptKey 解码后不足 16 字节时，应抛出 IllegalArgumentException.
   */
  @Test
  public void testEncryptWithEncryptKeyShortKey() {
    // Base64 编码的 8 字节 key（不符合 AES-128 要求）
    String shortKey = java.util.Base64.getEncoder().encodeToString(new byte[8]);
    assertThatThrownBy(() -> WxMaCryptUtils.encryptWithEncryptKey(shortKey, HEX_IV, "data"))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("encryptKey 解码后必须为 16 字节");
  }

  /**
   * 测试 decryptWithEncryptKey 使用非法 hexIv 时，应抛出 IllegalArgumentException.
   */
  @Test
  public void testDecryptWithEncryptKeyInvalidHexIv() {
    assertThatThrownBy(() -> WxMaCryptUtils.decryptWithEncryptKey(ENCRYPT_KEY, "abc", "data"))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("长度必须为偶数");
  }

  /**
   * 测试 decryptWithEncryptKey encryptKey 长度不合法时，应抛出 IllegalArgumentException.
   */
  @Test
  public void testDecryptWithEncryptKeyShortKey() {
    String shortKey = java.util.Base64.getEncoder().encodeToString(new byte[8]);
    assertThatThrownBy(() -> WxMaCryptUtils.decryptWithEncryptKey(shortKey, HEX_IV, "data"))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("encryptKey 解码后必须为 16 字节");
  }
}
