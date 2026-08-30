package me.chanjar.weixin.channel.bean.message.after;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 纠纷信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@Deprecated
public class ComplaintInfo implements Serializable {

  private static final long serialVersionUID = 3988395560953978239L;
  /** 纠纷单号 */
  @JsonProperty("complaint_id")
  @JacksonXmlProperty(localName = "complaint_id")
  private String complaintId;

  /** 小店售后单号 */
  @JsonProperty("after_sale_order_id")
  @JacksonXmlProperty(localName = "after_sale_order_id")
  private String afterSaleOrderId;

  /** 纠纷单状态 */
  @JsonProperty("status")
  @JacksonXmlProperty(localName = "status")
  private Integer status;
}
