package cn.binarywang.wx.miniapp.bean.xpay;

import cn.binarywang.wx.miniapp.bean.WxMaBaseResponse;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 小游戏道具直购API响应.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaXPayPresentGoodsResponse extends WxMaBaseResponse implements Serializable {
  private static final long serialVersionUID = 7495157056049312110L;

  /**
   * 商户订单号.
   */
  @SerializedName("order_id")
  private String orderId;

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}
