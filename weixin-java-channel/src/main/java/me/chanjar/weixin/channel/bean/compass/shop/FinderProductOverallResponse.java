package me.chanjar.weixin.channel.bean.compass.shop;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 带货达人详情 响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FinderProductOverallResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 6358992001065379269L;

  /** 带货达人详情 */
  @JsonProperty("data")
  private FinderGmvData data;

}
