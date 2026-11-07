package com.binarywang.wxjava.store.bean.sharer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 分享员绑定响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SharerSearchResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = -5346019069466917659L;
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

  /** 分享员类型 {@link com.binarywang.wxjava.store.enums.SharerType} */
  @JsonProperty("sharer_type")
  private Integer sharerType;

}
