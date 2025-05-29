package me.chanjar.weixin.channel.bean.compass.finder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.compass.CompassFinderBaseParam;

/**
 * 获取带货人群数据请求参数
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaleProfileDataParam extends CompassFinderBaseParam {

  private static final long serialVersionUID = 4037843292285732855L;

  /**
   * 用户类型 {@link me.chanjar.weixin.channel.enums.SaleProfileUserType}
   */
  @JsonProperty("type")
  private Integer type;

  public SaleProfileDataParam(String ds, Integer type) {
    super(ds);
    this.type = type;
  }

}
