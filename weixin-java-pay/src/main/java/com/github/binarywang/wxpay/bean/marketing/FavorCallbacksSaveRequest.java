package com.github.binarywang.wxpay.bean.marketing;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 设置消息通知地址
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/marketing/convention/chapter3_12.shtml
 * </pre>
 *
 * @author thinsstar
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FavorCallbacksSaveRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：商户号
   * 变量名：mchid
   * 是否必填：是
   * 类型：string[1,20]
   * 描述：
   *  微信支付商户号。
   *  示例值：9856888
   * </pre>
   */
  @SerializedName(value = "mchid")
  private String mchid;

  /**
   * <pre>
   * 字段名：通知url地址
   * 变量名：notify_url
   * 是否必填：是
   * 类型：string[1,256]
   * 描述：
   *  支付通知商户url地址。
   *  示例值：https://pay.weixin.qq.com
   * </pre>
   */
  @SerializedName(value = "notify_url")
  private String notifyUrl;

  /**
   * <pre>
   * 字段名：回调开关
   * 变量名：switch
   * 是否必填：否
   * 类型：bool
   * 描述：
   *  如果商户不需要再接收营销事件通知，可通过该开关关闭。枚举值：
   *  true：开启推送
   *  false：停止推送
   *  示例值：true
   * </pre>
   */
  @SerializedName(value = "switch")
  private Boolean switchBool;
}
