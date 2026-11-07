package com.binarywang.wxjava.store.bean.sharer;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 分享员解绑响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SharerUnbindResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = -2395560383862569445L;
  /** 成功列表 */
  @JsonProperty("success_openid")
  private List<String> successList;

  /** 失败列表，可重试 */
  @JsonProperty("fail_openid")
  private List<String> failList;

  /** 拒绝列表，不可重试（openid错误，未到解绑时间等） */
  @JsonProperty("refuse_openid")
  private List<String> refuseList;
}
