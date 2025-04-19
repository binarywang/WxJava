package me.chanjar.weixin.channel.bean.compass.shop;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 店铺人群数据
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class ShopSaleProfileData implements Serializable {

  private static final long serialVersionUID = -6825849811081728787L;

  /** 维度数据列表 */
  @JsonProperty("field_list")
  private List<ShopField> fieldList;

}
