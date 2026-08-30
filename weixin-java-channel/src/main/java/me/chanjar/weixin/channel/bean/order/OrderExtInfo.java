package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 订单备注信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@Deprecated
public class OrderExtInfo implements Serializable {

  private static final long serialVersionUID = 4568097877621455429L;
  /**
   * 用户备注
   */
  @JsonProperty("customer_notes")
  private String customerNotes;

  /**
   * 商家备注
   */
  @JsonProperty("merchant_notes")
  private String merchantNotes;

  /**
   * 确认收货时间，包括用户主动确认收货和超时自动确认收货
   */
  @JsonProperty("confirm_receipt_time")
  private Long confirmReceiptTime;

  /**
   * 视频号id
   */
  @JsonProperty("finder_id")
  private String finderId;

  /**
   * 直播id
   */
  @JsonProperty("live_id")
  private String liveId;

  /**
   * 下单场景，枚举值见 {@link me.chanjar.weixin.channel.enums.OrderScene}
   */
  @JsonProperty("order_scene")
  private Integer orderScene;
}
