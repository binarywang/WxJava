package me.chanjar.weixin.channel.bean.compass.shop;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.compass.CompassFinderBaseParam;

/**
 * 获取带货人群数据请求参数
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShopSaleProfileDataParam extends CompassFinderBaseParam {

  private static final long serialVersionUID = 240010632808576923L;

  /** 用户类型 */
  @JsonProperty("type")
  private Integer type;

  public ShopSaleProfileDataParam(String ds, Integer type) {
    super(ds);
    this.type = type;
  }

}
