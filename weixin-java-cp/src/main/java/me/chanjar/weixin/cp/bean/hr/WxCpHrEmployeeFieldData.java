package me.chanjar.weixin.cp.bean.hr;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 人事助手-员工档案数据（单个字段）.
 *
 * @author <a href="https://github.com/leejoker">leejoker</a> created on  2024-01-01
 */
@Data
@NoArgsConstructor
public class WxCpHrEmployeeFieldData implements Serializable {
  private static final long serialVersionUID = 4593693598671765396L;

  /**
   * 字段ID.
   */
  @SerializedName("fieldid")
  private Integer fieldId;

  /**
   * 子字段索引.
   */
  @SerializedName("sub_idx")
  private Integer subIdx;

  /**
   * 结果状态，1表示成功.
   */
  @SerializedName("result")
  private Integer result;

  /**
   * 值类型：1-字符串，2-uint64，3-uint32，4-int64，5-mobile.
   */
  @SerializedName("value_type")
  private Integer valueType;

  /**
   * 字符串值（value_type=1时使用）.
   */
  @SerializedName("value_string")
  private String valueString;

  /**
   * 无符号32位整数值（value_type=3时使用）.
   */
  @SerializedName("value_uint32")
  private Long valueUint32;

  /**
   * 有符号64位整数值（value_type=4时使用）.
   */
  @SerializedName("value_int64")
  private Long valueInt64;

  /**
   * 无符号64位整数值（value_type=2时使用）.
   */
  @SerializedName("value_uint64")
  private Long valueUint64;

  /**
   * 手机号值（value_type=5时使用）.
   */
  @SerializedName("value_mobile")
  private MobileValue valueMobile;

  /**
   * 手机号值.
   */
  @Data
  @NoArgsConstructor
  public static class MobileValue implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 国家代码.
     */
    @SerializedName("value_country_code")
    private String valueCountryCode;

    /**
     * 手机号.
     */
    @SerializedName("value_mobile")
    private String valueMobile;
  }

  /**
   * 员工userid（兼容旧版本，实际API不返回此字段）.
   * @deprecated 此字段在API响应中不存在
   */
  @Deprecated
  @SerializedName("userid")
  private String userid;

  /**
   * 字段数据列表（兼容旧版本，实际API不返回此字段）.
   * @deprecated 此字段在API响应中不存在
   */
  @Deprecated
  @SerializedName("field_list")
  private java.util.List<FieldItem> fieldList;

  /**
   * 字段数据项（用于更新员工档案）.
   */
  @Data
  @NoArgsConstructor
  public static class FieldItem implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 字段ID.
     */
    @SerializedName("fieldid")
    private Integer fieldId;

    /**
     * 字段值对象（推荐使用，支持多种类型）.
     */
    @SerializedName("field_value")
    private WxCpHrEmployeeFieldValue fieldValue;

    /**
     * 字符串值（简化用法，适用于文本类型字段）.
     */
    @SerializedName("value_string")
    private String valueString;
  }
}
