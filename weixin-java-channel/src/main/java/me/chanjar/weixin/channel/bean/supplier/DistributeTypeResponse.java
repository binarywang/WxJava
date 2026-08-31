package me.chanjar.weixin.channel.bean.supplier;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 分配方式响应。
 *
 * @author <a href="https://github.com/github-copilot">GitHub Copilot</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.supplier.DistributeTypeResponse}。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class DistributeTypeResponse extends WxChannelBaseResponse {
  private static final long serialVersionUID = -750860556286328053L;

  @JsonProperty("distribute_type")
  private Integer distributeType;

  @JsonProperty("supplier_info")
  private SupplierInfo supplierInfo;
}
