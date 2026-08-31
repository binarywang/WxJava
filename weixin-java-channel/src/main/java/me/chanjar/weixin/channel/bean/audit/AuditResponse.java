package me.chanjar.weixin.channel.bean.audit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 审核结果响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.audit.AuditResponse}。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class AuditResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 9218713381520774914L;

  /** 审核结果 1：审核中，3：审核成功，2：审核拒绝，12：主动取消申请单 */
  @JsonProperty("data")
  private AuditResult data;
}
