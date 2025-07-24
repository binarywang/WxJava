package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分佣信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class OrderCommissionInfo implements Serializable {

  private static final long serialVersionUID = -3046852309683467272L;
  /** 商品skuid */
  @JsonProperty("sku_id")
  private String skuId;

  /** 分账方昵称 */
  @JsonProperty("nickname")
  private String nickname;

  /** 分账方类型，0：达人，1：团长 */
  @JsonProperty("type")
  private Integer type;

  /** 分账状态， 1：未结算，2：已结算 */
  @JsonProperty("status")
  private Integer status;

  /** 分账金额 */
  @JsonProperty("amount")
  private Integer amount;

  /** 达人视频号id */
  @JsonProperty("finder_id")
  private String finderId;

  /** 达人openfinderid */
  @JsonProperty("openfinderid")
  private String openFinderId;

  /** 新带货平台 id */
  @JsonProperty("talent_id")
  private String talentId;
}
