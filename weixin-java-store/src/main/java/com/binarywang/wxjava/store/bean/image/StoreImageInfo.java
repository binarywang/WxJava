package com.binarywang.wxjava.store.bean.image;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 微信图片信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class StoreImageInfo implements Serializable {

  private static final long serialVersionUID = 8883519290965944530L;

  /** 开放平台media_id */
  @JsonProperty("media_id")
  private String mediaId;

  /** 图片链接，有访问频率限制 */
  @JsonProperty("img_url")
  private String url;

  /** 微信支付media_id */
  @JsonProperty("pay_media_id")
  private String payMediaId;
}
