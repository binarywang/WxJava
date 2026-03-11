package me.chanjar.weixin.mp.bean.draft;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;

/**
 * 草稿箱能力-商品相关信息
 *
 * @author 阿杆
 * created on 2025/5/23
 */
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class WxMpDraftProductInfo implements Serializable {
  private static final long serialVersionUID = 8637785998127610863L;

  /**
   * 文末插入商品相关信息
   */
  @SerializedName("footer_product_info")
  private FooterProductInfo footerProductInfo;

  public static WxMpDraftProductInfo fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpDraftProductInfo.class);
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class FooterProductInfo {
    /**
     * 商品key
     */
    @SerializedName("product_key")
    private String productKey;
  }

}
