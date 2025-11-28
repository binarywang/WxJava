package com.github.binarywang.wxpay.bean.marketing;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 根据商户号查用户的券
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/marketing/convention/chapter3_9.shtml
 * </pre>
 *
 * @author thinsstar
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FavorCouponsQueryRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：用户标识
   * 变量名：openid
   * 是否必填：是
   * 类型：string[1,128]
   * 描述：
   *  用户在商户appid 下的唯一标识。
   *  示例值：2323dfsdf342342
   * </pre>
   */
  @SerializedName(value = "openid")
  private String openid;

  /**
   * <pre>
   * 字段名：公众账号ID
   * 变量名：appid
   * 是否必填：是
   * 类型：string[1,128]
   * 描述：
   *  微信为发券方商户分配的公众账号ID，接口传入的所有appid应该为公众号的appid（在mp.weixin.qq.com申请的），不能为APP的appid（在open.weixin.qq.com申请的）。
   *  示例值：wx233544546545989
   * </pre>
   */
  @SerializedName(value = "appid")
  private String appid;

  /**
   * <pre>
   * 字段名：批次号
   * 变量名：stock_id
   * 是否必填：否
   * 类型：string[1,20]
   * 描述：
   *  批次号，是否指定批次号查询，填写available_mchid，该字段不生效。
   *  示例值：9865000
   * </pre>
   */
  @SerializedName(value = "stock_id")
  private String stockId;

  /**
   * <pre>
   * 字段名：券状态
   * 变量名：status
   * 是否必填：否
   * 类型：string[1,6]
   * 描述：
   *  代金券状态：
   *  SENDED：可用
   *  USED：已实扣
   *  填写available_mchid，该字段不生效。
   *  示例值：USED
   * </pre>
   */
  @SerializedName(value = "status")
  private String status;

  /**
   * <pre>
   * 字段名：创建批次的商户号
   * 变量名：creator_mchid
   * 是否必填：否
   * 类型：string[1,20]
   * 描述：
   *  批次创建方商户号。
   *  示例值：9865002
   * </pre>
   */
  @SerializedName(value = "creator_mchid")
  private String creatorMchid;

  /**
   * <pre>
   * 字段名：批次发放商户号
   * 变量名：sender_mchid
   * 是否必填：否
   * 类型：string[1,20]
   * 描述：
   *  批次创建方商户号。
   *  示例值：9865002
   * </pre>
   */
  @SerializedName(value = "sender_mchid")
  private String senderMchid;

  /**
   * <pre>
   * 字段名：可用商户号
   * 变量名：available_mchid
   * 是否必填：否
   * 类型：string[1,20]
   * 描述：
   *  批次创建方商户号。
   *  示例值：9865002
   * </pre>
   */
  @SerializedName(value = "available_mchid")
  private String availableMchid;

  /**
   * <pre>
   * 字段名：分页页码
   * 变量名：offset
   * 是否必填：是
   * 类型：uint32
   * 描述：
   *  页码从0开始，默认第0页。
   *  示例值：1
   * </pre>
   */
  @SerializedName(value = "offset")
  private Integer offset;

  /**
   * <pre>
   * 字段名：分页大小
   * 变量名：limit
   * 是否必填：是
   * 类型：uint32
   * 描述：
   *  分页大小，最大10。
   *  示例值：8
   * </pre>
   */
  @SerializedName(value = "limit")
  private Integer limit;
}
