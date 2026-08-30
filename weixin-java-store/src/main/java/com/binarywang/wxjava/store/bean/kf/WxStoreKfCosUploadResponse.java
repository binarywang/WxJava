package com.binarywang.wxjava.store.bean.kf;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/** 客服素材上传响应。 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WxStoreKfCosUploadResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = 1L;

  /** 素材在 COS 上的地址。 */
  @JsonProperty("cos_url")
  private String cosUrl;
}
