package me.chanjar.weixin.cp.bean.hr;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.util.List;

/**
 * 人事助手-获取员工档案数据响应.
 *
 * @author <a href="https://github.com/leejoker">leejoker</a> created on  2024-01-01
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WxCpHrEmployeeFieldDataResp extends WxCpBaseResp {
  private static final long serialVersionUID = 6593693598671765396L;

  /**
   * 字段数据列表（API实际返回field_info）.
   */
  @SerializedName("field_info")
  private List<WxCpHrEmployeeFieldData> fieldInfoList;

  /**
   * 员工档案数据列表（兼容旧版本方法名）.
   * @deprecated 请使用 getFieldInfoList()
   */
  @Deprecated
  public List<WxCpHrEmployeeFieldData> getEmployeeFieldList() {
    return this.fieldInfoList;
  }

  /**
   * 员工档案数据列表（兼容旧版本方法名）.
   * @deprecated 请使用 setFieldInfoList()
   */
  @Deprecated
  public void setEmployeeFieldList(List<WxCpHrEmployeeFieldData> employeeFieldList) {
    this.fieldInfoList = employeeFieldList;
  }

  /**
   * From json wx cp hr employee field data resp.
   *
   * @param json the json
   * @return the wx cp hr employee field data resp
   */
  public static WxCpHrEmployeeFieldDataResp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpHrEmployeeFieldDataResp.class);
  }
}
