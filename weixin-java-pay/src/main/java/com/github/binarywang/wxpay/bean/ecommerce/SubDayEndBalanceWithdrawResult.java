package com.github.binarywang.wxpay.bean.ecommerce;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 二级商户按日终余额预约提现结果
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/doc/v3/partner/4013328143
 * </pre>
 *
 * @author copilot
 * created on  2024/12/24
 */
@Data
@NoArgsConstructor
public class SubDayEndBalanceWithdrawResult implements Serializable {

  private static final long serialVersionUID = -8745123456789012346L;

  /**
   * <pre>
   * 字段名：二级商户号
   * 变量名：sub_mchid
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  电商平台二级商户号，由微信支付生成并下发。
   * 示例值：1900000109
   * </pre>
   */
  @SerializedName(value = "sub_mchid")
  private String subMchid;

  /**
   * <pre>
   * 字段名：电商平台商户号
   * 变量名：sp_mchid
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  电商平台商户号
   * 示例值：1800000123
   * </pre>
   */
  @SerializedName(value = "sp_mchid")
  private String spMchid;

  /**
   * <pre>
   * 字段名：商户提现单号
   * 变量名：out_request_no
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  商户提现单号，由商户自定义生成。
   * 示例值：20190611222222222200000000012122
   * </pre>
   */
  @SerializedName(value = "out_request_no")
  private String outRequestNo;

  /**
   * <pre>
   * 字段名：微信支付提现单号
   * 变量名：withdraw_id
   * 是否必填：是
   * 类型：string（128）
   * 描述：
   *  电商平台提交二级商户提现申请后，由微信支付返回的申请单号，作为查询申请状态的唯一标识。
   * 示例值：12321937198237912739132791732912793127931279317929791239112123
   * </pre>
   */
  @SerializedName(value = "withdraw_id")
  private String withdrawId;

  /**
   * <pre>
   * 字段名：提现单状态
   * 变量名：status
   * 是否必填：是
   * 类型：string（16）
   * 描述：
   *  枚举值：
   *  CREATE_SUCCESS：受理成功
   *  SUCCESS：提现成功
   *  FAIL：提现失败
   *  REFUND：提现退票
   *  CLOSE：关单
   *  INIT：业务单已创建
   * 示例值：CREATE_SUCCESS
   * </pre>
   */
  @SerializedName(value = "status")
  private String status;
}
