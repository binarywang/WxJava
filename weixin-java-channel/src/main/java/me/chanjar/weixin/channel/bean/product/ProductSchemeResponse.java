package me.chanjar.weixin.channel.bean.product;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/** 获取商品移动应用跳转 scheme 码响应. */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductSchemeResponse extends WxChannelBaseResponse {
  private static final long serialVersionUID = 7310433919100539990L;

  private String openlink;
}
