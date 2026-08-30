package com.binarywang.wxjava.store.bean.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 获取小店手机号认证状态 响应
 *
 * @author GitHub Copilot
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PrivateNumberGetPhoneResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = 1L;

  /** 手机号认证信息列表 */
  @JsonProperty("phone_list")
  private List<PrivateNumberPhoneInfo> phoneList;

}
