package com.binarywang.wxjava.store.bean.message.supplier;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 团长商品变更信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class SupplierItemInfo implements Serializable {

  private static final long serialVersionUID = -1971161027976024360L;
  /** 商品变更类型，1：新增商品；2：更新商品 */
  @JsonProperty("event_type")
  @JacksonXmlProperty(localName = "event_type")
  private Integer eventType;

  /** 团长商品所属小店appid */
  @JsonProperty("appid")
  @JacksonXmlProperty(localName = "appid")
  private String appid;

  /** 商品id */
  @JsonProperty("product_id")
  @JacksonXmlProperty(localName = "product_id")
  private String productId;

  /** 商品版本号 */
  @JsonProperty("version")
  @JacksonXmlProperty(localName = "version")
  private String version;

  /** 商品更新字段，当event_type = 2时有值。commission_ratio、service_ratio、status、active_time分别表示佣金、服务费、商品状态和合作生效时间有变更 */
  @JsonProperty("update_fields")
  @JacksonXmlProperty(localName = "update_fields")
  private List<String> updateFields;
}
