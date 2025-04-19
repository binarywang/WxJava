package com.github.binarywang.wxpay.bean.complaint;


import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 微信消费者投诉2.0
 * 查询投诉单协商历史返回的实体
 *
 * @author <a href="https://gitee.com/jeequan/jeepay">jmdhappy</a>
 * created on  2022-3-19
 */
@Data
public class NegotiationHistoryResult implements Serializable {
  private static final long serialVersionUID = -6201692411535927502L;

  /**
   * <pre>
   * 字段名：分页大小
   * 是否必填：是
   * 描述：设置该次请求返回的最大投诉条数，范围【1,50】
   * </pre>
   */
  @SerializedName("limit")
  private Integer limit;

  /**
   * <pre>
   * 字段名：分页开始位置
   * 是否必填：是
   * 描述：该次请求的分页开始位置，从0开始计数，例如offset=10，表示从第11条记录开始返回。
   * </pre>
   */
  @SerializedName("offset")
  private Integer offset;

  /**
   * <pre>
   * 字段名：投诉协商历史总条数
   * 是否必填：否
   * 描述：投诉协商历史总条数，当offset=0时返回
   * </pre>
   */
  @SerializedName("total_count")
  private Integer totalCount;

  /**
   * 投诉协商历史
   */
  @SerializedName("data")
  private List<NegotiationHistory> data;

  @Data
  public static class NegotiationHistory implements Serializable {
    private static final long serialVersionUID = 4240983048700956824L;

    /**
     * <pre>
     * 字段名：投诉资料列表
     * 是否必填：是
     * 用户上传的投诉相关资料，包括图片凭证等
     * </pre>
     */
    @SerializedName("complaint_media_list")
    private ComplaintMedia complaintMediaList;

    @Data
    public static class ComplaintMedia implements Serializable {
      private static final long serialVersionUID = 4240983048700956803L;

      /**
       * <pre>
       * 字段名：媒体文件业务类型
       * 是否必填：是
       * 描述：
       * 媒体文件对应的业务类型
       * USER_COMPLAINT_IMAGE：用户投诉图片，用户提交投诉时上传的图片凭证
       * OPERATION_IMAGE：操作流水图片，用户、商户、微信支付客服在协商解决投诉时，上传的图片凭证
       * 注：用户上传的图片凭证会以白名单的形式提供给商户，若希望查看用户图片，联系微信支付客服
       * 示例值：USER_COMPLAINT_IMAGE
       * </pre>
       */
      @SerializedName("media_type")
      private String mediaType;

      /**
       * <pre>
       * 字段名：媒体文件请求url
       * 是否必填：是
       * 描述：
       * 微信返回的媒体文件请求url
       * </pre>
       */
      @SerializedName("media_url")
      private List<String> mediaUrl;

    }

    /**
     * <pre>
     * 字段名：操作流水号
     * 是否必填：是
     * 描述：
     * 操作流水号
     * </pre>
     */
    @SerializedName("log_id")
    private String logId;

    /**
     * <pre>
     * 字段名：操作人
     * 是否必填：是
     * 描述：
     * 当前投诉协商记录的操作人
     * </pre>
     */
    @SerializedName("operator")
    private String operator;

    /**
     * <pre>
     * 字段名：操作时间
     * 是否必填：是
     * 描述：
     * 当前投诉协商记录的操作时间，遵循rfc3339标准格式，格式为yyyy-MM-DDTHH:mm:ss.sss+TIMEZONE，yyyy-MM-DD表示年月日，
     * T出现在字符串中，表示time元素的开头，HH:mm:ss.sss表示时分秒毫秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。
     * 例如：2015-05-20T13:29:35.120+08:00表示北京时间2015年05月20日13点29分35秒。
     * 示例值：2015-05-20T13:29:35.120+08:00
     * </pre>
     */
    @SerializedName("operate_time")
    private String operateTime;

