package me.chanjar.weixin.channel.bean.compass.shop;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 带货达人数据
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class FinderGmvData implements Serializable {

  private static final long serialVersionUID = -7463331971169286175L;

  /** 成交金额，单位分 */
  @JsonProperty("pay_gmv")
  private String payGmv;

  /** 动销商品数 */
  @JsonProperty("pay_product_id_cnt")
  private String payProductIdCnt;

  /** 成交人数 */
  @JsonProperty("pay_uv")
  private String payUv;

  /** 退款金额，单位分 */
  @JsonProperty("refund_gmv")
  private String refundGmv;

  /** 成交退款金额，单位分 */
  @JsonProperty("pay_refund_gmv")
  private String payRefundGmv;

}
