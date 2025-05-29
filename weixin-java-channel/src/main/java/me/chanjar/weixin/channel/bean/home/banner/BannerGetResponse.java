package me.chanjar.weixin.channel.bean.home.banner;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 精选展位返回结果
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BannerGetResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -1563254921362215934L;

  /** 当前生效的展示位 */
  @JsonProperty("banner")
  private BannerInfo banner;

  /** 最近一次流程中的申请。不返回已生效或已撤销的申请 */
  @JsonProperty("apply")
  private BannerApplyInfo apply;
}
