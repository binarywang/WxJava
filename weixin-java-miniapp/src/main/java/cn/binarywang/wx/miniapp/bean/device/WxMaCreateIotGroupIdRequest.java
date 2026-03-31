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
 * @Description: 创建设备组 请求参数
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaCreateIotGroupIdRequest implements Serializable {
  private static final long serialVersionUID = 1827809470414413390L;
  /**
   * 设备型号id。通过注册设备获得（必填）
   */
  @SerializedName("model_id")
  private String modelId;
  /**
   * 设备组的名称（创建时决定，无法修改）
   */
  @SerializedName("group_name")
  private String groupName;


  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}
