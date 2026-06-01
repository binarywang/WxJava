package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 获取库存流水 请求参数
 *
 * @author <a href="https://github.com/features/copilot">GitHub Copilot</a>
 * @see <a href="https://developers.weixin.qq.com/doc/store/shop/API/channels-shop-product/stock/api_getstockflow.html">获取库存流水</a>
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockFlowParam implements Serializable {

  private static final long serialVersionUID = -1L;

  /** 内部商品ID */
  @JsonProperty("product_id")
  private Long productId;

  /** 内部 sku_id */
  @JsonProperty("sku_id")
  private Long skuId;

  /**
   * 库存类型。
   * 0: 普通/通用库存；1: 达人专属计划营销库存；2: B2C活动；
   * 3: 同城配送门店库存；4: 活动库存；5: 限时抢购2.0库存
   */
  @JsonProperty("stock_type")
  private Integer stockType;

  /** 达人的视频号finder_id，若 stock_type 为1则必填 */
  @JsonProperty("finder_id")
  private String finderId;

  /** 时间范围开始时间戳（秒级） */
  @JsonProperty("begin_time")
  private Long beginTime;

  /** 时间范围结束时间戳（秒级） */
  @JsonProperty("end_time")
  private Long endTime;

  /** 库存事件类型列表（可选过滤条件） */
  @JsonProperty("op_type_list")
  private List<Integer> opTypeList;

  /** 每页数量 */
  @JsonProperty("page_size")
  private Integer pageSize;

  /** 翻页上下文，不传默认获取第一页 */
  @JsonProperty("next_key")
  private String nextKey;

  /** 库存类型id，若 stock_type 不为0且不为1则必填 */
  @JsonProperty("stock_type_id")
  private String stockTypeId;
}
