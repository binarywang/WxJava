package me.chanjar.weixin.channel.bean.cooperation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 合作账号状态响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.cooperation.CooperationStatusResponse}。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class CooperationStatusResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 6998637882644598826L;

  /** 合作账号状态 */
  @JsonProperty("data")
  private CooperationStatus data;
}
