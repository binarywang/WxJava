package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 赠品信息
 */
@Data
@NoArgsConstructor
public class FreeGiftInfo implements Serializable {

  private static final long serialVersionUID = -4562618835611282016L;

  /**
   * 赠品对应的主品信息
   */
  @JsonProperty("main_product_list")
  private List<MainProductInfo> main_product_list;

}
