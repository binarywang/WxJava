package me.chanjar.weixin.channel.bean.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class CategoryDetailResult extends WxChannelBaseResponse {

  private static final long serialVersionUID = 4657778764371047619L;

  @JsonProperty("info")
  private Info info;

  @JsonProperty("attr")
  private Attr attr;


  @Data
  @NoArgsConstructor
  public static class Info implements Serializable {

    /** 类目ID */
    @JsonProperty("cat_id")
    private String id;
    /** 类目名称 */
    @JsonProperty("name")
    private String name;
  }

  @Data
  @NoArgsConstructor
  public static class Attr implements Serializable {

    /** 是否支持虚拟发货 */
    @JsonProperty("shop_no_shipment")
    private Boolean shopNoShipment;

    /** 是否定向准入 */
    @JsonProperty("access_permit_required")
    private Boolean accessPermitRequired;

    /** 是否支持预售 */
    @JsonProperty("pre_sale")
    private Boolean preSale;

    /** 是否必须支持7天无理由退货 */
    @JsonProperty("seven_day_return")
    private Boolean sevenDayReturn;

    /** 定准类目的品牌ID */
    @JsonProperty("brand_list")
    private List<BrandInfo> brands;

    /** 类目关联的保证金，单位分 */
    @JsonProperty("deposit")
    private Long deposit;

    /** 产品属性 */
    @JsonProperty("product_attr_list")
    private List<ProductAttr> productAttrs;

    /** 销售属性 */
    @JsonProperty("sale_attr_list")
    private List<ProductAttr> saleAttrs;

    /** 佣金信息 */
    @JsonProperty("transactionfee_info")
    private FeeInfo feeInfo;

    /** 折扣规则 */
    @JsonProperty("coupon_rule")
    private CouponRule couponRule;

    /** 价格下限，单位分，商品售价不可低于此价格 */
    @JsonProperty("floor_price")
    private Long floorPrice;

    /** 收货时间选项 */
    @JsonProperty("confirm_receipt_days")
    private List<String> confirmReceiptDays;

    /** 是否品牌定向准入，即该类目一定要有品牌 */
    @JsonProperty("is_limit_brand")
    private Boolean limitBrand;

    /** 商品编辑要求 */
    @JsonProperty("product_requirement")
    private ProductRequirement productRequirement;

    /** 尺码表 */
    @JsonProperty("size_chart")
    private SizeChart sizeChart;

    /** 放心买必须打开坏损包赔 */
    @JsonProperty("is_confidence_require_bad_must_pay")
    private Boolean confidenceRequireBadMustPay;

    /** 资质信息 */
    @JsonProperty("product_qua_list")
    private List<QualificationInfo> productQuaList;
  }

  @Data
  @NoArgsConstructor
  public static class BrandInfo implements Serializable {

    /** 定准类目的品牌ID */
    @JsonProperty("brand_id")
    private String id;
  }

  @Data
  @NoArgsConstructor
  public static class ProductAttr implements Serializable {

    /** 类目必填项名称 */
    @JsonProperty("name")
    private String name;

    /** 属性类型，string为自定义，select_one为多选一，该参数短期保留，用于兼容。将来废弃，使用type_v2替代 */
    @JsonProperty("type")
    private String type;

    /**
     * 属性类型v2，共7种类型
     * string：文本
     * select_one：单选，选项列表在value中
     * select_many：多选，选项列表在value中
     * integer：整数，数字必须为整数
     * decimal4：小数（4 位精度），小数部分最多 4 位
     * integer_unit：整数 + 单位，单位的选项列表在value中
     * decimal4_unit：小数（4 位精度） + 单位，单位的选项列表在value中
     */
    @JsonProperty("type_v2")
    private String typeV2;

    /**
     * 可选项列表，当type为：select_one/select_many时，为选项列表
     * 当type为：integer_unit/decimal4_unit时，为单位的列表
     */
    @JsonProperty("value")
    private String value;

    /** 是否类目必填项 */
    @JsonProperty("is_required")
    private Boolean required;

    /** 输入提示，请填写提示语 */
    @JsonProperty("hint")
    private String hint;

    /** 允许添加选项，当type为select_one/select_many时，标识是否允许添加新选项（value中不存在的选项） */
    @JsonProperty("append_allowed")
    private Boolean appendAllowed;
  }

  @Data
  @NoArgsConstructor
  public static class FeeInfo implements Serializable {

    /** 类目实收的交易佣金比例，单位万分比 */
    @JsonProperty("basis_point")
    private Integer basisPoint;

    /** 类目原始佣金比例，单位万分比 */
    @JsonProperty("original_basis_point")
    private Integer originalBasisPoint;

    /** 佣金激励类型，0：无激励措施，1：新店佣金减免 */
    @JsonProperty("incentive_type")
    private Integer incentiveType;
  }

  @Data
  @NoArgsConstructor
  public static class CouponRule implements Serializable {

    /** 最高的折扣比例，百分比, 0表示无限制 */
    @JsonProperty("discount_ratio_limit")
    private Integer supportCoupon;

    /** 最高的折扣金额，单位分，0表示无限制 */
    @JsonProperty("discount_limit")
    private Integer couponType;
  }

  @Data
  @NoArgsConstructor
  public static class ProductRequirement implements Serializable {
    /** 商品标题的编辑要求 */
    @JsonProperty("product_title_requirement")
    private String productTitleRequirement;

    /** 商品主图的编辑要求 */
    @JsonProperty("product_img_requirement")
    private String productImgRequirement;

    /** 商品描述的编辑要求 */
    @JsonProperty("product_desc_requirement")
    private String productDescRequirement;
  }

  @Data
  @NoArgsConstructor
  public static class SizeChart implements Serializable {

    /** 是否支持尺码表 */
    @JsonProperty("is_support")
    private Boolean support;

    /** 尺码配置要求列表 */
    @JsonProperty("item_list")
    private List<SizeChartItem> itemList;
  }

  @Data
  @NoArgsConstructor
  public static class SizeChartItem implements Serializable {
    /** 尺码属性名称 */
    @JsonProperty("name")
    private String name;

    /** 尺码属性值的单位 */
    @JsonProperty("unit")
    private String unit;

    /** 尺码属性值的类型，1：字符型，2：整数型，3：小数型 */
    @JsonProperty("type")
    private String type;

    /** 尺码属性值的填写格式，1：单值填写，2：区间值填写，3：支持单值或区间值 */
    @JsonProperty("format")
    private String format;

    /** 尺码属性值的限制 */
    @JsonProperty("limit")
    private String limit;

    /** 是否必填 */
    @JsonProperty("is_required")
    private Boolean required;
  }

}




