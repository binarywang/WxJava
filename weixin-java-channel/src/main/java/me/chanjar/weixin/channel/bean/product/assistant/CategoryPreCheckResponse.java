package me.chanjar.weixin.channel.bean.product.assistant;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 发品前校验响应。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class CategoryPreCheckResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 8912798390684239592L;

  /** 是否全部校验通过。 */
  @JsonProperty("all_pass")
  private Boolean allPass;

  /** 校验不通过的原因。 */
  @JsonProperty("fail_reasons")
  private List<String> failReasons;
}
