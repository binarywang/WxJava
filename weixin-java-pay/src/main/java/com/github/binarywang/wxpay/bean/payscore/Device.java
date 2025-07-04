package com.github.binarywang.wxpay.bean.payscore;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 设备信息
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Device  implements Serializable {

  private static final long serialVersionUID = -4510224826631515321L;


  /**
   * 服务开始的设备ID
   */
  @SerializedName("start_device_id")
  private String startDeviceId;

  /**
   * 服务结束的设备ID
   */
  @SerializedName("end_device_id")
  private String endDeviceId;

  /**
   * 物料编码
   */
  @SerializedName("materiel_no")
  private String materielNo;
}
