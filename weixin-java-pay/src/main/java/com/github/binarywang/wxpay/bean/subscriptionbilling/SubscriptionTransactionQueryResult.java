package com.github.binarywang.wxpay.bean.subscriptionbilling;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 查询扣费记录响应结果
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/doc/v3/merchant/4012161105
 * </pre>
 *
 * @author Binary Wang
 */
@Data
@NoArgsConstructor
public class SubscriptionTransactionQueryResult implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：总数量
   * 变量名：total_count
   * 是否必填：是
   * 类型：int
   * 描述：
   *  符合条件的记录总数量
   *  示例值：100
   * </pre>
   */
  @SerializedName("total_count")
  private Integer totalCount;

  /**
   * <pre>
   * 字段名：扣费记录列表
   * 变量名：data
   * 是否必填：是
   * 类型：array
   * 描述：
   *  扣费记录列表
   * </pre>
   */
  @SerializedName("data")
  private List<SubscriptionTransaction> data;

  /**
   * 扣费记录
   */
  @Data
  @NoArgsConstructor
  public static class SubscriptionTransaction implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * <pre>
     * 字段名：微信支付订单号
     * 变量名：transaction_id
     * 是否必填：是
     * 类型：string(32)
     * 描述：
     *  微信支付系统生成的订单号
     *  示例值：1217752501201407033233368018
     * </pre>
     */
    @SerializedName("transaction_id")
    private String transactionId;

    /**
     * <pre>
     * 字段名：商户订单号
     * 变量名：out_trade_no
     * 是否必填：是
     * 类型：string(32)
     * 描述：
     *  商户系统内部订单号
     *  示例值：1217752501201407033233368018
     * </pre>
     */
    @SerializedName("out_trade_no")
    private String outTradeNo;

    /**
     * <pre>
     * 字段名：预约扣费ID
     * 变量名：subscription_id
     * 是否必填：否
     * 类型：string(64)
     * 描述：
     *  微信支付预约扣费ID，预约扣费产生的交易才有此字段
     *  示例值：1217752501201407033233368018
     * </pre>
     */
    @SerializedName("subscription_id")
    private String subscriptionId;

    /**
     * <pre>
     * 字段名：交易状态
     * 变量名：trade_state
     * 是否必填：是
     * 类型：string(32)
     * 描述：
     *  交易状态
     *  SUCCESS：支付成功
     *  REFUND：转入退款
     *  NOTPAY：未支付
     *  CLOSED：已关闭
     *  REVOKED：已撤销(刷卡支付)
     *  USERPAYING：用户支付中
     *  PAYERROR：支付失败
     *  示例值：SUCCESS
     * </pre>
     */
    @SerializedName("trade_state")
    private String tradeState;

    /**
     * <pre>
     * 字段名：支付完成时间
     * 变量名：success_time
     * 是否必填：否
     * 类型：string(32)
     * 描述：
     *  支付完成时间，遵循rfc3339标准格式
     *  示例值：2018-06-08T10:34:56+08:00
     * </pre>
     */
    @SerializedName("success_time")
    private String successTime;

    /**
     * <pre>
     * 字段名：扣费金额
     * 变量名：amount
     * 是否必填：是
     * 类型：object
     * 描述：
     *  扣费金额信息
     * </pre>
     */
    @SerializedName("amount")
    private SubscriptionAmount amount;

    /**
     * <pre>
     * 字段名：用户标识
     * 变量名：openid
     * 是否必填：是
     * 类型：string(128)
     * 描述：
     *  用户在直连商户appid下的唯一标识
     *  示例值：oUpF8uMuAJO_M2pxb1Q9zNjWeS6o
     * </pre>
     */
    @SerializedName("openid")
    private String openid;

    /**
     * <pre>
     * 字段名：订单描述
     * 变量名：description
     * 是否必填：是
     * 类型：string(127)
     * 描述：
     *  订单描述
     *  示例值：腾讯充值中心-QQ会员充值
     * </pre>
     */
    @SerializedName("description")
    private String description;

    /**
     * <pre>
     * 字段名：附加数据
     * 变量名：attach
     * 是否必填：否
     * 类型：string(128)
     * 描述：
     *  附加数据
     *  示例值：自定义数据
     * </pre>
     */
    @SerializedName("attach")
    private String attach;
  }
}