package com.github.binarywang.wxpay.bean.marketing;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 根据过滤条件查询用户券请求对象
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_4.shtml
 * </pre>
 *
 * @author yujam
 */
@Data
@NoArgsConstructor
public class BusiFavorQueryUserCouponsRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>* 字段名：用户标识
   * 变量名：openid
   * 是否必填：是
   * 类型：string[1,128]
   * 描述：
   * path Openid信息，用户在appid下的唯一标识。 示例值：2323dfsdf342342
   * </pre>
   */
  @SerializedName(value = "openid")
  private String openid;

  /**
   * <pre>* 字段名：公众账号ID
   * 变量名：appid
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   * query 支持传入与当前调用接口商户号有绑定关系的appid。支持小程序appid与公众号appid。 示例值：wx233544546545989
   * </pre>
   */
  @SerializedName(value = "appid")
  private String appid;

  /**
   * <pre>* 字段名：批次号
   * 变量名：stock_id
   * 是否必填：否
   * 类型：string[1,20]
   * 描述：
   * query 微信为每个商家券批次分配的唯一ID，是否指定批次号查询。 示例值：9865000
   * </pre>
   */
  @SerializedName(value = "stock_id")
  private String stockId;

  /**
   * <pre>* 字段名：券状态
   * 变量名：coupon_state
   * 是否必填：否
   * 类型：string[1,16]
   * 描述：
   * query 券状态 枚举值： SENDED：可用 USED：已核销 EXPIRED：已过期 示例值：SENDED
   * </pre>
   */
  @SerializedName(value = "coupon_state")
  private String couponState;

  /**
   * <pre>* 字段名：创建批次的商户号
   * 变量名：creator_merchant
   * 是否必填：否
   * 类型：string[1,32]
   * 描述：
   * query 批次创建方商户号 示例值：1000000001
   * </pre>
   */
  @SerializedName(value = "creator_merchant")
  private String creatorMerchant;

  /**
   * <pre>* 字段名：批次归属商户号
   * 变量名：belong_merchant
   * 是否必填：否
   * 类型：string[8,15]
   * 描述：
   * query 批次归属商户号 示例值：1000000002
   * </pre>
   */
  @SerializedName(value = "belong_merchant")
  private String belongMerchant;

  /**
   * <pre>* 字段名：批次发放商户号
   * 变量名：sender_merchant
   * 是否必填：否
   * 类型：string[1,32]
   * 描述：
   * query 批次发放商户号 示例值：1000000003
   * </pre>
   */
  @SerializedName(value = "sender_merchant")
  private String senderMerchant;

  /**
   * <pre>* 字段名：分页页码
   * 变量名：offset
   * 是否必填：否
   * 类型：int
   * 描述：
   * query 分页页码 示例值：0
   * </pre>
   */
  @SerializedName(value = "offset")
  private Integer offset;

  /**
   * <pre>* 字段名：分页大小
   * 变量名：limit
   * 是否必填：否
   * 类型：int
   * 描述：
   * query 分页大小 示例值：20
   * </pre>
   */
  @SerializedName(value = "limit")
  private Integer limit;
}
