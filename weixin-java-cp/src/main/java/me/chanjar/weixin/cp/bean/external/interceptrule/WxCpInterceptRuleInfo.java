package me.chanjar.weixin.cp.bean.external.interceptrule;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * @Date: 2024-03-07 17:02
 * @Author： shenliuming
 * @return：
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxCpInterceptRuleInfo extends WxCpBaseResp implements Serializable {

  private static final long serialVersionUID = -425957862453041229L;

  @SerializedName("rule")
  private Rule rule;

  @Data
  @EqualsAndHashCode(callSuper = false)
  public static class Rule implements Serializable {
    @SerializedName("rule_id")
    private String ruleId;

    @SerializedName("rule_name")
    private String ruleName;

    @SerializedName("word_list")
    private List<String> wordList;

    @SerializedName("semantics_list")
    private List<Integer> semanticsList;

    @SerializedName("intercept_type")
    private Integer interceptType;

    @SerializedName("applicable_range")
    private ApplicableRange applicableRange;

    @SerializedName("create_time")
    private long createTime;

  }


  public static WxCpInterceptRuleInfo fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpInterceptRuleInfo.class);
  }

}
