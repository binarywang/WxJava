package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.StreamPageParam;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class OrderSearchParam extends StreamPageParam {

  private static final long serialVersionUID = 5737520097455135218L;
  /** 商品标题关键词 */
  @JsonProperty("search_condition")
  private OrderSearchCondition searchCondition;

  /** 不填该参数:全部订单 0:没有正在售后的订单, 1:正在售后单数量大于等于1的订单 */
  @JsonProperty("on_aftersale_order_exist")
  private Integer onAfterSaleOrderExist;

  /** 订单状态 {@link me.chanjar.weixin.channel.enums.WxOrderStatus} */
  @JsonProperty("status")
  private Integer status;
}
