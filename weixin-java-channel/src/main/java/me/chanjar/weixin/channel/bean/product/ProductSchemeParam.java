package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 获取商品移动应用跳转 scheme 码请求参数.
 *
 * @author GitHub Copilot
 */
@Data
@NoArgsConstructor
public class ProductSchemeParam implements Serializable {

  private static final long serialVersionUID = 613832623081127830L;

  /** 商品ID. */
  @JsonProperty("product_id")
  private String productId;

  /** 来源appid. */
  @JsonProperty("from_appid")
  private String fromAppid;

  /** 过期时间（秒）. */
  private Integer expire;

  /** 附加信息. */
  @JsonProperty("ext_info")
  private String extInfo;
}
