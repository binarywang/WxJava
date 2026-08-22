package me.chanjar.weixin.channel.bean.ewaybill;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 运单ID请求参数。
 *
 * @author GitHub Copilot
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WaybillIdParam implements Serializable {
  private static final long serialVersionUID = -7601452772833268240L;

  @JsonProperty("waybill_id")
  private String waybillId;
}
