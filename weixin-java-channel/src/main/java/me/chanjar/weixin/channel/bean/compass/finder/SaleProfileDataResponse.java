package me.chanjar.weixin.channel.bean.compass.finder;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 获取带货人群数据响应
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SaleProfileDataResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = - 6409722880191468272L;

  /**
   * 带货人群数据
   */
  @JsonProperty("data")
  private SaleProfileData data;

}
