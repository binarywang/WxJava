package me.chanjar.weixin.channel.bean.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品类目
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class ShopCategory implements Serializable {

  /** 类目ID */
  @JsonProperty("cat_id")
  private String id;

  /** 类目父ID */
  @JsonProperty("f_cat_id")
  private String parentId;

  /** 类目名称 */
  @JsonProperty("name")
  private String name;

  /** 层级 */
  @JsonProperty("level")
  private Integer level;

  /** 是否为叶子类目（品类） */
  @JsonProperty("leaf")
  private Boolean leaf;
}
