package me.chanjar.weixin.channel.bean.compass.shop;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 带货数据概览
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class FinderOverallData implements Serializable {

  private static final long serialVersionUID = -994852668593815907L;

  /** 成交金额，单位分 */
  @JsonProperty("pay_gmv")
  private String payGmv;

  /** 动销达人数 */
  @JsonProperty("pay_sales_finder_cnt")
  private String paySalesFinderCnt;

  /** 动销商品数 */
  @JsonProperty("pay_product_id_cnt")
  private String payProductIdCnt;

  /** 点击-成交转化率 */
  @JsonProperty("click_to_pay_uv_ratio")
  private Double clickToPayUvRatio;

}
