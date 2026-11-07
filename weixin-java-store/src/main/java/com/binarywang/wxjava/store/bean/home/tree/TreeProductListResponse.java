package com.binarywang.wxjava.store.bean.home.tree;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 资金流水响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TreeProductListResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = 4566848209585635054L;

  /** 结果 */
  @JsonProperty("resp")
  private TreeProductListResult resp;
}
