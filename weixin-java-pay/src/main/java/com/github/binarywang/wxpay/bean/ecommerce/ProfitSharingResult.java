package com.github.binarywang.wxpay.bean.ecommerce;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 请求分账 结果响应
 *
 * @author f00lish
 * created on  2020/09/12
 */
@Data
@NoArgsConstructor
public class ProfitSharingResult implements Serializable {
  private static final long serialVersionUID = 9026456165403642050L;

  /**
   * <pre>
   * 字段名：二级商户号
   * 变量名：sub_mchid
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  分账出资的电商平台二级商户，填写微信支付分配的商户号。
   *  示例值：1900000109
   * </pre>
   */
  @SerializedName(value = "sub_mchid")
  private String subMchid;

  /**
   * <pre>
   * 字段名：微信订单号
   * 变量名：transaction_id
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  微信支付订单号。
   *  示例值：4208450740201411110007820472
   * </pre>
   */
  @SerializedName(value = "transaction_id")
  private String transactionId;

  /**
   * <pre>
   * 字段名：商户分账单号
   * 变量名：out_order_no
   * 是否必填：是
   * 类型：string（64）
   * 描述：
   *  商户系统内部的分账单号，在商户系统内部唯一（单次分账、多次分账、完结分账应使用不同的商户分账单号），同一分账单号多次请求等同一次。
   *  示例值：P20150806125346
   * </pre>
   */
  @SerializedName(value = "out_order_no")
  private String outOrderNo;

  /**
   * <pre>
   * 字段名：微信分账单号
   * 变量名：order_id
   * 是否必填：是
   * 类型：string (64)
   * 描述：
   *  微信分账单号，微信系统返回的唯一标识。
   *  示例值：6754760740201411110007865434
   * </pre>
   */
  @SerializedName(value = "order_id")
  private String orderId;

  /**
   * <pre>
   * 字段名：分账单状态
   * 变量名：status
   * 是否必填：是
   * 类型：string (64)
   * 描述：
   *  分账单状态，枚举值：
   *    PROCESSING：处理中，非终态
   *    FINISHED：分账完成，终态
   *  示例值：FINISHED
   * </pre>
   */
  @SerializedName(value = "status")
  private String status;

  /**
   * <pre>
   * 字段名：分账接收方列表
   * 变量名：receivers
   * 是否必填：否
   * 类型：array
   * 描述：
   *  分账接收方列表。当查询分账完结的执行结果时，不返回该字段
   * </pre>
   */
  @SerializedName(value = "receivers")
  private List<Receiver> receivers;
  /**
   * <pre>
   * 字段名：关单原因
   * 变量名：close_reason
   * 是否必填：否
   * 类型：string (32)
   * 描述：
   *  关单原因描述，当分账单状态status为CLOSED（处理失败，已关单）时，返回该字段。
   * 枚举值：
   *    NO_AUTH：分账授权已解除
   * 示例值：NO_AUTH
   * </pre>
   */
  @SerializedName(value = "close_reason")
  private String closeReason;

  /**
   * <pre>
   * 字段名：分账完结金额
   * 变量名：finish_amount
   * 是否必填：否
   * 类型：int
   * 描述：
   *  分账完结的分账金额，单位为分， 仅当查询分账完结的执行结果时，存在本字段。
   * 示例值：100
   * </pre>
   */
  @SerializedName(value = "finish_amount")
  private Integer finishAmount;

  /**
   * <pre>
   * 字段名：分账完结描述
   * 变量名：finish_description
   * 是否必填：否
   * 类型：string (80)
   * 描述：
   *  分账完结的原因描述，仅当查询分账完结的执行结果时，存在本字段。
   * 示例值：分账完结
   * </pre>
   */
  @SerializedName(value = "finish_description")
  private String finishDescription;

  @Data
  @NoArgsConstructor
  public static class Receiver implements Serializable {

    /**
     * <pre>
     * 字段名：分账金额
     * 变量名：amount
     * 是否必填：否
     * 类型：int
     * 描述：
     *  分账金额，单位为分，只能为整数，不能超过原订单支付金额及最大分账比例金额。
     * 示例值： 4208450740201411110007820472
     * </pre>
     */
    @SerializedName(value = "amount")
    private Integer amount;

    /**
     * <pre>
     * 字段名：分账描述
     * 变量名：description
     * 是否必填：是
     * 类型：string (80)
     * 描述：
     *  分账的原因描述，分账账单中需要体现。
     * 示例值：分帐1900000110
     * </pre>
     */
    @SerializedName(value = "description")
    private String description;

    /**
     * <pre>
     * 字段名：分账结果
     * 变量名：result
     * 是否必填：是
     * 类型：string (32)
     * 描述：
     *  分账结果，枚举值：
     *    PENDING：待分账
     *    SUCCESS：分账成功
     *    ADJUST：分账失败待调账
     *    RETURNED：已转回分账方
     *    CLOSED：已关闭
     * 示例值：SUCCESS
     * </pre>
     */
    @SerializedName(value = "result")
    private String result;

    /**
     * <pre>
     * 字段名：完成时间
     * 变量名：finish_time
     * 是否必填：是
     * 类型：string (64)
     * 描述：
     *  分账完成时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss.sss+TIMEZONE，YYYY-MM-DD表示年月日，
     *  T出现在字符串中，表示time元素的开头，HH:mm:ss.sss表示时分秒毫秒，TIMEZONE表示时区
     *  （+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35.120+08:00表示，北京时间2015年5月20日 13点29分35秒。
     * 示例值： 2015-05-20T13:29:35.120+08:00
     * </pre>
     */
    @SerializedName(value = "finish_time")
    private String finishTime;

    /**
     * <pre>
     * 字段名：分账失败原因
     * 变量名：fail_reason
     * 是否必填：否
     * 类型：string (32)
     * 描述：
     *  分账失败原因，当分账结果result为RETURNED（已转回分账方）或CLOSED（已关闭）时，返回该字段
     * 枚举值：
     *    ACCOUNT_ABNORMAL：分账接收账户异常
     *    NO_RELATION：分账关系已解除
     *    RECEIVER_HIGH_RISK：高风险接收方
     * 示例值：NO_RELATION
     * </pre>
     */
    @SerializedName(value = "fail_reason")
    private String failReason;

    /**
     * <pre>
     * 字段名：分账接收方类型
     * 变量名：type
     * 是否必填：是
     * 类型：string (32)
     * 描述：
     *  分账接收方类型，枚举值：
     *    MERCHANT_ID：商户
     *    PERSONAL_OPENID：个人
     * 示例值：MERCHANT_ID
     * </pre>
     */
    @SerializedName(value = "type")
    private String type;

    /**
     * <pre>
     * 字段名：分账接收方类型
     * 变量名：receiver_account
     * 是否必填：是
     * 类型：string (64)
     * 描述：
     *  分账接收方账号：
     * 类型是MERCHANT_ID时，是商户ID
     * 类型是PERSONAL_OPENID时，是个人openid
     * 示例值：1900000109
     * </pre>
     */
    @SerializedName(value = "receiver_account")
    private String receiverAccount;

    /**
     * <pre>
     * 字段名：分账明细单号
     * 变量名：detail_id
     * 类型：string[1,64]
     * 是否必填：是 （查询明细结果时是必有的）
     * 描述：微信分账明细单号，每笔分账业务执行的明细单号，可与资金账单对账使用
     * 示例值：3601111111111111111111
     * </pre>
     */
    @SerializedName(value = "detail_id")
    private String detailId;
  }
}
