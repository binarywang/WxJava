package com.binarywang.wxjava.store.bean.fund;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 资金流水
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class FundsFlow implements Serializable {

  private static final long serialVersionUID = -2785498655066305510L;
  /** 流水id */
  @JsonProperty("flow_id")
  private String flowId;

  /** 资金类型,见 {@link com.binarywang.wxjava.store.enums.FundsType} */
  @JsonProperty("funds_type")
  private Integer fundsType;

  /** 流水类型, 1 收入，2 支出 */
  @JsonProperty("flow_type")
  private Integer flowType;

  /** 流水金额 */
  @JsonProperty("amount")
  private Integer amount;

  /** 余额 */
  @JsonProperty("balance")
  private Integer balance;

  /** 流水关联信息 */
  @JsonProperty("related_info_list")
  private List<FlowRelatedInfo> relatedInfos;

  /** 记账时间 */
  @JsonProperty("bookkeeping_time")
  private String bookkeepingTime;

  /** 备注 */
  @JsonProperty("remark")
  private String remark;

}
