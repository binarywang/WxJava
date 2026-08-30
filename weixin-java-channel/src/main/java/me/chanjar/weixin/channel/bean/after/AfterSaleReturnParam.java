package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import me.chanjar.weixin.channel.bean.base.AddressInfo;

/**
 * 退货信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@Deprecated
public class AfterSaleReturnParam implements Serializable {

  private static final long serialVersionUID = -1101993925465293521L;
  /** 微信侧售后单号 */
  @JsonProperty("aftersale_id")
  private Long afterSaleId;

  /** 外部售后单号，和aftersale_id二选一 */
  @JsonProperty("out_aftersale_id")
  private String outAfterSaleId;

  /** 商家收货地址 */
  @JsonProperty("address_info")
  private AddressInfo addressInfo;

  public AfterSaleReturnParam() {
  }

  public AfterSaleReturnParam(Long afterSaleId, String outAfterSaleId) {
    this.outAfterSaleId = outAfterSaleId;
    this.afterSaleId = afterSaleId;
  }
}
