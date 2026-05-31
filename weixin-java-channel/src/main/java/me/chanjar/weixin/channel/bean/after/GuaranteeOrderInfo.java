package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 保障单信息.
 */
@Data
@NoArgsConstructor
public class GuaranteeOrderInfo implements Serializable {

  private static final long serialVersionUID = -2590210285444450666L;

  /** 保障单号. */
  @JsonProperty("guarantee_order_id")
  private String guaranteeOrderId;

  /** 订单号. */
  @JsonProperty("order_id")
  private String orderId;

  /** 保障单状态. */
  @JsonProperty("status")
  private Integer status;

  /** 保障单类型. */
  @JsonProperty("type")
  private Integer type;

  /** 申请原因. */
  @JsonProperty("reason")
  private String reason;

  /** 创建时间. */
  @JsonProperty("create_time")
  private Long createTime;

  /** 更新时间. */
  @JsonProperty("update_time")
  private Long updateTime;

  /** 买家openid. */
  @JsonProperty("openid")
  private String openid;

  /** 买家unionid. */
  @JsonProperty("unionid")
  private String unionid;

  /** 商品信息. */
  @JsonProperty("product_info")
  private AfterSaleProductInfo productInfo;
}
