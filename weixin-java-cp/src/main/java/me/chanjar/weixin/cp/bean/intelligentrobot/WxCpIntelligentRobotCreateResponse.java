package me.chanjar.weixin.cp.bean.intelligentrobot;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 创建智能机器人响应
 *
 * @author Binary Wang
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxCpIntelligentRobotCreateResponse extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -1L;

  /**
   * 机器人ID
   */
  @SerializedName("robot_id")
  private String robotId;

  public static WxCpIntelligentRobotCreateResponse fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpIntelligentRobotCreateResponse.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }
}