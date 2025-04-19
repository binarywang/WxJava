package me.chanjar.weixin.channel.bean.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 分类资质响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CategoryQualificationResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -7869091908852685830L;

  @JsonProperty("cats")
  private List<CategoryAndQualificationList> list;

  @JsonProperty("cats_v2")
  private List<CategoryAndQualificationList> catsV2;

}
