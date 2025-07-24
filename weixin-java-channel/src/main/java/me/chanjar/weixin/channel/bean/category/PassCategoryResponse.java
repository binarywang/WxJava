package me.chanjar.weixin.channel.bean.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 审核通过的分类和资质信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PassCategoryResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -3674591447273025743L;

  /** 类目和资质信息列表 */
  @JsonProperty("list")
  private List<PassCategoryInfo> list;
}
