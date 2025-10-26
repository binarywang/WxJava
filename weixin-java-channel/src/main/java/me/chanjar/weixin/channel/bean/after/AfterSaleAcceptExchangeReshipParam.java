package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 售后单换货发货信息
 *
 * @author <a href="https://gitee.com/cchengg">Chu</a>
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AfterSaleAcceptExchangeReshipParam extends AfterSaleIdParam {
  private static final long serialVersionUID = -7946679037747710613L;

  /** 快递单号*/
  @JsonProperty("waybill_id")
  private String waybillId;

  /** 快递公司id，通过获取快递公司列表接口获得，非主流快递公司可以填OTHER*/
  @JsonProperty("delivery_id")
  private String deliveryId;

  public AfterSaleAcceptExchangeReshipParam() {

  }

  public AfterSaleAcceptExchangeReshipParam(String afterSaleOrderId, String waybillId, String deliveryId) {
    super(afterSaleOrderId);
    this.waybillId = waybillId;
    this.deliveryId = deliveryId;
  }

}
