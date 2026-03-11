package me.chanjar.weixin.channel.bean.compass.shop;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 获取授权视频号列表 响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FinderAuthListResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -3215073536002857589L;

  /** 主营视频号id */
  @JsonProperty("main_finder_id")
  private String mainFinderId;

  /** 授权视频号id列表 */
  @JsonProperty("authorized_finder_id_list")
  private List<String> authorizedFinderIdList;

}
