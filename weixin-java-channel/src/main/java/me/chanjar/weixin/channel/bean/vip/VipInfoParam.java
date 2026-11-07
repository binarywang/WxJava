package me.chanjar.weixin.channel.bean.vip;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : zhenyun.su
 * @since : 2023/10/8
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.vip.VipInfoParam}。
 */

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Deprecated
public class VipInfoParam implements Serializable {
  private static final long serialVersionUID = -4196252299609288196L;
  @JsonProperty("openid")
  private String openId;

  @JsonProperty("need_phone_number")
  private Boolean needPhoneNumber;
}
