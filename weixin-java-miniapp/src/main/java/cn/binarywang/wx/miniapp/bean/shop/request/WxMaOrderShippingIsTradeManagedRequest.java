package cn.binarywang.wx.miniapp.bean.shop.request;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author xzh
 * created on  2023/5/17 17:01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaOrderShippingIsTradeManagedRequest implements Serializable {

  private static final long serialVersionUID = -5735132900385013330L;
  /**
   * 必填
   * 待查询小程序的 appid，非服务商调用时仅能查询本账号
   */
  @SerializedName("appid")
  private String appId;

}
