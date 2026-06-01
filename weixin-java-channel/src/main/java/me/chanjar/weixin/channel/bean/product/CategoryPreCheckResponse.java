package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 发品前校验 响应
 *
 * @author <a href="https://github.com/features/copilot">GitHub Copilot</a>
 * @see <a href="https://developers.weixin.qq.com/doc/store/shop/API/channels-shop-product/shop/api_categoryprecheck.html">发品前校验</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CategoryPreCheckResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -1L;

  /** 检查类目是否可用。true: 类目正常；false: 类目不可用 */
  @JsonProperty("all_pass")
  private Boolean allPass;

  /** 校验不通过的原因列表 */
  @JsonProperty("fail_reasons")
  private List<String> failReasons;
}
