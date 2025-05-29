package me.chanjar.weixin.channel.bean.compass.finder;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 维度数据
 *
 * @author <a href="https://github.com/Winnie-by996">Winnie</a>
 */
@Data
@NoArgsConstructor
public class Field implements Serializable {

  private static final long serialVersionUID = - 4243469984232948689L;

  /**
   * 维度类别名
   */
  @JsonProperty("field_name")
  private String fieldName;

  /**
   * 维度指标数据列表
   */
  @JsonProperty("data_list")
  private List<FieldData> dataList;

}
