package com.github.binarywang.wxpay.bean.bank;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 城市列表
 *
 * @author hupeng
 **/
@Data
public class CitiesResult implements Serializable {

  private static final long serialVersionUID = -6089905695087974693L;

  /**
   * <pre>
   * 字段名：查询数据总条数
   * 变量名：total_count
   * 是否必填：是
   * 类型：int
   * 描述：
   *  查询到的省份数据总条数
   *  示例值：10
   * </pre>
   */
  @SerializedName("total_count")
  private Integer totalCount;

  /**
   * <pre>
   * 字段名：城市列表
   * 变量名：data
   * 是否必填：否
   * 类型：array
   * 描述：
   *  查询返回的城市列表结果
   * </pre>
   */
  @SerializedName("data")
  private List<CityInfo> data;

  @Getter
  @Setter
  public static class CityInfo implements Serializable {

    private static final long serialVersionUID = -6089905695087974693L;

    /**
     * <pre>
     * 字段名：城市名称
     * 变量名：city_name
     * 是否必填：是
     * 类型：string[1, 256]
     * 描述：
     *  城市名称
     *  示例值：北京市
     * </pre>
     */
    @SerializedName("city_name")
    private String cityName;

    /**
     * <pre>
     * 字段名：城市编码
     * 变量名：city_code
     * 是否必填：是
     * 类型：int
     * 描述：
     *  城市编码，唯一标识一座城市，用于结合银行别名编码查询支行列表
     *  示例值：10
     * </pre>
     */
    @SerializedName("city_code")
    private Integer cityCode;
  }

}
