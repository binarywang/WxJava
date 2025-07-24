package cn.binarywang.wx.miniapp.bean.xpay;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @date 2025-07-23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaXPayResponseComplaintRequest implements Serializable {
  private static final long serialVersionUID = 7495157056049312108L;
  @SerializedName("env")
  private Integer env;
  @SerializedName("complaint_id")
  private String complaintId;
  @SerializedName("response_content")
  private String responseContent;
  @SerializedName("response_images")
  private List<String> responseImages;


  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}
