package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 售后单 响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.after.AfterSaleInfoResponse}。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class AfterSaleInfoResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -752661975153491902L;
  /** 售后单 */
  @JsonProperty("after_sale_order")
  private AfterSaleInfo info;
}
