package me.chanjar.weixin.channel.bean.warehouse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 仓库响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.warehouse.WarehouseResponse}。
 */
@Data
@NoArgsConstructor
@Deprecated
public class WarehouseResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 3206095869486573824L;
  /** 仓库库存 */
  @JsonProperty("data")
  private Warehouse data;
}
