package com.github.binarywang.wxpay.bean.notify;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 微信通知接口头部信息，需要做签名验证
 * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wechatpay/wechatpay4_1.shtml
 *
 * @author thinstar
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignatureHeader implements Serializable {
  private static final long serialVersionUID = -1L;
  /**
   * 时间戳
   */
  private String timeStamp;
  /**
   * 随机串
   */
  private String nonce;
  /**
   * 已签名字符串
   */
  private String signature;
  /**
   * 证书序列号
   */
  private String serial;
}
