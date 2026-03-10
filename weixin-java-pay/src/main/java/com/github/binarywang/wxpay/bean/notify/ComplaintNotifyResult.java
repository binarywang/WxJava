package com.github.binarywang.wxpay.bean.notify;

import com.github.binarywang.wxpay.v3.SpecEncrypt;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 投诉通知.
 * 文档见：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter10_2_16.shtml
 *
 * @author <a href="https://gitee.com/jeequan/jeepay">jmdhappy</a>
 */
@Data
@NoArgsConstructor
public class ComplaintNotifyResult implements Serializable {
  private static final long serialVersionUID = -1L;
  /**
   * 源数据
   */
  private OriginNotifyResponse rawData;
  /**
   * 解密后的数据
   */
  private DecryptNotifyResult result;

  @Data
  @NoArgsConstructor
  public static class DecryptNotifyResult implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * <pre>
     * 字段名：投诉单号
     * 是否必填：是
     * 描述：
     *  投诉单对应的投诉单号
     * </pre>
     */
    @SerializedName(value = "complaint_id")
    private String complaintId;

    /**
     * <pre>
     * 字段名：动作类型
     * 是否必填：是
     * 描述：
     * 触发本次投诉通知回调的具体动作类型，枚举如下：
     * 常规通知：
     * CREATE_COMPLAINT：用户提交投诉
     * CONTINUE_COMPLAINT：用户继续投诉
     * USER_RESPONSE：用户新留言
     * RESPONSE_BY_PLATFORM：平台新留言
     * SELLER_REFUND：商户发起全额退款
     * MERCHANT_RESPONSE：商户新回复
     * MERCHANT_CONFIRM_COMPLETE：商户反馈处理完成
     * USER_APPLY_PLATFORM_SERVICE：用户申请平台协助
     * USER_CANCEL_PLATFORM_SERVICE：用户取消平台协助
     * PLATFORM_SERVICE_FINISHED：客服结束平台协助
     *
     * 申请退款单的附加通知：
     * 以下通知会更新投诉单状态，建议收到后查询投诉单详情。
     * MERCHANT_APPROVE_REFUND：商户同意退款
     * MERCHANT_REJECT_REFUND：商户驳回退款
     * REFUND_SUCCESS：退款到账
     * </pre>
     */
    @SerializedName(value = "action_type")
    private String actionType;

    /**
     * <pre>
     * 字段名：商户订单号
     * 是否必填：是
     * 描述：
     * 投诉单关联的商户订单号
     * </pre>
     */
    @SerializedName("out_trade_no")
    private String outTradeNo;

    /**
     * <pre>
     * 字段名：投诉时间
     * 是否必填：是
     * 描述：投诉时间，遵循rfc3339标准格式，格式为yyyy-MM-DDTHH:mm:ss.sss+TIMEZONE，yyyy-MM-DD表示年月日，
     * T出现在字符串中，表示time元素的开头，HH:mm:ss.sss表示时分秒毫秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。
     * 例如：2015-05-20T13:29:35.120+08:00表示北京时间2015年05月20日13点29分35秒
     * 示例值：2015-05-20T13:29:35.120+08:00
     * </pre>
     */
    @SerializedName("complaint_time")
    private String complaintTime;

    /**
     * <pre>
     * 字段名：订单金额
     * 是否必填：是
     * 描述：
     * 订单金额，单位（分）
     * </pre>
     */
    @SerializedName("amount")
    private Integer amount;

    /**
     * <pre>
     * 字段名：投诉人联系方式
     * 是否必填：否
     * 投诉人联系方式。该字段已做加密处理，具体解密方法详见敏感信息加密说明。
     * </pre>
     */
    @SerializedName("payer_phone")
    @SpecEncrypt
    private String payerPhone;

    /**
     * <pre>
     * 字段名：投诉详情
     * 是否必填：是
     * 投诉的具体描述
     * </pre>
     */
    @SerializedName("complaint_detail")
    private String complaintDetail;

    /**
     * <pre>
     * 字段名：投诉单状态
     * 是否必填：是
     * 标识当前投诉单所处的处理阶段，具体状态如下所示：
     * PENDING：待处理
     * PROCESSING：处理中
     * PROCESSED：已处理完成
     * </pre>
     */
    @SerializedName("complaint_state")
    private String complaintState;

    /**
     * <pre>
     * 字段名：微信订单号
     * 是否必填：是
     * 描述：
     * 投诉单关联的微信订单号
     * </pre>
     */
    @SerializedName("transaction_id")
    private String transactionId;

    /**
     * <pre>
     * 字段名：商户处理状态
     * 是否必填：是
     * 描述：
     * 触发本次投诉通知回调的具体动作类型，枚举如下：
     * 常规通知：
     * CREATE_COMPLAINT：用户提交投诉
     * CONTINUE_COMPLAINT：用户继续投诉
     * USER_RESPONSE：用户新留言
     * RESPONSE_BY_PLATFORM：平台新留言
     * SELLER_REFUND：商户发起全额退款
     * MERCHANT_RESPONSE：商户新回复
     * MERCHANT_CONFIRM_COMPLETE：商户反馈处理完成
     * USER_APPLY_PLATFORM_SERVICE：用户申请平台协助
     * USER_CANCEL_PLATFORM_SERVICE：用户取消平台协助
     * PLATFORM_SERVICE_FINISHED：客服结束平台协助
     *
     * 申请退款单的附加通知：
     * 以下通知会更新投诉单状态，建议收到后查询投诉单详情。
     * MERCHANT_APPROVE_REFUND：商户同意退款
     * MERCHANT_REJECT_REFUND：商户驳回退款
     * REFUND_SUCCESS：退款到账
     * </pre>
     */
    @SerializedName("complaint_handle_state")
    private String complaintHandleState;
  }

}
