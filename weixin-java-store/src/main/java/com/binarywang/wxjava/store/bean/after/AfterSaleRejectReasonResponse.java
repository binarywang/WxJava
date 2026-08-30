package com.binarywang.wxjava.store.bean.after;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

import java.util.List;

/**
 * 售后原因
 *
 * @author lizhengwu
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class AfterSaleRejectReasonResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = -7946679037747710613L;

  /**
   * 售后原因列表
   */
  @JsonProperty("reject_reason_list")
  private List<AfterSaleRejectReason> rejectReasonList;

}
