package me.chanjar.weixin.channel.bean.compass.shop;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 获取电商概览数据响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ShopOverallResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 1632800741359642057L;

  /**
   * 电商概览数据
   */
  @JsonProperty("data")
  private ShopOverall data;

}
