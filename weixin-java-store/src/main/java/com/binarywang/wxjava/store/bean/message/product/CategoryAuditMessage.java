package com.binarywang.wxjava.store.bean.message.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.Map;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.message.WxStoreMessage;

/**
 * 类目审核消息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JacksonXmlRootElement(localName = "xml")
public class CategoryAuditMessage extends WxStoreMessage {

  private static final long serialVersionUID = 3192582751919917223L;
  /** 审核id */
  @JsonProperty("audit_id")
  @JacksonXmlProperty(localName = "audit_id")
  private String auditId;

  /** 审核状态, 1:审核中, 2:审核拒绝, 3:审核通过, 12:主动取消申请单 */
  @JsonProperty("status")
  @JacksonXmlProperty(localName = "status")
  private Integer status;

  /** 相关信息 */
  @JsonProperty("reason")
  @JacksonXmlProperty(localName = "reason")
  private String reason;

  @JsonProperty("ProductCategoryAudit")
  @JacksonXmlProperty(localName = "ProductCategoryAudit")
  private void unpackNameFromNestedObject(Map<String, Object> map) {
    if (map == null) {
      return;
    }
    Object obj = null;
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
