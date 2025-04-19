package me.chanjar.weixin.channel.bean.home.window;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 主页商品排序参数
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WindowProductIndexParam implements Serializable {

  private static final long serialVersionUID = 1370480140179330908L;

  /** 商品id */
  @JsonProperty("product_id")
  private String productId;

  /** 商品重新排序后的新序号，最大移动步长为500（即新序号与当前序号的距离小于500） */
  @JsonProperty("index_num")
  private Integer indexNum;
}
