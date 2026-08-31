package me.chanjar.weixin.channel.bean.warehouse;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 仓库id列表响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.warehouse.WarehouseIdsResponse}。
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class WarehouseIdsResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 3974529583232187473L;

  /** 外部仓库ID列表 */
  @JsonProperty("out_warehouse_ids")
  private List<String> ids;

  /** 本次翻页的上下文，用于请求下一页，如果是空，则当前是最后一页 */
  @JsonProperty("next_key")
  private String nextKey;

  public WarehouseIdsResponse() {
  }

  @JsonProperty("data")
  private void unpackNameFromNestedObject(Map<String, Object> map) {
    if (map == null) {
      return;
    }
    Object obj = map.get("out_warehouse_ids");
    if (obj != null) {
      if (obj instanceof List) {
        this.ids = (List<String>) obj;
      }
    }
    obj = map.get("next_key");
    if (obj != null) {
      this.nextKey = (String) obj;
    }
  }
}
