package me.chanjar.weixin.channel.bean.home.tree;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分类审核结果
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class TreeAuditResultDetail implements Serializable {

  private static final long serialVersionUID = -6085892397971684732L;

  /** 该分类ID的审核结果 */
  @JsonProperty("level_id")
  private Integer level_id;

  /** 审核结果枚举。1:不通过；2:通过 */
  @JsonProperty("result_code")
  private Integer result_code;

}
