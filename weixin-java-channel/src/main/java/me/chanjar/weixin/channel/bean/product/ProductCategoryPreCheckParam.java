package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 发品前校验请求参数.
 *
 * @author GitHub Copilot
 */
@Data
@NoArgsConstructor
public class ProductCategoryPreCheckParam implements Serializable {

  private static final long serialVersionUID = 5155253060483296766L;

  /** 叶子类目ID. */
  @JsonProperty("cat_id")
  private Long catId;
}
