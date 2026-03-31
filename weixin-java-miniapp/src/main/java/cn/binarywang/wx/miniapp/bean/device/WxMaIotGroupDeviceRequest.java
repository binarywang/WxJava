package cn.binarywang.wx.miniapp.bean.device;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: yanglegetuo
 * @Date: 2025/12/22 8:51
 * @Description: 设备组 添加/移除 设备 请求参数
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaIotGroupDeviceRequest implements Serializable {
  private static final long serialVersionUID = -5648997758678588138L;

  /**
   * 设备组的唯一标识（必填）
   */
  @SerializedName("group_id")
  private String groupId;
  /**
   * 设备列表
   */
  @SerializedName("device_list")
  private List<WxMaDeviceTicketRequest> deviceList;
  /**
   * 是否强制更新设备列表，等于 true 时将已存在其它设备组中的设备移除并添加到当前设备组，慎用。
   */
  @SerializedName("force_add")
  private Boolean forceAdd;

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}
