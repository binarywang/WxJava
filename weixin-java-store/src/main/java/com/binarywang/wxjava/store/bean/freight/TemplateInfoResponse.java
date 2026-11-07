package com.binarywang.wxjava.store.bean.freight;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 运费模板 响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TemplateInfoResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = -8381510839783330617L;
  /** 运费模板id */
  @JsonProperty("freight_template")
  private FreightTemplate template;

}
