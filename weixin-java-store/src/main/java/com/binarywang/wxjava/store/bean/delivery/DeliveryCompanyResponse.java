package com.binarywang.wxjava.store.bean.delivery;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 快递公司列表响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class DeliveryCompanyResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = -7695903997951385166L;
  /** 快递公司 */
  @JsonProperty("company_list")
  private List<DeliveryCompanyInfo> companyList;
}
