package me.chanjar.weixin.mp.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * <pre>
 * 查询群发消息发送状态【订阅号与服务号认证后均可用】
 * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Message_Management/Batch_Sends_and_Originality_Checks.html
 * </pre>
 * @author S <sshzh90@gmail.com>
 */
@Data
public class WxMpMassGetResult implements Serializable {
  private static final long serialVersionUID = -2909694117357278557L;

  @SerializedName("msg_id")
  private Long msgId;

  @SerializedName("msg_status")
  private String msgstatus;

  public static WxMpMassGetResult fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpMassGetResult.class);
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

}
