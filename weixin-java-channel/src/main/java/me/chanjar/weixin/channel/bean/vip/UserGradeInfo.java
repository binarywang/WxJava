package me.chanjar.weixin.channel.bean.vip;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 视频号小店-会员功能 - 用户等级信息
 *
 * @author <a href="https://github.com/asushiye">asushiye</a>
 *
 */
@Data
@NoArgsConstructor
@Deprecated
public class UserGradeInfo implements Serializable {

  private static final long serialVersionUID = -8040963202754069865L;
  /** 等级编号 */
  @JsonProperty("grade")
  protected Integer grade;

  /** 用户经验值 */
  @JsonProperty("experience_value")
  protected String experienceValue;
}
