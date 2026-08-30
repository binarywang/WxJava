package com.binarywang.wxjava.store.bean.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * 买赠活动信息
 *
 * @author GitHub Copilot
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GiftActivityInfo implements Serializable {

  private static final long serialVersionUID = 3970308144375119175L;

  @JsonProperty("activity_id")
  private String activityId;

  @JsonProperty("title")
  private String title;

  @JsonProperty("start_time")
  private Long startTime;

  @JsonProperty("end_time")
  private Long endTime;

  @JsonProperty("detail")
  private Detail detail;

  @Data
  public static class Detail implements Serializable {
    private static final long serialVersionUID = 1019081831733485084L;

    @JsonProperty("show_scene")
    private Integer showScene;

    @JsonProperty("receive_limit")
    private ReceiveLimit receiveLimit;

    @JsonProperty("main_products")
    private List<MainProduct> mainProducts;

    @JsonProperty("gift_set")
    private GiftSet giftSet;
  }

  @Data
  public static class ReceiveLimit implements Serializable {
    private static final long serialVersionUID = 3332293571373311829L;

    @JsonProperty("is_limited")
    private Boolean limited;

    @JsonProperty("limit_num")
    private Integer limitNum;
  }

  @Data
  public static class MainProduct implements Serializable {
    private static final long serialVersionUID = 6368866030784193437L;

    @JsonProperty("product_id")
    private String productId;
  }

  @Data
  public static class GiftSet implements Serializable {
    private static final long serialVersionUID = 8473755235926932739L;

    @JsonProperty("gift_items")
    private List<GiftItem> giftItems;

    @JsonProperty("gift_set_num")
    private Integer giftSetNum;
  }

  @Data
  public static class GiftItem implements Serializable {
    private static final long serialVersionUID = -4130391476834450014L;

    @JsonProperty("gift_id")
    private String giftId;

    @JsonProperty("give_num")
    private Integer giveNum;
  }
}
