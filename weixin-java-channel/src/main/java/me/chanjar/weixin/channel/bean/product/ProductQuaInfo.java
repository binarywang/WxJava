package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品资质信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductQuaInfo implements Serializable {

  private static final long serialVersionUID = -71766140204505768L;

  /** 商品资质id，对应获取类目信息中的字段product_qua_list[].qua_id */
  @JsonProperty("qua_id")
  private String quaId;

  /** 商品资质图片列表 */
  @JsonProperty("qua_url")
  private List<String> quaUrl;
}
