package com.github.binarywang.wxpay.bean.ecommerce;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 合单支付 通知结果
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pay/combine/chapter3_7.shtml
 * </pre>
 */
@Data
@NoArgsConstructor
public class CombineTransactionsNotifyResult implements Serializable {

  private static final long serialVersionUID = -4710926828683593250L;
  /**
   * 源数据
   */
  private NotifyResponse rawData;

  /**
   * 解密后的数据
   */
  private CombineTransactionsResult result;

}
