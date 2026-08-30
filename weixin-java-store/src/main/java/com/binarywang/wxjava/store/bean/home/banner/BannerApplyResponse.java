package com.binarywang.wxjava.store.bean.home.banner;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 提交精选展位申请 结果
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BannerApplyResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = -2194587734444499201L;

  /** 申请编号 */
  @JsonProperty("apply_id")
  private Integer applyId;

}
