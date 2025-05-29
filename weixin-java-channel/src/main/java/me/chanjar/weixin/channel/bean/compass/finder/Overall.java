package me.chanjar.weixin.channel.bean.compass.finder;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 电商概览数据
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@NoArgsConstructor
public class Overall implements Serializable {

  private static final long serialVersionUID = 2456038666608345011L;

  /**
   * 成交金额，单位分
   */
  @JsonProperty("pay_gmv")
  private String payGmv;

  /**
   * 直播成交金额，单位分
   */
  @JsonProperty("live_pay_gmv")
  private String livePayGmv;

  /**
   * 短视频成交金额，单位分
   */
  @JsonProperty("feed_pay_gmv")
  private String feedPayGmv;

  /**
   * 橱窗成交金额，单位分
   */
  @JsonProperty("window_pay_gmv")
  private String windowPayGmv;

  /**
   * 商品分享支付金额，单位分
   */
  @JsonProperty("product_pay_gmv")
  private String productPayGmv;

  /**
   * 其他渠道成交金额，单位分
   */
  @JsonProperty("other_pay_gmv")
  private String otherPayGmv;

}
