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
   * 字段key.
   */
  @SerializedName("field_key")
  private String fieldKey;

  /**
   * 字段英文名称.
   */
  @SerializedName("field_en_name")
  private String fieldEnName;

  /**
   * 字段中文名称.
   */
  @SerializedName("field_zh_name")
  private String fieldZhName;

  /**
   * 字段类型.
   * 具体取值参见 {@link WxCpHrFieldType}
   */
  @SerializedName("field_type")
  private Integer fieldType;

  /**
   * 获取字段类型枚举.
   *
   * @return 字段类型枚举，未匹配时返回 null
   */
  public WxCpHrFieldType getFieldTypeEnum() {
    return fieldType == null ? null : WxCpHrFieldType.fromCode(fieldType);
  }

  /**
   * 是否系统字段.
   * 0: 否
   * 1: 是
   */
  @SerializedName("is_sys")
  private Integer isSys;

  /**
   * 字段详情.
   */
  @SerializedName("field_detail")
  private FieldDetail fieldDetail;

  /**
   * 字段详情.
   */
  @Data
  @NoArgsConstructor
  public static class FieldDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 选项列表（单选/多选字段专用）.
     */
    @SerializedName("option_list")
    private List<Option> optionList;
  }

  /**
   * 选项.
   */
  @Data
  @NoArgsConstructor
  public static class Option implements Serializable {
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
