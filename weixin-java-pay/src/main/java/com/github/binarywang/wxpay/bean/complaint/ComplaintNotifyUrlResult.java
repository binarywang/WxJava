package com.github.binarywang.wxpay.bean.complaint;


import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * 微信消费者投诉2.0
 * 投诉通知地址返回的实体
 *
 * @author <a href="https://gitee.com/jeequan/jeepay">jmdhappy</a>
 * created on  2022-3-19
 */
@Data
public class ComplaintNotifyUrlResult implements Serializable {

  private static final long serialVersionUID = -6201692411535927502L;

  /**
   * <pre>
   * 字段名：商户号
   * 是否必填：是
   * 描述：返回创建回调地址的商户号，由微信支付生成并下发。
   * </pre>
   */
  @SerializedName("mchid")
  private String mchid;

  /**
   * <pre>
   * 字段名：通知地址
   * 是否必填：是
   * 描述：通知地址，仅支持https。
   * </pre>
   */
  @SerializedName("url")
  private String url;

}
