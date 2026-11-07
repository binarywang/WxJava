package me.chanjar.weixin.channel.bean.vip;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

import java.util.List;

/**
 * @author : zhenyun.su
 * @since : 2023/10/8
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.vip.VipListResponse}。
 */

@Data
@NoArgsConstructor
@Deprecated
public class VipListResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -8127372979925053579L;
  @JsonProperty("list")
  private List<VipInfo> vipInfos;

  @JsonProperty("total_num")
  private Long totalNum;
}
