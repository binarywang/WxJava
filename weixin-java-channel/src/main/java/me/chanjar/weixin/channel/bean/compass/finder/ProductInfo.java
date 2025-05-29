package me.chanjar.weixin.channel.bean.compass.finder;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 带货商品数据
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@NoArgsConstructor
public class ProductInfo implements Serializable {

  private static final long serialVersionUID = - 3347940276601700091L;

  /**
   * 商品id
   */
  @JsonProperty("product_id")
  private String productId;

  /**
   * 商品头图
   */
  @JsonProperty("head_img_url")
  private String headImgUrl;

  /**
   * 商品标题
   */
  @JsonProperty("title")
  private String title;

  /**
   * 商品价格
   */
  @JsonProperty("price")
  private String price;

  /**
   * 1级类目
   */
  @JsonProperty("first_category_id")
  private String firstCategoryId;

  /**
   * 2级类目
   */
  @JsonProperty("second_category_id")
  private String secondCategoryId;

  /**
   * 3级类目
   */
  @JsonProperty("third_category_id")
  private String thirdCategoryId;

  /**
   * 商品罗盘数据
   */
  @JsonProperty("data")
  private ProductCompassData data;

}
