package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

import java.util.List;

/**
 * 售后原因
 *
 * @author lizhengwu
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
@Deprecated
public class AfterSaleRejectReasonResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -7946679037747710613L;

  /**
   * 售后原因列表
   */
  @JsonProperty("reject_reason_list")
  private List<AfterSaleRejectReason> rejectReasonList;

}
