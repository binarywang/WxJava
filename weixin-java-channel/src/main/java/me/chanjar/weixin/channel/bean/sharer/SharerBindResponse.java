package me.chanjar.weixin.channel.bean.sharer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 分享员绑定响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.sharer.SharerBindResponse}。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class SharerBindResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 7078787380791500161L;
  /** 邀请二维码的图片二进制base64编码，3天有效 */
  @JsonProperty("qrcode_img_base64")
  private String qrcodeImgBase64;

  public String getQrcodeImgBase64() {
    return qrcodeImgBase64;
  }

  public void setQrcodeImgBase64(String qrcodeImgBase64) {
    this.qrcodeImgBase64 = qrcodeImgBase64;
  }
}
