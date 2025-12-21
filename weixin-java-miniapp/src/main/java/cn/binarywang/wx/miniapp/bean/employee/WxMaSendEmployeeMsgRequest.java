package cn.binarywang.wx.miniapp.bean.employee;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 小程序推送用工消息请求实体
 * <p>
 * 文档地址：<a href="https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/laboruse/api_sendemployeerelationmsg.html">推送用工消息</a>
 * </p>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on 2025-12-19
 */
@Data
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class WxMaSendEmployeeMsgRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：用户openid
   * 是否必填：是
   * 描述：需要接收消息的用户openid
   * </pre>
   */
  @SerializedName("openid")
  private String openid;

  /**
   * <pre>
   * 字段名：企业id
   * 是否必填：是
   * 描述：企业id，小程序管理员在微信开放平台配置
   * </pre>
   */
  @SerializedName("corp_id")
  private String corpId;

  /**
   * <pre>
   * 字段名：消息内容
   * 是否必填：是
   * 描述：推送的消息内容，文本格式，最长不超过200个字符
   * </pre>
   */
  @SerializedName("msg")
  private String msg;

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}
