package com.binarywang.wxjava.store.bean.window.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

import java.util.List;

/**
 * 获取账号收集的留资数据详情
 * @author imyzt
 * @date 2024/01/27
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GetWindowProductListResponse extends WxStoreBaseResponse {

  /**
   * 商品列表
   */
  private List<ProductInfo> products;

  /**
   * 本次翻页的上下文，用于顺序翻页请求
   */
  @JsonProperty("last_buffer")
  private String lastBuffer;

  /**
   * 商品总数
   */
  @JsonProperty("total_num")
  private int totalNum;

  /**
   * 商品信息类
   */
  @Data
  public static class ProductInfo {
    /**
     * 橱窗商品id
     */
    @JsonProperty("product_id")
    private String productId;

    /**
     * 商品来源店铺的appid
     */
    private String appid;
  }
}
