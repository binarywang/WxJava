package com.binarywang.wxjava.store.bean.image;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 微信图片信息响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UploadImageResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = -609315696774437877L;

  /** 图片信息 */
  @JsonProperty("pic_file")
  private StoreImageInfo imgInfo;
}
