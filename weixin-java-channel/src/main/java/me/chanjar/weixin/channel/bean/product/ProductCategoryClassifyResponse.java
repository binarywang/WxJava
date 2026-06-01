package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 商品类目推荐响应.
 *
 * @author GitHub Copilot
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductCategoryClassifyResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 8258747142248203374L;

  /** 推荐类目. */
  private List<CategoryInfo> categories;

  /** 是否类目错放. */
  @JsonProperty("wrong_cat")
  private Boolean wrongCat;

  @Data
  @NoArgsConstructor
  public static class CategoryInfo {
    /** 多级类目. */
    private List<CategoryLevel> cats;
  }

  @Data
  @NoArgsConstructor
  public static class CategoryLevel {
    @JsonProperty("cat_id")
    private Long catId;

    @JsonProperty("cat_name")
    private String catName;

    @JsonProperty("is_shop_no_audit")
    private Boolean shopNoAudit;
  }
}
