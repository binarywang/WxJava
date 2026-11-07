package com.binarywang.wxjava.store.bean.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 店铺的类目权限列表响应
 *
 * @author <a href="https://gitee.com/cchengg">chucheng</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RelationCategoryResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = -8473920857463918245L;

  @JsonProperty("list")
  private List<RelationCategoryItem> list;

}
