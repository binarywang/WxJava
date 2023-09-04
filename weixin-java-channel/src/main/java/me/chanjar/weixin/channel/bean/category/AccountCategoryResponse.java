package me.chanjar.weixin.channel.bean.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 分类响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AccountCategoryResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 3486089711447908477L;

  /** 类目列表 */
  @JsonProperty("data")
  private List<ShopCategory> categories;
}
