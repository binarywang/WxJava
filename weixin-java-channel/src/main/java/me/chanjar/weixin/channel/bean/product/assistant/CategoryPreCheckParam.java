package me.chanjar.weixin.channel.bean.product.assistant;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 发品前校验参数。
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.product.assistant.CategoryPreCheckParam}。
 */
@Data
@NoArgsConstructor
@Deprecated
public class CategoryPreCheckParam implements Serializable {

  private static final long serialVersionUID = 3616569394767815856L;

  /** 叶子类目 ID，不传时只校验店铺相关条件。 */
  @JsonProperty("cat_id")
  private Long catId;
}
