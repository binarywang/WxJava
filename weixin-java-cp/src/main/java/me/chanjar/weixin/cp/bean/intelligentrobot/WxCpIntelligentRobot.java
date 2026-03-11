package me.chanjar.weixin.cp.bean.intelligentrobot;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 智能机器人信息
 *
 * @author Binary Wang
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxCpIntelligentRobot extends WxCpBaseResp implements Serializable {
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

  /**
   * 创建时间
   */
  @SerializedName("create_time")
  private Long createTime;

  /**
   * 更新时间
   */
  @SerializedName("update_time")
  private Long updateTime;

  /**
   * From json wx cp intelligent robot.
   *
   * @param json the json
   * @return the wx cp intelligent robot
   */
  public static WxCpIntelligentRobot fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpIntelligentRobot.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}