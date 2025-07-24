package com.github.binarywang.wxpay.bean.result;

import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyCoupon;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.converter.WxPayOrderNotifyResultConverter;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.util.SignUtils;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.common.util.xml.XStreamInitializer;
import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author chenliang
 * created on  2021-08-02 5:09 下午
 *
 * <pre>
 *   微信代扣扣款回调结果
 * </pre>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@XStreamAlias("xml")
public class WxWithholdNotifyResult extends BaseWxPayResult {

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
   * 用户子标识
   * 非必传
   */
  @XStreamAlias("sub_openid")
  private String subOpenId;

  /**
   * 是否关注公众号
   * 非必传
   */
  @XStreamAlias("is_subscribe")
  private String isSubscribe;

  /**
   * 是否关注子商户关联的公众号
   * 非必传
   */
  @XStreamAlias("sub_is_subscribe")
  private String subIsSubscribe;

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
   * 交易状态
   * SUCCESS ： 支付成功，REFUND：转入退款，NOTPAY：未支付，CLOSED：已关闭，ACCEPT：已接收，PAY_FAIL:支付失败
   */
  @XStreamAlias("trade_state")
  private String tradeState;

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
   * 委托代扣协议ID
   */
  @XStreamAlias("contract_id")
  private String contractId;



  @Override
  public void checkResult(WxPayService wxPayService, String signType, boolean checkSuccess) throws WxPayException {
    //防止伪造成功通知
    if (WxPayConstants.ResultCode.SUCCESS.equals(getReturnCode()) && getSign() == null) {
      throw new WxPayException("伪造的通知！");
    }

    super.checkResult(wxPayService, signType, checkSuccess);
  }

  /**
   * From xml wx withhold notify result.
   *
   * @param xmlString the xml string
   * @return the wx withhold result
   */
  public static WxWithholdNotifyResult fromXML(String xmlString) {
    XStream xstream = XStreamInitializer.getInstance();
    xstream.processAnnotations(WxWithholdNotifyResult.class);
    xstream.registerConverter(new WxPayOrderNotifyResultConverter(xstream.getMapper(), xstream.getReflectionProvider()));
    WxWithholdNotifyResult result = (WxWithholdNotifyResult) xstream.fromXML(xmlString);
    result.setXmlString(xmlString);
    return result;
  }

  @Override
  public Map<String, String> toMap() {
    Map<String, String> resultMap = SignUtils.xmlBean2Map(this);
    if (this.getCouponCount() != null && this.getCouponCount() > 0) {
      for (int i = 0; i < this.getCouponCount(); i++) {
        WxPayOrderNotifyCoupon coupon = couponList.get(i);
        resultMap.putAll(coupon.toMap(i));
      }
    }
    return resultMap;
  }

  @Override
  protected void loadXml(Document d) {
    deviceInfo = readXmlString(d, "device_info");
    openId = readXmlString(d, "openid");
    isSubscribe = readXmlString(d, "is_subscribe");
    subIsSubscribe = readXmlString(d, "sub_is_subscribe");
    subOpenId = readXmlString(d, "sub_openid");
    bankType = readXmlString(d, "bank_type");
    totalFee = readXmlInteger(d, "total_fee");
    feeType = readXmlString(d, "fee_type");
    cashFee = readXmlInteger(d, "cash_fee");
    cashFeeType = readXmlString(d, "cash_fee_type");
    couponFee = readXmlInteger(d, "coupon_fee");
    couponCount = readXmlInteger(d, "coupon_count");
    transactionId = readXmlString(d, "transaction_id");
    outTradeNo = readXmlString(d, "out_trade_no");
    attach = readXmlString(d, "attach");
    timeEnd = readXmlString(d, "time_end");
    tradeState = readXmlString(d, "trade_state");
    contractId = readXmlString(d, "contract_id");

    composeCoupons();
  }

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
  public String toString() {
    return WxGsonBuilder.create().toJson(this);
  }

}
