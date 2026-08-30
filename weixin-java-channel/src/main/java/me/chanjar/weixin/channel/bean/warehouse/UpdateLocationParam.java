package me.chanjar.weixin.channel.bean.warehouse;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 仓库区域
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Deprecated
public class UpdateLocationParam implements Serializable {

  private static final long serialVersionUID = 6102771485047925091L;

  /** 外部仓库ID */
  @JsonProperty("out_warehouse_id")
  private String outWarehouseId;

  /** 覆盖区域 */
  @JsonProperty("cover_locations")
  private List<WarehouseLocation> coverLocations;
}
