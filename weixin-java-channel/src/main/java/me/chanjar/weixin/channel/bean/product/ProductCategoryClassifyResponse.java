package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/** 商品类目推荐响应. */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductCategoryClassifyResponse extends WxChannelBaseResponse {
  private static final long serialVersionUID = 8258747142248203374L;

  private List<CategoryInfo> categories;
  @JsonProperty("wrong_cat")
  private Boolean wrongCat;

  @Data
  public static class CategoryInfo {
    private List<CategoryLevel> cats;
  }

  @Data
  public static class CategoryLevel {
    @JsonProperty("cat_info")
    private Category catInfo;
    @JsonProperty("has_permission")
    private Boolean hasPermission;
  }

  @Data
  public static class Category {
    @JsonProperty("cat_id")
    private String catId;
    @JsonProperty("cat_name")
    private String catName;
    @JsonProperty("is_shop_no_audit")
    private Boolean shopNoAudit;
  }
}
