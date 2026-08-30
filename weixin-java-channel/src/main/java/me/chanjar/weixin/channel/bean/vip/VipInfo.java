package me.chanjar.weixin.channel.bean.vip;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 视频号小店-会员功能 - 订单详情
 *
 * @author <a href="https://github.com/asushiye">asushiye</a>
 *
 * "info": {
 *     "openid": "OPENID",
 *     "unionid": "UNIONID",
 *     "user_info": {
 *       "phone_number": "123456789"
 *     },
 *     "user_grade_info": {
 *       "grade": 1,
 *       "experience_value": "100"
 *     }
 *   }
 */
@Data
@NoArgsConstructor
@Deprecated
public class VipInfo implements Serializable {
  private static final long serialVersionUID = -215590991862774701L;

  /** 视频号openid */
  @JsonProperty("openid")
  protected String openId;

  /** 视频号union_id */
  @JsonProperty("union_id")
  protected String unionId;

  /** 用户信息 */
  @JsonProperty("user_info")
  protected UserInfo userInfo;

  /** 用户等级信息 */
  @JsonProperty("user_grade_info")
  protected UserGradeInfo userGradeInfo;

}
