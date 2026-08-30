package com.binarywang.wxjava.store.bean.delivery;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 快递公司信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class DeliveryCompanyInfo implements Serializable {

  private static final long serialVersionUID = 4225666604513570564L;
  /** 快递公司id */
  @JsonProperty("delivery_id")
  private String id;

  /** 快递公司名称 */
  @JsonProperty("delivery_name")
  private String name;
}
