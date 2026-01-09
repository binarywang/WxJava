package me.chanjar.weixin.cp.bean.external;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 离职员工外部联系人列表
 *
 * @author yqx, Wang_Wong
 * @since 2020/3/15
 */
@Getter
@Setter
public class WxCpUserExternalUnassignList extends WxCpBaseResp {

  @SerializedName("info")
  private List<UnassignInfo> unassignInfos;

  @SerializedName("is_last")
  private boolean isLast;

  @SerializedName("next_cursor")
  private String nextCursor;

  /**
   * The type Unassign info.
   */
  @Getter
  @Setter
  public static class UnassignInfo implements Serializable {
    private static final long serialVersionUID = -4301684507150486556L;

    /**
     * 离职成员userid
     */
    @SerializedName("handover_userid")
    private String handoverUserid;

    /**
     * 外部联系人userid
     */
    @SerializedName("external_userid")
    private String externalUserid;

    /**
     * 成员离职时间
     */
    @SerializedName("dimission_time")
    private Long dimissionTime;
  }

  /**
   * From json wx cp user external unassign list.
   *
   * @param json the json
   * @return the wx cp user external unassign list
   */
  public static WxCpUserExternalUnassignList fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpUserExternalUnassignList.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
