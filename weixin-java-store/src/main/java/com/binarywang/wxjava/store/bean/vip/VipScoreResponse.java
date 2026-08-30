package com.binarywang.wxjava.store.bean.vip;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * @author : zhenyun.su
 * @since : 2023/10/8
 */

@Data
@NoArgsConstructor
public class VipScoreResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = -7252972818862693546L;
  @JsonProperty("info")
  private ScoreInfo scoreInfo;
}
