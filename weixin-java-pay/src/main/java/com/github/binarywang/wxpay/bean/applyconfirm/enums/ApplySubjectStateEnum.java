package com.github.binarywang.wxpay.bean.applyconfirm.enums;

/**
 * 开户意愿申请单状态枚举类
 *
 * @author 潘安
 */
public enum ApplySubjectStateEnum {
  /**
   * 【审核中】，请耐心等待3~7个工作日，微信支付将会完成审核。
   */
  APPLYMENT_STATE_WAITTING_FOR_AUDIT,
  /**
   * 【编辑中】，可能提交申请发生了错误导致，可用同一个业务申请编号重新提交。
   */
  APPLYMENT_STATE_EDITTING,
  /**
   * 【待确认联系信息】，请扫描微信支付返回的二维码确认联系信息(此过程可修改超级管理员手机号)。
   */
  APPLYMENT_STATE_WAITTING_FOR_CONFIRM_CONTACT,
  /**
   * 【待账户验证】，请扫描微信支付返回的二维码在小程序端完成账户验证。
   */
  APPLYMENT_STATE_WAITTING_FOR_CONFIRM_LEGALPERSON,
  /**
   * 【审核通过】，请扫描微信支付返回的二维码在小程序端完成授权流程。
   */
  APPLYMENT_STATE_PASSED,
  /**
   * 【审核驳回】，请按照驳回原因修改申请资料，并更换业务申请编码，重新提交申请。
   */
  APPLYMENT_STATE_REJECTED,
  /**
   * 【已冻结】，可能是该主体已完成过入驻，请查看驳回原因，并通知驳回原因中指定的联系人扫描微信支付返回的二维码在小程序端完成授权流程。
   */
  APPLYMENT_STATE_FREEZED,
  /**
   * 【已作废】，表示申请单已被撤销，无需再对其进行操作。
   */
  APPLYMENT_STATE_CANCELED

}
