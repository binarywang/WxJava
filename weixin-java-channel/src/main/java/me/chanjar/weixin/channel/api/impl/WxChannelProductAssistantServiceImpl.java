package me.chanjar.weixin.channel.api.impl;

import me.chanjar.weixin.channel.api.WxChannelProductAssistantService;

/**
 * 微信小店商品辅助功能服务实现。
 */
public class WxChannelProductAssistantServiceImpl implements WxChannelProductAssistantService {

  /** 微信商店服务 */
  private final BaseWxChannelServiceImpl<?, ?> shopService;

  public WxChannelProductAssistantServiceImpl(BaseWxChannelServiceImpl<?, ?> shopService) {
    this.shopService = shopService;
  }
}
