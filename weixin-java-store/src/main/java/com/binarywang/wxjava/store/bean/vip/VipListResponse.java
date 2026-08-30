package com.binarywang.wxjava.store.bean.vip;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

import java.util.List;

/**
 * @author : zhenyun.su
 * @since : 2023/10/8
 */

@Data
@NoArgsConstructor
public class VipListResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = -8127372979925053579L;
  @JsonProperty("list")
  private List<VipInfo> vipInfos;

  @JsonProperty("total_num")
  private Long totalNum;
}
