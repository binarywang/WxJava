package com.binarywang.wxjava.store.bean.brand;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 品牌响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BrandInfoResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = 2105745692451683517L;

  /** 品牌信息 */
  @JsonProperty("brand")
  private BrandInfo brand;
}
