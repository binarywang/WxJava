package me.chanjar.weixin.cp.bean;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * 企业号应用信息.
 * Created by huansinho on 2018/4/13.
 * </pre>
 *
 * @author <a href="https://github.com/huansinho">huansinho</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxCpAgent implements Serializable {
  private static final long serialVersionUID = 5002894979081127234L;

  @SerializedName("errcode")
  private Integer errCode;

  @SerializedName("errmsg")
  private String errMsg;

  @SerializedName("agentid")
  private Long agentId;

  @SerializedName("name")
  private String name;

  @SerializedName("square_logo_url")
  private String squareLogoUrl;

  @SerializedName("logo_mediaid")
  private String logoMediaId;

  @SerializedName("description")
  private String description;

  @SerializedName("allow_userinfos")
  private Users allowUserInfos;

  @SerializedName("allow_partys")
  private Parties allowParties;

  @SerializedName("allow_tags")
  private Tags allowTags;

  @SerializedName("close")
  private Integer close;

  @SerializedName("redirect_domain")
  private String redirectDomain;

  @SerializedName("report_location_flag")
  private Integer reportLocationFlag;

  @SerializedName("isreportenter")
  private Integer isReportEnter;

  @SerializedName("home_url")
  private String homeUrl;

  @SerializedName("customized_publish_status")
  private Integer customizedPublishStatus;

  /**
   * From json wx cp agent.
   *
   * @param json the json
   * @return the wx cp agent
   */
  public static WxCpAgent fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpAgent.class);
  }

  /**
   * To json string.
   *
   * @return the string
   */
  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

  /**
   * The type Users.
   */
  @Data
  public static class Users implements Serializable {
    private static final long serialVersionUID = 8801100463558788565L;

    @SerializedName("user")
    private List<User> users;
  }

  /**
   * The type User.
   */
  @Data
  public static class User implements Serializable {
    private static final long serialVersionUID = 7287632514385508024L;

    @SerializedName("userid")
    private String userId;
  }

  /**
   * The type Parties.
   */
  @Data
  public static class Parties {
    @SerializedName("partyid")
    private List<Long> partyIds = null;
  }

  /**
   * The type Tags.
   */
  @Data
  public static class Tags {
    @SerializedName("tagid")
    private List<Integer> tagIds = null;
  }

}
