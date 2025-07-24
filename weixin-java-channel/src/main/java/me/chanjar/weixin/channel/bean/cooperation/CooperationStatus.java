package me.chanjar.weixin.channel.bean.cooperation;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 合作账号状态
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class CooperationStatus implements Serializable {

  private static final long serialVersionUID = -7096916911986699150L;

  /** 邀请/合作账号状态 1已绑定 2已解绑 3邀请已拒绝 4邀请接受中 5邀请接受超时 6邀请接受失败 7邀请店铺取消 */
  @JsonProperty("status")
  private Integer status;

}
