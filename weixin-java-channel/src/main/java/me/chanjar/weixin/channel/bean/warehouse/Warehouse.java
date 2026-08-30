package me.chanjar.weixin.channel.bean.warehouse;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 仓库
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@Deprecated
public class Warehouse implements Serializable {

  private static final long serialVersionUID = -2322154583471063637L;

  /** 外部仓库ID，一个店铺下，同一个外部ID只能创建一个仓库，最大32字符 */
  @JsonProperty("out_warehouse_id")
  private String outWarehouseId;

  /** 仓库名称 */
  @JsonProperty("name")
  private String name;

  /** 仓库介绍 */
  @JsonProperty("intro")
  private String intro;

  /** 覆盖区域，可以在创建后添加 */
  @JsonProperty("cover_locations")
  private List<WarehouseLocation> coverLocations;

}
