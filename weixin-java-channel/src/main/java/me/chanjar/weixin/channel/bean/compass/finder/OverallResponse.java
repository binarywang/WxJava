package me.chanjar.weixin.channel.bean.compass.finder;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 获取电商概览数据响应
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OverallResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 6350218415876820956L;

  /**
   * 电商概览数据
   */
  @JsonProperty("data")
  private Overall data;

}
