package me.chanjar.weixin.channel.bean.compass.shop;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 维度数据
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class ShopField implements Serializable {

  private static final long serialVersionUID = -8669197081350262569L;

  /** 维度类别名 */
  @JsonProperty("field_name")
  private String fieldName;

  /** 维度指标数据列表 */
  @JsonProperty("data_list")
  private List<FieldDetail> dataList;


  @Data
  @NoArgsConstructor
  public static class FieldDetail implements Serializable {

    private static final long serialVersionUID = 2900633035074950462L;

    /** 维度指标名 */
    @JsonProperty("dim_key")
    private String dimKey;

    /** 维度指标值 */
    @JsonProperty("dim_value")
    private String dimValue;

  }
}
