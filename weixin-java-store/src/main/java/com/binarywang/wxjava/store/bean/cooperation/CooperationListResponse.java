package com.binarywang.wxjava.store.bean.cooperation;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 合作账号列表响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CooperationListResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = 6998637882644598826L;

  /** 合作账号列表 */
  @JsonProperty("data_list")
  private List<CooperationData> dataList;
}
