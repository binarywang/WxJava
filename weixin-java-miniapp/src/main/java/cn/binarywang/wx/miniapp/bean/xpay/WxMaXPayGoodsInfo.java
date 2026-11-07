package cn.binarywang.wx.miniapp.bean.xpay;

import com.google.gson.annotations.SerializedName;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Data;
import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;

import java.io.Serializable;

/**
 * xpay_goods_deliver_notify 推送中的道具信息。
 */
@Data
public class WxMaXPayGoodsInfo implements Serializable {
  private static final long serialVersionUID = 7495157056049312108L;

  @SerializedName("ProductId")
  @XStreamAlias("ProductId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String productId;

  @SerializedName("Quantity")
  @XStreamAlias("Quantity")
  private Integer quantity;
}
