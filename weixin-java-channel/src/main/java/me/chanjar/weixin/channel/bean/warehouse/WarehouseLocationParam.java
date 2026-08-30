package me.chanjar.weixin.channel.bean.warehouse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@JsonInclude(Include.NON_NULL)
@Deprecated
public class WarehouseLocationParam extends WarehouseLocation {

  private static final long serialVersionUID = 3347484433136057123L;

  public WarehouseLocationParam() {
  }

  public WarehouseLocationParam(Integer addressId1, Integer addressId2, Integer addressId3, Integer addressId4) {
    super(addressId1, addressId2, addressId3, addressId4);
  }
}
