package me.chanjar.weixin.channel.bean.home.tree;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查询分类关联的商品 参数
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeProductListParam implements Serializable {

  private static final long serialVersionUID = -8444106841479328711L;

  /** 参数 */
  @JsonProperty("req")
  private TreeProductListInfo req;
}
