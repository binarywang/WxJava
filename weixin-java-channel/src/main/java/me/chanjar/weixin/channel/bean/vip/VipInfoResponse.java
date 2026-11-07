package me.chanjar.weixin.channel.bean.vip;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * @author : zhenyun.su
 * @since : 2023/10/8
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.vip.VipInfoResponse}。
 */

@Data
@NoArgsConstructor
@Deprecated
public class VipInfoResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -2439510304690862381L;
  @JsonProperty("info")
  private VipInfo vipInfo;
}
