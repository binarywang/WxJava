package cn.binarywang.wx.miniapp.bean.face;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 查询用户人脸核身真实验证结果 请求实体
 * <p>
 * 文档地址：<a href="https://developers.weixin.qq.com/miniprogram/dev/server/API/face/api_queryverifyinfo">查询用户人脸核身真实验证结果</a>
 * </p>
 *
 * @author <a href="https://github.com/github-copilot">GitHub Copilot</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaFaceQueryVerifyInfoRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：人脸核身会话唯一标识
   * 是否必填：是
   * 描述：getVerifyId接口返回的人脸核身会话唯一标识
   * </pre>
   */
  @SerializedName("verify_id")
  private String verifyId;

  /**
   * <pre>
   * 字段名：业务方系统外部流水号
   * 是否必填：是
   * 描述：必须和getVerifyId接口传入的out_seq_no一致
   * </pre>
   */
  @SerializedName("out_seq_no")
  private String outSeqNo;

  /**
   * <pre>
   * 字段名：证件信息摘要
   * 是否必填：是
   * 描述：根据getVerifyId中传入的证件信息生成的信息摘要。
   *       计算方式：对cert_info中的cert_type、cert_name、cert_no字段内容进行标准base64编码，
   *       按顺序拼接：cert_type=xxx&amp;cert_name=xxx&amp;cert_no=xxx，再对拼接串进行SHA256输出十六进制小写结果
   * </pre>
   */
  @SerializedName("cert_hash")
  private String certHash;

  /**
   * <pre>
   * 字段名：用户身份标识
   * 是否必填：是
   * 描述：必须和getVerifyId接口传入的openid一致
   * </pre>
   */
  @SerializedName("openid")
  private String openid;

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }

  /**
   * 计算证件信息摘要（cert_hash）
   * <p>
   * 计算规则（参见官方文档）：
   * 1. 对 cert_type、cert_name、cert_no 字段内容进行标准 base64 编码（若含中文等 Unicode 字符，先进行 UTF-8 编码）
   * 2. 按顺序拼接各个字段：cert_type=xxx&amp;cert_name=xxx&amp;cert_no=xxx
   * 3. 对拼接串进行 SHA256 并输出十六进制小写结果
   * </p>
   *
   * @param certType 证件类型
   * @param certName 证件姓名
   * @param certNo   证件号码
   * @return cert_hash 十六进制小写字符串
   */
  public static String calcCertHash(String certType, String certName, String certNo) {
    String encodedType = Base64.getEncoder().encodeToString(certType.getBytes(StandardCharsets.UTF_8));
    String encodedName = Base64.getEncoder().encodeToString(certName.getBytes(StandardCharsets.UTF_8));
    String encodedNo = Base64.getEncoder().encodeToString(certNo.getBytes(StandardCharsets.UTF_8));
    String raw = "cert_type=" + encodedType + "&cert_name=" + encodedName + "&cert_no=" + encodedNo;
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] hashBytes = digest.digest(raw.getBytes(StandardCharsets.UTF_8));
      StringBuilder hex = new StringBuilder();
      for (byte b : hashBytes) {
        hex.append(String.format("%02x", b));
      }
      return hex.toString();
    } catch (NoSuchAlgorithmException e) {
      throw new IllegalStateException("SHA-256 algorithm not available", e);
    }
  }
}
