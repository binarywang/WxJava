package me.chanjar.weixin.channel.bean.cooperation;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 合作账号信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class CooperationData implements Serializable {

  private static final long serialVersionUID = 3930010847236599458L;

  /** 合作账号id 公众号: gh_开头id 小程序: appid */
  @JsonProperty("sharer_id")
  private String sharerId;

  /** 邀请/合作账号状态 1已绑定 2已解绑 3邀请已拒绝 4邀请接受中 5邀请接受超时 6邀请接受失败 7邀请店铺取消 */
  @JsonProperty("status")
  private Integer status;

  /** 合作账号名称 */
  @JsonProperty("sharer_name")
  private String sharerName;

  /** 合作账号类型 2公众号 3小程序 */
  @JsonProperty("sharer_type")
  private Integer sharerType;

  /** 接受绑定时间戳，ms */
  @JsonProperty("bind_time")
  private Long bindTime;

  /** 用户拒绝时间戳，ms */
  @JsonProperty("reject_time")
  private Long rejectTime;

  /** 商家取消时间戳，ms */
  @JsonProperty("cancel_time")
  private Long cancelTime;

}
