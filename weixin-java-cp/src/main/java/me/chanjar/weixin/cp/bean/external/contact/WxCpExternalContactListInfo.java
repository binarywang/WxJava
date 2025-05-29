package me.chanjar.weixin.cp.bean.external.contact;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 *   获取已服务的外部联系人( <a href="https://developer.work.weixin.qq.com/document/path/99434">参考文档</a>)
 * @see WxCpService#getExternalContactService()
 * @serial
 */
@Getter
@Setter
public class WxCpExternalContactListInfo implements Serializable {

  private static final long serialVersionUID = 7114885886548364396L;

  @SerializedName("next_cursor")
  private String nextCursor;

  @SerializedName("errcode")
  private String errcode;

  @SerializedName("errmsg")
  private String errmsg;

  @SerializedName("info_list")
  private List<ExternalContactInfo> infoList;

  @Getter
  @Setter
  public static class ExternalContactInfo implements Serializable{

    private static final long serialVersionUID = -7400291089462740100L;

    /**
     * 是否被成员标记为客户
     */
    @SerializedName("is_customer")
    private Boolean isCustomer;

    /**
     * 外部联系人临时ID
     */
    @SerializedName("tmp_openid")
    private String tmpOpenid;

    /**
     * 外部联系人的externaluserid（如果是客户才返回）
     */
    @SerializedName("external_userid")
    private String externalUserid;

    /**
     * 脱敏后的外部联系人昵称（如果是其他外部联系人才返回）
     */
    @SerializedName("name")
    private String name;

    /**
     * 添加此外部联系人的企业成员或外部联系人所在群聊的群主userid
     */
    @SerializedName("follow_userid")
    private String followUserid;

    /**
     * 外部联系人所在的群聊ID（如果群聊被成员标记为客户群才返回）
     */
    @SerializedName("chat_id")
    private String chatId;

    /**
     * 外部联系人所在群聊的群名（如果群聊未被成员标记为客户群才返回）
     */
    @SerializedName("chat_name")
    private String chatName;

    /**
     * 外部联系人首次添加/进群的时间
     */
    @SerializedName("add_time")
    private Long addTime;
  }
  public static WxCpExternalContactListInfo fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpExternalContactListInfo.class);
  }

}
