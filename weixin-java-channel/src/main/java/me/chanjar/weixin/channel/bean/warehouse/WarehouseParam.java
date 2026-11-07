package me.chanjar.weixin.channel.bean.warehouse;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 仓库
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.warehouse.WarehouseParam}。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Deprecated
public class WarehouseParam extends Warehouse {

  private static final long serialVersionUID = -3412047348380785225L;
}
