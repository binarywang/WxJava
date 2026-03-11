package me.chanjar.weixin.cp.bean.intelligentrobot;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 更新智能机器人请求
 *
 * @author Binary Wang
 */
@Data
public class WxCpIntelligentRobotUpdateRequest implements Serializable {
  private static final long serialVersionUID = -1L;

  /**
   * 机器人ID
   */
  @SerializedName("robot_id")
  private String robotId;

  /**
   * 机器人名称
   */
  @SerializedName("name")
  private String name;

  /**
   * 机器人描述
   */
  @SerializedName("description")
  private String description;

  /**
   * 机器人头像
   */
  @SerializedName("avatar")
  private String avatar;

  /**
   * 机器人状态 0:停用 1:启用
   */
  @SerializedName("status")
  private Integer status;

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

  public static WxCpIntelligentRobotUpdateRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpIntelligentRobotUpdateRequest.class);
  }
}