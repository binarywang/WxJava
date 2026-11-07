package com.binarywang.wxjava.store.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 售后单退款直接原因
 *
 * @author lizhengwu
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum RefundReason {
  /** 1 商家通过店铺管理页或者小助手发起退款 */
  MERCHANT_INITIATED_REFUND(1, "商家通过店铺管理页或者小助手发起退款"),
  /** 2 退货退款场景，商家同意买家未上传物流单号情况下确认收货并退款，该场景限于订单无运费险 */
  MERCHANT_AGREES_NO_TRACKING_REFUND(2, "退货退款场景，商家同意买家未上传物流单号情况下确认收货并退款，该场景限于订单无运费险"),
  /** 3 商家通过后台api发起退款 */
  MERCHANT_API_INITIATED_REFUND(3, "商家通过后台api发起退款"),
  /** 4 未发货售后平台自动同意 */
  PRE_SHIPMENT_AUTOMATIC_REFUND(4, "未发货售后平台自动同意"),
  /** 5 平台介入纠纷退款 */
  PLATFORM_INTERVENED_DISPUTE_REFUND(5, "平台介入纠纷退款"),
  /** 6 特殊场景下平台强制退款 */
  PLATFORM_FORCED_REFUND(6, "特殊场景下平台强制退款"),
  /** 7 退货退款场景，买家同意没有上传物流单号情况下，商家确认收货并退款，该场景限于订单包含运费险，并无法理赔 */
  BUYER_AGREES_NO_TRACKING_REFUND(7, "退货退款场景，买家同意没有上传物流单号情况下，商家确认收货并退款，该场景限于订单包含运费险，并无法理赔"),
  /** 8 商家发货超时，平台退款 */
  LATE_SHIPMENT_PLATFORM_REFUND(8, "商家发货超时，平台退款"),
  /** 9 商家处理买家售后申请超时，平台自动同意退款 */
  MERCHANT_OVERDUE_AUTO_REFUND(9, "商家处理买家售后申请超时，平台自动同意退款"),
  /** 10 用户确认收货超时，平台退款 */
  BUYER_OVERDUE_AUTO_REFUND(10, "用户确认收货超时，平台退款"),
  /** 11 商家确认收货超时，平台退款 */
  MERCHANT_OVERDUE_CONFIRMATION_REFUND(11, "商家确认收货超时，平台退款"),
  ;

  private final int key;
  private final String value;

  RefundReason(int key, String value) {
    this.key = key;
    this.value = value;
  }

  public int getKey() {
    return key;
  }

  public String getValue() {
    return value;
  }
}
