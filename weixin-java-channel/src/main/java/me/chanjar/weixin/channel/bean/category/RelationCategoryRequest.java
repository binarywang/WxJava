package me.chanjar.weixin.channel.bean.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 类目权限列表请求参数
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.category.RelationCategoryRequest}。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Deprecated
public class RelationCategoryRequest implements Serializable {

  private static final long serialVersionUID = -8765432109876543210L;

  /** 是否按状态筛选 */
  @JsonProperty("is_filter_status")
  private Boolean isFilterStatus;

  /** 类目状态(当 isFilterStatus 为 true 时有效) */
  @JsonProperty("status")
  private Integer status;
}
