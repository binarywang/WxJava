package com.binarywang.wxjava.store.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 纠纷历史操作类型
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ComplaintItemType {
  /** 1 申请平台介入 */
  APPLY_PLATFORM_INTERVENTION(1, "申请平台介入"),
  /** 2 用户留言 */
  USER_MESSAGE(2, "用户留言"),
  /** 3 商家留言 */
  MERCHANT_MESSAGE(3, "商家留言"),
  /** 4 提交投诉成功 */
  SUBMIT_COMPLAINT_SUCCESS(4, "提交投诉成功"),
  /** 5 投诉已取消 */
  COMPLAINT_CANCELLED(5, "投诉已取消"),
  /** 6 商家已超时 */
  MERCHANT_TIMEOUT(6, "商家已超时"),
  /** 7 用户补充凭证 */
  USER_SUPPLEMENTARY_EVIDENCE(7, "用户补充凭证"),
  /** 8 商家补充凭证 */
  MERCHANT_SUPPLEMENTARY_EVIDENCE(8, "商家补充凭证"),
  /** 10 待商家处理纠纷 */
  WAIT_MERCHANT_HANDLE_DISPUTE(10, "待商家处理纠纷"),
  /** 11 待平台处理 */
  WAIT_PLATFORM_HANDLE(11, "待平台处理"),
  /** 12 取消平台介入 */
  CANCEL_PLATFORM_INTERVENTION(12, "取消平台介入"),
  /** 13 平台处理中 */
  PLATFORM_PROCESSING(13, "平台处理中"),
  /** 14 待用户补充凭证 */
  WAIT_USER_SUPPLEMENTARY_EVIDENCE(14, "待用户补充凭证"),
  /** 16 待商家补充凭证 */
  WAIT_MERCHANT_SUPPLEMENTARY_EVIDENCE(16, "待商家补充凭证"),
  /** 18 待双方补充凭证 */
  WAIT_BOTH_PARTIES_SUPPLEMENTARY_EVIDENCE(18, "待双方补充凭证"),
  /** 20 待商家确认 */
  WAIT_MERCHANT_CONFIRM(20, "待商家确认"),
  /** 21 商家申诉中 */
  MERCHANT_APPEALING(21, "商家申诉中"),
  /** 22 调解完成 */
  MEDIATION_COMPLETE(22, "调解完成"),
  /** 23 待平台核实 */
  WAIT_PLATFORM_VERIFY(23, "待平台核实"),
  /** 24 重新退款中 */
  REFUNDING_AGAIN(24, "重新退款中"),
  /** 26 调解关闭 */
  MEDIATION_CLOSED(26, "调解关闭"),
  /** 30 平台判定用户责任 */
  PLATFORM_JUDGMENT_USER_RESPONSIBILITY(30, "平台判定用户责任"),
  /** 31 平台判定商家责任 */
  PLATFORM_JUDGMENT_MERCHANT_RESPONSIBILITY(31, "平台判定商家责任"),
  /** 32 平台判定双方责任 */
  PLATFORM_JUDGMENT_BOTH_PARTIES_RESPONSIBILITY(32, "平台判定双方责任"),
  /** 33 平台判定无责任 */
  PLATFORM_JUDGMENT_NO_RESPONSIBILITY(33, "平台判定无责任"),
  /** 34 平台判定申诉无效 */
  PLATFORM_JUDGMENT_APPEAL_INVALID(34, "平台判定申诉无效"),
  /** 35 平台判定申诉生效 */
  PLATFORM_JUDGMENT_APPEAL_EFFECTIVE(35, "平台判定申诉生效"),
  /** 36 平台判定退款有效 */
  PLATFORM_JUDGMENT_REFUND_EFFECTIVE(36, "平台判定退款有效"),
  /** 37 平台判定退款无效 */
  PLATFORM_JUDGMENT_REFUND_INVALID(37, "平台判定退款无效"),
  /** 50 用户发起退款 */
  USER_INITIATE_REFUND(50, "用户发起退款"),
  /** 51 商家拒绝退款 */
  MERCHANT_REFUSE_REFUND(51, "商家拒绝退款"),
  /** 52 用户取消申请 */
  USER_CANCEL_APPLICATION(52, "用户取消申请"),
  /** 56 待买家退货 */
  WAIT_BUYER_RETURN_GOODS(56, "待买家退货"),
  /** 57 退货退款关闭 */
  REFUND_CLOSED(57, "退货退款关闭"),
  /** 58 待商家收货 */
  WAIT_MERCHANT_RECEIVE_GOODS(58, "待商家收货"),
  /** 59 商家逾期未退款 */
  MERCHANT_OVERDUE_REFUND(59, "商家逾期未退款"),
  /** 60 退款完成 */
  REFUND_COMPLETE(60, "退款完成"),
  /** 61 退货退款完成 */
  REFUND_GOODS_COMPLETE(61, "退货退款完成"),
  /** 62 平台退款中 */
  PLATFORM_REFUNDING(62, "平台退款中"),
  /** 63 平台退款失败 */
  PLATFORM_REFUND_FAILED(63, "平台退款失败"),
  /** 64 待用户确认 */
  WAIT_USER_CONFIRM(64, "待用户确认"),

  ;

  private final int key;
  private final String value;

  ComplaintItemType(int key, String value) {
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
