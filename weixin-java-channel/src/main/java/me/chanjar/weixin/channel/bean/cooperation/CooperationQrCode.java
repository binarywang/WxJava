package me.chanjar.weixin.channel.bean.cooperation;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 合作账号二维码数据
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class CooperationQrCode implements Serializable {

  private static final long serialVersionUID = -7096916911986699150L;

  /** base64编码后的图片数据 */
  @JsonProperty("qrcode_base64")
  private Integer qrCodeBase64;

}
