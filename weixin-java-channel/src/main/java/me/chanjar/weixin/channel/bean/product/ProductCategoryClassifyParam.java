package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品类目推荐请求参数.
 *
 * @author GitHub Copilot
 */
@Data
@NoArgsConstructor
public class ProductCategoryClassifyParam implements Serializable {

  private static final long serialVersionUID = 4665563979720739777L;

  /** 请求类型. */
  @JsonProperty("req_type")
  private Integer reqType;

  /** 商品标题. */
  private String title;

  /** 商品主图，至少一个. */
  @JsonProperty("head_imgs")
  private List<String> headImgs;

  /** 类目ID，请求类型为2时必填. */
  @JsonProperty("cat_id")
  private Long catId;
}
