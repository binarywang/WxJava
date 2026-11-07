package com.binarywang.wxjava.store.bean.warehouse;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import lombok.Data;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 仓库库存响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
public class WarehouseStockResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = 1810645965041317763L;
  /** 仓库库存 */
  @JsonProperty("num")
  private Integer num;

  public WarehouseStockResponse() {
  }

  @JsonProperty("data")
  private void unpackNameFromNestedObject(Map<String, Object> map) {
    if (map == null) {
      return;
    }
    Object obj = map.get("num");
    if (obj != null) {
      this.num = (Integer) obj;
    }
  }
}
