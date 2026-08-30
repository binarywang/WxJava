package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/** 商品类目推荐请求参数. */
@Data
@Deprecated
public class ProductCategoryClassifyParam implements Serializable {
  private static final long serialVersionUID = 4665563979720739777L;

  @JsonProperty("req_type")
  private Integer reqType;
  private String title;
  @JsonProperty("head_imgs")
  private List<String> headImgs;
  @JsonProperty("cat_id")
  private String catId;
}
