package me.chanjar.weixin.channel.bean.fund.qrcode;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 二维码响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class QrCodeResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 4521008628337929496L;
  /** 二维码(base64编码二进制,需要base64解码) */
  @JsonProperty("qrcode_buf")
  private String qrcodeBuf;

}
