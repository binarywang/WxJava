package com.github.binarywang.wxpay.bean.customs;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author xifenzhu
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class DeclarationQueryResult implements Serializable {

  private static final long serialVersionUID = 7776809282150143165L;
  /**
   * <pre>
   * 字段名：机构APPID
   * 变量名：appid
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  微信分配的公众账号ID
   *  示例值：wxd678efh567hg6787
   * </pre>
   */
  @SerializedName(value = "appid")
  private String appid;

  /**
   * <pre>
   * 字段名：商户号
   * 变量名：mchid
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  微信支付分配的商户号
   *  示例值：1230000109
   * </pre>
   */
  @SerializedName(value = "mchid")
  private String mchid;

  /**
   * <pre>
   * 字段名：微信支付返回的订单号
   * 变量名：transaction_id
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  微信分配的公众账号ID
   *  示例值：1000320306201511078440737890
   * </pre>
   */
  @SerializedName(value = "transaction_id")
  private String transactionId;

  /**
   * <pre>
   * 字段名：核验机构
   * 变量名：verify_department
   * 是否必填：是
   * 类型：string（16）
   * 描述：
   *  核验机构代码
   *  UNIONPAY：银联
   *  NETSUNION：网联
   *  OTHERS：其他
   *  示例值：UNIONPAY
   * </pre>
   */
  @SerializedName(value = "verify_department")
  private String verifyDepartment;

  /**
   * <pre>
   * 字段名：核验机构交易流水号
   * 变量名：Verify_department_trade_id
   * 是否必填：是
   * 类型：string(64)
   * 描述：
   *  交易流水号，来自验核机构，如银联记录的交易流水号，供商户报备海关
   *  示例值：2018112288340107038204310100000
   * </pre>
   */
  @SerializedName(value = "verify_department_trade_id")
  private String verifyDepartmentTradeId;

  /**
   * <pre>
   * 字段名：偏移量
   * 变量名：offset
   * 是否必填：是
   * 类型：int
   * 描述：
   *  非0整数，该次请求资源的起始位置，从0开始计数。调用方选填，默认为0
   *  示例值：0
   * </pre>
   */
  @SerializedName(value = "offset")
  private Integer offset;

  /**
   * <pre>
   * 字段名：请求最大记录条数
   * 变量名：limit
   * 是否必填：是
   * 类型：int
   * 描述：
   *  非0非负的整数，该次请求可返回的最大资源条数。调用方选填，默认值建议为20
   *  示例值：20
   * </pre>
   */
  @SerializedName(value = "limit")
  private Integer limit;

  /**
   * <pre>
   * 字段名：查询结果总条数
   * 变量名：total_count
   * 是否必填：是
   * 类型：int
   * 描述：
   *  查询结果总条数
   *  示例值：1
   * </pre>
   */
  @SerializedName(value = "total_count")
  private Integer totalCount;

  /**
   * <pre>
   * 字段名：报关数据包
   * 变量名：data
   * 是否必填：是
   * 类型：array
   * 描述：
   *  报关单结果数组，具体内容参见下方描述
   *  示例值：
   * </pre>
   */
  @SerializedName(value = "data")
  private List<DeclarationData> data;

  /**
   * 驳回原因详情
   */
  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @Accessors(chain = true)
  public static class DeclarationData {
    /**
     * <pre>
     * 字段名：商户子订单号
     * 变量名：sub_order_no
     * 是否必填：否
     * 类型：string（32）
     * 描述：
     *  微信子订单号，如有拆单则返回
     *  示例值：20150806125346
     * </pre>
     */
    @SerializedName(value = "sub_order_no")
    private String subOrderNo;

    /**
     * <pre>
     * 字段名：微信子订单号
     * 变量名：sub_order_id
     * 是否必填：否
     * 类型：string（32）
     * 描述：
     *  商户子订单号，如有拆单则必传
     *  注意：仅适用于机构模式
     *  示例值：20150806125346
     * </pre>
     */
    @SerializedName(value = "sub_order_id")
    private String subOrderId;

    /**
     * <pre>
     * 字段名：商户海关备案号
     * 变量名：merchant_customs_no
     * 是否必填：是
     * 类型：string（32）
     * 描述：
     *  商户在海关登记的备案号
     *  示例值：123456
     * </pre>
     */
    @SerializedName(value = "mch_customs_no")
    private String merchantCustomsNo;

    /**
     * <pre>
     * 字段名：海关
     * 变量名：customs
     * 是否必填：是
     * 类型：string（32）
     * 描述：
     *  海关代码, 枚举值参见参数规定-海关列表（https://pay.weixin.qq.com/wiki/doc/api/wxpay/ch/declarecustom_ch/chapter2_3.shtml#menu11）
     *  示例值：SHANGHAI_ZS
     * </pre>
     */
    @SerializedName(value = "customs")
    private String customs;

    /**
     * <pre>
     * 字段名：关税
     * 变量名：duty
     * 是否必填：否
     * 类型：int
     * 描述：
     *  关税，以分为单位，非必填项，不会提交给海关
     *  示例值：888
     * </pre>
     */
    @SerializedName(value = "duty")
    private Integer duty;

    /**
     * <pre>
     * 字段名：货币类型
     * 变量名：fee_type
     * 是否必填：否
     * 类型：string（32）
     * 描述：
     *  微信支付订单支付时使用的币种，暂只支持人民币CNY，如有拆单则必传
     *  示例值：CNY
     * </pre>
     */
    @SerializedName(value = "fee_type")
    private String feeType;

    /**
     * <pre>
     * 字段名：子订单金额
     * 变量名：order_fee
     * 是否必填：否
     * 类型：int
     * 描述：
     *  子订单金额，以分为单位，不能超过原订单金额，order_fee=transport_fee+product_fee（应付金额=物流费+商品价格），如有拆单则必传
     *  示例值：888
     * </pre>
     */
    @SerializedName(value = "order_fee")
    private Integer orderFee;

    /**
     * <pre>
     * 字段名：物流费用
     * 变量名：transport_fee
     * 是否必填：否
     * 类型：int
     * 描述：
     *  物流费用，以分为单位，如有拆单则必传
     *  示例值：888
     * </pre>
     */
    @SerializedName(value = "transport_fee")
    private Integer transportFee;

    /**
     * <pre>
     * 字段名：商品费用
     * 变量名：product_fee
     * 是否必填：否
     * 类型：int
     * 描述：
     *  商品费用，以分为单位，如有拆单则必传
     *  示例值：888
     * </pre>
     */
    @SerializedName(value = "product_fee")
    private Integer productFee;

    /**
     * <pre>
     * 字段名：报关状态
     * 变量名：state
     * 是否必填：是
     * 类型：string（32）
     * 描述：
     *  申报结果状态码
     *  PROCESSING：申报中
     *  UNDECLARED：未申报
     *  SUBMITTED：已修改未申报
     *  SUCCESS：申报成功
     *  FAIL：申报失败
     *  EXCEPT：海关接口异常
     *  示例值：PROCESSING
     * </pre>
     */
    @SerializedName(value = "state")
    private String state;

    /**
     * <pre>
     * 字段名：报关结果说明
     * 变量名：explanation
     * 是否必填：是
     * 类型：string（128）
     * 描述：
     *  申报结果说明，如果状态是失败或异常，显示失败原因
     *  示例值：支付单已存在并且为非退单状态
     * </pre>
     */
    @SerializedName(value = "explanation")
    private String explanation;

    /**
     * <pre>
     * 字段名：最后更新时间
     * 变量名：modify_time
     * 是否必填：是
     * 类型：string（32）
     * 描述：
     *  最后更新时间，该时间取自微信服务器
     *  示例值：2015-09-01T10:00:00+08:00
     * </pre>
     */
    @SerializedName(value = "modify_time")
    private String modifyTime;
  }
}
