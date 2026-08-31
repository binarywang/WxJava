package me.chanjar.weixin.channel.bean.kf;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/** 客服素材上传响应。
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.kf.WxStoreKfCosUploadResponse}。
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class WxChannelKfCosUploadResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 1L;

  /** 素材在 COS 上的地址。 */
  @JsonProperty("cos_url")
  private String cosUrl;
}
