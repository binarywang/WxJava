package me.chanjar.weixin.channel.bean.window.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 上架商品到橱窗
 * @author imyzt
 * @date 2024/01/27
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.window.request.AddWindowProductRequest}。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Deprecated
public class AddWindowProductRequest {

  /**
   * 橱窗商品ID
   */
  @JsonProperty("product_id")
  private String productId;

  /**
   * 商品来源店铺的appid
   */
  @JsonProperty("appid")
  private String appid;

  /**
   * 是否需要在个人橱窗页隐藏 (默认为false)
   */
  @JsonProperty("is_hide_for_window")
  private Boolean isHideForWindow;

}
