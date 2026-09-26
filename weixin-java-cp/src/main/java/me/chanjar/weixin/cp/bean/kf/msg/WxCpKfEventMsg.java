package me.chanjar.weixin.cp.bean.kf.msg;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Wx cp kf event msg.
 *
 * @author leiin  created on  2022/1/26 6:44 下午
 */
@NoArgsConstructor
@Data
public class WxCpKfEventMsg {
  @SerializedName("event_type")
  private String eventType;
  @SerializedName("open_kfid")
  private String openKfid;
  @SerializedName("external_userid")
  private String externalUserId;
  @SerializedName("servicer_userid")
  private String servicerUserId;
  @SerializedName("old_servicer_userid")
  private String oldServicerUserId;
  @SerializedName("new_servicer_userid")
  private String newServicerUserId;
  private String scene;
  @SerializedName("scene_param")
  private String sceneParam;
  @SerializedName("welcome_code")
  private String welcomeCode;
  @SerializedName("fail_msgid")
  private String failMsgId;
  @SerializedName("fail_type")
  private Integer failType;
  private Integer status;
  @SerializedName("change_type")
  private Integer changeType;
  @SerializedName("msg_code")
  private String msgCode;
  @SerializedName("recall_msgid")
  private String recallMsgId;
  @SerializedName("reject_switch")
  private Integer rejectSwitch;
}
