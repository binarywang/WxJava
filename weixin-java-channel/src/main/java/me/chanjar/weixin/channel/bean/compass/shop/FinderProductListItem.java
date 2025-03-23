package me.chanjar.weixin.channel.bean.compass.shop;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 带货达人商品列表
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class FinderProductListItem implements Serializable {

  private static final long serialVersionUID = 1646092488200992026L;

  /** 商品id */
  @JsonProperty("product_id")
  private String productId;

  /** 商品头图 */
  @JsonProperty("head_img_url")
  private String headImgUrl;

  /** 商品标题 */
  @JsonProperty("title")
  private String title;

  /** 商品价格 */
  @JsonProperty("price")
  private String price;

  /** 商品1级类目 */
  @JsonProperty("first_category_id")
  private String firstCategoryId;

  /** 商品2级类目 */
  @JsonProperty("second_category_id")
  private String secondCategoryId;

  /** 商品3级类目 */
  @JsonProperty("third_category_id")
  private String thirdCategoryId;

  /** gmv */
  @JsonProperty("data")
  private GmvData data;


  @Data
  @NoArgsConstructor
  public static class GmvData implements Serializable {
    private static final long serialVersionUID = 1840494188469233735L;

    /** 佣金率 */
    @JsonProperty("commission_ratio")
    private Double commissionRatio;

    /** 成交金额，单位分 */
    @JsonProperty("pay_gmv")
    private String payGmv;
  }

}