    /**
     * <pre>
     * 字段名：操作类型
     * 是否必填：是
     * 描述：
     * 当前投诉协商记录的操作类型，对应枚举：
     * USER_CREATE_COMPLAINT: 用户提交投诉
     * USER_CONTINUE_COMPLAINT: 用户继续投诉
     * USER_RESPONSE: 用户留言
     * PLATFORM_RESPONSE: 平台留言
     * MERCHANT_RESPONSE: 商户留言
     * MERCHANT_CONFIRM_COMPLETE: 商户申请结单
     * USER_CREATE_COMPLAINT_SYSTEM_MESSAGE: 用户提交投诉系统通知
     * COMPLAINT_FULL_REFUNDED_SYSTEM_MESSAGE: 投诉单发起全额退款系统通知
     * USER_CONTINUE_COMPLAINT_SYSTEM_MESSAGE: 用户继续投诉系统通知
     * USER_REVOKE_COMPLAINT: 用户主动撤诉（只存在于历史投诉单的协商历史中）
     * USER_COMFIRM_COMPLAINT: 用户确认投诉解决（只存在于历史投诉单的协商历史中）
     * PLATFORM_HELP_APPLICATION: 平台催办
     * USER_APPLY_PLATFORM_HELP: 用户申请平台协助
     * MERCHANT_APPROVE_REFUND: 商户同意退款申请
     * MERCHANT_REFUSE_RERUND: 商户拒绝退款申请, 此时操作内容里展示拒绝原因
     * USER_SUBMIT_SATISFACTION: 用户提交满意度调查结果,此时操作内容里会展示满意度分数
     * SERVICE_ORDER_CANCEL: 服务订单已取消
     * SERVICE_ORDER_COMPLETE: 服务订单已完成
     * COMPLAINT_PARTIAL_REFUNDED_SYSTEM_MESSAGE: 投诉单发起部分退款系统通知
     * COMPLAINT_REFUND_RECEIVED_SYSTEM_MESSAGE: 投诉单退款到账系统通知
     * COMPLAINT_ENTRUSTED_REFUND_SYSTEM_MESSAGE: 投诉单受托退款系统通知
     * USER_APPLY_PLATFORM_SERVICE: 用户申请平台协助
     * USER_CANCEL_PLATFORM_SERVICE: 用户取消平台协助
     * PLATFORM_SERVICE_FINISHED: 客服结束平台协助
     * </pre>
     */
    @SerializedName("operate_type")
    private String operateType;

    /**
     * <pre>
     * 字段名：操作内容
     * 是否必填：否
     * 描述：
     * 当前投诉协商记录的具体内容
     * </pre>
     */
    @SerializedName("operate_details")
    private String operateDetails;

    /**
     * <pre>
     * 字段名：图片凭证
     * 是否必填：是
     * 描述：
     * 当前投诉协商记录提交的图片凭证（url格式），最多返回4张图片，url有效时间为1小时。如未查询到协商历史图片凭证，则返回空数组。
     * 注：本字段包含商户、微信支付客服在协商解决投诉时上传的图片凭证，若希望查看用户图片，请使用complaint_media_list字段并联系微信支付客服
     * 注：此字段不包含用户提交的图片凭证，建议统一使用complaint_media_list字段接收和请求资料凭证，未来该字段将废弃
     * </pre>
     */
    @SerializedName("image_list")
    private List<String> imageList;

    /**
     * <pre>
     * 字段名：用户申请平台协助原因
     * 是否必填：否
     * 描述：用户此次申请平台协助时选择的申请协助原因
     * </pre>
     */
    @SerializedName("user_appy_platform_service_reason")
    private String userApplyPlatformServiceReason;

    /**
     * <pre>
     * 字段名：用户申请平台协助原因描述
     * 是否必填：否
     * 描述：用户此次申请平台协助时填写的具体申请协助原因描述
     * </pre>
     */
    @SerializedName("user_appy_platform_service_reason_description")
    private String userApplyPlatformServiceReasonDescription;

  }

}
