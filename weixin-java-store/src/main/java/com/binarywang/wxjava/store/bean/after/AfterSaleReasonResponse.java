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
 *
 * @author lizhengwu
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class AfterSaleReasonResponse extends WxStoreBaseResponse {


  private static final long serialVersionUID = -580378623915041396L;

  @JsonProperty("reason_list")
  private List<AfterSaleReason> reasonList;

}
