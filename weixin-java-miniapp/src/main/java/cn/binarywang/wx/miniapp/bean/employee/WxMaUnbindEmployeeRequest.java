package cn.binarywang.wx.miniapp.bean.employee;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 小程序解绑用工关系请求实体
 * <p>
 * 文档地址：<a href="https://developers.weixin.qq.com/miniprogram/dev/server/API/laboruse/api_unbinduserb2cauthinfo.html">解绑用工关系</a>
 * </p>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on 2025-12-19
 * update on 2026-01-22 15:14:09
 */
@Data
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class WxMaUnbindEmployeeRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：用户openid列表
   * 是否必填：是
   * 描述：需要解绑的用户openid列表
   * </pre>
   */
  @SerializedName("openid_list")
  private List<String> openidList;

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}
