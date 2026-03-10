package cn.binarywang.wx.miniapp.bean.xpay;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 小游戏道具直购API请求.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaXPayPresentGoodsRequest implements Serializable {
  private static final long serialVersionUID = 7495157056049312109L;

  /**
   * 用户的openid.
   */
  @SerializedName("openid")
  private String openid;

  /**
   * 环境。0-正式环境；1-沙箱环境.
   */
  @SerializedName("env")
  private Integer env;

  /**
   * 商户订单号.
   */
  @SerializedName("order_id")
  private String orderId;

  /**
   * 设备类型。0-安卓；1-iOS.
   */
  @SerializedName("device_type")
  private Integer deviceType;

  /**
   * 道具id.
   */
  @SerializedName("goods_id")
  private String goodsId;

  /**
   * 道具数量.
   */
  @SerializedName("goods_number")
  private Integer goodsNumber;

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}
