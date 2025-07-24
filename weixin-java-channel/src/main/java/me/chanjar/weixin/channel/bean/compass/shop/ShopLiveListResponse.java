package me.chanjar.weixin.channel.bean.compass.shop;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 店铺开播列表 响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ShopLiveListResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -7110751559923117330L;

  /** 店铺开播列表 */
  @JsonProperty("live_list")
  private List<ShopLiveData> liveList;

}
