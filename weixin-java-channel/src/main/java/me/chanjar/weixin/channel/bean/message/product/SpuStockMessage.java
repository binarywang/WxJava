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
 * SPU库存不足消息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JacksonXmlRootElement(localName = "xml")
public class SpuStockMessage extends WxChannelMessage {

  private static final long serialVersionUID = 2250860804161527363L;

  /** 商品id */
  @JsonProperty("product_id")
  @JacksonXmlProperty(localName = "product_id")
  private String productId;

  /** 平台商品id */
  @JsonProperty("sku_id")
  @JacksonXmlProperty(localName = "sku_id")
  private String skuId;

  /** 剩余库存：当前实时库存数量 */
  @JsonProperty("remaining_stock_amount")
  @JacksonXmlProperty(localName = "remaining_stock_amount")
  private Long remainingStockAmount;

  /** 未发放的预存code数【该字段对code_source_type=2的团购优惠生效，其他类型该字段值为0】 */
  @JsonProperty("remaining_code_amount")
  @JacksonXmlProperty(localName = "remaining_code_amount")
  private Long remainingCodeAmount;

  /** ChannelsEcStockNoEnough */
  @JsonProperty("channels_ec_stock_no_enough")
  @JacksonXmlProperty(localName = "channels_ec_stock_no_enough")
  private void stockNoEnough(Map<String, Object> map) {
    this.unpackNameFromNestedObject(map);
  }

  /**
   * 从嵌套对象中解析字段
   *
   * @param map 嵌套对象
   */
  protected void unpackNameFromNestedObject(Map<String, Object> map) {
    if (map == null) {
      return;
    }
    Object obj = null;
    obj = map.get("product_id");
    if (obj != null) {
      this.productId = (obj instanceof String ? (String) obj : String.valueOf(obj));
    }
    obj = map.get("sku_id");
    if (obj != null) {
      this.skuId = (obj instanceof String ? (String) obj : String.valueOf(obj));
    }

    obj = map.get("remaining_stock_amount");
    if (obj != null) {
      if (obj instanceof Number) {
        this.remainingStockAmount = ((Number) obj).longValue();
      } else if (obj instanceof String) {
        this.remainingStockAmount = Long.parseLong((String) obj);
      }
    }
    obj = map.get("remaining_code_amount");
    if (obj != null) {
      if (obj instanceof Number) {
        this.remainingCodeAmount = ((Number) obj).longValue();
      } else if (obj instanceof String) {
        this.remainingCodeAmount = Long.parseLong((String) obj);
      }
    }
  }
}
