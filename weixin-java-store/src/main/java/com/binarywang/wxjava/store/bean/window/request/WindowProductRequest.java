package com.binarywang.wxjava.store.bean.window.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 橱窗商品
 * @author imyzt
 * @date 2024/01/27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WindowProductRequest {

  /**
   * 橱窗商品ID
   */
  @JsonProperty("product_id")
  private String productId;

  /**
   * 商品来源店铺的appid
   */
  @JsonProperty("appid")
  private String appid;
}
