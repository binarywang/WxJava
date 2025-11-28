package com.github.binarywang.wxpay.bean.ecommerce;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 普通支付 通知结果
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/doc/v3/merchant/4012068166
 * </pre>
 */
@Data
@NoArgsConstructor
public class PartnerTransactionsNotifyResult implements Serializable {
  private static final long serialVersionUID = -6602962275015706689L;
  /**
   * 源数据
   */
  private NotifyResponse rawData;

  /**
   * 解密后的数据
   */
  private PartnerTransactionsResult result;
}
