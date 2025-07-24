package me.chanjar.weixin.channel.bean.compass.shop;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.compass.CompassFinderBaseParam;

/**
 * 带货达人 请求参数
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompassFinderIdParam extends CompassFinderBaseParam {

  private static final long serialVersionUID = 9214560943091074780L;

  /** 视频号ID */
  @JsonProperty("finder_id")
  private String finderId;

  public CompassFinderIdParam(String ds, String finderId) {
    super(ds);
    this.finderId = finderId;
  }

}
