package com.binarywang.wxjava.store.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 微信小店 售后单状态
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AfterSaleStatus {
  /** 用户取消申请 */
  USER_CANCELD("USER_CANCELD", "用户取消申请"),
  /** 商家受理中 */
  MERCHANT_PROCESSING("MERCHANT_PROCESSING", "商家受理中"),
  /** 商家拒绝退款 */
  MERCHANT_REJECT_REFUND("MERCHANT_REJECT_REFUND", "商家拒绝退款"),
  /** 商家拒绝退货退款 */
  MERCHANT_REJECT_RETURN("MERCHANT_REJECT_RETURN", "商家拒绝退货退款"),
  /** 待买家退货 */
  USER_WAIT_RETURN("USER_WAIT_RETURN", "待买家退货"),
  /** 7 售后单关闭 */
  RETURN_CLOSED("RETURN_CLOSED", "退货退款关闭"),
  /** 8 待商家收货 */
  MERCHANT_WAIT_RECEIPT("MERCHANT_WAIT_RECEIPT", "待商家收货"),
  /** 商家逾期未退款 */
  MERCHANT_OVERDUE_REFUND("MERCHANT_OVERDUE_REFUND", "商家逾期未退款"),
  /** 退款完成 */
  MERCHANT_REFUND_SUCCESS("MERCHANT_REFUND_SUCCESS", "退款完成"),
  /** 退货退款完成 */
  MERCHANT_RETURN_SUCCESS("MERCHANT_RETURN_SUCCESS", "退货退款完成"),
  /** 11 平台退款中 */
  PLATFORM_REFUNDING("PLATFORM_REFUNDING", "平台退款中"),
  /** 25 平台退款失败 */
  PLATFORM_REFUND_FAIL("PLATFORM_REFUND_FAIL", "平台退款失败"),
  /** 待用户确认 */
  USER_WAIT_CONFIRM("USER_WAIT_CONFIRM", "待用户确认"),
  /** 商家打款失败，客服关闭售后 */
  MERCHANT_REFUND_RETRY_FAIL("MERCHANT_REFUND_RETRY_FAIL", "商家打款失败，客服关闭售后"),
  /** 售后关闭 */
  MERCHANT_FAIL("MERCHANT_FAIL", "售后关闭"),
  /** 待用户处理商家协商 */
  USER_WAIT_CONFIRM_UPDATE("USER_WAIT_CONFIRM_UPDATE", "待用户处理商家协商"),
  /** 待用户处理商家代发起的售后申请 */
  USER_WAIT_HANDLE_MERCHANT_AFTER_SALE("USER_WAIT_HANDLE_MERCHANT_AFTER_SALE", "待用户处理商家代发起的售后申请"),
  ;

  private final String key;
  private final String value;

  AfterSaleStatus(String key, String value) {
    this.key = key;
    this.value = value;
  }

  public String getKey() {
    return key;
  }

  public String getValue() {
    return value;
  }

}
