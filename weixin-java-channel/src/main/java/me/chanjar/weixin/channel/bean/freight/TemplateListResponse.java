package me.chanjar.weixin.channel.bean.freight;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 运费模板 列表 响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class TemplateListResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 5375602442595264719L;
  /** 运费模板 id 列表 */
  @JsonProperty("template_id_list")
  private List<String> ids;
}
