package cn.binarywang.wx.miniapp.bean.complaint;

import cn.binarywang.wx.miniapp.bean.WxMaBaseResponse;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 小程序投诉单详情结果
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on 2025-01-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxMaComplaintDetailResult extends WxMaBaseResponse {

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
   * 描述：用户提交投诉的时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE
   * </pre>
   */
  @SerializedName("complaint_time")
  private String complaintTime;

  /**
   * <pre>
   * 字段名：投诉详情
   * 是否必填：是
   * 描述：用户提交的投诉详情
   * </pre>
   */
  @SerializedName("complaint_detail")
  private String complaintDetail;

  /**
   * <pre>
   * 字段名：投诉状态
   * 是否必填：是
   * 描述：投诉单状态：WAITING_MERCHANT_RESPONSE-等待商户回复 MERCHANT_RESPONSED-商户已回复 WAITING_USER_RESPONSE-等待用户回复 USER_RESPONSED-用户已回复 COMPLAINT_COMPLETED-投诉已完成
   * </pre>
   */
  @SerializedName("complaint_state")
  private String complaintState;

  /**
   * <pre>
   * 字段名：投诉人openid
   * 是否必填：是
   * 描述：投诉人在小程序的openid
   * </pre>
   */
  @SerializedName("openid")
  private String openid;

  /**
   * <pre>
   * 字段名：投诉人联系方式
   * 是否必填：否
   * 描述：投诉人联系方式，可能为空
   * </pre>
   */
  @SerializedName("phone_number")
  private String phoneNumber;

  /**
   * <pre>
   * 字段名：被投诉订单信息
   * 是否必填：是
   * 描述：被投诉的订单信息
   * </pre>
   */
  @SerializedName("complaint_order_info")
  private ComplaintOrderInfo complaintOrderInfo;

  /**
   * <pre>
   * 字段名：投诉材料
   * 是否必填：否
   * 描述：用户上传的投诉相关的图片凭证
   * </pre>
   */
  @SerializedName("complaint_media_list")
  private List<ComplaintMedia> complaintMediaList;

  /**
   * 被投诉订单信息
   */
  @Data
  public static class ComplaintOrderInfo implements Serializable {
    private static final long serialVersionUID = 3244929701614280806L;

    /**
     * <pre>
     * 字段名：商户订单号
     * 是否必填：是
     * 描述：商户系统内部订单号
     * </pre>
     */
    @SerializedName("transaction_id")
    private String transactionId;

    /**
     * <pre>
     * 字段名：微信订单号
     * 是否必填：是
     * 描述：微信支付系统生成的订单号
     * </pre>
     */
    @SerializedName("out_trade_no")
    private String outTradeNo;

    /**
     * <pre>
     * 字段名：订单金额
     * 是否必填：是
     * 描述：订单金额，单位为分
     * </pre>
     */
    @SerializedName("amount")
    private Integer amount;
  }

  /**
   * 投诉材料
   */
  @Data
  public static class ComplaintMedia implements Serializable {
    private static final long serialVersionUID = 3244929701614280806L;

    /**
     * <pre>
     * 字段名：媒体文件业务类型
     * 是否必填：是
     * 描述：媒体文件对应的业务类型：USER_COMPLAINT_IMAGE-用户投诉图片
     * </pre>
     */
    @SerializedName("media_type")
    private String mediaType;

    /**
     * <pre>
     * 字段名：媒体文件请求URL
     * 是否必填：是
     * 描述：微信返回的媒体文件请求URL，通过该URL可以获取到对应的媒体文件（图片、视频等）
     * </pre>
     */
    @SerializedName("media_url")
    private String mediaUrl;
  }
}