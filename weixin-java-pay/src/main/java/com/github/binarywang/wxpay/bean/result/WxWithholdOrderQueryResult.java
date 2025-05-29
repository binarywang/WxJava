package com.github.binarywang.wxpay.bean.result;

import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyCoupon;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.w3c.dom.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenliang
 * created on  2021-08-02 5:42 下午
 *
 * <pre>
 *   代扣订单查询结果
 * </pre>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@XStreamAlias("xml")
public class WxWithholdOrderQueryResult extends BaseWxPayResult implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 设备号
   * 非必传
   */
  @XStreamAlias("device_info")
  private String deviceInfo;

  /**
   * 用户标识
   */
  @XStreamAlias("openid")
  private String openId;

  /**
   * 是否关注公众号
   * 非必传
   */
  @XStreamAlias("is_subscribe")
  private String isSubscribe;

  /**
   * 交易类型
   */
  @XStreamAlias("trade_type")
  private String tradeType;

  /**
   * 交易状态
   * SUCCESS ： 支付成功，REFUND：转入退款，NOTPAY：未支付，CLOSED：已关闭，ACCEPT：已接收，PAY_FAIL:支付失败
   */
  @XStreamAlias("trade_state")
  private String tradeState;

  /**
   * 付款银行
   */
  @XStreamAlias("bank_type")
  private String bankType;

  /**
   * 总金额
   */
  @XStreamAlias("total_fee")
  private Integer totalFee;

  /**
   * 货币类型
   * 非必传
   */
  @XStreamAlias("fee_type")
  private String feeType;

  /**
   * 现金支付金额
   */
  @XStreamAlias("cash_fee")
  private Integer cashFee;

  /**
   * 现金支付货币类型
   * 非必传
   */
  @XStreamAlias("cash_fee_type")
  private String cashFeeType;

  /**
   * 代金券或立减优惠金额
   * 非必传
   */
  @XStreamAlias("coupon_fee")
  private Integer couponFee;

  /**
   * 代金券或立减优惠使用数量
   */
  @XStreamAlias("coupon_count")
  private Integer couponCount;

  private List<WxPayOrderNotifyCoupon> couponList;


  /**
   * 微信支付单号
   */
  @XStreamAlias("transaction_id")
  private String transactionId;

  /**
   * 商户订单号
   */
  @XStreamAlias("out_trade_no")
  private String outTradeNo;

  /**
   * 商家数据包
   */
  @XStreamAlias("attach")
  private String attach;

  /**
   * 支付完成时间
   */
  @XStreamAlias("time_end")
  private String timeEnd;

  /**
   * 交易状态描述
   * 例如：支付失败，请重新下单
   */
  @XStreamAlias("trade_state_desc")
  private String tradeStateDesc;


  /**
   * 通过xml组装couponList属性内容.
   */
  protected void composeCoupons() {
    if (this.couponCount == null || this.couponCount == 0) {
      return;
    }
    this.couponList = new ArrayList<>(couponCount);
    for (int i = 0; i < this.couponCount; i++) {
      WxPayOrderNotifyCoupon coupon = new WxPayOrderNotifyCoupon();
      coupon.setCouponId(this.getXmlValue("xml/coupon_id_" + i));
      coupon.setCouponType(this.getXmlValue("xml/coupon_type_" + i));
      coupon.setCouponFee(this.getXmlValueAsInt("xml/coupon_fee_" + i));
      couponList.add(coupon);
    }
  }

  @Override
  protected void loadXml(Document d) {
    deviceInfo = readXmlString(d, "device_info");
    openId = readXmlString(d, "openid");
    isSubscribe = readXmlString(d, "is_subscribe");
    bankType = readXmlString(d, "bank_type");
    totalFee = readXmlInteger(d, "total_fee");
    feeType = readXmlString(d, "fee_type");
    cashFee = readXmlInteger(d, "cash_fee");
    tradeType = readXmlString(d, "trade_type");
    cashFeeType = readXmlString(d, "cash_fee_type");
    couponFee = readXmlInteger(d, "coupon_fee");
    couponCount = readXmlInteger(d, "coupon_count");
    transactionId = readXmlString(d, "transaction_id");
    outTradeNo = readXmlString(d, "out_trade_no");
    attach = readXmlString(d, "attach");
    timeEnd = readXmlString(d, "time_end");
    tradeState = readXmlString(d, "trade_state");
    tradeStateDesc = readXmlString(d, "trade_state_desc");

    composeCoupons();
  }
}
