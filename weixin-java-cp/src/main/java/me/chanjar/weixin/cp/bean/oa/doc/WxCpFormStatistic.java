package me.chanjar.weixin.cp.bean.oa.doc;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * 收集表统计结果（单条统计项）.
 */
@Data
public class WxCpFormStatistic implements Serializable {
  private static final long serialVersionUID = -7531427161782533396L;

  @SerializedName("fill_cnt")
  private Long fillCnt;

  @SerializedName("repeated_id")
  private String repeatedId;

  @SerializedName("repeated_name")
  private String repeatedName;

  @SerializedName("fill_user_cnt")
  private Long fillUserCnt;

  @SerializedName("unfill_user_cnt")
  private Long unfillUserCnt;

  @SerializedName("submit_users")
  private List<SubmitUser> submitUsers;

  @SerializedName("unfill_users")
  private List<UnfillUser> unfillUsers;

  @SerializedName("has_more")
  private Boolean hasMore;

  @SerializedName("cursor")
  private Long cursor;

  public static WxCpFormStatistic fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpFormStatistic.class);
  }

  public static List<WxCpFormStatistic> listFromJson(String json) {
    WxCpFormStatistic[] results = WxCpGsonBuilder.create().fromJson(json, WxCpFormStatistic[].class);
    return results == null ? null : Arrays.asList(results);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

  @Getter
  @Setter
  public static class SubmitUser implements Serializable {
    private static final long serialVersionUID = 879241255378502866L;

    @SerializedName("userid")
    private String userId;

    @SerializedName("tmp_external_userid")
    private String tmpExternalUserId;

    @SerializedName("submit_time")
    private Long submitTime;

    @SerializedName("answer_id")
    private Long answerId;

    @SerializedName("user_name")
    private String userName;
  }

  @Getter
  @Setter
  public static class UnfillUser implements Serializable {
    private static final long serialVersionUID = -1561219841801653574L;

    @SerializedName("userid")
    private String userId;

    @SerializedName("user_name")
    private String userName;
  }
}
