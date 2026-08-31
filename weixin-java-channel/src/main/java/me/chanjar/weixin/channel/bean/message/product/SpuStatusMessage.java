package me.chanjar.weixin.channel.bean.message.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.Map;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.message.WxChannelMessage;

/**
 * SPU状态消息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.message.product.SpuStatusMessage}。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JacksonXmlRootElement(localName = "xml")
@Deprecated
public class SpuStatusMessage extends WxChannelMessage {

  private static final long serialVersionUID = 6872830451279856492L;
  /** 商家自定义商品id */
  @JsonProperty("out_product_id")
  @JacksonXmlProperty(localName = "out_product_id")
  private String outProductId;

  /** 平台商品id */
  @JsonProperty("product_id")
  @JacksonXmlProperty(localName = "product_id")
  private String productId;

  /** 当前商品上下架状态 参考 {@link me.chanjar.weixin.channel.enums.SpuStatus } */
  @JsonProperty("status")
  @JacksonXmlProperty(localName = "status")
  private Integer status;

  /** 相关信息 */
  @JsonProperty("reason")
  @JacksonXmlProperty(localName = "reason")
  private String reason;

  @JsonProperty("OpenProductSpuStatusUpdate")
  @JacksonXmlProperty(localName = "OpenProductSpuStatusUpdate")
  private void unpackNameFromNestedObject(Map<String, Object> map) {
    if (map == null) {
      return;
    }
    Object obj = null;
    obj = map.get("out_product_id");
    if (obj != null) {
      this.outProductId = (obj instanceof String ? (String) obj : String.valueOf(obj));
    }
    obj = map.get("product_id");
    if (obj != null) {
      this.productId = (obj instanceof String ? (String) obj : String.valueOf(obj));
    }
    obj = map.get("status");
    if (obj != null) {
      if (obj instanceof Integer) {
        this.status = (Integer) obj;
      } else if (obj instanceof String) {
        this.status = Integer.parseInt((String) obj);
      }
    }
    obj = map.get("reason");
    if (obj != null) {
      this.reason = (obj instanceof String ? (String) obj : String.valueOf(obj));
    }
  }
}
