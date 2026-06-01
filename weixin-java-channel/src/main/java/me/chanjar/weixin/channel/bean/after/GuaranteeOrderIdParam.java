package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GuaranteeOrderIdParam implements Serializable {
  private static final long serialVersionUID = 4325797703077757139L;

  @JsonProperty("guarantee_order_id")
  private String guaranteeOrderId;
}
