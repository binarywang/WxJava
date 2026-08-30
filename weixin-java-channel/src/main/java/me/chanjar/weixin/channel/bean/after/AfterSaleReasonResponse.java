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
 *
 * @author lizhengwu
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
@Deprecated
public class AfterSaleReasonResponse extends WxChannelBaseResponse {


  private static final long serialVersionUID = -580378623915041396L;

  @JsonProperty("reason_list")
  private List<AfterSaleReason> reasonList;

}
