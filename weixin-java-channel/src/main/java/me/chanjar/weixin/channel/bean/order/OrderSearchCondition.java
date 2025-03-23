package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单 搜索条件
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class OrderSearchCondition implements Serializable {

  private static final long serialVersionUID = 5492584333971883140L;
  /** 商品标题关键词 */
  @JsonProperty("title")
  private String title;

  /** 商品编码 */
  @JsonProperty("sku_code")
  private String skuCode;

  /** 收件人 */
  @JsonProperty("user_name")
  private String userName;

  /**
   * 收件人电话
   * @deprecated 当前字段已经废弃，请勿使用，如果原本填手机后四位，可正常使用，否则接口报错
   */
  @JsonProperty("tel_number")
  @Deprecated
  private String telNumber;

  /**
   * 收件人电话后四位
   */
  @JsonProperty("tel_number_last4")
  private String telNumberLast4;

  /** 选填，只搜一个订单时使用 */
  @JsonProperty("order_id")
  private String orderId;

  /** 商家备注 */
  @JsonProperty("merchant_notes")
  private String merchantNotes;

  /** 买家备注 */
  @JsonProperty("customer_notes")
  private String customerNotes;

  /** 申请修改地址审核中 */
  @JsonProperty("address_under_review")
  private Boolean addressUnderReview;
}
