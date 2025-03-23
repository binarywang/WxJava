package me.chanjar.weixin.channel.bean.home.banner;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 精选展示位明细中的商品
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class BannerItemProduct implements Serializable {

  private static final long serialVersionUID = 8034487065591522594L;

  /** 商品id */
  @JsonProperty("product_id")
  private Long productId;
}
