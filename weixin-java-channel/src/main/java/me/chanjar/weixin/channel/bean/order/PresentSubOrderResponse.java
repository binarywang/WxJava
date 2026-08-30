package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 获取礼物单的子单列表 响应
 *
 * @author GitHub Copilot
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class PresentSubOrderResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 1L;

  /** 子单列表 */
  @JsonProperty("sub_order_ids")
  private List<String> subOrderIds;

}
