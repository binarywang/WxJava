package com.binarywang.wxjava.store.bean.home.background;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 提交背景图申请 结果
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BackgroundApplyResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = -5627456997199822109L;

  /** 申请编号 */
  @JsonProperty("apply_id")
  private Integer applyId;

}
