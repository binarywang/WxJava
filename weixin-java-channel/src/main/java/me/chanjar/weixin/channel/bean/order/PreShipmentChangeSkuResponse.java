package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 获取待发货前更换SKU待处理请求 响应
 *
 * @author GitHub Copilot
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.order.PreShipmentChangeSkuResponse}。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class PreShipmentChangeSkuResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 1L;

  /** 更换SKU信息 */
  @JsonProperty("change_sku_info")
  private ChangeSkuInfo changeSkuInfo;

}
