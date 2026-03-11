package me.chanjar.weixin.channel.bean.compass.shop;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 电商概览数据
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class ShopOverall implements Serializable {

  private static final long serialVersionUID = 3304918097895132226L;

  /** 成交金额，单位分 */
  @JsonProperty("pay_gmv")
  private String payGmv;

  /** 成交人数 */
  @JsonProperty("pay_uv")
  private String payUv;

  /** 成交退款金额，单位分 */
  @JsonProperty("pay_refund_gmv")
  private String payRefundGmv;

  /** 成交订单数 */
  @JsonProperty("pay_order_cnt")
  private String payOrderCnt;

  /** 直播成交金额，单位分 */
  @JsonProperty("live_pay_gmv")
  private String livePayGmv;

  /** 短视频成交金额，单位分 */
  @JsonProperty("feed_pay_gmv")
  private String feedPayGmv;
}
