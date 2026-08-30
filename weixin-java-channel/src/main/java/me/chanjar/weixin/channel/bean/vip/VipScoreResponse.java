package me.chanjar.weixin.channel.bean.vip;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * @author : zhenyun.su
 * @since : 2023/10/8
 */

@Data
@NoArgsConstructor
@Deprecated
public class VipScoreResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -7252972818862693546L;
  @JsonProperty("info")
  private ScoreInfo scoreInfo;
}
