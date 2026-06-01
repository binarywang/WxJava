package me.chanjar.weixin.channel.bean.gift;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 获取赠品 响应
 *
 * @author <a href="https://github.com/features/copilot">GitHub Copilot</a>
 * @see <a href="https://developers.weixin.qq.com/doc/store/shop/API/channels-shop-product/gift/api_getgiftproduct.html">获取赠品</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GiftGetResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -1L;

  /** 赠品线上数据（data_type!=2 时返回） */
  @JsonProperty("product")
  private GiftProduct product;

  /** 赠品草稿数据（data_type!=1 时返回） */
  @JsonProperty("edit_product")
  private GiftProduct editProduct;
}
