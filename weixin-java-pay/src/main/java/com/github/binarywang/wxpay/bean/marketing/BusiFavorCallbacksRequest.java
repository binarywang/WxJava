package com.github.binarywang.wxpay.bean.marketing;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 设置商家券事件通知地址请求对象
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_7.shtml
 * </pre>
 *
 * @author yujam
 */
@Data
@NoArgsConstructor
public class BusiFavorCallbacksRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * <pre>* 字段名：商户号
   * 变量名：mchid
   * 是否必填：否
   * 类型：string[8,15]
   * 描述：
   * body 微信支付商户的商户号，由微信支付生成并下发，不填默认查询调用方商户的通知URL。 示例值：10000098
   * </pre>
   */
  @SerializedName(value = "mchid")
  private String mchid;

  /**
   * <pre>* 字段名：通知URL地址
   * 变量名：notify_url
   * 是否必填：是
   * 类型：string[10,256]
   * 描述：
   * body 商户提供的用于接收商家券事件通知的url地址，必须支持https。 示例值：https://pay.weixin.qq.com
   * </pre>
   */
  @SerializedName(value = "notify_url")
  private String notifyUrl;
}
