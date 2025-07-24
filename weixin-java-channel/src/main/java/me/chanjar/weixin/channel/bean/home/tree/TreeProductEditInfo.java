package me.chanjar.weixin.channel.bean.home.tree;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 添加/删除分类关联的商品 参数
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeProductEditInfo implements Serializable {

  private static final long serialVersionUID = -5596947592282082891L;

  /** 一级分类id */
  @JsonProperty("level_1_id")
  private Integer level1Id;

  /** 二级分类id */
  @JsonProperty("level_2_id")
  private Integer level2Id;

  /** 商品id列表 */
  @JsonProperty("product_ids")
  private List<Long> productIds;
}
