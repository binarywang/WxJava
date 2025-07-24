package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.AttrInfo;

/**
 * Spu信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SpuInfo extends SpuSimpleInfo {

  private static final long serialVersionUID = -1183209029245287297L;

  /** 标题，字符类型，最少不低于3，最长不超过60。商品标题不得仅为数字、字母、字符或上述三种的组合 */
  @JsonProperty("title")
  private String title;

  /** 副标题，最多18字符 */
  @JsonProperty("sub_title")
  private String subTitle;

  /** 主图，多张，列表，图片类型，最多不超过9张 */
  @JsonProperty("head_imgs")
  private List<String> headImgs;

  /** 发货方式：0-快递发货；1-无需快递，手机号发货；3-无需快递，可选发货账号类型，默认为0，若为无需快递，则无需填写运费模版id */
  @JsonProperty("deliver_method")
  private Integer deliverMethod;

  /**
   * 发货账号：1-微信openid；2-QQ号；3-手机号；4-邮箱。
   * 可多选，只有deliver_method=3时，本参数有意义。
   * 且当发货账号为微信、QQ和邮箱时，需要更新订单接口读取详情字段，详情参考订单接口的说明
   */
  @JsonProperty("deliver_acct_type")
  private List<Integer> deliverAcctType;

  /** 商品详情 */
  @JsonProperty("desc_info")
  private DescriptionInfo descInfo;

  /** 商品类目，大小恒等于3（一二三级类目） */
  @JsonProperty("cats")
  private List<SpuCategory> cats;

  /** 新类目树，商家需要先申请可使用类目 */
  @JsonProperty("cats_v2")
  private List<SpuCategory> catsV2;

  /** 商品参数 */
  @JsonProperty("attrs")
  private List<AttrInfo> attrs;

  /** 商品编码 */
  @JsonProperty("spu_code")
  private String spuCode;

  /** 品牌id，无品牌为2100000000 */
  @JsonProperty("brand_id")
  private String brandId;

  /** 商品资质图片（最多5张） */
  @JsonProperty("qualifications")
  private List<String> qualifications;

  /** 运费信息 */
  @JsonProperty("express_info")
  private ExpressInfo expressInfo;

  /** 售后说明 */
  @JsonProperty("aftersale_desc")
  private String afterSaleDesc;

  /** 限购信息 */
  @JsonProperty("limited_info")
  @JsonInclude(Include.NON_EMPTY)
  private LimitInfo limitInfo;

  /** 附加服务 */
  @JsonProperty("extra_service")
  private ExtraServiceInfo extraService;

  /** 商品线上状态 {@link me.chanjar.weixin.channel.enums.SpuStatus } */
  @JsonProperty("status")
  private Integer status;

  /** 商品草稿状态 */
  @JsonProperty("edit_status")
  private Integer editStatus;

  /** 最低价格 */
  @JsonProperty("min_price")
  private Integer minPrice;

  /** 创建时间 yyyy-MM-dd HH:mm:ss */
  @JsonProperty("create_time")
  private String createTime;

  /**
   * 商品草稿最近一次修改时间
   */
  @JsonProperty("edit_time")
  private Long editTime;

  /**
   * 商品类型。1: 小店普通自营商品；2: 福袋抽奖商品；3: 直播间闪电购商品。
   * 注意: 福袋抽奖、直播间闪电购类型的商品为只读数据，不支持编辑、上架操作，不支持用data_type=2的参数获取。
   */
  @JsonProperty("product_type")
  private Integer productType;

  /**
   * 商品的售后信息
   */
  @JsonProperty("after_sale_info")
  private AfterSaleInfo afterSaleInfo;

  /**
   * 当商品类型位福袋抽奖商品（即product_type==2）且该抽奖商品由橱窗的自营商品导入生成时有值，
   * 表示导入的来源商品id，其他场景下该字段无值或者值为0
   */
  @JsonProperty("src_product_id")
  private String srcProductId;

  /** 商品资质列表 */
  @JsonProperty("product_qua_infos")
  private List<ProductQuaInfo> productQuaInfos;

  /** 尺码表信息 */
  @JsonProperty("size_chart")
  private SpuSizeChart sizeChart;
}
