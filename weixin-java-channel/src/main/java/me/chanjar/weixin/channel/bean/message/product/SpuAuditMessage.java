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
 * SPU审核消息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JacksonXmlRootElement(localName = "xml")
@Deprecated
public class SpuAuditMessage extends WxChannelMessage {

  private static final long serialVersionUID = 1763291928383078102L;
  /** 商品id */
  @JsonProperty("product_id")
  @JacksonXmlProperty(localName = "product_id")
  private String productId;

  /**
   * 审核状态, 2:审核不通过；3:审核通过 商品状态, 5:上架；11:自主下架；13:系统下架
   */
  @JsonProperty("status")
  @JacksonXmlProperty(localName = "status")
  private Integer status;

  /** 审核/下架原因，非必填字段 */
  @JsonProperty("reason")
  @JacksonXmlProperty(localName = "reason")
  private String reason;



  @JsonProperty("ProductSpuAudit")
  @JacksonXmlProperty(localName = "ProductSpuAudit")
  public void ProductSpuAudit(Map<String, Object> map) {
    this.unpackNameFromNestedObject(map);
  }

  @JsonProperty("ProductSpuUpdate")
  @JacksonXmlProperty(localName = "ProductSpuUpdate")
  public void ProductSpuUpdate(Map<String, Object> map) {
    this.unpackNameFromNestedObject(map);
  }

  @JsonProperty("ProductSpuListing")
  @JacksonXmlProperty(localName = "ProductSpuListing")
  public void ProductSpuListing(Map<String, Object> map) {
    this.unpackNameFromNestedObject(map);
  }

  private void unpackNameFromNestedObject(Map<String, Object> map) {
    if (map == null) {
      return;
    }
    Object obj = null;
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
