package me.chanjar.weixin.channel.bean.image;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 微信图片信息响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class UploadImageResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -609315696774437877L;

  /** 图片信息 */
  @JsonProperty("pic_file")
  private ChannelImageInfo imgInfo;
}
