package me.chanjar.weixin.channel.bean.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 资质信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class QualificationInfo implements Serializable {

  /** 资质ID */
  @JsonProperty("qua_id")
  private String id;

  /** 是否需要申请 */
  @JsonProperty("need_to_apply")
  private Boolean needToApply;

  /** 资质信息 */
  @JsonProperty("tips")
  private String tips;

  /** 该类目申请的时候是否一定要提交资质 */
  @JsonProperty("mandatory")
  private Boolean mandatory;

  /** 资质名称 */
  @JsonProperty("name")
  private String name;
}
