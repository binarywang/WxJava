package com.binarywang.wxjava.store.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 批量查询sku库存响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SkuStockBatchResponse extends WxStoreBaseResponse {

    private static final long serialVersionUID = 7745444061881828137L;

    /** 库存信息 */
    @JsonProperty("data")
    private SkuStockBatchList data;
  }
