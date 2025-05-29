package com.github.binarywang.wxpay.bean.ecommerce;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 提交注销申请单
 * <pre>
 *   https://pay.weixin.qq.com/docs/partner/apis/ecommerce-cancel/cancel-applications/create-cancel-application.html
 * </pre>
 */
@Data
@NoArgsConstructor
public class AccountCancelApplicationsResult implements Serializable {

  /**
   * 【商户注销申请单号】 商户注销申请单号，原样返回请求参数里的内容
   */
  @SerializedName(value = "out_apply_no")
  private String outApplyNo;

  /**
   * 【二级商户号】 二级商户号
   */
  @SerializedName(value = "sub_mchid")
  private String subMchid;

  /**
   * 【驳回原因】 受理失败原因
   */
  @SerializedName(value = "reject_reason")
  private String rejectReason;

  /**
   * 【注销状态】 注销状态
   * 可选取值：
   * REVIEWING: 审核中
   * REJECTED: 审核驳回，驳回原因详见reject_reason
   * CANCEL_SUCCESS: 注销成功
   */
  @SerializedName(value = "cancel_state")
  private String cancelState;

  /**
   * 【最后更新时间】 最后更新时间。遵循rfc3339标准格式，格式为yyyy-MM-DDTHH:mm:ss+TIMEZONE，yyyy-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日 13点29分35秒。
   */
  @SerializedName(value = "update_time")
  private String updateTime;
}
