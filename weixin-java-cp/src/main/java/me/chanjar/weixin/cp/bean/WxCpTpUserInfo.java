package me.chanjar.weixin.cp.bean;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * The type Wx cp tp user info.
 *
 * @author huangxiaoming
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxCpTpUserInfo extends WxCpBaseResp {
  private static final long serialVersionUID = -5028321625140879571L;

  /**
   * 用户所属企业的corpid
   */
  @SerializedName("corpid")
  private String corpId;

  /**
   * 用户在企业内的UserID，如果该企业与第三方应用有授权关系时，返回明文UserId，否则返回密文UserId
   */
  @SerializedName("userid")
  private String userId;

  /**
   * 成员票据，最大为512字节。
   * scope为snsapi_userinfo或snsapi_privateinfo，且用户在应用可见范围之内时返回此参数。
   * 后续利用该参数可以获取用户信息或敏感信息，参见:<a href="https://work.weixin.qq.com/api/doc/90001/90143/91122">...</a>
   */
  @SerializedName("user_ticket")
  private String userTicket;

  /**
   * user_ticket的有效时间（秒），随user_ticket一起返回
   */
  @SerializedName("expires_in")
  private String expiresIn;

  /**
   * 全局唯一。对于同一个服务商，不同应用获取到企业内同一个成员的open_userid是相同的，最多64个字节。仅第三方应用可获取
   */
  @SerializedName("open_userid")
  private String openUserId;

  /**
   非企业成员的标识，对当前服务商唯一
   */
  @SerializedName("openid")
  private String openid;

  /**
   * From json wx cp tp user info.
   *
   * @param json the json
   * @return the wx cp tp user info
   */
  public static WxCpTpUserInfo fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpTpUserInfo.class);
  }

}
