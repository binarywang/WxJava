package com.binarywang.wxjava.store.bean.warehouse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 仓库响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class WarehouseResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = 3206095869486573824L;
  /** 仓库库存 */
  @JsonProperty("data")
  private Warehouse data;
}
