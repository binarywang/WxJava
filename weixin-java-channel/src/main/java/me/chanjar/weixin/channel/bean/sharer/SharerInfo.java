package me.chanjar.weixin.channel.bean.sharer;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 分享员信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.sharer.SharerInfo}。
 */
@Data
@NoArgsConstructor
@Deprecated
public class SharerInfo implements Serializable {

  private static final long serialVersionUID = -4373597470611742887L;
  /** 分享员openid */
  @JsonProperty("openid")
  private String openid;

  /** 分享员unionid */
  @JsonProperty("unionid")
  private String unionid;

  /** 分享员openid */
  @JsonProperty("nickname")
  private String nickname;

  /** 绑定时间 */
  @JsonProperty("bind_time")
  private Long bindTime;

  /** 分享员类型 {@link me.chanjar.weixin.channel.enums.SharerType} */
  @JsonProperty("sharer_type")
  private Integer sharerType;

}
