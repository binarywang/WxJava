package com.binarywang.wxjava.store.bean.shop;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 店铺口令 响应
 *
 * @author <a href="https://github.com/copilot">GitHub Copilot</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ShopTagLinkResponse extends WxStoreBaseResponse {

  /** 店铺微信口令 */
  @JsonProperty("shop_taglink")
  private String shopTaglink;
}
