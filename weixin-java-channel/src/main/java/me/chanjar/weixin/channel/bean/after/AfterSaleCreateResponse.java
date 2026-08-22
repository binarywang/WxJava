package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

@Data
@EqualsAndHashCode(callSuper = true)
public class AfterSaleCreateResponse extends WxChannelBaseResponse {
  private static final long serialVersionUID = 2680676438284658410L;

  @JsonProperty("after_sale_order_id")
  private String afterSaleOrderId;
}
