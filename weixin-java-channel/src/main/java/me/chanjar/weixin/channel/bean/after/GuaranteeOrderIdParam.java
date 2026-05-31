package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 保障单id参数.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class GuaranteeOrderIdParam implements Serializable {

  private static final long serialVersionUID = 773665483877442194L;

  /** 保障单号. */
  @JsonProperty("guarantee_order_id")
  private String guaranteeOrderId;
}
