package me.chanjar.weixin.cp.bean.hr;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 人事助手-员工档案数据（单个员工）.
 *
 * @author <a href="https://github.com/leejoker">leejoker</a> created on  2024-01-01
 */
@Data
@NoArgsConstructor
public class WxCpHrEmployeeFieldData implements Serializable {
  private static final long serialVersionUID = 4593693598671765396L;

  /**
   * 员工userid.
   */
  @SerializedName("userid")
  private String userid;

  /**
   * 字段数据列表.
   */
  @SerializedName("field_list")
  private List<FieldItem> fieldList;

  /**
   * 字段数据项.
   */
  @Data
  @NoArgsConstructor
  public static class FieldItem implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 字段key.
     */
    @SerializedName("field_key")
    private String fieldKey;

    /**
     * 字段值.
     */
    @SerializedName("field_value")
    private WxCpHrEmployeeFieldValue fieldValue;
  }
}
