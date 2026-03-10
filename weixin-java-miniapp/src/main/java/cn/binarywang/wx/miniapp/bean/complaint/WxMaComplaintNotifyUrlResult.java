package cn.binarywang.wx.miniapp.bean.complaint;

import cn.binarywang.wx.miniapp.bean.WxMaBaseResponse;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 小程序投诉通知回调URL结果
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on 2025-01-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxMaComplaintNotifyUrlResult extends WxMaBaseResponse {

  /**
   * <pre>
   * 字段名：通知地址
   * 是否必填：是
   * 描述：通知地址，仅支持https
   * </pre>
   */
  @SerializedName("url")
  private String url;

  /**
   * <pre>
   * 字段名：签名串
   * 是否必填：是
   * 描述：用于验证通知消息的签名串
   * </pre>
   */
  @SerializedName("signature")
  private String signature;
}