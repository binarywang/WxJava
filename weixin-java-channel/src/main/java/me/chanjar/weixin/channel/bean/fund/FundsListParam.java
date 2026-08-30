package me.chanjar.weixin.channel.bean.fund;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 资金流水参数
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@Deprecated
public class FundsListParam implements Serializable {

  private static final long serialVersionUID = 2998955690332382229L;
  /** 页码，从1开始 */
  @JsonProperty("page")
  private Integer page;

  /** 页数，不填默认为10 */
  @JsonProperty("page_size")
  protected Integer pageSize;

  /** 流水产生的开始时间，uinx时间戳 */
  @JsonProperty("start_time")
  private Long startTime;

  /** 流水产生的结束时间，unix时间戳 */
  @JsonProperty("end_time")
  private Long endTime;

  /** 流水类型, 1 收入，2 支出 */
  @JsonProperty("flow_type")
  private Integer flowType;

  /** 关联支付单号 */
  @JsonProperty("transaction_id")
  private String transactionId;

  /**
   * 分页参数，翻页时写入上一页返回的next_key(page为上一页加一， 并且page_size与上一页相同的时候才生效)，page * page_size >= 10000时必填
   */
  @JsonProperty("next_key")
  private String nextKey;


}
