package com.github.binarywang.wxpay.bean.ecommerce;

import com.github.binarywang.wxpay.v3.SpecEncrypt;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 添加分账接收方API
 * <pre>
 * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/profitsharing/chapter3_7.shtml
 * </pre>
 */
@Data
@NoArgsConstructor
public class ProfitSharingReceiverRequest implements Serializable {

  /**
   * <pre>
   * 字段名：公众账号ID
   * 变量名：appid
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  电商平台的appid（公众号APPID或者小程序APPID）
   * 示例值：wx8888888888888888
   * </pre>
   */
  @SerializedName(value = "appid")
  private String appid;

  /**
   * <pre>
   * 字段名：接收方类型
   * 变量名：type
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  分账接收方的类型，枚举值：
   *    MERCHANT_ID：商户
   *    PERSONAL_OPENID：个人
   * 示例值：MERCHANT_ID
   * </pre>
   */
  @SerializedName(value = "type")
  private String type;

  /**
   * <pre>
   * 字段名：接收方账号
   * 变量名：account
   * 是否必填：是
   * 类型：string（64）
   * 描述：
   *  分账接收方的账号
   *    类型是MERCHANT_ID时，是商户号
   *    类型是PERSONAL_OPENID时，是个人openid，openid获取方法
   * 示例值：190001001
   * </pre>
   */
  @SerializedName(value = "account")
  private String account;

  /**
   * <pre>
   * 字段名：接收方名称
   * 变量名：name
   * 是否必填：是
   * 类型：string（256）
   * 描述：
   *  分账接收方的名称，当type为MERCHANT_ID时，接收方名称是商户全称。
   * 示例值：张三网络公司
   * </pre>
   */
  @SerializedName(value = "name")
  private String name;

  /**
   * <pre>
   * 字段名：接收方名称的密文
   * 变量名：encrypted_name
   * 是否必填：否
   * 类型：string（10240）
   * 描述：
   *  1、分账接收方类型是PERSONAL_OPENID时，是个人姓名的密文（选传，传则校验）
   * 此字段的加密的方式为：
   *    2、使用微信支付平台证书中的公钥
   *    3、使用RSAES-OAEP算法进行加密
   *    4、将请求中HTTP头部的Wechatpay-Serial设置为证书序列号
   *  字段加密: 使用APIv3定义的方式加密
   * 示例值：hu89ohu89ohu89o
   * </pre>
   */
  @SpecEncrypt
  @SerializedName(value = "encrypted_name")
  private String encryptedName;

  /**
   * <pre>
   * 字段名：与分账方的关系类型
   * 变量名：relation_type
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  子商户与接收方的关系。
   * 枚举值：
   *    SUPPLIER：供应商
   *    DISTRIBUTOR：分销商
   *    SERVICE_PROVIDER：服务商
   *    PLATFORM：平台
   *    OTHERS：其他
   * 示例值：SUPPLIER
   * </pre>
   */
  @SerializedName(value = "relation_type")
  private String relationType;

}
