package com.binarywang.spring.starter.wxjava.pay.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信支付多公众号关联配置属性类.
 *
 * @author Binary Wang
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(WxPayMultiProperties.PREFIX)
public class WxPayMultiProperties implements Serializable {
  private static final long serialVersionUID = -8015955705346835955L;
  public static final String PREFIX = "wx.pay";

  /**
   * 多个公众号的配置信息，key 可以是 appId 或自定义的标识.
   */
  private Map<String, WxPaySingleProperties> configs = new HashMap<>();
}
