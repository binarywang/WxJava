package me.chanjar.weixin.cp.bean.external.interceptrule;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * @Date: 2024-03-07 15:54
 * @Author： shenliuming
 * @return：
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxCpInterceptRuleList extends WxCpBaseResp implements Serializable {

  private static final long serialVersionUID = -830298362453041229L;
  /**
   * link_id列表
   */
  @SerializedName("rule_list")
  private List<Rule> ruleList;

  public static WxCpInterceptRuleList fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpInterceptRuleList.class);
  }

  @Data
  @EqualsAndHashCode(callSuper = false)
  public static class Rule implements Serializable {
    private static final long serialVersionUID = 4750537220968228300L;

    /**
     * 规则id
     */
    @SerializedName("rule_id")
    private String ruleId;

    /**
     * 规则名称，长度上限20个字符
     */
    @SerializedName("rule_name")
    private String ruleName;

    /**
     * 创建时间
     */
    @SerializedName("create_time")
    private Long createTime;

    public static WxCpInterceptRuleList.Rule fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, WxCpInterceptRuleList.Rule.class);
    }
  }
}
