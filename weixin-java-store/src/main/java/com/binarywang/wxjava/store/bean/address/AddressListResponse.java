package com.binarywang.wxjava.store.bean.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 地址列表 响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AddressListResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = -3997164605170764105L;

  /** 地址详情 */
  @JsonProperty("address_id_list")
  private List<String> ids;

}
