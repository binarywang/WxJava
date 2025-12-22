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
 * @Description: 设备组信息 响应参数
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaIotGroupDeviceInfoResponse implements Serializable {

  private static final long serialVersionUID = 6615660801230308048L;
  /**
   * 设备组名称
   */
  @SerializedName("group_name")
  private String groupName;
  /**
   * 设备列表
   */
  @SerializedName("device_list")
  private List<WxMaDeviceTicketRequest> deviceList;

  /**
   * 设备型号id。通过注册设备获得（必填）
   */
  @SerializedName("model_id")
  private String modelId;
  /**
   * 门禁机
   */
  @SerializedName("model_type")
  private String modelType;
  /**
   * 组最大设备数量
   */
  @SerializedName("max_device_count")
  private String maxDeviceCount;

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}
