package com.binarywang.spring.starter.wxjava.pay.service;

import com.binarywang.spring.starter.wxjava.pay.properties.WxPayMultiProperties;
import com.binarywang.spring.starter.wxjava.pay.properties.WxPaySingleProperties;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 微信支付多服务管理实现类.
 *
 * @author Binary Wang
 */
@Slf4j
public class WxPayMultiServicesImpl implements WxPayMultiServices {
  private final Map<String, WxPayService> services = new ConcurrentHashMap<>();
  private final WxPayMultiProperties wxPayMultiProperties;

  public WxPayMultiServicesImpl(WxPayMultiProperties wxPayMultiProperties) {
    this.wxPayMultiProperties = wxPayMultiProperties;
  }

  @Override
  public WxPayService getWxPayService(String configKey) {
    if (StringUtils.isBlank(configKey)) {
      log.warn("配置标识为空，无法获取WxPayService");
      return null;
    }

    WxPayService wxPayService = services.get(configKey);
    if (wxPayService == null) {
      synchronized (this) {
        wxPayService = services.get(configKey);
        if (wxPayService == null) {
          WxPaySingleProperties properties = wxPayMultiProperties.getConfigs().get(configKey);
          if (properties == null) {
            log.warn("未找到配置标识为[{}]的微信支付配置", configKey);
            return null;
          }
          wxPayService = this.buildWxPayService(properties);
          services.put(configKey, wxPayService);
        }
      }
    }
    return wxPayService;
  }

  @Override
  public void removeWxPayService(String configKey) {
    services.remove(configKey);
  }

  /**
   * 根据配置构建 WxPayService.
   *
   * @param properties 单个配置属性
   * @return WxPayService
   */
  private WxPayService buildWxPayService(WxPaySingleProperties properties) {
    WxPayServiceImpl wxPayService = new WxPayServiceImpl();
    WxPayConfig payConfig = new WxPayConfig();

    payConfig.setAppId(StringUtils.trimToNull(properties.getAppId()));
    payConfig.setMchId(StringUtils.trimToNull(properties.getMchId()));
    payConfig.setMchKey(StringUtils.trimToNull(properties.getMchKey()));
    payConfig.setSubAppId(StringUtils.trimToNull(properties.getSubAppId()));
    payConfig.setSubMchId(StringUtils.trimToNull(properties.getSubMchId()));
    payConfig.setKeyPath(StringUtils.trimToNull(properties.getKeyPath()));
    payConfig.setUseSandboxEnv(properties.isUseSandboxEnv());
    payConfig.setNotifyUrl(StringUtils.trimToNull(properties.getNotifyUrl()));
    payConfig.setRefundNotifyUrl(StringUtils.trimToNull(properties.getRefundNotifyUrl()));

    // 以下是apiv3以及支付分相关
    payConfig.setServiceId(StringUtils.trimToNull(properties.getServiceId()));
    payConfig.setPayScoreNotifyUrl(StringUtils.trimToNull(properties.getPayScoreNotifyUrl()));
    payConfig.setPayScorePermissionNotifyUrl(StringUtils.trimToNull(properties.getPayScorePermissionNotifyUrl()));
    payConfig.setPrivateKeyPath(StringUtils.trimToNull(properties.getPrivateKeyPath()));
    payConfig.setPrivateCertPath(StringUtils.trimToNull(properties.getPrivateCertPath()));
    payConfig.setCertSerialNo(StringUtils.trimToNull(properties.getCertSerialNo()));
    payConfig.setApiV3Key(StringUtils.trimToNull(properties.getApiv3Key()));
    payConfig.setPublicKeyId(StringUtils.trimToNull(properties.getPublicKeyId()));
    payConfig.setPublicKeyPath(StringUtils.trimToNull(properties.getPublicKeyPath()));
    payConfig.setApiHostUrl(StringUtils.trimToNull(properties.getApiHostUrl()));
    payConfig.setStrictlyNeedWechatPaySerial(properties.isStrictlyNeedWechatPaySerial());
    payConfig.setFullPublicKeyModel(properties.isFullPublicKeyModel());

    wxPayService.setConfig(payConfig);
    return wxPayService;
  }
}
