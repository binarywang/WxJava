package me.chanjar.weixin.channel.bean.cooperation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 合作账号参数
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CooperationSharerParam implements Serializable {

  private static final long serialVersionUID = 5032621997764493109L;

  /** 合作账号id */
  @JsonProperty("sharer_id")
  private String sharerId;

  /** 合作账号类型 */
  @JsonProperty("sharer_type")
  private Integer sharerType;
}
