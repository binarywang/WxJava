package com.github.binarywang.wxpay.bean.bank;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 省份列表
 *
 * @author hupeng
 **/
@Data
public class ProvincesResult implements Serializable {

  private static final long serialVersionUID = -4118613374545722650L;

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
   * 字段名：省份列表
   * 变量名：data
   * 是否必填：否
   * 类型：array
   * 描述：
   *  查询到的省份列表数组
   * </pre>
   */
  @SerializedName("data")
  private List<ProvinceInfo> data;

  @Getter
  @Setter
  public static class ProvinceInfo implements Serializable {

    private static final long serialVersionUID = -4118613374545722650L;

    /**
     * <pre>
     * 字段名：省份名称
     * 变量名：province_name
     * 是否必填：是
     * 类型：string[1, 256]
     * 描述：
     *  省份名称
     *  示例值：广东省
     * </pre>
     */
    @SerializedName("province_name")
    private String provinceName;

    /**
     * <pre>
     * 字段名：省份编码
     * 变量名：province_code
     * 是否必填：是
     * 类型：int
     * 描述：
     *  省份编码，唯一标识一个省份，用于根据省份编码查询省份下的城市列表数据
     *  示例值：22
     * </pre>
     */
    @SerializedName("province_code")
    private Integer provinceCode;
  }

}
