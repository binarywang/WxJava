package me.chanjar.weixin.channel.bean.home.tree;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分类展示信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeShowInfo implements Serializable {

  /** 分类树 */
  @JsonProperty("tree")
  private LevelTreeInfo tree;

  /** 版本号。通过获取商品分类树或者本接口得到 */
  @JsonProperty("version")
  private Integer version;

  /** 表示有哪一些分类ID清空关联得商品,如果不清空,那么分类ID和商品得关联关系会一直存在。如果是一级分类，就填"1"。如果是二级分类，就填"1.2"。 */
  @JsonProperty("classification_id_deleted")
  private List<String> classificationIdDeleted;

  // 一些自定义的方法

  /**
   * 创建Tree节点
   *
   * @return Tree节点
   */
  protected LevelTreeInfo createTree() {
    if (tree == null) {
      tree = new LevelTreeInfo();
    }
    return tree;
  }
}
