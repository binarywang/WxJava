package me.chanjar.weixin.cp.bean.hr;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

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
   * 字段信息列表.
   */
  @SerializedName("field_info_list")
  private List<WxCpHrEmployeeFieldInfo> fieldInfoList;

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
