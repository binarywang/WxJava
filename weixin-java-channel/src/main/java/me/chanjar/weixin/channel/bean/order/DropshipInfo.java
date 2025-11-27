package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 代发相关信息
 */
@Data
@NoArgsConstructor
public class DropshipInfo implements Serializable {

  private static final long serialVersionUID = -4562618835611282016L;

  /**
   * 代发单号
   */
  @JsonProperty("ds_order_id")
  private Long dsOrderId;

}
