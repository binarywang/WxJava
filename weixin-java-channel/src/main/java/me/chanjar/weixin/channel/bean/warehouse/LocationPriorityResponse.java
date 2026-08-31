package me.chanjar.weixin.channel.bean.warehouse;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 仓库优先级响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.warehouse.LocationPriorityResponse}。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class LocationPriorityResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -4037484169497319150L;

  /** 按照out_warehouse_id排序优先级从高到低 */
  @JsonProperty("priority_sort")
  private List<String> prioritySort;
}
