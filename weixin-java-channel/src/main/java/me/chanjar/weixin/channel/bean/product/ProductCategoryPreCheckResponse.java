package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 发品前校验响应.
 *
 * @author GitHub Copilot
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductCategoryPreCheckResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 7136603000806024499L;

  /** 校验是否通过. */
  @JsonProperty("all_pass")
  private Boolean allPass;

  /** 失败原因列表. */
  @JsonProperty("fail_reasons")
  private List<String> failReasons;
}
