package me.chanjar.weixin.channel.bean.compass.finder;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 带货人群数据
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@NoArgsConstructor
public class SaleProfileData implements Serializable {

  private static final long serialVersionUID = - 5542602540358792014L;

  /**
   * 维度数据列表
   */
  @JsonProperty("field_list")
  private List<Field> fieldList;

}
