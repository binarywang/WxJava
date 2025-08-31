package cn.binarywang.wx.miniapp.bean.customservice;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 客服绑定结果信息，包括错误码、主体名称、企业ID和绑定时间戳。
 * <p>
 * 字段说明：
 * <ul>
 *   <li>errCode: 错误码</li>
 *   <li>entityName: 小程序主体名称，未绑定时不返回</li>
 *   <li>corpid: 企业ID，未绑定时不返回</li>
 *   <li>bindTime: 接受绑定时间戳（毫秒）</li>
 * </ul>
 * @author <a href="https://github.com/tryking123">tryking123</a>
 * @since 2025/8/18 17:40
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaCustomserviceResult  implements Serializable {
  private static final long serialVersionUID = 8854979405505241314L;

  @SerializedName("errcode")
  private Integer errCode;

  /**
   * 该小程序的主体名称，未绑定时不返回
   */
  @SerializedName("entityName")
  private String entityName;

  /**
   * 企业ID，未绑定时不返回
   */
  @SerializedName("corpid")
  private String corpid;

  /** 接受绑定时间戳，ms */
  @JsonProperty("bindTime")
  private Long bindTime;

  public static WxMaCustomserviceResult fromJson(String json) {
    return WxMaGsonBuilder.create().fromJson(json, WxMaCustomserviceResult.class);
  }
}
