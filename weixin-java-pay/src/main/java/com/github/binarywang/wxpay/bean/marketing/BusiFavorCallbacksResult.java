package com.github.binarywang.wxpay.bean.marketing;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 设置商家券事件通知地址返回对象
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_7.shtml
 * </pre>
 *
 * @author yujam
 */
@Data
@NoArgsConstructor
public class BusiFavorCallbacksResult implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>* 字段名：修改时间
   * 变量名：update_time
   * 是否必填：否
   * 类型：string[1,32]
   * 描述：
   * 修改时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日 13点29分35秒。 示例值：2015-05-20T13:29:35+08:00
   * </pre>
   */
  @SerializedName(value = "update_time")
  private String updateTime;

  /**
   * <pre>* 字段名：通知URL地址
   * 变量名：notify_url
   * 是否必填：是
   * 类型：string[10,256]
   * 描述：
   * 商户提供的用于接收商家券事件通知的url地址，必须支持https。 示例值：https://pay.weixin.qq.com
   * </pre>
   */
  @SerializedName(value = "notify_url")
  private String notifyUrl;

  /**
   * <pre>* 字段名：商户号
   * 变量名：mchid
   * 是否必填：是
   * 类型：string[8,15]
   * 描述：
   * 微信支付商户的商户号，由微信支付生成并下发。 示例值：10000098
   * </pre>
   */
  @SerializedName(value = "mchid")
  private String mchid;
}
