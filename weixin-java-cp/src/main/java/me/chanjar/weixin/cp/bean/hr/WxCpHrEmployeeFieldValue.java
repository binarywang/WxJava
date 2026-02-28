package me.chanjar.weixin.cp.bean.hr;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 人事助手-员工档案字段值.
 *
 * @author <a href="https://github.com/leejoker">leejoker</a> created on  2024-01-01
 */
@Data
@NoArgsConstructor
public class WxCpHrEmployeeFieldValue implements Serializable {
  private static final long serialVersionUID = 3593693598671765396L;

  /**
   * 文本/数字/手机/邮箱类型字段值.
   */
  @SerializedName("text_value")
  private String textValue;

  /**
   * 单选类型字段值.
   */
  @SerializedName("option_value")
  private OptionValue optionValue;

  /**
   * 多选类型字段值列表.
   */
  @SerializedName("option_value_list")
  private List<OptionValue> optionValueList;

  /**
   * 日期类型字段值.
   */
  @SerializedName("date_value")
  private DateValue dateValue;

  /**
   * 附件类型字段值.
   */
  @SerializedName("attachment_value")
  private AttachmentValue attachmentValue;

  /**
   * 单选/多选选项值.
   */
  @Data
  @NoArgsConstructor
  public static class OptionValue implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 选项key.
     */
    @SerializedName("key")
    private String key;
  }

  /**
   * 日期值.
   */
  @Data
  @NoArgsConstructor
  public static class DateValue implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 时间戳（字符串格式）.
     */
    @SerializedName("timestamp")
    private String timestamp;
  }

  /**
   * 附件值.
   */
  @Data
  @NoArgsConstructor
  public static class AttachmentValue implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 附件id列表.
     */
    @SerializedName("id_list")
    private List<String> idList;
  }
}
