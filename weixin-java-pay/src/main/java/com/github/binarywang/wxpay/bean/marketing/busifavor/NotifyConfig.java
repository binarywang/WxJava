package com.github.binarywang.wxpay.bean.marketing.busifavor;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 事件通知配置
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_1.shtml
 * </pre>
 *
 * @author yujam
 */
@Data
@NoArgsConstructor
public class NotifyConfig implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：事件通知appid
   * 变量名：coupon_image_url
   * 是否必填：否
   * 类型：string[1,64]
   * 描述：
   *  用于回调通知时，计算返回操作用户的openid（诸如领券用户），支持小程序or公众号的APPID；如该字段不填写，则回调通知中涉及到用户身份信息的openid与unionid都将为空。
   *  示例值：wx23232232323
   * </pre>
   */
  @SerializedName(value = "notify_appid")
  private String notifyAppId;
}
