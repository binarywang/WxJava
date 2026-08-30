package com.binarywang.wxjava.store.bean.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 分类响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AccountCategoryResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = 3486089711447908477L;

  /** 类目列表 */
  @JsonProperty("data")
  private List<ShopCategory> categories;
}
