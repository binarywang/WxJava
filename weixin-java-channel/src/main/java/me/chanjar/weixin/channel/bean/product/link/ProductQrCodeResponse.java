package me.chanjar.weixin.channel.bean.product.link;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 商品二维码 结果
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.product.link.ProductQrCodeResponse}。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class ProductQrCodeResponse extends WxChannelBaseResponse {

  /** 商品二维码 */
  @JsonProperty("product_qrcode")
  private String productQrcode;
}
