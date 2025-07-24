package com.github.binarywang.wxpay.bean.complaint;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 微信消费者投诉2.0
 * 更新退款审批结果请求实体
 *
 * @author <a href="https://github.com/jackytse">jackytse</a>
 * created on  2024-12-21
 */
@Data
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRefundProgressRequest implements Serializable {
  private static final long serialVersionUID = 6975811815225228118L;

  /**
   * <pre>
   * 字段名：投诉单号
   * 是否必填：是
   * 描述：投诉单对应的投诉单号
   * </pre>
   */
  @SerializedName("complaint_id")
  @Expose
  private String complaintId;

  /**
   * <pre>
   * 字段名：审批动作
   * 是否必填：是
   * 描述：同意 或 拒绝
   * 可选取值：
   * REJECT: 拒绝退款
   * APPROVE: 同意退款
   * </pre>
   */
  @SerializedName("action")
  private String action;

  /**
   * <pre>
   * 字段名：预计发起退款时间
   * 是否必填：否
   * 描述：在同意退款时返回，预计将在多少个工作日内能发起退款, 0代表当天
   * </pre>
   */
  @SerializedName("launch_refund_day")
  private Integer launchRefundDay;

  /**
   * <pre>
   * 字段名：拒绝退款原因
   * 是否必填：否 string(200)
   * 描述：在拒绝退款时返回拒绝退款的原因
   * </pre>
   */
  @SerializedName("reject_reason")
  private String rejectReason;

  /**
   * <pre>
   * 字段名：拒绝退款的举证图片列表
   * 是否必填：否
   * 描述：在拒绝退款时，如果有拒绝的图片举证，可以提供 最多上传4张图片,
   *      传入调用“商户上传反馈图片”接口返回的media_id，最多上传4张图片凭证
   *
   * </pre>
   */
  @SerializedName("reject_media_list")
  private List<String> rejectMediaList;

  /**
   * <pre>
   * 字段名：备注
   * 是否必填：否 string(200)
   * 描述：任何需要向微信支付客服反馈的信息
   * </pre>
   */
  @SerializedName("remark")
  private String remark;
}
