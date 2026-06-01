package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 获取商品移动应用跳转 scheme 码响应.
 *
 * @author GitHub Copilot
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductSchemeResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 7310433919100539990L;

  /** 商品跳转scheme码. */
  private String openlink;
}
