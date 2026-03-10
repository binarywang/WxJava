package cn.binarywang.wx.miniapp.bean.device;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: yanglegetuo
 * @Date: 2025/12/22 8:51
 * @Description: 查询设备组信息 请求参数
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaGetIotGroupInfoRequest implements Serializable {

  private static final long serialVersionUID = 4913375114243384968L;
  /**
   * 设备组的唯一标识（必填）
   */
  @SerializedName("group_id")
  private String groupId;

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}
