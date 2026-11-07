package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 手机号认证信息
 *
 * @author GitHub Copilot
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.order.PrivateNumberPhoneInfo}。
 */
@Data
@NoArgsConstructor
@Deprecated
public class PrivateNumberPhoneInfo implements Serializable {

  private static final long serialVersionUID = 1L;

  /** 手机号 */
  @JsonProperty("phone")
  private String phone;

  /**
   * 认证状态：1-待认证，2-认证成功，3-认证失败
   */
  @JsonProperty("auth_status")
  private Integer authStatus;

}
