package me.chanjar.weixin.channel.bean.window.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 获取橱窗商品详情
 * @author imyzt
 * @date 2024/01/27
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class GetWindowProductResponse extends WxChannelBaseResponse {

  /**
   * 橱窗商品详情
   */
  @JsonProperty("product")
  private String product;

  @Data
  public static class Product {
    /**
     * 橱窗商品ID
     */
    @JsonProperty("product_id")
    private String productId;

    /**
     * 商家侧外部商品ID
     */
    @JsonProperty("out_product_id")
    private String outProductId;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 商品头图url
     */
    @JsonProperty("img_url")
    private String imgUrl;

    /**
     * 商品所属三级类目ID
     */
    @JsonProperty("third_category_id")
    private String thirdCategoryId;

    /**
     * 商品状态
     * 1	已上架到橱窗
     * 2	未上架到橱窗
     * 3	已在商品来源处删除
     */
    private Integer status;

    /**
     * 价格区间最大值(单位分) (市场价，原价）
     */
    @JsonProperty("market_price")
    private Long marketPrice;

    /**
     * 价格区间最小值(单位分) (销售价)
     */
    @JsonProperty("selling_price")
    private Long sellingPrice;

    /**
     * 剩余库存
     */
    private Long stock;

    /**
     * 商品来源店铺的appid(非带货商品才拥有)
     */
    private String appid;

    /**
     * 商品详情页路径信息
     */
    @JsonProperty("page_path")
    private PagePath pagePath;

    /**
     * 商品所属电商平台ID
     */
    @JsonProperty("platform_id")
    private Long platformId;

    /**
     * 商品所属电商平台名
     */
    @JsonProperty("platform_name")
    private String platformName;

    /**
     * 是否在个人橱窗页隐藏
     */
    @JsonProperty("is_hide_for_window")
    private Boolean isHideForWindow;

    /**
     * 商品是否处于禁止售卖的状态
     */
    private Boolean banned;

    /**
     * 禁售原因及申请相关信息
     */
    @JsonProperty("banned_details")
    private BannedDetails bannedDetails;

    /**
     * 分店信息
     */
    @JsonProperty("branch_info")
    private BranchInfo branchInfo;

    /**
     * 抢购活动信息
     */
    @JsonProperty("limit_discount_info")
    private LimitDiscountInfo limitDiscountInfo;
  }

  /**
   * 商品详情页路径信息
   */
  @Data
  public static class PagePath {
    /**
     * 商品详情半屏页、全屏页所属appid
     */
    private String appid;

    /**
     * 商品详情半屏页path
     */
    @JsonProperty("half_page_path")
    private String halfPagePath;

    /**
     * 商品详情全屏页path
     */
    @JsonProperty("full_page_path")
    private String fullPagePath;
  }

  /**
   * 商品禁售原因及申请相关信息
   */
  @Data
  public static class BannedDetails {
    /**
     * 禁售原因
     * 0	三级类目在橱窗禁售 或 商品在来源处被禁售
     * 1	商品属于可申请售卖的类目，但商家未完成申请
     * 2	商品所属分店未处于营业状态
     */
    private Integer reason;

    /**
     * 需要申请的类目ID
     */
    @JsonProperty("need_apply_category_id")
    private String needApplyCategoryId;

    /**
     * 需要申请的类目名
     */
    @JsonProperty("need_apply_category_name")
    private String needApplyCategoryName;
  }

  /**
   * 分店信息
   */
  @Data
  public static class BranchInfo {
    /**
     * 分店ID
     */
    @JsonProperty("branch_id")
    private Long branchId;

    /**
     * 分店名
     */
    @JsonProperty("branch_name")
    private String branchName;

    /**
     * 分店状态
     * 0	营业中
     * 1	停业
     */
    @JsonProperty("branch_status")
    private Integer branchStatus;
  }

  /**
   * 抢购活动信息
   */
  @Data
  public static class LimitDiscountInfo {
    /**
     * 是否有生效中的抢购活动
     */
    @JsonProperty("is_effect")
    private Boolean isEffect;

    /**
     * 抢购价
     */
    @JsonProperty("discount_price")
    private Long discountPrice;

    /**
     * 抢购活动结束时间(毫秒时间戳)
     */
    @JsonProperty("end_time_ms")
    private String endTimeMs;

    /**
     * 抢购剩余库存
     */
    private Long stock;
  }
}
