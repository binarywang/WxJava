package com.binarywang.wxjava.store.bean.freight;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.AddressInfo;

/**
 * 地址列表
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class AddressInfoList implements Serializable {

  private static final long serialVersionUID = 5923805297331862706L;
  /** 地址列表 */
  @JsonProperty("address_infos")
  private List<AddressInfo> addressInfos;
}
