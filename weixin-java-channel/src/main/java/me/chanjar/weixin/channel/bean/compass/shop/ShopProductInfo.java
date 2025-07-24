package me.chanjar.weixin.channel.bean.compass.shop;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 店铺带货商品数据
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class ShopProductInfo implements Serializable {

  private static final long serialVersionUID = 3376047696301017643L;

  /** 商品id */
  @JsonProperty("product_id")
  private String productId;

  /** 商品图 */
  @JsonProperty("head_img_url")
  private String headImgUrl;

  /** 商品标题 */
  @JsonProperty("title")
  private String title;

  /** 商品价格，单位分 */
  @JsonProperty("price")
  private String price;

  /** 商品一级类目 */
  @JsonProperty("first_category_id")
  private String firstCategoryId;

  /** 商品二级类目 */
  @JsonProperty("second_category_id")
  private String secondCategoryId;

  /** 商品三级类目 */
  @JsonProperty("third_category_id")
  private String thirdCategoryId;

  /** 商品罗盘数据 */
  @JsonProperty("data")
  private ShopProductCompassData data;

}
