package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/** 发品前校验响应. */
@Data
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class ProductCategoryPreCheckResponse extends WxChannelBaseResponse {
  private static final long serialVersionUID = 7136603000806024499L;

  @JsonProperty("all_pass")
  private Boolean allPass;
  @JsonProperty("fail_reasons")
  private List<String> failReasons;
}
