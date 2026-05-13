package me.chanjar.weixin.cp.bean.hr;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 人事助手-员工档案字段信息.
 *
 * @author <a href="https://github.com/leejoker">leejoker</a> created on  2024-01-01
 */
@Data
@NoArgsConstructor
public class WxCpHrEmployeeFieldInfo implements Serializable {
  private static final long serialVersionUID = 2593693598671765396L;

  /**
   * 字段ID.
   */
  @SerializedName("fieldid")
  private Integer fieldId;

  /**
   * 字段名称.
   */
  @SerializedName("field_name")
  private String fieldName;

  /**
   * 字段类型.
   * 1: 文本
   * 2: 单选/多选
   * 3: 日期
   */
  @SerializedName("field_type")
  private Integer fieldType;

  /**
   * 是否必填.
   */
  @SerializedName("is_must")
  private Boolean isMust;

  /**
   * 值类型.
   * 1: 字符串
   * 2: uint64
   * 3: uint32
   * 4: int64
   * 5: mobile
   */
  @SerializedName("value_type")
  private Integer valueType;

  /**
   * 获取字段类型枚举.
   *
   * @return 字段类型枚举，未匹配时返回 null
   */
  public WxCpHrFieldType getFieldTypeEnum() {
    return fieldType == null ? null : WxCpHrFieldType.fromCode(fieldType);
  }

  /**
   * 选项列表（单选/多选字段专用）.
   */
  @SerializedName("option_list")
  private List<Option> optionList;

  /**
   * 选项.
   */
  @Data
  @NoArgsConstructor
  public static class Option implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 选项ID.
     */
    @SerializedName("id")
    private Integer id;

    /**
     * 选项值.
     */
    @SerializedName("value")
    private String value;
  }

  // ===== 以下字段为兼容旧版本 =====

  /**
   * 字段key（兼容旧版本，实际API不返回此字段）.
   * @deprecated 使用 fieldId 代替
   */
  @Deprecated
  @SerializedName("field_key")
  private String fieldKey;

  /**
   * 字段英文名称（兼容旧版本，实际API不返回此字段）.
   * @deprecated 此字段在API响应中不存在
   */
  @Deprecated
  @SerializedName("field_en_name")
  private String fieldEnName;

  /**
   * 字段中文名称（兼容旧版本）.
   * @deprecated 使用 fieldName 代替
   */
  @Deprecated
  @SerializedName("field_zh_name")
  private String fieldZhName;

  /**
   * 是否系统字段（兼容旧版本，实际API不返回此字段）.
   * @deprecated 此字段在API响应中不存在
   */
  @Deprecated
  @SerializedName("is_sys")
  private Integer isSys;

  /**
   * 字段详情（兼容旧版本）.
   * @deprecated 使用 optionList 直接访问选项列表
   */
  @Deprecated
  @SerializedName("field_detail")
  private FieldDetail fieldDetail;

  /**
   * 字段详情（兼容旧版本）.
   * @deprecated 使用 optionList 代替
   */
  @Deprecated
  @Data
  @NoArgsConstructor
  public static class FieldDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 选项列表（单选/多选字段专用）.
     */
    @SerializedName("option_list")
    private List<OldOption> optionList;
  }

  /**
   * 旧版选项（兼容旧版本）.
   * @deprecated 使用 Option 代替
   */
  @Deprecated
  @Data
  @NoArgsConstructor
  public static class OldOption implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 选项key.
     */
    @SerializedName("key")
    private String key;

    /**
     * 选项值.
     */
    @SerializedName("value")
    private String value;
  }
}
