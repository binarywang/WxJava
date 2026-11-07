package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/** 商品类目推荐响应.
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.product.ProductCategoryClassifyResponse}。
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class ProductCategoryClassifyResponse extends WxChannelBaseResponse {
  private static final long serialVersionUID = 8258747142248203374L;

  private List<CategoryInfo> categories;
  @JsonProperty("wrong_cat")
  private Boolean wrongCat;

  @Data
  public static class CategoryInfo implements Serializable {
    private static final long serialVersionUID = -4800760946330901306L;

    private List<CategoryLevel> cats;
  }

  @Data
  public static class CategoryLevel implements Serializable {
    private static final long serialVersionUID = 8010801623725584755L;

    @JsonProperty("cat_info")
    private Category catInfo;
    @JsonProperty("has_permission")
    private Boolean hasPermission;
  }

  @Data
  public static class Category implements Serializable {
    private static final long serialVersionUID = -9013991576741902059L;

    @JsonProperty("cat_id")
    private String catId;
    @JsonProperty("cat_name")
    private String catName;
    @JsonProperty("is_shop_no_audit")
    private Boolean shopNoAudit;
  }
}
