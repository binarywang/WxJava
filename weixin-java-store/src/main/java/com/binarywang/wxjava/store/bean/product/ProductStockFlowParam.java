package com.binarywang.wxjava.store.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/** 获取库存流水请求参数. */
@Data
public class ProductStockFlowParam implements Serializable {
  private static final long serialVersionUID = -407227347279113050L;

  @JsonProperty("product_id")
  private String productId;
  @JsonProperty("sku_id")
  private String skuId;
  @JsonProperty("stock_type")
  private Integer stockType;
  @JsonProperty("finder_id")
  private String finderId;
  @JsonProperty("begin_time")
  private Long beginTime;
  @JsonProperty("end_time")
  private Long endTime;
  @JsonProperty("op_type_list")
  private List<Integer> opTypeList;
  @JsonProperty("page_size")
  private Integer pageSize;
  @JsonProperty("next_key")
  private String nextKey;
  @JsonProperty("stock_type_id")
  private String stockTypeId;
}
