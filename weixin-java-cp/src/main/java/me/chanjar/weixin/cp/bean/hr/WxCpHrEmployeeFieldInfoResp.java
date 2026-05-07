package me.chanjar.weixin.cp.bean.hr;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 人事助手-获取员工档案字段信息响应.
 *
 * @author <a href="https://github.com/leejoker">leejoker</a> created on  2024-01-01
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WxCpHrEmployeeFieldInfoResp extends WxCpBaseResp {
  private static final long serialVersionUID = 5593693598671765396L;

  /**
   * 字段分组列表（API实际返回group_list）.
   */
  @SerializedName("group_list")
  private List<FieldGroup> groupList;

  /**
   * 字段信息列表（兼容旧版本方法名）.
   * @deprecated 请使用 getGroupList() 获取分组后的字段信息
   */
  @Deprecated
  public List<WxCpHrEmployeeFieldInfo> getFieldInfoList() {
    if (groupList == null || groupList.isEmpty()) {
      return null;
    }
    // 兼容旧版本：将所有分组的字段合并到一个列表
    List<WxCpHrEmployeeFieldInfo> allFields = new java.util.ArrayList<>();
    for (FieldGroup group : groupList) {
      if (group.getFieldList() != null) {
        allFields.addAll(group.getFieldList());
      }
    }
    return allFields.isEmpty() ? null : allFields;
  }

  /**
   * 字段分组.
   */
  @Data
  @NoArgsConstructor
  public static class FieldGroup implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 分组ID.
     */
    @SerializedName("group_id")
    private Integer groupId;

    /**
     * 分组名称.
     */
    @SerializedName("group_name")
    private String groupName;

    /**
     * 字段列表.
     */
    @SerializedName("field_list")
    private List<WxCpHrEmployeeFieldInfo> fieldList;
  }

  /**
   * From json wx cp hr employee field info resp.
   *
   * @param json the json
   * @return the wx cp hr employee field info resp
   */
  public static WxCpHrEmployeeFieldInfoResp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpHrEmployeeFieldInfoResp.class);
  }
}
