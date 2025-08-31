package cn.binarywang.wx.miniapp.bean.complaint;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 小程序提交回复请求实体
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on 2025-01-01
 */
@Data
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class WxMaResponseRequest implements Serializable {
  private static final long serialVersionUID = 3244929701614280806L;

  /**
   * <pre>
   * 字段名：投诉单号
   * 是否必填：是
   * 描述：投诉单对应的投诉单号
   * </pre>
   */
  @SerializedName("complaint_id")
  private String complaintId;

  /**
   * <pre>
   * 字段名：回复内容
   * 是否必填：是
   * 描述：具体的回复内容，长度不超过200字符
   * </pre>
   */
  @SerializedName("response_content")
  private String responseContent;

  /**
   * <pre>
   * 字段名：回复图片
   * 是否必填：否
   * 描述：回复的图片凭证，最多可传5张图片，由图片上传接口返回
   * </pre>
   */
  @SerializedName("response_images")
  private List<String> responseImages;

  /**
   * <pre>
   * 字段名：跳转链接
   * 是否必填：否
   * 描述：点击跳转链接
   * </pre>
   */
  @SerializedName("jump_url")
  private String jumpUrl;

  /**
   * <pre>
   * 字段名：跳转链接文案
   * 是否必填：否
   * 描述：跳转链接文案，在response_content中展示的跳转链接文案，长度不超过10个字符
   * </pre>
   */
  @SerializedName("jump_url_text")
  private String jumpUrlText;

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}