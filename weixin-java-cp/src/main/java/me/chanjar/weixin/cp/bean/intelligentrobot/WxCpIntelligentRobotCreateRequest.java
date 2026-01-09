package me.chanjar.weixin.cp.bean.intelligentrobot;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 创建智能机器人请求
 *
 * @author Binary Wang
 */
@Data
public class WxCpIntelligentRobotCreateRequest implements Serializable {
  private static final long serialVersionUID = -1L;

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

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

  public static WxCpIntelligentRobotCreateRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpIntelligentRobotCreateRequest.class);
  }
}