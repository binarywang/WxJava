package me.chanjar.weixin.channel.bean.talent;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 带货助手-获取佣金单列表 请求参数
 *
 * @author <a href="https://github.com/features/copilot">GitHub Copilot</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.talent.TalentOrderListParam}。
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Deprecated
public class TalentOrderListParam implements Serializable {

  private static final long serialVersionUID = -6218342185316399261L;

  /** 佣金单创建时间范围之开始时间【和更新时间范围二选一】 */
  @JsonProperty("create_time_gt")
  private Long createTimeGt;

  /** 佣金单创建时间范围之结束时间【和更新时间范围二选一】 */
  @JsonProperty("create_time_lt")
  private Long createTimeLt;

  /** 订单 ID 过滤 */
  @JsonProperty("order_id")
  private String orderId;

  /** 商品 id 过滤 */
  @JsonProperty("spu_id")
  private String spuId;

  /** 佣金单更新时间范围之开始时间【和创建时间范围二选一】 */
  @JsonProperty("update_time_gt")
  private Long updateTimeGt;

  /** 佣金单更新时间范围之结束时间【和创建时间范围二选一】 */
  @JsonProperty("update_time_lt")
  private Long updateTimeLt;

  /** 单页佣金单数（不超过10） */
  @JsonProperty("page_size")
  private Integer pageSize;

  /** 由上次请求返回，顺序翻页时需要传入, 会从上次返回的结果往后翻一页 */
  @JsonProperty("next_key")
  private String nextKey;
}
