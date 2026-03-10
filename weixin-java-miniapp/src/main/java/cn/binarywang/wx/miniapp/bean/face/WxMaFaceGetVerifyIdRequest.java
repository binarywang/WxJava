package cn.binarywang.wx.miniapp.bean.face;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 获取用户人脸核身会话唯一标识 请求实体
 * <p>
 * 文档地址：<a href="https://developers.weixin.qq.com/miniprogram/dev/server/API/face/api_getverifyid">获取用户人脸核身会话唯一标识</a>
 * </p>
 *
 * @author <a href="https://github.com/github-copilot">GitHub Copilot</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaFaceGetVerifyIdRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：业务方系统内部流水号
   * 是否必填：是
   * 描述：要求5-32个字符内，只能包含数字、大小写字母和_-字符，且在同一个appid下唯一
   * </pre>
   */
  @SerializedName("out_seq_no")
  private String outSeqNo;

  /**
   * <pre>
   * 字段名：用户身份信息
   * 是否必填：是
   * 描述：证件信息对象
   * </pre>
   */
  @SerializedName("cert_info")
  private CertInfo certInfo;

  /**
   * <pre>
   * 字段名：用户身份标识
   * 是否必填：是
   * 描述：用户的openid
   * </pre>
   */
  @SerializedName("openid")
  private String openid;

  /**
   * 用户身份信息
   */
  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class CertInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * <pre>
     * 字段名：证件类型
     * 是否必填：是
     * 描述：证件类型，身份证填 IDENTITY_CARD
     * </pre>
     */
    @SerializedName("cert_type")
    private String certType;

    /**
     * <pre>
     * 字段名：证件姓名
     * 是否必填：是
     * 描述：证件上的姓名，UTF-8编码
     * </pre>
     */
    @SerializedName("cert_name")
    private String certName;

    /**
     * <pre>
     * 字段名：证件号码
     * 是否必填：是
     * 描述：证件号码
     * </pre>
     */
    @SerializedName("cert_no")
    private String certNo;
  }

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}
