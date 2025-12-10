package com.binarywang.solon.wxjava.pay.config;

import com.binarywang.solon.wxjava.pay.properties.WxPayProperties;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Condition;
import org.noear.solon.annotation.Configuration;

/**
 * <pre>
 *  微信支付自动配置
 *  Created by BinaryWang on 2019/4/17.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Configuration
@Condition(
  onProperty = "${wx.pay.enabled:true} = true",
  onClass=WxPayService.class
)
public class WxPayAutoConfiguration {
  private WxPayProperties properties;

  public WxPayAutoConfiguration(WxPayProperties properties) {
    this.properties = properties;
  }

  /**
   * 构造微信支付服务对象.
   *
   * @return 微信支付service
   */
  @Bean
  @Condition(onMissingBean=WxPayService.class)
  public WxPayService wxPayService() {
    final WxPayServiceImpl wxPayService = new WxPayServiceImpl();
    WxPayConfig payConfig = new WxPayConfig();
    payConfig.setAppId(StringUtils.trimToNull(this.properties.getAppId()));
    payConfig.setMchId(StringUtils.trimToNull(this.properties.getMchId()));
    payConfig.setMchKey(StringUtils.trimToNull(this.properties.getMchKey()));
    payConfig.setSubAppId(StringUtils.trimToNull(this.properties.getSubAppId()));
    payConfig.setSubMchId(StringUtils.trimToNull(this.properties.getSubMchId()));
    payConfig.setKeyPath(StringUtils.trimToNull(this.properties.getKeyPath()));
    payConfig.setUseSandboxEnv(this.properties.isUseSandboxEnv());
    payConfig.setNotifyUrl(StringUtils.trimToNull(this.properties.getNotifyUrl()));
    //以下是apiv3以及支付分相关
    payConfig.setServiceId(StringUtils.trimToNull(this.properties.getServiceId()));
    payConfig.setPayScoreNotifyUrl(StringUtils.trimToNull(this.properties.getPayScoreNotifyUrl()));
    payConfig.setPayScorePermissionNotifyUrl(StringUtils.trimToNull(this.properties.getPayScorePermissionNotifyUrl()));
    payConfig.setPrivateKeyPath(StringUtils.trimToNull(this.properties.getPrivateKeyPath()));
    payConfig.setPrivateCertPath(StringUtils.trimToNull(this.properties.getPrivateCertPath()));
    payConfig.setCertSerialNo(StringUtils.trimToNull(this.properties.getCertSerialNo()));
    payConfig.setApiV3Key(StringUtils.trimToNull(this.properties.getApiv3Key()));
    payConfig.setPublicKeyId(StringUtils.trimToNull(this.properties.getPublicKeyId()));
    payConfig.setPublicKeyPath(StringUtils.trimToNull(this.properties.getPublicKeyPath()));
    payConfig.setApiHostUrl(StringUtils.trimToNull(this.properties.getApiHostUrl()));
    payConfig.setStrictlyNeedWechatPaySerial(this.properties.isStrictlyNeedWechatPaySerial());
    payConfig.setFullPublicKeyModel(this.properties.isFullPublicKeyModel());

    wxPayService.setConfig(payConfig);
    return wxPayService;
  }

}
