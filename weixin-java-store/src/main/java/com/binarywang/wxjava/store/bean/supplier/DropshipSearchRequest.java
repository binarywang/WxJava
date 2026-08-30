package com.binarywang.wxjava.store.bean.supplier;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 代发单搜索请求。
 *
 * @author <a href="https://github.com/github-copilot">GitHub Copilot</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DropshipSearchRequest extends DropshipListRequest {
  private static final long serialVersionUID = 3915264648809784742L;

  @JsonProperty("order_id")
  private String orderId;

  @JsonProperty("dropship_id")
  private String dropshipId;
}
