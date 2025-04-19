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
public class ShopCategoryResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 3871098948660947422L;

  /** 类目列表 */
  @JsonProperty("cat_list")
  private List<ShopCategory> categories;

  /** 类目列表 */
  @JsonProperty("cat_list_v2")
  private List<ShopCategory> catListV2;
}
