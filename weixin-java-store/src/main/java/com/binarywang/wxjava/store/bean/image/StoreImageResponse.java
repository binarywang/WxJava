package com.binarywang.wxjava.store.bean.image;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.File;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class StoreImageResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = -4163511427507976489L;

  @JsonIgnore
  private File file;

  private String contentType;

  public StoreImageResponse() {
  }

  public StoreImageResponse(File file, String contentType) {
    this.errCode = SUCCESS_CODE;
    this.errMsg = "ok";
    this.file = file;
    this.contentType = contentType;
  }
}
