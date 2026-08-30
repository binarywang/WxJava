package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;

/** 发品前校验请求参数. */
@Data
@Deprecated
public class ProductCategoryPreCheckParam implements Serializable {
  private static final long serialVersionUID = 5155253060483296766L;

  @JsonProperty("cat_id")
  private Long catId;
}
