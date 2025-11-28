package com.github.binarywang.wxpay.bean.notify;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

/**
 * 微信支付订单和退款的异步通知，V3版本共用的响应类.
 * https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_1_11.shtml
 *
 * @author <a href="https://github.com/0katekate0">Wang_Wong</a>
 * created on  2022-08-15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxPayNotifyV3Response {

  private static final transient String SUCCESS = "SUCCESS";
  private static final transient String FAIL = "FAIL";

  private String code;
  private String message;

  /**
   * 返回成功
   *
   * @param msg
   * @return
   */
  public static String success(String msg) {
    WxPayNotifyV3Response response = new WxPayNotifyV3Response(SUCCESS, msg);
    return WxGsonBuilder.create().toJson(response);
  }

  /**
   * 返回失败
   *
   * @param msg 返回信息，如非空，为错误原因
   * @return
   */
  public static String fail(String msg) {
    WxPayNotifyV3Response response = new WxPayNotifyV3Response(FAIL, msg);
    return WxGsonBuilder.create().toJson(response);
  }

}
