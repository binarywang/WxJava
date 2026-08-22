package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 保障单号参数。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class GuaranteeOrderIdParam implements Serializable {

  private static final long serialVersionUID = -6638498743123537413L;

  /** 保障单号。 */
  @JsonProperty("guarantee_order_id")
  private String guaranteeOrderId;
}
