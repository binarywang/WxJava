package me.chanjar.weixin.channel.bean.ewaybill;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 批量运单ID请求参数。
 *
 * @author GitHub Copilot
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.ewaybill.WaybillIdsParam}。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Deprecated
public class WaybillIdsParam implements Serializable {
  private static final long serialVersionUID = -9030594599179993010L;

  @JsonProperty("waybill_ids")
  private List<String> waybillIds;
}
