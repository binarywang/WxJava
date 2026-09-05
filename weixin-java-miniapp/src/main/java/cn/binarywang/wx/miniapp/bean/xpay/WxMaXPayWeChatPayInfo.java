package cn.binarywang.wx.miniapp.bean.xpay;

import com.google.gson.annotations.SerializedName;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Data;
import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;

import java.io.Serializable;

/**
 * xpay_goods_deliver_notify 推送中的微信支付信息。
 */
@Data
public class WxMaXPayWeChatPayInfo implements Serializable {
  private static final long serialVersionUID = 7495157056049312108L;

  @SerializedName("MchOrderNo")
  @XStreamAlias("MchOrderNo")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String mchOrderNo;
}
