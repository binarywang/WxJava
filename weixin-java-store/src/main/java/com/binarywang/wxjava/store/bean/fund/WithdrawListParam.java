package com.binarywang.wxjava.store.bean.fund;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 提现列表参数
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawListParam implements Serializable {

  private static final long serialVersionUID = -672422656564313999L;
  /** 页码，从1开始 */
  @JsonProperty("page_num")
  private Integer pageNum;

  /** 页数 */
  @JsonProperty("page_size")
  private Integer pageSize;

  /** 开始时间 */
  @JsonProperty("start_time")
  private Long startTime;

  /** 结束时间 */
  @JsonProperty("end_time")
  private Long endTime;

}
