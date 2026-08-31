package me.chanjar.weixin.channel.bean.supplier;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 供货商信息响应。
 *
 * @author <a href="https://github.com/github-copilot">GitHub Copilot</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.supplier.SupplierInfoResponse}。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class SupplierInfoResponse extends WxChannelBaseResponse {
  private static final long serialVersionUID = -3071464065836573893L;

  @JsonProperty("supplier_info")
  private SupplierInfo supplierInfo;
}
