package com.github.binarywang.wxpay.bean.complaint;


import com.github.binarywang.wxpay.v3.SpecEncrypt;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 微信消费者投诉2.0
 * 查询投诉单列表接口 和 查询投诉单详情接口返回的实体
 *
 * @author <a href="https://gitee.com/jeequan/jeepay">jmdhappy</a>
 * created on  2022-3-19
 */
@Data
public class ComplaintDetailResult implements Serializable {
  private static final long serialVersionUID = -6201692411535927503L;

  /**
   * <pre>
   * 字段名：投诉单号
   * 是否必填：是
   * 描述：投诉单对应的投诉单号
   * </pre>
   */
  @SerializedName("complaint_id")
  private String complaintId;

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
   * 字段名：投诉详情
   * 是否必填：是
   * 投诉的具体描述
   * </pre>
   */
  @SerializedName("complaint_detail")
  private String complaintDetail;

  /**
   * <pre>
   * 字段名：被诉商户号
   * 是否必填：是
   * 投诉单对应的被诉商户号。
   * </pre>
   */
  @SerializedName("complainted_mchid")
  private String complainedMchid;

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
   * 字段名：投诉人openid
   * 是否必填：是
   * 投诉人在商户appid下的唯一标识
   * </pre>
   */
  @SerializedName("payer_openid")
  private String payerOpenid;


  /**
   * <pre>
   * 字段名：投诉资料列表
   * 是否必填：是
   * 用户上传的投诉相关资料，包括图片凭证等
   * </pre>
   */
  @SerializedName("complaint_media_list")
  private List<ComplaintMedia> complaintMediaList;

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
   * 字段名：投诉单关联订单信息
   * 是否必填：是
   * 投诉单关联订单信息
   * 注：投诉单和订单目前是一对一关系，array是预留未来一对多的扩展
   * </pre>
   */
  @SerializedName("complaint_order_info")
  private List<ComplaintOrder> complaintOrderInfo;

  @Data
  public static class ComplaintOrder implements Serializable {
    private static final long serialVersionUID = 4240983048700956804L;

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
     * 字段名：订单金额
     * 是否必填：是
     * 描述：
     * 订单金额，单位（分）
     * </pre>
     */
    @SerializedName("amount")
    private Integer amount;

  }

    /**
   * <pre>
   * 字段名：投诉单关联服务单信息
   * 是否必填：否
   * 投诉单关联服务单信息, 支付分服务单投诉时可能存在
   * </pre>
   */
  @SerializedName("service_order_info")
  private List<ServiceOrder> serviceOrderInfo;

  /**
  * <pre>
  * 服务单信息
  * </pre>
  */
  @Data
  public static class ServiceOrder implements Serializable {
    private static final long serialVersionUID = 4240983048700956805L;

    /**
     * <pre>
     * 字段名：微信支付服务订单号
     * 是否必填：否
     * 描述：
     * 微信支付服务订单号，每个微信支付服务订单号与商户号下对应的商户服务订单号一一对应
     * </pre>
     */
    @SerializedName("order_id")
    private String orderId;

    /**
     * <pre>
     * 字段名：商户服务订单号
     * 是否必填：否
     * 描述：
     * 商户系统内部服务订单号（不是交易单号），与创建订单时一致
     * </pre>
     */
    @SerializedName("out_order_no")
    private String outOrderNo;

    /**
     * <pre>
     * 字段名：支付分服务单状态
     * 是否必填：否
     * 描述：
     * 此处上传的是用户发起投诉时的服务单状态，不会实时更新
     * DOING：服务订单进行中
     * REVOKED：商户取消服务订单
     * WAITPAY：服务订单待支付
     * DONE：服务订单已完成
     * </pre>
     */
    @SerializedName("state")
    private String state;

  }

  /**
   * <pre>
   * 字段名：投诉单是否已全额退款
   * 是否必填：是
   * 描述：
   * 投诉单下所有订单是否已全部全额退款
   * </pre>
   */
  @SerializedName("complaint_full_refunded")
  private Boolean complaintFullRefunded;

  /**
   * <pre>
   * 字段名：是否有待回复的用户留言
   * 是否必填：是
   * 描述：
   * 投诉单是否有待回复的用户留言
   * </pre>
   */
  @SerializedName("incoming_user_response")
  private Boolean incomingUserResponse;

  /**
   * <pre>
   * 字段名：问题描述
   * 是否必填：是
   * 描述：
   * 用户发起投诉前选择的faq标题（2021年7月15日之后的投诉单均包含此信息）
   * </pre>
   */
  @SerializedName("problem_description")
  private String problemDescription;

  /**
   * <pre>
   * 字段名：用户投诉次数
   * 是否必填：是
   * 描述：
   * 用户投诉次数。用户首次发起投诉记为1次，用户每有一次继续投诉就加1
   * </pre>
   */
  @SerializedName("user_complaint_times")
  private Integer userComplaintTimes;

