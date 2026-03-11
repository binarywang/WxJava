package cn.binarywang.wx.miniapp.bean.face;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 查询用户人脸核身真实验证结果 响应实体
 * <p>
 * 文档地址：<a href="https://developers.weixin.qq.com/miniprogram/dev/server/API/face/api_queryverifyinfo">查询用户人脸核身真实验证结果</a>
 * </p>
 *
 * @author <a href="https://github.com/github-copilot">GitHub Copilot</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxMaFaceQueryVerifyInfoResponse implements Serializable {
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
   * 字段名：人脸核身验证结果
   * 是否必填：否
   * 类型：number
   * 描述：核身通过的判断条件：errcode=0 且 verify_ret=10000
   *       枚举值说明：
   *       10000 - 识别成功
   *       10001 - 参数错误
   *       10002 - 人脸特征检测失败
   *       10003 - 身份证号不匹配
   *       10004 - 比对人脸信息不匹配
   *       10005 - 正在检测中
   *       10006 - appid没有权限
   *       10300 - 未完成核身
   *       90001 - 设备不支持人脸检测
   *       90002 - 用户取消
   *       其他枚举值请参见官方文档
   * </pre>
   */
  @SerializedName("verify_ret")
  private Integer verifyRet;

  public static WxMaFaceQueryVerifyInfoResponse fromJson(String json) {
    return WxMaGsonBuilder.create().fromJson(json, WxMaFaceQueryVerifyInfoResponse.class);
  }
}
