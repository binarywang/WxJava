package me.chanjar.weixin.channel.bean.image;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.File;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.image.StoreImageResponse}。
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class ChannelImageResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -4163511427507976489L;

  @JsonIgnore
  private File file;

  private String contentType;

  public ChannelImageResponse() {
  }

  public ChannelImageResponse(File file, String contentType) {
    this.errCode = SUCCESS_CODE;
    this.errMsg = "ok";
    this.file = file;
    this.contentType = contentType;
  }
}
