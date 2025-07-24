package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 获取订单详情参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderInfoParam implements Serializable {

  private static final long serialVersionUID = 42L;

  /** 订单ID */
  @JsonProperty("order_id")
  private String orderId;

  /**
   * 用于商家提前测试订单脱敏效果，如果传true，即对订单进行脱敏，后期会默认对所有订单脱敏
   */
  @JsonProperty("encode_sensitive_info")
  private Boolean encodeSensitiveInfo;
}
