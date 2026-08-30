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
 * 品牌消息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JacksonXmlRootElement(localName = "xml")
@Deprecated
public class BrandMessage extends WxChannelMessage {

  private static final long serialVersionUID = -3773902704930003105L;
  /** 品牌库中的品牌编号 */
  @JsonProperty("brand_id")
  @JacksonXmlProperty(localName = "brand_id")
  private String brandId;

  /** 审核id */
  @JsonProperty("audit_id")
  @JacksonXmlProperty(localName = "audit_id")
  private String auditId;

  /** 审核状态, 1新增品牌 2更新品牌 3撤回品牌审核 4审核成功 5审核失败 6删除品牌 7品牌资质被系统撤销 */
  @JsonProperty("status")
  @JacksonXmlProperty(localName = "status")
  private Integer status;

  /** 相关信息 */
  @JsonProperty("reason")
  @JacksonXmlProperty(localName = "reason")
  private String reason;

  @JsonProperty("BrandEvent")
  @JacksonXmlProperty(localName = "BrandEvent")
  private void unpackNameFromNestedObject(Map<String, Object> map) {
    if (map == null) {
      return;
    }
    Object obj = null;
    obj = map.get("brand_id");
    if (obj != null) {
      this.brandId = (obj instanceof String ? (String) obj : String.valueOf(obj));
    }
    obj = map.get("audit_id");
    if (obj != null) {
      this.auditId = (obj instanceof String ? (String) obj : String.valueOf(obj));
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
