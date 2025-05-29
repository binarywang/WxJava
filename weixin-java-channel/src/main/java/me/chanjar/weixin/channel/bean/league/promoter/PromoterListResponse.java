package me.chanjar.weixin.channel.bean.league.promoter;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 达人列表响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PromoterListResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 1411870432999885996L;
  /** 达人finder_id列表，待废除后续以promoter_ids为准 */
  @JsonProperty("finder_ids")
  private List<String> finderIds;

  /** 达人总数 */
  @JsonProperty("total_num")
  private Integer totalNum;

  /** 后面是否还有（true: 还有内容; false: 已结束）*/
  @JsonProperty("continue_flag")
  private Boolean continueFlag;

  /** 达人带货id列表 */
  @JsonProperty("promoter_ids")
  private List<String> promoterIds;
}
