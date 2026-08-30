package com.binarywang.wxjava.store.bean.talent;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 带货助手-获取达人橱窗商品详情 请求参数
 *
 * @author <a href="https://github.com/features/copilot">GitHub Copilot</a>
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TalentWindowProductDetailParam implements Serializable {

  private static final long serialVersionUID = 3849271605183749261L;

  /** 橱窗商品ID（可以从"获取达人橱窗商品列表"接口获取） */
  @JsonProperty("product_id")
  private String productId;
}
