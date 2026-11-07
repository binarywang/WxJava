package me.chanjar.weixin.channel.bean.warehouse;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 仓库区域
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.warehouse.WarehouseLocation}。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Deprecated
public class WarehouseLocation implements Serializable {

  private static final long serialVersionUID = 1626579682640060352L;

  /** 省份地址编码 */
  @JsonProperty("address_id1")
  private Integer addressId1;

  /** 市地址编码 */
  @JsonProperty("address_id2")
  private Integer addressId2;

  /** 区地址编码 */
  @JsonProperty("address_id3")
  private Integer addressId3;

  /** 街道地址编码 */
  @JsonProperty("address_id4")
  private Integer addressId4;
}
