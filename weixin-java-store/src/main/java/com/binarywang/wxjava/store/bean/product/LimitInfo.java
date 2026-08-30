package com.binarywang.wxjava.store.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 限时购信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LimitInfo implements Serializable {

  private static final long serialVersionUID = -4670198322237114719L;

  /** 限购周期类型，0无限购（默认），1按自然日限购，2按自然周限购，3按自然月限购 */
  @JsonProperty("period_type")
  private Integer periodType;

  /** 限购周期类型，0无限购（默认），1按自然日限购，2按自然周限购，3按自然月限购 */
  @JsonProperty("limited_buy_num")
  private Integer num;
}
