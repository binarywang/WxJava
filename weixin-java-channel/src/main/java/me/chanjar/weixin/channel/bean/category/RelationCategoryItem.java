package me.chanjar.weixin.channel.bean.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 店铺类目权限列表项
 *
 * @author <a href="https://gitee.com/cchengg">chucheng</a>
 */
@Data
@NoArgsConstructor
public class RelationCategoryItem implements Serializable {

  /** 类目id */
  @JsonProperty("id")
  private Long id;

  /** 类目状态, 1生效中，2已失效 */
  @JsonProperty("status")
  private Integer status;

  /** 失效原因 */
  @JsonProperty("uneffective_reason")
  private String uneffectiveReason;

  /** 生效时间 */
  @JsonProperty("effective_time")
  private Long effectiveTime;

  /** 失效时间 */
  @JsonProperty("uneffective_time")
  private Long uneffectiveTime;

  /** 类目资质id */
  @JsonProperty("qua_id")
  private Long quaId;
}