  /**
   * <pre>
   * 字段名：问题类型
   * 是否必填：否
   * 描述：问题类型为申请退款的单据是需要最高优先处理的单据
   * REFUND：申请退款
   * SERVICE_NOT_WORK：服务权益未生效
   * OTHERS：其他类型
   * 示例值：REFUND
   * </pre>
   */
  @SerializedName("problem_type")
  private String problemType;

  /**
   * <pre>
   * 字段名：用户投诉次数
   * 是否必填：否
   * 描述：仅当问题类型为申请退款时, 有值, (单位:分)
   * 示例值：10
   * </pre>
   */
  @SerializedName("apply_refund_amount")
  private Integer applyRefundAmount;

  /**
   * <pre>
   * 字段名：用户标签列表
   * 是否必填：否
   * 描述： TRUSTED：可信，此类用户满足极速退款条件
   *       OTHERS：其它，此类用户不满足极速退款条件
   * 示例值：[TRUSTED]
   * </pre>
   */
  @SerializedName("user_tag_list")
  private String[] userTagList;

  /**
   * <pre>
   * 字段名：补充信息
   * 是否必填：否
   * 描述： 用在特定行业或场景下返回的补充信息
   * </pre>
   */
  @SerializedName("additional_info")
  private AdditionalInfo additionalInfo;

  @Data
  public static class AdditionalInfo implements Serializable {
    private static final long serialVersionUID = 7917816070738944147L;

    /**
     * <pre>
     * 字段名：补充信息类型
     * 是否必填：否
     * 描述： 补充信息类型
     * 示例值：SHARE_POWER_TYPE: 充电宝投诉相关行业
     * </pre>
     */
    @SerializedName("type")
    private String type;

    /**
     * <pre>
     * 字段名：充电宝投诉相关信息
     * 是否必填：否
     * 描述：当type为充电宝投诉相关时有值
     * </pre>
     */
    @SerializedName("share_power_info")
    private SharePowerInfo sharePowerInfo;

    /**
     * 充电宝投诉相关信息
     */
    @Data
    public static class SharePowerInfo implements Serializable {
      private static final long serialVersionUID = -2878382307459369354L;

      /**
       * <pre>
       * 字段名：归还时间
       * 是否必填：否
       * 描述：遵循rfc3339标准格式，格式为yyyy-MM-DDTHH:mm:ss+TIMEZONE，
       *      yyyy-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，
       *      HH:mm:ss表示时分秒，
       *      TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。
       * 示例值：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日 13点29分35秒
       * </pre>
       */
      @SerializedName("return_time")
      private String returnTime;

      /**
       * <pre>
       * 字段名：归还地点信息
       * 是否必填：否
       * 描述：  归还地点信息
       * </pre>
       */
      @SerializedName("return_address_info")
      private ReturnAddressInfo returnAddressInfo;

      @Data
      public static class ReturnAddressInfo implements Serializable {
        private static final long serialVersionUID = -7649986542568217256L;

        /**
         * <pre>
         * 字段名：归还地点
         * 是否必填：否 string(256)
         * 描述：归还地点
         * </pre>
         */
        @SerializedName("return_address")
        private String returnAddress;

        /**
         * <pre>
         * 字段名：归还地点经度
         * 是否必填：否 string(32)
         * 描述：经度，字符串，范围为-180~180，负数表示西经。使用GCJ-02坐标系
         * </pre>
         */
        @SerializedName("longitude")
        private String longitude;

        /**
         * <pre>
         * 字段名：归还地点纬度
         * 是否必填：否 string(32)
         * 描述：纬度，字符串，范围为-90~90，负数表示南纬。使用GCJ-02坐标系
         * </pre>
         */
        @SerializedName("latitude")
        private String latitude;
      }

      /**
       * <pre>
       * 字段名：是否归还同一柜机
       * 是否必填：否
       * 描述：用户声明是否将充电宝归还至与借取时同一柜机
       * </pre>
       */
      @SerializedName("is_returned_to_same_machine")
      private Boolean isReturnedToSameMachine;
    }
  }

  /**
   * <pre>
   * 字段名：是否在平台协助中
   * 是否必填：否
   * 描述：标识当前投诉单是否正处在平台协助流程中。
   * 注：在协助期间由微信支付客服为用户服务，期间商户向用户发送的留言用户不可见
   * </pre>
   */
  @SerializedName("in_platform_service")
  private Boolean inPlatformService;

  /**
   * <pre>
   * 字段名：是否需即时服务用户
   * 是否必填：否
   * 描述：因用户诉求紧急度、用户界面差异等因素，部分投诉单建议商户更即时地响应用户诉求。
   *      如此处标识为“是”，建议商户提升服务时效，给用户带来更好的体验
   * </pre>
   */
  @SerializedName("need_immediate_service")
  private Boolean needImmediateService;


}
