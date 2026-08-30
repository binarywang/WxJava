package com.binarywang.wxjava.store.bean.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 审核通过的分类和资质信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PassCategoryResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = -3674591447273025743L;

  /** 类目和资质信息列表 */
  @JsonProperty("list")
  private List<PassCategoryInfo> list;
}
