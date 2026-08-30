package me.chanjar.weixin.channel.bean.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 偏移参数
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
@Deprecated
public class OffsetParam implements Serializable {

  private static final long serialVersionUID = -1268796871980541662L;

  /** 起始位置 */
  @JsonProperty("offset")
  private Integer offset;
  /** 拉取个数 */
  @JsonProperty("limit")
  private Integer limit;
}
