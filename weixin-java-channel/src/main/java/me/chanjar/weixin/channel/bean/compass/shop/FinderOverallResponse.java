package me.chanjar.weixin.channel.bean.compass.shop;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 带货数据概览 响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FinderOverallResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -4935555091396799318L;

  /**
   * 电商概览数据
   */
  @JsonProperty("data")
  private FinderOverallData data;
}
