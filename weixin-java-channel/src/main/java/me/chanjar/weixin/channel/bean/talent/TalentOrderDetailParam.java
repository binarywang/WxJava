package me.chanjar.weixin.channel.bean.talent;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 带货助手-获取佣金单详情 请求参数
 *
 * @author <a href="https://github.com/features/copilot">GitHub Copilot</a>
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Deprecated
public class TalentOrderDetailParam implements Serializable {

  private static final long serialVersionUID = 8741285036412736219L;

  /** 订单号，可从获取佣金单列表接口获得 */
  @JsonProperty("order_id")
  private String orderId;

  /** 商品skuid，可从获取佣金单列表接口获得 */
  @JsonProperty("sku_id")
  private String skuId;

  /** 订单额外参数【在订单列表里面返回的参数回传】 */
  @JsonProperty("special_id")
  private String specialId;
}
