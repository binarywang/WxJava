package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 批量查询sku库存响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SkuStockBatchResponse extends WxChannelBaseResponse {

    private static final long serialVersionUID = 7745444061881828137L;

    /** 库存信息 */
    @JsonProperty("data")
    private SkuStockBatchList data;
  }
