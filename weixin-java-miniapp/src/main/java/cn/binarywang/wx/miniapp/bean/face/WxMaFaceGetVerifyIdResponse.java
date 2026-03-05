package cn.binarywang.wx.miniapp.bean.face;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 获取用户人脸核身会话唯一标识 响应实体
 * <p>
 * 文档地址：<a href="https://developers.weixin.qq.com/miniprogram/dev/server/API/face/api_getverifyid">获取用户人脸核身会话唯一标识</a>
 * </p>
 *
 * @author <a href="https://github.com/github-copilot">GitHub Copilot</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxMaFaceGetVerifyIdResponse implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：错误码
   * 是否必填：是
   * 类型：number
   * 描述：0表示成功，其他值表示失败
   * </pre>
   */
  @SerializedName("errcode")
  private Integer errcode;

  /**
   * <pre>
   * 字段名：错误信息
   * 是否必填：是
   * 类型：string
   * 描述：错误信息描述
   * </pre>
   */
  @SerializedName("errmsg")
  private String errmsg;

  /**
   * <pre>
   * 字段名：人脸核身会话唯一标识
   * 是否必填：否
   * 类型：string
   * 描述：微信侧生成的人脸核身会话唯一标识，用于后续接口调用，长度不超过256字符
   * </pre>
   */
  @SerializedName("verify_id")
  private String verifyId;

  /**
   * <pre>
   * 字段名：有效期
   * 是否必填：否
   * 类型：number
   * 描述：verify_id有效期，过期后无法发起核身，默认值3600，单位：秒
   * </pre>
   */
  @SerializedName("expires_in")
  private Integer expiresIn;

  public static WxMaFaceGetVerifyIdResponse fromJson(String json) {
    return WxMaGsonBuilder.create().fromJson(json, WxMaFaceGetVerifyIdResponse.class);
  }
}
