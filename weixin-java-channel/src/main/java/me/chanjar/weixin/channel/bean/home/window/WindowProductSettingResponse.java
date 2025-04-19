package me.chanjar.weixin.channel.bean.home.window;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 主页商品配置列表
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WindowProductSettingResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 1L;

  /** 商品信息 */
  @JsonProperty("products")
  private List<WindowProductSetting> products;

  /** 本次翻页的上下文，用于请求下一页 */
  @JsonProperty("next_key")
  private String nextKey;

  /** 商品总数 */
  @JsonProperty("total_num")
  private Integer totalNum;
}
