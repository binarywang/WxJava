package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 售后单同意信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.after.AfterSaleAcceptParam}。
 */
@Data
@JsonInclude(Include.NON_NULL)
@Deprecated
public class AfterSaleAcceptParam extends AfterSaleIdParam {

  private static final long serialVersionUID = -4352801757159074950L;
  /** 同意退货时传入地址id */
  @JsonProperty("address_id")
  private String addressId;

  /** 针对退货退款同意售后的阶段: 1. 同意退货退款，并通知用户退货; 2. 确认收到货并退款给用户。 如果不填则将根据当前的售后单状态自动选择相应操作。对于仅退款的情况，由于只存在一种同意的场景，无需填写此字段。*/
  @JsonProperty("accept_type")
  private Integer acceptType;

  public AfterSaleAcceptParam() {
  }

  public AfterSaleAcceptParam(String afterSaleOrderId, String addressId) {
    super(afterSaleOrderId);
    this.addressId = addressId;
  }

  public AfterSaleAcceptParam(String afterSaleOrderId, String addressId, Integer acceptType) {
    super(afterSaleOrderId);
    this.addressId = addressId;
    this.acceptType = acceptType;
  }
}
