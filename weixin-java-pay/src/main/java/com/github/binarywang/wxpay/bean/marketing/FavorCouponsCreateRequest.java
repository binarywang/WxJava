package com.github.binarywang.wxpay.bean.marketing;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 发放代金券
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/marketing/convention/chapter3_2.shtml
 * </pre>
 *
 * @author thinsstar
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FavorCouponsCreateRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：批次号
   * 变量名：stock_id
   * 是否必填：是
   * 类型：string[1,20]
   * 描述：
   *  微信为每个批次分配的唯一id。
   *  校验规则：必须为代金券（全场券或单品券）批次号，不支持立减与折扣。
   *  示例值：9856000
   * </pre>
   */
  @SerializedName(value = "stock_id")
  private String stockId;

  /**
   * <pre>
   * 字段名：商户单据号
   * 变量名：out_request_no
   * 是否必填：是
   * 类型：string[1,128]
   * 描述：
   *  商户此次发放凭据号（格式：商户id+日期+流水号），可包含英文字母，数字，|，_，*，-等内容，不允许出现其他不合法符号，商户侧需保持唯一性。
   *  示例值： 89560002019101000121
   * </pre>
   */
  @SerializedName(value = "out_request_no")
  private String outRequestNo;

  /**
   * <pre>
   * 字段名：公众账号ID
   * 变量名：appid
   * 是否必填：是
   * 类型：string[1,128]
   * 描述：
   *  微信为发券方商户分配的公众账号ID，接口传入的所有appid应该为公众号的appid或者小程序的appid（在mp.weixin.qq.com申请的），不能为APP的appid（在open.weixin.qq.com申请的）。。
   *  校验规则：
   *  1、该appid需要与接口传入中的openid有对应关系；
   *  2、该appid需要与调用接口的商户号（即请求头中的商户号）有绑定关系，若未绑定，可参考该指引完成绑定（商家商户号与AppID账号关联管理）
   *  示例值：wx233544546545989
   * </pre>
   */
  @SerializedName(value = "appid")
  private String appid;

  /**
   * <pre>
   * 字段名：创建批次的商户号
   * 变量名：stock_creator_mchid
   * 是否必填：是
   * 类型：string[1,20]
   * 描述：
   *  批次创建方商户号。
   *  示例值：8956000
   * </pre>
   */
  @SerializedName(value = "stock_creator_mchid")
  private String stockCreatorMchid;

  /**
   * <pre>
   * 字段名：指定面额发券，面额
   * 变量名：coupon_value
   * 是否必填：否
   * 类型：uint64
   * 描述：
   *  指定面额发券场景，券面额，其他场景不需要填，单位：分。
   *  校验规则：仅在发券时指定面额及门槛的场景才生效，常规发券场景请勿传入该信息。
   *  示例值：100
   * </pre>
   */
  @SerializedName(value = "coupon_value")
  private Integer couponValue;

  /**
   * <pre>
   * 字段名：指定面额发券，券门槛
   * 变量名：coupon_minimum
   * 是否必填：是
   * 类型：uint64
   * 描述：
   *  指定面额发券批次门槛，其他场景不需要，单位：分。
   *  校验规则：仅在发券时指定面额及门槛的场景才生效，常规发券场景请勿传入该信息。
   *  示例值：100
   * </pre>
   */
  @SerializedName(value = "coupon_minimum")
  private Integer couponMinimum;
}
