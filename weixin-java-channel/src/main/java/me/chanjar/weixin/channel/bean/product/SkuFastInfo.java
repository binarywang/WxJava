package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 免审商品更新Sku数据
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SkuFastInfo implements Serializable {

  /** sku_id */
  @JsonProperty("sku_id")
  private String skuId;

  /** 售卖价格，以分为单位，数字类型，最大不超过10000000（1000万元） */
  @JsonProperty("sale_price")
  private Integer salePrice;

  @JsonProperty("stock_info")
  private StockInfo stockInfo;

  /** sku发货信息 */
  @JsonProperty("sku_deliver_info")
  private SkuDeliverInfo skuDeliverInfo;

  /** 是否要删除当前sku */
  @JsonProperty("is_delete")
  private Boolean delete;


  @Data
  @NoArgsConstructor
  public static class StockInfo implements Serializable {

    /** 修改类型。1: 增加；2:减少；3:设置 */
    @JsonProperty("diff_type")
    protected Integer diffType;

    /** 增加、减少或者设置的库存值 */
    @JsonProperty("num")
    protected Integer num;
  }

}
