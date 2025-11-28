package com.github.binarywang.wxpay.bean.ecommerce;

import com.github.binarywang.wxpay.v3.SpecEncrypt;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 请求分账 对象
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/profitsharing/chapter3_1.shtml
 * </pre>
 *
 * @author f00lish
 * created on  2020/09/12
 */
@Data
@NoArgsConstructor
public class ProfitSharingRequest implements Serializable {

  private static final long serialVersionUID = -8662837652326828377L;
  /**
   * <pre>
   * 字段名：公众账号ID
   * 变量名：appid
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  微信分配的公众账号ID。
   *  示例值：wx8888888888888888
   * </pre>
   */
  @SerializedName(value = "appid")
  private String appid;

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
   * 字段名：分账接收方列表
   * 变量名：receivers
   * 是否必填：是
   * 类型：array
   * 描述：
   *  分账接收方列表，支持设置出资商户作为分账接收方，单次分账最多可有5个分账接收方
   * </pre>
   */
  @SerializedName(value = "receivers")
  @SpecEncrypt
  private List<Receiver> receivers;

  /**
   * <pre>
   * 字段名：是否分账完成
   * 变量名：finish
   * 是否必填：是
   * 类型：bool
   * 描述：
   *  是否完成分账
   *  1、如果为true，该笔订单剩余未分账的金额会解冻回电商平台二级商户；
   *  2、如果为false，该笔订单剩余未分账的金额不会解冻回电商平台二级商户，可以对该笔订单再次进行分账。
   *  示例值：true
   * </pre>
   */
  @SerializedName(value = "finish")
  private Boolean finish;

  @Data
  @NoArgsConstructor
  public static class Receiver implements Serializable {

    private static final long serialVersionUID = 8995144356011793136L;

    /**
     * <pre>
     * 字段名：分账接收方类型
     * 变量名：type
     * 是否必填：否
     * 类型：string（32）
     * 描述：
     *  分账接收方类型，枚举值：
     *  MERCHANT_ID：商户
     *  PERSONAL_OPENID：个人
     *  示例值：MERCHANT_ID
     * </pre>
     */
    @SerializedName(value = "type")
    private String type;

    /**
     * <pre>
     * 字段名：分账接收方账号
     * 变量名：receiver_account
     * 是否必填：是
     * 类型：string（32）
     * 描述：
     *  分账接收方账号：
     *  类型是MERCHANT_ID时，是商户ID
     *  类型是PERSONAL_OPENID时，是个人openid，openid获取方法 https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/guide/chapter2_1.shtml#menu1
     *  示例值：1900000109
     * </pre>
     */
    @SerializedName(value = "receiver_account")
    private String receiverAccount;

    /**
     * <pre>
     * 字段名：分账金额
     * 变量名：amount
     * 是否必填：是
     * 类型：int
     * 描述：
     *  分账金额，单位为分，只能为整数，不能超过原订单支付金额及最大分账比例金额。
     *  示例值：190
     * </pre>
     */
    @SerializedName(value = "amount")
    private Integer amount;

    /**
     * <pre>
     * 字段名：分账描述
     * 变量名：description
     * 是否必填：是
     * 类型：string（180）
     * 描述：
     *  分账的原因描述，分账账单中需要体现。
     *  示例值：分给商户1900000109
     * </pre>
     */
    @SerializedName(value = "description")
    private String description;

    /**
     * <pre>
     * 字段名：分账个人姓名
     * 变量名：receiver_name
     * 是否必填：否
     * 类型：string（10240）
     * 描述：
     *  可选项，在接收方类型为个人的时可选填，若有值，会检查与 receiver_name 是否实名匹配，不匹配会拒绝分账请求
     *  1、分账接收方类型是PERSONAL_OPENID时，是个人姓名的密文（选传，传则校验） 此字段的加密方法详见：敏感信息加密说明
     *  2、使用微信支付平台证书中的公钥
     *  3、使用RSAES-OAEP算法进行加密
     *  4、将请求中HTTP头部的Wechatpay-Serial设置为证书序列号
     *  示例值：hu89ohu89ohu89o
     * </pre>
     */
    @SerializedName(value = "receiver_name")
    @SpecEncrypt
    private String receiverName;

  }
}
