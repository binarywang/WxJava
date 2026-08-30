package com.binarywang.wxjava.store.bean.sharer;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 分享员订单响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SharerOrderResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = 2807417719466178508L;
  /** 分享员订单 */
  @JsonProperty("order_list")
  private List<SharerOrder> list;
}
