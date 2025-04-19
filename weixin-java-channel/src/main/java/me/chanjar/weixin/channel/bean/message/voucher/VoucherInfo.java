package me.chanjar.weixin.channel.bean.message.voucher;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class VoucherInfo implements Serializable {
  private static final long serialVersionUID = 6007964849358969438L;

  /** 券code */
  @JsonProperty("code")
  @JacksonXmlProperty(localName = "code")
  private String code;

  /** 劵码类型，1商户实时code 2户预存 3平台生成 */
  @JsonProperty("code_type")
  @JacksonXmlProperty(localName = "code_type")
  private Integer codeType;

  /** 券状态 */
  @JsonProperty("status")
  @JacksonXmlProperty(localName = "status")
  private Integer status;

  /** 发放时间，时间戳 */
  @JsonProperty("send_time")
  @JacksonXmlProperty(localName = "send_time")
  private Long sendTime;

  /** 最近更新时间，时间戳 */
  @JsonProperty("update_time")
  @JacksonXmlProperty(localName = "update_time")
  private Long updateTime;

  /** 核销生效时间，时间戳 */
  @JsonProperty("start_time")
  @JacksonXmlProperty(localName = "start_time")
  private Long startTime;

  /** 核销结束时间，时间戳 */
  @JsonProperty("end_time")
  @JacksonXmlProperty(localName = "end_time")
  private Long endTime;

  /** 核销时间，时间戳。次卡时不返回此字段 */
  @JsonProperty("consume_time")
  @JacksonXmlProperty(localName = "consume_time")
  private Long consumeTime;

  /** 退券时间，时间戳。次卡时不返回此字段 */
  @JsonProperty("refund_time")
  @JacksonXmlProperty(localName = "refund_time")
  private Long refundTime;

  /** 核销门店名称 */
  @JsonProperty("consume_store_name")
  @JacksonXmlProperty(localName = "consume_store_name")
  private String consumeStoreName;

  /**  */
  @JsonProperty("voucher_type")
  @JacksonXmlProperty(localName = "voucher_type")
  private Integer voucherType;

  /** 券的售卖价格（分） */
  @JsonProperty("voucher_buy_amount")
  @JacksonXmlProperty(localName = "voucher_buy_amount")
  private Integer voucherBuyAmount;

  /** 券市场金额（分） */
  @JsonProperty("voucher_actual_amount")
  @JacksonXmlProperty(localName = "voucher_actual_amount")
  private Integer voucherActualAmount;

  /** 用户手机号 */
  @JsonProperty("telphone_no")
  @JacksonXmlProperty(localName = "telphone_no")
  private String telPhoneNo;

  /** 商品id */
  @JsonProperty("product_id")
  @JacksonXmlProperty(localName = "product_id")
  private String productId;

  /** 商品下的skuId */
  @JsonProperty("sku_id")
  @JacksonXmlProperty(localName = "sku_id")
  private String skuId;

  /** 购买券的订单id */
  @JsonProperty("order_id")
  @JacksonXmlProperty(localName = "order_id")
  private String orderId;

  /** 用户在商家品牌appid下的openid */
  @JsonProperty("openid")
  @JacksonXmlProperty(localName = "openid")
  private String openId;
}
