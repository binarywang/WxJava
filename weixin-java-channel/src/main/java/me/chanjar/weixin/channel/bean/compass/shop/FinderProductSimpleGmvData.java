package me.chanjar.weixin.channel.bean.compass.shop;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 带货达人商品GMV数据
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class FinderProductSimpleGmvData implements Serializable {
  private static final long serialVersionUID = -3740996985044711599L;

  /** 佣金率 */
  @JsonProperty("commission_ratio")
  private Double commissionRatio;

  /** 成交金额，单位分 */
  @JsonProperty("pay_gmv")
  private String payGmv;

}
