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
public class TreeProductListInfo implements Serializable {

  private static final long serialVersionUID = 2774682583380930076L;

  /** 一级分类id */
  @JsonProperty("level_1_id")
  private Integer level1Id;

  /** 二级分类id */
  @JsonProperty("level_2_id")
  private Integer level2Id;

  /** 分页大小 */
  @JsonProperty("page_size")
  private Integer pageSize;

  /** 从头拉取填空。翻页拉取的话填resp返回的值 */
  @JsonProperty("page_context")
  private String pageContext;
}
