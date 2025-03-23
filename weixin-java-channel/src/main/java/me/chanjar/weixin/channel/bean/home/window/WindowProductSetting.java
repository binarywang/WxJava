package me.chanjar.weixin.channel.bean.home.window;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 主页商品配置 返回结果 / 设置请求参数
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WindowProductSetting implements Serializable {

  private static final long serialVersionUID = -5931781905709862287L;

  /** 商品id */
  @JsonProperty("product_id")
  private String productId;

  /** 是否隐藏，设置为隐藏的商品只在首页不可见，并不代表下架。 */
  @JsonProperty("is_set_hide")
  private Integer setHide;

  /** 是否置顶 */
  @JsonProperty("is_set_top")
  private Integer setTop;
}
