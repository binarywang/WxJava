package com.binarywang.wxjava.store.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 创建买赠活动响应
 *
 * @author GitHub Copilot
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GiftActivityAddResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = -4527079816331082871L;

  @JsonProperty("activity_id")
  private String activityId;
}
