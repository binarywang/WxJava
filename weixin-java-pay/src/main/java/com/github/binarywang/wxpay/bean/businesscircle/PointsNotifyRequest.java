package com.github.binarywang.wxpay.bean.businesscircle;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 商圈积分同步
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/businesscircle/chapter3_2.shtml
 * </pre>
 *
 * @author thinsstar
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PointsNotifyRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：商圈商户ID
   * 变量名：sub_mchid
   * 是否必填：否
   * 类型：string[1,64]
   * 描述：
   *  当以服务商模式管理商圈积分能力时，则要带上商圈商户ID，否则留空
   *  示例值：1234567890
   * </pre>
   */
  @SerializedName(value = "sub_mchid")
  private String subMchid;

  /**
   * <pre>
   * 字段名：微信订单号
   * 变量名：transaction_id
   * 是否必填：是
   * 类型：string[1,64]
   * 描述：
   *  微信支付推送的商圈内交易通知里携带的微信订单号
   *  示例值：1217752501201407033233368018
   * </pre>
   */
  @SerializedName(value = "transaction_id")
  private String transactionId;

  /**
   * <pre>
   * 字段名：小程序appid
   * 变量名：appid
   * 是否必填：是
   * 类型：string[1,128]
   * 描述：
   *  顾客授权积分时使用的小程序的appid
   *  示例值：wx1234567890abcdef
   * </pre>
   */
  @SerializedName(value = "appid")
  private String appid;

  /**
   * <pre>
   * 字段名：用户标识
   * 变量名：openid
   * 是否必填：是
   * 类型：string[1,64]
   * 描述：
   *  顾客授权时使用的小程序上的openid
   *  示例值：oWmnN4xxxxxxxxxxe92NHIGf1xd8
   * </pre>
   */
  @SerializedName(value = "openid")
  private String openid;

  /**
   * <pre>
   * 字段名：是否获得积分
   * 变量名：earn_points
   * 是否必填：是
   * 类型：boolean
   * 描述：
   *  用于标明此单是否获得积分，
   *  true为获得积分，
   *  false为未获得
   *  示例值：true
   * </pre>
   */
  @SerializedName(value = "earn_points")
  private Boolean earnPoints;

  /**
   * <pre>
   * 字段名：订单新增积分值
   * 变量名：increased_points
   * 是否必填：是
   * 类型：int
   * 描述：
   *  顾客此笔交易新增的积分值
   *  示例值：100
   * </pre>
   */
  @SerializedName(value = "increased_points")
  private Integer increasedPoints;

  /**
   * <pre>
   * 字段名：积分更新时间
   * 变量名：points_update_time
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   *  为顾客此笔交易成功积分的时间
   *  示例值：2020-05-20T13:29:35.120+08:00
   * </pre>
   */
  @SerializedName(value = "points_update_time")
  private String pointsUpdateTime;

  /**
   * <pre>
   * 字段名：未获得积分的备注信息
   * 变量名：no_points_remarks
   * 是否必填：否
   * 类型：string[1,128]
   * 描述：
   *  当未获得积分时，提供未获得积分的原因等备注信息
   *  示例值：商品不参与积分活动
   * </pre>
   */
  @SerializedName(value = "no_points_remarks")
  private String noPointsRemarks;

  /**
   * <pre>
   * 字段名：顾客积分总额
   * 变量名：total_points
   * 是否必填：否
   * 类型：int
   * 描述：
   *  当前顾客积分总额
   *  示例值：888888
   * </pre>
   */
  @SerializedName(value = "total_points")
  private Integer totalPoints;
}
