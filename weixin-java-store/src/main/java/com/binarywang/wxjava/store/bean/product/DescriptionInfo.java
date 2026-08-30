package com.binarywang.wxjava.store.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品详情
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class DescriptionInfo implements Serializable {

  private static final long serialVersionUID = 3402153796734747882L;

  /** 商品详情图文，字符类型，最长不超过2000 */
  @JsonProperty("desc")
  private String desc;

  /** 商品详情图片，图片类型，最多不超过50张 */
  @JsonProperty("imgs")
  private List<String> imgs;
}
