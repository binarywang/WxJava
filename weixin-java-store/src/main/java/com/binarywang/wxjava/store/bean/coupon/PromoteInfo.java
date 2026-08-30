package com.binarywang.wxjava.store.bean.coupon;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 推广信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class PromoteInfo implements Serializable {

  private static final long serialVersionUID = -3030639750899957382L;
  /** 推广类型 {@link com.binarywang.wxjava.store.enums.PromoteType} */
  @JsonProperty("promote_type")
  private Integer promoteType;
}
