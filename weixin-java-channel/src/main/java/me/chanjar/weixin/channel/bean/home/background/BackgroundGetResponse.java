package me.chanjar.weixin.channel.bean.home.background;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 背景图返回结果
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BackgroundGetResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -9158761351220981959L;

  /** 当前生效的背景图片url */
  @JsonProperty("img_url")
  private String imgUrl;

  /** 背景图审核信息 */
  @JsonProperty("apply")
  private BackgroundApplyResult apply;
}
